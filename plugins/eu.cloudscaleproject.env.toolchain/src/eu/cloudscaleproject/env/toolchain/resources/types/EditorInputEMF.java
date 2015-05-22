package eu.cloudscaleproject.env.toolchain.resources.types;

import java.io.IOException;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.util.CustomAdapterFactory;

public class EditorInputEMF extends EditorInputFolder{
	
	private static final AdapterFactory factory = new CustomAdapterFactory();

	private static final Logger logger = Logger.getLogger(EditorInputEMF.class.getName());

	public static final String PROP_COMMAND_STACK_CHANGED = EditorInputEMF.class.getName() + ".commandStackChanged";
	
	protected final BasicCommandStack commandStack;
	protected final AdapterFactoryEditingDomain editingDomain;
	
	protected final ResourceSet resSet;

	public EditorInputEMF(IProject project, IFolder folder,String validationID) {
		super(project, folder, validationID);
		
		commandStack = new BasicCommandStack();
		commandStack.addCommandStackListener(new CommandStackListener() {
			
			@Override
			public void commandStackChanged(EventObject event) {
				pcs.firePropertyChange(PROP_COMMAND_STACK_CHANGED, false, true);
			}
		});
		
		editingDomain = new AdapterFactoryEditingDomain(factory, commandStack);
		resSet = editingDomain.getResourceSet();
	}
	
	public void addSubResourceModel(IResource res) {
		//TODO: make abstract
		throw new UnsupportedOperationException();
	}
	
	public void removeSubResourceModel(IResource res) {
		//TODO: make abstract
		throw new UnsupportedOperationException();
	}
	
	public ResourceSet getResourceSet(){
		return editingDomain.getResourceSet();
	}
	
	public EditingDomain getEditingDomain(){
		return editingDomain;
	}
	
	public AdapterFactory getAdapterFactory(){
		return factory;
	}
	
	public List<EObject> getModelRoot(String key) {
		return getModelRoot(resSet, key);
	}
	
	public EObject getModelRoot(Resource resource) {
		if(resource.getContents().isEmpty()){
			return null;
		}
		return resource.getContents().get(0);
	}
	
	public List<EObject> getModelRoot(ResourceSet resSet, String key) {
		
		List<EObject> out = new ArrayList<>();
		List<IResource> resources = getSubResources(key);
		
		for(IResource res : resources){
			if(res instanceof IFile){
				IFile file = (IFile)res;
				Resource emfResource = ExplorerProjectPaths.getEmfResource(resSet, file);
				if(emfResource.isLoaded() && !emfResource.getContents().isEmpty()){
					out.add(emfResource.getContents().get(0));
				}
			}
		}
		return out;
	}
	
	public List<Resource> loadExternal(EditorInputEMF resource, String key){
		
		List<Resource> resources = new ArrayList<Resource>();
		for(IResource res : resource.getSubResources(key)){
			if(res instanceof IFile){
				resources.add(ExplorerProjectPaths.getEmfResource(resSet, (IFile)res));
			}
		}
		return resources;
	}
	
	@Override
	protected void doSave() {

		super.doSave();
		for(Resource res : new ArrayList<Resource>(editingDomain.getResourceSet().getResources())){
			try {
				if(!res.getContents().isEmpty()){
					res.save(null);
				}
			} catch (UnknownServiceException e){
				//ignore - file can not be saved
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.severe("Conf alternative: "+ getResource().getLocation().toString() 
								+" Can not save resource: "+ res.getURI().toString());
				e.printStackTrace();
			}
		}

		try {
			getResource().refreshLocal(IFile.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		commandStack.saveIsDone();
	}
	
	@Override
	public boolean isDirty() {
		return super.isDirty() || ((BasicCommandStack)editingDomain.getCommandStack()).isSaveNeeded();
	}
}
