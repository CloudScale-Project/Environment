package org.scaledl.overview.diagram.decorations;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.tb.IDecorator;

public class DecorationManager {

	private static HashSet<DecorationProvider> providers = new HashSet<DecorationProvider>();
	
	public static void addProvider(DecorationProvider provider){
		providers.add(provider);
	}
	
	public static void removeProvider(DecorationProvider provider){
		providers.remove(provider);
	}
	
	public static Iterator<DecorationProvider> getProviders(){
		return providers.iterator();
	}
	
	public static Set<IDecorator> getDecorations(PictogramElement pe){
		HashSet<IDecorator> out = new HashSet<IDecorator>();
		
		for(DecorationProvider dp : providers){
			out.addAll(dp.getDecorators(pe));
		}
		
		return out;
	}
	
}
