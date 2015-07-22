package eu.cloudscaleproject.env.overview.wizard;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.IPageChangingListener;
import org.eclipse.jface.dialogs.PageChangingEvent;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.scaledl.overview.Overview;

import eu.cloudscaleproject.env.overview.OverviewAlternative;
import eu.cloudscaleproject.env.overview.wizard.pages.DeploymentWizardPage;
import eu.cloudscaleproject.env.overview.wizard.pages.ExposedInterfacesWizardPage;
import eu.cloudscaleproject.env.overview.wizard.pages.RequiredInterfacesWizardPage;
import eu.cloudscaleproject.env.overview.wizard.pages.TransformationPage;
import eu.cloudscaleproject.env.overview.wizard.util.IWizardPageControll;
import eu.cloudscaleproject.env.overview.wizard.util.OverviewHelper;
import eu.cloudscaleproject.env.overview.wizard.util.WizardData;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.util.OpenAlternativeUtil;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeNamePage;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeSelectionPage;

public class ImportWizard extends Wizard implements IWorkbenchWizard {

	private WizardData data = new WizardData();

	private AlternativeNamePage namePage;
	private AlternativeSelectionPage selectionPage;
	private TransformationPage transformPage = new TransformationPage(data);
	private DeploymentWizardPage deploymentWizardPage = new DeploymentWizardPage(data);
	private ExposedInterfacesWizardPage exposedInterfacesWizardPage = new ExposedInterfacesWizardPage(data);
	private RequiredInterfacesWizardPage requiredInterfacesWizardPage = new RequiredInterfacesWizardPage(data);

	private WizardDialog dialog;
	private IWizardPageControll currentPage;

	private IProject project;

	private boolean merge;

	public ImportWizard(IProject project, boolean merge) {
		setWindowTitle("ScaleDL Overview Import Wizard");

		this.project = project;
		data.setProject(project);
		
		this.merge = merge;

	}

	@Override
	public void addPages() {
		ResourceProvider provider = ResourceRegistry.getInstance().getResourceProvider(project, CSTool.OVERVIEW);
		if (merge)
		{
			selectionPage = new CustomAlternativeSelectionPage(data, provider);
			this.addPage(selectionPage);
		}
		else
		{
			namePage = new AlternativeNamePage(provider);
			namePage.setTitle("Overview alternative name");
			this.addPage(namePage);
		}
		
		this.addPage(transformPage);
		this.addPage(exposedInterfacesWizardPage);
		this.addPage(deploymentWizardPage);
		this.addPage(requiredInterfacesWizardPage);
	}
	
	@Override
	public IWizardPage getPreviousPage(IWizardPage page) {
		// TODO Auto-generated method stub
		java.lang.System.out.println("getPreviousPage() : "+page.getClass().getName());
		return super.getPreviousPage(page);
	}
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		
		if (page == transformPage && page.isPageComplete())
		{
            java.lang.System.out.println("getNextPage() : "+page.getClass().getName());
			//selectModelPage.performPageComplete();
		}

		return super.getNextPage(page);
	}
	
	@Override
	public void setContainer(IWizardContainer wizardContainer) {
		// TODO Auto-generated method stub
		
        super.setContainer(wizardContainer);
		if (wizardContainer == null)
		{
			if (this.dialog != null)
			{
				this.dialog = null;
			}
			return;
		}

		this.dialog = (WizardDialog)wizardContainer;
		this.dialog.addPageChangingListener(new IPageChangingListener() {
			

			@Override
			public void handlePageChanging(PageChangingEvent event) {
				// TODO Auto-generated method stub
				Object current = event.getCurrentPage();
				Object target = event.getTargetPage();
				
				List<IWizardPage> list = Arrays.asList(getPages());
				int idxCurrent = list.indexOf(current);
				int idxTarget = list.indexOf(target);
				
				java.lang.System.out.println("Current: "+current.getClass().getName()+"  ;;  Target: "+target.getClass().getName());
				if (current instanceof IWizardPageControll)
				{
                        if (idxCurrent < idxTarget)
                        {
                                // perform Next
                                ((IWizardPageControll)current).performNext();
                        }
                        else
                        {
                                // perform Back
                                ((IWizardPageControll)current).performBack();
                        }
				}
				
				if (target instanceof IWizardPageControll)
				{
					((IWizardPageControll)target).performUpdate();
					currentPage = (IWizardPageControll)target;
				}
				else
				{
					currentPage = null;
				}


				
			}
		});
	}
	
	@Override
	public boolean performFinish() {

		if (this.currentPage != null)
		{
			currentPage.performNext();
		}
		try {

			OverviewAlternative alternative;
			if (merge)
			{
				alternative = (OverviewAlternative) selectionPage.getSelection();
			}
			else
			{
				ResourceProvider provider = ResourceRegistry.getInstance().getResourceProvider(project, CSTool.OVERVIEW);
				alternative = (OverviewAlternative) provider.createNewResource(namePage.getName(), null);
			}

			Overview overviewToMerge = data.getOverviewModel();
			Resource overviewRes = alternative.getModelResource(ToolchainUtils.KEY_FILE_OVERVIEW);
			Overview overview = (Overview) overviewRes.getContents().get(0);
			
			// Merge/move all temporary overview
			OverviewHelper.mergeOverviewModel(overviewToMerge, overview);
			// Deploy service
			data.getPlatformRuntimeService().getSoftwareServices().add(data.getSoftwareService());

			overviewRes.save(null);
			
			OpenAlternativeUtil.openAlternative(alternative);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean canFinish() {
		if (currentPage == getPages()[getPageCount()-1])
		{
			return true;
		}
		return false;
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
	}
	
	private static class CustomAlternativeSelectionPage extends AlternativeSelectionPage implements IWizardPageControll
	{
		private WizardData data;

		public CustomAlternativeSelectionPage(WizardData data, ResourceProvider provider)
		{
			super(provider);
			this.data = data;
			
			setTitle("Select Overview alternative");
			setDescription("Please select Overview alternative to be used as target when importing transformed PCM models.");
		}

		@Override
		public void performNext()
		{
			Resource modelResource = ((OverviewAlternative)getSelection()).getModelResource(ToolchainUtils.KEY_FILE_OVERVIEW);
			Overview overview = (Overview) modelResource.getContents().get(0);
			this.data.setTargetOverviewModel(overview);
		}

		@Override public void performBack() { }
		@Override public void performUpdate() { }
		
	}
}
