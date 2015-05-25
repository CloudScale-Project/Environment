package eu.cloudscaleproject.env.common.ui.list;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ExpandableComposite;

public class CompositeContainer extends ScrolledComposite{
	
	private final Composite composite;
	private List<CompositeContainerChild> childs = new ArrayList<CompositeContainerChild>(); 

	public CompositeContainer(Composite parent, int style) {
		super(parent, SWT.V_SCROLL);
		
		composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(1, true));
		
		this.setContent(composite);
		this.setExpandHorizontal(true);
	}
	
	public Composite getCompositeArea(){
		return composite;
	}
	
	public void showChild(Object childSource){
		for(CompositeContainerChild child : getChilds()){
			if(childSource.equals(child.getSource())){
				Composite childComposite = child.getComposite();
				if(childComposite instanceof ExpandableComposite){
					ExpandableComposite ec = (ExpandableComposite)childComposite;
					ec.setExpanded(true);
					
					composite.layout();
					composite.redraw();
					composite.pack();
				}
				showControl(child.getComposite());
			}
		}
	}
	
	public void addChild(int index, CompositeContainerChild child){
		Composite c = child.createComposite(this);
		c.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		Composite upper = null;
		if(childs.size() > index){
			upper = childs.get(index).getComposite();
		}
		c.moveBelow(upper);
		childs.add(index, child);
		
		notifyListeners(SWT.CHANGED, null);
		
		composite.layout();
		composite.redraw();
		composite.pack();
	}
	
	public void removeChild(int index, CompositeContainerChild child){
		
		Composite c;
		
		if(index >= 0 && index < childs.size()){
			child = childs.get(index);
			c = childs.get(index).getComposite();
		}
		else{
			c = child.getComposite();
		}
		
		childs.remove(child);
		child.disposeComposite();
		
		if(c != null && !c.isDisposed()){
			c.dispose();
		}
		
		notifyListeners(SWT.CHANGED, null);
		
		composite.layout();
		composite.redraw();
		composite.pack();
	}
	
	public List<CompositeContainerChild> getChilds(){
		return new ArrayList<CompositeContainerChild>(childs);
	}

}
