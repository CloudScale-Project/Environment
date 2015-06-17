package eu.cloudscaleproject.env.usageevolution;

import org.eclipse.core.resources.IFile;
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

public class DlimGenerator
{
	public enum Template{
		LINEAR_TREND, 
		LINEAR_INCREASE_DECLINE, 
		SINUSOIDAL,
		SINUSOIDAL_TREND, 
		EXPONENTIAL, 
		EXPONENTIAL_LOG
	}
	
	public static Sequence createEmptySequence ()
	{
		Sequence sequence = DlimFactory.eINSTANCE.createSequence();
		sequence.setName("Sequence");
		return sequence;
	}
	

	public static Sequence createTemplateSequance(Template type){

		Sequence sequence = DlimFactory.eINSTANCE.createSequence();
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
		
		return sequence;
	}
	
	private static Function createLinearTrendFunction(){
		LinearTrend func = DlimFactory.eINSTANCE.createLinearTrend();
		func.setFunctionOutputAtStart(0);
		func.setFunctionOutputAtEnd(50);
		return func;
	}
	
	private static Function createLinearIncreaseAndDecline(){
		LinearIncreaseAndDecline func = DlimFactory.eINSTANCE.createLinearIncreaseAndDecline();
		func.setBase(0);
		func.setPeak(50);
		func.setPeakTime(0);
		return func;
	}
	
	private static Function createSinusoidal(){
		Sin func = DlimFactory.eINSTANCE.createSin();
		func.setMax(50);
		func.setMin(0);
		func.setPeriod(100);
		func.setPhase(0);
		return func;
	}
	
	private static Function createSinusoidalTrend(){
		SinTrend func = DlimFactory.eINSTANCE.createSinTrend();
		func.setFunctionOutputAtStart(0);
		func.setFunctionOutputAtEnd(50);
		return func;
	}
	
	private static Function createExponentialTrend(){
		ExponentialTrend func = DlimFactory.eINSTANCE.createExponentialTrend();
		func.setFunctionOutputAtStart(0);
		func.setFunctionOutputAtEnd(50);
		return func;
	}
	
	private static Function createExponentialLogFunction(){
		ExponentialIncreaseLogarithmicDecline func = DlimFactory.eINSTANCE.createExponentialIncreaseLogarithmicDecline();
		func.setPeak(200);
		func.setBase(10);
		func.setLogarithmicOrder(10);
		return func;
	}

	public void openLimboEditor(IFile limboFile){
		//IFile limboFile = (IFile)getSubResource(ToolchainUtils.KEY_FILE_LIMBO);
		try {
			IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), limboFile);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}
