package eu.cloudscaleproject.env.csm2pcm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.diff.merge.service.MergeService;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeLeftTarget;
import org.eclipse.emf.compare.diff.service.DiffService;
import org.eclipse.emf.compare.match.MatchOptions;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.compare.match.service.MatchService;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.m2m.qvt.oml.BasicModelExtent;
import org.eclipse.m2m.qvt.oml.ExecutionContextImpl;
import org.eclipse.m2m.qvt.oml.ExecutionDiagnostic;
import org.eclipse.m2m.qvt.oml.ModelExtent;
import org.eclipse.m2m.qvt.oml.TransformationExecutor;

import eu.cloudscaleproject.env.csm.Csm;
import eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer;
import eu.cloudscaleproject.env.csm.architecture.SoftwareService;
import eu.cloudscaleproject.env.csm.converter.ICSMConverterCallback;
import eu.cloudscaleproject.env.csm.converter.ICsmConverter;
import eu.cloudscaleproject.env.csm.core.Entity;
import eu.cloudscaleproject.env.csm.impl.CsmPackageImpl;
import eu.cloudscaleproject.env.csm2pcm.PalladioUtil.ModelID;

public class CsmConverter implements ICsmConverter{
	
	private static final String ENTITY_ID_PREFIX = PalladioModel.DEFAULT_MODEL_ID;
	private static final String CSM2PCM_QVTO = "transforms/csm2pcm.qvto";
	
	private ExecutionContextImpl context;
	private TransformationExecutor executor;
	
	private final PalladioModel[] currentModels = new PalladioModel[5];
	
	public CsmConverter() {
		
		// If not removed, ClassCastException...
		EPackage.Registry.INSTANCE.remove ("http://org.somox/qimpressgast");
	}
	
	private void init(){
		
		this.executor = new TransformationExecutor(URI.createURI("platform:/plugin/eu.cloudscaleproject.env.csm2pcm/"+CSM2PCM_QVTO));
		this.executor.loadTransformation();

		this.context = new ExecutionContextImpl();
		// configuration properties, logger, parameters.
		context.setConfigProperty("keepModeling", true);
		context.setLog(new TransformationLogger());
		context.setConfigProperty("CSMID", ENTITY_ID_PREFIX);
		
		context.setConfigProperty("PCM_SYSTEM_KEY", ICsmConverter.KEY_PCM_SYSTEM);
		context.setConfigProperty("PCM_INTERFACE_KEY", ICsmConverter.KEY_PCM_INTERFACE);
	}
	
	private void merge(EObject currentRoot, EObject newRoot){
		MatchModel match;
		DiffModel diff;
		try {
			Map<String, Object> options = new HashMap<String, Object>();
			options.put(MatchOptions.OPTION_IGNORE_XMI_ID, true);
			
			//match = MatchService.doContentMatch(newRoot, currentRoot, options);
			match = MatchService.doContentMatch(currentRoot, newRoot, options);
			diff = DiffService.doDiff(match, false);
			
			List<DiffElement> filtered = new ArrayList<DiffElement>();
			for(DiffElement element : diff.getDifferences()){
				
				System.out.println("All diffs: " + "kind: "+element.getKind().getName() + " :__to_string__: " + element.toString());
				
				if(element instanceof ModelElementChangeLeftTarget){
					if(((ModelElementChangeLeftTarget) element).getLeftElement()
						instanceof de.uka.ipd.sdq.pcm.core.entity.Entity){
						de.uka.ipd.sdq.pcm.core.entity.Entity entity = (de.uka.ipd.sdq.pcm.core.entity.Entity)
							((ModelElementChangeLeftTarget) element).getLeftElement();
						if(entity.getId().startsWith(ENTITY_ID_PREFIX)){
							continue;
						}
						if(entity.eIsProxy()){
							continue;
						}
					}
					System.out.println(element.getKind().getName() + " Left: " + element.toString());
					filtered.add(element);
				}
				/*
				else if(element instanceof ModelElementChangeRightTarget){
					if(((ModelElementChangeRightTarget) element).getRightElement()
						instanceof de.uka.ipd.sdq.pcm.core.entity.Entity){
						de.uka.ipd.sdq.pcm.core.entity.Entity entity = (de.uka.ipd.sdq.pcm.core.entity.Entity)
							((ModelElementChangeRightTarget) element).getRightElement();
						if(entity.getId().startsWith(ENTITY_ID_PREFIX)){
							continue;
						}
					}
					System.out.println(element.getKind().getName() + " Right: " + element.toString());
					filtered.add(element);
				}
				*/
				else{
					/*
					filtered.add(element);
					System.out.println(element.getKind().getName() + " Unhandled: " + element.toString());
					*/
				}
			}
			if(!filtered.isEmpty()){
				MergeService.merge(filtered, true);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void transform(Resource csmResource){
		
		init();
		
		URI outDiagramPrefix = getGeneratedFolder(csmResource);
		
		// Just in case
		if (outDiagramPrefix == null) 
		{
			outDiagramPrefix = csmResource.getURI().trimSegments(1).appendSegment("pcm");
		}
		
		URI outModelPrefix = outDiagramPrefix.appendSegment("models");
		
		this.currentModels[PalladioUtil.ModelID.REPOSITORY.ordinal()] = 
				new PalladioModel(outModelPrefix, outDiagramPrefix, PalladioUtil.ModelID.REPOSITORY);
		this.currentModels[PalladioUtil.ModelID.SYSTEM.ordinal()] = 
				new PalladioModel(outModelPrefix, outDiagramPrefix, PalladioUtil.ModelID.SYSTEM);
		this.currentModels[PalladioUtil.ModelID.RESOURCE.ordinal()] = 
				new PalladioModel(outModelPrefix, outDiagramPrefix, PalladioUtil.ModelID.RESOURCE);
		this.currentModels[PalladioUtil.ModelID.ALLOCATION.ordinal()] = 
				new PalladioModel(outModelPrefix, outDiagramPrefix, PalladioUtil.ModelID.ALLOCATION);
		this.currentModels[PalladioUtil.ModelID.USAGE.ordinal()] = 
				new PalladioModel(outModelPrefix, outDiagramPrefix, PalladioUtil.ModelID.USAGE);
		
		// define the transformation input
		EList<EObject> inObjectsCsm = csmResource.getContents();
		
		EcoreUtil.resolveAll(inObjectsCsm.get(0));
		
		ResourceSet resSet = new ResourceSetImpl();
		URI inResTypeUri = URI.createURI("pathmap://PCM_MODELS/Palladio.resourcetype");
		Resource resTypesResource = resSet.getResource(inResTypeUri, true);
		EList<EObject> inObjectsResTypes = resTypesResource.getContents();
		
		ResourceSet dataSet = new ResourceSetImpl();
		URI inDataTypeUri = URI.createURI("pathmap://PCM_MODELS/PrimitiveTypes.repository");
		Resource dataTypesResource = dataSet.getResource(inDataTypeUri, true);
		EList<EObject> inObjectsDataTypes = dataTypesResource.getContents();
		
		// create the input extent with its initial contents
		ModelExtent inputCsm = new BasicModelExtent(inObjectsCsm);		
		ModelExtent inputResTypes = new BasicModelExtent(inObjectsResTypes);		
		ModelExtent inputDataTypes = new BasicModelExtent(inObjectsDataTypes);
		
		ModelExtent outputResource = new BasicModelExtent();
		ModelExtent outputRepository = new BasicModelExtent();
		ModelExtent outputSystem = new BasicModelExtent();
		ModelExtent outputAllocation = new BasicModelExtent();
		ModelExtent outputUsage = new BasicModelExtent();
		
		
		// run the transformation assigned to the executor with the given 
		// input and output and execution context -> ChangeTheWorld(in, out)
		// Remark: variable arguments count is supported
		ExecutionDiagnostic result = executor.execute(context, 
													  inputCsm, inputResTypes, inputDataTypes,
													  outputResource, outputRepository, outputSystem, outputAllocation, outputUsage);

		// check the result for success
		if(result.getSeverity() == Diagnostic.OK) {
			
			CsmPackageImpl.eINSTANCE.eClass();
			EcoreUtil.resolveAll(PalladioModel.resourceSet);
			// the output objects got captured in the output extent
			final EObject[] newObjects = new EObject[5];
			
			newObjects[PalladioUtil.ModelID.RESOURCE.ordinal()]   = outputResource.getContents().get(0);
			newObjects[PalladioUtil.ModelID.REPOSITORY.ordinal()] = outputRepository.getContents().get(0);
			newObjects[PalladioUtil.ModelID.SYSTEM.ordinal()]     = outputSystem.getContents().get(0);
			newObjects[PalladioUtil.ModelID.ALLOCATION.ordinal()] = outputAllocation.getContents().get(0);
			newObjects[PalladioUtil.ModelID.USAGE.ordinal()]      = outputUsage.getContents().get(0);
			
			for(int i=0; i<5; i++){
				PalladioModel model = this.currentModels[i];
				model.getResourceDiagram().setTrackingModification(false);
			}
			for(int i=0; i<5; i++){
				PalladioModel model = this.currentModels[i];
				model.loadModelResource();
				merge(model.getModel(), newObjects[i]);
				model.setModel(newObjects[i]);
			}
			
			//clean up
			for(int i=0; i<5; i++){
				final PalladioModel model = this.currentModels[i];
				model.unloadDiagramResource();
				model.saveModelResource();

				model.getResourceDiagram().setTrackingModification(true);
				
				model.loadDiagramResource();
				model.saveDiagramResource();
				model.getResourceDiagram().setModified(false);
				model.getResourceDiagram().setTimeStamp(System.currentTimeMillis());
			}
			
		} 
		
		/*new Job ("tt")
		{@Override
		protected IStatus run(IProgressMonitor monitor) {
			// TODO Auto-generated method stub

			currentModels[0].getResourceDiagram().setTrackingModification(false);
			CreateShortcutAction.execute(currentModels[0].getDiagram());
			currentModels[0].getResourceDiagram().setTrackingModification(true);
			return Status.OK_STATUS;
		
		}}.schedule(5000);*/

		IStatus status = BasicDiagnostic.toIStatus(result);
		Activator.getDefault().getLog().log(status);
	}
	
	private URI getGeneratedFolder(Resource csmResource) {
		try
		{
			// Find project, load project.proerties and get generated models folder location
			String ps = csmResource.getURI().toPlatformString(true);
			IResource rcpResource = ResourcesPlugin.getWorkspace().getRoot().findMember(ps);
			
			IProject project = rcpResource.getProject();
			IFile file = project.getFile("project.properties");
			Properties p = new Properties();
			p.load(file.getContents(true));
			String genFolderName = p.getProperty("generated-folder");
			IFolder folder = project.getFolder(genFolderName);
			if (!folder.exists())
				folder.create(true, true, new NullProgressMonitor());
				
			return URI.createPlatformResourceURI(folder.getFullPath().toString(), true);
		}
		catch (Exception e)
		{
			System.out.println("Exception durring finding generated models folder: "+e.getMessage());
		}
		
		return null;
	}

	@Override
	public boolean canTransform(Resource resource){
		EList<EObject> inObjects = resource.getContents();
		
		for(EObject object : inObjects){
			if(object instanceof Csm){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean addExternalModel(final Entity entity, final List<EObject> external, ICSMConverterCallback callback) {
		
		if(!(entity instanceof SoftwareService)){
			throw new UnsupportedOperationException("Model import not supported for this object type");
		}
		
		//set new ID's for imported models.
		/*
		for (EObject obj : external) {
			
			try {
				Resource r = obj.eResource();
				r.unload();
				r.load(null);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			System.out.println(obj.toString());
			TreeIterator<EObject> iter = EcoreUtil.getAllProperContents(obj, true); 
			while(iter.hasNext()){
				EObject current = iter.next();
				if(current instanceof de.uka.ipd.sdq.pcm.core.entity.Entity){
					de.uka.ipd.sdq.pcm.core.entity.Entity e = (de.uka.ipd.sdq.pcm.core.entity.Entity)current;
					e.setId(ENTITY_ID_PREFIX + e.getId());
					System.out.println(e.getEntityName());
				}
			}
		
			try {
				obj.eResource().save(null);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		*/
		
		// Create diagrams
		new Job("Imported models : Create diagrams") {
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				for (EObject obj : external) {
					
					ModelID modelId = PalladioUtil.getModelId (obj);
					
					// Create diagramResource -- located in the same folder as model resource
					URI folder = obj.eResource().getURI().trimSegments(1);
					URI diagramURI = PalladioUtil.createDiagramURI(modelId, folder);
					Resource diagramResource = obj.eResource().getResourceSet().createResource(diagramURI);
					
					// Create diagram add to resource and save it
					try {
						Diagram diagram = PalladioUtil.createDiagram(modelId, obj);
						diagramResource.getContents().add(diagram);
						diagramResource.save(null);
						diagramResource.unload();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				return Status.OK_STATUS;
			}
		}.schedule();
		
		return CSMImport.addPcmToOpInterfaceContainer((OperationInterfaceContainer)entity, external, callback);
	}

	@Override
	public boolean removeExternalModel(Entity entity, List<EObject> external, ICSMConverterCallback callback) {

		if(!(entity instanceof SoftwareService)){
			throw new UnsupportedOperationException("Model import not supported for this object type");
		}

		return CSMImport.removePcmFromOpInterfaceContainer((OperationInterfaceContainer)entity, callback);
	}
}
