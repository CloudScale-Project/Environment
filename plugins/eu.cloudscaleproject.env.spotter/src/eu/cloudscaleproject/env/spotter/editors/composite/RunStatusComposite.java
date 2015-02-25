package eu.cloudscaleproject.env.spotter.editors.composite;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.spotter.eclipse.ui.view.ActiveRunView;

public class RunStatusComposite extends Composite{

	private final ActiveRunView activeRunView;
	
	public RunStatusComposite(IProject project, Composite parent, int style) {
		super(parent, style);
	
		this.activeRunView = new ActiveRunViewExtended();
		this.activeRunView.createPartControl(this);
		//this.activeRunView.updateContent(project);
		
		this.layout();
		
		addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent e) {
				activeRunView.dispose();
			}
		});
	}
	
	private class ActiveRunViewExtended extends ActiveRunView{
		//@Override
		//protected void updateView() {
			//this.updateContent(project);
		//}
	}
	
}
