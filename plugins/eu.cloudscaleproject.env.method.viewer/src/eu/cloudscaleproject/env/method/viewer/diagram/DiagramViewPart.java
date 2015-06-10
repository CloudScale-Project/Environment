 
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
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.notification.diagram.IValidationDiagram;
import eu.cloudscaleproject.env.common.notification.diagram.IValidationDiagramFactory;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.method.viewer.MethodDiagramComposite;

public class DiagramViewPart implements IDiagramView, IValidationDiagramFactory{
	
	@Inject
	private MPart part;
	
	private Composite composite = null;
	private StackLayout stackLayout = new StackLayout();
	
	private MethodDiagramComposite currentDiagram = null;
	
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
	
	/*
	private IWorkbenchPart getCompatibilityPart(){
		
		IWorkbenchPart e3Part = null;
		
		Object client = part.getObject();
		if (client instanceof CompatibilityPart) {
			IWorkbenchPart workbenchPart = ((CompatibilityPart) client).getPart();
			e3Part = workbenchPart;
		} else if (client != null) {
			if (part.getTransientData().get(E4PartWrapper.E4_WRAPPER_KEY) instanceof E4PartWrapper) {
				e3Part = (IWorkbenchPart) part.getTransientData().get(
						E4PartWrapper.E4_WRAPPER_KEY);
			}
		}		
		return e3Part;
	}
	*/
	
	@Focus
	public void onFocus(){
		if(currentDiagram != null){
			
			//Tukaj ostal v torek
			//Problem: DiagramViewPart ne klice activate, kar povzroci v PropertySheet.class line:355 da pade ven...
			//Posledicno se ne trigera Property sheet page update/prikaz...
			
			//set selection provider to the active page...
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();			
			IWorkbenchPartSite site = page.getActivePart().getSite();
			if(site != null){
				site.setSelectionProvider(currentDiagram.getGraphicalViewer());
			}
			//

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