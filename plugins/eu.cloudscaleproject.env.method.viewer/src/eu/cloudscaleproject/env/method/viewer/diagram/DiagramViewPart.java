 
package eu.cloudscaleproject.env.method.viewer.diagram;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.notification.diagram.IValidationDiagram;
import eu.cloudscaleproject.env.common.notification.diagram.IValidationDiagramFactory;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.method.viewer.MethodDiagramComposite;

public class DiagramViewPart implements IValidationDiagramFactory{
	
	@Inject
	private MPart part;
	
	private Composite composite = null;
	private StackLayout stackLayout = new StackLayout();
	
	private MethodDiagramComposite currentDiagram = null;
		
	@Inject
	public DiagramViewPart() {
	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		
		this.composite = new Composite(parent, SWT.NONE);
		this.composite.setLayout(stackLayout);
				
		Composite noDiagramComposite = new Composite(composite, SWT.NONE);
		noDiagramComposite.setLayout(new GridLayout());
		
		Label label = new Label(noDiagramComposite, SWT.CENTER | SWT.WRAP);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
		label.setText("Please create new Cloudscale project or open 'project.cse' configuration file to display workflow");
		
		stackLayout.topControl = noDiagramComposite;
		
		ValidationDiagramService.registerDiagramFactory(this);
	}
	
	
	@PreDestroy
	public void preDestroy() {
		ValidationDiagramService.registerDiagramFactory(null);
	}
	
	public void setTopDiagram(MethodDiagramComposite diagramComposite){
		if(!composite.isDisposed()){
			stackLayout.topControl = diagramComposite;
			composite.layout();
			composite.redraw();
		
			currentDiagram = diagramComposite;
		}
		
		part.setLabel("Project workflow diagram ["+diagramComposite.getProject().getName()+"]");
	}
	
	@Focus
	public void onFocus(){
		if(currentDiagram != null){
			CloudscaleContext.getActiveContext().set(IProject.class, currentDiagram.getProject());
		}
	}

	@Override
	public IValidationDiagram createDiagram(Resource diagramResource) {
		
		URI diagramUri = null;
		
		if(diagramResource == null){
			diagramUri = URI.createURI("pathmap://METHOD_WORKFLOW/method.workflow");
		}
		else{
			diagramUri = diagramResource.getURI();
		}
		
		MethodDiagramComposite diagramComposite = new MethodDiagramComposite(this, composite);
		
		DiagramEditorInput editorInput = new DiagramEditorInput(diagramUri, null);
		diagramComposite.setInput(editorInput);
		
		composite.layout();
		composite.redraw();
				
		return diagramComposite;
	}
}