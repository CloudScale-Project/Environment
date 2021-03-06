package eu.cloudscaleproject.env.csm2pcm;

import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.m2m.qvt.oml.BasicModelExtent;
import org.eclipse.m2m.qvt.oml.ExecutionContextImpl;
import org.eclipse.m2m.qvt.oml.ExecutionDiagnostic;
import org.eclipse.m2m.qvt.oml.ModelExtent;
import org.eclipse.m2m.qvt.oml.TransformationExecutor;
import org.scaledl.overview.Overview;
import org.scaledl.overview.OverviewPackage;
import org.scaledl.overview.application.OperationInterfaceContainer;
import org.scaledl.overview.architecture.SoftwareService;
import org.scaledl.overview.converter.IOverviewConverter;
import org.scaledl.overview.converter.IOverviewConverterCallback;
import org.scaledl.overview.core.Entity;

import eu.cloudscaleproject.env.analyser.PCMResourceSet;
import eu.cloudscaleproject.env.toolchain.ModelType;

public class OverviewConverter implements IOverviewConverter{
	
	private static final String ENTITY_ID_PREFIX = PalladioModel.DEFAULT_MODEL_ID;
	private static final String CSM2PCM_QVTO = "transforms/csm2pcm.qvto";
	
	private static final ModelType[] ModelTypes = new ModelType[]{ModelType.REPOSITORY,
																		ModelType.SYSTEM,
																		ModelType.RESOURCE,
																		ModelType.ALLOCATION,
																		ModelType.USAGE};
	
	//private HashMap<IProject, PCMResourceSet> resourceSetMap = new HashMap<IProject, PCMResourceSet>();
	
	public OverviewConverter() {
		// If not removed, ClassCastException...
		EPackage.Registry.INSTANCE.remove ("http://org.somox/qimpressgast");
	}
	
	@Override
	public void transform(Resource csmResource, IFolder outputFolder){
		
		// Initialize QVTO transformation
		TransformationExecutor executor = new TransformationExecutor(URI.createURI("platform:/plugin/eu.cloudscaleproject.env.csm2pcm/"+CSM2PCM_QVTO));
		executor.loadTransformation();
		ExecutionContextImpl context = new ExecutionContextImpl();
		// configuration properties, logger, parameters.
		context.setConfigProperty("keepModeling", true);
		context.setLog(new TransformationLogger());
		context.setConfigProperty("CSMID", ENTITY_ID_PREFIX);
		context.setConfigProperty("PCM_SYSTEM_KEY", IOverviewConverter.KEY_PCM_SYSTEM);
		context.setConfigProperty("PCM_INTERFACE_KEY", IOverviewConverter.KEY_PCM_INTERFACE);
		context.setConfigProperty("PCM_OPERATION_KEY", IOverviewConverter.KEY_PCM_OPERATION);
		
		//IProject project = ExplorerProjectPaths.getProjectFromEmfResource(csmResource);
		
		/*
		PCMResourceSet resSet = resourceSetMap.get(project);
		if(resSet == null){
			resSet = new PCMResourceSet(outputFolder);
			resSet.createAll(ModelTypes);
			
			resourceSetMap.put(project, resSet);
			
		}
		*/
		PCMResourceSet resSet = new PCMResourceSet(outputFolder);
		resSet.createAll(ModelTypes);
				
		// define the transformation input
		EList<EObject> inObjectsCsm = csmResource.getContents();
		EcoreUtil.resolveAll(inObjectsCsm.get(0));
		
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
		
		ExecutionDiagnostic result = executor.execute(context, 
													  inputCsm, inputResTypes, inputDataTypes,
													  outputResource, outputRepository, outputSystem, outputAllocation, outputUsage);
		
		// check the result for success
		if(result.getSeverity() == Diagnostic.OK) {
			
			OverviewPackage.eINSTANCE.eClass();
			
			resSet.clearAll(ModelTypes);
						
			resSet.setRootObject(ModelType.RESOURCE, 
					outputResource.getContents().isEmpty() ? null : outputResource.getContents().get(0));
			resSet.setRootObject(ModelType.REPOSITORY, 
					outputRepository.getContents().isEmpty() ? null : outputRepository.getContents().get(0));
			resSet.setRootObject(ModelType.SYSTEM, 
					outputSystem.getContents().isEmpty() ? null : outputSystem.getContents().get(0));
			resSet.setRootObject(ModelType.ALLOCATION, 
					outputAllocation.getContents().isEmpty() ? null : outputAllocation.getContents().get(0));
			resSet.setRootObject(ModelType.USAGE, 
					outputUsage.getContents().isEmpty() ? null : outputUsage.getContents().get(0));
			
			resSet.saveAll(ModelTypes);
			
			//create or set analyser generated input alternative
			
			//open dialog
			/*
			InputAlternative ia = ResourceUtils.getGeneratedResourceInput(project);
			ia.setAllocation(resSet.getModelFile(PCMModelType.ALLOCATION));
			ia.setSubResource(ToolchainUtils.KEY_FILE_USAGE, resSet.getModelFile(PCMModelType.USAGE));
			ia.save();
			*/
		} 

		IStatus status = BasicDiagnostic.toIStatus(result);
		Activator.getDefault().getLog().log(status);
	}

	@Override
	public boolean canTransform(Resource resource){
		EList<EObject> inObjects = resource.getContents();
		
		for(EObject object : inObjects){
			if(object instanceof Overview){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean addExternalModel(final Entity entity, final List<EObject> external, IOverviewConverterCallback callback) {
		
		if(!(entity instanceof SoftwareService)){
			throw new UnsupportedOperationException("Model import not supported for this object type");
		}
		
		// Create diagrams
		/*
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
		*/
		
		return OverviewImport.addPcmToOpInterfaceContainer((OperationInterfaceContainer)entity, external, callback);
	}

	@Override
	public boolean removeExternalModel(Entity entity, List<EObject> external, IOverviewConverterCallback callback) {

		if(!(entity instanceof SoftwareService)){
			throw new UnsupportedOperationException("Model import not supported for this object type");
		}

		return OverviewImport.removePcmFromOpInterfaceContainer((OperationInterfaceContainer)entity, callback);
	}
}
