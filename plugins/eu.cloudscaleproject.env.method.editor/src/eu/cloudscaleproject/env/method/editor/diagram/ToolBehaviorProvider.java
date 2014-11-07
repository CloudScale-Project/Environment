package eu.cloudscaleproject.env.method.editor.diagram;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;

import eu.cloudscaleproject.env.method.common.method.Link;
import eu.cloudscaleproject.env.method.common.method.LinkedObject;
import eu.cloudscaleproject.env.method.common.method.Node;

public class ToolBehaviorProvider extends DefaultToolBehaviorProvider{
		
	public ToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}
	
	@Override
	public Object getToolTip(GraphicsAlgorithm ga) {
		PictogramElement pe = ga.getPictogramElement();
		Object bo = getFeatureProvider().getBusinessObjectForPictogramElement(pe);
		if (bo instanceof Node) {
			Node node = (Node)bo;
			
			StringBuilder sb = new StringBuilder();
			sb.append("Node: ").append(node.getName()).append("\n");
			if(!node.getTooltip().isEmpty()){
				sb.append(node.getTooltip()).append("\n");
				sb.append("\n");
			}
			sb.append("Description: ").append(node.getDescription()).append("\n");

			{

				boolean hasRequired = false;
				if(node instanceof LinkedObject){
					StringBuilder sb1 = new StringBuilder();
					sb1.append("Required: ");
					
					LinkedObject ln = (LinkedObject)node;
					for (Link sc : ln.getPrevious()) {
						if (sc.isRequired() && sc.getStart() instanceof Node) {
							sb1.append(((Node)sc.getStart()).getName());
							sb1.append(", ");
							hasRequired = true;
						}
					}
					if (hasRequired) {
						sb1.deleteCharAt(sb1.lastIndexOf(", "));
						sb1.append("\n");
						sb.append(sb1.toString());
					}
				}
			}
		
			return sb.toString();
		}
		return super.getToolTip(ga);
	}
}
