package eu.cloudscaleproject.env.analyser.wizard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.wizard.Wizard;

import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.analyser.wizard.pages.ImportAlternativeOptionsPage;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.util.OpenAlternativeUtil;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeNamePage;
import eu.cloudscaleproject.env.toolchain.wizard.pages.ExternalModelsSelectionPage;

public class ExternalImportInputWizard extends Wizard{

	private IProject project;
	
	private AlternativeNamePage nameSelectionPage;
	private ExternalModelsSelectionPage importModelSelectionPage;
	private ImportAlternativeOptionsPage optionsPage;
	
	//auto-selection
	private final ICheckStateListener modelCheckStateListener = new ICheckStateListener() {
		
		@Override
		public void checkStateChanged(CheckStateChangedEvent event) {
			Object el = event.getElement();			
			EObject root = null;
			
			//get root object
			if (el instanceof EObject)
			{
				root = (EObject)el;
			}
			if(el instanceof Resource){
				Resource r = (Resource)el;
				root = r.getContents().isEmpty() ? null : r.getContents().get(0);
			}
			
			if(root instanceof UsageModel){
				importModelSelectionPage.selectModel(ModelType.USAGE, false, false);
				importModelSelectionPage.selectModel(root, event.getChecked());
			}
			
			//handle selection
			selectLinked(root, event.getChecked());
		}
	};
	
	public ExternalImportInputWizard(IProject project) {
		
		this.project = project;
		
		setWindowTitle("Analyser - External project import");
		
		nameSelectionPage = new AlternativeNamePage(ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.ANALYSER_INPUT_ID));

		importModelSelectionPage = new ExternalModelsSelectionPage(null,ModelType.GROUP_PCM_EXTENDED, modelCheckStateListener);
		
		optionsPage = new ImportAlternativeOptionsPage();
	}
	
	private void selectLinked(EObject object, boolean selectionState){
		
		List<EObject> selected = new ArrayList<EObject>();
		Iterator<EObject> iter = EcoreUtil.getAllContents(object, false);
		while(iter.hasNext()){
			EObject o = iter.next();
			
			{
				EObject root = EcoreUtil.getRootContainer(o, false);
				if(root != null && !selected.contains(root)){
					importModelSelectionPage.selectModel(root, selectionState);
					selected.add(root);
				}	
			}
			
			for(EStructuralFeature feature : o.eClass().getEAllStructuralFeatures()){
				Object child = o.eGet(feature, false);
				if(child instanceof InternalEObject){
					InternalEObject ieo = (InternalEObject)child;
					
					if(ieo.eProxyURI() == null
							|| ieo.eProxyURI().scheme().equals("pathmap")){
						continue;
					}
					
					EObject eo = o.eResource().getResourceSet().getEObject(ieo.eProxyURI(), false);
					
					if(eo != null){
						EObject root = EcoreUtil.getRootContainer(eo, false);
						if(root != null && !selected.contains(root)){
							importModelSelectionPage.selectModel(root, selectionState);
							selected.add(root);
							selectLinked(root, selectionState);
						}
					}
				}
			}
			
		}
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
		Resource[] selectedResources = importModelSelectionPage.getSelectedResources();
		Resource[] selectedDiagramResources = importModelSelectionPage.getSelectedDiagramResources();
		boolean copyIntoProject = optionsPage.getCopyIntoProjectParam();
		
		ResourceProvider provider = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.ANALYSER_INPUT_ID);
		InputAlternative alternative = (InputAlternative)provider.createNewResource(altName, null);
		
		if (copyIntoProject)
		{			
			ExplorerProjectPaths.copyEMFResources(alternative.getResource(), selectedResources);
			ExplorerProjectPaths.copyEMFResources(alternative.getResource(), selectedDiagramResources);
		}

		for (Resource resource : selectedResources)
		{
			IFile f = ExplorerProjectPaths.getFileFromEmfResource(resource);
			alternative.addSubResourceModel(f);
		}
		alternative.save();
		
		ValidationDiagramService.showStatus(project, alternative);
		OpenAlternativeUtil.openAlternative(alternative);
		
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
