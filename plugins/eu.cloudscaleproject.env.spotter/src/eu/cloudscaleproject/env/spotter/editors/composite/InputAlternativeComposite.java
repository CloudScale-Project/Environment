package eu.cloudscaleproject.env.spotter.editors.composite;

import org.eclipse.core.resources.IFile;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.spotter.eclipse.ui.editors.InstrumentationEditor;
import org.spotter.eclipse.ui.editors.MeasurementEditor;
import org.spotter.eclipse.ui.editors.factory.ElementFactory;

import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;

public class InputAlternativeComposite extends Composite{
	
	private final EditorInputFolder editorInput;
	private Text textName;
		
	public InputAlternativeComposite(Composite parent, int style, final EditorInputFolder editorInput) {
		super(parent, style);
		this.editorInput = editorInput;

		setLayout(new GridLayout(2, false));
		
		Label lblName = new Label(this, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblName.setText("Name:");
		
		textName = new Text(this, SWT.BORDER);
		textName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textName.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				InputAlternativeComposite.this.editorInput.setName(textName.getText().trim());
				InputAlternativeComposite.this.editorInput.save();
			}
		});
		
		Label label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 2);
		gd_composite.widthHint = 57;
		composite.setLayoutData(gd_composite);
		
		Button btnIsa = new Button(composite, SWT.NONE);
		btnIsa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnIsa.setBounds(0, 0, 64, 15);
		btnIsa.setText("Open Instrumentation Satellite Adapter Editor...");
		btnIsa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IFile file = InputAlternativeComposite.this.editorInput.getResource().getFile("mEnv.xml");
				InstrumentationEditor.openInstance(
						(IEditorInput) ElementFactory.createEditorInput(InstrumentationEditor.ID, file), 
						InstrumentationEditor.ID);
			}
		});
		
		Button btnMsa = new Button(composite, SWT.NONE);
		btnMsa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnMsa.setText("Open Measurement Satellite Adapter Editor...");
		btnMsa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IFile file = InputAlternativeComposite.this.editorInput.getResource().getFile("mEnv.xml");
				MeasurementEditor.openInstance(
						(IEditorInput) ElementFactory.createEditorInput(MeasurementEditor.ID, file), 
						MeasurementEditor.ID);
			}
		});
		
		load();
	}
	
	public void load(){
		String name = editorInput.getName();
		textName.setText(name != null ? name : "");
	}
	
	@Override
	public void update() {
		editorInput.load();
		load();
		super.update();
	}
}
