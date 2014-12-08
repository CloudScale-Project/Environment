package eu.cloudscaleproject.env.analyser.editors.composite;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.celleditor.AdapterFactoryTreeEditor;
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
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.palladiosimulator.edp2.models.measuringpoint.provider.MeasuringpointItemProviderAdapterFactory;
import org.palladiosimulator.experimentautomation.experiments.provider.ExperimentsItemProviderAdapterFactory;
import org.palladiosimulator.simulizar.pms.provider.PmsItemProviderAdapterFactory;

import de.uka.ipd.sdq.pcm.allocation.util.AllocationAdapterFactory;
import de.uka.ipd.sdq.pcm.repository.util.RepositoryAdapterFactory;
import de.uka.ipd.sdq.pcm.seff.util.SeffAdapterFactory;
import de.uka.ipd.sdq.pcm.system.util.SystemAdapterFactory;
import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;

public class ConfigAlternativeTreeviewComposite extends Composite{

	private ConfAlternative alternative;
	
	private final Tree tree;
	private final TreeViewer treeViewer;
	
	private ComposedAdapterFactory adapterFactory;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ConfigAlternativeTreeviewComposite(IProject project, ConfAlternative ca, Composite parent, int style) {
		super(parent, style);
		this.alternative = ca;
		
		setLayout(new GridLayout(1, false));
		
		this.tree = new Tree(this, SWT.BORDER | SWT.V_SCROLL);
		this.tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		this.treeViewer = new TreeViewer(tree);
		this.treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				ISelection s = treeViewer.getSelection();
				Object element = ((StructuredSelection)s).getFirstElement();
				if (element instanceof EObject) {
					EObject eo = (EObject)element;
					Resource res = eo.eResource();
					IFile file = ExplorerProjectPaths.getFileFromEmfResource(res);
					
					String path = res.getURI().lastSegment() + "_diagram";
					IFile diagramFile = file.getParent().getParent().getFile(new Path(path));
					
					if(diagramFile.exists()){
						file = diagramFile;
					}
					
					try {
						IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), file);
					} catch (PartInitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
				
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		
		adapterFactory.addAdapterFactory(new RepositoryAdapterFactory());
		adapterFactory.addAdapterFactory(new SystemAdapterFactory());
		adapterFactory.addAdapterFactory(new AllocationAdapterFactory());
		adapterFactory.addAdapterFactory(new SeffAdapterFactory());
		adapterFactory.addAdapterFactory(new MeasuringpointItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new PmsItemProviderAdapterFactory());
		//TODO: check if this is the right factory for experiment automation
		adapterFactory.addAdapterFactory(new ExperimentsItemProviderAdapterFactory());
		
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new EcoreAdapterFactory());
				
		this.treeViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
		this.treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		//this.treeViewer.addFilter(new ModelViewFilter());
		
		new AdapterFactoryTreeEditor(tree, adapterFactory);
		
		updateTreeview();
	}
	
	private void updateTreeview(){
		
		alternative.load();
		
		if(!this.tree.isDisposed()){
			this.treeViewer.setInput(alternative.getResourceSet());
			this.treeViewer.expandToLevel(2);
			this.treeViewer.refresh();
		}
	}
	
	public void update(){
		alternative.load();
		if(!this.isDisposed()){
			updateTreeview();
		}
		
		super.update();
	}
}
