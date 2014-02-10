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
package eu.cloudscaleproject.env.csm.diagram;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import eu.cloudscaleproject.env.csm.diagram.editor.system.SystemEditor;
import eu.cloudscaleproject.env.csm.diagram.editor.system.SystemEditorInput;

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

}
