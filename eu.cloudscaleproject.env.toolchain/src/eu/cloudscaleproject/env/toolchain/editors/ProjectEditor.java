package eu.cloudscaleproject.env.toolchain.editors;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.toolchain.Activator;
import eu.cloudscaleproject.env.toolchain.ProjectEditorExtension;

public class ProjectEditor extends EditorPart {

	private static final Logger logger = Logger.getLogger(ProjectEditor.class.getName());	
	public static final String ID = "eu.cloudscaleproject.env.toolchain.ToolchainEditorPart";
	
	private CTabFolder tabFolder;
	
	@Inject
	private ProjectEditorProvider editorProvider;
		
	public ProjectEditor() {
		IEclipseContext context = EclipseContextFactory.getServiceContext(Activator.plugin.getBundle().getBundleContext());
		ContextInjectionFactory.inject(this, context);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		if (adapter.equals(IPropertySheetPage.class)) {
			for(ProjectEditorExtension pee : editorProvider.getToolExtensions()){
				if(tabFolder.getSelection() != null && tabFolder.getSelection().equals(pee.getTabItem())){
					if(pee.getPropertySheetPage() != null){
						return pee.getPropertySheetPage();
					}
				}
			}
			return super.getAdapter(adapter);
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
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {

		setSite(site);
		setInputWithNotify(input);
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
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
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}
}
