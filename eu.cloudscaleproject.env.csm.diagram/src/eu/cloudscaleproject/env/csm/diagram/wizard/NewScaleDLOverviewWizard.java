package eu.cloudscaleproject.env.csm.diagram.wizard;


import org.eclipse.emf.ecore.EObject;

import eu.cloudscaleproject.env.csm.Csm;
import eu.cloudscaleproject.env.csm.architecture.Architecture;
import eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureFactoryImpl;
import eu.cloudscaleproject.env.csm.definition.DefinitionFactory;
import eu.cloudscaleproject.env.csm.definition.SystemDefinition;
import eu.cloudscaleproject.env.csm.impl.CsmFactoryImpl;

public class NewScaleDLOverviewWizard extends GraphitiNewWizard {

	public NewScaleDLOverviewWizard() {
		super("ScaleDL Overview", "sldo_diagram", "sdlo", "CSMDiagram", "eu.cloudscaleproject.env.diagram.DiagramEditor",10, true);
		setWindowTitle("ScaleDL Overview");
	}

	@Override
	protected EObject createModel(String name) {
		
		Csm csm = CsmFactoryImpl.eINSTANCE.createCsm();
		
		Architecture architecture = ArchitectureFactoryImpl.eINSTANCE.createArchitecture();
		SystemDefinition definition = DefinitionFactory.eINSTANCE.createSystemDefinition();
		
		csm.setArchitecture(architecture);
		csm.setDefinition(definition);
		// TODO Auto-generated method stub
		return csm;
	}
	
	
	
}
