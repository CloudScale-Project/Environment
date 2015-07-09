package eu.cloudscaleproject.env.toolchain.resources.types;

import java.util.logging.Logger;

import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public abstract class EditorInputJob extends WorkspaceJob{
	
	private static final Logger logger = Logger.getLogger(EditorInputJob.class.getName());
	
	private EditorInputResource[] eirs;
	
	public EditorInputJob(String name, EditorInputResource... eirs) {
		super(name);
		this.eirs = eirs;
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
		
		try{
			for(EditorInputResource eir : eirs){
				eir.setJobInProgress(true);
			}
			
			return execute(monitor);
		}
		finally{
			for(EditorInputResource eir : eirs){
				
				if(eir.isDirty()){
					logger.warning("Job has left alternative in dirty state. Triggering save. Alternative: " + eir.toString());
					eir.save();
				}
				
				eir.setJobInProgress(false);
				eir.load();
			}
		}
		
		
	}

	public abstract IStatus execute(IProgressMonitor monitor);
}
