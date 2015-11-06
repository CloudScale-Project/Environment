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
		for(IValidationStatus status : provider.getSubStatuses()){
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
	
	private WarningNode[] createWarningNodes(Object parent, Warning... warnings){
		
		List<WarningNode> warningNodes = new ArrayList<WarningNode>();
		
		for(Warning w : warnings){
			WarningNode wn = new WarningNode(w);
			wn.setParent(parent);
			warningNodes.add(wn);
		}
		
		return warningNodes.toArray(new WarningNode[warningNodes.size()]);
	}

	@Override
	public Object[] getElements(Object inputElement) {
		
		List<Object> providers = new ArrayList<>();
		
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
		
		if(providers.isEmpty()){
			
			providers.add("Current Explorer selection does not contain any warnings.");
			providers.add("Selecting project node in the Explorer, reveals all issues regarding this project.");
			providers.add("Selecting alternative node in the Explorer, reveals all issues regarding this alternative.");
		}
		
		return providers.toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		
		if(parentElement instanceof IValidationStatusProvider){
			IValidationStatusProvider sp = (IValidationStatusProvider)parentElement;
			
			List<Object> children = new ArrayList<Object>();
			
			//add self warnings
			WarningNode[] warningNodes = createWarningNodes(parentElement, sp.getSelfStatus().getWarnings());			
			children.addAll(Arrays.asList(warningNodes));
			
			for(IValidationStatus stat : sp.getSubStatuses()){
				if(hasWarnings(stat)){
					children.add(stat);
				}
			}
			
			return children.toArray();
		}
		if(parentElement instanceof IValidationStatus){
			IValidationStatus sp = (IValidationStatus)parentElement;
			return createWarningNodes(sp, sp.getWarnings());
		}
			
		return null;
	}

	@Override
	public Object getParent(Object element) {
		if(element instanceof IValidationStatus){
			IValidationStatus stat = (IValidationStatus)element;
			return stat.getProvider();
		}
		if(element instanceof WarningNode){
			WarningNode wn = (WarningNode)element;
			return wn.getParent();
		}
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
