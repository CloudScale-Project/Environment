package eu.cloudscaleproject.env.csm.diagram.editor.system;

import java.util.EventObject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.MultiPageEditorPart;

import eu.cloudscaleproject.env.csm.Csm;
import eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService;

public class SystemEditor extends MultiPageEditorPart {

	public static final String ID = "eu.cloudscaleproject.env.csm.diagram.editor.system.SystemEditor"; //$NON-NLS-1$
	private SystemEditorInput input;
	private TransactionalEditingDomain editingDomain;
	private CommandStackListener commandStackListener;
	private Csm dirtyCheckCsm;
	private DeploymentEditor deploymentEditor;
	private OverviewEditor overviewEditor;

	public SystemEditor() {
	}
	
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		super.init(site, input);
		
		this.input = (SystemEditorInput)input;
		setPartName(this.input.getName());
		
		this.dirtyCheckCsm = EcoreUtil.copy(this.input.getCsm());
		
		ResourceSet rset = this.input.getCsm().eResource().getResourceSet();  
		this.editingDomain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(rset);
		this.commandStackListener =  new CommandStackListener() {
			
			@Override
			public void commandStackChanged(EventObject event) {
				 getContainer().getDisplay().asyncExec
					 (new Runnable() {
						  public void run() {
							  firePropertyChange(PROP_DIRTY);
						  }
					  });
			}
		};
		
		this.editingDomain.getCommandStack().addCommandStackListener(this.commandStackListener);
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
		} catch (PartInitException e) {
		}
		
	}
	
	@Override
	public boolean isDirty() {
		ResourceSet rset = input.getCsm().eResource().getResourceSet();  // access some hypothetical resource set
		TransactionalEditingDomain domain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(rset);
		
		boolean stackChanged = ((BasicCommandStack)domain.getCommandStack()).isSaveNeeded();
		
		if (stackChanged)
		{
			return !EcoreUtil.equals(dirtyCheckCsm, input.getCsm());
		}
		
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
		
		// FIXME : for now we only save Graphiti editor - the dirty check is synced with editing domain
		
		// Save all editors -- Graphiti diagram changes and needs to be saved.
		Job job = new Job ("Save all editors"){

		@Override
		protected IStatus run(IProgressMonitor monitor) {
			Display.getDefault().syncExec(new Runnable() {
				
				@Override
				public void run() {
					PlatformUI.getWorkbench().saveAllEditors(false);
					dirtyCheckCsm = EcoreUtil.copy(input.getCsm());
					firePropertyChange(PROP_DIRTY);
				}
			});
			return Status.OK_STATUS;
		}};
		job.schedule();
	}
	
	@Override
	public void doSaveAs() {
	}
	
	
}
