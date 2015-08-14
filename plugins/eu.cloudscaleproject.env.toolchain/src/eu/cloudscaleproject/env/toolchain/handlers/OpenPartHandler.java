 
package eu.cloudscaleproject.env.toolchain.handlers;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

public class OpenPartHandler {
	
	@Inject
	private MApplication application;
	
	@Execute
	public void execute(EPartService partService, EModelService modelService, @Named("part_id") String partID) {
		MPart part = partService.findPart(partID);
				
		if(part == null){
			MPartStack stack = (MPartStack)modelService.find("org.eclipse.e4.primaryDataStack", application);
			if(stack != null){
				part = partService.createPart(partID);
				stack.getChildren().add(part);
			}
		}
		
		partService.showPart(part, PartState.ACTIVATE);
	}
	
	@CanExecute
	public boolean canExecute(@Named("part_id") String partID) {
		
		//TODO: return false if the part or part descriptor does not exist
		
		//MUIElement el = modelService.find(partID, application);
		//if(el == null){
		//	return false;
		//}
		return true;
	}
		
}