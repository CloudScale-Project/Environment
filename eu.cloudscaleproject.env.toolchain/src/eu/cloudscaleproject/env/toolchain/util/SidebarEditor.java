package eu.cloudscaleproject.env.toolchain.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

import eu.cloudscaleproject.env.common.BasicCallback;
import eu.cloudscaleproject.env.common.dialogs.TextInputDialog;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class SidebarEditor extends AbstractSidebarEditor{

	private SidebarContentProvider contentProvider = null;
	private ResourceProvider resourceProvider = null;
	
	public SidebarEditor(Composite sidebar, Composite area) {
		super(sidebar, area);
	}
	
	public void setContentProvider(SidebarContentProvider compositeProvider){
		this.contentProvider = compositeProvider;
		init();
	}
	
	public void setResourceProvider(ResourceProvider resourceProvider){
		this.resourceProvider = resourceProvider;
		init();
	}
	
	@Override
	public void init() {
		if(resourceProvider != null && contentProvider != null){
			super.init();
		}
	}

	@Override
	public Composite createInputComposite(IEditorInput input, Composite parent, int style) {
		if(contentProvider == null){
			return null;
		}
		
		return contentProvider.createComposite(parent, style, (IEditorInputResource)input);
	}

	@Override
	public List<IEditorInput> getInputs(String section) {
		List<IEditorInput> resources = new ArrayList<IEditorInput>();
		
		if(resourceProvider == null){
			return resources;
		}
		
		for(IEditorInputResource res : resourceProvider.getResources()){
			if(section == null){
				if(contentProvider.getSection(res) == null){
					resources.add(res);
				}
			}
			else if(section.equals(contentProvider.getSection(res))){
				resources.add(res);
			}
		}
		return resources;
	}

	@Override
	public String[] getSidebarSections() {
		if(contentProvider == null){
			return new String[]{};
		}
		return contentProvider.getSections();
	}

	@Override
	public IResource[] getDependentRootResource() {
		
		if(resourceProvider == null){
			return null;
		}
		
		return new IResource[]{resourceProvider.getRootFolder()};
	}

	@Override
	public void handleNewInput(IEditorInput selected) {
		TextInputDialog dialog = new TextInputDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
				new BasicCallback<String>() {
			
			@Override
			public void handle(String text) {
				if(resourceProvider == null){
					throw new IllegalStateException("Sidebar resource provider not set!");
				}
				resourceProvider.createNewResource(text);				
			}
		});
		dialog.open();
	}

	@Override
	public void handleNewInputFrom(final IEditorInput selected) {
		
		if(!(selected instanceof IEditorInputResource)){
			return;
		}
		
		TextInputDialog dialog = new TextInputDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
				new BasicCallback<String>() {
			
			@Override
			public void handle(String text) {
				if(resourceProvider == null){
					throw new IllegalStateException("Sidebar resource provider not set!");
				}
				IEditorInputResource res = resourceProvider.createNewResource(text);
				res.copyFrom(((IEditorInputResource)selected).getResource());
				res.setName(text);
				res.save();
			}
		});
		dialog.open();
	}

	@Override
	public void handleInputDelete(IEditorInput toDelete) {
		if(!(toDelete instanceof IEditorInputResource)){
			return;
		}
		
		resourceProvider.deleteResource(((IEditorInputResource)toDelete).getResource().getName());
	}
	
	@Override
	public void update() {
		
		if(contentProvider == null || resourceProvider == null){
			super.update();
			return;
		}
		
		if(resourceProvider.reloadResources()){
			init();
		}
		else{
			super.update();
		}
	}
}
