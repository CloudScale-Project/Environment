package eu.cloudscaleproject.env.toolchain.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.wizard.util.ProjectSelectionPage;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.AbstractConfigAlternative;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.resources.types.IInputAlternative;
import eu.cloudscaleproject.env.toolchain.util.OpenAlternativeUtil;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeNamePage;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeSelectionPage;

public class CreateConfigAlternativeWizard extends Wizard implements IWorkbenchWizard{
	
	private final CSToolResource inputID;
	private final CSToolResource configID;

	private IProject project;
	private ResourceProvider inputProvider;
	private ResourceProvider configProvider;
	
	private ProjectSelectionPage projectSelectionPage;
	private AlternativeNamePage nameSelectionPage;
	private AlternativeSelectionPage inputSelectionPage;
	
	private IEditorInputResource inputResource;
	
	public CreateConfigAlternativeWizard(CSToolResource configID, CSToolResource inputID){
		
		this.inputID = inputID;
		this.configID = configID;
		
		this.projectSelectionPage = new ProjectSelectionPage(){
			@Override
			public void handleSelection(IProject project) {
				if(ExplorerProjectPaths.isCloudScaleProject(project)){
					setProject(project);
				}
			}
		};
		this.inputSelectionPage = new AlternativeSelectionPage(){
			@Override
			public void handleSelection(IEditorInputResource eir) {
				setInputResource(eir);
			}
		};
		this.nameSelectionPage = new AlternativeNamePage();
		
		setWindowTitle("Create alternative");
	}
	
	public CreateConfigAlternativeWizard(ResourceProvider configProvider, ResourceProvider inputProvider) {
		
		//Not needed
		this.inputID = null;
		this.configID = null;
		
		this.project = configProvider.getProject();

		//Not needed - we already have the input alternative
		this.inputProvider = inputProvider;
		this.configProvider = configProvider;
		this.inputResource = null;
		
		this.nameSelectionPage = new AlternativeNamePage(configProvider);
		this.inputSelectionPage = new AlternativeSelectionPage(){
			@Override
			public void handleSelection(IEditorInputResource eir) {
				setInputResource(eir);
			}
		};
		this.inputSelectionPage.setResourceProvider(this.inputProvider);
				
		setWindowTitle("Create alternative");
	}

	public CreateConfigAlternativeWizard(ResourceProvider configProvider, IEditorInputResource inputAlternative) {
		
		//Not needed
		this.inputID = null;
		this.configID = null;
		
		this.project = configProvider.getProject();

		//Not needed - we already have the input alternative
		this.inputProvider = null;
		this.configProvider = configProvider;
		this.inputResource = inputAlternative;
		
		this.nameSelectionPage = new AlternativeNamePage(configProvider);
		
		if (inputProvider != null) {
			this.inputSelectionPage = new AlternativeSelectionPage();
			this.inputSelectionPage.setResourceProvider(inputProvider);
		}
				
		setWindowTitle("Create alternative");
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		
		IProject project = ExplorerProjectPaths.getProject(selection);
		if(ExplorerProjectPaths.isCloudScaleProject(project)){
			setProject(project);
		}
		
		IEditorInputResource inputRes = CloudscaleContext.getActiveContext().get(IEditorInputResource.class);
		if(inputRes instanceof IInputAlternative){
			setInputResource(inputRes);
		}
		
	}
	
	public void setProject(IProject project){
		
		if(inputID == null || configID == null){
			throw new IllegalStateException("Can not set the project! The resource providers and the project has already been specified in the constructor");
		}
		
		this.project = project;
		this.inputProvider = ResourceRegistry.getInstance().getResourceProvider(project, inputID);
		this.configProvider = ResourceRegistry.getInstance().getResourceProvider(project, configID);
		this.nameSelectionPage.setResourceProvider(configProvider);
	}
	
	public void setInputResource(IEditorInputResource eir){
		if(eir.getProject() != null){
			setProject(eir.getProject());
		}
		this.inputResource = eir;
	}
	
	@Override
	public void addPages() {
		
		if(this.project == null){
			addPage(projectSelectionPage);
		}
		if(this.inputResource == null){
			addPage(inputSelectionPage);
		}
		
		addPage(nameSelectionPage);
	}

	@Override
	public boolean performFinish() {
		
		String altName = nameSelectionPage.getName();
		
		if (this.inputSelectionPage != null) this.inputResource = this.inputSelectionPage.getSelection();

		AbstractConfigAlternative alternative = (AbstractConfigAlternative)configProvider.createNewResource(altName, null);
		initAlternative(alternative);	
		
		alternative.setInputAlternative((IInputAlternative)this.inputResource);
		alternative.save();
		
		OpenAlternativeUtil.openAlternative(alternative);
		
		return true;
	}
	

	protected void initAlternative (IEditorInputResource alternative)
	{
		// Nothing to do here - overwrite
	}
	
	
	@Override
	public boolean canFinish()
	{
		if (getContainer().getCurrentPage() == getPages()[getPageCount()-1] 
				&& getContainer().getCurrentPage().isPageComplete())
			return true;

		return false;
	}

}
