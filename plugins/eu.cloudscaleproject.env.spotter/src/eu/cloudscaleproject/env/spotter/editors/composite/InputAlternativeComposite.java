package eu.cloudscaleproject.env.spotter.editors.composite;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.spotter.eclipse.ui.editors.InstrumentationEditor;
import org.spotter.eclipse.ui.editors.InstrumentationEditorInput;
import org.spotter.eclipse.ui.editors.MeasurementEditor;
import org.spotter.eclipse.ui.editors.MeasurementEditorInput;

import eu.cloudscaleproject.env.spotter.editors.SpotterTabItemExtension;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;

public class InputAlternativeComposite extends Composite{
	
	private final EditorInputFolder editorInput;
	private Text textName;
	
	private Composite insComposite;
	private Composite meaComposite;
	
	private InstrumentationEditor insEditor = new InstrumentationEditor(){
		public String getContentDescription() {return "";};
		protected void setContentDescription(String description) {};
	};
	
	private MeasurementEditor meaEditor = new MeasurementEditor(){
		public String getContentDescription() {return "";};
		protected void setContentDescription(String description) {};
	};
		
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
		
		//Label label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		//label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 2);
		gd_composite.widthHint = 57;
		composite.setLayoutData(gd_composite);
		
		CTabFolder tabFolder = new CTabFolder(composite, SWT.BORDER | SWT.FLAT);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		//tabFolder.setTabHeight(10);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));	
		
		//create instrumentation editor
		try {
			CTabItem tabItemInstrumentation = new CTabItem(tabFolder, SWT.NONE);
			tabItemInstrumentation.setText("Instrumentations");
			
			insComposite = new Composite(tabFolder, SWT.NONE);
			insComposite.setLayout(new FillLayout());
			
			insEditor.addPropertyListener(new IPropertyListener() {
				@Override
				public void propertyChanged(Object source, int propId) {
					if(EditorPart.PROP_DIRTY == propId){
						insEditor.doSave(null);
					}
				}
			});
			
			IFile file = editorInput.getResource().getFile("mEnv.xml");
			
			if(!file.exists()){
				createDefaultFile(file, 
						"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
						"<measurementEnvironment xmlns=\"org.spotter.shared.environment.model\"/>"
								);
			}
			
			InstrumentationEditorInput inEditorInput = new InstrumentationEditorInput(file);
			insEditor.init(SpotterTabItemExtension.editorPart.getEditorSite(), inEditorInput);
			
			tabItemInstrumentation.setControl(insComposite);
			tabFolder.setSelection(tabItemInstrumentation);

		} catch (PartInitException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//create measurements editor
		try {
			CTabItem tabItemMeasurement = new CTabItem(tabFolder, SWT.NONE);
			tabItemMeasurement.setText("Measurements");
			
			meaComposite = new Composite(tabFolder, SWT.NONE);
			meaComposite.setLayout(new FillLayout());
			
			meaEditor.addPropertyListener(new IPropertyListener() {
				@Override
				public void propertyChanged(Object source, int propId) {
					if(EditorPart.PROP_DIRTY == propId){
						meaEditor.doSave(null);
					}
				}
			});
			
			IFile file = editorInput.getResource().getFile("mEnv.xml");
			
			if(!file.exists()){
				createDefaultFile(file, 
						"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
						"<measurementEnvironment xmlns=\"org.spotter.shared.environment.model\"/>"
								);
			}
			
			MeasurementEditorInput measurementEditorInput = new MeasurementEditorInput(file);
			meaEditor.init(SpotterTabItemExtension.editorPart.getEditorSite(), measurementEditorInput);
			
			tabItemMeasurement.setControl(meaComposite);
		} catch (PartInitException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		/*
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
		*/
		
		load();
	}
	
	private void createDefaultFile(IFile file, String string){
		if (!file.exists()) {
		    byte[] bytes = 
		    		("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
		    		+ "<measurementEnvironment xmlns=\"org.spotter.shared.environment.model\"/>").getBytes();
		    
		    InputStream source = new ByteArrayInputStream(bytes);
		    try {
				file.create(source, IResource.NONE, null);
			} catch (CoreException e1) {
				try {
					source.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				e1.printStackTrace();
			}
		}
	}
	
	public void load(){
		String name = editorInput.getName();
		textName.setText(name != null ? name : "");
	}
	
	@Override
	public void update() {
		editorInput.load();
		load();
		
		for(Control c : meaComposite.getChildren()){
			c.dispose();
		}
		for(Control c : insComposite.getChildren()){
			c.dispose();
		}
		
		meaEditor.createPartControl(meaComposite);
		insEditor.createPartControl(insComposite);
		
		meaComposite.layout();
		insComposite.layout();
		
		super.update();
	}
}
