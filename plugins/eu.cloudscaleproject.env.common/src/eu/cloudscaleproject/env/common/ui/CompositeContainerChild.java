package eu.cloudscaleproject.env.common.ui;

import org.eclipse.swt.widgets.Composite;

public abstract class CompositeContainerChild {
	
	private Object data;
	private Composite composite = null;

	protected abstract Composite doCreateComposite(Composite parent, Object source);
	
	protected CompositeContainerChild(){};
	
	public Composite createComposite(Composite parent){
		if(composite != null){
			composite.dispose();
			composite = null;
		}
		composite = doCreateComposite(parent, data);
		return composite;
	}
	
	public void disposeComposite(){
		if(composite != null){
			composite.dispose();
			composite = null;
		}
	}
	
	public Composite getComposite(){
		return composite;
	}
	
	public Object getSource(){
		return data;
	}
	
	public void setSource(Object source){
		this.data = source;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompositeContainerChild other = (CompositeContainerChild) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}

	
}
