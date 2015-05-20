package eu.cloudscaleproject.env.analyser.editors.config;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.IEMFListProperty;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.scaledl.usageevolution.Usage;

import tools.descartes.dlim.Sequence;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import de.uka.ipd.sdq.pcm.usagemodel.UsagemodelPackage;
import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class UsageComposite extends Composite implements IRefreshable{
	
	private static final Logger logger = Logger.getLogger(UsageComposite.class.getName());

	private ComboViewer scenarioComboViewer;
	private ComboViewer limboComboViewer;

	private final ConfAlternative alternative;
	private final Usage usage;
	
	private final PropertyChangeListener inputSetListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if(ConfAlternative.PROP_INPUT_ALT_SET.equals(evt.getPropertyName())){
				IEMFListProperty usageScenListProp = EMFProperties.list(UsagemodelPackage.Literals.USAGE_MODEL__USAGE_SCENARIO_USAGE_MODEL);
				scenarioComboViewer.setInput(usageScenListProp.observe(alternative.getUsedUsageModel()));
				scenarioComboViewer.refresh();
			}
		}
	};
	
	public UsageComposite(ConfAlternative alt, Usage usage, Composite parent, int style) {
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
		scenarioComboViewer.setContentProvider(new ObservableListContentProvider());
		IEMFListProperty usageScenListProp = EMFProperties.list(UsagemodelPackage.Literals.USAGE_MODEL__USAGE_SCENARIO_USAGE_MODEL);
		scenarioComboViewer.setInput(usageScenListProp.observe(alternative.getUsedUsageModel()));
		
		alternative.addPropertyChangeListener(inputSetListener);
		addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				alternative.removePropertyChangeListener(inputSetListener);
			}
		});
		
		//limbo model selection
		Label lblLimbo = new Label(this, SWT.NONE);
		lblLimbo.setText("Select usage evolution: ");
		{
			GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
			lblScenario.setLayoutData(gridData);
		}
		
		limboComboViewer = new ComboViewer(this, SWT.READ_ONLY);
		limboComboViewer.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		limboComboViewer.setLabelProvider(new LabelProvider() {
			
			@Override
			public String getText(Object element) {
				if (element instanceof Sequence) {
					return ((Sequence) element).getName();
				}
				return super.getText(element);
			}
			
		});
		limboComboViewer.setContentProvider(new ArrayContentProvider());
		limboComboViewer.setInput(getSequences());
		
		Button button = new Button(this, SWT.NONE);
		button.setText("Edit arrival rate");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openLimboEditor();
				super.widgetSelected(e);
			}
		});
		
		scenarioComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection ss = (StructuredSelection)event.getSelection();
				UsageScenario us = (UsageScenario)ss.getFirstElement();
				UsageComposite.this.usage.setScenario(us);
			}
		});
		
		limboComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection ss = (StructuredSelection)event.getSelection();
				Sequence sequence = (Sequence)ss.getFirstElement();
				UsageComposite.this.usage.setLoadEvolution(sequence);
			}
		});
		
	}
	
	private List<Sequence> getSequences(){
		List<Sequence> out = new ArrayList<Sequence>();
		
		ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(alternative.getProject(), ToolchainUtils.USAGEEVOLUTION_ID);
		for(IEditorInputResource eir : rp.getResources()){
			if(eir instanceof EditorInputEMF){
				EditorInputEMF alt = (EditorInputEMF)eir;
				
				for(EObject eo : alt.getModelRoot(ToolchainUtils.KEY_FILE_LIMBO)){
					if(eo instanceof Sequence){
						out.add((Sequence)eo);
					}
				}
				
			}
		}
		
		return out;
	}
	
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

	@Override
	public void refresh() {
		limboComboViewer.setInput(getSequences());
		
		scenarioComboViewer.setSelection(new StructuredSelection(usage.getScenario()));
		limboComboViewer.setSelection(new StructuredSelection(usage.getLoadEvolution()));
	}
}