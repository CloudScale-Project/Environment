package eu.cloudscaleproject.env.toolchain;

import org.eclipse.emf.common.util.Diagnostic;

import eu.cloudscaleproject.env.common.notification.IValidationStatus;

public class EMFUtils {

	public static void fillWarnings(IValidationStatus status, Diagnostic diagnostic){
		
		for(int i=0; i<diagnostic.getChildren().size(); i++){
			
			Diagnostic d = diagnostic.getChildren().get(i);
			
			int severity = 2;
			if(d.getSeverity() == Diagnostic.INFO){
				severity = IValidationStatus.SEVERITY_INFO;
			}
			else if(d.getSeverity() == Diagnostic.WARNING){
				severity = IValidationStatus.SEVERITY_WARNING;
			}
			else if(d.getSeverity() == Diagnostic.ERROR){
				severity = IValidationStatus.SEVERITY_ERROR;
			}
			
			status.addWarning(d.toString(), severity, d.getMessage());
			for(Diagnostic ds : d.getChildren()){
				fillWarnings(status, ds);
			}
		}
	}
	
}
