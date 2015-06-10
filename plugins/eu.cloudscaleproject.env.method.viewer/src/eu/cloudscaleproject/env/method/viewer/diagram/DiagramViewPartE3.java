package eu.cloudscaleproject.env.method.viewer.diagram;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.PropertySheetPage;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.notification.diagram.IValidationDiagram;
import eu.cloudscaleproject.env.common.notification.diagram.IValidationDiagramFactory;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.method.viewer.MethodDiagramComposite;
import eu.cloudscaleproject.env.method.viewer.diagram.properties.DiagramPropertyPage;

public class DiagramViewPartE3 extends ViewPart implements IDiagramView, IValidationDiagramFactory{
	
	private Composite composite = null;
	private StackLayout stackLayout = new StackLayout();
	
	private MethodDiagramComposite currentDiagram = null;

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

	@Override
	public void createPartControl(Composite parent) {
		this.composite = new Composite(parent, SWT.NONE);
		this.composite.setLayout(stackLayout);
				
		Composite noDiagramComposite = new Composite(composite, SWT.NONE);
		noDiagramComposite.setLayout(new GridLayout());
		
		Label label = new Label(noDiagramComposite, SWT.CENTER | SWT.WRAP);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
		label.setText("Please create new Cloudscale project or open 'project.cse' configuration file to display workflow");
		
		stackLayout.topControl = noDiagramComposite;
		
		ValidationDiagramService.registerDiagramFactory(this);
		parent.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent e) {
				ValidationDiagramService.registerDiagramFactory(null);
			}
		});
	}
	
	public void setTopDiagram(MethodDiagramComposite diagramComposite){
		if(!composite.isDisposed()){
			stackLayout.topControl = diagramComposite;
			composite.layout();
			composite.redraw();
		
			currentDiagram = diagramComposite;
		}
		
		setPartName("Project workflow diagram ["+diagramComposite.getProject().getName()+"]");
	}

	@Override
	public void setFocus() {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();			
		IWorkbenchPartSite site = page.getActivePart().getSite();
		if(site != null){
			site.setSelectionProvider(currentDiagram.getGraphicalViewer());
		}
		//

		CloudscaleContext.getActiveContext().set(IProject.class, currentDiagram.getProject());
	}
	
	@Override
	public Object getAdapter(Class adapter) {
		if(adapter.equals(PropertySheetPage.class)){
			return new DiagramPropertyPage();
		}
		return super.getAdapter(adapter);
	}

}
