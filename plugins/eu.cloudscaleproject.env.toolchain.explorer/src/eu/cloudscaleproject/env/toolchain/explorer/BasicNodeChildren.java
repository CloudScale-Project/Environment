package eu.cloudscaleproject.env.toolchain.explorer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BasicNodeChildren extends ExplorerNodeChildren{

	private final List<IExplorerNode> nodes = new ArrayList<IExplorerNode>();
	
	public BasicNodeChildren(IExplorerNode[] nodes) {
		super(false);
		
		Collections.addAll(this.nodes, nodes);
	}
	
	public BasicNodeChildren(Collection<IExplorerNode> nodes) {
		super(false);
		
		this.nodes.addAll(nodes);
	}
	
	@Override
	protected List<? extends Object> getKeys() {
		return nodes;
	}

	@Override
	public IExplorerNode getChild(Object key) {
		return (IExplorerNode)key;
	}

}
