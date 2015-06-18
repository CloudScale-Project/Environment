package eu.cloudscaleproject.env.common.notification.views;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;

public class StatusViewPart {
	
	private Composite composite;
	private TableViewer tableViewer;
	
	private IProject project;
	private List<IValidationStatusProvider> statusProviders = null;
	
	private final PropertyChangeListener startusListener = new PropertyChangeListener() {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Display.getDefault().asyncExec(new Runnable()
			{
				@Override
				public void run()
				{
					reload(project);
				}
			});
		}
	};

	@PostConstruct
	public void postConstruct(Composite parent) {
		
		this.composite = new Composite(parent, SWT.NONE);
		this.composite.setLayout(new FillLayout());
		
		this.tableViewer = new TableViewer(composite);
		this.tableViewer.setContentProvider(new StatusContentProvider());
		this.tableViewer.setLabelProvider(new StatusLabelProvider());
		this.tableViewer.setInput(statusProviders);
		
		StatusManager.getInstance().addPropertyChangeListener(startusListener);
	}
	
	@Inject
	private void reload(@Optional IProject project){
		
		if(project == null){
			return;
		}
		
		this.project = project;
		this.statusProviders = StatusManager.getInstance().getStatusProviders(project);
		
		if(this.tableViewer != null && this.statusProviders != null){
			this.tableViewer.setInput(this.statusProviders);
			this.tableViewer.refresh();
		}
	}
	
	
	@PreDestroy
	public void preDestroy() {
		ValidationDiagramService.registerDiagramFactory(null);
	}
	
	@Focus
	public void onFocus(){
	}
	
}
