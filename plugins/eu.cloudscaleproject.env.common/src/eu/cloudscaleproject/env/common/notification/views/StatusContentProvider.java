package eu.cloudscaleproject.env.common.notification.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatus.Warning;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;

public class StatusContentProvider implements IStructuredContentProvider{

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

	@Override
	public Object[] getElements(Object inputElement) {
		
		List<Warning> warnings = new ArrayList<Warning>();
		
		if(inputElement instanceof List){
			List<?> providers = (List<?>)inputElement;
			for(Object o : providers){
				if(o instanceof IValidationStatusProvider){
					warnings.addAll(collectWarnings((IValidationStatusProvider)o));
				}
			}
		}
		
		return warnings.toArray();
	}
}
