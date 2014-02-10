package eu.cloudscaleproject.env.csm.diagram.editor.system;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;

import eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService;

public class SystemEditor extends MultiPageEditorPart {

	public static final String ID = "eu.cloudscaleproject.env.csm.diagram.editor.system.SystemEditor"; //$NON-NLS-1$
	
	@SuppressWarnings("unused")
	private SystemEditorInput input;
	private TransactionalEditingDomain editingDomain;
	private CommandStackListener commandStackListener;
	private DeploymentEditor deploymentEditor;
	private OverviewEditor overviewEditor;

	public SystemEditor() {
	}
	
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		super.init(site, input);
		
		this.input = (SystemEditorInput)input;
		setPartName("System editor");
	}
	
	@Override
	public void dispose() {
		
		this.editingDomain.getCommandStack().removeCommandStackListener(this.commandStackListener);
		super.dispose();
	}
	

	@Override
	protected void createPages() {
		try {
			this.overviewEditor = new OverviewEditor ();
			int pageIndex = addPage(overviewEditor, getEditorInput());
			setPageText(pageIndex, "Overview");
			
			this.deploymentEditor = new DeploymentEditor();
			pageIndex = addPage(deploymentEditor, getEditorInput());
			System.out.println(pageIndex);
			setPageText(pageIndex, "Deployment");
			
			setActiveEditor(deploymentEditor);
		} catch (PartInitException e) {
		}
		
	}
	
	@Override
	public boolean isDirty() {
		
		// TODO: if needed
		// Partial solution removed in r715
		
		return false;
	}
	
	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
	
	public void setSelection (IEditorInput input)
	{
		if (input instanceof SystemEditorInput)
		{
			Object selection = ((SystemEditorInput)input).getSelection();
			if (selection != null)
			{
				if (selection instanceof ComputingInfrastructureService) {
					setActiveEditor(deploymentEditor);
					this.deploymentEditor.setSelection((ComputingInfrastructureService)selection);
				}
			}
		}
		else
		{
			throw new RuntimeException("Should not happen!");
		}
	}
	

	@Override
	public void doSave(final IProgressMonitor monitor) {
		// TODO: no used - check isDirty
	}
	
	@Override
	public void doSaveAs() {
		// TODO: no used - check isDirty
	}
	
	
}
