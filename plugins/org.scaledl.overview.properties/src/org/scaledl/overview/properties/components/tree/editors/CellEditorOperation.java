package org.scaledl.overview.properties.components.tree.editors;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.scaledl.overview.application.Operation;
import org.scaledl.overview.application.OperationInterfaceContainer;
import org.scaledl.overview.core.Entity;
import org.scaledl.overview.diagram.Util;
import org.scaledl.overview.diagram.editor.interfaces.OICEditor;
import org.scaledl.overview.diagram.editor.interfaces.OICEditorInput;
import org.scaledl.overview.properties.components.editors.IEditor;
import org.scaledl.overview.util.OverviewUtil;

public class CellEditorOperation extends CellEditor implements IEditor{

	private final Operation operation;
	private DataBindingContext bindingContext = new DataBindingContext();
	
	public CellEditorOperation(Composite composite, Operation op){
		super(composite);
		this.operation = op;

		init();
	}
	
	public CellEditorOperation(Composite composite, Operation op, int style){
		super(composite, style);
		this.operation = op;
		
		init();
	}
	
	public boolean canEdit(){
		if(operation instanceof EObject){
			EObject eobject = (EObject)operation;
			
			while(eobject != null){
				if(eobject instanceof Entity){
					Entity e = (Entity)eobject;
					if(OverviewUtil.hasExternalModel(e)){return false;}
				}
				eobject = eobject.eContainer();
			}
		}
		
		return true;
	}
	
	@Override
	protected Control createControl(Composite parent) {
		
        Font font = parent.getFont();
        Color bg = parent.getBackground();

        Composite c = new Composite(parent, getStyle());
        c.setFont(font);
        c.setBackground(bg);
        
        FillLayout fill = new FillLayout();
        c.setLayout(fill);
        c.setBounds(parent.getClientArea());
        
        Button editButton = new Button(c, SWT.NONE);
        editButton.setText("Edit operation");
        editButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				EObject eobject = operation.eContainer().eContainer();
				if(eobject instanceof OperationInterfaceContainer){
					Util.openEditor(new OICEditorInput((OperationInterfaceContainer)eobject), OICEditor.ID);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        
        /*
        GridLayout cLayout = new GridLayout(3, false);
        cLayout.horizontalSpacing = 3;
        cLayout.marginHeight = 0;
        cLayout.marginWidth = 0;
        c.setLayout(cLayout);
        c.setBounds(parent.getClientArea());
        
        //return type
		comboReturnType = new Combo(c, SWT.CENTER);
		GridData gd_combo = new GridData(SWT.LEFT, SWT.FILL, false, false);
		gd_combo.widthHint = 90;
		comboReturnType.setLayoutData(gd_combo);
		comboReturnType.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(final ModifyEvent e) {
				if(ed != null){
					ed.getCommandStack().execute(new RecordingCommand(ed) {
						
						@Override
						protected void doExecute() {
							operation.setReturnValue(TypeEnum.get((String)(comboReturnType.getText())));
						}
					});
				}
			}
		});
		comboReturnType.setBounds(0,0,5,5);
		
		//method name
		textMethodName = new Text(c, SWT.SINGLE | SWT.BORDER);
		textMethodName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		//parameters
		textParameters = new Text(c, SWT.SINGLE | SWT.BORDER);
		textParameters.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		textParameters.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(final ModifyEvent e) {
				if(ed != null){
					ed.getCommandStack().execute(new RecordingCommand(ed) {
						
						@Override
						protected void doExecute() {
							String text = textParameters.getText();
							String[] parts = text.split(",");
							
							operation.getParameters().clear();
							for(String p : parts){
								TypeEnum e = TypeEnum.get(p.trim());
								if(e != null){
									operation.getParameters().add(e);
								}
							}
						}
					});
				}
			}
		});
		*/
		
		return c;
	}
	
	private void init(){
		/*
		String[] values = new String[TypeEnum.values().length];
		for(int i=0; i< TypeEnum.values().length; i++){
			values[i] = TypeEnum.values()[i].getLiteral();
		}
		
		//set combo
		comboReturnType.setItems(values);
		comboReturnType.select(operation.getReturnValue().ordinal());
		
		//set parameters
		String parameters = "";
		for(TypeEnum e : operation.getParameters()){
			parameters += e.getLiteral() + ", ";
		}
		if(!parameters.isEmpty()){parameters = parameters.substring(0, parameters.length()-2);};
		textParameters.setText(parameters);
		
		if(bindingContext != null){
			bindingContext.dispose();
		}
		this.bindingContext = initBindings();
		*/
	}
	
	/*
	private DataBindingContext initBindings(){

		DataBindingContext bindingContext = new DataBindingContext();
		
		this.ed = TransactionalEditingDomain.
        		Factory.INSTANCE.getEditingDomain(EcoreUtil.getRootContainer(operation).eResource().getResourceSet());
		
		//method name
		IObservableValue emfNameObservable = EMFEditObservables.observeValue(this.ed, this.operation,
				ApplicationPackage.Literals.OPERATION__METHOD_NAME);
		IObservableValue localNameObservable = WidgetProperties.text(SWT.Modify).observe(this.textMethodName);
		bindingContext.bindValue(localNameObservable, emfNameObservable, null, null);
		bindingContext.updateTargets();
		
		return bindingContext;
	}
	*/

	@Override
	protected Object doGetValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void doSetFocus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doSetValue(Object value) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void dispose() {
		super.dispose();
		bindingContext.dispose();
	}

}
