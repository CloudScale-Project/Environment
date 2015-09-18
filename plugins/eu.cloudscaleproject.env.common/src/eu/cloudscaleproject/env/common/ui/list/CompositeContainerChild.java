package eu.cloudscaleproject.env.common.ui.list;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;

public abstract class CompositeContainerChild {
	
	private Object data;
	
	private ExpandableComposite expComposite;
	private Composite composite = null;
	private Composite clientComposite = null;
	
	protected abstract void doRefreshComposite(ExpandableComposite parent, Composite client, Object source);
	protected abstract Composite doCreateComposite(ExpandableComposite parent, Object source);
	
	public Composite createComposite(final CompositeContainer container){
		if(composite != null){
			composite.dispose();
			composite = null;
		}
		
		//
		expComposite = new ExpandableComposite(container.getCompositeArea(), SWT.BORDER,
				ExpandableComposite.CLIENT_INDENT
				| ExpandableComposite.COMPACT
				| ExpandableComposite.TITLE_BAR
				| ExpandableComposite.TWISTIE);
		
		expComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));		
		clientComposite = doCreateComposite(expComposite, data);
		
		Button btnDelete = new Button(expComposite, SWT.NONE);
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				container.removeChild(-1, CompositeContainerChild.this);
				container.postDeleteChild(getSource());
			}
		});
		btnDelete.setText("Delete");
		
		expComposite.setClient(clientComposite);
		expComposite.setTextClient(btnDelete);
		
		expComposite.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				if(container.getCompositeArea() != null 
						&& !container.getCompositeArea().isDisposed()){
					container.getCompositeArea().layout();
					container.getCompositeArea().redraw();
					container.getCompositeArea().pack();
				}
			}
		});
		composite = expComposite;
		return composite;
	}
	
	public void refreshComposite(){
		doRefreshComposite(expComposite, getClient(), getSource());
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
	
	public Composite getClient(){
		return clientComposite;
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
