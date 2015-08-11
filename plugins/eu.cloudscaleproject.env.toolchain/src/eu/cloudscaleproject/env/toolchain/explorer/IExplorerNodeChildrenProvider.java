package eu.cloudscaleproject.env.toolchain.explorer;


/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public interface IExplorerNodeChildrenProvider {
	
	public IExplorerNodeChildren create(IExplorerNode node);
	public boolean canCreate(IExplorerNode node);

}
