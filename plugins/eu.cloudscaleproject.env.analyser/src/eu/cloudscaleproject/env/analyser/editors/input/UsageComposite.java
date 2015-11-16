package eu.cloudscaleproject.env.analyser.editors.input;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.palladiosimulator.pcm.usagemodel.UsageModel;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.scaledl.usageevolution.Usage;

import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import tools.descartes.dlim.Sequence;

public class UsageComposite extends Composite implements IRefreshable{
	
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(UsageComposite.class.getName());

	private ComboViewer scenarioComboViewer;
	private ComboViewer limboComboViewer;

	private final InputAlternative alternative;
	private final Usage usage;
	
	private boolean refreshInProgress = false;
	
	private final ISelectionChangedListener scenarioComboListener = new ISelectionChangedListener() {
		
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			
			if(refreshInProgress){
				return;
			}
			
			final StructuredSelection ss = (StructuredSelection)event.getSelection();
			
			alternative.getEditingDomain().getCommandStack().execute(new AbstractCommand() {
				
				UsageScenario last = null;
				
				@Override
				public void undo() {
					UsageComposite.this.usage.setScenario(last);
				}
				
				@Override
				public void redo() {
					UsageScenario us = (UsageScenario)ss.getFirstElement();
					UsageComposite.this.usage.setScenario(us);
				}
				
				@Override
				public boolean canExecute() {
					return true;
				}
				
				@Override
				public void execute() {
					last = UsageComposite.this.usage.getScenario();
					UsageScenario us = (UsageScenario)ss.getFirstElement();
					UsageComposite.this.usage.setScenario(us);
				}
			});
		}
	};
	
	private final ISelectionChangedListener limboComboListener = new ISelectionChangedListener() {
		
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			
			if(refreshInProgress){
				return;
			}
			
			final StructuredSelection ss = (StructuredSelection)event.getSelection();
			
			alternative.getEditingDomain().getCommandStack().execute(new AbstractCommand() {
				
				Sequence last = null;
				
				@Override
				public void undo() {
					UsageComposite.this.usage.setLoadEvolution(last);
				}
				
				@Override
				public void redo() {
					Sequence sequence = (Sequence)ss.getFirstElement();
					UsageComposite.this.usage.setLoadEvolution(sequence);
				}
				
				@Override
				public boolean canExecute() {
					return true;
				}
				
				@Override
				public void execute() {
					last = UsageComposite.this.usage.getLoadEvolution();
					
					IEditorInputResource eir = (IEditorInputResource)ss.getFirstElement();
					if(eir instanceof EditorInputEMF){
						EditorInputEMF eie = (EditorInputEMF)eir;
						EObject eo = eie.getModelRootObject(ToolchainUtils.KEY_FILE_LIMBO);
						UsageComposite.this.usage.setLoadEvolution((Sequence)eo);						
					}					
				}
			});
		}
	};
	
	public UsageComposite(InputAlternative alt, Usage usage, Composite parent, int style) {
		super(parent, style);
		
		this.alternative = alt;
		this.usage = usage;
		
		GridLayout layout = new GridLayout(2, false);
		setLayout(layout);
		
		//usage scenario selection
		Label lblScenario = new Label(this, SWT.NONE);
		lblScenario.setText("Select usage scenario: ");
		{
			GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
			lblScenario.setLayoutData(gridData);
		}
		
		scenarioComboViewer = new ComboViewer(this, SWT.READ_ONLY);
		scenarioComboViewer.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		scenarioComboViewer.setLabelProvider(new LabelProvider() {
			
			@Override
			public String getText(Object element) {
				if (element instanceof UsageScenario) {
					return ((UsageScenario) element).getEntityName();
				}
				return super.getText(element);
			}
			
		});
		scenarioComboViewer.setContentProvider(new ArrayContentProvider());
		scenarioComboViewer.setInput(getUsageScenarios());
		
		//limbo model selection
		Label lblLimbo = new Label(this, SWT.NONE);
		lblLimbo.setText("Select limbo alternative: ");
		{
			GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
			lblScenario.setLayoutData(gridData);
		}
		
		limboComboViewer = new ComboViewer(this, SWT.READ_ONLY);
		limboComboViewer.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		limboComboViewer.setLabelProvider(new LabelProvider() {
			
			@Override
			public String getText(Object element) {
				if (element instanceof IEditorInputResource) {
					return ((IEditorInputResource) element).getName();
				}
				return super.getText(element);
			}
			
		});
		limboComboViewer.setContentProvider(new ArrayContentProvider());
		limboComboViewer.setInput(getLimboAlternatives());
		
		Button button = new Button(this, SWT.NONE);
		button.setText("Open limbo editor");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				IStructuredSelection selection = (IStructuredSelection)limboComboViewer.getSelection();
				if(selection != null && selection.getFirstElement() instanceof IEditorInputResource){
					ResourceRegistry.getInstance().openResourceEditor((IEditorInputResource)selection.getFirstElement());
				}
				else{
					MessageDialog.openInformation(getShell(), "Information", "Empty selection! Please select Usage evolution first.");
				}
				super.widgetSelected(e);
			}
		});
		
		limboComboViewer.addSelectionChangedListener(limboComboListener);
		scenarioComboViewer.addSelectionChangedListener(scenarioComboListener);
	}
	
	private List<UsageScenario> getUsageScenarios(){
		List<EObject> usageModels = alternative.getModelRootObjects(ToolchainUtils.KEY_FILE_USAGE);
		
		List<UsageScenario> scenarios = new ArrayList<>();
		for(EObject eo : usageModels){
			if(eo instanceof UsageModel){
				UsageModel um = (UsageModel)eo;
				scenarios.addAll(um.getUsageScenario_UsageModel());
			}
		}
		return scenarios;
	}
	
	private List<IEditorInputResource> getLimboAlternatives(){
		
		List<IEditorInputResource> out = new ArrayList<IEditorInputResource>();
		
		ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(alternative.getProject(), CSToolResource.USAGEEVOLUTION);
		for(IEditorInputResource eir : rp.getResources()){
			out.add(eir);
		}
		
		return out;
	}
	
	private IEditorInputResource getLimboAlternative(Sequence sequence){
		
		if(sequence == null){
			return null;
		}
		
		IResource limboResource = ExplorerProjectPaths.getFileFromEmfResource(sequence.eResource());
				
		ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(alternative.getProject(), CSToolResource.USAGEEVOLUTION);
		for(IEditorInputResource eir : rp.getResources()){
			if(eir instanceof EditorInputEMF){
				EditorInputEMF eie = (EditorInputEMF)eir;
				if (!eie.isLoaded()) eie.load();

				IResource res = eie.getSubResource(ToolchainUtils.KEY_FILE_LIMBO);

				if (limboResource.equals(res)) return eir;
			}
		}
		
		return null;
	}
	
	/*
	public void openLimboEditor(){
		IFile limboFile = ExplorerProjectPaths.getFileFromEmfResource(usage.getLoadEvolution().eResource());
		
		if(limboFile == null){
			logger.warning("clear(): Limbo model file not set!");
			return;
		}
		
		try {
			IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), limboFile);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
	*/

	@Override
	public void refresh() {
		
		try{
			refreshInProgress = true;
			
			scenarioComboViewer.setInput(getUsageScenarios());
			limboComboViewer.setInput(getLimboAlternatives());
			
			if(usage.getScenario() != null){
				scenarioComboViewer.setSelection(new StructuredSelection(usage.getScenario()));
			}
			
			IEditorInputResource eir = getLimboAlternative(this.usage.getLoadEvolution());
			if(eir != null){
				if(usage.getLoadEvolution() != null){
					limboComboViewer.setSelection(new StructuredSelection(eir));
				}
			}
		}
		finally{
			refreshInProgress = false;
		}
		
	}
}