package eu.cloudscaleproject.env.analyser.editors.composite;

import java.io.File;

import javax.inject.Inject;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.edp2.datastream.IDataSource;
import org.palladiosimulator.edp2.datastream.chaindescription.ChainDescription;
import org.palladiosimulator.edp2.datastream.edp2source.Edp2DataTupleDataSource;
import org.palladiosimulator.edp2.impl.RepositoryManager;
import org.palladiosimulator.edp2.models.ExperimentData.Measurement;
import org.palladiosimulator.edp2.models.ExperimentData.RawMeasurements;
import org.palladiosimulator.edp2.models.Repository.LocalDirectoryRepository;
import org.palladiosimulator.edp2.models.Repository.Repository;
import org.palladiosimulator.edp2.visualization.IVisualisationInput;
import org.palladiosimulator.edp2.visualization.wizards.DefaultViewsWizard;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.common.ui.IRefreshable;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.util.CustomAdapterFactory;

public class ResultAlternativeComposite extends Composite implements IRefreshable{
	
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
		
		//TODO: make EDP2 editor/view accessible to other modules
		
		ComposedAdapterFactory adapterFactory = new CustomAdapterFactory();
		
		this.treeViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
		this.treeViewer.setLabelProvider(new org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider(
				new AdapterFactoryLabelProvider.StyledLabelProvider(adapterFactory, this.treeViewer)));
		
		this.treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				ISelection s = treeViewer.getSelection();
				Object element = ((StructuredSelection)s).getFirstElement();
				if (element instanceof Measurement) {
					openChainSelectionDialog(element);
					
					//for now just open edp2 perspective
					//TODO: improve/better integrate results tree-view
					commandExecutor.execute("eu.cloudscaleproject.env.analyser.openAnalyserResultsPerspective");
				}
			}
		});
		
		loadModel();
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
			
		LocalDirectoryRepository rep = findOrInitRepository(alternative.getResource().getLocation().toOSString());
		
		if(!rep.getExperimentGroups().isEmpty()){
			this.treeViewer.setInput(rep.getExperimentGroups().get(0));
			this.treeViewer.expandToLevel(3);
			this.treeViewer.refresh();
		}
		else{
			this.treeViewer.setInput(null);
			this.treeViewer.refresh();
		}
	}
	
	/**
     * Initializes the repository in which the data will be stored.
     * 
     * @param directory
     *            Path to directory in which the data should be stored.
     * @return the initialized repository.
     */
    private LocalDirectoryRepository findOrInitRepository(final String directory) {
    	File dir = new File(directory);
        final LocalDirectoryRepository repo = RepositoryManager.initializeLocalDirectoryRepository(dir);
        
        /*
         * Add repository to a (optional) central directory of repositories. This can be useful to
         * manage more than one repository or have links between different existing repositories. A
         * repository must be connected to an instance of Repositories in order to be opened.
         */
        
        String path = URI.createFileURI(dir.getAbsolutePath()).toString();
        
        for(Repository rep : RepositoryManager.getCentralRepository().getAvailableRepositories()){
        	if(rep instanceof LocalDirectoryRepository){
        		LocalDirectoryRepository ldr = (LocalDirectoryRepository)rep;
        		if(ldr.getUri().equals(path)){
        			return ldr;
        		}
        	}
        }
        
        //if the repository for the specified path is not found
        RepositoryManager.addRepository(RepositoryManager.getCentralRepository(), repo);
        return repo;
    }
	
	public void refresh(){
		loadModel();
	}
	
	/*
	 * This is copy/paste from EDP2 NavigatorDoubleClickListener in a attempt to open graphs for the measurements.
	 * Unfortunately the following code do not work (edp2Source.getDataStream() throws an exception).    
	 * 
	 * 
	 */
    private void openChainSelectionDialog(final Object selectedObject) {
        final Measurement measurements = (Measurement) selectedObject;
        final RawMeasurements rawMeasurements = measurements.getMeasurementRanges().get(0).getRawMeasurements();

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
}
