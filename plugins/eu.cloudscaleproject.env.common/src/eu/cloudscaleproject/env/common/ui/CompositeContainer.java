package eu.cloudscaleproject.env.common.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;

public class CompositeContainer extends Composite{
	
	private List<CompositeContainerChild> childs = new ArrayList<CompositeContainerChild>(); 

	public CompositeContainer(Composite parent, int style) {
		super(parent, style);
	}
	
	public void addChild(int index, CompositeContainerChild child){
		Composite c = child.createComposite(this);
		
		Composite upper = null;
		if(childs.size() > index){
			upper = childs.get(index).getComposite();
		}
		c.moveBelow(upper);
		childs.add(index, child);
	}
	
	public void removeChild(int index, CompositeContainerChild child){
		Composite c = childs.get(index).getComposite();
		if(c != null){
			c.dispose();
		}
		childs.remove(child);
	}
	
	public List<CompositeContainerChild> getChilds(){
		return new ArrayList<CompositeContainerChild>(childs);
	}

}
