package eu.cloudscaleproject.env.extractor.editors.composites;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.modisco.infra.discovery.catalog.DirectionKind;
import org.eclipse.modisco.infra.discovery.catalog.DiscovererDescription;
import org.eclipse.modisco.infra.discovery.catalog.DiscovererParameter;
import org.eclipse.modisco.infra.discovery.launch.LaunchConfiguration;
import org.eclipse.modisco.infra.discovery.ui.internal.launch.DiscovererUpdate;
import org.eclipse.modisco.infra.discovery.ui.internal.launch.data.DiscovererParameterDisplay;
import org.eclipse.modisco.infra.discovery.ui.internal.launch.data.DiscovererParameterDisplayImpl;
import org.eclipse.modisco.infra.discovery.ui.internal.launch.parametersdisplay.ParametersTableComposite;
import org.eclipse.modisco.infra.discovery.ui.internal.util.LaunchModelUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.extractor.alternatives.InputAlternative;

public class ModiscoConfigurationComposite extends Composite
{
	private InputAlternative alternative;
	@SuppressWarnings("unused")
	private ParametersTableComposite parametersTable;
	private boolean initialized = false;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ModiscoConfigurationComposite(Composite parent, int style, InputAlternative alternative)
	{
		super(parent, style);

		this.alternative = alternative;
		this.setLayout(new GridLayout(1, false));
		initialize(parent);
	}

	private final void initialize(Composite parent)
	{
		LaunchConfiguration modiscoConfiguration = this.alternative.getModiscoConfiguration();
		DiscovererDescription discoverer = this.alternative.getModiscoConfiguration().getDiscoverer();
		List<DiscovererParameterDisplay> displayParameters = new ArrayList<DiscovererParameterDisplay>();

		for (DiscovererParameter parameter : discoverer.getParameterDefinitions())
		{
			if (parameter.getDirection() != DirectionKind.IN) continue;

			CustomDiscovererUpdate cdu = new CustomDiscovererUpdate(modiscoConfiguration);
			DiscovererParameterDisplay displayParameter = new DiscovererParameterDisplayImpl(parameter, discoverer, cdu);
			cdu.setParameterDisplay(displayParameter);
			displayParameters.add(displayParameter);
		}

		this.parametersTable = new ParametersTableComposite(this, SWT.NONE, displayParameters, true);
		
		initialized = true;
	}
	
	private class CustomDiscovererUpdate implements DiscovererUpdate
	{
		private DiscovererParameterDisplay parameterDisplay;
		private LaunchConfiguration configuration;

		public CustomDiscovererUpdate(LaunchConfiguration configuration)
		{
			this.configuration = configuration;
		}
		
		public void setParameterDisplay(DiscovererParameterDisplay displayParameter)
		{
			this.parameterDisplay = displayParameter;

			if (configuration != null)
			{
				Object value = LaunchModelUtils.getDiscoveryParameterValue(configuration, displayParameter.getParameterDescription());
				displayParameter.setValue(value);
			} else
			{
				displayParameter.initialize(null);
			}

		}

		@Override
		public void update()
		{
			if (!initialized) return;
			
			this.parameterDisplay.getValue();
			this.parameterDisplay.getParameterDescription();
			
			LaunchModelUtils.setDiscoveryParameterValue(
					configuration, 
					this.parameterDisplay.getParameterDescription(), 
					this.parameterDisplay.getValue());
		}
		
	}

}
