package eu.cloudscaleproject.env.method.viewer.diagram;

import org.eclipse.core.resources.IProject;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;

public class CustomDiagramEditor extends DiagramEditor{
		
	private final Object lock = new Object();
	private boolean resize = false;
		
	@Override
	protected DiagramBehavior createDiagramBehavior() {
		return new CustomDiagramBehavior(this);
	}
	
	@Override
	public boolean isDirty() {
		return false;		
	}
	
	@Override
	public String getPartName() {
		IProject p = ExplorerProjectPaths.getProject(this);
		return "Workflow ("+p.getName()+")";
	}
	
	public void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
				
		//set zoom
		final ZoomManager zoomManager = (ZoomManager) getAdapter(ZoomManager.class);
		zoomManager.setZoomAnimationStyle(ZoomManager.ANIMATE_NEVER);
		
		getGraphicalViewer().getControl().addControlListener(new ControlListener() {
			
			@Override
			public void controlResized(ControlEvent e) {
				synchronized (lock) {
					resize = true;
					lock.notifyAll();
				}
			}
			
			@Override
			public void controlMoved(ControlEvent e) {
			}
		});
		
		if(getGraphicalViewer().getControl() instanceof FigureCanvas){
			FigureCanvas fc = (FigureCanvas)getGraphicalViewer().getControl();
			
			fc.setVerticalScrollBarVisibility(FigureCanvas.NEVER);
			fc.setHorizontalScrollBarVisibility(FigureCanvas.NEVER);
		}
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(!Thread.currentThread().isInterrupted()){
					try {
						synchronized (lock) {
							if(!resize){
								lock.wait();
							}
							resize = false;
						}
						
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						Display.getDefault().asyncExec(new Runnable() {
							public void run() {
								zoomManager.setZoomAsText(ZoomManager.FIT_ALL);
							}
						});
						
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} catch (InterruptedException e) {
					}
					
				}
			}
		});
		t.start();
	}
}
