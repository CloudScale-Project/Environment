package eu.cloudscaleproject.env.spotter.validation;

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.spotter.eclipse.ui.Activator;
import org.spotter.eclipse.ui.ServiceClientWrapper;
import org.spotter.eclipse.ui.UICoreException;
import org.spotter.eclipse.ui.model.xml.MeasurementEnvironmentFactory;
import org.spotter.shared.environment.model.XMeasurementEnvironment;

import eu.cloudscaleproject.env.common.notification.IResourceValidator;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.ValidationException;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class InputValidator implements IResourceValidator {
	
	private final String ERROR = "eu.cloudscaleproject.env.spotter.validation.InputValidator.error.input";
	private final String MESSAGE_PATTERN = "Alternative {0}: {1}";
	
	@Override
	public String getID() {
		return ToolchainUtils.SPOTTER_DYN_INPUT_ID;
	}
	
	@Override
	public void validate(IProject project, IValidationStatusProvider statusProvider) {
		
		IValidationStatus status = statusProvider.getSelfStatus();
		IEditorInputResource selectedRes = (IEditorInputResource)statusProvider;
		
		//validate selected input alternatives
		try {								
			// check connection
			ServiceClientWrapper client = Activator.getDefault().getClient(selectedRes.getResource().getName());
			String hostname = selectedRes.getProperty("hostname");
			String port = selectedRes.getProperty("port");
			
			status.check(ERROR, hostname != null && hostname.isEmpty(), true, 
					MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(),"Connection hostname is not specified!"));
			status.check(ERROR, port != null && !port.isEmpty(), true, 
					MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(),"Connection port is not specified!"));
			
			client.saveServiceClientSettings(hostname, port);
			
			status.check(ERROR, client.testConnection(false), true, 
					MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(),"Connection to server can not be established!"));
			
			// check input files
			IFolder folder = (IFolder)selectedRes.getResource();
			IFile confFile = folder.getFile("mEnv.xml");
			status.check(ERROR, confFile.exists(), true, 
					MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(),"Instrumentation controller and Measurement controller not specified!"));
			
			MeasurementEnvironmentFactory factory = MeasurementEnvironmentFactory.getInstance();
				
			XMeasurementEnvironment confEnv = factory.parseXMLFile(confFile.getLocation().toString());
			status.check(ERROR, confEnv != null, true, 
					MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(),"Invalid input configuration!"));
			
			List<?> ic = confEnv.getInstrumentationController();
			List<?> mc = confEnv.getMeasurementController();
			
			status.check(ERROR, ic != null && !ic.isEmpty(), true, 
					MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(),"Instrumentation controller not specified!"));					
			status.check(ERROR, mc != null && !mc.isEmpty(), true,
					MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(),"Measurement controller not specified!"));
				
		} catch (ValidationException e) {
			status.setIsValid(false);
			return;
		} catch (UICoreException e) {
			e.printStackTrace();
		}
		
		status.setIsValid(true);
	}

}
