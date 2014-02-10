package eu.cloudscaleproject.env.product;


import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;


public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {
	
	//private ToolBarVisibilityHandler toolBarVisibilityHandler;
	
    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
    	return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(400, 300));
        configurer.setShowCoolBar(false);
        
        //toolBarVisibilityHandler = new ToolBarVisibilityHandler(configurer);
        //toolBarVisibilityHandler.addListener();
    }
    
    
    @Override
    public void postWindowOpen() {
    	super.postWindowOpen();
    	/*
    	try {
            IHandlerService service = (IHandlerService) PlatformUI
                    .getWorkbench().getActiveWorkbenchWindow()
                    .getService(IHandlerService.class);
            if (service != null)
                service.executeCommand("org.eclipse.ui.ToggleCoolbarAction",
                        null);
        } catch (Exception e) {
            //handle error
        }
        */
    }
}
