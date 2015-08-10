 
package eu.cloudscaleproject.env.analyser.contributions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.ui.di.AboutToShow;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuFactory;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuSeparator;

import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.explorer.Explorer;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class NewModelMenu {
	
	@AboutToShow
	public void aboutToShow(ECommandService commandService, List<MMenuElement> items) {
		
		IEditorInputResource alternative = Explorer.getInstance().getRoot().getContext().getActive(IEditorInputResource.class);
		
		System.out.println("@AboutToShow: " + (alternative == null ? "null" : alternative.getName()));
		
		if(alternative instanceof InputAlternative){
			
			MMenuSeparator separator = MMenuFactory.INSTANCE.createMenuSeparator();
			items.add(separator);
			
			for(ModelType type : ModelType.GROUP_PCM){
				MHandledMenuItem item = MMenuFactory.INSTANCE.createHandledMenuItem();
				item.setLabel(type.name());
				
				Map<String, String> param = new HashMap<String, String>();
				param.put("eu.cloudscaleproject.env.analyser.commandparameter.fileextension", "repository");
				
				Command command = commandService.getCommand("eu.cloudscaleproject.env.analyser.command.createModel");
				ParameterizedCommand pc = ParameterizedCommand.generateCommand(command, param);
				
				System.out.println("@AboutToShow: pc: " + (pc == null ? "null" : pc.toString()));
				
				item.setWbCommand(pc);
				items.add(item);
			}
		}
		
	}
		
}