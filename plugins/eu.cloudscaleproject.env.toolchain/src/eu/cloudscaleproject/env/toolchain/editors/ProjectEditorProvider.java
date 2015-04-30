package eu.cloudscaleproject.env.toolchain.editors;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Creatable;

import eu.cloudscaleproject.env.common.ExtensionRetriever;
import eu.cloudscaleproject.env.toolchain.ProjectEditorExtension;

@Creatable
public class ProjectEditorProvider {
	
	public interface ToolEditorProviderListener{
		public void onAddToolEditorExtension(ProjectEditorExtension ext);
		public void onRemoveToolEditorExtension(ProjectEditorExtension ext);
	}
	
	private final List<ToolEditorProviderListener> listeners = new ArrayList<ProjectEditorProvider.ToolEditorProviderListener>();
	private final List<ProjectEditorExtension> extensions = new ArrayList<ProjectEditorExtension>();
	
	@Inject
	public ProjectEditorProvider(ExtensionRetriever extensionRetriever){
		List<ProjectEditorExtension> toolEditorExtensions 
			= extensionRetriever.retrieveExtensionObjects("eu.cloudscaleproject.env.toolchain.editors", "class", ProjectEditorExtension.class);
	
		for(ProjectEditorExtension tee : toolEditorExtensions){
			extensions.add(tee);
		}
	}
	
	public List<ProjectEditorExtension> getToolExtensions(){
		return new ArrayList<ProjectEditorExtension>(extensions);
	}
	
	public ProjectEditorExtension getToolExtension(String id){
		ProjectEditorExtension extension = null;
		for(ProjectEditorExtension pee : extensions){
			if(pee.getID().equals(id)){
				extension = pee;
			}
		}
		return extension;
	}
	
	public void addToolchainEditorExtension(ProjectEditorExtension ext){
		extensions.add(ext);
		for(ToolEditorProviderListener l : listeners){
			l.onAddToolEditorExtension(ext);
		}
	}
	
	public void removeToolchainEditorExtension(ProjectEditorExtension ext){
		extensions.remove(ext);
		for(ToolEditorProviderListener l : listeners){
			l.onRemoveToolEditorExtension(ext);
		}
	}
	
	public void addListener(ToolEditorProviderListener l){
		this.listeners.add(l);
	}
	
	public void removeListener(ToolEditorProviderListener l){
		this.listeners.remove(l);
	}

}
