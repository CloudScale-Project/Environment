package eu.cloudscaleproject.env.toolchain.resources.types;

import java.util.EventObject;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;

public class EditorInputEMF extends EditorInputFolder{
	
	private static final long serialVersionUID = 1L;

	public static final String PROP_COMMAND_STACK_CHANGED = EditorInputEMF.class.getName() + ".commandStackChanged";
	
	protected final BasicCommandStack commandStack;
	protected final AdapterFactoryEditingDomain editingDomain;
	protected final AdapterFactory factory;
	
	protected final ResourceSet resSet = new ResourceSetImpl();

	public EditorInputEMF(IProject project, IFolder folder, AdapterFactory factory) {
		super(project, folder);
		this.factory = factory;
		
		commandStack = new BasicCommandStack();
		commandStack.addCommandStackListener(new CommandStackListener() {
			
			@Override
			public void commandStackChanged(EventObject event) {
				firePropertyChange(PROP_COMMAND_STACK_CHANGED, false, true);
			}
		});
		
		editingDomain = new AdapterFactoryEditingDomain(factory, commandStack, resSet);

	}
	
	public ResourceSet getResourceSet(){
		return this.resSet;
	}
	
	public EditingDomain getEditingDomain(){
		return editingDomain;
	}
	
	public AdapterFactory getAdapterFactory(){
		return factory;
	}
	
	@Override
	public synchronized final void save() {
		super.save();
		commandStack.saveIsDone();
	}
	
	@Override
	public boolean isDirty() {
		return super.isDirty() || ((BasicCommandStack)editingDomain.getCommandStack()).isSaveNeeded();
	}
}
