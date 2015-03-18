package eu.cloudscaleproject.env.toolchain.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.ui.celleditor.AdapterFactoryTreeEditor;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.ProjectEditorSelectionService;

public class EMFTreeviewComposite extends Composite
{

	private final Tree tree;
	private final TreeViewer treeViewer;
	private final AdapterFactoryContentProvider contentProvider;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public EMFTreeviewComposite(AdapterFactory adapterFactory, ResourceSet resSet, Composite parent, int style)
	{
		super(parent, style);
		setLayout(new GridLayout(1, false));

		this.tree = new Tree(this, SWT.BORDER | SWT.V_SCROLL);
		this.tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		this.treeViewer = new TreeViewer(tree);
		this.treeViewer.addDoubleClickListener(new IDoubleClickListener()
		{
			@Override
			public void doubleClick(DoubleClickEvent event)
			{
				IFile file = getSelectedDiagramFile();
				if (file == null)
					file = getSelectedModelFile();

				if (file != null)
					openEditor(file);
			}
		});

		contentProvider = new AdapterFactoryContentProvider(adapterFactory);
		this.treeViewer.setContentProvider(contentProvider);
		this.treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		new AdapterFactoryTreeEditor(tree, adapterFactory);

		tree.addFocusListener(new FocusListener()
		{
			@Override
			public void focusLost(FocusEvent e)
			{
			}

			@Override
			public void focusGained(FocusEvent e)
			{
				ProjectEditorSelectionService.getInstance().setSelectionProviderDelegate(treeViewer);
			}
		});

		this.treeViewer.setInput(resSet);
		this.treeViewer.expandToLevel(2);
		this.treeViewer.refresh();
	}
	
	public void reload(){
		if(!this.tree.isDisposed()){
			this.treeViewer.expandToLevel(2);
			this.treeViewer.refresh();
		}
	}
	
	private IFile getSelectedModelFile()
	{
		ISelection s = treeViewer.getSelection();
		Object element = ((StructuredSelection) s).getFirstElement();
		
		System.out.println(element);
		
		if (element instanceof Resource)
		{
			return ExplorerProjectPaths.getFileFromEmfResource((Resource)element);
		}

		if (element instanceof EObject)
		{
			EObject eo = (EObject) element;
			Resource res = eo.eResource();
			return ExplorerProjectPaths.getFileFromEmfResource(res);
		}
		

		return null;
	}

	private IFile getSelectedDiagramFile ()
	{
		IFile modelFile = getSelectedModelFile();

		if (modelFile == null) return null;

		String path = modelFile.getName() + "_diagram";

        IFile diagramFile = modelFile.getParent().getFile(new Path(path));
        if (diagramFile.exists())
        {
                return diagramFile;
        }
        else
        {
                diagramFile = modelFile.getParent().getParent().getFile(new Path(path));
                if (diagramFile.exists())
                {
                	return diagramFile;
                }
        }
        
        return null;
	}


	private void openEditor(IFile file)
	{
        try
        {
                IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), file);
        }
        catch (PartInitException e)
        {
                e.printStackTrace();
        }

	}

	public void addFilter(ViewerFilter filter)
	{
		this.treeViewer.addFilter(filter);
	}
}
