package eu.cloudscaleproject.env.method.viewer;

import java.beans.PropertyChangeSupport;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.notification.IToolStatus;
import eu.cloudscaleproject.env.common.notification.IToolStatusListener;
import eu.cloudscaleproject.env.method.common.method.Command;
import eu.cloudscaleproject.env.method.common.method.Link;
import eu.cloudscaleproject.env.method.common.method.LinkedObject;
import eu.cloudscaleproject.env.method.common.method.MethodFactory;
import eu.cloudscaleproject.env.method.common.method.MethodPackage;
import eu.cloudscaleproject.env.method.common.method.Section;
import eu.cloudscaleproject.env.method.common.method.StatusNode;
import eu.cloudscaleproject.env.method.common.method.Warning;

public class ToolStatusImpl implements IToolStatus{
	
	private final Object listenersLock = new Object();
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	protected final IProject project;
	protected final StatusNode statusNode;
	
	private final AdapterImpl adapter = new AdapterImpl(){
		
		public void notifyChanged(Notification msg) {
			Object feature = msg.getFeature();
			int type = msg.getEventType();
			
			if(feature == MethodPackage.Literals.SECTION__VALID){
				fireChange(IToolStatusListener.PROP_VALID, msg.getOldValue(), msg.getNewValue());
			}
			else if(feature == MethodPackage.Literals.SECTION__IN_PROGRESS){
				fireChange(IToolStatusListener.PROP_PROGRESS, msg.getOldValue(), msg.getNewValue());
			}
			else if(feature == MethodPackage.Literals.STATUS_NODE__DONE){
				fireChange(IToolStatusListener.PROP_DONE, msg.getOldValue(), msg.getNewValue());
			}
			else if(feature == MethodPackage.Literals.STATUS_NODE__DIRTY){
				fireChange(IToolStatusListener.PROP_DIRTY, msg.getOldValue(), msg.getNewValue());
			}
			else if (feature == MethodPackage.Literals.STATUS_NODE__INSTANCE_NAME) {
				fireChange(IToolStatusListener.PROP_INSTANCE, msg.getOldValue(), msg.getNewValue());
			}
			else if(feature == MethodPackage.Literals.STATUS_NODE__WARNINGS){
				if(type == Notification.ADD){
					fireChange(IToolStatusListener.PROP_WARNING_ADD, msg.getOldValue(), msg.getNewValue());
				}
				else if(type == Notification.ADD_MANY){
					fireChange(IToolStatusListener.PROP_WARNING_ADD, msg.getOldValue(), msg.getNewValue());
				}
				else if(type == Notification.REMOVE){
					fireChange(IToolStatusListener.PROP_WARNING_REMOVE, msg.getOldValue(), msg.getNewValue());
				}
				else if(type == Notification.REMOVE_MANY){
					fireChange(IToolStatusListener.PROP_WARNING_REMOVE, msg.getOldValue(), msg.getNewValue());
				}
			}
			else if(feature == MethodPackage.Literals.REQUIREMENT){
				fireChange(IToolStatusListener.PROP_REQUIREMENT_UPDATE, msg.getOldValue(), msg.getNewValue());
			}
		};
	};
	
	public ToolStatusImpl(IProject project, StatusNode statusNode) {
		
		this.project = project;
		this.statusNode = statusNode;
		
		this.statusNode.eSetDeliver(true);
		this.statusNode.eAdapters().add(adapter);
	}
	
	public IProject getProject(){
		return this.project;
	}
	
	public StatusNode getStatusNode(){
		return this.statusNode;
	}
		
	@Override
	public boolean hasMetRequirements() {
		if(this.statusNode instanceof LinkedObject){
			return hasMetRequirementsRecursive((LinkedObject)this.statusNode);
		}
		return true;
	}
	
	private boolean hasMetRequirementsRecursive(LinkedObject linkedObject){
		boolean out = true;
		for(Link link : linkedObject.getPrevious()){
			if(link.isRequired() && link.getStart() instanceof StatusNode){
				StatusNode required = (StatusNode)link.getStart();
				
				if(!out){break;}
				out &= required.getWarnings().isEmpty();
				if(!out){break;}
				out &= required.isDone();
				if(!out){break;}
				if(required instanceof LinkedObject){
					out &= hasMetRequirementsRecursive((LinkedObject)required);
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
	public synchronized boolean hasWarnings() {
		return !statusNode.getWarnings().isEmpty();
	}

	@Override
	public synchronized boolean isInProgress() {
		if(this.statusNode instanceof Section){
			return ((Section)this.statusNode).isInProgress();
		}
		return false;
	}
	
	@Override
	public synchronized boolean isDirty() {
		return statusNode.isDirty();
	}

	@Override
	public synchronized boolean isDone() {
		return statusNode.isDone() && hasMetRequirements() && statusNode.getWarnings().isEmpty();
	}
	
	public synchronized String getWarningMessage(String id){
		for(Warning w : statusNode.getWarnings()){
			if(w.getId().equals(id)){
				return w.getMessage();
			}
		}
		return null;
	}
	
	public synchronized String getWarningCommand(String id){
		for(Warning w : statusNode.getWarnings()){
			if(w.getId().equals(id)){
				return w.getCommands().get(0).getName();
			}
		}
		return null;
	}
	
	public synchronized String[] getWarningCommandParam(String id){
		return new String[]{};
	}
	
	public synchronized Set<String> getWarningIDs(){
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
	}

	@Override
	public synchronized void addWarning(String id, String message) {
		Warning w = MethodFactory.eINSTANCE.createWarning();
		w.setId(id);
		w.setMessage(message);
		statusNode.getWarnings().add(w);
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
	}

	@Override
	public synchronized void removeWarning(String id) {	
		Iterator<Warning> iter = statusNode.getWarnings().iterator();
		while(iter.hasNext()){
			Warning w = iter.next();
			if(w.getId().equals(id)){
				iter.remove();
			}
		}
	}
	
	@Override
	public synchronized void setInstanceName(String name){
		this.statusNode.setInstanceName(name);
	}

	@Override
	public synchronized void setMetRequirements(boolean metRequ) {
		throw new UnsupportedOperationException();
	}

	@Override
	public synchronized void setIsDone(boolean isDone) {
		
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
	}
	
	@Override
	public synchronized void setIsDirty(boolean dirty) {
		
		if(isDirty() == dirty){
			return;
		}
		
		this.statusNode.setDirty(dirty);
	}
	
	@Override
	public synchronized void setIsDirtyNextRecursive(boolean dirty){
		if(this.statusNode instanceof LinkedObject){
			LinkedObject ln = (LinkedObject)this.statusNode;
			
			for(Link link : ln.getNext()){
				LinkedObject next = link.getEnd();
				if(next instanceof StatusNode){
					if(((StatusNode)next).isDone()){
						if(((StatusNode)next).isDirty() != dirty){
							((StatusNode)next).setDirty(dirty);
						}
					}
				}
			}
		}
	}

	@Override
	public synchronized void setIsInProgress(boolean inProgress) {
		
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
		}
		else{
			throw new UnsupportedOperationException("setIsInProgress: Method ID: " + this.statusNode.getId());
		}
	}

	@Override
	public void addListener(IToolStatusListener listener) {
		synchronized (listenersLock) {
			pcs.addPropertyChangeListener(listener);
		}
	}

	@Override
	public void removeListener(IToolStatusListener listener) {
		synchronized (listenersLock) {
			pcs.removePropertyChangeListener(listener);
		}
	}
	
	public void fireChange(final String prop, final Object oldValue, final Object newValue){
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				synchronized (listenersLock) {
					pcs.firePropertyChange(prop, oldValue, newValue);
				}
			}
		});
	}
	
	public synchronized void dispose(){
		this.statusNode.eAdapters().remove(adapter);
	}

	@Override
	public synchronized void handleWarning(String id, boolean expression,
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
	public synchronized void handleWarning(String id, boolean expression,
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
	public synchronized void handleWarning(String id, boolean expression,
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
