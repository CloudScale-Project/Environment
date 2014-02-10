package eu.cloudscaleproject.env.toolchain;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.e4.core.contexts.IEclipseContext;

import eu.cloudscaleproject.env.toolchain.definition.IAnalyser;
import eu.cloudscaleproject.env.toolchain.definition.IExtractor;
import eu.cloudscaleproject.env.toolchain.definition.ISpotter;

public class RegistryEventListener implements IRegistryEventListener{

	private IEclipseContext context;
	
	public RegistryEventListener(IEclipseContext context) {
		this.context = context;
	}
	
	@Override
	public void added(IExtension[] extensions) {
		for (IExtension extension : extensions) {
			try {
				for (IConfigurationElement e : extension.getConfigurationElements()) {
					System.out.println("Adding extension in toolchain context");
					System.out.println("Adding extension id = " + e.getAttribute("id"));
					System.out.println("Adding extension name = "+ e.getAttribute("name"));
					final Object o = e.createExecutableExtension("class");
					
					if(o instanceof IAnalyser){
						this.context.set(IAnalyser.class, (IAnalyser)o);
					}
					else if(o instanceof IExtractor){
						this.context.set(IExtractor.class, (IExtractor)o);
					}
					else if(o instanceof ISpotter){
						this.context.set(ISpotter.class, (ISpotter)o);
					}
				}
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	@Override
	public void removed(IExtension[] extensions) {
		for (IExtension extension : extensions) {
			try {
				for (IConfigurationElement e : extension.getConfigurationElements()) {
					System.out.println("Removing extension in toolchain context");
					System.out.println("Removing extension id = " + e.getAttribute("id"));
					System.out.println("Removing extension name = "+ e.getAttribute("name"));
					final Object o = e.createExecutableExtension("class");
					
					if(o instanceof IAnalyser){
						this.context.set(IAnalyser.class, null);
					}
					else if(o instanceof IExtractor){
						this.context.set(IExtractor.class, null);
					}
					else if(o instanceof ISpotter){
						this.context.set(ISpotter.class, null);
					}
				}
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	@Override
	public void added(IExtensionPoint[] extensionPoints) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removed(IExtensionPoint[] extensionPoints) {
		// TODO Auto-generated method stub
		
	}
	
}
