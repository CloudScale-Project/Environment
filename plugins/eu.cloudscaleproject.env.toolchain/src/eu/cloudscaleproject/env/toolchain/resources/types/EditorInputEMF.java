package eu.cloudscaleproject.env.toolchain.resources.types;

import java.io.IOException;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.emf.transaction.util.TransactionUtil;

import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.util.CustomAdapterFactory;

public class EditorInputEMF extends EditorInputFolder{
	
	private static final AdapterFactory factory = new CustomAdapterFactory();

	private static final Logger logger = Logger.getLogger(EditorInputEMF.class.getName());

	public static final String PROP_COMMAND_STACK_CHANGED = EditorInputEMF.class.getName() + ".commandStackChanged";
	
	protected final CommandStack commandStack;
	protected final TransactionalEditingDomain editingDomain;
	
	protected final ResourceSet resSet;
	protected final ModelType[] modelTypes;
	
	public EditorInputEMF(IProject project, IFolder folder, ModelType[] modelTypes, String validationID) {
		super(project, folder, validationID);
		
		this.modelTypes = modelTypes != null ? modelTypes : new ModelType[]{};
		
		editingDomain = new TransactionalEditingDomainImpl(factory);
		
		commandStack = (TransactionalCommandStack)editingDomain.getCommandStack();
		commandStack.addCommandStackListener(new CommandStackListener() {
			
			@Override
			public void commandStackChanged(EventObject event) {
				setDirty(true);
				firePropertyChange(PROP_COMMAND_STACK_CHANGED, false, true);
			}
		});
		
		resSet = editingDomain.getResourceSet();
	}
	
	public void addSubResourceModel(IResource res) {
		
		String ext = res.getFileExtension();
		ModelType type = ModelType.getModelType(ext);
		String key = type != null ? type.getToolchainFileID() : null;
		
		if(key == null){
			logger.warning("Model with extension: " + res.getFileExtension() + " is not registred in the ModelType enum!");
			return;
		}
		

		loadModelResource((IFile)res);
		addSubResource(key, res);
	}
	
	public void removeSubResourceModel(IResource res) {
		String ext = res.getFileExtension();
		String key = null;

		for (ModelType type : ModelType.GROUP_ALL)
		{
			if (type.getFileExtension().equals(ext))
				key = type.getToolchainFileID();
		}
		
		if(key == null){
			logger.warning("Model with extension: " + res.getFileExtension() + "is not registred in the ModelType enum!");
			return;
		}
		
		unloadModelResource((IFile)res);
		removeSubResource(key, res);
	}
	
	private Resource loadModelResource(final IFile file){

		try {
			Resource resource = TransactionUtil.runExclusive(editingDomain, new RunnableWithResult.Impl<Resource>(){

				@Override
				public void run() {
					Resource resource = null;
					if(ModelType.getModelType(file.getFileExtension()) != null){
						resource = editingDomain.loadResource(file.getFullPath().toString());
					}
					
					if(resource == null){
						logger.severe("Can not load model resource! File: '" + file.getLocation().toString() + "' extension can not recognized!");
					}
					
					setResult(resource);
				}
			});
			
			return resource;
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void unloadModelResource(final IFile file){

		try {
			TransactionUtil.runExclusive(editingDomain, new RunnableWithResult.Impl<Resource>(){

				@Override
				public void run() {
					Resource resource = getModelResource(file);
					if(resource != null){
						resource.unload();
					}
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Resource getModelResource(IFile file){
		URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), false);
		return editingDomain.getResourceSet().getResource(uri, false);
	}
	
	public Resource getModelResource(String key){
		IResource res = getSubResource(key);
		if(res instanceof IFile){
			return getModelResource((IFile)res);
		}
		
		return null;
	}
	
	public List<Resource> getModelResources(String key){
		
		List<Resource> out = new ArrayList<Resource>();
		List<IResource> resources = getSubResources(key);
		
		for(IResource res : resources){
			if(res instanceof IFile){
				out.add(getModelResource((IFile)res));
			}
		}
		
		return out;
	}
	
	public ResourceSet getResourceSet(){
		return editingDomain.getResourceSet();
	}
	
	public int getUnloadProxyResourcesWork(){		
		List<IResource> loadedSubResources = getLoadedSubResources();
		List<Resource> loadedEMFResources = new ArrayList<Resource>();
		
		for(IResource res : loadedSubResources){
			if(res instanceof IFile){
				Resource emfRes = getModelResource((IFile)res);
				loadedEMFResources.add(emfRes);
			}
		}
		
		List<Resource> proxyResources = new ArrayList<Resource>();
		proxyResources.addAll(resSet.getResources());
		proxyResources.removeAll(loadedEMFResources);
		
		return proxyResources.size();
	}
	
	public void unloadProxyResources(IProgressMonitor monitor){
				
		List<IResource> loadedSubResources = getLoadedSubResources();
		List<Resource> loadedEMFResources = new ArrayList<Resource>();
		
		for(IResource res : loadedSubResources){
			if(res instanceof IFile){
				Resource emfRes = getModelResource((IFile)res);
				loadedEMFResources.add(emfRes);
			}
		}
		
		List<Resource> proxyResources = new ArrayList<Resource>();		
		proxyResources.addAll(resSet.getResources());
		proxyResources.removeAll(loadedEMFResources);
		
		boolean reloaded = false;
		
		workOn(monitor, "Unloading proxy resources");

		for(Resource r : proxyResources){
			r.unload();
			reloaded = true;
			work(monitor);
		}
		
		firePropertyChange(PROP_SUB_RESOURCE_CHANGED, false, reloaded);
	}
	
	public TransactionalEditingDomain getEditingDomain(){
		return editingDomain;
	}
	
	public AdapterFactory getAdapterFactory(){
		return factory;
	}

	/*
	public Resource getModelResource(String key){
		return getModelResource(resSet, key);
	}
	
	public Resource getModelResource(ResourceSet resSet, String key){
		List<Resource> resources = getModelResources(resSet, key);
		assert(resources.size() <= 1);
		return resources.size() == 0 ? null : resources.get(0);
	}
	
	public List<Resource> getModelResources(String key) {
		return getModelResources(resSet, key);
	}
	
	public List<Resource> getModelResources(ResourceSet resSet, String key) {
		List<Resource> out = new ArrayList<>();
		List<IResource> resources = getSubResources(key);
		
		for(IResource res : resources){
			if(res instanceof IFile){
				IFile file = (IFile)res;
				Resource emfResource = ExplorerProjectPaths.getEmfResource(resSet, file);
				out.add(emfResource);
			}
		}
		return out;
	}
	*/
	
	public EObject getModelRootSingle(String key){
		return getModelRootSingle(resSet, key);
	}
	
	public EObject getModelRootSingle(ResourceSet resSet, String key){
		List<EObject> roots = getModelRoot(resSet, key);
		assert(roots.size() <= 1);
		return roots.size() == 0 ? null : roots.get(0);
	}
	
	//TODO: refactor to getModelRoots and implement getModelRoot for retrieving single EObject.
	public List<EObject> getModelRoot(String key) {
		return getModelRoot(resSet, key);
	}
	
	//TODO: refactor to getModelRoots and implement getModelRoot for retrieving single EObject.
	public List<EObject> getModelRoot(ResourceSet resSet, String key) {
		
		List<EObject> out = new ArrayList<>();
		List<IResource> resources = getSubResources(key);
		
		for(IResource res : resources){
			if(res instanceof IFile){
				IFile file = (IFile)res;
				Resource emfResource = getModelResource(file);
				
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
				resources.add(loadModelResource((IFile)res));
			}
		}
		return resources;
	}
	
	public void executeModelChange(final Runnable runnable){
		commandStack.execute(new RecordingCommand(editingDomain) {

			@Override
			protected void doExecute() {
				runnable.run();
			}
			
			@Override
			public boolean canUndo() {
				return false;
			}
			
		});
	}
	
	public void executeRecordingModelChange(final Runnable runnable){
		commandStack.execute(new RecordingCommand(editingDomain) {

			@Override
			protected void doExecute() {
				runnable.run();
			}
			
		});
	}
	
	@Override
	protected void doCreate(final IProgressMonitor monitor) {
		
		workOn(monitor, "Creating Analyser configuration alternative.");
		
		commandStack.execute(new RecordingCommand(editingDomain) {

			@Override
			protected void doExecute() {
				doCreateModels();
			}
			
			@Override
			public boolean canUndo() {
				return false;
			}
			
		});
		
		work(monitor);
	}
	
	protected void doCreateModels(){
		
	}
	
	@Override
	public int getCreateWork() {
		return super.getCreateWork() + 1;
	}
	
	@Override
	protected void doSave(IProgressMonitor monitor) {
		
		super.doSave(monitor);
		
		for(IResource r : getLoadedSubResources()){
			
			if(r instanceof IFile){
				try {
					//do not save auto-loaded resources
					IFile file = (IFile)r;
					Resource res = getModelResource(file); 
					
					if(res != null && !res.getContents().isEmpty()){
						if(monitor != null){
							monitor.subTask("Saving resource '" + res.getURI().lastSegment() + "'");
						}
						res.save(null);
						if(monitor != null){
							monitor.worked(1);
						}
					}
				} catch (UnknownServiceException e){
					//ignore - file can not be saved
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.severe("Conf alternative: "+ getResource().getLocation().toString() 
									+" Can not save resource: "+ r.getFullPath().toString());
					e.printStackTrace();
				}
			}
			
		}

		try {
			getResource().refreshLocal(IFile.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		commandStack.flush();
	}
	
	@Override
	protected void doLoad(IProgressMonitor monitor) {
		
		super.doLoad(monitor);
		
		//unload existing models
		for(Resource res : new ArrayList<Resource>(resSet.getResources())){
			res.unload();
		}
		
		//TODO: Test the new loading and saving approach
		editingDomain.getResourceSet().getResources().clear();

		workOn(monitor, "Loading resources");
		
		//load registered models
		for (ModelType type : modelTypes)
		{
			for (IResource resource : getSubResources(type.getToolchainFileID()))
			{
				if(resource == null || !resource.exists()){
					logger.warning("Registered resource does not exist: " + resource.getFullPath().toString());
					continue;
				}

				if(resource instanceof IFile){
					IFile file = (IFile)resource;
				
					if(ModelType.getModelType(file.getFileExtension()) != null){
						loadModelResource(file);
					}
				}
			}
		}
	}
	
	public Map<IFile, List<Diagnostic>> validateModels(){

		Map<IFile, List<Diagnostic>> out = new HashMap<IFile, List<Diagnostic>>();

		for(IResource resource : getSubResources()){
			
			if(!(resource instanceof IFile)){
				continue;
			}
			
			if(ModelType.getModelType(resource.getFileExtension()) == null){
				//resource is not a EMF model
				continue;
			}

			final IFile file = (IFile)resource;
			
			if(!resource.exists()){
				logger.warning("Registered subresource does not exist! File: " + resource.getFullPath().toString());
				continue;
			}
			
			try {
				List<Diagnostic> diagnostics = TransactionUtil.runExclusive(editingDomain, new RunnableWithResult.Impl<List<Diagnostic>>() {

					@Override
					public void run() {
						
						List<Diagnostic> out = new ArrayList<Diagnostic>();

						Resource res = getModelResource(file);
						if(res == null){
							logger.warning("Model resource is not contained in the resource set! File: " + file.getLocation().toString());
							setResult(out);
							return;
						}
						
						// TODO:
						// Concurrent modification exception can occur here - despite using transactions...
						for(EObject eo : new ArrayList<EObject>(res.getContents())){

							Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eo);
							out.add(diagnostic);
							
						}
						
						setResult(out);
					}
				});
				
				out.put(file, diagnostics);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return out;
	}
	
	@Override
	public int getLoadWork() {
		
		int work = 0;
		for (ModelType type : modelTypes)
		{
			for (IResource f : getSubResources(type.getToolchainFileID()))
			{
				if(f == null || !f.exists()){
					logger.warning("Registered resource does not exist: " + f.getFullPath().toString());
					continue;
				}
				work++;
			}
		}
		
		return super.getLoadWork() + work;
	}
	
	@Override
	public int getSaveWork() {
		
		int work = super.getSaveWork();
		for (IResource r : getLoadedSubResources()) {
			if (r instanceof IFile) {
				
				Resource res = getModelResource((IFile) r);
				if (res != null && !res.getContents().isEmpty()) {
					work++;
				}
			}
		}

		return work;
	}
	
	@Override
	public void dispose() {
		editingDomain.dispose();
		super.dispose();
	}
}
