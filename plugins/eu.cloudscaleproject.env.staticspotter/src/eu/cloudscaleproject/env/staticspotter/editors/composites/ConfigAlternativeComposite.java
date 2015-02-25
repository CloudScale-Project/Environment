package eu.cloudscaleproject.env.staticspotter.editors.composites;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wb.swt.SWTResourceManager;
import org.fujaba.commons.console.ReportLevel;
import org.reclipse.structure.inference.DetectPatternsJob;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.annotations.AnnotationsPackage;
import org.reclipse.structure.inference.annotations.SetInstanceAnnotation;
import org.reclipse.structure.inference.annotations.TemporaryAnnotation;
import org.reclipse.structure.inference.notification.InferenceProgressListener;
import org.reclipse.structure.inference.ui.views.annotations.AnnotationView;
import org.reclipse.structure.inference.util.InferenceExtensionsHelper;
import org.reclipse.structure.inference.util.InferenceExtensionsHelper.AnnotationEvaluatorItem;
import org.reclipse.structure.specification.PSPatternSpecification;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.staticspotter.ConfigPersistenceFolder;
import eu.cloudscaleproject.env.staticspotter.util.Util;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

import org.eclipse.swt.layout.FillLayout;

public class ConfigAlternativeComposite extends Composite
{

	@Optional
	@Inject
	private CommandExecutor executor;

	private Combo combo;
	private ConfigPersistenceFolder configPersistenceFolder;

	private ComboViewer comboViewer;

	private Composite annotationViewComposite;

	private AnnotationView annotationView;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ConfigAlternativeComposite(Composite parent, int style, final ConfigPersistenceFolder cif)
	{
		super(parent, style);
		CloudscaleContext.inject(this);

		this.configPersistenceFolder = cif;

		// this.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
		// 1));
		this.setLayout(new GridLayout(3, false));

		Label lblConfigurationalternative = new Label(this, SWT.NONE);
		lblConfigurationalternative.setFont(SWTResourceManager.getFont("Sans", 14, SWT.NORMAL));
		lblConfigurationalternative.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		lblConfigurationalternative.setText("Configuration (" + configPersistenceFolder.getName() + ")");

		Label label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		GridData gd_label = new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1);
		gd_label.widthHint = 167;
		label.setLayoutData(gd_label);

		Label lblInput = new Label(this, SWT.NONE);
		lblInput.setFont(SWTResourceManager.getFont("Sans", 11, SWT.NORMAL));
		lblInput.setText("Input:");

		comboViewer = new ComboViewer(this, SWT.NONE);
		combo = comboViewer.getCombo();
		GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_combo.widthHint = 170;
		combo.setLayoutData(gd_combo);

		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		Button btnRunAlternative = new Button(this, SWT.NONE);
		btnRunAlternative.setText("Run alternative");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		annotationViewComposite = new Composite(this, SWT.NONE);
		annotationViewComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
		annotationViewComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));

		btnRunAlternative.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				runDetectionJob();
			}
		});

		loadInputs();
		initAnnotationView();
	}

	@Override
	public void update()
	{
		this.configPersistenceFolder.load();
		loadInputs();
		super.update();
	}

	private void loadInputs()
	{
		ResourceProvider resourceProvider = ResourceRegistry.getInstance().getResourceProvider(configPersistenceFolder.getProject(),
				ToolchainUtils.EXTRACTOR_RES_ID);

		WritableList list = new WritableList();
		list.addAll(resourceProvider.getResources());

		comboViewer.setContentProvider(new ObservableListContentProvider());
		comboViewer.setLabelProvider(new LabelProvider()
		{
			@Override
			public String getText(Object element)
			{
				IEditorInputResource eir = (IEditorInputResource) element;
				return eir.getName();
			}
		});
		comboViewer.setInput(list);
	}

	private void initAnnotationView()
	{
		this.annotationView = new AnnotationView()
		{
			@Override
			public IWorkbenchPartSite getSite()
			{
				// TODO Auto-generated method stub
				return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getSite();
			}
		};

		Display.getDefault().asyncExec(new Runnable()
		{
			@Override
			public void run()
			{
				annotationView.createPartControl(annotationViewComposite);
				
				annotationViewComposite.getChildren()[1].setParent(annotationViewComposite);

			}

		});
	}

	private void runDetectionJob()
	{
		// Prepare it outside - SWT thread bullshit
		EditorInputFolder extractorResult = (EditorInputFolder) ((IStructuredSelection) comboViewer.getSelection()).getFirstElement();
		final DetectPatternsJob detectPaternJob = Util.createDetectPaternJob(configPersistenceFolder, extractorResult);

		annotationView.switchToInference(detectPaternJob.getEngine());
		annotationView.init();
		// configureMatchingViews();

		Job job = new Job("StaticSpotter : spotting")
		{

			@Override
			protected IStatus run(IProgressMonitor monitor)
			{
				// TODO Auto-generated method stub
				IStatus status = detectPaternJob.run(monitor);

				if (status.isOK())
				{
					// Collect results
					Util.saveAnnotations(configPersistenceFolder, detectPaternJob);
				}

				return status;
			}
		};

		job.schedule();
	}



	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}

}
