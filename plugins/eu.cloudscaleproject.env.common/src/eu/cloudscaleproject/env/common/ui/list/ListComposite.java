package eu.cloudscaleproject.env.common.ui.list;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateListStrategy;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ExpandableComposite;

import eu.cloudscaleproject.env.common.interfaces.IRefreshable;

public abstract class ListComposite extends CompositeContainer implements IRefreshable{
	
	private IObservableList currentObservable = null;
	private DataBindingContext bindingContext = null;
	
	protected abstract Composite createComposite(ExpandableComposite parent, Object source);
		
	private class Child extends CompositeContainerChild{
		
		@Override
		protected Composite doCreateComposite(ExpandableComposite parent, Object source) {
			return ListComposite.this.createComposite(parent, source);
		}
		
		@Override
		protected void doRefreshComposite(ExpandableComposite parent, Composite client, Object source) {
			ListComposite.this.refreshComposite(parent, client, source);
		}
	}
	
	public ListComposite(Composite parent, int style) {		
		super(parent, style);
	}
	
	protected void refreshComposite(ExpandableComposite parent, Composite client, Object source){
		//override
	}
	
	public void updateTarget(){
		if(bindingContext != null){
			bindingContext.updateTargets();
		}
	}
	
	public void updateModel(){
		if(bindingContext != null){
			bindingContext.updateModels();
		}
	}
	
	public void initBindings(IObservableList modelObservable){
		
		if(currentObservable == modelObservable){
			return;
		}
		
		if(bindingContext != null){
			bindingContext.dispose();
			bindingContext = null;
		}
		bindingContext = new DataBindingContext();
		
		CompositeContainerChildProperty childrenProp = new CompositeContainerChildProperty();
		IObservableList childObs = childrenProp.observe(this);
		
		IConverter[] converter = getCompositeContainerChildConverter(modelObservable.getElementType());
		UpdateListStrategy t2mStrategy = new UpdateListStrategy(UpdateListStrategy.POLICY_UPDATE);
		t2mStrategy.setConverter(converter[0]);
		
		UpdateListStrategy m2tStrategy = new UpdateListStrategy(UpdateListStrategy.POLICY_UPDATE);
		m2tStrategy.setConverter(converter[1]);
		
		bindingContext.bindList(childObs, modelObservable, t2mStrategy, m2tStrategy);
		bindingContext.updateTargets();
		currentObservable = modelObservable;
	}
	
	private IConverter[] getCompositeContainerChildConverter(final Object toType){
		
		IConverter[] converter = new IConverter[2];
		
		//t2m
		converter[0] = new IConverter() {
			
			@Override
			public Object getToType() {
				return toType;
			}
			
			@Override
			public Object getFromType() {
				return CompositeContainerChild.class;
			}
			
			@Override
			public Object convert(Object fromObject) {
				
				if(fromObject == null){
					return null;
				}
				
				CompositeContainerChild child = (CompositeContainerChild)fromObject;
				return child.getSource();
			}
		};
		
		//m2t
		converter[1] = new IConverter() {
			
			@Override
			public Object getToType() {
				return CompositeContainerChild.class;
			}
			
			@Override
			public Object getFromType() {
				return Object.class;
			}
			
			@Override
			public Object convert(Object fromObject) {
				CompositeContainerChild out = new Child();
				out.setSource(fromObject);
				return out;
			}
		};
		
		return converter;
	}
	
	public void refresh(){

		for(CompositeContainerChild child : getChilds()){
			
			child.refreshComposite();
			
			Composite clientComposite = child.getClient();
			if(clientComposite instanceof IRefreshable){
				((IRefreshable)clientComposite).refresh();
			}
		}
	}
}
