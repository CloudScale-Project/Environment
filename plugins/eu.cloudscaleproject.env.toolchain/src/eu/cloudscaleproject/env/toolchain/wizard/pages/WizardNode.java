package eu.cloudscaleproject.env.toolchain.wizard.pages;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardNode;
import org.eclipse.swt.graphics.Point;

public abstract class WizardNode implements IWizardNode{
	
	protected IWizard wizard;
	
	public abstract String getName();
	public abstract String getDescription();
	public abstract IWizard createWizard();
	
	@Override
	public void dispose() {
		if(wizard != null){
			wizard.dispose();
			wizard = null;
		}
	}

	@Override
	public Point getExtent() {
		return new Point(-1, -1);
	}

	@Override
	public IWizard getWizard() {
		if(wizard == null){
			wizard = createWizard();
		}
		return wizard;
	}

	@Override
	public boolean isContentCreated() {
		return (wizard != null);
	}
}
