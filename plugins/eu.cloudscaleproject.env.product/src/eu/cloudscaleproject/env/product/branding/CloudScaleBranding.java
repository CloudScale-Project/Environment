package eu.cloudscaleproject.env.product.branding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.dialogs.WorkbenchWizardElement;
import org.eclipse.ui.internal.wizards.AbstractExtensionWizardRegistry;
import org.eclipse.ui.navigator.resources.ProjectExplorer;
import org.eclipse.ui.wizards.IWizardCategory;
import org.eclipse.ui.wizards.IWizardDescriptor;

public class CloudScaleBranding {
	
	public static void initialize()
	{
		removeWizards();
		initProjectExplorer();
	}
	
	public static void initProjectExplorer ()
	{
	
		// HACK - WORKAROUND for initializing project explorer on startup 
		// This is needed if showing CloudScale or Palladio perspective on startup (not intro)
		// otherwise initializeProjectExplorer() must be called after perspective switch
		Job job = new Job("Initialize project explorer") {
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				// TODO Auto-generated method stub
				Display.getDefault().syncExec(new Runnable() {
					
					@Override
					public void run() {
						IWorkbenchWindow[] workbenchs = 
						PlatformUI.getWorkbench().getWorkbenchWindows();
			
						ProjectExplorer view = null;
						for (IWorkbenchWindow workbench : workbenchs) {
							for (IWorkbenchPage page : workbench.getPages()) {
								view = (ProjectExplorer) 
								page.findView("org.eclipse.ui.navigator.ProjectExplorer");
								break;
							}
						}
			
						if (view == null) {
							return;
						}
			
						view.getCommonViewer().setInput(ResourcesPlugin.getWorkspace ().getRoot());
						view.getCommonViewer().refresh();
					}
				});
				
				return Status.OK_STATUS;
			}
		};
		
		job.schedule(500);
	}
	
	private static void removeWizards ()
	{	
		AbstractExtensionWizardRegistry wizardRegistry = (AbstractExtensionWizardRegistry)PlatformUI.getWorkbench().getNewWizardRegistry();
		IWizardCategory[] categories = PlatformUI.getWorkbench().getNewWizardRegistry().getRootCategory().getCategories();
		for(IWizardDescriptor wizard : getAllWizards(categories)){
		    if(
		    		!wizard.getCategory().getId().equals("org.eclipse.ui.Basic") && 
		    		!wizard.getCategory().getId().equals("org.eclipse.ui.Examples") && 
		    		!wizard.getCategory().getId().startsWith("org.scaledl") && 
		    		!wizard.getCategory().getId().startsWith("de.uka") && 
		    		!wizard.getCategory().getId().startsWith("org.spotter") && 
		    		!wizard.getCategory().getId().startsWith("org.palladiosimulator") && 
		    		!wizard.getCategory().getId().contains("cloudscale")
		    		)
		    		
		    {
		        WorkbenchWizardElement wizardElement = (WorkbenchWizardElement) wizard;
		        wizardRegistry.removeExtension(wizardElement.getConfigurationElement().getDeclaringExtension(), new Object[]{wizardElement});
		    }
		}
		
	}
	
	private static IWizardDescriptor[] getAllWizards(IWizardCategory[] categories) {
		  List<IWizardDescriptor> results = new ArrayList<IWizardDescriptor>();
		  for(IWizardCategory wizardCategory : categories){
		    results.addAll(Arrays.asList(wizardCategory.getWizards()));
		    results.addAll(Arrays.asList(getAllWizards(wizardCategory.getCategories())));
		  }
		  return results.toArray(new IWizardDescriptor[0]);
		}

}
