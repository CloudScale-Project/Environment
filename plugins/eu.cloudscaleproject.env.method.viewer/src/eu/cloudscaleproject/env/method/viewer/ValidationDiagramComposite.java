package eu.cloudscaleproject.env.method.viewer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.graphiti.ui.editor.DiagramComposite;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.BatchExecutor;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ValidationDiagramComposite extends DiagramComposite{
	
	private final ValidationDiagram diagram;
	
	private Thread zoomThread = null;

	private final PropertyChangeListener diagramListener = new PropertyChangeListener(){
		
		public void propertyChange(PropertyChangeEvent evt) {
			if(ValidationDiagram.PROP_STATUS_CHANGED.equals(evt.getPropertyName())){
				
				BatchExecutor.getInstance().addTask(this, "refresh", new Runnable() {
					@Override
					public void run() {
						Display.getDefault().asyncExec(new Runnable() {
							
							@Override
							public void run() {
								if(getDiagramTypeProvider() != null 
										&& getDiagramTypeProvider().getDiagramBehavior() != null){
									getDiagramTypeProvider().getDiagramBehavior().refreshContent();
								}
							}
						});
					}
				});
				
			}
		};
		
	};
	
	public ValidationDiagramComposite(ValidationDiagram diagram, Composite parent, int style) {
		super(parent, style);	
		
		this.diagram = diagram;
		this.diagram.addPropertyChangeListener(diagramListener);
		
		addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent e) {
				
				if(zoomThread != null){
					zoomThread.interrupt();
				}
				
				ValidationDiagramComposite.this.diagram.removePropertyChangeListener(diagramListener);
				ValidationDiagramComposite.this.diagram.dispose();
			}
		});
		
	}
	
	public ValidationDiagram getValidationDiagram(){
		return this.diagram;
	}
	
	//ZOOM Workaround!
	
	private final Object lock = new Object();
	private boolean resize = false;
	
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
		
		zoomThread = new Thread(new Runnable() {
			
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
						
						Thread.sleep(300);
						
						Display.getDefault().asyncExec(new Runnable() {
							public void run() {
								if(!ValidationDiagramComposite.this.isDisposed()){
									zoomManager.setZoomAnimationStyle(ZoomManager.ANIMATE_NEVER);
									zoomManager.setZoomAsText(ZoomManager.FIT_ALL);
								}
							}
						});
						
						Thread.sleep(1000);
						
					} 
					catch (InterruptedException e) {
						return;
					}
					
				}
			}
		}, "Validation diagram fit to view");
		zoomThread.start();
	}

}
