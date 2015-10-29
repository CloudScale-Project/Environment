package eu.cloudscaleproject.env.spotter.editors.composite;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorActionBarContributor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IKeyBindingService;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.services.IServiceLocator;

@SuppressWarnings("deprecation")
public class DummyEditorSite implements IEditorSite{

	@Override
	public String getId() {
		return "DummySite.ID";
	}

	@Override
	public String getPluginId() {
		return "DummySite.PluginID";
	}

	@Override
	public String getRegisteredName() {
		return "DummySite";
	}

	@Override
	public void registerContextMenu(String menuId, MenuManager menuManager, ISelectionProvider selectionProvider) {
		// Nothing to do 
	}

	@Override
	public void registerContextMenu(MenuManager menuManager, ISelectionProvider selectionProvider) {
		// Nothing to do 
		
	}

	@Override
	public IKeyBindingService getKeyBindingService() {
		return null;
	}

	@Override
	public IWorkbenchPart getPart() {
		return null;
	}

	@Override
	public IWorkbenchPage getPage() {
		return null;
	}

	@Override
	public ISelectionProvider getSelectionProvider() {
		return null;
	}

	@Override
	public Shell getShell() {
		return Display.getDefault().getShells()[0];
	}

	@Override
	public IWorkbenchWindow getWorkbenchWindow() {
		return null;
	}

	@Override
	public void setSelectionProvider(ISelectionProvider provider) {
	}

	@Override
	public <T> T getAdapter(Class<T> adapter) {
		return null;
	}

	@Override
	public <T> T getService(Class<T> api) {
		return null;
	}

	@Override
	public boolean hasService(Class<?> api) {
		return false;
	}

	@Override
	public IEditorActionBarContributor getActionBarContributor() {
		return new IEditorActionBarContributor() {
			
			@Override
			public void setActiveEditor(IEditorPart targetEditor) {
			}
			
			@Override
			public void init(IActionBars bars, IWorkbenchPage page) {
			}
			
			@Override
			public void dispose() {
			}
		};
	}

	@Override
	public IActionBars getActionBars() {
		
		return new IActionBars() {
			
			@Override
			public void updateActionBars() {
				
			}
			
			@Override
			public void setGlobalActionHandler(String actionId, IAction handler) {
				
			}
			
			@Override
			public IToolBarManager getToolBarManager() {
				return null;
			}
			
			@Override
			public IStatusLineManager getStatusLineManager() {
				return null;
			}
			
			@Override
			public IServiceLocator getServiceLocator() {
				return null;
			}
			
			@Override
			public IMenuManager getMenuManager() {
				return null;
			}
			
			@Override
			public IAction getGlobalActionHandler(String actionId) {
				return null;
			}
			
			@Override
			public void clearGlobalActionHandlers() {
				
			}
		};
	}

	@Override
	public void registerContextMenu(MenuManager menuManager, ISelectionProvider selectionProvider,
			boolean includeEditorInput) {
		
	}

	@Override
	public void registerContextMenu(String menuId, MenuManager menuManager, ISelectionProvider selectionProvider,
			boolean includeEditorInput) {
		
	}

}
