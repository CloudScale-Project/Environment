package eu.cloudscaleproject.env.csm.properties.components.dialogs;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

import eu.cloudscaleproject.env.csm.core.Entity;
import eu.cloudscaleproject.env.csm.core.Operation;
import eu.cloudscaleproject.env.csm.core.TypeEnum;
import eu.cloudscaleproject.env.csm.properties.components.editors.IListEditor;

import org.eclipse.swt.widgets.Label;

public class DialogListEditor<T> extends Dialog{

	protected Object result;
	protected Shell shell;
	
	private List listCurrent;
	private final IListEditor<T> listEditor;
	
	private final TransactionalEditingDomain ed;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 * @wbp.parser.constructor
	 */
	public DialogListEditor(IListEditor<T> listEditor, TransactionalEditingDomain ed, String name, Shell parent, int style) {
		super(parent, style);
		setText(name);
		this.listEditor = listEditor;
		this.ed = ed;
	}

	public DialogListEditor(IListEditor<T> listEditor, TransactionalEditingDomain ed, String name, Shell parent) {
		super(parent);
		setText(name);
		this.listEditor = listEditor;
		this.ed = ed;
	}
	
	private String getObjectName(Object o){
		if(o instanceof Operation){
			Operation op = (Operation)o;
			String parameters = "";
			for(TypeEnum e : op.getParameters()){
				parameters += parameters + e.toString()+", ";
			}
			return op.getReturnValue().getName()+" "+op.getMethodName()+"("+parameters+")";
		}
		else if(o instanceof Entity){
			Entity entity = (Entity)o;
			return entity.getName();
		}
		else{
			return o.toString();
		}
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.RESIZE);
		shell.setSize(528, 300);
		shell.setText(getText());
		shell.setLayout(new GridLayout(2, false));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 2));
		composite.setLayout(new GridLayout(2, false));
		
		this.listCurrent = new List(composite, SWT.BORDER);
		GridData gd_listCurrent = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 5);
		gd_listCurrent.widthHint = 10000;
		listCurrent.setLayoutData(gd_listCurrent);
		
		Button btnNew = new Button(composite, SWT.NONE);
		GridData gd_btnNew = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_btnNew.widthHint = 27;
		btnNew.setLayoutData(gd_btnNew);
		btnNew.setText("+");
		
		btnNew.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(ed != null){
					ed.getCommandStack().execute(new RecordingCommand(ed) {
						@Override
						protected void doExecute() {
							onButtonNew();
						}
					});
				}
				else {
					onButtonNew();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		if(!listEditor.canCreateDeleteEntry()){
			btnNew.setEnabled(false);
		}
		
		Button btnDelete = new Button(composite, SWT.NONE);
		btnDelete.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		btnDelete.setText("-");
		
		btnDelete.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(ed != null){
					ed.getCommandStack().execute(new RecordingCommand(ed) {
						@Override
						protected void doExecute() {
							onButtonDelete();
						}
					});
				}
				else {
					onButtonDelete();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		if(!listEditor.canCreateDeleteEntry()){
			btnDelete.setEnabled(false);
		}

		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		update();
	}
	
	private void onButtonDelete(){
		
		if(!listEditor.canCreateDeleteEntry()){
			return;
		}
		
		java.util.List<T> selected = new ArrayList<T>();
		for(int i : listCurrent.getSelectionIndices()){
			selected.add(listEditor.getEntry(i));
		}
		for(T s : selected){
			listEditor.deleteEntry(s);
		}
		
		update();
	}
	
	private void onButtonNew(){
		if(!listEditor.canCreateDeleteEntry()){
			return;
		}
		
		listEditor.createEntry();
		update();
	}
	
	public void update(){
		
		this.listCurrent.removeAll();
		
		Iterator<T> iter = listEditor.getIterator();
		
		while(iter.hasNext()){
			listCurrent.add(getObjectName(iter.next()));
		}
	}
}
