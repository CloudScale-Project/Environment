package eu.cloudscaleproject.env.analyser.editors.composite;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.util.ISaveableComposite;

public class ConfigSloComposite extends Composite implements ISaveableComposite{
	
	private final ConfAlternative alternative;

	public ConfigSloComposite(ConfAlternative input, Composite parent, int style) {
		super(parent, style);
		this.alternative = input;
	}
	
	@Override
	public void update() {
		
		boolean hasMeasuringPoints = false;
		
		IFile file = alternative.getMeasuringPoints();

		if(file != null && file.exists()){
			Resource res = ExplorerProjectPaths.getEmfResource(alternative.getResourceSet(), file);
			if(!res.getContents().isEmpty()){
				res.getContents().get(0);
			}
		}
		
		super.update();
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

}
