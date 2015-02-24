package eu.cloudscaleproject.env.analyser.editors.composite;

import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.toolchain.util.ISaveableComposite;

public class ConfigSloComposite extends Composite implements ISaveableComposite{
	
	private final ConfAlternative alternative;

	public ConfigSloComposite(ConfAlternative input, Composite parent, int style) {
		super(parent, style);
		this.alternative = input;
	}
	
	@Override
	public void update() {
		
		/*
		boolean hasMeasuringPoints = false;
		
		List<MeasuringPoint> mps = alternative.getMeasuringPoints(PcmmeasuringpointPackage.Literals.ACTIVE_RESOURCE_MEASURING_POINT);
		
		List<IFile> fileMPoints = alternative.getMeasuringPoints();

		for(IFile file : fileMPoints){
			if(file != null && file.exists()){
				Resource res = ExplorerProjectPaths.getEmfResource(alternative.getResourceSet(), file);
				if(!res.getContents().isEmpty()){
					res.getContents().get(0);
				}
			}
		}
		*/
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
