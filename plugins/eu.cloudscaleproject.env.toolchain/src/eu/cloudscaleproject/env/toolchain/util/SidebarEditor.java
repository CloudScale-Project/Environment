package eu.cloudscaleproject.env.toolchain.util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputJob;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.wizard.CloneAlternativeWizard;
import eu.cloudscaleproject.env.toolchain.wizard.CreateAlternativeWizard;

public class SidebarEditor extends AbstractSidebarEditor
{

	private SidebarContentProvider contentProvider = null;
	private ResourceProvider resourceProvider = null;

	private final PropertyChangeListener rcl = new PropertyChangeListener()
	{

		@Override
		public void propertyChange(PropertyChangeEvent evt)
		{

			if (contentProvider == null)
			{
				return;
			}

			if (ResourceProvider.PROP_RESOURCE_ADDED.equals(evt.getPropertyName()))
			{
				IEditorInputResource res = (IEditorInputResource) evt.getNewValue();
				String section = contentProvider.getSection(res);
				SidebarEditor.this.addSidebarEditor(res, section);
			}
			if (ResourceProvider.PROP_RESOURCE_REMOVED.equals(evt.getPropertyName()))
			{
				IEditorInputResource res = (IEditorInputResource) evt.getOldValue();
				SidebarEditor.this.removeSidebarEditor(res);
			}
		}
	};

	public SidebarEditor(Composite sidebar, Composite area)
	{
		super(sidebar, area);
	}

	public void setContentProvider(final SidebarContentProvider compositeProvider)
	{
		this.contentProvider = compositeProvider;
		init();
	}

	public void setResourceProvider(final ResourceProvider resourceProvider)
	{
		this.resourceProvider = resourceProvider;
		
		EditorInputJob job = new EditorInputJob("Loading Dashboard") {
			
			@Override
			public IStatus execute(IProgressMonitor monitor) {
				
				monitor.beginTask("Loading Dasboard...", IProgressMonitor.UNKNOWN);
				
				for(IEditorInputResource eir : resourceProvider.getResources()){
					
					if(!eir.isLoaded()){
						eir.load(monitor);
					}
					eir.validate(monitor);
					
					if(eir instanceof IValidationStatusProvider){
						IValidationStatusProvider vp = (IValidationStatusProvider)eir;
						
						ValidationDiagramService.showDiagram(resourceProvider.getProject());
						ValidationDiagramService.showStatus(resourceProvider.getProject(), vp);
						break;
					}
				}
				
				return Status.OK_STATUS;
			}
		};
		
		job.setUser(true);
		job.schedule();
		
		init();
	}

	@Override
	public void init()
	{
		if (resourceProvider != null && contentProvider != null)
		{
			super.init();
			resourceProvider.addListener(rcl);
		}
	}

	@Override
	public Composite createInputComposite(IEditorInput input, Composite parent, int style)
	{
		if (contentProvider == null)
		{
			return null;
		}

		return contentProvider.createComposite(parent, style, (IEditorInputResource) input);
	}

	@Override
	public List<IEditorInput> getInputs(String section)
	{
		List<IEditorInput> resources = new ArrayList<IEditorInput>();

		if (resourceProvider == null)
		{
			return resources;
		}

		for (IEditorInputResource res : resourceProvider.getResources())
		{
			if (section == null)
			{
				if (contentProvider.getSection(res) == null)
				{
					resources.add(res);
				}
			} else if (section.equals(contentProvider.getSection(res)))
			{
				resources.add(res);
			}
		}
		return resources;
	}

	@Override
	public String[] getSidebarSections()
	{
		if (contentProvider == null)
		{
			return new String[] {};
		}
		return contentProvider.getSections();
	}

	@Override
	public void handleNewInput(IEditorInput selected)
	{
		doHandleNewInput(selected);
	}

	public void doHandleNewInput(IEditorInput selected)
	{
		CreateAlternativeWizard createlternativeWizard = new CreateAlternativeWizard(resourceProvider.getProject(), resourceProvider);
		WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), createlternativeWizard);
		wizardDialog.open();
	}

	@Override
	public void handleNewInputFrom(final IEditorInput selected)
	{
		doHandleNewInputFrom(selected);
	}

	public void doHandleNewInputFrom(final IEditorInput selected)
	{
		if (resourceProvider.getResources().isEmpty()) return;

		CloneAlternativeWizard cloneAlternativeWizard = new CloneAlternativeWizard(resourceProvider);
		WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), cloneAlternativeWizard);
		wizardDialog.open();
	}

	@Override
	public void handleInputDelete(IEditorInput toDelete)
	{
		if (!(toDelete instanceof IEditorInputResource))
		{
			return;
		}

		removeSidebarEditor(toDelete);
		((IEditorInputResource)toDelete).delete();
	}

	@Override
	public void dispose()
	{
		if (resourceProvider != null)
		{
			List<IEditorInputResource> closedAlternatives = resourceProvider.getResources();
			HashSet<IEditorInputResource> openedAlternatives = ToolchainUtils.getOpenedAlternatives();
			closedAlternatives.removeAll(openedAlternatives);
			
			for(IEditorInputResource eir : closedAlternatives){
				eir.load();
			}
			
			resourceProvider.removeListener(rcl);
		}
		super.dispose();
	}
}
