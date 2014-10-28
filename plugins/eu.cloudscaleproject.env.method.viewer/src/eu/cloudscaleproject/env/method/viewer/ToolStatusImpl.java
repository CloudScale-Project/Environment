package eu.cloudscaleproject.env.method.viewer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.resource.Resource;

import eu.cloudscaleproject.env.common.notification.IToolStatus;
import eu.cloudscaleproject.env.common.notification.IToolStatusListener;
import eu.cloudscaleproject.env.method.common.method.Command;
import eu.cloudscaleproject.env.method.common.method.LinkedNode;
import eu.cloudscaleproject.env.method.common.method.MethodFactory;
import eu.cloudscaleproject.env.method.common.method.MethodPackage;
import eu.cloudscaleproject.env.method.common.method.Section;
import eu.cloudscaleproject.env.method.common.method.SectionConnector;
import eu.cloudscaleproject.env.method.common.method.StatusNode;
import eu.cloudscaleproject.env.method.common.method.Warning;

public class ToolStatusImpl implements IToolStatus{
	
	private final HashSet<IToolStatusListener> listeners = new HashSet<IToolStatusListener>();
	
	private StatusNode statusNode = null;
	
	private final AdapterImpl adapter = new AdapterImpl(){
		
		public void notifyChanged(Notification msg) {
			Object feature = msg.getFeature();
			int type = msg.getEventType();
			
			if(feature == MethodPackage.Literals.SECTION__VALID){
				fireChange(IToolStatusListener.PROP_VALID);
			}
			else if(feature == MethodPackage.Literals.SECTION__IN_PROGRESS){
				fireChange(IToolStatusListener.PROP_PROGRESS);
			}
			else if(feature == MethodPackage.Literals.STATUS_NODE__DONE){
				fireChange(IToolStatusListener.PROP_DONE);
			}
			else if(feature == MethodPackage.Literals.STATUS_NODE__WARNINGS){
				if(type == Notification.ADD){
					fireChange(IToolStatusListener.PROP_WARNING_ADD);
				}
				else if(type == Notification.ADD_MANY){
					fireChange(IToolStatusListener.PROP_WARNING_ADD);
				}
				else if(type == Notification.REMOVE){
					fireChange(IToolStatusListener.PROP_WARNING_REMOVE);
				}
				else if(type == Notification.REMOVE_MANY){
					fireChange(IToolStatusListener.PROP_WARNING_REMOVE);
				}
			}
			else if(feature == MethodPackage.Literals.REQUIREMENT){
				fireChange(IToolStatusListener.PROP_REQUIREMENT_UPDATE);
			}
		};
		
	};
	
	public void setStatusNode(StatusNode section){
		
		if(this.statusNode == section){
			return;
		}
		
		if(this.statusNode != null){
			this.statusNode.eAdapters().remove(adapter);
			this.statusNode = null;
		}
		
		if(section != null){
			this.statusNode = section;
			this.statusNode.eAdapters().add(adapter);
		}
		
		fireChange(IToolStatusListener.PROP_UPDATE_ALL);
	}
		
	@Override
	public boolean hasMetRequirements() {
		if(this.statusNode instanceof LinkedNode){
			return hasMetRequirementsRecursive((LinkedNode)this.statusNode);
		}
		return true;
	}
	
	private boolean hasMetRequirementsRecursive(LinkedNode linkedNode){
		boolean out = true;
		for(SectionConnector sc : linkedNode.getPrevious()){
			if(sc.isRequired() && sc.getStart() instanceof StatusNode){
				StatusNode required = (StatusNode)sc.getStart();
				
				if(!out){break;}
				out &= required.getWarnings().isEmpty();
				if(!out){break;}
				out &= required.isDone();
				if(!out){break;}
				if(required instanceof LinkedNode){
					out &= hasMetRequirementsRecursive((LinkedNode)required);
				}
				else{
					out &= true;
				}
				if(!out){break;}
			}
		}
		return out;
	}

	@Override
	public boolean hasWarnings() {
		return !statusNode.getWarnings().isEmpty();
	}

	@Override
	public boolean isInProgress() {
		if(this.statusNode instanceof Section){
			return ((Section)this.statusNode).isInProgress();
		}
		return false;
	}
	
	@Override
	public boolean isDirty() {
		return statusNode.isDirty();
	}

	@Override
	public boolean isDone() {
		return statusNode.isDone();
	}
	
	public String getWarningMessage(String id){
		for(Warning w : statusNode.getWarnings()){
			if(w.getId().equals(id)){
				return w.getMessage();
			}
		}
		return null;
	}
	
	public String getWarningCommand(String id){
		for(Warning w : statusNode.getWarnings()){
			if(w.getId().equals(id)){
				return w.getCommands().get(0).getName();
			}
		}
		return null;
	}
	
	public String[] getWarningCommandParam(String id){
		return new String[]{};
	}
	
	public Set<String> getWarningIDs(){
		HashSet<String> ids = new HashSet<String>();
		for(Warning w : statusNode.getWarnings()){
			ids.add(w.getId());
		}
		return ids;
	}

	@Override
	public synchronized void clearWarnings() {
		
		if(statusNode.getWarnings().isEmpty()){
			return;
		}
		
		statusNode.getWarnings().clear();		
		save();
	}

	@Override
	public synchronized void addWarning(String id, String message) {
		Warning w = MethodFactory.eINSTANCE.createWarning();
		w.setId(id);
		w.setMessage(message);
		statusNode.getWarnings().add(w);
		save();
	}
	
	public synchronized void addWarning(String id, String message, String command){
		Warning w = MethodFactory.eINSTANCE.createWarning();
		w.setId(id);
		w.setMessage(message);
		
		//add command
		Command comm = MethodFactory.eINSTANCE.createCommand();
		comm.setName(command);
		w.getCommands().add(comm);
		
		statusNode.getWarnings().add(w);
		save();
	}
	
	public synchronized void addWarning(String id, String message, String command, String... param){
		Warning w = MethodFactory.eINSTANCE.createWarning();
		w.setId(id);
		w.setMessage(message);
		
		//add command
		Command comm = MethodFactory.eINSTANCE.createCommand();
		comm.setName(command);
		for(String p : param){
			comm.getCommandParam().add(p);
		}
		w.getCommands().add(comm);
		
		statusNode.getWarnings().add(w);
		save();
	}

	@Override
	public synchronized void removeWarning(String id) {
		
		boolean removed = false;
		
		Iterator<Warning> iter = statusNode.getWarnings().iterator();
		while(iter.hasNext()){
			Warning w = iter.next();
			if(w.getId().equals(id)){
				iter.remove();
				removed = true;
			}
		}
		
		if(removed){
			save();
		}
	}

	@Override
	public void setMetRequirements(boolean metRequ) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setIsDone(boolean isDone) {
		
		if(isDone == true){
			if(this.statusNode instanceof Section){
				Section section = (Section)this.statusNode;
				if(section.isInProgress()){
					setIsInProgress(false);
				}
			}
			if(this.statusNode.isDirty()){
				this.statusNode.setDirty(false);
			}
			if(!this.statusNode.getWarnings().isEmpty()){
				this.statusNode.getWarnings().clear();
			}
		}
		
		if(isDone() == isDone){
			return;
		}
		
		this.statusNode.setDone(isDone);
		save();
	}
	
	@Override
	public void setIsDirty(boolean dirty) {
		
		if(isDirty() == dirty){
			return;
		}
		
		this.statusNode.setDirty(dirty);
		save();
	}
	
	@Override
	public void setIsDirtyNextRecursive(boolean dirty){
		if(this.statusNode instanceof LinkedNode){
			LinkedNode ln = (LinkedNode)this.statusNode;
			
			for(SectionConnector sc : ln.getNext()){
				LinkedNode next = sc.getEnd();
				if(next instanceof StatusNode){
					if(((StatusNode)next).isDone()){
						if(((StatusNode)next).isDirty() != dirty){
							((StatusNode)next).setDirty(dirty);
						}
					}
				}
			}
		}
		save();
	}

	@Override
	public void setIsInProgress(boolean inProgress) {
		
		if(isInProgress() == inProgress){
			return;
		}
		
		if(this.statusNode instanceof Section){
			Section section = (Section)this.statusNode;
			if(inProgress){
				if(section.isDone()){
					section.setDone(false);
				}
			}
			if(section.isInProgress() != inProgress){
				section.setInProgress(inProgress);
			}
			save();
		}
		else{
			throw new UnsupportedOperationException("setIsInProgress: Method ID: " + this.statusNode.getId());
		}
	}
	
	private void save(){
		if(statusNode.eResource() != null){
			Resource res = statusNode.eResource();
			try {
				res.save(null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void addListener(IToolStatusListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void removeListener(IToolStatusListener listener) {
		this.listeners.remove(listener);
	}
	
	public void fireChange(String prop){
		for(IToolStatusListener listener : listeners){
			listener.notifie(prop, this);
		}
	}
	
	public void dispose(){
		if(this.statusNode != null){
			this.statusNode.eAdapters().remove(adapter);
			this.statusNode = null;
		}
	}

	@Override
	public void handleWarning(String id, boolean expression,
			boolean throwException, String message)
			throws IllegalStateException {
		
		removeWarning(id);
		
		if(expression){
			addWarning(id, message);
			if(throwException){
				throw new IllegalStateException();
			}
		}
	}

	@Override
	public void handleWarning(String id, boolean expression,
			boolean throwException, String message, String command)
			throws IllegalStateException {
		
		removeWarning(id);
		
		if(expression){
			addWarning(id, message, command);
			if(throwException){
				throw new IllegalStateException();
			}
		}
	}

	@Override
	public void handleWarning(String id, boolean expression,
			boolean throwException, String message, String command,
			String... param) throws IllegalStateException {
		
		removeWarning(id);
		
		if(expression){
			addWarning(id, message, command, param);
			if(throwException){
				throw new IllegalStateException();
			}
		}
		
	}
}
