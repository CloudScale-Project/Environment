package eu.cloudscaleproject.env.analyser.editors.composite;

import java.util.List;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.NumberToStringConverter;
import org.eclipse.core.databinding.conversion.StringToNumberConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.palladiosimulator.experimentautomation.experiments.ExperimentsPackage;
import org.palladiosimulator.experimentautomation.experiments.NestedIntervalsValueProvider;
import org.palladiosimulator.experimentautomation.experiments.ValueProvider;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;

public class ConfigCapacity extends ConfigBasicComposite{
	
	private Text textMinValue;
	private Text textMaxValue;
	
	public ConfigCapacity(ConfAlternative input, Composite parent, int style) {
		super(input, parent, style);
	}
	
	@Override
	protected void initExtensions(Composite parent) {
		setLayout(new GridLayout(1, false));
		
		Label lblDescription = new Label(this, SWT.NONE);
		lblDescription.setText("Capacity measurements settings:");
		
		Composite compositeConf = new Composite(this, SWT.NONE);
		compositeConf.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		GridLayout gl_compositeConf = new GridLayout(2, false);
		gl_compositeConf.marginLeft = 10;
		compositeConf.setLayout(gl_compositeConf);
		
		Label lblMinVal = new Label(compositeConf, SWT.NONE);
		lblMinVal.setText("Minimum number of users:");
		
		textMinValue = new Text(compositeConf, SWT.BORDER);
		textMinValue.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblMaxVal = new Label(compositeConf, SWT.NONE);
		lblMaxVal.setText("Maximum number of users:");
		
		textMaxValue = new Text(compositeConf, SWT.BORDER);
		textMaxValue.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	}

	@Override
	public void load() {
		
		super.load();
		
		List<ValueProvider> nestedIntValProviders 
			= alternative.getVariationValueProviders(ExperimentsPackage.Literals.NESTED_INTERVALS_VALUE_PROVIDER);
		
		if(nestedIntValProviders.isEmpty()){
			textMaxValue.setEnabled(false);
			textMinValue.setEnabled(false);
			return;
		}
		
		NestedIntervalsValueProvider valueProvider = (NestedIntervalsValueProvider)nestedIntValProviders.get(0);
		
		//data binding
		UpdateValueStrategy t2mStrategy = new UpdateValueStrategy(UpdateValueStrategy.POLICY_UPDATE);
		t2mStrategy.setConverter(StringToNumberConverter.toLong(true));
		
		UpdateValueStrategy m2tStrategy = new UpdateValueStrategy(UpdateValueStrategy.POLICY_UPDATE);
		m2tStrategy.setConverter(NumberToStringConverter.fromLong(true));
		
		IObservableValue minValObs = EMFEditProperties.value(alternative.getEditingDomain(),
				ExperimentsPackage.Literals.NESTED_INTERVALS_VALUE_PROVIDER__MIN_VALUE).observe(valueProvider);
		IObservableValue maxValObs = EMFEditProperties.value(alternative.getEditingDomain(),
				ExperimentsPackage.Literals.NESTED_INTERVALS_VALUE_PROVIDER__MAX_VALUE).observe(valueProvider);
		
		Binding minValBind = bindingContext.bindValue(WidgetProperties.text(SWT.Modify).observe(textMinValue),
		        minValObs, t2mStrategy, m2tStrategy);
		
		Binding maxValBind = bindingContext.bindValue(WidgetProperties.text(SWT.Modify).observe(textMaxValue),
		        maxValObs, t2mStrategy, m2tStrategy);
		
		ControlDecorationSupport.create(minValBind, SWT.TOP | SWT.LEFT);
		ControlDecorationSupport.create(maxValBind, SWT.TOP | SWT.LEFT);
		
		bindingContext.updateTargets();
	}
}
