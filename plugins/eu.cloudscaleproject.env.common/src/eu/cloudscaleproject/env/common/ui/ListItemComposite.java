package eu.cloudscaleproject.env.common.ui;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateListStrategy;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.common.Converters;
import eu.cloudscaleproject.env.common.ui.util.CompositeContainerChildProperty;

public abstract class ListItemComposite extends CompositeContainer{
	
	private DataBindingContext bindingContext = null;
		
	public ListItemComposite(Composite parent, int style) {		
		super(parent, style);
		setLayout(new GridLayout(1, true));		
	}
	
	public void initBindings(IObservableList modelObservable, Class<? extends CompositeContainerChild> childClass){
		
		if(bindingContext != null){
			bindingContext.dispose();
		}
		bindingContext = new DataBindingContext();
		
		CompositeContainerChildProperty childrenProp = new CompositeContainerChildProperty();
		IObservableList childObs = childrenProp.observe(this);
		
		IConverter[] converter = Converters.getCompositeContainerChildConverter(childClass);
		UpdateListStrategy t2mStrategy = new UpdateListStrategy(UpdateListStrategy.POLICY_UPDATE);
		t2mStrategy.setConverter(converter[0]);
		
		UpdateListStrategy m2tStrategy = new UpdateListStrategy(UpdateListStrategy.POLICY_UPDATE);
		m2tStrategy.setConverter(converter[1]);
		
		bindingContext.bindList(childObs, modelObservable, t2mStrategy, m2tStrategy);
		
	}
}
