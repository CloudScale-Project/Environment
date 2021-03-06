 
package eu.cloudscaleproject.env.method.viewer.diagram;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.services.IValidationDiagramService;
import eu.cloudscaleproject.env.method.viewer.ValidationDiagram;
import eu.cloudscaleproject.env.method.viewer.ValidationDiagramComposite;
import eu.cloudscaleproject.env.method.viewer.ValidationDiagramService;

public class DiagramViewPart{
	
	public static final String PIN_DIAGRAM = "pinValidationDiagram";
	
	@Inject
	private MPart part;
	
	private boolean pinDiagram = false;
	
	private Composite noDiagramComposite;
	
	private Composite composite = null;
	private StackLayout stackLayout = new StackLayout();
	
	private HashMap<IProject, ValidationDiagramComposite> composites = new HashMap<IProject, ValidationDiagramComposite>();
	
	private final PropertyChangeListener diagramServiceListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			if(ValidationDiagramService.PROP_CREATE_DIAGRAM.equals(evt.getPropertyName())){
				
				Display.getDefault().asyncExec(new Runnable() {
					
					@Override
					public void run() {
						
						ValidationDiagramService diagramService = (ValidationDiagramService)evt.getSource();
						IProject project = (IProject)evt.getNewValue();
						
						ValidationDiagram diagram = diagramService.getDiagram(project);
						ValidationDiagramComposite composite = createDiagramComposite((ValidationDiagram)diagram);
						composites.put(project, composite);
					}
				});
				
			}

			if(ValidationDiagramService.PROP_DELETE_DIAGRAM.equals(evt.getPropertyName())){

				Display.getDefault().asyncExec(new Runnable() {
					
					@Override
					public void run() {
						ValidationDiagramComposite composite = composites.get(evt.getOldValue());
						composites.remove(composite);
						composite.dispose();
					}
				});
				
			}

			if(ValidationDiagramService.PROP_SHOW_DIAGRAM.equals(evt.getPropertyName())){

				if(!pinDiagram){
					showDiagram((ValidationDiagram)evt.getNewValue());
				}
			}

		}
	};
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		
		this.composite = new Composite(parent, SWT.NONE);
		this.composite.setLayout(stackLayout);
		
		this.noDiagramComposite = createDiagramComposite(new ValidationDiagram(null));
		stackLayout.topControl = noDiagramComposite;
		
		ValidationDiagramService diagramService = CloudscaleContext.getGlobalContext().get(ValidationDiagramService.class);
		diagramService.addPropertyChangeListener(diagramServiceListener);
		for(ValidationDiagram diagram : diagramService.getDiagrams()){
			ValidationDiagramComposite composite = createDiagramComposite((ValidationDiagram)diagram);
			composites.put(diagram.getProject(), composite);
		}
		
		showDiagram(diagramService.getActiveDiagram());
	}
	
	@Inject
	@Optional
	private void pinDiagram(@Named(PIN_DIAGRAM) Boolean pin){
		
		if(pin == null){
			return;
		}
		
		this.pinDiagram = pin;
		
		if(!pin){
			ValidationDiagramService diagramService = CloudscaleContext.getGlobalContext().get(ValidationDiagramService.class);
			showDiagram(diagramService.getActiveDiagram());
		}
		
	}
	
	private void showDiagram(final ValidationDiagram diagram){

		if(composite == null || composite.isDisposed()){
			return;
		}
		
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				if(diagram == null){

					part.getContext().set(IProject.class, null);
					
					stackLayout.topControl = noDiagramComposite;
					DiagramViewPart.this.composite.layout();
					DiagramViewPart.this.composite.redraw();
				}
				else{

					part.getContext().set(IProject.class, diagram.getProject());
					
					ValidationDiagramComposite composite = composites.get(diagram.getProject());

					if(composite != null && !composite.isDisposed()){
						stackLayout.topControl = composite;
						DiagramViewPart.this.composite.layout();
						DiagramViewPart.this.composite.redraw();
					}
				}
			}
		});
	}
	
	@PreDestroy
	public void preDestroy() {
		IValidationDiagramService diagramService = CloudscaleContext.getGlobalContext().get(IValidationDiagramService.class);
		diagramService.removePropertyChangeListener(diagramServiceListener);
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
		diagramComposite.getDiagramBehavior().refresh();
		
		return diagramComposite;
	}
}