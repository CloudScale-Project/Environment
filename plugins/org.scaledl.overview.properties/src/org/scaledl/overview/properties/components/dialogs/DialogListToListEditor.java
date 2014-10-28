package org.scaledl.overview.properties.components.dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.scaledl.overview.application.Operation;
import org.scaledl.overview.core.Entity;
import org.scaledl.overview.parametertype.Parameter;
import org.scaledl.overview.properties.components.editors.IListEditor;

public class DialogListToListEditor<T> extends Dialog{

	protected Object result;
	protected Shell shell;
	
	private List listCurrent;
	private List listCandidates;

	private Label labelCurrent;
	private Label labelCandidates;
	
	private String nameCandidates;
	
	private java.util.List<T> candidates;
	private final IListEditor<T> listEditor;
	
	private Button btnNew;
	private Button btnDelete;
	private Button btnAdd;
	private Button btnRemove;
	
	private TransactionalEditingDomain ed;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 * @wbp.parser.constructor
	 */
	public DialogListToListEditor(IListEditor<T> listEditor, TransactionalEditingDomain ed, String name, Shell parent, int style) {
		super(parent, style);
		setText(name);
		this.listEditor = listEditor;
		this.ed = ed;
	}

	public DialogListToListEditor(IListEditor<T> listEditor, TransactionalEditingDomain ed, String name, Shell parent) {
		super(parent);
		setText(name);
		this.listEditor = listEditor;
		this.ed = ed;
	}
	
	public void setData(String nameCandidates, Collection<T> candidates){
		
		this.nameCandidates = nameCandidates;
		this.candidates = new ArrayList<T>(candidates);
		
		Iterator<T> iter = listEditor.getIterator();
		while(iter.hasNext()){
			this.candidates.remove(iter.next());
		}
	}

	public void setData(String nameCandidates, EList<T> candidates){
		
		this.nameCandidates = nameCandidates;
		this.candidates = candidates;
		
		Iterator<T> iter = listEditor.getIterator();
		while(iter.hasNext()){
			this.candidates.remove(iter.next());
		}
	}
	
	private String getObjectName(Object o){
		if(o instanceof Entity){
			Entity entity = (Entity)o;
			return entity.getName();
		}
		else if(o instanceof Operation){
			Operation op = (Operation)o;
			String parameters = "";
			for(Parameter p : op.getParameters()){
				parameters += parameters + p.getName()+", ";
			}
			return op.getReturnParameter().getName()+" "+op.getName()+"("+parameters+")";
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
		GridLayout gl_composite = new GridLayout(3, false);
		composite.setLayout(gl_composite);
		
		labelCurrent = new Label(composite, SWT.NONE);
		labelCurrent.setText("Current:");
		new Label(composite, SWT.NONE);
		
		labelCandidates = new Label(composite, SWT.NONE);
		labelCandidates.setText(this.nameCandidates);
		
		this.listCurrent = new List(composite, SWT.BORDER);
		GridData gd_listCurrent = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 4);
		gd_listCurrent.widthHint = 10000;
		listCurrent.setLayoutData(gd_listCurrent);
		
		btnNew = new Button(composite, SWT.NONE);
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
		
		this.listCandidates = new List(composite, SWT.BORDER);
		GridData gd_listCandidates = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 4);
		gd_listCandidates.widthHint = 10000;
		listCandidates.setLayoutData(gd_listCandidates);
		
		btnDelete = new Button(composite, SWT.NONE);
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
		
		btnAdd = new Button(composite, SWT.NONE);
		GridData gd_btnAdd = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_btnAdd.heightHint = 71;
		btnAdd.setLayoutData(gd_btnAdd);
		btnAdd.setText("<-");
		
		
		btnAdd.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					
					if(ed != null){
						ed.getCommandStack().execute(new RecordingCommand(ed) {
							@Override
							protected void doExecute() {
								onButtonAdd();
							}
						});
					}
					else {
						onButtonAdd();
					}
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
				}
			});
		
		btnRemove = new Button(composite, SWT.NONE);
		GridData gd_btnRemove = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_btnRemove.heightHint = 72;
		btnRemove.setLayoutData(gd_btnRemove);
		btnRemove.setText("->");
		
		btnRemove.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					
					if(ed != null){
						ed.getCommandStack().execute(new RecordingCommand(ed) {
							@Override
							protected void doExecute() {
								onButtonRemove();
							}
						});
					}
					else {
						onButtonRemove();
					}
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
				}
			});
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		update();
	}
	
	private void onButtonAdd(){
		
		java.util.List<T> selected = new ArrayList<T>();
		for(int i : listCandidates.getSelectionIndices()){
			selected.add(candidates.get(i));
		}
		candidates.removeAll(selected);
		
		for(T s : selected){
			this.listEditor.add(s);
		}
		update();
	}
	
	private void onButtonDelete(){
		java.util.List<T> selected = new ArrayList<T>();
		for(int i : listCurrent.getSelectionIndices()){
			selected.add(this.listEditor.getEntry(i));
		}
		
		for(T s : selected){
			this.listEditor.deleteEntry(s);
		}
		
		update();
	}
	
	private void onButtonRemove(){
		java.util.List<T> selected = new ArrayList<T>();
		for(int i : listCurrent.getSelectionIndices()){
			selected.add(this.listEditor.getEntry(i));
		}
		
		candidates.addAll(selected);
		
		for(T s : selected){
			this.listEditor.removeEntry(s);
		}
		update();
	}

	private void onButtonNew(){
		this.listEditor.createEntry();
		update();
	}
	
	public void update(){
		
		if(!listEditor.canCreateDeleteEntry()){
			btnNew.setEnabled(false);
			btnDelete.setEnabled(false);
		}
		else{
			btnNew.setEnabled(true);
			btnDelete.setEnabled(true);
		}
		
		this.labelCandidates.setText(nameCandidates);
		
		this.listCurrent.removeAll();
		
		this.listCandidates.removeAll();
		for(Object o : this.candidates){
			listCandidates.add(getObjectName(o));
		}
		
		Iterator<T> iter = this.listEditor.getIterator();
		while(iter.hasNext()){
			listCurrent.add(getObjectName(iter.next()));
		}
	}
}
