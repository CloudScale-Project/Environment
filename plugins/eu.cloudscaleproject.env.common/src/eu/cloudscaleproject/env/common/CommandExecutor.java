package eu.cloudscaleproject.env.common;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.IParameter;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.di.annotations.Creatable;

@Creatable
@Singleton
public class CommandExecutor {
	
	//TODO: CommandExecutor does not work for commands, where the handler has to inject e4 objects!
	
	private static final Logger logger = Logger.getLogger(CommandExecutor.class.getName());
		
	private ECommandService commandService;
	private EHandlerService handlerService;
	
	@Inject
	public CommandExecutor(ECommandService commandService, EHandlerService handlerService) {
		this.commandService = commandService;
		this.handlerService = handlerService;
	}

	/**
	 * TODO: CommandExecutor does not work for commands, where the handler has to inject e4 objects!
	 * 
	 * @param id
	 */
	public void execute(String id){
		Command command = commandService.getCommand(id);
		
		if(command == null){
			logger.warning("execute("+ id +"): Command with specified ID was not found!");
			return;
		}
		
		ParameterizedCommand pc = new ParameterizedCommand(command, null);
		if(handlerService.canExecute(pc, CloudscaleContext.getContext())){
			handlerService.executeHandler(pc, CloudscaleContext.getContext());
		}
	}
	
	/**
	 * 
	 * Execute command with parameters .
	 * 
	 * TODO: CommandExecutor does not work for commands, where the handler has to inject e4 objects!
	 * 
	 * @param id : command id
	 * @param param : array of parameters
	 */
	public void execute(String id, Map<String, String> param){
		Command command = commandService.getCommand(id);
		
		if(command == null){
			logger.warning("execute("+ id +"): Command with specified ID was not found!");
			return;
		}
		
		try {
			if(command.getParameters() == null){
				execute(id);
				return;
			}
		} 
		catch (NotDefinedException e1) {
			e1.printStackTrace();
		}
		
		ParameterizedCommand pc = ParameterizedCommand.generateCommand(command, param);
		if(pc == null){
			logger.warning("execute("+ id +"): Can not create parameterized command!");
			return;
		}
		
		if(handlerService.canExecute(pc, CloudscaleContext.getContext())){
			handlerService.executeHandler(pc, CloudscaleContext.getContext());
		}
		
	}
	
	/**
	 * 
	 * Execute command with parameters without parameter id.
	 * Prameters are filled into command in order - from first to last.
	 * 
	 * TODO: CommandExecutor does not work for commands, where the handler has to inject e4 objects!
	 * 
	 * @param id : command id
	 * @param param : array of parameters
	 */
	public void execute(String id, String... params){
		Command command = commandService.getCommand(id);
		
		if(command == null){
			logger.warning("execute("+ id +"): Command with specified ID was not found!");
			return;
		}
		
		try {
			if(command.getParameters() == null){
				execute(id);
				return;
			}
		} 
		catch (NotDefinedException e1) {
			e1.printStackTrace();
		}
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		try {
			for(int i=0; i<command.getParameters().length && i<params.length; i++){
				IParameter p = command.getParameters()[i];
				paramsMap.put(p.getId(), params[i]);
			}
		} catch (NotDefinedException e) {
			e.printStackTrace();
		}
		
		ParameterizedCommand pc = ParameterizedCommand.generateCommand(command, paramsMap);
		if(pc == null){
			logger.warning("execute("+ id +"): Can not create parameterized command!");
			return;
		}
		
		if(handlerService.canExecute(pc, CloudscaleContext.getContext())){
			handlerService.executeHandler(pc, CloudscaleContext.getContext());
		}
		
	}
}
