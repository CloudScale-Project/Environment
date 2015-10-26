package eu.cloudscaleproject.env.toolchain.resources.types;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;

public abstract class AbstractInputAlternative extends EditorInputEMF implements IInputAlternative{

	private static final Logger logger = Logger.getLogger(AbstractResultAlternative.class.getName());
	private CSTool tool;
	
	
	public AbstractInputAlternative(IProject project, IFolder folder, ModelType[] modelTypes, CSTool tool) {
		super(project, folder, modelTypes, tool.getInput().getID());
		this.tool = tool;
	}
	
	public CSTool getTool()
	{
		return tool;
	}
	
	@Override
	public List<IConfigAlternative> getConfigAlternatives() {
		
		ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(getProject(), tool.getConfig().getID());
		
		if(rp == null){
			logger.warning("Resource provider not found! ID: " + tool.getConfig().getID());
			logger.warning("Configuration alternative can not be retrieved for the input alternative! ID: " 
								+ getID() + ", Name: " + getName());
			return null;
		}
		
		List<IConfigAlternative> configAlternatives = new ArrayList<IConfigAlternative>();
		
		for(IEditorInputResource eir : rp.getResources()){
			if(eir instanceof AbstractConfigAlternative){
				AbstractConfigAlternative ca = (AbstractConfigAlternative)eir;
				if(this.equals(ca.getInputAlternative())){
					configAlternatives.add(ca);
				}
			}
		}
		return configAlternatives;
	}

	@Override
	public List<IResultAlternative> getResultAlternatives() {
		
		ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(getProject(), tool.getResult().getID());
		
		if(rp == null){
			logger.warning("Resource provider not found! ID: " + tool.getConfig().getID());
			logger.warning("Configuration alternative can not be retrieved for the result alternative! ID: " 
								+ getID() + ", Name: " + getName());
			return null;
		}
		
		List<IResultAlternative> resultAlternatives = new ArrayList<IResultAlternative>();
		
		for(IEditorInputResource eir : rp.getResources()){
			if(eir instanceof AbstractResultAlternative){
				AbstractResultAlternative ra = (AbstractResultAlternative)eir;
				if(this.equals(ra.getInputAlternative())){
					resultAlternatives.add(ra);
				}
			}
		}
		return resultAlternatives;
	}

}
