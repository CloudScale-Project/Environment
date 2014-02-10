package eu.cloudscaleproject.env.method.layer;

import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.swt.graphics.Rectangle;

public class MethodDescriptors {
	
	private static LinkedList<Descriptor> descriptors;
	
	private static final String HELP_LOC="/eu.cloudscaleproject.env.help/html/method/";
	private static final String REQUIREMENTS = HELP_LOC+"requirements.html";
	private static final String CONSTRUCTION = HELP_LOC+"construction.html";
	private static final String REALISATION = HELP_LOC+"realisation.html";
	private static final String DEPLOYMENT = HELP_LOC+"deployment.html";
	private static final String OPERATION = HELP_LOC+"operation.html";
	private static final String MONITORING = HELP_LOC+"monitoring.html";
	private static final String ANALYSER = HELP_LOC+"analyser.html";
	private static final String EXTRACTOR = HELP_LOC+"extractor.html";
	private static final String SPOTTER = HELP_LOC+"spotter.html";

	static {
		initDescriptors();
	}
	
	public static Collection<Descriptor> getDescriptors()
	{
		return descriptors;
		
	}

	private static void initDescriptors() {
		Descriptor reqAndIdent = new Descriptor(
				new Rectangle(14, 249, 161, 69),			
				"Requirements & Identification", REQUIREMENTS);
				
		Descriptor realisation = new Descriptor(
			new Rectangle(977,691,479,169),
			"Realisation", REALISATION);
		
		Descriptor deployment = new Descriptor(
			new Rectangle(552,689,375,171),
			"Deployment", DEPLOYMENT);
		
		Descriptor operation = new Descriptor(
			new Rectangle(309,786,138,84),
			"Operation", OPERATION);
		
		Descriptor monitoring = new Descriptor(
			new Rectangle(309,693,138,84),
			"Monitoring", MONITORING);
		
		Descriptor sysConstruction = new Descriptor(
			new Rectangle(722, 52, 292, 24),
			"System Construction and Anlysis", CONSTRUCTION);
		
		Descriptor extractor = new Descriptor(
			new Rectangle(582,113,139,87),
			"Extractor", EXTRACTOR);
		
		Descriptor analyser = new Descriptor(
			new Rectangle(1030, 189,178,117),
			"Analyser & Static Spotter", ANALYSER);
		
		Descriptor spotter = new Descriptor(
			new Rectangle(976, 410,141,89),
			"Dynamic Spotter", SPOTTER);
		
		
		descriptors = new LinkedList<Descriptor>();
		descriptors.add(reqAndIdent);
		descriptors.add(sysConstruction);
		descriptors.add(realisation);
		descriptors.add(deployment);
		descriptors.add(operation);
		descriptors.add(monitoring);
		descriptors.add(analyser);
		descriptors.add(extractor);
		descriptors.add(spotter);
	}

}
