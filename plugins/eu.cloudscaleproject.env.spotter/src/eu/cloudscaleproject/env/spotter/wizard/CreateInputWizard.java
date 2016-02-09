package eu.cloudscaleproject.env.spotter.wizard;

import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.wizard.CreateAlternativeWizard;

public class CreateInputWizard extends CreateAlternativeWizard{

	public CreateInputWizard() {
		super(CSToolResource.SPOTTER_DYN_INPUT);
	}

}
