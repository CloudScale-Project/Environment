package eu.cloudscaleproject.env.overview.validation;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.resource.Resource;
import org.scaledl.overview.Overview;

import eu.cloudscaleproject.env.common.notification.IResourceValidator;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.ValidationException;
import eu.cloudscaleproject.env.overview.OverviewAlternative;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;

/**
 * 
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class OverviewValidator implements IResourceValidator {
	
	//private static final String ERR_OVERVIEW_NULL = "org.scaledl.overview.diagram.validation.OverviewValidator.overviewnull";
	private static final String ERR_OVERVIEW_EMPTY = "org.scaledl.overview.diagram.validation.OverviewValidator.overviewempty";
	private static final String ERR_OVERVIEW_INVALID = "org.scaledl.overview.diagram.validation.OverviewValidator.overviewinvalid";

	@Override
	public String getID() {
		return CSTool.OVERVIEW.getID();
	}

	@Override
	public void validate(IProject project, IValidationStatusProvider statusProvider) {
		
		OverviewAlternative alternative = (OverviewAlternative)statusProvider;
		IValidationStatus status = statusProvider.getSelfStatus();
		
		try{	
			IResource res = alternative.getSubResource(ToolchainUtils.KEY_FILE_OVERVIEW);
			//status.handleWarning(ERR_OVERVIEW_NULL, res == null, false, "Overview model file does not exist!");
			if(res == null){
				status.setIsValid(false);
				status.clearWarnings();
				return;
			}
						
			// validate model
			Resource overviewRes = alternative.getModelResource(ToolchainUtils.KEY_FILE_OVERVIEW);
			status.checkError(ERR_OVERVIEW_INVALID, overviewRes != null, true, "Overview model file has invalid content!");
			status.checkError(ERR_OVERVIEW_EMPTY, !overviewRes.getContents().isEmpty(), true, "Overview model file is empty!");
			
			Overview ov = (Overview)alternative.getModelRootSingle(ToolchainUtils.KEY_FILE_OVERVIEW);
			status.checkError(ERR_OVERVIEW_EMPTY, ov.getArchitecture() != null, true, "Overview model missing architecture!");
			status.checkError(ERR_OVERVIEW_EMPTY, ov.getDefinition() != null, true, "Overview model missing system specification!");
			status.checkError(ERR_OVERVIEW_EMPTY, ov.getDeployment() != null, true, "Overview model missing deployment!");
			status.checkError(ERR_OVERVIEW_EMPTY, ov.getDataTypes() != null, true, "Overview model missing data types definition!");
			
			status.checkError(ERR_OVERVIEW_EMPTY, !ov.getArchitecture().eContents().isEmpty(), true, "Overview model has empty architecture!");
			status.checkError(ERR_OVERVIEW_EMPTY, !ov.getDeployment().eContents().isEmpty(), true, "Overview model has empty deployment!");
			status.checkError(ERR_OVERVIEW_EMPTY, !ov.getDefinition().eContents().isEmpty(), true, "Overview model has empty system specification!");			
			
			//
			// Removed - problems with imported models (Extractor import) ...
			// 
			//Diagnostic diagnostic = Diagnostician.INSTANCE.validate(overviewRes.getContents().get(0));
			//boolean modelValid = diagnostic.getSeverity() == Diagnostic.OK;
			//status.handleWarning(ERR_OVERVIEW_INVALID, !modelValid, true, diagnostic.getMessage());
			
			status.setIsValid(true);
		}
		catch(ValidationException e){
			status.setIsValid(false);
		}
	}

}
