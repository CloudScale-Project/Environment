package eu.cloudscaleproject.env.toolchain.editors;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.Activator;
import eu.cloudscaleproject.env.toolchain.IDirtyAdapter;
import eu.cloudscaleproject.env.toolchain.ProjectEditorExtension;
import eu.cloudscaleproject.env.toolchain.ProjectEditorSelectionService;

public class ProjectEditor extends EditorPart implements IDirtyAdapter{

	private static final Logger logger = Logger.getLogger(ProjectEditor.class.getName());	
	public static final String ID = "eu.cloudscaleproject.env.toolchain.ToolchainEditorPart";
		
	private CTabFolder tabFolder;
	
	@Inject
	private ProjectEditorProvider editorProvider;
		
	public ProjectEditor() {
		IEclipseContext context = EclipseContextFactory.getServiceContext(Activator.plugin.getBundle().getBundleContext());
		ContextInjectionFactory.inject(this, context);
	}
	
	@Override
	public String getPartName() {
		IProject p = ExplorerProjectPaths.getProject(this);
		return "Dashboard ("+p.getName()+")";
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		if (adapter.equals(IPropertySheetPage.class)) {
			for(ProjectEditorExtension pee : editorProvider.getToolExtensions()){
				if(tabFolder.getSelection() != null && tabFolder.getSelection().equals(pee.getTabItem())){
					return pee.getPropertySheetPage();
				}
			}
			return super.getAdapter(adapter);
		}
		else if(adapter.equals(IDirtyAdapter.class)){
			return this;
		}
		else {
			return super.getAdapter(adapter);
		}
	}
	
	public CTabFolder getTabFolder(){
		return tabFolder;
	}
	
	public void openTab(String editorExtensionID, String action){
		if(tabFolder != null){
			ProjectEditorExtension pee = editorProvider.getToolExtension(editorExtensionID);
			if(pee != null){
				tabFolder.setSelection(pee.getTabItem());
				pee.handleAction(action);				
			}
			else{
				logger.warning("Can't open tab item! Can't find extension pont!");
			}
		}
	}
	
	@Override
	public void fireDirtyState() {
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		for(ProjectEditorExtension pee : editorProvider.getToolExtensions()){
			pee.save();
		}
		fireDirtyState();
	}

	@Override
	public void doSaveAs() {
		// saveAs is not allowed!
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {

		setSite(site);
		setInputWithNotify(input);
	}

	@Override
	public boolean isDirty() {
		for(ProjectEditorExtension pee : editorProvider.getToolExtensions()){
			if(pee.isDirty()){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		
		tabFolder = new CTabFolder(parent, SWT.BORDER | SWT.FLAT);
		tabFolder.setTabHeight(40);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		for(ProjectEditorExtension tee : editorProvider.getToolExtensions()){
			tee.createTabItem(this);
		}
		
		if(!editorProvider.getToolExtensions().isEmpty()){
			tabFolder.setSelection(editorProvider.getToolExtensions().get(0).getTabItem());
		}
		
		getSite().setSelectionProvider(ProjectEditorSelectionService.getInstance());
		ValidationDiagramService.showDiagram(ExplorerProjectPaths.getProject(this));
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		ValidationDiagramService.showDiagram(ExplorerProjectPaths.getProject(this));
	}
}
