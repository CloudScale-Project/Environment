/**
 * More information on this code: http://www.jevon.org/wiki/GMF_Code_Samples
 */
package eu.cloudscaleproject.env.csm2pcm.diagram;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;


/**
 * Refresh all the connections from a given EObject.
 * Based on generated XXXDiagramUpdateCommand and XXXCreateShortcutDecorationsCommand.
 *
 * @author jmwright
 *
 */
public class RefreshElementCommand extends AbstractTransactionalCommand {

	private EObject rootObject;
	private View parentView;

	public RefreshElementCommand(
			EObject rootObject,
			TransactionalEditingDomain editingDomain, View parentView) {
		super(editingDomain, "Refresh element view", getWorkspaceFiles(parentView)); //$NON-NLS-1$
		this.rootObject = rootObject;
		this.parentView = parentView;
	}

	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {

		// refresh visibility
		parentView.setVisible(false);
		parentView.setVisible(true);

		// from generated DiagramUpdateCommand
		List editPolicies = CanonicalEditPolicy
			.getRegisteredEditPolicies(rootObject);
		for (Iterator it = editPolicies.iterator(); it.hasNext(); ) {
			CanonicalEditPolicy nextEditPolicy = (CanonicalEditPolicy) it.next();
			nextEditPolicy.refresh();
		}

		return CommandResult.newOKCommandResult();
	}

}