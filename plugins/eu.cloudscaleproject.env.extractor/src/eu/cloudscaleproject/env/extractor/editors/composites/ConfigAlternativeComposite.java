package eu.cloudscaleproject.env.extractor.editors.composites;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.Properties;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import eu.cloudscaleproject.env.extractor.ConfigPersistenceFolder;
import eu.cloudscaleproject.env.extractor.wizard.util.Util;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.ui.RunComposite;

public class ConfigAlternativeComposite extends RunComposite
{
	private DataBindingContext m_bindingContext;

	private Combo combo;
	private List<IEditorInputResource> inputs;
	private ConfigPersistenceFolder configPersistenceFolder;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ConfigAlternativeComposite(Composite parent, int style, ConfigPersistenceFolder cif)
	{
		super(parent, style);

		this.configPersistenceFolder = cif;

		// this.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
		// 1));
		getContainer().setLayout(new GridLayout(2, false));

		setTitle(configPersistenceFolder.getName());

		{
		Label lblNewLabel = new Label(getContainer(), SWT.NONE);
		lblNewLabel.setText("Input project");

		comboViewer = new ComboViewer(getContainer(), SWT.NONE);
		Combo combo = comboViewer.getCombo();
		GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		gd_combo.widthHint = 250;
		combo.setLayoutData(gd_combo);
		}

		{
		 Label lblModiscoConfig = new Label(getContainer(), SWT.NONE);
		 lblModiscoConfig.setFont(SWTResourceManager.getFont("Sans", 11,
		 SWT.NORMAL));
		 lblModiscoConfig.setText("Modisco config:    ");
		
		 Label lblNewLabel = new Label(getContainer(), SWT.NONE);
		 lblNewLabel.setText("modisco.conf");
		
		 Button btnViewModisco = new Button(getContainer(), SWT.NONE);
		 btnViewModisco.addSelectionListener(new SelectionAdapter() {
		 @Override
		 public void widgetSelected(SelectionEvent e) {
		 Util.showConfigurationFile(configPersistenceFolder.getModiscoConfigFile());
		 }
		 });
		 btnViewModisco.setText("View");
		}

		Composite containerConfiguration = new Composite(getContainer(), SWT.NONE);
		containerConfiguration.setLayout(new FillLayout(SWT.HORIZONTAL));
		containerConfiguration.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));

		new SomoxConfigurationComposite(configPersistenceFolder.getSomoxConfiguration(), containerConfiguration, SWT.NONE);

		m_bindingContext = initDataBindings();
	}

	private static String JAVA_NATURE_ID = "org.eclipse.jdt.core.javanature";
	private ComboViewer comboViewer;

	private List<IProject> getJavaProjects()
	{
		List<IProject> javaProjects = new ArrayList<>();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

		for (IProject project : root.getProjects())
		{
			IProjectNature nature;
			try
			{
				nature = project.getNature(JAVA_NATURE_ID);
				if (nature != null)
					javaProjects.add(project);
			} catch (CoreException e)
			{
				System.out.println("Unable to retrieve nature: " + e.getMessage());
			}
		}

		return javaProjects;
	}

	@Override
	public void update()
	{
		this.configPersistenceFolder.load();
		m_bindingContext.updateTargets();

		super.update();
	}

	@Override
	protected IStatus doRun(IProgressMonitor m)
	{
		Util.ExtractorRunJob job = new Util.ExtractorRunJob(this.configPersistenceFolder);
		return job.run(m);
	}

	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}

	protected DataBindingContext initDataBindings()
	{
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap observeMap = PojoObservables.observeMap(listContentProvider.getKnownElements(), IProject.class, "name");
		comboViewer.setLabelProvider(new ObservableMapLabelProvider(observeMap));
		comboViewer.setContentProvider(listContentProvider);
		//
		IObservableList selfList = Properties.selfList(IProject.class).observe(getJavaProjects());
		comboViewer.setInput(selfList);
		//
		IObservableValue observeSingleSelectionComboViewer_1 = ViewerProperties.singleSelection().observe(comboViewer);
		IObservableValue projectConfigPersistenceFolderObserveValue = BeanProperties.value("extractedProject").observe(
				configPersistenceFolder);
		bindingContext.bindValue(observeSingleSelectionComboViewer_1, projectConfigPersistenceFolderObserveValue, null, null);
		//
		return bindingContext;
	}
}
