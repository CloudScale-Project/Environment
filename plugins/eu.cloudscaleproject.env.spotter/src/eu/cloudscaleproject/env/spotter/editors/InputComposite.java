package eu.cloudscaleproject.env.spotter.editors;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.spotter.ResourceUtils;
import eu.cloudscaleproject.env.spotter.editors.composite.InputAlternativeComposite;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

public class InputComposite extends SidebarEditorComposite implements PropertyChangeListener{
	
	private final String[] sections = new String[]{"Inputs:"};
	private final ResourceProvider resourceProvider;
		
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public InputComposite(IProject project, Composite parent, int style) {
		super(parent, style);
		
		this.resourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.SPOTTER_DYN_INPUT_ID);
		this.resourceProvider.addListener(this);
		
		setResourceProvider(resourceProvider);
		setContentProvider(new SidebarContentProvider() {
			
			@Override
			public String[] getSections() {
				return sections;
			}
			
			@Override
			public String getSection(IEditorInputResource resource) {
				return sections[0];
			}
			
			@Override
			public Composite createComposite(Composite parent, int style,
					IEditorInputResource resource) {
				
				return new InputAlternativeComposite(parent, style, (EditorInputFolder)resource);
			}
		});
		
		addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent e) {
				resourceProvider.removeListener(InputComposite.this);
			}
		});
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg) {
		if(ResourceProvider.PROP_RESOURCE_CREATED.equals(arg.getPropertyName())){
			IEditorInputResource eir = (IEditorInputResource)arg.getNewValue();
			eir.setProperty(ResourceUtils.KEY_CLIENT_NAME, eir.getResource().getName());
		}
	}
}
