package eu.cloudscaleproject.env.common;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.IParameter;
import org.eclipse.core.commands.Parameterization;
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
	 * Execute command with parameters without parameter id.
	 * Prameters are filled into command in order - from first to last.
	 * 
	 * TODO: CommandExecutor does not work for commands, where the handler has to inject e4 objects!
	 * 
	 * @param id : command id
	 * @param param : array of parameters
	 */
	public void execute(String id, String... param){
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
		} catch (NotDefinedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		List<Parameterization> parameters = new ArrayList<Parameterization>();
		
		try {
			int i = 0;
			for(IParameter parameter : command.getParameters()){
				if(param.length > i){
					parameters.add(new Parameterization(parameter, param[i]));
					i++;
				}
				else{
					break;
				}
			}
		} catch (NotDefinedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Parameterization[] out= new Parameterization[parameters.size()];
		
		ParameterizedCommand pc = new ParameterizedCommand(command, parameters.toArray(out));
		if(handlerService.canExecute(pc, CloudscaleContext.getContext())){
			handlerService.executeHandler(pc, CloudscaleContext.getContext());
		}
	}
	
	/**
	 * 
	 * Execute command with parameters.
	 * Parameters must be specified as new String[]{new String[]{paramID, value}, new String[]{paramID, value}}
	 * 
	 * TODO: CommandExecutor does not work for commands, where the handler has to inject e4 objects!
	 * 
	 * @param id : command id
	 * @param param : command parameters
	 */
	public void execute(String id, List<String[]> param){
		Command command = commandService.getCommand(id);
		
		if(command == null){
			logger.warning("execute("+ id +"): Command with specified ID was not found!");
			return;
		}

		List<Parameterization> parameters = new ArrayList<Parameterization>();
		
		for(int i=0; i<param.size(); i++){
			IParameter p;
			try {
				p = command.getParameter(param.get(i)[0]);
				if(param.get(i).length > 1 && param.get(i)[1] != null){
					parameters.add(new Parameterization(p, param.get(i)[1]));
				}
			} catch (NotDefinedException e) {
				e.printStackTrace();
			}
		}
		
		Parameterization[] out= new Parameterization[parameters.size()];
		
		ParameterizedCommand pc = new ParameterizedCommand(command, parameters.toArray(out));
		if(handlerService.canExecute(pc, CloudscaleContext.getContext())){
			handlerService.executeHandler(pc, CloudscaleContext.getContext());
		}
	}
}
