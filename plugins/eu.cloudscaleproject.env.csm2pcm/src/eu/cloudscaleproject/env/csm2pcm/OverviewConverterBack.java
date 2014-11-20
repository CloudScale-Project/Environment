package eu.cloudscaleproject.env.csm2pcm;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.match.DefaultComparisonFactory;
import org.eclipse.emf.compare.match.DefaultEqualityHelperFactory;
import org.eclipse.emf.compare.match.DefaultMatchEngine;
import org.eclipse.emf.compare.match.IComparisonFactory;
import org.eclipse.emf.compare.match.IMatchEngine;
import org.eclipse.emf.compare.match.eobject.IEObjectMatcher;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryImpl;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryRegistryImpl;
import org.eclipse.emf.compare.merge.BatchMerger;
import org.eclipse.emf.compare.merge.IMerger;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.compare.utils.UseIdentifiers;
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
import org.scaledl.overview.Overview;
import org.scaledl.overview.OverviewPackage;
import org.scaledl.overview.application.OperationInterfaceContainer;
import org.scaledl.overview.architecture.SoftwareService;
import org.scaledl.overview.converter.IOverviewConverter;
import org.scaledl.overview.converter.IOverviewConverterCallback;
import org.scaledl.overview.core.Entity;

import com.google.common.base.Predicate;

import eu.cloudscaleproject.env.analyser.InputAlternative;
import eu.cloudscaleproject.env.analyser.ResourceUtils;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.csm2pcm.PalladioUtil.ModelID;

public class OverviewConverterBack implements IOverviewConverter{
	
	private static final String ENTITY_ID_PREFIX = PalladioModel.DEFAULT_MODEL_ID;
	private static final String CSM2PCM_QVTO = "transforms/csm2pcm.qvto";
	
	private ExecutionContextImpl context;
	private TransformationExecutor executor;
	
	//TODO: this resource sets should not be static! If they are not, the merge don't work properly!
	//		Find workaround for this.
	//private static final ResourceSet resoultResSet = new ResourceSetImpl();
	//private static final ResourceSet oldGenResSet = new ResourceSetImpl();
	//private static final ResourceSet newGenResSet = new ResourceSetImpl();
	
	private final PalladioModel[] resultModels = new PalladioModel[5];
	//private final PalladioModel[] oldGenModels = new PalladioModel[5];
	//private final PalladioModel[] newGenModels = new PalladioModel[5];
	
	public OverviewConverterBack() {
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
		
		context.setConfigProperty("PCM_SYSTEM_KEY", IOverviewConverter.KEY_PCM_SYSTEM);
		context.setConfigProperty("PCM_INTERFACE_KEY", IOverviewConverter.KEY_PCM_INTERFACE);
	}
	
	//Model merge disabled for now, so set output of the transformation directly to results.
	/*
	private Comparison compare(Notifier originObject, Notifier newObject, Notifier resultObject) {

		// Configure EMF Compare
		IEObjectMatcher matcher = DefaultMatchEngine.createDefaultEObjectMatcher(UseIdentifiers.NEVER);
		
		IComparisonFactory comparisonFactory = new DefaultComparisonFactory(new DefaultEqualityHelperFactory());
		IMatchEngine.Factory matchEngineFactory = new MatchEngineFactoryImpl(matcher, comparisonFactory);
		matchEngineFactory.setRanking(20);
		
		IMatchEngine.Factory.Registry matchEngineRegistry = new MatchEngineFactoryRegistryImpl();
		matchEngineRegistry.add(matchEngineFactory);
		
		EMFCompare comparator = EMFCompare.builder().setMatchEngineFactoryRegistry(matchEngineRegistry).build();
	 
		// Compare the two models
		IComparisonScope scope = new DefaultComparisonScope(newObject, resultObject, originObject);
		return comparator.compare(scope);
	}
	
	private void merge(Notifier originObject, Notifier newObject, Notifier resoultObject, Predicate<? super Diff> predicate){
		
		Comparison comparison = compare(originObject, newObject, resoultObject);
		BatchMerger merger = null;
		
		if(predicate != null){
			merger = new BatchMerger(IMerger.RegistryImpl.createStandaloneInstance(), predicate);
		}
		else{
			merger = new BatchMerger(IMerger.RegistryImpl.createStandaloneInstance());
		}
		
		merger.copyAllLeftToRight(comparison.getDifferences(), null);
	}
	*/
	
	@Override
	public void transform(Resource csmResource){
		
		init();
		
		IProject project = ExplorerProjectPaths.getProjectFromEmfResource(csmResource);
		//IFolder genFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_GENERATED);
		
		IFolder analyserFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_ANALYSER);
		IFolder analyserInput = analyserFolder.getFolder(ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_INPUT));
		
		String analyserGenPath = ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_GENERATED);
		IFolder analyserGen = analyserInput.getFolder(analyserGenPath);
		if(!analyserGen.exists()){
			try {
				analyserGen.create(true, true, new NullProgressMonitor());
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
		URI analyserGenFolderUri = URI.createPlatformResourceURI(analyserGen.getFullPath().toString(), true);
		//URI genFolderUri = URI.createPlatformResourceURI(genFolder.getFullPath().toString(), true);
		
		URI outDiagramPrefix = analyserGenFolderUri;
		URI outModelPrefix = analyserGenFolderUri.appendSegment("models");
		
		//Model merge disabled for now, so set output of the transformation directly to results.
		/*
		URI outModelGenPrefix = genFolderUri.appendSegment("gen");
		URI outModelTmpGenPrefix = genFolderUri.appendSegment("gen").appendSegment("tmp");
		
		this.oldGenModels[PalladioUtil.ModelID.REPOSITORY.ordinal()] = 
				new PalladioModel(oldGenResSet, outModelGenPrefix, null, PalladioUtil.ModelID.REPOSITORY);
		this.oldGenModels[PalladioUtil.ModelID.SYSTEM.ordinal()] = 
				new PalladioModel(oldGenResSet, outModelGenPrefix, null, PalladioUtil.ModelID.SYSTEM);
		this.oldGenModels[PalladioUtil.ModelID.RESOURCE.ordinal()] = 
				new PalladioModel(oldGenResSet, outModelGenPrefix, null, PalladioUtil.ModelID.RESOURCE);
		this.oldGenModels[PalladioUtil.ModelID.ALLOCATION.ordinal()] = 
				new PalladioModel(oldGenResSet, outModelGenPrefix, null, PalladioUtil.ModelID.ALLOCATION);
		this.oldGenModels[PalladioUtil.ModelID.USAGE.ordinal()] = 
				new PalladioModel(oldGenResSet, outModelGenPrefix, null, PalladioUtil.ModelID.USAGE);
		
		this.newGenModels[PalladioUtil.ModelID.REPOSITORY.ordinal()] = 
				new PalladioModel(newGenResSet, outModelTmpGenPrefix, null, PalladioUtil.ModelID.REPOSITORY);
		this.newGenModels[PalladioUtil.ModelID.SYSTEM.ordinal()] = 
				new PalladioModel(newGenResSet, outModelTmpGenPrefix, null, PalladioUtil.ModelID.SYSTEM);
		this.newGenModels[PalladioUtil.ModelID.RESOURCE.ordinal()] = 
				new PalladioModel(newGenResSet, outModelTmpGenPrefix, null, PalladioUtil.ModelID.RESOURCE);
		this.newGenModels[PalladioUtil.ModelID.ALLOCATION.ordinal()] = 
				new PalladioModel(newGenResSet, outModelTmpGenPrefix, null, PalladioUtil.ModelID.ALLOCATION);
		this.newGenModels[PalladioUtil.ModelID.USAGE.ordinal()] = 
				new PalladioModel(newGenResSet, outModelTmpGenPrefix, null, PalladioUtil.ModelID.USAGE);
		*/
		
		ResourceSet resultResSet = new ResourceSetImpl();
		this.resultModels[PalladioUtil.ModelID.REPOSITORY.ordinal()] = 
				new PalladioModel(resultResSet, outModelPrefix, outDiagramPrefix, PalladioUtil.ModelID.REPOSITORY);
		this.resultModels[PalladioUtil.ModelID.SYSTEM.ordinal()] = 
				new PalladioModel(resultResSet, outModelPrefix, outDiagramPrefix, PalladioUtil.ModelID.SYSTEM);
		this.resultModels[PalladioUtil.ModelID.RESOURCE.ordinal()] = 
				new PalladioModel(resultResSet, outModelPrefix, outDiagramPrefix, PalladioUtil.ModelID.RESOURCE);
		this.resultModels[PalladioUtil.ModelID.ALLOCATION.ordinal()] = 
				new PalladioModel(resultResSet, outModelPrefix, outDiagramPrefix, PalladioUtil.ModelID.ALLOCATION);
		this.resultModels[PalladioUtil.ModelID.USAGE.ordinal()] = 
				new PalladioModel(resultResSet, outModelPrefix, outDiagramPrefix, PalladioUtil.ModelID.USAGE);
		
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
			
			OverviewPackage.eINSTANCE.eClass();
			
			//Model merge disabled for now, so set output of the transformation directly to results.
			//EcoreUtil.resolveAll(resoultResSet);
			//EcoreUtil.resolveAll(oldGenResSet);
			
			// the output objects got captured in the output extent
			final EObject[] newObjects = new EObject[5];
			
			newObjects[PalladioUtil.ModelID.RESOURCE.ordinal()]   = outputResource.getContents().isEmpty() ? null : outputResource.getContents().get(0);
			newObjects[PalladioUtil.ModelID.REPOSITORY.ordinal()] = outputRepository.getContents().isEmpty() ? null : outputRepository.getContents().get(0);
			newObjects[PalladioUtil.ModelID.SYSTEM.ordinal()]     = outputSystem.getContents().isEmpty() ? null : outputSystem.getContents().get(0);
			newObjects[PalladioUtil.ModelID.ALLOCATION.ordinal()] = outputAllocation.getContents().isEmpty() ? null : outputAllocation.getContents().get(0);
			newObjects[PalladioUtil.ModelID.USAGE.ordinal()]      = outputUsage.getContents().isEmpty() ? null : outputUsage.getContents().get(0);
			
			//set transformed model to newGenModel
			for(int i=0; i<5; i++){
				
				if(newObjects[i] == null){continue;}
				
				//Model merge disabled for now, so set output of the transformation directly to results.
				//OLD
				//PalladioModel newGenModel = this.newGenModels[i];
				//newGenModel.setModel(newObjects[i]);
				//NEW
				//TODO: fix merge and remove this block!
				PalladioModel resModel = this.resultModels[i];
				resModel.setModel(newObjects[i]);
				////////////////////////////////////////////////////////////////////////////////////////
			}
			
			//added for disabling marge
			//TODO: remove this block
			for(int i=0; i<5; i++){
				
				if(newObjects[i] == null){continue;}
				
				final PalladioModel resModel = this.resultModels[i];
				resModel.saveModelResource();
				resModel.saveDiagramResource();
				resModel.loadModelResource();
				resModel.loadDiagramResource();
			}
			//////////////////////////
			
			//****************
			//Removed because of merge
			//TODO: fix merge and uncomment this bloc!
			/*START
			
			//save new gen model and load existing
			for(int i=0; i<5; i++){
				
				if(newObjects[i] == null){continue;}
				
				PalladioModel newGenModel = this.newGenModels[i];
				newGenModel.saveModelResource();
				
				this.oldGenModels[i].loadModelResource();
				this.resultModels[i].loadModelResource();
			}
			
			//set track modifications false
			for(int i=0; i<5; i++){
				
				if(newObjects[i] == null){continue;}
				
				PalladioModel model = this.resultModels[i];
				model.getResourceDiagram().setTrackingModification(false);
			}
			
			//merge
			merge(oldGenResSet, newGenResSet, resoultResSet, fromSide(DifferenceSource.LEFT));
			
			//clean up
			for(int i=0; i<5; i++){
				
				if(newObjects[i] == null){continue;}
				
				final PalladioModel model = this.resultModels[i];
				model.unloadDiagramResource();
				model.saveModelResource();

				model.getResourceDiagram().setTrackingModification(true);
				
				model.loadDiagramResource();
				model.saveDiagramResource();
				model.getResourceDiagram().setModified(false);
				model.getResourceDiagram().setTimeStamp(System.currentTimeMillis());
			}
			
			//set unmerged model
			for(int i=0; i<5; i++){
				
				if(newObjects[i] == null){continue;}
				
				final PalladioModel model = this.oldGenModels[i];
				model.setModel(newObjects[i]);
			}
			//save unmerged model
			for(int i=0; i<5; i++){
				
				if(newObjects[i] == null){continue;}
				
				final PalladioModel model = this.oldGenModels[i];
				model.saveModelResource();
			}
			
			END*/
			
			//create or set analyser generated input alternative
			InputAlternative ia = ResourceUtils.getGeneratedResourceInput(project);
			ia.setAllocation(ExplorerProjectPaths.getFileFromEmfResource(this.resultModels[PalladioUtil.ModelID.ALLOCATION.ordinal()].getResource()));
			ia.setUsage(ExplorerProjectPaths.getFileFromEmfResource(this.resultModels[PalladioUtil.ModelID.USAGE.ordinal()].getResource()));
			ia.save();
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
