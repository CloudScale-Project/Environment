package eu.cloudscaleproject.env.analyser.editors.composite;

import java.io.IOException;

import javax.inject.Inject;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.palladiosimulator.edp2.models.ExperimentData.Measurements;
import org.palladiosimulator.edp2.models.ExperimentData.provider.ExperimentDataItemProviderAdapterFactory;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;

public class ResultAlternativeComposite extends Composite{
	
	private final Tree tree;
	private final TreeViewer treeViewer;
	private final EditorInputFolder alternative;
	
	@Optional @Inject
	private CommandExecutor commandExecutor;
	
	public ResultAlternativeComposite(EditorInputFolder alternative, Composite parent, int style) {
		super(parent, style);
		
		CloudscaleContext.inject(this);
		this.alternative = alternative;

		setLayout(new GridLayout(1, false));
		
		this.tree = new Tree(this, SWT.BORDER);
		this.tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		this.treeViewer = new TreeViewer(tree);
		
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ExperimentDataItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new EcoreAdapterFactory());
		
		this.treeViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
		this.treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		//this.treeViewer.setContentProvider(new DatasourceListContentProvider());
		//this.treeViewer.setLabelProvider(new DatasourceListLabelProvider());
		
		this.treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				ISelection s = treeViewer.getSelection();
				Object element = ((StructuredSelection)s).getFirstElement();
				if (element instanceof Measurements) {
					//openChainSelectionDialog(element);
					
					//for now just open edp2 perspective
					//TODO: improve/better integrate results tree-view
					commandExecutor.execute("eu.cloudscaleproject.env.analyser.openAnalyserResultsPerspective");
				}
			}
		});
		
		loadModel();
	}
	
	@Override
	public void update() {
		loadModel();
		super.update();
	}
	
	private void loadModel(){
		
		if(!alternative.getResource().exists()){
			return;
		}
		
		try {
			alternative.getResource().refreshLocal(IFolder.DEPTH_INFINITE, null);
		} catch (CoreException e1) {
			e1.printStackTrace();
		}
		
		IFile modelFile = null;
		try {
			for(IResource res : alternative.getResource().members()){
				if("edp2".equals(res.getFileExtension()) && res instanceof IFile){
					modelFile = (IFile)res;
				}
			}
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(modelFile != null){
			ResourceSet resSet = new ResourceSetImpl();
			Resource resource = resSet.createResource(
					URI.createPlatformResourceURI(modelFile.getFullPath().toString(), true));
			try {
				resource.load(resSet.getLoadOptions());
				this.treeViewer.setInput(resource.getContents().get(0));
				this.treeViewer.expandToLevel(3);
				this.treeViewer.refresh();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void refresh(){
		loadModel();
	}
	
	/*
	 * This is copy/paste from EDP2 NavigatorDoubleClickListener in a attempt to open graphs for the measurements.
	 * Unfortunately the following code do not work (edp2Source.getDataStream() throws an exception).    
	 * 
    private void openChainSelectionDialog(final Object selectedObject) {
        final Measurements measurements = (Measurements) selectedObject;
        final RawMeasurements rawMeasurements = measurements.getMeasurementsRanges().get(0).getRawMeasurements();

        if (rawMeasurements != null && !rawMeasurements.getDataSeries().isEmpty()) {
            final IDataSource edp2Source = new Edp2DataTupleDataSource(rawMeasurements);
            final int dataStreamSize = edp2Source.getDataStream().size();
            edp2Source.getDataStream().close();

            if (dataStreamSize > 0) {
                openWizard(edp2Source);
            }
        } else {
            throw new RuntimeException("Empty Measurements!");
        }
    }

    // open the wizard with reference to the selected source
    // it shows possible visualizations, which are instances of
    // DefaultSequence
    private void openWizard(final IDataSource edp2Source) {
        final DefaultViewsWizard wizard = new DefaultViewsWizard(edp2Source);
        final WizardDialog wdialog = new WizardDialog(PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getShell(), wizard);
        wdialog.open();

        if (wdialog.getReturnCode() == Window.OK) {
            openEditor(edp2Source, wizard);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void openEditor(final IDataSource edp2Source, final DefaultViewsWizard wizard) {
        final ChainDescription chainDescription = wizard.getSelectedDefault();
        final IVisualisationInput input = (IVisualisationInput) chainDescription.getVisualizationInput();
        input.addInput(input.createNewInput(chainDescription.attachRootDataSource(edp2Source)));
        try {
            final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                    .getActivePage();
            page.openEditor(input, "org.palladiosimulator.edp2.visualization.editors.JFreeChartEditor");
        } catch (final PartInitException e) {
            throw new RuntimeException(e);
        }
    }
    */
}
