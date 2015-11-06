package eu.cloudscaleproject.env.analyser.validation;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.palladiosimulator.experimentautomation.experiments.Experiment;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.Monitor;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjective;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository;
import org.scaledl.usageevolution.Usage;
import org.scaledl.usageevolution.UsageEvolution;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.common.BasicCallback;
import eu.cloudscaleproject.env.common.notification.IResourceValidator;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.ValidationException;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;

public class ConfigValidator implements IResourceValidator {
	
	private static final Logger logger = Logger.getLogger(ConfigValidator.class.getName());
	
	private static final String ERR_INPUT_NOT_SET = "eu.cloudscaleproject.env.analyser.validation.ConfValidator.inputnotset";

	@Override
	public String getID() {
		return CSToolResource.ANALYSER_CONF.getID();
	}
	
	public void validateModels(IProject project, ConfAlternative ca) throws CoreException, ValidationException{
		
		boolean needsSlo = false;
		if(ConfAlternative.Type.CAPACITY.equals(ca.getTypeEnum()) || ConfAlternative.Type.SCALABILITY.equals(ca.getTypeEnum())){
			needsSlo = true;
		}
		
		List<IResource> expFiles = ca.getSubResources(ToolchainUtils.KEY_FILE_EXPERIMENTS);
		List<IResource> mpFiles = ca.getSubResources(ToolchainUtils.KEY_FILE_MESURPOINTS);
		List<IResource> monitorFiles = ca.getSubResources(ToolchainUtils.KEY_FILE_MONITOR);
		List<IResource> sloFiles = ca.getSubResources(ToolchainUtils.KEY_FILE_SLO);
		
		ca.getSelfStatus().checkError("Experiments missing", !expFiles.isEmpty(), false, "Experiments model is missing!");
		ca.getSelfStatus().checkError("MeasureP missing", !mpFiles.isEmpty(), false, "Measuring points model is missing!");
		ca.getSelfStatus().checkError("Monitor missing", !monitorFiles.isEmpty(), false, "Monitor model is missing!");
		
		if(needsSlo){
			ca.getSelfStatus().checkError("SLO missing", !sloFiles.isEmpty(), false, "SLO model is missing!");
		}

		boolean areModelsValid = true;
		
		Map<IFile, List<Diagnostic>> diagnostics = ca.validateModels();
		for(Entry<IFile, List<Diagnostic>> entry : diagnostics.entrySet()){
			
			final IFile file = entry.getKey();
			IValidationStatus status = ca.getStatus(file);
			
			if(status == null){
				logger.warning("IValidationStatus for the resource does not exist! Resource: " + entry.getKey().toString());
				continue;
			}
			
			status.clearWarnings();

			BasicCallback<Object> handle = new BasicCallback<Object>() {

				@Override
				public void handle(Object object) {
					try {
						IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
						IDE.openEditor(page, file);
					} catch (PartInitException e) {
						e.printStackTrace();
					}
				}
			};
			
			for(Diagnostic d : entry.getValue()){
				
				if(d.getSeverity() == Diagnostic.OK){
					status.setIsValid(true);
				}
				if(d.getSeverity() == Diagnostic.WARNING){
					status.addWarning(d.getSource(), IValidationStatus.SEVERITY_WARNING, d.getMessage(), handle);
					status.setIsValid(false);
					areModelsValid &= false;
				}
				if(d.getSeverity() == Diagnostic.ERROR){
					status.addWarning(d.getSource(), IValidationStatus.SEVERITY_ERROR, d.getMessage(), handle);
					status.setIsValid(false);
					areModelsValid &= false;
				}
				if(d.getSeverity() == Diagnostic.INFO){
					status.addWarning(d.getSource(), IValidationStatus.SEVERITY_INFO, d.getMessage(), handle);
					status.setIsValid(false);
					areModelsValid &= true;
				}
				if(d.getSeverity() == Diagnostic.CANCEL){
					status.addWarning(d.getSource(), IValidationStatus.SEVERITY_ERROR, d.getMessage(), handle);
					status.setIsValid(false);
					areModelsValid &= false;
				}
			}
		}
		
		ca.getSelfStatus().check("Models are not valid", areModelsValid, false, 
				IValidationStatus.SEVERITY_ERROR, "Alternative models are not valid!");
		ca.getSelfStatus().setIsValid(areModelsValid);
		
	}
	
	private boolean validateExperiment(ConfAlternative alt) throws ValidationException{
		
		Experiment experiment = alt.getActiveExperiment();
	
		IValidationStatus status = alt.getSelfStatus();
		
		status.checkError("ExperimentError", experiment != null, true, "Experiment: Experiment does not exist!");
		status.checkError("InitialModelError", experiment.getInitialModel() != null, true, "Experiment: Initial model does not exist!");
		status.checkError("StopConditionError", !experiment.getStopConditions().isEmpty(), true, "Experiment: Stop condition is empty!");

		return true;
	}
	
	private boolean validateSloRepository(ConfAlternative alt) throws ValidationException{
		
		ServiceLevelObjectiveRepository sloRep = alt.getActiveSLORepository();
		IValidationStatus[] statusArray = alt.getSubStatus(ToolchainUtils.KEY_FILE_SLO);
		
		for(IValidationStatus status : statusArray){
			
			if(ConfAlternative.Type.CAPACITY.equals(alt.getTypeEnum())
					|| ConfAlternative.Type.SCALABILITY.equals(alt.getTypeEnum())){
				
				status.checkError("SLOError", sloRep != null , true, "This experiment type needs SLO specifications");
				status.checkError("SLOEmptyError", !sloRep.getServicelevelobjectives().isEmpty() , true, "This experiment type needs SLO specifications");

			}
			
			if(sloRep != null && !sloRep.getServicelevelobjectives().isEmpty()){
				
				String errorID = "SLOError";
				
				for(ServiceLevelObjective slo : sloRep.getServicelevelobjectives()){
					status.checkError(errorID, slo.getLowerThreshold() != null || slo.getUpperThreshold() != null, true,
							"SLO: '"+ slo.getName() +"' needs uper or lower threshold!");
					status.checkError(errorID, slo.getMeasurementSpecification() != null, true, 
							"SLO: '"+ slo.getName() +"' needs measurement specification!");
				}
			}
			status.clearWarnings();
		}

		return true;
	}
	
	private boolean validateMonitorRepository(ConfAlternative alt) throws ValidationException{

		String errorSpecMissingID = "MonitorSpecError";
		String errorSpecIncompleteID = "MonitorSpecIncompleteError";

		String errorMpID = "MonitorMpError";
		String errorMonitorRepID = "MonitorRepMissing";
		
		boolean isValid = true;
		
		MonitorRepository monitorRep = alt.getActiveMonitorRepository();
		IValidationStatus[] statusArray = alt.getSubStatus(ToolchainUtils.KEY_FILE_MONITOR);
		
		for(IValidationStatus status : statusArray){
			
			status.clearWarnings();
			status.checkError(errorMonitorRepID, monitorRep != null, true,
					"Monitor repository does not exist!");
			
			for(Monitor monitor : monitorRep.getMonitors()){
				
				try{
					status.checkError(errorSpecMissingID, monitor.getMeasurementSpecifications() != null, true,
						"Monitor: '"+ monitor.getEntityName() +"' needs measurement specification!");
				
					for(MeasurementSpecification ms : monitor.getMeasurementSpecifications()){
						status.checkError(errorSpecIncompleteID, ms.getMetricDescription() != null, true,
								"Monitor: '"+ monitor.getEntityName() +"' incomplete measurement specification!");
						status.checkError(errorSpecIncompleteID, ms.getStatisticalCharacterization() != null, true,
								"Monitor: '"+ monitor.getEntityName() +"' incomplete measurement specification!");
					}
				}
				catch(ValidationException e){
					isValid = false;
				}
				
				status.checkError(errorMpID, monitor.getMeasuringPoint() != null, true,
						"Monitor: '"+ monitor.getEntityName() +"' does not have measuring point specified!");
			}
		}

		return isValid;
	}
	
	private boolean validateUsageEvolution(ConfAlternative alt) throws ValidationException{
		
		UsageEvolution usageEvolution = alt.getActiveUsageEvolutionModel();
		if(usageEvolution == null){
			return true;
		}
		
		IValidationStatus[] statusArray = alt.getSubStatus(ToolchainUtils.KEY_FILE_USAGEEVOLUTION);
		
		for(IValidationStatus status : statusArray){
				
			status.checkError("UEEmpty", !usageEvolution.getUsages().isEmpty(), true, 
					"Usage evolution with empty configuration is set in Experiment!");
			
			for(Usage usage : usageEvolution.getUsages()){
				status.checkError("", usage.getLoadEvolution() != null, true, 
						"Usage evolution: '"+ usage.getEntityName() + "' does not have load evolution!");
				status.checkError("", usage.getScenario() != null, true, 
						"Usage evolution: '"+ usage.getEntityName() + "' does not have usage scenarion!");
			}
			status.clearWarnings();
		}
		return true;
	}
	
	@Override
	public void validate(IProject project, IValidationStatusProvider statusProvider) {
		
		final ConfAlternative alternative = (ConfAlternative)statusProvider;
		IValidationStatus status = statusProvider.getSelfStatus();
		
		InputAlternative inputAlternative = alternative.getInputAlternative();
		
		try {
			status.checkError(ERR_INPUT_NOT_SET, inputAlternative != null, true, "Input alternative is not set!");
			inputAlternative.validate();
			
			validateModels(project, alternative);

			//additional custom model validation checks 
			boolean valid = alternative.getSelfStatus().isValid();
			
			if(valid){
				try{valid &= validateExperiment(alternative);}
				catch(ValidationException e){ valid = false;};
				try{valid &= validateMonitorRepository(alternative);}
				catch(ValidationException e){ valid = false;};
				try{valid &= validateSloRepository(alternative);}
				catch(ValidationException e){ valid = false;};
				try{valid &= validateUsageEvolution(alternative);}
				catch(ValidationException e){ valid = false;};

				alternative.getSelfStatus().setIsValid(valid);
			}

			//TODO: check usage evolution
			
		} 
		catch (CoreException e) {
			e.printStackTrace();
		}
		catch (ValidationException e) {
			status.setIsValid(false);
		}
		
		status.setIsDirty(alternative.isDirty());
	}

}
