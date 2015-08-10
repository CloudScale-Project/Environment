 
package eu.cloudscaleproject.env.analyser.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;

import eu.cloudscaleproject.env.analyser.ModelUtils;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;

public class CreateModelHandler {
	
	@Execute
	public void execute(EditorInputEMF alternative, 
						@Named("eu.cloudscaleproject.env.analyser.commandparameter.fileextension") String extension) {
		ModelUtils.executeCreateModels(alternative, new ModelType[]{ModelType.getModelType(extension)});
	}
		
}