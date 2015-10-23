package eu.cloudscaleproject.env.toolchain.explorer.processors;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.workbench.modeling.EModelService;

public class PopupMenuProcessor {

	// I get this via the parameter of the process definition
   
 
    @Execute
    public void execute(MApplication app, EModelService modelService) {
    	
    	// This model processor is not used.
    	// It was made in a attempt to remove unused menu items from the Explorer popup menu.
    	// Popup menu does not contain unwanted menu entries at the time of execution of this method.
    	// TODO: find a way to remove unwanted menu entries
    	
    	/*
    	MUIElement el = modelService.find(ExplorerViewPart.POPUP_MENU_ELEMENT, app);
    	
    	MMenu menu = null;
    	if(el instanceof MMenu){
    		menu = (MMenu)el;
    	}
    	
        // Remove unused menu entries
        if (menu != null && menu.getChildren() != null) {
           
        	List<MMenuElement> list = new ArrayList<MMenuElement>();
            
        	for (MMenuElement element : menu.getChildren()) {
                if (element.getElementId().equals("org.eclipse.modisco.infra.discovery.ui.menu")) {
                    list.add(element);
                }
                if (element.getElementId().equals("org.modelversioning.emfprofile.application.decorator.reflective.commands.ApplyStereotype")) {
                    list.add(element);
                }
            }
        	
            menu.getChildren().removeAll(list);
        }
        */
        
    }
	
}
