package eu.cloudscaleproject.env.method.editor.diagram.features;

import java.util.LinkedList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;

import eu.cloudscaleproject.env.method.common.method.Container;
import eu.cloudscaleproject.env.method.common.method.LinkedNode;
import eu.cloudscaleproject.env.method.common.method.Node;
import eu.cloudscaleproject.env.method.common.method.SectionConnector;

public class DeleteFeature extends DefaultDeleteFeature{

	public DeleteFeature(IFeatureProvider fp) {
		super(fp);
	}
	
	@Override
	public void preDelete(IDeleteContext context) {
		
		PictogramLink link = context.getPictogramElement().getLink();
		
		if(link == null){
			return;
		}
		
		for(Object o : new LinkedList<Object>(link.getBusinessObjects())){
			
			if(o instanceof LinkedNode){
				disposeLinkedNode((LinkedNode)o);;
			}
			else if(o instanceof Container){
				Container c = (Container)o;
				for(Node node : c.getChildren()){
					if(node instanceof LinkedNode){
						disposeLinkedNode((LinkedNode)node);
					}
				}
			}
			
			if(o instanceof SectionConnector){
				SectionConnector sc = (SectionConnector)o;
				
				LinkedNode lnStart = sc.getStart();
				if(lnStart != null){
					lnStart.getNext().remove(sc);
				}
				
				LinkedNode lnEnd = sc.getEnd();
				if(lnEnd != null){
					lnStart.getPrevious().remove(sc);
				}
			}
			
			if(o instanceof EObject){
				EcoreUtil.delete((EObject)o);
			}
		}
	}
	
	private void disposeLinkedNode(LinkedNode linkedNode){
		for(SectionConnector sc : new LinkedList<SectionConnector>(linkedNode.getPrevious())){
			EcoreUtil.delete(sc);
		}
		for(SectionConnector sc : new LinkedList<SectionConnector>(linkedNode.getNext())){
			EcoreUtil.delete(sc);
		}
	}
}
