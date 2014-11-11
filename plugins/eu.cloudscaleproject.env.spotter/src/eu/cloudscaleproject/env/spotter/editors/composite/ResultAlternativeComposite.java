package eu.cloudscaleproject.env.spotter.editors.composite;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.spotter.ResourceUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.util.AbstractSidebarMenuComposite;

public class ResultAlternativeComposite extends AbstractSidebarMenuComposite{

	private final String[] sections = new String[]{"Results:"};
	private final EditorInputFolder editorInput;
	
	public ResultAlternativeComposite(Composite parent, int style, EditorInputFolder editorInput) {
		super(parent, style);
		
		this.editorInput = editorInput;
		
		init();
	}
	
	private boolean validateResults(EditorInputFolder editorInput){
		
		if(editorInput == null){
			return false;
		}
		
		if(!editorInput.getResource().exists()){
			return false;
		}
		
		if(!editorInput.getResource().getFile("results.ser").exists()){
			return false;
		}
		
		if(!editorInput.getResource().getFile("SpotterReport.txt").exists()){
			return false;
		}
		
		return true;
	}

	@Override
	public Composite createInputComposite(IEditorInput input, Composite parent,
			int style) {
		
		String inputResourceName = ((EditorInputFolder)input).getProperty(ResourceUtils.KEY_PARENT_EDITOR_RESOURCE);
		return new ResultComposite(parent, style, inputResourceName, editorInput.getResource().getFolder(input.getName()));
	}

	@Override
	public List<IEditorInput> getInputs(String section) {
		List<IEditorInput> out = new ArrayList<IEditorInput>();
		
		if(validateResults(editorInput)){
			return out;
		}
		
		if(section.equals(sections[0])){
			try {
				for(IResource res : editorInput.getResource().members()){
					if(res instanceof IFolder){
						EditorInput ei = new EditorInput(res.getName());
						out.add(ei);
					}
				}
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return out;
	}

	@Override
	public String[] getSidebarSections() {
		return sections;
	}

}
