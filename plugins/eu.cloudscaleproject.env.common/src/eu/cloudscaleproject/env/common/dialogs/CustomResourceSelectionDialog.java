package eu.cloudscaleproject.env.common.dialogs;
import java.util.LinkedList;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.ui.model.WorkbenchLabelProvider;

public class CustomResourceSelectionDialog extends ElementTreeSelectionDialog {

    private String[] extensions;

    private static ITreeContentProvider contentProvider = new ITreeContentProvider() {
        public Object[] getChildren(Object element) {
            if (element instanceof IContainer) {
                try {
                    return ((IContainer) element).members();
                }
                catch (CoreException e) {
                }
            }
            return null;
        }

        public Object getParent(Object element) {
            return ((IResource) element).getParent();
        }

        public boolean hasChildren(Object element) {
            return element instanceof IContainer;
        }

        public Object[] getElements(Object input) {
            return (Object[]) input;
        }

        public void dispose() {
        }

        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        }
    };

    private static final String PLUGIN_ID = "eu.cloudscaleproject.env.extractor";
    private static final IStatus OK = new Status(IStatus.OK, PLUGIN_ID, 0, "", null);
    private static final IStatus ERROR = new Status(IStatus.ERROR, PLUGIN_ID, 0, "", null);

    /*
     * Validator
     */
    private ISelectionStatusValidator validator = new ISelectionStatusValidator() {
        public IStatus validate(Object[] selection) {
        	
        	if (selection.length == 1)
        	{
        		if (resourceClass.isAssignableFrom(selection[0].getClass()))
        		{
        			if (selection[0] instanceof IFile)
        			{
        				return checkExtension(((IFile) selection[0]).getFileExtension()) ? OK : ERROR;
        			}
        			else
        			{
        				return OK;
        			}
        		}
        		
        	}
        	
        	return ERROR;
        }
    };

	private Class<?> resourceClass;

	private IContainer container;

    public CustomResourceSelectionDialog(String title, String message, Class<?> resourceClass) {
    	this(title, message, resourceClass, null);
    }
    public CustomResourceSelectionDialog(String title, String message, Class<?> resourceClass, String[] extensions) {
    	this(title, message, resourceClass, extensions, null);
    }

    public CustomResourceSelectionDialog(String title, String message, Class<?> resourceClass, String[] extensions, IContainer root) {
        this(Display.getDefault().getActiveShell(), WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider(),
                contentProvider);

        this.extensions = extensions;
        this.resourceClass = resourceClass == null ? IFile.class : resourceClass;
        this.container = root == null ? ResourcesPlugin.getWorkspace().getRoot() : root;

        setTitle(title);
        setMessage(message);

        setInput(computeInput(this.container));
        setValidator(validator);
    }

    public CustomResourceSelectionDialog(Shell parent, ILabelProvider labelProvider, ITreeContentProvider contentProvider) {
        super(parent, labelProvider, contentProvider);
    }

    /*
     * Show projects
     */
    private Object[] computeInput(IContainer root) {
        /*
         * Refresh projects tree.
         */
        try {
			root.refreshLocal(IResource.DEPTH_ONE, null);
		} catch (CoreException e1) {
			e1.printStackTrace();
		}
        
        LinkedList<IResource> resources = new LinkedList<IResource>();
        		
        try {
			for (IResource r : root.members())
			{
				if (r instanceof IContainer)
				{
					IContainer c = (IContainer) r;
					c.refreshLocal(IResource.DEPTH_INFINITE, null);
				}
				
				if (r instanceof IProject)
				{
					if (((IProject)r).isOpen()) resources.add(r);
				}
				else
				{
					resources.add(r);
				}
			}
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        return resources.toArray();
    }

    /*
     * Check file extension
     */
    private boolean checkExtension(String name) {
        if (name.equals("*")) {
            return true;
        }

        for (int i = 0; i < extensions.length; i++) {
            if (extensions[i].equals(name)) {
                return true;
            }
        }
        return false;
    }
}