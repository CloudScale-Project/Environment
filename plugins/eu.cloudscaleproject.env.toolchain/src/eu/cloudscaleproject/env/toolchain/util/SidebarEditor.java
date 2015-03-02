package eu.cloudscaleproject.env.toolchain.util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

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
	
	private final PropertyChangeListener rcl = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			
			if(contentProvider == null){
				return;
			}
			
			if(ResourceProvider.PROP_RESOURCE_ADDED.equals(evt.getPropertyName())){
				IEditorInputResource res = (IEditorInputResource)evt.getNewValue();
				String section = contentProvider.getSection(res);
				SidebarEditor.this.addSidebarEditor(res, section);
			}
			if(ResourceProvider.PROP_RESOURCE_REMOVED.equals(evt.getPropertyName())){
				IEditorInputResource res = (IEditorInputResource)evt.getOldValue();
				SidebarEditor.this.removeSidebarEditor(res);
			}
			if(ResourceProvider.PROP_RESOURCE_MODIFIED.equals(evt.getPropertyName())){
				IEditorInputResource res = (IEditorInputResource)evt.getNewValue();
				Composite current = getCurrentSelection();
				if(current != null && !current.isDisposed() && current.isVisible()){
					return;
				}
				load(res);
			}
		}
	};
	
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
			resourceProvider.addListener(rcl);
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
	public void handleNewInput(IEditorInput selected) {
		doHandleNewInput(selected);
	}
	
	public void doHandleNewInput(IEditorInput selected) {
		TextInputDialog dialog = new TextInputDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
				new BasicCallback<String>() {
			
			@Override
			public void handle(String text) {
				if(resourceProvider == null){
					throw new IllegalStateException("Sidebar resource provider not set!");
				}
				resourceProvider.createNewResource(text, null);				
			}
		});
		dialog.open();
	}

	@Override
	public void handleNewInputFrom(final IEditorInput selected) {
		doHandleNewInputFrom(selected);
	}
	
	public void doHandleNewInputFrom(final IEditorInput selected) {
		
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
				IEditorInputResource res = resourceProvider.createNewResource(text, null);
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
	public void dispose() {
		if(resourceProvider != null){
			resourceProvider.removeListener(rcl);
		}
		super.dispose();
	}
}
