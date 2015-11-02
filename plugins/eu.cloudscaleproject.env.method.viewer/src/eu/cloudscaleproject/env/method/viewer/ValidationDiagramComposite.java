package eu.cloudscaleproject.env.method.viewer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.editor.DiagramComposite;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.common.BatchExecutor;
import eu.cloudscaleproject.env.method.viewer.diagram.CustomDiagramBehavior;

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
				
				BatchExecutor.getInstance().addUITask(this, "refresh", new Runnable() {
					@Override
					public void run() {
						
						if(getDiagramTypeProvider() != null 
								&& getDiagramTypeProvider().getDiagramBehavior() != null){
							getDiagramTypeProvider().getDiagramBehavior().refreshContent();
						}
							
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
	
	@Override
	protected DiagramBehavior createDiagramBehavior() {
		return new CustomDiagramBehavior(this);
	}
	
	public void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
				
		//set zoom
		final ZoomManager zoomManager = (ZoomManager) getAdapter(ZoomManager.class);

		getGraphicalViewer().getControl().addControlListener(new ControlListener() {
			
			@Override
			public void controlResized(ControlEvent e) {
				zoomManager.setZoomAsText(ZoomManager.FIT_ALL);
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
	}

}
