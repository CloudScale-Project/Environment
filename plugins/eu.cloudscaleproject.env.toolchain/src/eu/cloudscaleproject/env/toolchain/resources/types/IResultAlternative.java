package eu.cloudscaleproject.env.toolchain.resources.types;

import eu.cloudscaleproject.env.toolchain.CSTool;

public interface IResultAlternative extends IEditorInputResource{
	
	public CSTool getTool();
	public IInputAlternative getInputAlternative();
	public IConfigAlternative getConfigAlternative();

}
