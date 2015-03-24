package eu.cloudscaleproject.env.analyser.wizard;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardNode;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardSelectionPage;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

public class NewInputAlternative extends WizardSelectionPage{
	
	private static class WizardNode implements IWizardNode{
		
		private final Wizard wizard;
		
		public WizardNode(Wizard wizard) {
			this.wizard = wizard;
		}

		@Override
		public void dispose() {
			this.wizard.dispose();
		}

		@Override
		public Point getExtent() {
			return new Point(-1, -1);
		}

		@Override
		public IWizard getWizard() {
			return wizard;
		}

		@Override
		public boolean isContentCreated() {
			return true;
		}
		
	}

	protected NewInputAlternative(String pageName) {
		super(pageName);
	}

	@Override
	public void createControl(Composite parent) {
		
	}

}
