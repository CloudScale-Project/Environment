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
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Creatable;

@Creatable
@Singleton
public class CommandExecutor {
		
	private static final Logger logger = Logger.getLogger(CommandExecutor.class.getName());
		
	private ECommandService commandService;
	private EHandlerService handlerService;
	
	public static CommandExecutor getInstance(){
		return CloudscaleContext.getGlobalContext().get(CommandExecutor.class);
	}
	
	@Inject
	public CommandExecutor(ECommandService commandService, EHandlerService handlerService) {
		this.commandService = commandService;
		this.handlerService = handlerService;
	}

	public boolean execute(String id){
		Command command = commandService.getCommand(id);
		
		if(command == null){
			logger.warning("execute("+ id +"): Command with specified ID was not found!");
			return false;
		}
		
		ParameterizedCommand pc = new ParameterizedCommand(command, null);
		
		if(handlerService.canExecute(pc, CloudscaleContext.getActiveContext())){
			handlerService.executeHandler(pc, CloudscaleContext.getActiveContext());
			return true;
		}
		
		return false;
	}
	
	public boolean execute(String id, IEclipseContext staticContext){
		Command command = commandService.getCommand(id);
		
		if(command == null){
			logger.warning("execute("+ id +"): Command with specified ID was not found!");
			return false;
		}
		
		ParameterizedCommand pc = new ParameterizedCommand(command, null);
		
		if(handlerService.canExecute(pc, staticContext)){
			handlerService.executeHandler(pc, staticContext);
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * Execute command with parameters.
	 * 
	 * @param id : command id
	 * @param param : array of parameters
	 */
	public boolean execute(String id, Map<String, String> param){
		return execute(id, null, param);
	}
	
	/**
	 * 
	 * Execute command with parameters.
	 *
	 * @param id : command id
	 * @param param : array of parameters
	 */
	public boolean execute(String id, IEclipseContext staticContext, Map<String, String> param){
		Command command = commandService.getCommand(id);
		
		if(command == null){
			logger.warning("execute("+ id +"): Command with specified ID was not found!");
			return false;
		}
		
		try {
			if(command.getParameters() == null){
				return execute(id);
			}
		} 
		catch (NotDefinedException e1) {
			e1.printStackTrace();
		}
		
		ParameterizedCommand pc = ParameterizedCommand.generateCommand(command, param);
		if(pc == null){
			logger.warning("execute("+ id +"): Can not create parameterized command!");
			return false;
		}
		
		if(staticContext == null){
			if(handlerService.canExecute(pc)){
				handlerService.executeHandler(pc);
				return true;
			}
		}
		else{
			if(handlerService.canExecute(pc, staticContext)){
				handlerService.executeHandler(pc, staticContext);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 * Execute command with parameters without parameter id.
	 * Prameters are filled into command in order - from first to last.
	 * 
	 * @param id : command id
	 * @param param : array of parameters
	 */
	public boolean execute(String id, String... params){
		return execute(id, null, params);
	}
	
	/**
	 * 
	 * Execute command with parameters without parameter id.
	 * Prameters are filled into command in order - from first to last.
	 * 
	 * @param id : command id
	 * @param param : array of parameters
	 */
	public boolean execute(String id, IEclipseContext staticContext, String... params){
		Command command = commandService.getCommand(id);
		
		if(command == null){
			logger.warning("execute("+ id +"): Command with specified ID was not found!");
			return false;
		}
		
		try {
			if(command.getParameters() == null){
				return execute(id);
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
			return false;
		}
		
		if(staticContext == null){
			if(handlerService.canExecute(pc)){
				handlerService.executeHandler(pc);
				return true;
			}
		}
		else{
			if(handlerService.canExecute(pc, CloudscaleContext.getActiveContext())){
				handlerService.executeHandler(pc, CloudscaleContext.getActiveContext());
				return true;
			}
		}
		
		return false;
	}
}
