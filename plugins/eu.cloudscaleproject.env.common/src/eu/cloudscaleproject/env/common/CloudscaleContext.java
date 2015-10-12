package eu.cloudscaleproject.env.common;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.translation.TranslationService;

public class CloudscaleContext {
	
	//model named objects
	
	//TODO: find better solution
	//HACK: the value of the ACTIVE_ALTERNATIVE is deliberately set to IEditorInputResource full class path, 
	//		so it can be injected as a class object instead of 'named' object.  
	public static final String ACTIVE_ALTERNATIVE = "eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource";
	
	private static IEclipseContext csContext = EclipseContextFactory.create("CloudscaleContext");
	private static IEclipseContext apContext = null;
	
	public static void initialize(IEclipseContext applicationContext){
		
		apContext = applicationContext;
		
		//bug fix...
		apContext.set(TranslationService.LOCALE, "en");
		csContext.setParent(apContext);
	}
	
	public static IEclipseContext getCustomContext(){
		return csContext;
	}
	
	public static IEclipseContext getActiveContext(){
		
		if(apContext == null){
			throw new IllegalStateException("Main application context is not initialized jet.");
		}
		
		return apContext.getActiveLeaf();
	}
	
	public static IEclipseContext getGlobalContext(){
		
		if(apContext == null){
			throw new IllegalStateException("Main application context is not initialized jet.");
		}
		
		return apContext;
	}
	
	public static void inject(Object object){
		ContextInjectionFactory.inject(object, getActiveContext());
	}
	
	public static void inject(Object object, IEclipseContext context){
		ContextInjectionFactory.inject(object, getActiveContext());
	}
	
	public static <T> T createInstance(Class<T> clazz){
		T instance = ContextInjectionFactory.make(clazz, getActiveContext());
		return instance;
	}
	
	public static <T> void registerCustom(Class<T> clazz){
		registerCustom(clazz, ContextInjectionFactory.make(clazz, getActiveContext()));
	}
	
	public static <T> void registerCustom(Class<T> clazz, T object){
		csContext.set(clazz, object);
	}
	
	public static <T> void registerCustom(String name, T object){
		csContext.set(name, object);
	}
	
	public static <T> void registerCurrent(Class<T> clazz){
		IEclipseContext current = getActiveContext();
		registerCurrent(clazz, ContextInjectionFactory.make(clazz, current));
	}
	
	public static <T> void registerCurrent(Class<T> clazz, T object){
		IEclipseContext current = getActiveContext();
		current.set(clazz, object);
	}
	
	public static <T> void registerGlobal(Class<T> clazz){
		registerGlobal(clazz, ContextInjectionFactory.make(clazz, getActiveContext()));
	}
	
	public static <T> void registerGlobal(Class<T> clazz, T object){
		
		if(apContext == null){
			throw new IllegalStateException("Main application context is not initialized jet.");
		}
		
		apContext.set(clazz, object);
	}
	
	public static <T> void registerGlobal(String name, T object){
		
		if(apContext == null){
			throw new IllegalStateException("Main application context is not initialized jet.");
		}
		
		apContext.set(name, object);
	}
	
	public static <T> void register(Class<T> clazz, IEclipseContext context){
		csContext.set(clazz, ContextInjectionFactory.make(clazz, context));
	}
	
}
