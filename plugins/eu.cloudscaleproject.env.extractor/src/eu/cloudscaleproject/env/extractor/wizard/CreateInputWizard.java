package eu.cloudscaleproject.env.extractor.wizard;

import org.eclipse.ui.IWorkbenchWizard;

import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.wizard.CreateAlternativeWizard;

public class CreateInputWizard extends CreateAlternativeWizard implements IWorkbenchWizard {
	
	public CreateInputWizard(){
		super(CSToolResource.EXTRACTOR_INPUT);
	}


}
