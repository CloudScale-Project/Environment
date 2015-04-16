package eu.cloudscaleproject.env.method.viewer;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.ui.editor.DiagramEditor;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.notification.IToolStatusListener;
import eu.cloudscaleproject.env.method.common.method.Requirement;
import eu.cloudscaleproject.env.method.common.method.StatusNode;

public class ProjectStatusService {

	private static final Logger logger = Logger.getLogger(ProjectStatusService.class.getName());
	
	private ResourceSet resourceSet = new ResourceSetImpl();
	private DiagramEditor editor;
	private final Object editorLock = new Object();

	private HashMap<String, ToolStatusImpl> toolStatusMap = new HashMap<String, ToolStatusImpl>();
	private HashMap<String, ResourceStatusImpl> resoStatusMap = new HashMap<String, ResourceStatusImpl>();

	public ProjectStatusService(IProject project) {
		
		String path = ExplorerProjectPaths.getProjectProperty(project,
				ExplorerProjectPaths.FILE_METHOD_NEW);
		URI uri = URI.createPlatformResourceURI(
				project.getFullPath().append(path).toString(), true);

		IResource resource = project.findMember(path);
		// create new model
		if (resource == null || !resource.exists()) {
			IFile file = project.getFile(path);
			Util.createMethod(file);
		}

		// load model
		Resource res = resourceSet.getResource(uri, true);
		if (!res.isLoaded()) {
			try {
				res.load(null);
			} catch (IOException e) {
				logger.severe("Unable to load resource URI: " + uri.toString());
				e.printStackTrace();
			}
		}
		
		//create and cache all tool statuses
		TreeIterator<Object> iter = EcoreUtil.getAllContents(res, true);
		while (iter.hasNext()) {
			Object o = iter.next();
			if (o instanceof StatusNode) {
				StatusNode s = (StatusNode) o;
				
				ToolStatusImpl status = null;
				
				if(s instanceof Requirement){
					status = new ResourceStatusImpl(project, s);
					resoStatusMap.put(s.getId(), (ResourceStatusImpl)status);
				}
				else{
					status = new ToolStatusImpl(project, s);
					toolStatusMap.put(s.getId(), status);
				}
				
				status.addListener(new IToolStatusListener() {
					@Override
					public void propertyChange(PropertyChangeEvent arg0) {
						synchronized (editorLock) {
							if(ProjectStatusService.this.editor != null){
								ProjectStatusService.this.editor.getDiagramBehavior().refreshContent();
							}
						}
					}
				});
			}
		}
	}

	public void registerDiagramEditor(DiagramEditor editor) {
		synchronized (editorLock) {
			this.editor = editor;
			if(editor != null){
				this.editor.getDiagramBehavior().refreshContent();
			}
		}
	}

	public ToolStatusImpl getToolStatus(String toolID) {
		return toolStatusMap.get(toolID);
	}
	
	public ResourceStatusImpl getResourceStatus(String resID) {
		return resoStatusMap.get(resID);
	}
	
	public void dispose(){
		for(ToolStatusImpl status : toolStatusMap.values()){
			status.dispose();
		}
		for(ToolStatusImpl status : resoStatusMap.values()){
			status.dispose();
		}
	}
}
