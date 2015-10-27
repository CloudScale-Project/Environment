package eu.cloudscaleproject.env.csm2pcm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
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
import org.scaledl.overview.OverviewPackage;
import org.scaledl.overview.converter.IOverviewConverter;

import eu.cloudscaleproject.env.analyser.PCMResourceSet;
import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.common.BasicCallback;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.overview.OverviewAlternative;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;

public class OverviewConverterNew{
	
	private static OverviewConverterNew instance = null;
	
	public static OverviewConverterNew getInstance(){
		if(instance == null){
			instance = new OverviewConverterNew();
		}
		return instance;
	}
	
	private static final Logger logger = Logger.getLogger(OverviewConverterNew.class.getName());
	
	private static final String ENTITY_ID_PREFIX = PalladioModel.DEFAULT_MODEL_ID;
	private static final String CSM2PCM_QVTO = "transforms/csm2pcm.qvto";
	
	private static final ModelType[] ModelTypes = new ModelType[]{ModelType.REPOSITORY,
																		ModelType.SYSTEM,
																		ModelType.RESOURCE,
																		ModelType.ALLOCATION,
																		ModelType.USAGE};
		
	public OverviewConverterNew() {
		// If not removed, ClassCastException...
		EPackage.Registry.INSTANCE.remove ("http://org.somox/qimpressgast");
	}
	
	public void transform(OverviewAlternative overviewAlternative, InputAlternative analyserAlternative){
		
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
		
		PCMResourceSet resSet = new PCMResourceSet(analyserAlternative.getResource());
		resSet.createAll(ModelTypes);
				
		// define the transformation input
		Resource overviewResource = overviewAlternative.getModelResource(ToolchainUtils.KEY_FILE_OVERVIEW);
		EList<EObject> inObjectsCsm = overviewResource.getContents();
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

			//unload all resources so we start with a clean resource set
			resSet.clearAll(ModelTypes);
			for(Resource res : resSet.getResources()){
				res.unload();
			}
						
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
		
			//copy Overview external PCM models into AnalyserAlternative
			{
				final IFolder servicesFolder = analyserAlternative.getResource().getFolder("services");
				try {
					ExplorerProjectPaths.prepareFolder(servicesFolder);
				} catch (CoreException e) {
					e.printStackTrace();
				}
				
				List<IResource> externalResources = overviewAlternative.getSubResources(ToolchainUtils.KEY_OVERVIEW_EXTERNAL_MODELS);
				
				for(IResource externalModel : externalResources){
					//Resource emfResource = ExplorerProjectPaths.getEmfResource(resSet, (IFile)externalModel);
					//externalEmfResources.add(emfResource);
				}
				
				//resource set should now contain only Overview external and transformed PCM models
				logger.info("Models contained in the resource set after transformation: ");
				for(Resource res : resSet.getResources()){
					logger.info(res.toString());
				}
				
				HashSet<Resource> oldRes = new HashSet<Resource>(resSet.getResources());

				for (Resource res : oldRes)
				{
					try {
						res.unload();
						res.load(null);
						EcoreUtil.resolveAll(res);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				HashSet<Resource> newRes = new HashSet<Resource>(resSet.getResources());
				newRes.removeAll(oldRes);

				final List<Resource> externalEmfResources = new ArrayList<Resource>();
				externalEmfResources.addAll(newRes);
				
				ExplorerProjectPaths.copyEMFResources(
						resSet.getResources().toArray(new Resource[resSet.getResources().size()]), 
						new BasicCallback<Resource>() {
					
					@Override
					public void handle(Resource resource) {
						if(externalEmfResources.contains(resource)){
														
							int segmentsCount = resource.getURI().segmentCount();
							
							String filename = resource.getURI().lastSegment();
							String folder = resource.getURI().segment(segmentsCount - 2);

							IFile file = servicesFolder.getFolder(folder).getFile(filename);
	
							URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
							resource.setURI(uri);
						}
						else{
							//live other models untouched
						}
					}
				}, null);
			}
			
			//register new input alternative models
			for (Resource resource : resSet.getResources())
			{
				ModelType mt = ModelType.getModelType(resource);
				if(mt != null){
					IFile file = ExplorerProjectPaths.getFileFromEmfResource(resource);
					if(file != null && analyserAlternative.getResource().getFullPath().isPrefixOf(file.getFullPath())){
						analyserAlternative.addSubResourceModel(file);
					}
				}

			}
		
		}

		IStatus status = BasicDiagnostic.toIStatus(result);
		Activator.getDefault().getLog().log(status);
	}
	
}
