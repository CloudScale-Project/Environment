package eu.cloudscaleproject.env.analyser.editors.composite;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
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
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.palladiosimulator.edp2.models.ExperimentData.util.ExperimentDataAdapterFactory;
import org.palladiosimulator.edp2.models.measuringpoint.provider.MeasuringpointItemProviderAdapterFactory;
import org.palladiosimulator.simulizar.pms.provider.PmsItemProviderAdapterFactory;
import org.palladiosimulator.simulizar.pms.util.PmsResourceImpl;

import de.uka.ipd.sdq.pcm.allocation.util.AllocationAdapterFactory;
import de.uka.ipd.sdq.pcm.allocation.util.AllocationResourceImpl;
import de.uka.ipd.sdq.pcm.repository.util.RepositoryAdapterFactory;
import de.uka.ipd.sdq.pcm.repository.util.RepositoryResourceImpl;
import de.uka.ipd.sdq.pcm.resourceenvironment.util.ResourceenvironmentResourceImpl;
import de.uka.ipd.sdq.pcm.seff.util.SeffAdapterFactory;
import de.uka.ipd.sdq.pcm.system.util.SystemAdapterFactory;
import de.uka.ipd.sdq.pcm.system.util.SystemResourceImpl;
import de.uka.ipd.sdq.pcm.usagemodel.util.UsagemodelResourceImpl;
import eu.cloudscaleproject.env.analyser.InputAlternative;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;

public class InputAlternativeTreeviewComposite extends Composite{
	
	private final InputAlternative alternative;
	
	private final Tree tree;
	private final TreeViewer treeViewer;
	

	public InputAlternativeTreeviewComposite(InputAlternative alternative, Composite parent, int style) {
		super(parent, style);
		this.alternative = alternative;
		
		setLayout(new GridLayout(1, false));
		
		this.tree = new Tree(this, SWT.BORDER | SWT.V_SCROLL);
		this.tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		this.treeViewer = new TreeViewer(tree);
		
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		
		adapterFactory.addAdapterFactory(new RepositoryAdapterFactory());
		adapterFactory.addAdapterFactory(new SystemAdapterFactory());
		adapterFactory.addAdapterFactory(new AllocationAdapterFactory());
		adapterFactory.addAdapterFactory(new SeffAdapterFactory());
		adapterFactory.addAdapterFactory(new MeasuringpointItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new PmsItemProviderAdapterFactory());
		//TODO: check if this is the right factory for experiment automation
		adapterFactory.addAdapterFactory(new ExperimentDataAdapterFactory());
		
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new EcoreAdapterFactory());
		
		this.treeViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
		this.treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		this.treeViewer.addFilter(new ModelViewFilter());
		
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
		
		new AdapterFactoryTreeEditor(tree, adapterFactory);
		
		alternative.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getPropertyName().equals(InputAlternative.KEY_REPOSITORY)
					|| evt.getPropertyName().equals(InputAlternative.KEY_SYSTEM)
					|| evt.getPropertyName().equals(InputAlternative.KEY_ALLOCATION)
					|| evt.getPropertyName().equals(InputAlternative.KEY_RESOURCES)){
						
						updateTreeview();				
					}
			}
		});
		
		updateTreeview();
	}
	
	private void updateTreeview(){
			
		ResourceSet resSet = new ResourceSetImpl();
		if(alternative.getRepository() != null){
			ExplorerProjectPaths.getEmfResource(resSet, alternative.getRepository());
		}
		if(alternative.getSystem() != null){
			ExplorerProjectPaths.getEmfResource(resSet, alternative.getSystem());
		}
		if(alternative.getAllocation() != null){
			ExplorerProjectPaths.getEmfResource(resSet, alternative.getAllocation());
		}
		if(alternative.getResourceEnv() != null){
			ExplorerProjectPaths.getEmfResource(resSet, alternative.getResourceEnv());
		}
		
		if(!this.tree.isDisposed()){
			this.treeViewer.setInput(resSet);
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

	
	private class ModelViewFilter extends ViewerFilter{

		@Override
		public boolean select(Viewer viewer, Object parentElement,
				Object element) {
			
			if(element instanceof Resource){
				if(element instanceof RepositoryResourceImpl){
					return true;
				}
				if(element instanceof SystemResourceImpl){
					return true;
				}
				if(element instanceof AllocationResourceImpl){
					return true;
				}
				if(element instanceof ResourceenvironmentResourceImpl){
					return true;
				}
				if(element instanceof UsagemodelResourceImpl){
					return true;
				}	
				if(element instanceof PmsResourceImpl){
					return true;
				}
			
				return false;
			}
			
			return true;
		}
	}
}
