package eu.cloudscaleproject.env.analyser.wizard.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;
import org.palladiosimulator.experimentautomation.experiments.Experiment;
import org.palladiosimulator.experimentautomation.experiments.ExperimentRepository;
import org.palladiosimulator.pcm.allocation.Allocation;

import eu.cloudscaleproject.env.analyser.Activator;
import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.common.BasicCallback;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputJob;
import eu.cloudscaleproject.env.toolchain.util.OpenAlternativeUtil;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeNamePage;
import eu.cloudscaleproject.env.toolchain.wizard.pages.ExternalModelsSelectionPage;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ImportAnalyserProjectWizard extends Wizard{
	
	private final IProject project;
	
	private final ResourceProvider inputResourceProvider;
	private final ResourceProvider confResourceProvider;

	private final AlternativeNamePage selectNamePage;
	private final ExternalModelsSelectionPage modelSelectionPage;
	
	public ImportAnalyserProjectWizard(IProject project) {
		
		this.project = project;
		
		this.inputResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, CSTool.ANALYSER_INPUT);
		this.confResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, CSTool.ANALYSER_CONF);
		
		//TODO: here only configuration resource provider is used
		//		In this wizard we are creating input wizard with the same name! Could this be a problem?
		this.selectNamePage = new AlternativeNamePage(confResourceProvider);
		this.modelSelectionPage = new ExternalModelsSelectionPage(new ModelType[]{ModelType.EXPERIMENTS});
	}
	
	@Override
	public void addPages() {
		
		addPage(selectNamePage);
		addPage(modelSelectionPage);
		
		super.addPages();
	}
	
	private Set<Resource> findLinked(Resource resource){
		
		Set<Resource> outSet = new HashSet<Resource>();
		Iterator<EObject> iter = EcoreUtil.getAllContents(resource, true);
		while(iter.hasNext()){
			EObject o = iter.next();
			
			//System.out.println("EObject: " + o.toString());
			
			{	
				Resource res = o.eResource();
				if(res != null && !outSet.contains(res)){
					outSet.add(res);
				}
			}
			
			for(EStructuralFeature feature : o.eClass().getEAllStructuralFeatures()){
				Object child = o.eGet(feature, false);
				if(child instanceof InternalEObject){
					
					InternalEObject ieo = (InternalEObject)child;
					EObject eo = null;
					
					//System.out.println("EObject feature: " + ieo.toString() + "Is proxy: " + ieo.eIsProxy());
					
					//if(ieo.eProxyURI() == null || ieo.eProxyURI().scheme().equals("pathmap")){
					//	continue;
					//}
					
					if(ieo.eProxyURI() != null){
						eo = o.eResource().getResourceSet().getEObject(ieo.eProxyURI(), true);
					}
					else{
						eo = ieo;
					}
					
					Resource res = eo.eResource();
					if(res == null){
						//System.out.println("EObject feature res NULL!");
						continue;
					}
					
					//System.out.println("EObject feature res: " + res.toString());
					
					//skip resources loaded from plugin
					if(res.getURI() != null 
							&& res.getURI().scheme() != null 
							&& res.getURI().scheme().equals("pathmap")){
						
						//System.out.println("EObject feature res from plugin!");
						continue;
					}

					if(!outSet.contains(res)){
						outSet.add(res);
						outSet.addAll(findLinked(res));
					}
				}
			}
			
		}
		
		//remove self from this list!
		outSet.remove(resource);
		return outSet;
	}
	
	@Override
	public boolean performFinish() {
		
		final InputAlternative ia = (InputAlternative)inputResourceProvider.createNewResource(selectNamePage.getName(), null);
		final ConfAlternative ca = (ConfAlternative)confResourceProvider.createNewResource(selectNamePage.getName(), ConfAlternative.Type.NORMAL.name(), true);
		
		EditorInputJob job = new EditorInputJob("Project import", ia, ca) {
			
			@Override
			public IStatus execute(final IProgressMonitor monitor){
								
				try{
					boolean done = importProject(ia, ca, monitor);
					if(!done){
						ia.delete();
						ca.delete();
					}
				}
				catch(Exception e){
					e.printStackTrace();
					return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Error has occured during project import!");
				}
				return new Status(IStatus.OK, Activator.PLUGIN_ID, "Project has been imported");
			}
		};
		
		job.setUser(true);
		job.schedule();
		
		return true;
	}

	public boolean importProject(final InputAlternative ia, final ConfAlternative ca, final IProgressMonitor monitor) {
		
		monitor.beginTask("Project import", 10);
		
		monitor.worked(1);
		if(monitor.isCanceled()){
			return false;
		}
		
		//get selected experiment
		Resource experimentResource = null;
		Resource[] experimentResources = modelSelectionPage.getSelectedResources();
		if(experimentResources.length == 0){
			return false;
		}
		else{
			experimentResource = experimentResources[0];
		}
		
		if(experimentResource == null){
			return false;
		}
		
		IProject projectToImport = ExplorerProjectPaths.getFileFromEmfResource(experimentResource).getProject();
		
		//reload experimentResource to the new resSet
		ResourceSet resSet = new ResourceSetImpl();
		IFile experimentFile = ExplorerProjectPaths.getFileFromEmfResource(experimentResource);
		experimentResource = ExplorerProjectPaths.getEmfResource(resSet, experimentFile);
				
		//TODO: only first experiment is imported!
		Experiment exp = null;
		if(!experimentResource.getContents().isEmpty()){
			EObject eo = experimentResource.getContents().get(0);
			if(eo instanceof ExperimentRepository){
				ExperimentRepository er = (ExperimentRepository)eo;
				if(!er.getExperiments().isEmpty()){
					exp = er.getExperiments().get(0);
				}
			}
			if(eo instanceof Experiment){
				exp = (Experiment)eo;
			}
		}
		
		if(exp == null){
			return false;
		}
		
		monitor.worked(1);
		if(monitor.isCanceled()){
			return false;
		}
		
		monitor.subTask("Allocationg models...");
		
		//extract input models
		final Set<Resource> inputResources = new HashSet<Resource>();		
		//System.out.println("INPUT: ");

		Allocation allocation = exp.getInitialModel().getAllocation();
		inputResources.addAll(findLinked(allocation.eResource()));
		inputResources.add(allocation.eResource());
		
		//find usages
		List<IFile> usageFiles = ExplorerProjectPaths.findResources(projectToImport, ModelType.USAGE.getFileExtension());
		for(IFile file : usageFiles){
			inputResources.add(ExplorerProjectPaths.getEmfResource(resSet, file));
		}
		//find usage evolution files
		List<IFile> ueFiles = ExplorerProjectPaths.findResources(projectToImport, ModelType.USAGE_EVOLUTION.getFileExtension());
		for(IFile file : ueFiles){
			inputResources.add(ExplorerProjectPaths.getEmfResource(resSet, file));
		}
		
		monitor.worked(1);
		if(monitor.isCanceled()){
			return false;
		}
		
		//extract configuration models
		//System.out.println("CONFIGURATION: ");
		final Set<Resource> confResources = findLinked(experimentResource);
		confResources.add(experimentResource);
		confResources.removeAll(inputResources);
		
		//collect all resources
		final List<Resource> resources = new ArrayList<>();
		resources.addAll(inputResources);
		resources.addAll(confResources);
				
		monitor.worked(1);
		if(monitor.isCanceled()){
			return false;
		}
		
		monitor.subTask("Copying models...");
		
		ExplorerProjectPaths.copyEMFResources(resources.toArray(new Resource[resources.size()]), new BasicCallback<Resource>() {
			
			@Override
			public void handle(Resource res) {
				
				if(inputResources.contains(res)){
					String[] segments = res.getURI().segments();
					String segment = segments[segments.length - 1];
					IFile file = ia.getResource().getFile(new Path(segment));

					URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
					res.setURI(uri);
				}
				if(confResources.contains(res)){
					String[] segments = res.getURI().segments();
					String segment = segments[segments.length - 1];
					IFile file = ca.getResource().getFile(new Path(segment));

					URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
					res.setURI(uri);
				}
			}
		});
		
		monitor.worked(1);
		if(monitor.isCanceled()){
			return false;
		}
		
		for (Resource resource : inputResources)
		{
			IFile f = ExplorerProjectPaths.getFileFromEmfResource(resource);
			ia.addSubResourceModel(f);
		}
		for (Resource resource : confResources)
		{
			IFile f = ExplorerProjectPaths.getFileFromEmfResource(resource);
			ca.addSubResourceModel(f);
		}
		
		monitor.worked(1);
		if(monitor.isCanceled()){
			return false;
		}
		
		ia.save();
		ca.setSubResource(ToolchainUtils.KEY_INPUT_ALTERNATIVE, ia.getResource());
		ca.save();
		
		//import has completed 
		
		monitor.subTask("Loading new models...");

		ia.load();
		ca.load();
		
		monitor.subTask("Validating...");
		
		monitor.worked(1);
		if(monitor.isCanceled()){
			return false;
		}
		
		ia.validate();
		monitor.worked(1);
		if(monitor.isCanceled()){
			return false;
		}
		
		ca.validate();
		monitor.worked(1);
		if(monitor.isCanceled()){
			return false;
		}

		monitor.subTask("Updating GUI...");
		Display.getDefault().syncExec(new Runnable() {
			
			@Override
			public void run() {
				ValidationDiagramService.showStatus(project, CSTool.ANALYSER_CONF.getID(), ca);
				OpenAlternativeUtil.openAlternative(ia);
				OpenAlternativeUtil.openAlternative(ca);
			}
		});
		
		if(monitor.isCanceled()){
			return false;
		}
		monitor.done();
		
		return true;
	}
	
	@Override
	public boolean canFinish()
	{
		if (getContainer().getCurrentPage() == getPages()[getPageCount()-1])
			return true;

		return false;
	}

}