package eu.cloudscaleproject.env.analyser.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.analyser.Activator;
import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.analyser.wizard.pages.ImportAlternativeOptionsPage;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputJob;
import eu.cloudscaleproject.env.toolchain.util.OpenAlternativeUtil;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeNamePage;
import eu.cloudscaleproject.env.toolchain.wizard.pages.ExternalModelsSelectionPage;

public class ExternalImportInputWizard extends Wizard{

	private IProject project;
	
	private AlternativeNamePage nameSelectionPage;
	private ExternalModelsSelectionPage importModelSelectionPage;
	private ImportAlternativeOptionsPage optionsPage;
	
	//limit selection to only one usage model
	private final ICheckStateListener modelCheckStateListener = new ICheckStateListener() {
		
		@Override
		public void checkStateChanged(CheckStateChangedEvent event) {
			/*
			Object el = event.getElement();			
			EObject root = null;
			
			//get root object
			if (el instanceof EObject){
				root = (EObject)el;
			}
			if(el instanceof Resource){
				Resource r = (Resource)el;
				root = r.getContents().isEmpty() ? null : r.getContents().get(0);
			}
			
			if(root instanceof UsageModel){
				importModelSelectionPage.selectResource(ModelType.USAGE, false, false);
				importModelSelectionPage.selectResource(root.eResource(), event.getChecked());
			}
			*/
		}
	};
	
	public ExternalImportInputWizard(IProject project) {
		
		this.project = project;
		
		setWindowTitle("Analyser - External project import");
		
		nameSelectionPage = new AlternativeNamePage(ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.ANALYSER_INPUT));

		importModelSelectionPage = new ExternalModelsSelectionPage(null,ModelType.GROUP_PCM_EXTENDED, modelCheckStateListener);
		
		optionsPage = new ImportAlternativeOptionsPage();
	}
	
	@Override
	public void addPages() {
		addPage(nameSelectionPage);
		addPage(importModelSelectionPage);
		addPage(optionsPage);
	}

	@Override
	public boolean performFinish() {
		
		String altName = nameSelectionPage.getName();
		final Resource[] selectedResources = importModelSelectionPage.getSelectedResources();
		final Resource[] selectedDiagramResources = importModelSelectionPage.getSelectedDiagramResources();
		final boolean copyIntoProject = optionsPage.getCopyIntoProjectParam();
		
		ResourceProvider provider = ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.ANALYSER_INPUT);
		final InputAlternative alternative = (InputAlternative)provider.createNewResource(altName, null);
		
		EditorInputJob job = new EditorInputJob("Creating empty models.", alternative) {
			
			@Override
			public IStatus execute(IProgressMonitor monitor) {

				int tasks = copyIntoProject ? 6 : 4;
				monitor.beginTask("Importing models...", tasks + selectedResources.length * 6);

				if (copyIntoProject)
				{
					monitor.subTask("Copying models...");
					ExplorerProjectPaths.copyEMFResources(alternative.getResource(), selectedResources, monitor);
					monitor.worked(1);
					ExplorerProjectPaths.copyEMFResources(alternative.getResource(), selectedDiagramResources, monitor);
					monitor.worked(1);
				}
				
				for (Resource resource : selectedResources)
				{
					IFile f = ExplorerProjectPaths.getFileFromEmfResource(resource);
					alternative.addSubResourceModel(f);
				}
				monitor.worked(1);
				
				monitor.subTask("Validating alternative...");
				alternative.validate();
				monitor.worked(1);
				
				monitor.subTask("Saving alternative...");
				alternative.save();
				monitor.worked(1);
				
				monitor.subTask("Updating UI...");

				OpenAlternativeUtil.openAlternative(alternative);

				monitor.worked(1);
				monitor.done();
				
				return new Status(IStatus.OK, Activator.PLUGIN_ID, "Empty models have been created.");
			}
		};
		
		job.setUser(true);
		job.schedule();
		
		return true;
	}
	
	@Override
	public boolean canFinish()
	{
		if (getContainer().getCurrentPage() == getPages()[getPageCount()-1])
			return true;

		return false;
	}

}
