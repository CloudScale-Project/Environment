package org.scaledl.overview.properties;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.platform.IDiagramBehavior;
import org.eclipse.graphiti.tb.ColorDecorator;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.scaledl.overview.application.OperationInterface;
import org.scaledl.overview.architecture.ComputingInfrastructureService;
import org.scaledl.overview.diagram.decorations.DecorationManager;
import org.scaledl.overview.diagram.decorations.DecorationProvider;
import org.scaledl.overview.diagram.util.OverviewDiagramUtil;
import org.scaledl.overview.properties.components.tree.TreeRow;

public class HighlightDecorationProvider implements DecorationProvider{
	
	private Diagram diagram;
	private IDiagramBehavior diagramEditor;
	
	private static final IColorConstant color = new ColorConstant(255, 140, 140);
	private static final IColorConstant colorSec = new ColorConstant(140, 255, 140);
	
	private final TreeViewer treeViewer;
	private final HashSet<PictogramElement> updateElements = new HashSet<PictogramElement>();

	private static final HashMap<PictogramElement, IColorConstant> highlightedElements = new HashMap<PictogramElement, IColorConstant>();
	
	private final ISelectionChangedListener selectionListener;
	private final PaintListener paintListener;
	
	public HighlightDecorationProvider(TreeViewer tw) {
		this.treeViewer = tw;
		
		this.selectionListener = new ISelectionChangedListener() {
			@Override
			public void selectionChanged(final SelectionChangedEvent event) {
				update(event.getSelection());
			}
    	};
		this.treeViewer.addSelectionChangedListener(this.selectionListener);
		
		this.paintListener = new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				update(treeViewer.getSelection());
			}
		};
		this.treeViewer.getTree().addPaintListener(this.paintListener);
		
		DecorationManager.addProvider(this);
	}
	
	public void update(ISelection s){
		IStructuredSelection selection = (IStructuredSelection)s;
		Object element = selection.getFirstElement();
		if(element == null){
			clear();
			return;
		}

		updateElements.addAll(highlightedElements.keySet());
		highlightedElements.clear();
		
		if(diagram != null){
			colleactHighlitedElements((TreeRow)element);
			updateElements.addAll(highlightedElements.keySet());
		}
		
		if(diagramEditor != null){
			for(PictogramElement pe : updateElements){
				diagramEditor.refreshRenderingDecorators(pe);
			}
		}

		updateElements.clear();
	}
	private void colleactHighlitedElements(TreeRow row){
		collectSecHighlitedElements(row);
		collectBaseHighlitedElements(row);
	}
	
	private void collectSecHighlitedElements(TreeRow row){
		if(row == null){
			return;
		}
		
		Object o = row.getCell(1).getData();

		if(o == null){
			return;
		}
		
		if(!highlightedElements.containsValue(colorSec)){
			if(o instanceof List<?>){
				List<?> list = (List<?>)o;
				if(!list.isEmpty() && list.get(0) instanceof OperationInterface){
					for(Object item : list){
						OperationInterface op = (OperationInterface)item;
						addPictograms(OverviewDiagramUtil.getPictogramElementsFromBusinessObjects(diagram, op.getProvidingContainer()), colorSec);
						addPictograms(OverviewDiagramUtil.getPictogramElementsFromBusinessObjects(diagram, op.getRequiringContainer()), colorSec);
					}
				}
			}
			else if(o instanceof OperationInterface){
				OperationInterface oi = (OperationInterface)o;
				addPictograms(OverviewDiagramUtil.getPictogramElementsFromBusinessObjects(diagram, oi.getProvidingContainer()), colorSec);
				addPictograms(OverviewDiagramUtil.getPictogramElementsFromBusinessObjects(diagram, oi.getRequiringContainer()), colorSec);
			}
			
			if(!highlightedElements.containsValue(colorSec) && row.getParent() != null){
				collectSecHighlitedElements(row.getParent());
			}
		}
	}
	
	private void collectBaseHighlitedElements(TreeRow row){
		
		if(row == null){
			return;
		}
		
		Object o = row.getCell(1).getData();

		if(o == null){
			return;
		}
		
		addPictograms(OverviewDiagramUtil.getPictogramElementsFromBusinessObjects(diagram, o), color);
		
		if(!highlightedElements.containsValue(color)){
			if(o instanceof ComputingInfrastructureService){
				ComputingInfrastructureService cis = (ComputingInfrastructureService)o;
				addPictograms(OverviewDiagramUtil.getPictogramElementsFromBusinessObjectsList(diagram, cis.getPlatformServices()), color);
			}
			
			if(!highlightedElements.containsValue(color) && row.getParent() != null){
				collectBaseHighlitedElements(row.getParent());
			}
		}
	}
	
	private void addPictograms(Collection<PictogramElement> pictograms, IColorConstant color){
		for(PictogramElement el : pictograms){
			highlightedElements.put(el, color);
		}
	}
	
	public void clear(){
		if(this.diagramEditor != null){
			updateElements.addAll(highlightedElements.keySet());
			highlightedElements.clear();
			for(PictogramElement pe : updateElements){
				diagramEditor.refreshRenderingDecorators(pe);
			}
			updateElements.clear();
		}
	}
	
	public void setDiagram(Diagram diagram, IDiagramBehavior editor){
		this.diagram = diagram;
		this.diagramEditor = editor;
	}

	@Override
	public Set<IDecorator> getDecorators(PictogramElement el) {
		Set<IDecorator> out = new HashSet<IDecorator>();
		
		if(highlightedElements.containsKey(el)){
			
			IColorConstant c = highlightedElements.get(el);
			float or = (float)c.getRed();
			float og = (float)c.getGreen();
			float ob = (float)c.getBlue();
			
			Color bg = el.getGraphicsAlgorithm().getBackground();

			float r = bg != null ? (float)bg.getRed() : or;
			float g = bg != null ? (float)bg.getGreen() : og;
			float b = bg != null ? (float)bg.getBlue() : ob;
			
			float a = 0.6f;
			
			r = Math.min(or*a+(1.0f-a)*r, 255.0f);
			g = Math.min(og*a+(1.0f-a)*g, 255.0f);
			b = Math.min(ob*a+(1.0f-a)*b, 255.0f);
			
			ColorConstant color = new ColorConstant((int)r, (int)g, (int)b);
			out.add(new ColorDecorator(null, color));
		}
		return out;
	}
	
	public void dispose(){
		diagram = null;
		clear();
		
		DecorationManager.removeProvider(this);
	}

}
