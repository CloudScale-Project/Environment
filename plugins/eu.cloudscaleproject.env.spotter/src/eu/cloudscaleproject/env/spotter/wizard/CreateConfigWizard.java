package eu.cloudscaleproject.env.spotter.wizard;

import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.wizard.CreateConfigAlternativeWizard;

public class CreateConfigWizard extends CreateConfigAlternativeWizard{

	public CreateConfigWizard() {
		super(CSToolResource.SPOTTER_DYN_CONF, CSToolResource.SPOTTER_DYN_INPUT);
	}

}
