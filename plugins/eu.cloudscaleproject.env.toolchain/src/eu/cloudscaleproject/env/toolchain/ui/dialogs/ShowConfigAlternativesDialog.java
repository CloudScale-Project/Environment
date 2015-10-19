package eu.cloudscaleproject.env.toolchain.ui.dialogs;

import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ShowConfigAlternativesDialog extends ShowAlternativeDialog {

	public static final int CREATE_ID = IDialogConstants.CLIENT_ID + 1;
	private static final String CREATE_LABEL = "Create configuration ...";

	public ShowConfigAlternativesDialog(List<? extends IEditorInputResource> alternatives) {
		super(alternatives);
	}

	@Override
	protected String getTitle() {
		return "Show configuration";
	}

	@Override
	protected String getEmptyText() {
		return "No configuration available for this alternative.\nCreate one...";
	}

	protected void createPressed() {
		// TODO: call action
		throw new IllegalStateException("TODO: implementation");
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		// TODO Auto-generated method stub
		parent.setLayout(new GridLayout());
		if (!alternatives.isEmpty()) {
			createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		}

		createButton(parent, CREATE_ID, CREATE_LABEL, false);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	protected void buttonPressed(int buttonId) {
		if (CREATE_ID == buttonId) {
			createPressed();
		} else {
			super.buttonPressed(buttonId);
		}
	}
}