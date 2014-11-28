package eu.cloudscaleproject.env.usageevolution;

import java.io.IOException;
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

import dlim.DlimFactory;
import dlim.ExponentialIncreaseLogarithmicDecline;
import eu.cloudscaleproject.env.common.dialogs.DialogUtils;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;

public class UsageEvolutionAlternative extends EditorInputFolder{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(UsageEvolutionAlternative.class.getName());
	
	public static final String KEY_FILE_USAGEEVOLUTION = "usageevolution_model";
	public static final String KEY_FILE_LIMBO = "limbo_model";
		
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
		
		setFileResource(KEY_FILE_USAGEEVOLUTION, usageFile);
		setFileResource(KEY_FILE_LIMBO, limboFile);
	}
	
	public void clear(){
		
		IFile limboFile = getFileResource(KEY_FILE_LIMBO);
		IFile usageFile = getFileResource(KEY_FILE_USAGEEVOLUTION);
		
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
		
		IFile limboFile = getFileResource(KEY_FILE_LIMBO);
		IFile usageFile = getFileResource(KEY_FILE_USAGEEVOLUTION);
		
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
		else{
			logger.warning("createEILDPreset(): Can't create '.limbo' file! File already exist!");
		}
		if(!usageFile.exists()){
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
		else{
			logger.warning("createEILDPreset(): Can't create '.usageevolution' file! File already exist!");
		}
	}
	
	public void openUsageEvolutionEditor(){
		
		IFile usageFile = getFileResource(KEY_FILE_USAGEEVOLUTION);
		
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
		IFile limboFile = getFileResource(KEY_FILE_LIMBO);
		
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
}
