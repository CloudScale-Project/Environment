package eu.cloudscaleproject.env.toolchain.ui.dialogs;

import java.util.List;

import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ShowResultAlternativesDialog extends ShowAlternativeDialog {
	
	public ShowResultAlternativesDialog(List<? extends IEditorInputResource> alternatives) {
		super(alternatives);
	}
	
	@Override
	protected String getTitle() {
		return "Show results";
	}
	
	@Override
	protected String getEmptyText() {
		return "No results available for this alternative. Create & Run configuration for this input.";
	}
	
}