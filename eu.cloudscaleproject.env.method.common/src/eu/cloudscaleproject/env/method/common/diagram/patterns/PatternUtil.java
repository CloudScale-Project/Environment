package eu.cloudscaleproject.env.method.common.diagram.patterns;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

public class PatternUtil {

	public static final void addShapeID(PictogramElement pe, String id) {
		Graphiti.getPeService().setPropertyValue(pe, id, id);
	}

	public static boolean hasShapeID(PictogramElement pe, String id) {
		return (Graphiti.getPeService().getPropertyValue(pe, id) != null);
	}
	
	public static final void addShapeID(PictogramElement pe, int id) {
		addShapeID(pe, Integer.toString(id));
	}

	public static boolean hasShapeID(PictogramElement pe, int id) {
		return hasShapeID(pe, Integer.toString(id));
	}
	
	public static Shape findChild(ContainerShape cs, String id){		
		for(Shape s : cs.getChildren()){
			if(hasShapeID(s, id)){
				return s;
			}
		}
		return null;
	}
	
	public static List<Shape> findChildren(ContainerShape cs, String id){
		
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		
		for(Shape s : cs.getChildren()){
			if(hasShapeID(s, id)){
				shapes.add(s);
			}
		}
		return shapes;
	}
	
	public static Shape findChild(ContainerShape cs, int id){
		return findChild(cs, Integer.toString(id));
	}

	public static List<Shape> findChildren(ContainerShape cs, int id){
		return findChildren(cs, Integer.toString(id));
	}
	
	public static boolean comapreColorEquals(Color c1, Color c2){
		
		if(c1 == null && c2 != null){
			return false;
		}
		if(c2 == null && c1 != null){
			return false;
		}
		
		if(c1.getBlue() != c2.getBlue()){
			return false;
		}
		if(c1.getGreen() != c2.getGreen()){
			return false;
		}
		if(c1.getRed() != c2.getRed()){
			return false;
		}
		
		return true;
	}

}
