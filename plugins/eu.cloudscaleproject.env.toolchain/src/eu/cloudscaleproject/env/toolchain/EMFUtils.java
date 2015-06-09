package eu.cloudscaleproject.env.toolchain;

import org.eclipse.emf.common.util.Diagnostic;

import eu.cloudscaleproject.env.common.notification.IValidationStatus;

public class EMFUtils {

	public static void fillWarnings(IValidationStatus status, Diagnostic diagnostic){
		
		for(int i=0; i<diagnostic.getChildren().size(); i++){
			
			Diagnostic d = diagnostic.getChildren().get(i);
			status.addWarning(d.toString(), d.getSeverity(), d.getMessage());
			
			for(Diagnostic ds : d.getChildren()){
				fillWarnings(status, ds);
			}
		}
	}
	
}
