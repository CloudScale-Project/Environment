/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    cbrand - Bug 377475 - Fix AbstractCustomFeature.execute and canExecute
 *
 * </copyright>
 *
 *******************************************************************************/
package eu.cloudscaleproject.env.csm.diagram.features;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import eu.cloudscaleproject.env.csm.Csm;
import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.PlatformService;
import eu.cloudscaleproject.env.csm.architecture.Proxy;
import eu.cloudscaleproject.env.csm.architecture.SoftwareService;
import eu.cloudscaleproject.env.csm.core.Entity;
import eu.cloudscaleproject.env.csm.diagram.Util;
import eu.cloudscaleproject.env.csm.diagram.editor.application.SoftwareServiceEditor;
import eu.cloudscaleproject.env.csm.diagram.editor.application.SoftwareServiceEditorInput;
import eu.cloudscaleproject.env.csm.diagram.editor.platform.PlatformServiceEditor;
import eu.cloudscaleproject.env.csm.diagram.editor.platform.PlatformServiceEditorInput;
import eu.cloudscaleproject.env.csm.diagram.editor.system.SystemEditor;
import eu.cloudscaleproject.env.csm.diagram.editor.system.SystemEditorInput;

public class EditorFeature extends AbstractCustomFeature {

	private boolean hasDoneChanges = false;

	public EditorFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public String getName() {
		return "Editor"; //$NON-NLS-1$
	}

	@Override
	public String getDescription() {
		return "Editor for selected element"; //$NON-NLS-1$
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		
		boolean ret = false;
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length == 1) {
			Object bo = getBusinessObjectForPictogramElement(pes[0]);
			if (bo instanceof Entity) {
				ret = true;
			}
		}
		else
		{
			System.out.println("Double click elsewhere.");
		}
		return ret;
	}

	public void execute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length == 1) {
			Object bo = getBusinessObjectForPictogramElement(pes[0]);
			System.out.println(getAllBusinessObjectsForPictogramElement(pes[0]).length);
			System.out.println(pes[0]);
			System.out.println(bo.getClass());
			
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			IWorkbenchPage page = window.getActivePage();
			
			if (bo instanceof PlatformService) {
				
				Util.openEditor(new PlatformServiceEditorInput((PlatformService)bo), PlatformServiceEditor.ID);
			}
			else if (bo instanceof SoftwareService) {
				
				Util.openEditor(new SoftwareServiceEditorInput((SoftwareService)bo), SoftwareServiceEditor.ID);
			}
			else if (bo instanceof CloudEnvironment || bo instanceof Proxy)
			{
				System.out.println("Double click on cloudprovider or connector");
				Csm csm =  (Csm)EcoreUtil.getRootContainer((Entity)bo);
				try {
					page.openEditor(new SystemEditorInput(csm), SystemEditor.ID);
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (bo instanceof Proxy)
			{
				System.out.println("Double click on connector");
			}

		}
		else 
		{
			System.out.println("Double click outside: "+pes);
		}
		
		
		
		
	}

	@Override
	public boolean hasDoneChanges() {
		return this.hasDoneChanges;
	}
}
