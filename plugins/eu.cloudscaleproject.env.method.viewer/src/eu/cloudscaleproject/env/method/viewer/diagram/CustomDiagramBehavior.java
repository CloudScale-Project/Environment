package eu.cloudscaleproject.env.method.viewer.diagram;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.editor.IDiagramContainerUI;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProviderInternal;
import org.eclipse.graphiti.ui.internal.contextbuttons.ContextButtonManagerForPad;
import org.eclipse.graphiti.ui.internal.fixed.FixedScalableFreeformRootEditPart;
import org.eclipse.graphiti.ui.internal.util.draw2d.GridLayer;

public class CustomDiagramBehavior extends DiagramBehavior{
	
	public CustomDiagramBehavior(IDiagramContainerUI container){
		super(container);
	}
	
	protected void configureGraphicalViewer() {

		ScrollingGraphicalViewer viewer = (ScrollingGraphicalViewer) getDiagramContainer().getGraphicalViewer();

		FixedScalableFreeformRootEditPart rootEditPart = new FixedScalableFreeformRootEditPart() {

			protected GridLayer createGridLayer() {
				return new org.eclipse.graphiti.ui.internal.util.draw2d.GridLayer(
						(IConfigurationProviderInternal) getConfigurationProvider());
			}

		};

		// configure ZoomManager
		viewer.setRootEditPart(rootEditPart); // support

		// animation of the zoom
		ZoomManager zoomManager = rootEditPart.getZoomManager();
		List<String> zoomLevels = new ArrayList<String>(3);
		zoomLevels.add(ZoomManager.FIT_ALL);
		zoomLevels.add(ZoomManager.FIT_WIDTH);
		zoomLevels.add(ZoomManager.FIT_HEIGHT);
		zoomManager.setZoomLevelContributions(zoomLevels);
		IToolBehaviorProvider toolBehaviorProvider = getConfigurationProvider().getDiagramTypeProvider()
				.getCurrentToolBehaviorProvider();
		zoomManager.setZoomLevels(toolBehaviorProvider.getZoomLevels());

		this.initActionRegistry(zoomManager);

		// set the keyhandler.
		viewer.setKeyHandler((new GraphicalViewerKeyHandler(viewer)).setParent(getCommonKeyHandler()));

		// settings for grid and guides
		Diagram diagram = getConfigurationProvider().getDiagram();

		boolean snapToGrid = diagram.isSnapToGrid();
		int horizontalGridUnit = diagram.getGridUnit();
		int verticalGridUnit = diagram.getVerticalGridUnit();
		if (verticalGridUnit == -1) {
			// No vertical grid unit set (or old diagram before 0.8): use
			// vertical grid unit
			verticalGridUnit = horizontalGridUnit;
		}
		boolean gridVisisble = (horizontalGridUnit > 0) && (verticalGridUnit > 0);

		viewer.setProperty(SnapToGrid.PROPERTY_GRID_VISIBLE, new Boolean(gridVisisble));
		viewer.setProperty(SnapToGrid.PROPERTY_GRID_ENABLED, new Boolean(snapToGrid));
		viewer.setProperty(SnapToGrid.PROPERTY_GRID_SPACING, new Dimension(horizontalGridUnit, verticalGridUnit));
		viewer.setProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED, toolBehaviorProvider.isShowGuides());

		// context button manager
		IConfigurationProviderInternal configurationProvider = (IConfigurationProviderInternal) this
				.getConfigurationProvider();
		configurationProvider.setContextButtonManager(new ContextButtonManagerForPad(this, configurationProvider
				.getResourceRegistry()));

	}
	
}
