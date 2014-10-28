package eu.cloudscaleproject.env.toolchain.util;

import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;

public class SidebarEditorComposite extends AbstractSidebarEditorComposite{

	private SidebarEditor sidebarBuilder;

	public SidebarEditorComposite(Composite parent, int style) {
		super(parent, style);
		sidebarBuilder = new SidebarEditor(compositeSidebar, compositeArea);
	}
	
	public void setContentProvider(SidebarContentProvider compositeProvider) {
		sidebarBuilder.setContentProvider(compositeProvider);
	}

	public void setResourceProvider(ResourceProvider resourceProvider) {
		sidebarBuilder.setResourceProvider(resourceProvider);
	}

	@Override
	public Composite createInputComposite(IEditorInput input,
			Composite parent, int style) {
		return sidebarBuilder.createInputComposite(input, parent, style);
	}

	@Override
	public void handleNewInput(IEditorInput selected) {
		sidebarBuilder.handleNewInput(selected);
	}

	@Override
	public void handleNewInputFrom(IEditorInput selected) {
		sidebarBuilder.handleNewInputFrom(selected);
	}

	@Override
	public void handleInputDelete(IEditorInput toDelete) {
		sidebarBuilder.handleInputDelete(toDelete);
	}

	@Override
	public List<IEditorInput> getInputs(String section) {
		return sidebarBuilder.getInputs(section);
	}

	@Override
	public String[] getSidebarSections() {
		return sidebarBuilder.getSidebarSections();
	}

	@Override
	public IResource[] getDependentRootResource() {
		return sidebarBuilder.getDependentRootResource();
	}
}
