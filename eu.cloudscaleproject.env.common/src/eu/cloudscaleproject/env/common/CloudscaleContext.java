package eu.cloudscaleproject.env.common;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;

public class CloudscaleContext {
	
	private static IEclipseContext csContext = EclipseContextFactory.create();
	
	static{
		csContext.activate();
	}

	/*
	public static IEclipseContext getContext(){
		if(csContext == null){
			IEclipseContext context = EclipseContextFactory.getServiceContext(Activator.plugin.getBundle().getBundleContext());
			csContext = context.createChild();
			csContext.activate();
		}
		return csContext;
	}
	*/
	
	public static IEclipseContext getContext(){
		return csContext;
	}
	
	public static void setParentContext(IEclipseContext context){
		csContext.setParent(context);
	}
	
	public static void inject(Object object){
		ContextInjectionFactory.inject(object, csContext);
	}
	
	public static <T> T createInstance(Class<T> clazz){
		T instance = ContextInjectionFactory.make(clazz, csContext);
		csContext.set(clazz, instance);
		return instance;
	}
	
	public static <T> void register(Class<T> clazz){
		csContext.set(clazz, ContextInjectionFactory.make(clazz, csContext));
	}
	
}
