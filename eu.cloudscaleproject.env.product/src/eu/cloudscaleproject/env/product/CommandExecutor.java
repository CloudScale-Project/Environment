package eu.cloudscaleproject.env.product;

import javax.inject.Inject;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.di.annotations.Creatable;

@Creatable
public class CommandExecutor {
	
	private ECommandService commandService;
	private EHandlerService handlerService;
	
	@Inject
	public CommandExecutor(ECommandService commandService, EHandlerService handlerService) {
		this.commandService = commandService;
		this.handlerService = handlerService;
	}

	public void execute(String id){
		Command command = commandService.getCommand(id);
		ParameterizedCommand pc = new ParameterizedCommand(command, null);
		if(handlerService.canExecute(pc)){
			handlerService.executeHandler(pc);
		}
	}
}
