package eu.cloudscaleproject.env.csm.diagram.editor.component;

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

import eu.cloudscaleproject.env.csm.core.Entity;
import eu.cloudscaleproject.env.csm.diagram.editor.extractor.ExtractorEditor;

public class ComponentEditor extends MultiPageEditorPart {

	public static final String ID = "eu.cloudscaleproject.env.csm.diagram.editor.component.ComponentEditor"; //$NON-NLS-1$
	private ComponentEditorInput input;
	private TransactionalEditingDomain editingDomain;
	private CommandStackListener commandStackListener;
	private Entity dirtyCheckEntity;

	public ComponentEditor() {
	}
	
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		super.init(site, input);
		
		this.input = (ComponentEditorInput)input;
		setPartName(this.input.getName());
		
		this.dirtyCheckEntity = EcoreUtil.copy(this.input.getEntity());
		
		ResourceSet rset = this.input.getEntity().eResource().getResourceSet();  
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
			int pageIndex = addPage(new MainEditor(), getEditorInput());
			setPageText(pageIndex, "Main");
			pageIndex = addPage(new AnalyserEditor(), getEditorInput());
			setPageText(pageIndex, "Analyser");
			pageIndex = addPage(new ExtractorEditor(), getEditorInput());
			setPageText(pageIndex, "Extractor");
			pageIndex = addPage(new SpotterEditor(), getEditorInput());
			setPageText(pageIndex, "Spotter");
		} catch (PartInitException e) {
		}
	}
	
	@Override
	public boolean isDirty() {
		ResourceSet rset = input.getEntity().eResource().getResourceSet();  // access some hypothetical resource set
		TransactionalEditingDomain domain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(rset);
		
		boolean stackChanged = ((BasicCommandStack)domain.getCommandStack()).isSaveNeeded();
		
		if (stackChanged)
		{
			return !EcoreUtil.equals(dirtyCheckEntity, input.getEntity());
		}
		
		return false;
	}
	
	@Override
	public boolean isSaveAsAllowed() {
		return false;
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
					dirtyCheckEntity = EcoreUtil.copy(input.getEntity());
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
