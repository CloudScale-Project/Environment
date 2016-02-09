package eu.cloudscaleproject.env.staticspotter.wizard;

import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.wizard.CreateConfigAlternativeWizard;

public class CreateConfigWizard extends CreateConfigAlternativeWizard{

	public CreateConfigWizard() {
		super(CSToolResource.SPOTTER_STA_CONF, CSToolResource.SPOTTER_STA_INPUT);
		
	}

}
