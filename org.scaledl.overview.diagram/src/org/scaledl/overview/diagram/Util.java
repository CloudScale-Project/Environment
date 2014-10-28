/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.scaledl.overview.diagram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.PictogramsFactory;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.statushandlers.StatusManager;
import org.scaledl.overview.diagram.editor.system.SystemEditor;
import org.scaledl.overview.diagram.editor.system.SystemEditorInput;
import org.scaledl.overview.util.OverviewUtil;

public class Util {

	public static void openEditor (final IEditorInput editorInput, final String editorId)
	{
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				IWorkbenchPage page = window.getActivePage();
				
				try {
					IEditorPart editor = page.openEditor(editorInput, editorId);
					
					if (editor instanceof SystemEditor)
					{
						((SystemEditor)editor).setSelection((SystemEditorInput)editorInput);
					}
					
				} catch (PartInitException e) {
				}
				
			}
		});
	}
	
	public static Collection<Diagram> getDiagrams(IProject p) {
		final List<IFile> files = getDiagramFiles(p);
		final List<Diagram> diagramList = new ArrayList<Diagram>();
		final ResourceSet rSet = new ResourceSetImpl();
		for (final IFile file : files) {
			final Diagram diagram = getDiagramFromFile(file, rSet);
			if (diagram != null) {
				diagramList.add(diagram);
			}
		}
		return diagramList;
	}

	private static List<IFile> getDiagramFiles(IContainer folder) {
		final List<IFile> ret = new ArrayList<IFile>();
		try {
			final IResource[] members = folder.members();
			for (final IResource resource : members) {
				if (resource instanceof IContainer) {
					ret.addAll(getDiagramFiles((IContainer) resource));
				} else if (resource instanceof IFile) {
					final IFile file = (IFile) resource;
					if (file.getName().endsWith(".diagram")) { //$NON-NLS-1$
						ret.add(file);
					}
				}
			}
		} catch (final CoreException e) {
			e.printStackTrace();
		}
		return ret;
	}

	private static Diagram getDiagramFromFile(IFile file, ResourceSet resourceSet) {
		// Get the URI of the model file.
		final URI resourceURI = getFileURI(file, resourceSet);

		// Demand load the resource for this file.
		Resource resource;
		try {
			resource = resourceSet.getResource(resourceURI, true);
			if (resource != null) {
				// does resource contain a diagram as root object?
				final EList<EObject> contents = resource.getContents();
				for (final EObject object : contents) {
					if (object instanceof Diagram) {
						return (Diagram) object;
					}
				}
			}
		} catch (final WrappedException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static URI getFileURI(IFile file, ResourceSet resourceSet) {
		final String pathName = file.getFullPath().toString();
		URI resourceURI = URI.createFileURI(pathName);
		resourceURI = resourceSet.getURIConverter().normalize(resourceURI);
		return resourceURI;
	}
	
    private static int gridSize = 10;
	private static boolean snapToGrid = true;
	private static String diagramTypeName = "CSMDiagram";

    public static void openDiagram(final Resource diagramResource) {
        String path = diagramResource.getURI().toPlatformString(true);
        IResource workspaceResource = ResourcesPlugin.getWorkspace().getRoot()
                .findMember(new Path(path));
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		
        if (workspaceResource instanceof IFile) {
            try {
				page.openEditor(new FileEditorInput((IFile) workspaceResource), "eu.cloudscaleproject.env.diagram.DiagramEditor");
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
	public static Resource createDiagram(final URI diagramURI, final URI modelURI) {
        // create a resource set and editing domain
        TransactionalEditingDomain editingDomain = GraphitiUi.getEmfService()
                .createResourceSetAndEditingDomain();
        ResourceSet resourceSet = editingDomain.getResourceSet();
        CommandStack commandStack = editingDomain.getCommandStack();
        // create resources for the diagram and domain model files
        final Resource diagramResource = resourceSet.createResource(diagramURI);
        final Resource modelResource = resourceSet.createResource(modelURI);
        if (diagramResource != null && modelResource != null) {
            commandStack.execute(new RecordingCommand(editingDomain) {
                @Override
                protected void doExecute() {
                    createModel(diagramResource, diagramURI.lastSegment(), modelResource,
                            modelURI.lastSegment());
                }
            });

            try {
                modelResource.save(createSaveOptions());
                diagramResource.save(createSaveOptions());
            } catch (IOException exception) {
                IStatus status = new Status(IStatus.ERROR, "de.cau.cs.rtprak.turing.graphiti",
                        "Unable to store model and diagram resources", exception);
                StatusManager.getManager().handle(status);
            }
            setCharset(WorkspaceSynchronizer.getFile(modelResource));
            setCharset(WorkspaceSynchronizer.getFile(diagramResource));
        }
        return diagramResource;
    }

    /**
     * Creates save options for the resources.
     * 
     * @return new save options
     */
    public static Map<?, ?> createSaveOptions() {
        HashMap<String, Object> saveOptions = new HashMap<String, Object>();
        saveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
        saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED,
                Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
        return saveOptions;
    }

    /**
     * Set the character set for the given file to UTF-8.
     * 
     * @param file
     *            a file
     */
    public static void setCharset(final IFile file) {
        try {
            if (file != null) {
                file.setCharset("UTF-8", new NullProgressMonitor());
            }
        } catch (CoreException e) {
            StatusManager.getManager().handle(e, "de.cau.cs.rtprak.turing.graphiti");
        }
    }
    
    private static void createModel(final Resource diagramResource, final String diagramName,
            final Resource modelResource, final String modelName) {
        modelResource.setTrackingModification(true);
        EObject domainModel;

        try
        {
        	modelResource.load(null);
        }
        catch (Exception e)
        {
        	//ignore if model don't exist jet
        	//e.printStackTrace();
        }
        if (!modelResource.getContents().isEmpty())
        {
        	domainModel = modelResource.getContents().get(0);
        }
        else
        {
        	domainModel = OverviewUtil.createOverviewModel();
        	modelResource.getContents().add(domainModel);
        }

        diagramResource.setTrackingModification(true);
        Diagram diagram = Graphiti.getPeCreateService().createDiagram(diagramTypeName, diagramName,
               gridSize, snapToGrid);
        PictogramLink link = PictogramsFactory.eINSTANCE.createPictogramLink();
        link.setPictogramElement(diagram);
        link.getBusinessObjects().add(domainModel);
        diagramResource.getContents().add(diagram);
    }
}
