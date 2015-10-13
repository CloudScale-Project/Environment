package eu.cloudscaleproject.env.toolchain.resources.types;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;

public abstract class AbstractInputAlternative extends EditorInputEMF implements IInputAlternative{

	private static final Logger logger = Logger.getLogger(AbstractInputAlternative.class.getName());
	
	private final String confID;
	
	public AbstractInputAlternative(IProject project, IFolder folder, ModelType[] modelTypes, String inputID, String confID) {
		super(project, folder, modelTypes, inputID);
		this.confID = confID;
	}
	
	@Override
	public String getConfigAlternativeID() {
		return confID;
	}
	
	@Override
	public List<IConfigAlternative> getConfigAlternatives() {
		
		ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(getProject(), confID);
		
		if(rp == null){
			logger.warning("Resource provider not found! ID: " + confID);
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

}
