package eu.cloudscaleproject.env.toolchain.resources.types;

import java.util.List;

public interface IInputAlternative extends IEditorInputResource{

	public String getConfigAlternativeID();
	public List<IConfigAlternative> getConfigAlternatives();
	
}
