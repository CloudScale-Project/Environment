package eu.cloudscaleproject.env.toolchain.resources.types;

import java.util.List;

import eu.cloudscaleproject.env.toolchain.CSTool;

public interface IInputAlternative extends IEditorInputResource{

	public CSTool getTool();
	public List<IConfigAlternative> getConfigAlternatives();
	public List<IResultAlternative> getResultAlternatives();
	
}
