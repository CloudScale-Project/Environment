package eu.cloudscaleproject.env.usageevolution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import tools.descartes.dlim.ClockType;
import tools.descartes.dlim.DlimFactory;
import tools.descartes.dlim.ExponentialIncreaseLogarithmicDecline;
import tools.descartes.dlim.ExponentialTrend;
import tools.descartes.dlim.Function;
import tools.descartes.dlim.LinearIncreaseAndDecline;
import tools.descartes.dlim.LinearTrend;
import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.Sin;
import tools.descartes.dlim.SinTrend;
import tools.descartes.dlim.TimeDependentFunctionContainer;
import eu.cloudscaleproject.env.common.dialogs.DialogUtils;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;

public class UsageEvolutionAlternative extends EditorInputEMF{

	private static final Logger logger = Logger.getLogger(UsageEvolutionAlternative.class.getName());
	
	public enum Presets{
		LINEAR_TREND, 
		LINEAR_INCREASE_DECLINE, 
		SINUSOIDAL,
		SINUSOIDAL_TREND, 
		EXPONENTIAL, 
		EXPONENTIAL_LOG
	}
		
	public UsageEvolutionAlternative(IProject project, IFolder folder) {
		super(project, folder, ToolchainUtils.USAGEEVOLUTION_ID);
		
		IFile limboFile = folder.getFile("pcm.dlim");
		if(!limboFile.exists()){
			//create dummy limbo model		
			createPreset(Presets.LINEAR_TREND);
		}
		setSubResource(ToolchainUtils.KEY_FILE_LIMBO, limboFile);
	}
	
	public void createPreset(Presets type){
		
		IFile limboFile = (IFile)getSubResource(ToolchainUtils.KEY_FILE_LIMBO);
		if(limboFile == null){
			limboFile = getResource().getFile("pcm.dlim");
		}
		
		if(limboFile.exists()){
			if(!DialogUtils.openConfirm("Current usage evolution data will be removed! Do you confirm?")){
				return;
			}
		}
				
		Resource res = ExplorerProjectPaths.getEmfResource(resSet, limboFile);
		Sequence sequence = res.getContents().isEmpty() ? null : (Sequence)res.getContents().get(0);
		
		if(sequence != null){
			EcoreUtil.delete(sequence, true);
		}
		
		sequence = DlimFactory.eINSTANCE.createSequence();
		sequence.setName("No name");
		sequence.setTerminateAfterLoops(1);
		
		TimeDependentFunctionContainer fc = DlimFactory.eINSTANCE.createTimeDependentFunctionContainer();
		fc.setDuration(60*10);
		fc.setName("Container");
		fc.setPointOfReferenceClockType(ClockType.CONTAINER_CLOCK);
		
		Function f = null;
		
		switch(type){
			case LINEAR_TREND:
				sequence.setName("Linear trend");
				f = createLinearTrendFunction();
				break;
			case LINEAR_INCREASE_DECLINE:
				sequence.setName("Linear increase and decline");
				f = createLinearIncreaseAndDecline();
				break;
			case SINUSOIDAL:
				sequence.setName("Sinusoidal");
				f = createSinusoidal();
				break;
			case SINUSOIDAL_TREND:
				sequence.setName("Sinusoidal trend");
				f = createSinusoidalTrend();
				break;
			case EXPONENTIAL:
				sequence.setName("Exponential");
				f = createExponentialTrend();
				break;
			case EXPONENTIAL_LOG:
				sequence.setName("Explonential increase and log decline");
				f = createExponentialLogFunction();
				break;
		}
		
		fc.setFunction(f);
		sequence.getSequenceFunctionContainers().add(fc);
		res.getContents().add(sequence);
		
		try {
			res.save(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Function createLinearTrendFunction(){
		LinearTrend func = DlimFactory.eINSTANCE.createLinearTrend();
		func.setFunctionOutputAtStart(0);
		func.setFunctionOutputAtEnd(50);
		return func;
	}
	
	private Function createLinearIncreaseAndDecline(){
		LinearIncreaseAndDecline func = DlimFactory.eINSTANCE.createLinearIncreaseAndDecline();
		func.setBase(0);
		func.setPeak(50);
		func.setPeakTime(0);
		return func;
	}
	
	private Function createSinusoidal(){
		Sin func = DlimFactory.eINSTANCE.createSin();
		func.setMax(50);
		func.setMin(0);
		func.setPeriod(100);
		func.setPhase(0);
		return func;
	}
	
	private Function createSinusoidalTrend(){
		SinTrend func = DlimFactory.eINSTANCE.createSinTrend();
		func.setFunctionOutputAtStart(0);
		func.setFunctionOutputAtEnd(50);
		return func;
	}
	
	private Function createExponentialTrend(){
		ExponentialTrend func = DlimFactory.eINSTANCE.createExponentialTrend();
		func.setFunctionOutputAtStart(0);
		func.setFunctionOutputAtEnd(50);
		return func;
	}
	
	private Function createExponentialLogFunction(){
		ExponentialIncreaseLogarithmicDecline func = DlimFactory.eINSTANCE.createExponentialIncreaseLogarithmicDecline();
		func.setPeak(200);
		func.setBase(10);
		func.setLogarithmicOrder(10);
		return func;
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
