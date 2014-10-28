package eu.cloudscaleproject.env.method.common.util;

import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import eu.cloudscaleproject.env.method.common.method.Action;
import eu.cloudscaleproject.env.method.common.method.Command;
import eu.cloudscaleproject.env.method.common.method.Container;
import eu.cloudscaleproject.env.method.common.method.Method;
import eu.cloudscaleproject.env.method.common.method.MethodFactory;
import eu.cloudscaleproject.env.method.common.method.Requirement;
import eu.cloudscaleproject.env.method.common.method.Section;

public class MethodUtil {
	
	public static Method getMethodModel (final Diagram d)
	{
		Method method = null;
		for(EObject eo : d.eResource().getContents()){
			if(eo instanceof Method){
				method = (Method)eo;
			}
		}
		
		if(method == null){
			method = MethodFactory.eINSTANCE.createMethod();
			d.eResource().getContents().add(method);
			
			try {
				d.eResource().save(null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return method;
	}
	
	public static Container createContainer(){
		Container c = MethodFactory.eINSTANCE.createContainer();
		c.setName("Section container");
		return c;
	}
	
	public static Action createAction(){
		Action action = MethodFactory.eINSTANCE.createAction();
		action.setName("Action");
		
		action.setColorBackground("a1c9d5");
		action.setColorForeground("000000");
		
		action.setDone(false);
		
		return action;
	}
	
	public static Section createSection(){
		Section s = MethodFactory.eINSTANCE.createSection();
		
		s.setId(EcoreUtil.generateUUID());
		s.setName("Section");
		
		s.setColorBackground("a1c9d5");
		s.setColorForeground("000000");
		
		s.setInProgress(false);
		s.setValid(true);
		s.setDone(false);
		
		return s;
	}
	
	public static Command createCommand(){
		Command command = MethodFactory.eINSTANCE.createCommand();
		command.setColorBackground("a1c9d5");
		command.setColorForeground("000000");
		command.setColorText("0000ff");
		command.setLayout(true);
		return command;
	}
	
	public static Requirement createRequirement(){
		Requirement requirement = MethodFactory.eINSTANCE.createRequirement();
		requirement.setColorBackground("a1c9d5");
		requirement.setColorForeground("000000");
		requirement.setColorText("0000ff");
		requirement.setLayout(true);
		return requirement;
	}

}
