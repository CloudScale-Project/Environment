package eu.cloudscaleproject.env.toolchain.wizard;

import java.util.logging.Logger;

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
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.services.IExplorerService;
import eu.cloudscaleproject.env.toolchain.util.OpenAlternativeUtil;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeNamePage;

public class CreateAlternativeWizard extends Wizard implements IWorkbenchWizard{
	
	private static final Logger logger = Logger.getLogger(CreateAlternativeWizard.class.getName());
	
	protected final CSToolResource providerID;
	
	protected IProject project = null;
	protected ResourceProvider provider = null;
	
	protected ProjectSelectionPage projectSelectionPage = null;
	protected AlternativeNamePage nameSelectionPage = null;
	
	public CreateAlternativeWizard(ResourceProvider rp){
		
		this.project = rp.getProject();
		this.provider = rp;
		//Not needed
		this.providerID = null;
		
		projectSelectionPage = new ProjectSelectionPage(){
			public void handleSelection(IProject p) {
				if(ExplorerProjectPaths.isCloudScaleProject(p)){
					setProject(p);
				}
			};
		};
		nameSelectionPage = new AlternativeNamePage(rp);
		
		setWindowTitle("Create alternative");
	}

	public CreateAlternativeWizard(CSToolResource resID) {
		
		this.providerID = resID;
		
		projectSelectionPage = new ProjectSelectionPage(){
			public void handleSelection(IProject p) {
				if(ExplorerProjectPaths.isCloudScaleProject(p)){
					setProject(p);
				}
			};
		};
		nameSelectionPage = new AlternativeNamePage();
		
		setWindowTitle("Create alternative");
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		
		IProject project = ExplorerProjectPaths.getProject(selection);
		if(ExplorerProjectPaths.isCloudScaleProject(project)){
			setProject(project);
		}
	}
	
	public void setProject(IProject p){
		
		if(providerID == null){
			throw new IllegalStateException("Can not set the project! The resource provider and the project has already been specified in the constructor");
		}
		
		this.project = p;
		
		if(p != null){
			this.provider = ResourceRegistry.getInstance().getResourceProvider(p, providerID);
		}
		if(this.provider != null){
			this.nameSelectionPage.setResourceProvider(this.provider);
		}
	}
	
	@Override
	public void addPages() {
		if(this.project == null){
			addPage(projectSelectionPage);
		}
		addPage(nameSelectionPage);
	}

	@Override
	public boolean performFinish() {
		
		if(project == null || provider == null){
			logger.severe("Alternative can not be created! Project or ResourceProvider is NULL!");
			return false;
		}
		
		String altName = nameSelectionPage.getName();
		if(altName == null || altName.isEmpty()){
			logger.severe("Alternative can not be created! The alternative name is not specified!");
			return false;
		}

		IEditorInputResource alternative = (IEditorInputResource)provider.createNewResource(altName, null);
		initAlternative(alternative);
		
		IExplorerService explorerService = CloudscaleContext.getGlobalContext().get(IExplorerService.class);
		explorerService.setSelection(alternative);
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
