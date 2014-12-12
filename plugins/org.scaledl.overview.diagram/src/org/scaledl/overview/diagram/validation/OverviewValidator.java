package org.scaledl.overview.diagram.validation;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.resource.Resource;
import org.scaledl.overview.Overview;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.notification.IToolStatus;
import eu.cloudscaleproject.env.common.notification.StatusManager.Tool;
import eu.cloudscaleproject.env.common.notification.ToolValidator;

public class OverviewValidator extends ToolValidator {
	
	//private static final String ERR_OVERVIEW_NULL = "org.scaledl.overview.diagram.validation.OverviewValidator.overviewnull";
	private static final String ERR_OVERVIEW_EMPTY = "org.scaledl.overview.diagram.validation.OverviewValidator.overviewempty";
	private static final String ERR_OVERVIEW_INVALID = "org.scaledl.overview.diagram.validation.OverviewValidator.overviewinvalid";

	@Override
	public String getToolID() {
		return Tool.OVERVIEW.getID();
	}

	@Override
	public IResource[] getDependantResources(IProject project) {
		return new IResource[]{ExplorerProjectPaths
					.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_SCALEDL)};
	}

	@Override
	public boolean doValidate(IProject project, IToolStatus status) {
		boolean isValid = true;
				
		try{
			IResource res = ExplorerProjectPaths.getProjectFile(project, ExplorerProjectPaths.KEY_FILE_OVERVIEW_MODEL);
			//status.handleWarning(ERR_OVERVIEW_NULL, res == null, false, "Overview model file does not exist!");
			if(res == null){
				status.setIsInProgress(false);
				status.setIsDone(false);
				status.clearWarnings();
				return false;
			}
			
			// validate model
			Resource overviewRes = ExplorerProjectPaths.getProjectEmfResource(project, ExplorerProjectPaths.KEY_FILE_OVERVIEW_MODEL);
			status.handleWarning(ERR_OVERVIEW_INVALID, overviewRes == null, true, "Overview model file has invalid content!");
			status.handleWarning(ERR_OVERVIEW_EMPTY, overviewRes.getContents().isEmpty(), true, "Overview model file is empty!");
			
			Overview ov = (Overview)overviewRes.getContents().get(0);
			status.handleWarning(ERR_OVERVIEW_EMPTY, ov.getArchitecture() == null, true, "Overview model missing architecture!");
			status.handleWarning(ERR_OVERVIEW_EMPTY, ov.getDefinition() == null, true, "Overview model missing system specification!");
			status.handleWarning(ERR_OVERVIEW_EMPTY, ov.getDeployment() == null, true, "Overview model missing deployment!");
			status.handleWarning(ERR_OVERVIEW_EMPTY, ov.getDataTypes() == null, true, "Overview model missing data types definition!");
			
			status.handleWarning(ERR_OVERVIEW_EMPTY, ov.getArchitecture().eContents().isEmpty(), true, "Overview model has empty architecture!");
			status.handleWarning(ERR_OVERVIEW_EMPTY, ov.getDeployment().eContents().isEmpty(), true, "Overview model has empty deployment!");
			status.handleWarning(ERR_OVERVIEW_EMPTY, ov.getDefinition().eContents().isEmpty(), true, "Overview model has empty system specification!");

			
			status.setIsInProgress(true);
			
			//
			// Removed - problems with imported models (Extractor import) ...
			// 
			//Diagnostic diagnostic = Diagnostician.INSTANCE.validate(overviewRes.getContents().get(0));
			//boolean modelValid = diagnostic.getSeverity() == Diagnostic.OK;
			//status.handleWarning(ERR_OVERVIEW_INVALID, !modelValid, true, diagnostic.getMessage());
			
			status.setIsDone(true);
		}
		catch(IllegalStateException e){
			isValid = false;
		}
		
		return isValid;
	}

}
