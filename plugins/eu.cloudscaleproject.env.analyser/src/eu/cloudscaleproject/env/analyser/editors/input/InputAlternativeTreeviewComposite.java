package eu.cloudscaleproject.env.analyser.editors.input;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.ui.celleditor.AdapterFactoryTreeEditor;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.view.ExtendedPropertySheetPage;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.palladiosimulator.monitorrepository.util.MonitorRepositoryResourceImpl;

import de.uka.ipd.sdq.pcm.allocation.util.AllocationResourceImpl;
import de.uka.ipd.sdq.pcm.repository.util.RepositoryResourceImpl;
import de.uka.ipd.sdq.pcm.resourceenvironment.util.ResourceenvironmentResourceImpl;
import de.uka.ipd.sdq.pcm.system.util.SystemResourceImpl;
import de.uka.ipd.sdq.pcm.usagemodel.util.UsagemodelResourceImpl;
import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.IDirtyAdapter;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.ProjectEditorSelectionService;
import eu.cloudscaleproject.env.toolchain.util.EMFPopupMenuSupport;

public class InputAlternativeTreeviewComposite extends Composite implements IPropertySheetPageProvider{
	
	private final InputAlternative alternative;
	
	private final Tree tree;
	private final TreeViewer treeViewer;
	
	private final IDirtyAdapter dirtyAdapter;
	private final AdapterFactoryContentProvider contentProvider;
	private final EMFPopupMenuSupport menuSupport;
		
	public InputAlternativeTreeviewComposite(IEditorPart editor, InputAlternative alternative, Composite parent, int style) {
		super(parent, style);
		
		this.alternative = alternative;
		this.dirtyAdapter = (IDirtyAdapter)editor.getAdapter(IDirtyAdapter.class);
		
		setLayout(new GridLayout(1, false));
		
		this.tree = new Tree(this, SWT.BORDER | SWT.V_SCROLL);
		this.tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		this.treeViewer = new TreeViewer(tree);
		
		this.contentProvider = new AdapterFactoryContentProvider(alternative.getAdapterFactory());
		this.treeViewer.setContentProvider(contentProvider);
		this.treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(alternative.getAdapterFactory()));
		this.treeViewer.addFilter(new ModelViewFilter());
		
		this.treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				ISelection s = treeViewer.getSelection();
				Object element = ((StructuredSelection)s).getFirstElement();
				if (element instanceof EObject) {
					EObject eo = (EObject)element;
					Resource res = eo.eResource();
					IFile file = ExplorerProjectPaths.getFileFromEmfResource(res);
					
					//find diagram file from model file
					String diagramFilename = res.getURI().lastSegment() + "_diagram";
					IFile diagramFile = null;
					
					int i=0;
					IContainer parent = file.getParent();
					while(parent != null){
						diagramFile = parent.getFile(new Path(diagramFilename));
						if(diagramFile.exists()){
							break;
						}
						parent = parent.getParent();
						i++;
						if(i >= 2){break;}
					}
										
					if(diagramFile != null && diagramFile.exists()){
						file = diagramFile;
					}
					
					try {
						IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), file);
					} catch (PartInitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		menuSupport = new EMFPopupMenuSupport(alternative.getEditingDomain());
		menuSupport.setViewer(treeViewer);
				
		new AdapterFactoryTreeEditor(tree, this.alternative.getAdapterFactory());
		
		//wire dirty state from alternative to editor part 
		final PropertyChangeListener listener = new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				dirtyAdapter.fireDirtyState();
			}
		};
		this.alternative.addPropertyChangeListener(listener);
		addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent e) {
				InputAlternativeTreeviewComposite.this.alternative.removePropertyChangeListener(listener);
			}
		});
		
		addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				ProjectEditorSelectionService.getInstance().setSelectionProviderDelegate(null);				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				ProjectEditorSelectionService.getInstance().setSelectionProviderDelegate(treeViewer);				
			}
		});
		
		this.treeViewer.setInput(alternative.getResourceSet());
		this.treeViewer.expandToLevel(2);
		this.treeViewer.refresh();
	}
	
	private class ModelViewFilter extends ViewerFilter{

		@Override
		public boolean select(Viewer viewer, Object parentElement,
				Object element) {
			
			if(element instanceof Resource){
				if(element instanceof RepositoryResourceImpl){
					return true;
				}
				if(element instanceof SystemResourceImpl){
					return true;
				}
				if(element instanceof AllocationResourceImpl){
					return true;
				}
				if(element instanceof ResourceenvironmentResourceImpl){
					return true;
				}
				if(element instanceof UsagemodelResourceImpl){
					return true;
				}	
				if(element instanceof MonitorRepositoryResourceImpl){
					return true;
				}
			
				return false;
			}
			
			return true;
		}
	}

	@Override
	public IPropertySheetPage getPropertySheetPage() {
		ExtendedPropertySheetPage propertySheetPage = new ExtendedPropertySheetPage(
				(AdapterFactoryEditingDomain)alternative.getEditingDomain());
		propertySheetPage.setPropertySourceProvider(contentProvider);
		return propertySheetPage;
	}
}
