package eu.cloudscaleproject.env.csm.properties.components;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

import eu.cloudscaleproject.env.csm.properties.components.editors.AbstractEditorList;
import eu.cloudscaleproject.env.csm.properties.components.editors.AbstractEditorListToList;
import eu.cloudscaleproject.env.csm.properties.components.editors.IListEditor;
import eu.cloudscaleproject.env.csm.properties.components.tree.BasicTreeContentProvider;
import eu.cloudscaleproject.env.csm.properties.components.tree.BasicTreeEditingSupport;
import eu.cloudscaleproject.env.csm.properties.components.tree.BasicTreeLabelProvider;
import eu.cloudscaleproject.env.csm.properties.components.tree.BasicTreeViewFilter;
import eu.cloudscaleproject.env.csm.properties.components.tree.TreeRow;

public class CsmTreeViewEditor extends Composite {
    
    public final TreeViewer treeViewer;
    public final Tree tree;
    
    public final Button buttonAdd;
    public final Button buttonAddFrom;
    public final Button buttonRemove;
    
    private TreeRow selectedRow = null;
    
    private final BasicTreeViewFilter filter = new BasicTreeViewFilter();
    
    private TransactionalEditingDomain ed;
    
    /**
     * Create the composite.
     * @param parent
     * @param style
     */
    public CsmTreeViewEditor(Composite parent, int style) {
        super(parent, style);
        
        GridLayout gridLayout = new GridLayout(2, false);
        setLayout(gridLayout);
        
        this.treeViewer = new TreeViewer(this, SWT.BORDER);
        this.tree = treeViewer.getTree();
        tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        tree.setLinesVisible(true);
        tree.setHeaderVisible(true);
        
        treeViewer.setContentProvider(new BasicTreeContentProvider(treeViewer));
        
        TreeViewerColumn treeViewerColumnName = new TreeViewerColumn(treeViewer, SWT.LEFT);
        treeViewerColumnName.setLabelProvider(new BasicTreeLabelProvider());
        treeViewerColumnName.setEditingSupport(new BasicTreeEditingSupport(treeViewerColumnName.getViewer(), 0));
        
        //
        // Columns - WIDTH is controlled through Resize listener
        //
        TreeColumn columnName = treeViewerColumnName.getColumn();
        columnName.setText("Hierarchy");
        columnName.setResizable(true);
        
        TreeViewerColumn treeViewerColumnValue = new TreeViewerColumn(treeViewer, SWT.LEFT);
        treeViewerColumnValue.setLabelProvider(new BasicTreeLabelProvider());
        treeViewerColumnValue.setEditingSupport(new BasicTreeEditingSupport(treeViewerColumnValue.getViewer(), 1));
        
        TreeColumn columnValue = treeViewerColumnValue.getColumn();
        columnValue.setText("Object/Name");
        columnValue.setResizable(true);

        TreeViewerColumn treeViewerColumnDetail = new TreeViewerColumn(treeViewer, SWT.LEFT);
        treeViewerColumnDetail.setLabelProvider(new BasicTreeLabelProvider());
        treeViewerColumnDetail.setEditingSupport(new BasicTreeEditingSupport(treeViewerColumnDetail.getViewer(), 2));
        
        TreeColumn columnDetail = treeViewerColumnDetail.getColumn();
        columnDetail.setText("Provided from");
        columnDetail.setResizable(true);
        
        tree.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				
				// Problem on windows machines - last column is empty
				// Works! - I don't care...
			    int width = tree.getClientArea().width;
			    tree.getColumn(0).setWidth(300);
			    tree.getColumn(1).setWidth(Math.max(100, width-500-20));
			    tree.getColumn(2).setWidth(200);
			}
		});
        
        // resize the row height using a MeasureItem listener
        tree.addListener(SWT.MeasureItem, new Listener() {
            public void handleEvent(Event event) {
                event.height = 22;
            }
        });
        // // //
        
        Composite composite = new Composite(this, SWT.NONE);
        GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
        gd_composite.widthHint = 120;
        composite.setLayoutData(gd_composite);
        composite.setLayout(new FormLayout());
        
        this.buttonAdd = new Button(composite, SWT.NONE);
        FormData fd_buttonAdd = new FormData();
        fd_buttonAdd.top = new FormAttachment(0, 10);
        fd_buttonAdd.right = new FormAttachment(100, -10);
        fd_buttonAdd.left = new FormAttachment(0, 10);
        buttonAdd.setLayoutData(fd_buttonAdd);
        buttonAdd.setText("Add new");
        buttonAdd.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	ISelection s = treeViewer.getSelection();
            	
            	if(s == null || !(s instanceof IStructuredSelection)){
            		buttonAdd.setEnabled(false);
            		buttonAddFrom.setEnabled(false);
            		buttonRemove.setEnabled(false);
            		return;
            	}
            	
            	IStructuredSelection selection = (IStructuredSelection)s;
            	final TreeRow row = ((TreeRow)selection.getFirstElement());
                
                ed.getCommandStack().execute(new RecordingCommand(ed) {
                    @Override
                    protected void doExecute() {
                    	//list selected 
                    	if(row.getChildListEditor() instanceof AbstractEditorList<?>){
                    		AbstractEditorList<?> editor = (AbstractEditorList<?>)row.getChildListEditor();
                    		if(editor.canCreateDeleteEntry()){
                    			editor.createEntry();
                    		}
                    		else{
                    			editor.openDialog();
                    		}
                    		treeViewer.expandToLevel(row, 1);
                    	}
                    	else{
                			buttonAdd.setEnabled(false);
                		}
                    }
                });
            }
        });

        this.buttonAddFrom = new Button(composite, SWT.NONE);
        FormData fd_buttonAddFrom = new FormData();
        fd_buttonAddFrom.right = new FormAttachment(buttonAdd, 0, SWT.RIGHT);
        fd_buttonAddFrom.top = new FormAttachment(buttonAdd, 6);
        fd_buttonAddFrom.left = new FormAttachment(0, 10);
        buttonAddFrom.setLayoutData(fd_buttonAddFrom);
        buttonAddFrom.setText("Add from...");
        buttonAddFrom.addSelectionListener(new SelectionAdapter() {
        	@Override
            public void widgetSelected(SelectionEvent e) {
            	ISelection s = treeViewer.getSelection();
            	
            	if(s == null || !(s instanceof IStructuredSelection)){
            		buttonAdd.setEnabled(false);
            		buttonAddFrom.setEnabled(false);
            		buttonRemove.setEnabled(false);
            		return;
            	}
            	
            	IStructuredSelection selection = (IStructuredSelection)s;
            	final TreeRow row = ((TreeRow)selection.getFirstElement());
                
                ed.getCommandStack().execute(new RecordingCommand(ed) {
                    @Override
                    protected void doExecute() {
                    	//list selected 
                    	if(row.getChildListEditor() instanceof AbstractEditorList<?>){
                    		AbstractEditorList<?> editor = (AbstractEditorList<?>)row.getChildListEditor();
                    		editor.openDialog();
                    	}
                    	else{
                			buttonAddFrom.setEnabled(false);
                		}
                    }
                });
            }
		});
        
        this.buttonRemove = new Button(composite, SWT.NONE);
        FormData fd_buttonRemove = new FormData();
        fd_buttonRemove.top = new FormAttachment(buttonAdd, 67);
        fd_buttonRemove.left = new FormAttachment(buttonAdd, 0, SWT.LEFT);
        fd_buttonRemove.right = new FormAttachment(100, -10);
        buttonRemove.setLayoutData(fd_buttonRemove);
        buttonRemove.setText("Remove");
        
        buttonRemove.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ISelection s = treeViewer.getSelection();
                
                if(s == null || !(s instanceof IStructuredSelection)){
            		buttonAdd.setEnabled(false);
            		buttonAddFrom.setEnabled(false);
            		buttonRemove.setEnabled(false);
            		return;
            	}

            	IStructuredSelection selection = (IStructuredSelection)s;
                
                if (!selection.isEmpty())
                {
                	final Object data = ((TreeRow)selection.getFirstElement()).getCell(1).getData();
                	final TreeRow row = (TreeRow)selection.getFirstElement();

                    ed.getCommandStack().execute(
                        new RecordingCommand(ed) {
                            
                            @Override
                            protected void doExecute() {
                            	if(row.getParent() != null && 
                            			row.getParent().getChildListEditor() instanceof AbstractEditorList<?>){
                            	
                            		AbstractEditorList<?> editor = (AbstractEditorList<?>)row.getParent().getChildListEditor();
                            		if(editor.canCreateDeleteEntry()){
                            			editor.deleteEntry(data);
                            		}
                            		else{
                            			editor.removeEntry(data);
                            		}
                            	}
                            	else{
                        			buttonRemove.setEnabled(false);
                        		}
                            }
                        });
                }
            }
        });
        
        tree.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				ISelection s = treeViewer.getSelection();
				if(s == null){selectedRow = null;}
				
				Object element = ((StructuredSelection)s).getFirstElement();
            	if(element == null){selectedRow = null;}
            	
            	selectedRow = (TreeRow)element; 
                updateButtons();
			}
		});
        
        treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
            	selectedRow = (TreeRow)((StructuredSelection)event.getSelection()).getFirstElement();
            	updateButtons();
            }
        });


        buttonAdd.setEnabled(false);
        buttonAddFrom.setEnabled(false);
        buttonRemove.setEnabled(false);
        
        final Button checkShowAll = new Button(composite, SWT.CHECK);
        checkShowAll.setToolTipText("Show all properties");
        FormData fd_checkShowAll = new FormData();
        fd_checkShowAll.bottom = new FormAttachment(100, -10);
        fd_checkShowAll.right = new FormAttachment(buttonAdd, 0, SWT.RIGHT);
        fd_checkShowAll.left = new FormAttachment(buttonAdd, 0, SWT.LEFT);
        checkShowAll.setLayoutData(fd_checkShowAll);
        checkShowAll.setText("Show all");
        
        checkShowAll.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(checkShowAll.getSelection()){
					treeViewer.removeFilter(filter);
					treeViewer.expandToLevel(2);
				}
				else{
					treeViewer.addFilter(filter);
					treeViewer.expandToLevel(2);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
        treeViewer.addFilter(filter);
        
        treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				ISelection s = treeViewer.getSelection();
				if(s == null){selectedRow = null;}
				
				Object element = ((StructuredSelection)s).getFirstElement();
            	if(element == null){selectedRow = null;}
            	
            	selectedRow = (TreeRow)element;
            	IListEditor<?> editor = selectedRow.getChildListEditor();
            	if(editor != null){
            		editor.openDialog();
            	}
			}
		});
        
        treeViewer.getTree().pack();
    }
    
    private void updateButtons(){
    	TreeRow row = selectedRow;

    	if(row == null){
    		return;
    	}
    	
        if(row.getChildListEditor() instanceof AbstractEditorList<?>){
        	AbstractEditorList<?> editor = (AbstractEditorList<?>)row.getChildListEditor();
        	if(editor.canEdit() && editor.canCreateDeleteEntry()){
        		buttonAdd.setEnabled(true);
        	}
        	else{
        		buttonAdd.setEnabled(false);
        	}
        }
        else{
       		buttonAdd.setEnabled(false);
        }

        if(row.getChildListEditor() instanceof AbstractEditorListToList<?>){
        	AbstractEditorList<?> editor = (AbstractEditorList<?>)row.getChildListEditor();
        	if(editor.canEdit()){
        		buttonAddFrom.setEnabled(true);
        	}
        	else{
        		buttonAddFrom.setEnabled(false);
        	}
        }
        else{
       		buttonAddFrom.setEnabled(false);
        }

        if(row.getParent() != null && row.getParent().getChildListEditor() instanceof AbstractEditorList<?>){
    		AbstractEditorList<?> editor = (AbstractEditorList<?>)row.getParent().getChildListEditor();
        	if(editor.canEdit()){
        		buttonRemove.setEnabled(true);
        	}
        	else{
        		buttonRemove.setEnabled(false);
        	}
        }
        else{
        	buttonRemove.setEnabled(false);
        }
    }

    public void setInput(EObject eobject){
    	
        ed = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(
                		eobject.eResource().getResourceSet());
    	
    	treeViewer.setInput(eobject);
    	treeViewer.expandToLevel(2);
    }
    
    @Override
    protected void checkSubclass() {
    }
}
