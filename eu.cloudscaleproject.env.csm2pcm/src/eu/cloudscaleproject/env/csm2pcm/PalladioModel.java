package eu.cloudscaleproject.env.csm2pcm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.PlatformResourceURIHandlerImpl;
import org.eclipse.gmf.runtime.notation.Diagram;

import eu.cloudscaleproject.env.csm2pcm.PalladioUtil.ModelID;

public class PalladioModel {
	
	public static final String DEFAULT_MODEL_ID = "CSMGen_";
	
	public static final Logger logger = Logger.getLogger(PalladioModel.class.getSimpleName());
	//public static final ResourceSet resourceSet = new ResourceSetImpl();
	
	public final URI uri;
	public final URI uriDiagram;
	
	private Resource resource = null;
	private Resource resourceDiagram = null;
	
	private EObject model = null;
	private Diagram diagram = null;
	
	public PalladioModel(ResourceSet resourceSet, URI modelUriFolder, URI diagramUriFolder, ModelID id) {
		this.uri = PalladioUtil.createModelURI(id, modelUriFolder);
		
		if(diagramUriFolder != null){
			this.uriDiagram = PalladioUtil.createDiagramURI(id, diagramUriFolder);
		}
		else{
			this.uriDiagram = null;
		}
		
		try{
			this.resource = resourceSet.getResource(uri, false);
			
			if(uriDiagram != null){
				this.resourceDiagram = resourceSet.getResource(uriDiagram, true);
			}
		}
		catch(Exception e){
			//ignore exception - resource file probably don't exist jet...
		}
		
		//remove/delete resource if file doesn't exist
		if(!fileExist(uri) && this.resource != null){
			try {
				this.resource.delete(null);
				this.resource = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//remove/delete diagram resource if file doesn't exist
		if(resourceDiagram != null && !fileExist(uriDiagram)){
			try {
				this.resourceDiagram.delete(null);
				this.resourceDiagram = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//handle resource
		if(this.resource == null){
			this.resource = resourceSet.createResource(uri);
			this.saveModelResource();
			this.loadModelResource();
		}
		else{
			if(!resource.getContents().isEmpty()){
				this.model = resource.getContents().get(0);
			}
		}
		
		//handle resource diagram
		if(uriDiagram != null){
			if(this.resourceDiagram == null){
				this.resourceDiagram = resourceSet.createResource(uriDiagram);
				saveDiagramResource();
				loadDiagramResource();
			}
			else{
				if(!resourceDiagram.getContents().isEmpty()){
					this.diagram = (Diagram)this.resourceDiagram.getContents().get(0);
				}
			}
		}
		
		//check
		if(this.resource == null || (this.uriDiagram != null && this.resourceDiagram == null)){
			logger.log(Level.SEVERE, "Can not create resource files! Check write permissions!");
		}
		
		//create new model if needed
		if(this.model == null){
			this.model = PalladioUtil.createModel(id);
			this.resource.getContents().add(this.model);
			saveModelResource();
		}
		
		//create new diagram if needed
		if(this.resourceDiagram != null && this.diagram == null){
			this.diagram = PalladioUtil.createDiagram(id, model);
			resourceDiagram.getContents().add(diagram);
			
			saveDiagramResource();
		}
		
		//check
		if(this.model == null || (this.uriDiagram != null && this.diagram == null)){
			logger.log(Level.SEVERE, "Can not create model files!");
		}
	}
	
	public final boolean fileExist(URI uri){
		PlatformResourceURIHandlerImpl uriHandler = new PlatformResourceURIHandlerImpl();
		return uriHandler.exists(uri, null);
	}
	
	public final void loadModelResource(){
		if(this.resource != null){
			if(resource.isLoaded()){
				resource.unload();
			}
			try {
				resource.load(null);
				if(!resource.getContents().isEmpty()){
					this.model = resource.getContents().get(0);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public final void loadDiagramResource(){
		if(this.resourceDiagram != null){
			if(resourceDiagram.isLoaded()){
				resourceDiagram.unload();
			}
			try {
				resourceDiagram.load(null);
				if(!resourceDiagram.getContents().isEmpty()){
					this.diagram = (Diagram)resourceDiagram.getContents().get(0);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void unloadResource(){
		if(resource != null){
			this.resource.unload();
		}
	}
	
	public void unloadDiagramResource(){
		if(resourceDiagram != null){
			this.resourceDiagram.unload();
		}
	}
	
	public final void saveModelResource(){
		if(this.resource == null){
			logger.log(Level.SEVERE, "save(): resource is NULL!");
			return;
		}
		
		try {
			this.resource.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public final void saveDiagramResource(){
		
		Map<String, String> options = new HashMap<String, String>();
		options.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, null);
		
		if(this.resourceDiagram == null){
			logger.log(Level.SEVERE, "save(): resource is NULL!");
			return;
		}
		
		try {
			this.resourceDiagram.save(options);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setModel(EObject root){
		this.resource.getContents().clear();
		this.resource.getContents().add(root);
		this.model = root;
		
		if(this.diagram != null){
			this.diagram.setElement(root);
		}
	}
	
	public EObject getModel(){
		return model;
	}
	
	public Diagram getDiagram(){
		return this.diagram;
	}
	
	public Resource getResource(){
		return resource;
	}
	
	public Resource getResourceDiagram(){
		return resourceDiagram;
	}
}
