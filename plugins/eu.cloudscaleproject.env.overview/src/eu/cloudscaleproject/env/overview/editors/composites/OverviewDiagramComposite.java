package eu.cloudscaleproject.env.overview.editors.composites;

import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.graphiti.ui.editor.DiagramComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public class OverviewDiagramComposite extends DiagramComposite{
	
	private PaletteViewer paletteViewer;
	private PaletteViewerProvider paletteProvider;
	
	public OverviewDiagramComposite(Composite parent, int style) {
		super(parent, style);		
	}

	public final PaletteViewerProvider getPaletteViewerProvider() {
		if (paletteProvider == null)
			paletteProvider = createPaletteViewerProvider();
		return paletteProvider;
	}
	
	public void initializePalette(Composite composite){
		
		composite.setLayout(new FillLayout());
		
		PaletteViewerProvider paletteViewerProvider = getPaletteViewerProvider();
		paletteViewer = paletteViewerProvider.createPaletteViewer(composite);
		paletteViewer.setPaletteRoot(getPaletteRoot());
	}
}
