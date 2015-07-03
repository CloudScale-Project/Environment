package eu.cloudscaleproject.env.toolchain.ui.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatus.Warning;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;

public class StatusContentProvider implements ITreeContentProvider{

	@Override
	public void dispose() {		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {		
	}
	
	private List<Warning> collectWarnings(IValidationStatusProvider provider){
		List<Warning> warnings = new ArrayList<Warning>();
		
		warnings.addAll(Arrays.asList(provider.getSelfStatus().getWarnings()));
		for(IValidationStatus status : provider.getStatus()){
			warnings.addAll(Arrays.asList(status.getWarnings()));
		}
		return warnings;
	}
	
	private boolean hasWarnings(IValidationStatusProvider sp){
		return !collectWarnings(sp).isEmpty();
	}
	
	private boolean hasWarnings(IValidationStatus stat){
		return stat.getWarnings().length > 0;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		
		List<IValidationStatusProvider> providers = new ArrayList<>();
		
		//add providers with warnings only
		if(inputElement instanceof List){
			List<?> list = (List<?>)inputElement;
			for(Object o : list){
				if(o instanceof IValidationStatusProvider){
					IValidationStatusProvider sp = (IValidationStatusProvider)o;
					if(hasWarnings(sp)){
						providers.add(sp);
					}
				}
			}
		}
		
		return providers.toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		
		if(parentElement instanceof IValidationStatusProvider){
			IValidationStatusProvider sp = (IValidationStatusProvider)parentElement;
			
			//remove self status
			List<IValidationStatus> statList = new ArrayList<IValidationStatus>();
			
			for(IValidationStatus stat : sp.getStatus()){
				if(hasWarnings(stat) && stat != sp.getSelfStatus()){
					statList.add(stat);
				}
			}
			
			return statList.toArray();
		}
		if(parentElement instanceof IValidationStatus){
			IValidationStatus sp = (IValidationStatus)parentElement;
			return sp.getWarnings();
		}
			
		return null;
	}

	@Override
	public Object getParent(Object element) {
		if(element instanceof IValidationStatus){
			IValidationStatus stat = (IValidationStatus)element;
			return stat.getProvider();
		}
		//TODO: can not get warning parent!
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if(element instanceof IValidationStatusProvider){
			return hasWarnings((IValidationStatusProvider)element);
		}
		if(element instanceof IValidationStatus){
			IValidationStatus stat = (IValidationStatus)element;
			return hasWarnings(stat);
		}
			
		return false;
	}

}
