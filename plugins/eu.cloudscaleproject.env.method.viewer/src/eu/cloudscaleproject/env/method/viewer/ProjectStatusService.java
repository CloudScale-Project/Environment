package eu.cloudscaleproject.env.method.viewer;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.notification.IToolStatus;
import eu.cloudscaleproject.env.method.common.method.Method;
import eu.cloudscaleproject.env.method.common.method.StatusNode;

public class ProjectStatusService {

	private static final Logger logger = Logger
			.getLogger(ProjectStatusService.class.getName());

	private ResourceSet resourceSet = new ResourceSetImpl();
	private DiagramEditor editor;

	private final Object editorLock = new Object();

	private HashMap<String, ToolStatusImpl> statusMap = new HashMap<String, ToolStatusImpl>();

	private boolean timerActive = false;

	public ProjectStatusService() {
		resourceSet.eAdapters().add(
				new ResourceSetUpdateAdapter(new AdapterImpl() {

					@Override
					public boolean isAdapterForType(Object type) {
						return true;
					}

					@Override
					public void notifyChanged(Notification msg) {
						synchronized (editorLock) {
							if (!timerActive) {
								timerActive = true;
								Display.getDefault().timerExec(100,
										new Runnable() {

											@Override
											public void run() {
												timerActive = false;
												if (ProjectStatusService.this.editor != null) {
													ResourceSet diagramRs = ProjectStatusService.this.editor .getDiagramBehavior() .getResourceSet();
													for (Resource res : diagramRs .getResources()) {
														res.unload();
													}

													ProjectStatusService.this.editor .getDiagramBehavior() .refreshContent();
												}

											}
										});
							}
						}
						super.notifyChanged(msg);
					}

				}));
	}

	public void registerDiagramEditor(DiagramEditor editor) {
		synchronized (editorLock) {
			this.editor = editor;
		}
	}

	public IToolStatus getToolStatus(IProject project, String tool) {
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Method method = null;
		if (method == null) {
			for (EObject eo : res.getContents()) {
				if (eo instanceof Method) {
					method = (Method) eo;
				}
			}
		}

		if (res == null || method == null) {
			logger.severe("Project: "
					+ project.getName()
					+ "failed to create/load method model used for notification system!");
			return null;
		}

		StatusNode statusNode = null;
		TreeIterator<Object> iter = EcoreUtil.getAllContents(res, true);
		while (iter.hasNext()) {
			Object o = iter.next();
			if (o instanceof StatusNode) {
				StatusNode s = (StatusNode) o;
				if (s.getId().equals(tool)) {
					statusNode = s;
				}
			}
		}

		if (statusNode == null) {
			return null;
		}

		ToolStatusImpl status = statusMap.get(tool);
		if (status == null) {
			status = new ToolStatusImpl();
			statusMap.put(tool, status);
		}

		status.setStatusNode(statusNode);
		return status;
	}

	private class ResourceSetUpdateAdapter extends AdapterImpl {

		private Adapter adapter;

		/**
		 * @param diagramEditorBehavior
		 */
		ResourceSetUpdateAdapter(Adapter a) {
			this.adapter = a;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(ResourceSet.class) == ResourceSet.RESOURCE_SET__RESOURCES) {
				switch (msg.getEventType()) {
				case Notification.ADD:
					((Resource) msg.getNewValue()).eAdapters().add(adapter);
					break;
				case Notification.ADD_MANY:
					for (final Resource res : (Collection<Resource>) msg
							.getNewValue()) {
						res.eAdapters().add(adapter);
					}
					break;
				case Notification.REMOVE:
					((Resource) msg.getOldValue()).eAdapters().remove(adapter);
					break;
				case Notification.REMOVE_MANY:
					for (final Resource res : (Collection<Resource>) msg
							.getOldValue()) {
						res.eAdapters().remove(adapter);
					}
					break;

				default:
					break;
				}
			}
		}
	}
}
