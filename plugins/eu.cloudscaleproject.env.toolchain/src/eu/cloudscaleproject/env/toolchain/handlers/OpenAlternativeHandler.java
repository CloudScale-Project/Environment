package eu.cloudscaleproject.env.toolchain.handlers;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.core.resources.IResource;
import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.toolchain.resources.ResourceExtensions;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class OpenAlternativeHandler {

	@Inject
	public MApplication application;
	@Inject
	public EModelService modelService;
	
	@Execute
	public void execute(MApplication app, @Active final IEditorInputResource eir){
		
		//TODO: fix this workaround ("No active window exception")!
		List<MWindow> windowList = app.getChildren();
		if(windowList.isEmpty()){
			return;
		}
		
		final MWindow window = windowList.get(0);
		
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				
				BusyIndicator.showWhile(Display.getDefault(), new Runnable() {
					@Override
					public void run() {
						EPartService partService = window.getContext().get(EPartService.class);
						openEditor(partService, eir);
					}
				});
				
			}
		});		
		
	}
	
	@CanExecute
	public boolean canExecute(@Active @Optional IEditorInputResource eir){
		
		if(eir == null){
			return false;
		}
		
		String editorID = findEditorID(eir);
		if(editorID != null){
			return true;
		}
		return false;
	}
	
	private void openEditor(EPartService partService, IEditorInputResource eir){
		String editorPartID = findEditorID(eir);
		MPart part = partService.findPart(editorPartID);
		
		if(part == null){
			MPartStack stack = (MPartStack)modelService.find("org.eclipse.e4.primaryDataStack", application);
			if(stack != null){
				
				part = partService.createPart(editorPartID);
				part.getProperties().put("input", eir.getResource().getFullPath().toPortableString());
				
				stack.getChildren().add(part);				
				partService.showPart(part, PartState.ACTIVATE);
			}
		}
		else{
			
			partService.showPart(part, PartState.ACTIVATE);
			
			IEclipseContext context = part.getContext();
			context.set(eir.getClass().getName(), eir);
			
			IResource resource = eir.getResource();
			if(resource != null){
				context.set(IResource.class, resource);
			}
		}
		
	}
	
	private String findEditorID(IEditorInputResource eir){
		
		List<ResourceProvider> resourceProviders = ResourceRegistry.getInstance().getResourceProviders(eir.getProject());
		
		for(ResourceProvider rp : resourceProviders){
			if(rp.getResources().contains(eir)){
				
				String editorPartID = ResourceExtensions.getInstance().getResourceExtension(rp.getID()).getEditorID();
				
				if(editorPartID != null && !editorPartID.isEmpty()){
					return editorPartID;
				}
			}
		}
		
		return null;
	}
	
}
