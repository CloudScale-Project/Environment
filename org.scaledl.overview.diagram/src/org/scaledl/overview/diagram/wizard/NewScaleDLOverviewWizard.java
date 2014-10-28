package org.scaledl.overview.diagram.wizard;


import org.eclipse.emf.ecore.EObject;
import org.scaledl.overview.util.OverviewUtil;

public class NewScaleDLOverviewWizard extends GraphitiNewWizard {

	public NewScaleDLOverviewWizard() {
		super("ScaleDL Overview", "sdlo_diagram", "sdlo", "CSMDiagram", "eu.cloudscaleproject.env.diagram.DiagramEditor",10, true);
		setWindowTitle("ScaleDL Overview");
	}

	@Override
	protected EObject createModel(String name) {
		
		return OverviewUtil.createOverviewModel();
		
		/*
		Csm csm = CsmFactoryImpl.eINSTANCE.createCsm();
		
		Architecture architecture = ArchitectureFactoryImpl.eINSTANCE.createArchitecture();
		SystemDefinition definition = DefinitionFactory.eINSTANCE.createSystemDefinition();
		
		csm.setArchitecture(architecture);
		csm.setDefinition(definition);
		// TODO Auto-generated method stub
		return csm;
		*/
	}
	
	
	
}
