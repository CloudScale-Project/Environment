package eu.cloudscaleproject.env.method;


import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class MethodEditorPart extends EditorPart {

	private static final String PLUGIN_ID = "eu.cloudscaleproject.env.method";
	private static final String IMAGE_METHOD = "icons/method.png";
	public static final String ID = "eu.cloudscaleproject.env.method.MethodEditorPart"; //$NON-NLS-1$

	public MethodEditorPart() {
		setPartName("Method Overview");
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final SWTImageCanvas c = new SWTImageCanvas(parent);
		
		Image img = AbstractUIPlugin
				.imageDescriptorFromPlugin(PLUGIN_ID, IMAGE_METHOD)
				.createImage(); 

		c.loadImage(img);
		
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				c.fitCanvas();
			}
		});
	}
	
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		
		setSite(site);
		setInput(input);
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

}
