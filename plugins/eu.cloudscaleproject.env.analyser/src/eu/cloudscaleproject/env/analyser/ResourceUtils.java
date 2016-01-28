package eu.cloudscaleproject.env.analyser;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.palladiosimulator.pcm.system.System;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ResourceUtils {
	
	private static final Logger logger = Logger.getLogger(ResourceUtils.class.getName());
	
	public static final String ANALYSER_INPUT_GENERATED_RES_NAME = "overview.alt";
	public static final String ANALYSER_CONF_CAPACITY_ANALYSES = "Capacity analyses";

	
	public static InputAlternative getGeneratedResourceInput(IProject project){
		ResourceProvider resourceProvider = ResourceRegistry.getInstance()
												.getResourceProvider(project, CSToolResource.ANALYSER_INPUT);
		IEditorInputResource editorInput = resourceProvider.getResource(ANALYSER_INPUT_GENERATED_RES_NAME);
		
		if(editorInput == null){
			editorInput = resourceProvider.createNewResource(ANALYSER_INPUT_GENERATED_RES_NAME, "Generated input");
		}
		
		return (InputAlternative)editorInput;
	}
	
	public static void createSystemDiagramRepresentation(System system, IFile file, ResourceSet resSet){
		
		//monitor.beginTask("Create System and representation:", 100);
		
		/*
		final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(systemURI.segment(1));
		if (!project.hasNature(ModelingProject.NATURE_ID)) {
			monitor.subTask("Converting to Modeling Project");
			// Ask user whether he wants to add the modeling nature
			final MessageDialog confirmationDialog = new MessageDialog(
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), CONFIRMATION_TITLE, null,
					CONFIRMATION_QUESTION,
					MessageDialog.CONFIRM, new String[] { IDialogConstants.YES_LABEL, IDialogConstants.CANCEL_LABEL }, 0);
			if (confirmationDialog.open() != Dialog.OK)
				return;

			ModelingProjectManager.INSTANCE.convertToModelingProject(project, monitor);
		}
		*/

		//monitor.subTask("Requesting Session");
		final Session session = SessionManager.INSTANCE.getSession(
				URI.createPlatformResourceURI(file.getFullPath().toString(), true),
				new NullProgressMonitor());
		
		//SessionManager.INSTANCE.add(session);
		final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();

		//monitor.worked(16);

		/*
		monitor.subTask("Creating System");
		final CreateSystemModelCommand createSystemModelCommand = new CreateSystemModelCommand(domain, systemURI);
		domain.getCommandStack().execute(createSystemModelCommand);

		monitor.worked(16);
		*/

		
		//monitor.subTask("Adding as semantic resource");
		//final System createdSystem = createSystemModelCommand.getCreatedSystem();
		domain.getCommandStack().execute(new AddSemanticResourceCommand(session, system.eResource().getURI(), new NullProgressMonitor()));

		//monitor.worked(16);
		
		Viewpoint systemViewpoint = null;
		
		for(Viewpoint viewpoint : ViewpointRegistry.getInstance().getViewpoints()){
			if(viewpoint.getName().equals("System Design")){
				systemViewpoint = viewpoint;
			}
		}
		
		if(systemViewpoint == null){
			logger.warning("System diagram viewpoint does not exist! Representation diagram can not be created.");
			return;
		}
		
		RepresentationDescription systemRepresentationDescription = null;
		
		for (RepresentationDescription representationDescription : systemViewpoint.getOwnedRepresentations()) {
			  if (representationDescription.getName().equals("ComposedProvidingRequiringEntity Diagram")) {
				  systemRepresentationDescription = representationDescription;
				  break;
			  }	  
		}
		
		if(systemRepresentationDescription == null){
			logger.warning("System diagram representation description does not exist! Representation diagram can not be created.");
			return;
		}

		//monitor.subTask("Selecting viewpoint");
		/*
		final Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(true);
		if (!selectedViewpoints.contains(Activator.getDefault().SYSTEM_DESIGN)) {
			domain.getCommandStack()
					.execute(new ChangeViewpointSelectionCommand(session, new ViewpointSelectionCallback(),
							Collections.singleton(Activator.getDefault().SYSTEM_DESIGN),
							Collections.<Viewpoint> emptySet(), new NullProgressMonitor()));
		}
		*/
		//monitor.worked(16);

		//monitor.subTask("Creating representation");
		/*
		final CreateRepresentationCommand createRepresentationCommand = new CreateRepresentationCommand(session,
				systemRepresentationDescription, system, "System diagram representation",
				new NullProgressMonitor());
		
		domain.getCommandStack().execute(createRepresentationCommand);
		*/
		
		//final DRepresentation createdRepresentation = createRepresentationCommand.getCreatedRepresentation();
		//monitor.worked(16);
		
		//test
		DialectManager dm = DialectManager.INSTANCE;
		DRepresentation representation = dm.createRepresentation("TEst", system, systemRepresentationDescription, session, new NullProgressMonitor());

		//
		
		session.save(null);
		
	}
	
	/**
	 * 
	 * Retrieves Analyser capacity experiment configuration alternative resource for the specified project.
	 * If this alternative does not exist, it is created.
	 * 
	 * @param project Workbench project to retrieve <code>ResourceProvider</code> from.
	 * @return Default capacity experiment configuration alternative.
	 */
	/*
	public static ConfAlternative getCapacityResourceConf(IProject project){
		ResourceProvider resourceProvider = ResourceRegistry.getInstance()
												.getResourceProvider(project, ToolchainUtils.ANALYSER_CONF_ID);
		
		IEditorInputResource editorInput = resourceProvider.getResource(ANALYSER_CONF_CAPACITY_ANALYSES);
		
		if(editorInput == null){
			editorInput = createCapacityResourceConf(resourceProvider);
		}
		
		return (ConfAlternative)editorInput;
	}
	*/
	
	/**
	 * Creates capacity experiment configuration alternative resource inside specified <code>ResourceProvider</code>.
	 * If capacity experiment configuration alternative already exist, this method does nothing.
	 * 
	 * @param resourceProvider ResourceProvider specifies, where to create alternative resource. 
	 * @return Capacity experiment configuration alternative resource.
	 */
	/*
	public static ConfAlternative createCapacityResourceConf(ResourceProvider resourceProvider){
		IEditorInputResource editorInput = resourceProvider.getResource(ANALYSER_CONF_CAPACITY_ANALYSES);
		
		if(editorInput == null){
			IFolder folder = resourceProvider.getRootFolder().getFolder(ANALYSER_CONF_CAPACITY_ANALYSES);
			
			editorInput = new CapacityAlternative(resourceProvider.getRootFolder().getProject(), folder);
			editorInput.setProperty(KEY_TYPE, CapacityAlternative.class.getName());
			editorInput.save();
		}
		
		return (CapacityAlternative)editorInput;
	}
	*/
	
	public static List<ConfAlternative> getConfAlternatives(IProject project, InputAlternative inputAlt){
		
		ResourceProvider confResourceProvider = ResourceRegistry.getInstance().
				getResourceProvider(project, CSToolResource.ANALYSER_CONF);
		
		List<ConfAlternative> out = new ArrayList<ConfAlternative>();
		
		for(IEditorInputResource res : confResourceProvider.getResources()){
			if(res instanceof ConfAlternative){
				IFolder inputAltFolder = (IFolder)((ConfAlternative)res).getSubResource(ToolchainUtils.KEY_INPUT_ALTERNATIVE);
				if(inputAltFolder == null){
					continue;
				}
				IEditorInputResource inputResource = ResourceRegistry.getInstance()
					.getResourceProvider(project, CSToolResource.ANALYSER_INPUT).getResource(inputAltFolder);
				if(inputResource == inputAlt){
					out.add((ConfAlternative)res);
				}
			}
		}
		
		return out;
	}
	
	/*
	public static void registerResourceFactories(){
		
		//register resource provider factories		
		ResourceRegistry.getInstance().registerFactory(CSTool.ANALYSER_INPUT, new IResourceProviderFactory(){

			@Override
			public ResourceProvider create(IFolder folder) {
				
				ResourceProvider resourceProvider = new ResourceProvider(folder, "Alternative") {
					
					@Override
					public boolean validateResource(IResource res) {
						if(res instanceof IFolder){
							if(((IFolder)res).getFile(EditorInputFolder.PROP_FILENAME).exists()){
								return true;
							}
						}
						return false;
					}
					
					@Override
					public IEditorInputResource createEditorInputResource(IResource res, String type) {
						
						InputAlternative eif = new InputAlternative(res.getProject(), (IFolder)res);
						return eif;
					}
					
					@Override
					public IResource createResource(String resourceName) {
						return getRootFolder().getFolder(resourceName);
					}
				};

				return resourceProvider;
			}
			
		});
		
		ResourceRegistry.getInstance().registerFactory(CSTool.ANALYSER_CONF, new IResourceProviderFactory(){

			@Override
			public ResourceProvider create(IFolder folder) {
				
				ResourceProvider resourceProvider = new ResourceProvider(folder, "Alternative") {
					
					@Override
					public boolean validateResource(IResource res) {
						if(res instanceof IFolder){
							if(((IFolder)res).getFile(EditorInputFolder.PROP_FILENAME).exists()){
								return true;
							}
						}
						return false;
					}
					
					@Override
					public IEditorInputResource createEditorInputResource(IResource res, String type) {
						
						if(type == null){
							type = ConfAlternative.Type.NORMAL.name();
						}
						
						ConfAlternative.Type typeEnum = ConfAlternative.Type.valueOf(type);
						ConfAlternative eif = new ConfAlternative(res.getProject(), (IFolder)res, typeEnum);
						return eif;
					}
					
					@Override
					public IResource createResource(String resourceName) {
						return getRootFolder().getFolder(resourceName);
					}
				};
				
				return resourceProvider;
			}
			
		});
		
		ResourceRegistry.getInstance().registerFactory(CSTool.ANALYSER_RES, new IResourceProviderFactory() {
			
			@Override
			public ResourceProvider create(IFolder folder) {
				
				ResourceProvider resourceProvider = new ResourceProvider(folder, "Result") {
					
					@Override
					public boolean validateResource(IResource res) {
						if(res instanceof IFolder){
							if(((IFolder)res).getFile(EditorInputFolder.PROP_FILENAME).exists()){
								return true;
							}
						}
						return false;
					}
					
					@Override
					public IEditorInputResource createEditorInputResource(IResource res, String type) {
						
						if(type == null){
							type = ConfAlternative.Type.NORMAL.name();
						}
						
						ConfAlternative.Type typeEnum = ConfAlternative.Type.valueOf(type);
						ResultAlternative eif = new ResultAlternative(res.getProject(), (IFolder)res, typeEnum);
						return eif;
					}
					
					@Override
					public IResource createResource(String resourceName) {
						return getRootFolder().getFolder(resourceName);
					}
				};
				
				return resourceProvider;
			}
		});
	}
	*/

}
