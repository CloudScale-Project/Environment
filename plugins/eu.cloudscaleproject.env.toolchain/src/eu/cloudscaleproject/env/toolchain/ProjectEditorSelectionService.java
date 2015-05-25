package eu.cloudscaleproject.env.toolchain;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.viewers.IPostSelectionProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.PropertySheet;

/**
 * IPostSelectionProvider implementation that delegates to another
 * ISelectionProvider or IPostSelectionProvider. The selection provider used for
 * delegation can be exchanged dynamically. Registered listeners are adjusted
 * accordingly. This utility class may be used in workbench parts with multiple
 * viewers.
 * 
 * @author Marc R. Hoffmann
 */
public class ProjectEditorSelectionService implements IPostSelectionProvider
{

	private final ListenerList selectionListeners = new ListenerList();
	private final ListenerList postSelectionListeners = new ListenerList();
	private ISelectionProvider delegate;

	private static ProjectEditorSelectionService instance = null;

	public static ProjectEditorSelectionService getInstance()
	{
		if (instance == null)
		{
			instance = new ProjectEditorSelectionService();
		}
		return instance;
	}

	private ISelectionChangedListener selectionListener = new ISelectionChangedListener()
	{
		public void selectionChanged(SelectionChangedEvent event)
		{
			if (event.getSelectionProvider() == delegate)
			{
				fireSelectionChanged(event.getSelection());
			}
		}
	};

	private ISelectionChangedListener postSelectionListener = new ISelectionChangedListener()
	{
		public void selectionChanged(SelectionChangedEvent event)
		{
			if (event.getSelectionProvider() == delegate)
			{
				firePostSelectionChanged(event.getSelection());
			}
		}
	};

	/**
	 * Sets a new selection provider to delegate to. Selection listeners
	 * registered with the previous delegate are removed before.
	 * 
	 * @param newDelegate
	 *            new selection provider
	 */
	public void setSelectionProviderDelegate(ISelectionProvider newDelegate)
	{
		if (delegate == newDelegate)
		{
			fireSelectionChanged(newDelegate.getSelection());
			firePostSelectionChanged(newDelegate.getSelection());
			return;
		}
		if (delegate != null)
		{
			delegate.removeSelectionChangedListener(selectionListener);
			if (delegate instanceof IPostSelectionProvider)
			{
				((IPostSelectionProvider) delegate).removePostSelectionChangedListener(postSelectionListener);
			}
		}
		delegate = newDelegate;
		if (newDelegate != null)
		{
			newDelegate.addSelectionChangedListener(selectionListener);
			if (newDelegate instanceof IPostSelectionProvider)
			{
				((IPostSelectionProvider) newDelegate).addPostSelectionChangedListener(postSelectionListener);
			}
			fireSelectionChanged(newDelegate.getSelection());
			firePostSelectionChanged(newDelegate.getSelection());
		}
	}

	protected void fireSelectionChanged(ISelection selection)
	{
		fireSelectionChanged(selectionListeners, selection);
	}

	protected void firePostSelectionChanged(ISelection selection)
	{
		fireSelectionChanged(postSelectionListeners, selection);
	}

	private void fireSelectionChanged(ListenerList list, ISelection selection)
	{
		SelectionChangedEvent event = new SelectionChangedEvent(delegate, selection);
		Object[] listeners = list.getListeners();
		for (int i = 0; i < listeners.length; i++)
		{
			try
			{
				ISelectionChangedListener listener = (ISelectionChangedListener) listeners[i];
				listener.selectionChanged(event);
			}
			catch (Exception e)
			{
				Logger.getLogger(ProjectEditorSelectionService.class.getName()).log(Level.WARNING, "Exception fireing selection changed: "+e.getMessage());
			}

		}
	}

	// IPostSelectionProvider Implementation

	public void addSelectionChangedListener(ISelectionChangedListener listener)
	{
		selectionListeners.add(listener);
	}

	public void removeSelectionChangedListener(ISelectionChangedListener listener)
	{
		selectionListeners.remove(listener);
	}

	public void addPostSelectionChangedListener(ISelectionChangedListener listener)
	{
		postSelectionListeners.add(listener);
	}

	public void removePostSelectionChangedListener(ISelectionChangedListener listener)
	{
		postSelectionListeners.remove(listener);
	}

	public ISelection getSelection()
	{
		return delegate == null ? null : delegate.getSelection();
	}

	public void setSelection(ISelection selection)
	{
		if (delegate != null)
		{
			delegate.setSelection(selection);
		}
	}

	public void reloadPropertySheetPage()
	{
		IWorkbenchPart activePart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
		if (activePart == null) return;

		@SuppressWarnings("deprecation")
		IViewPart[] views = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViews();
		for (IViewPart v : views)
		{
			if (v instanceof PropertySheet)
			{
				PropertySheet ps = (PropertySheet) v;
				ps.partClosed(activePart);
				ps.partOpened(activePart);
				ps.partActivated(activePart);
			}
		}

        
	}

}