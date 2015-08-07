 
package eu.cloudscaleproject.env.method.viewer.diagram;

import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.di.annotations.Optional;
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
import eu.cloudscaleproject.env.method.viewer.ValidationDiagram;
import eu.cloudscaleproject.env.method.viewer.ValidationDiagramComposite;

public class DiagramViewPart{
	
	@Inject
	private MPart part;
	
	private Composite composite = null;
	private StackLayout stackLayout = new StackLayout();
		
	private HashMap<IValidationDiagram, ValidationDiagramComposite> composites = new HashMap<>();
	
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
		
		Object object = part.getContext().get(CloudscaleContext.ACTIVE_VALIDATION_DIAGRAM);
		if(object instanceof ValidationDiagram){
			showDiagram((ValidationDiagram)object);
		}
	}
	
	@Inject
	private void showDiagram(@Named(CloudscaleContext.ACTIVE_VALIDATION_DIAGRAM) @Optional ValidationDiagram diagram){
		
		if(composite == null){
			return;
		}
		
		if(diagram == null){
			return;
		}
		
		ValidationDiagramComposite composite = composites.get(diagram);
		if(composite == null){
			composite = createDiagramComposite(diagram);
			composites.put(diagram, composite);
		}
		
		if(!composite.isDisposed()){
			stackLayout.topControl = composite;
			composite.layout();
			composite.redraw();
		}
		
		CloudscaleContext.getActiveContext().set(IProject.class, diagram.getProject());
		part.setLabel("Workflow ["+diagram.getProject().getName()+"]");
	}
	
	
	@PreDestroy
	public void preDestroy() {
	}

	public ValidationDiagramComposite createDiagramComposite(ValidationDiagram diagram) {
		
		Resource resource = diagram.getResource();
		URI diagramUri = null;
		
		if(resource == null){
			diagramUri = URI.createURI("pathmap://METHOD_WORKFLOW/method.workflow");
		}
		else{
			diagramUri = resource.getURI();
		}
		
		ValidationDiagramComposite diagramComposite = new ValidationDiagramComposite(diagram, composite, SWT.NONE);
		
		DiagramEditorInput editorInput = new DiagramEditorInput(diagramUri, null);
		diagramComposite.setInput(editorInput);
		
		diagram.initialize(diagramComposite.getDiagramTypeProvider());
		
		composite.layout();
		composite.redraw();
				
		return diagramComposite;
	}
}