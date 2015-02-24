package eu.cloudscaleproject.env.usageevolution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.scaledl.usageevolution.Usage;
import org.scaledl.usageevolution.UsageEvolution;
import org.scaledl.usageevolution.UsageevolutionFactory;

import tools.descartes.dlim.DlimFactory;
import tools.descartes.dlim.ExponentialIncreaseLogarithmicDecline;

import tools.descartes.dlim.Sequence;
import eu.cloudscaleproject.env.common.dialogs.DialogUtils;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;

public class UsageEvolutionAlternative extends EditorInputFolder{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(UsageEvolutionAlternative.class.getName());
		
	private final ResourceSet resSet = new ResourceSetImpl();

	public UsageEvolutionAlternative(IProject project, IFolder folder) {
		super(project, folder);
		
		IFile usageFile = folder.getFile("pcm.usageevolution");
		IFile limboFile = folder.getFile("pcm.dlim");
		
		if(!limboFile.exists()){
			//create dummy limbo model
			
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, limboFile);
			ExponentialIncreaseLogarithmicDecline model = DlimFactory.eINSTANCE.createExponentialIncreaseLogarithmicDecline();
			model.setBase(0);
			model.setPeak(500);
			model.setPeakTime(100);
			res.getContents().add(model);
			
			try {
				res.save(null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(!usageFile.exists()){
			//create dummy usageevolution model
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, usageFile);
						
			UsageEvolution ue = UsageevolutionFactory.eINSTANCE.createUsageEvolution();
			Usage usage = UsageevolutionFactory.eINSTANCE.createUsage();
			ue.getUsages().add(usage);
			
			res.getContents().add(ue);
			
			try {
				res.save(null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		setSubResource(ToolchainUtils.KEY_FILE_USAGEEVOLUTION, usageFile);
		setSubResource(ToolchainUtils.KEY_FILE_LIMBO, limboFile);
	}
	
	public void clear(){
		
		IFile limboFile = (IFile)getSubResource(ToolchainUtils.KEY_FILE_LIMBO);
		IFile usageFile = (IFile)getSubResource(ToolchainUtils.KEY_FILE_USAGEEVOLUTION);
		
		if(limboFile == null){
			logger.warning("clear(): Limbo model file not set!");
			return;
		}
		if(usageFile == null){
			logger.warning("clear(): Usage evolution model file not set!");
			return;
		}

		if(limboFile.exists() || usageFile.exists()){
			if(!DialogUtils.openConfirm("Current usage evolution data will be removed! Do you confirm?")){
				return;
			}
		}
		
		try {
			
			limboFile.delete(true, null);
			limboFile.getParent().refreshLocal(IFile.DEPTH_ZERO, null);
			usageFile.delete(true, null);
			usageFile.getParent().refreshLocal(IFile.DEPTH_ZERO, null);
			
		} catch (CoreException e1) {
			e1.printStackTrace();
		}		
	}
	
	public void createEILDPreset(){
		
		IFile limboFile = (IFile)getSubResource(ToolchainUtils.KEY_FILE_LIMBO);
		IFile usageFile = (IFile)getSubResource(ToolchainUtils.KEY_FILE_USAGEEVOLUTION);
		
		if(limboFile == null){
			logger.warning("clear(): Limbo model file not set!");
			return;
		}
		if(usageFile == null){
			logger.warning("clear(): Usage evolution model file not set!");
			return;
		}
		
		clear();
		
		if(!limboFile.exists()){			
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, limboFile);
			Sequence model = DlimFactory.eINSTANCE.createSequence();
			res.getContents().add(model);
			
			try {
				res.save(null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			logger.warning("createEILDPreset(): Can't create '.limbo' file! File already exist!");
		}
		if(!usageFile.exists()){
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, usageFile);
						
			UsageEvolution ue = UsageevolutionFactory.eINSTANCE.createUsageEvolution();
			Usage usage = UsageevolutionFactory.eINSTANCE.createUsage();
			usage.setLoadEvolution(getSequence());
			ue.getUsages().add(usage);
			
			res.getContents().add(ue);
			
			try {
				res.save(null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			logger.warning("createEILDPreset(): Can't create '.usageevolution' file! File already exist!");
		}
	}
	
	public Usage getUsage(){
		for(Resource res : resSet.getResources()){
			if(!res.getContents().isEmpty()){
				if(res.getContents().get(0) instanceof Usage){
					return (Usage)res.getContents().get(0);
				}
			}
		}
		return null;
	}
	
	public Sequence getSequence(){
		for(Resource res : resSet.getResources()){
			if(!res.getContents().isEmpty()){
				if(res.getContents().get(0) instanceof Sequence){
					return (Sequence)res.getContents().get(0);
				}
			}
		}
		return null;
	}
	
	public void openUsageEvolutionEditor(){
		
		IFile usageFile = (IFile)getSubResource(ToolchainUtils.KEY_FILE_USAGEEVOLUTION);
		
		if(usageFile == null){
			logger.warning("clear(): Usage evolution model file not set!");
			return;
		}
		
		try {
			IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), usageFile);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
	
	public void openLimboEditor(){
		IFile limboFile = (IFile)getSubResource(ToolchainUtils.KEY_FILE_LIMBO);
		
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
	
	public boolean hasModel(String extension){
		return getModels(extension).size() > 0 ? true : false;
	}
	
	public List<Resource> getModels(String fileExtension) {
		
		List<Resource> out = new ArrayList<Resource>();
		for(Resource res : resSet.getResources()){
			if(res.getURI().lastSegment().endsWith(fileExtension)){
				out.add(res);
			}
		}
		return out;
	}
}
