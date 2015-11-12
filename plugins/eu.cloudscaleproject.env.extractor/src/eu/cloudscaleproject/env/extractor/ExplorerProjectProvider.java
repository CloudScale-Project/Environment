package eu.cloudscaleproject.env.extractor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.contexts.IEclipseContext;

import eu.cloudscaleproject.env.extractor.alternatives.InputAlternative;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerContentRetriever;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ExplorerProjectProvider extends ExplorerContentRetriever{
	

	private InputAlternative alternative;
	
	private PropertyChangeListener projectListener = new PropertyChangeListener()
	{
		
		@Override
		public void propertyChange(PropertyChangeEvent evt)
		{
			if (evt.getPropertyName().equals(InputAlternative.KEY_INPUT_PROJECT))
			{
				refresh();
			}
		}
	};
	

	@Override
	public List<Object> getChildren() {
		List<Object> out = new ArrayList<Object>();

		IProject extractedProject = alternative.getExtractedProject();
		if (extractedProject == null || !extractedProject.exists())
			return out;

		out.add(alternative.getExtractedProject());
		return out;
	}

	@Override
	public void initialize(String nodeID, IEclipseContext context) {
		alternative = (InputAlternative)context.get(IEditorInputResource.class);
		if(alternative != null){
			alternative.addPropertyChangeListener(projectListener);
		}
	}
	

	@Override
	public void dispose() {

	}

}
