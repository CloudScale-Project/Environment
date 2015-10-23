package eu.cloudscaleproject.env.extractor.editors.composites;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import eu.cloudscaleproject.env.common.dialogs.CustomResourceSelectionDialog;
import eu.cloudscaleproject.env.extractor.alternatives.InputAlternative;
import eu.cloudscaleproject.env.toolchain.ui.InputEditorView;

public class InputAlternativeComposite extends InputEditorView {

	private Text txtProjectName;
	private InputAlternative alternative;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public InputAlternativeComposite(Composite parent, int style, final InputAlternative alternative) {
		super(parent, SWT.NONE, alternative);
		
		this.alternative = alternative;
		
		getContainer().setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(getContainer(), SWT.NONE);
		GridLayout gl_composite = new GridLayout(1, false);
		gl_composite.verticalSpacing = 20;
		composite.setLayout(gl_composite);
		
		{ // Project container 
			Group projectContainer = new Group(composite, SWT.NONE);
			projectContainer.setText("Project");
			projectContainer.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			
			initProjectSelection(projectContainer);
		}
		
		{ // Modisco container
			Group modiscoContainer = new Group(composite, SWT.NONE);
			modiscoContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			modiscoContainer.setText("Configuration");
			modiscoContainer.setLayout(new FillLayout(SWT.HORIZONTAL));
			
			new ModiscoConfigurationComposite(modiscoContainer, SWT.NONE, alternative);
		}

		initDataBindings();
		
	}
	
	private void initProjectSelection(Composite container)
	{
		container.setLayout(new GridLayout(2, false));

		txtProjectName = new Text(container, SWT.BORDER);
		txtProjectName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnChoose = new Button(container, SWT.NONE);
		btnChoose.setText("...");
		

		btnChoose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
		        CustomResourceSelectionDialog dialog = new CustomResourceSelectionDialog("Project selection", "Select Java project ...", IProject.class, null);
		        dialog.open();
		        Object selection = dialog.getFirstResult();
		        
		        if (selection instanceof IProject)
		        {
		        	IProject project = (IProject) selection;
		        	txtProjectName.setText(project.getName());
		        }
			}
		});
	}
	
	@Override
	public void update() {
		super.update();
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTxtProjectNameObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtProjectName);
		IObservableValue extractedProjectAlternativeObserveValue = BeanProperties.value("extractedProjectName").observe(alternative);
		bindingContext.bindValue(observeTextTxtProjectNameObserveWidget, extractedProjectAlternativeObserveValue, null, null);
		//
		return bindingContext;
	}
}
