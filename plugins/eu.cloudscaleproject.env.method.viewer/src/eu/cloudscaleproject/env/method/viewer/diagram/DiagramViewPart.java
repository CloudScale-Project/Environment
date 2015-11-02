 
package eu.cloudscaleproject.env.method.viewer.diagram;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
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
	
	private HashMap<ValidationDiagram, ValidationDiagramComposite> composites = new HashMap<>();
	
	private final PropertyChangeListener diagramServiceListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if(ValidationDiagramService.PROP_SHOW_DIAGRAM.equals(evt.getPropertyName())){
				if(!pinDiagram){
					showDiagram((ValidationDiagram)evt.getNewValue());
				}
			}
		}
	};
	
	private final IResourceChangeListener resourceChangeListener = new IResourceChangeListener()
	{
		@Override
		public void resourceChanged(IResourceChangeEvent event) {
			Control control = stackLayout.topControl;
			if(control instanceof ValidationDiagramComposite){
				ValidationDiagramComposite vdc = (ValidationDiagramComposite)control;
				ValidationDiagram diagram = vdc.getValidationDiagram();
				IProject project = diagram.getProject();
				if(project == null || !project.isAccessible()){
					showDiagram(null);
				}
			}
		}
	};
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		
		this.composite = new Composite(parent, SWT.NONE);
		this.composite.setLayout(stackLayout);
		
		this.noDiagramComposite = createDiagramComposite(new ValidationDiagram(null));
		/*
		this.noDiagramComposite = new Composite(composite, SWT.NONE);
		this.noDiagramComposite.setLayout(new GridLayout());
		
		Label label = new Label(noDiagramComposite, SWT.CENTER | SWT.WRAP);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
		label.setText("Please create select the Cloudscale project node or editor to display the Workflow diagram");
		*/
		
		stackLayout.topControl = noDiagramComposite;
		
		ValidationDiagramService diagramService = CloudscaleContext.getGlobalContext().get(ValidationDiagramService.class);
		diagramService.addPropertyChangeListener(diagramServiceListener);
		
		showDiagram(diagramService.getActiveDiagram());

		ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceChangeListener, IResourceChangeEvent.POST_CHANGE);
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
		
		if(diagram == null){
			
			this.part.getContext().set(IProject.class, null);
			
			Display.getDefault().asyncExec(new Runnable() {
				
				@Override
				public void run() {
					
					if(composite == null || composite.isDisposed()){
						return;
					}
					
					stackLayout.topControl = noDiagramComposite;
					DiagramViewPart.this.composite.layout();
					DiagramViewPart.this.composite.redraw();
				}
			});
			
			return;
		}
		
		this.part.getContext().set(IProject.class, diagram.getProject());
		
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				
				if(composite == null || composite.isDisposed()){
					return;
				}
				
				ValidationDiagramComposite composite = composites.get(diagram);
				if(composite == null){
					composite = createDiagramComposite((ValidationDiagram)diagram);
					composites.put(diagram, composite);
				}
				
				if(!composite.isDisposed()){
					stackLayout.topControl = composite;
					DiagramViewPart.this.composite.layout();
					DiagramViewPart.this.composite.redraw();
				}		
			}
		});
		
	}
	
	@PreDestroy
	public void preDestroy() {
		IValidationDiagramService diagramService = CloudscaleContext.getGlobalContext().get(IValidationDiagramService.class);
		diagramService.removePropertyChangeListener(diagramServiceListener);
		
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceChangeListener);
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