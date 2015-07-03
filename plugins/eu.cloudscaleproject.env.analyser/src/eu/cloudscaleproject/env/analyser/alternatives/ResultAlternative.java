package eu.cloudscaleproject.env.analyser.alternatives;

import java.io.File;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.edp2.impl.RepositoryManager;
import org.palladiosimulator.edp2.local.LocalDirectoryRepository;
import org.palladiosimulator.edp2.models.Repository.Repository;
import org.palladiosimulator.edp2.repository.local.LocalDirectoryRepositoryHelper;

import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;

public class ResultAlternative extends EditorInputFolder{

	private final ConfAlternative.Type type;
	
	public ResultAlternative(IProject project, IFolder folder, ConfAlternative.Type type) {
		super(project, folder, CSTool.ANALYSER_RES.getID());
		this.type = type;		
	}
	
	public ConfAlternative.Type getTypeEnum(){
		return type;
	}
	
	public ConfAlternative getConfAlternative(){
		IResource res = getSubResource(ToolchainUtils.KEY_CONFIG_ALTERNATIVE);
		ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(project, CSTool.ANALYSER_CONF);
		return (ConfAlternative) rp.getResource(res);
	}
	
	public LocalDirectoryRepository getEDP2Model(){
		
		if(!getResource().exists()){
			return null;
		}
		
		try {
			getResource().refreshLocal(IFolder.DEPTH_INFINITE, null);
		} catch (CoreException e1) {
			e1.printStackTrace();
		}
			
		return findOrInitRepository(getResource().getLocation().toOSString());
	}
	
	/**
     * Initializes the repository in which the data will be stored.
     * 
     * @param directory
     *            Path to directory in which the data should be stored.
     * @return the initialized repository.
     */
    private LocalDirectoryRepository findOrInitRepository(final String directory) {
    	File dir = new File(directory);
        
        /*
         * Add repository to a (optional) central directory of repositories. This can be useful to
         * manage more than one repository or have links between different existing repositories. A
         * repository must be connected to an instance of Repositories in order to be opened.
         */
        
        String path = URI.createFileURI(dir.getAbsolutePath()).toString();
        
        for(Repository rep : RepositoryManager.getCentralRepository().getAvailableRepositories()){
        	if(rep instanceof LocalDirectoryRepository){
        		LocalDirectoryRepository ldr = (LocalDirectoryRepository)rep;
        		if(ldr.getUri().equals(path)){
        			return ldr;
        		}
        	}
        }
        
        //if the repository for the specified path is not found
        final LocalDirectoryRepository repo = LocalDirectoryRepositoryHelper.initializeLocalDirectoryRepository(dir);
        RepositoryManager.addRepository(RepositoryManager.getCentralRepository(), repo);
        return repo;
    }
    
    @Override
    protected void doDelete() {
    	LocalDirectoryRepository ldr = getEDP2Model();
    	RepositoryManager.removeRepository(RepositoryManager.getCentralRepository(), ldr);
    	super.doDelete();
    }
}
