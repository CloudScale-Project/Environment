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
			}

		});
	}

	private void runDetectionJob()
	{
		// Prepare it outside - SWT thread bullshit
		EditorInputFolder extractorResult = (EditorInputFolder) ((IStructuredSelection) comboViewer.getSelection()).getFirstElement();
		final DetectPatternsJob detectPaternJob = createDetectPaternJob(extractorResult);

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
					saveAnnotations(detectPaternJob);
				}

				return status;
			}
		};

		job.schedule();
	}

	private DetectPatternsJob createDetectPaternJob(EditorInputFolder extractorResult)
	{
		assert (extractorResult != null);

		ResourceSet resSet = new ResourceSetImpl();
		IFile catalogFile = configPersistenceFolder.getFileResource(ConfigPersistenceFolder.KEY_CATALOG);
		URI catalogURI = URI.createPlatformResourceURI(catalogFile.getFullPath().toString(), true);

		IFile enginesFile = configPersistenceFolder.getFileResource(ConfigPersistenceFolder.KEY_ENGINES);
		URI enginesURI = URI.createPlatformResourceURI(enginesFile.getFullPath().toString(), true);

		IFile sdFile = extractorResult.getFileResource(ToolchainUtils.KEY_FILE_SOURCEDECORATOR);
		URI sdURI = URI.createPlatformResourceURI(sdFile.getFullPath().toString(), true);

		//
		// Run resources
		//
		Resource catalogResource = resSet.createResource(catalogURI);
		Resource enginesResource = resSet.createResource(enginesURI);
		Resource sourcedecoratorResource = resSet.createResource(sdURI);

		//
		// Configurations
		//
		ReportLevel reportLevel = ReportLevel.INFO;
		AnnotationEvaluatorItem evaluator = InferenceExtensionsHelper.getRegisteredEvaluators().get(0);
		boolean searchForAdditionalElements = false;

		DetectPatternsJob job = new DetectPatternsJob(catalogResource, enginesResource, sourcedecoratorResource, reportLevel);
		job.setAnnotateAdditionalElements(searchForAdditionalElements);
		job.setEvaluator(evaluator.getEvaluator());

		return job;
	}

	private void saveAnnotations(DetectPatternsJob job)
	{
		List<ASGAnnotation> annotations = getAnnotations(job);

		IFile file = configPersistenceFolder.getResource().getFile("test.res");
		URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
		ResourceSet ress = new ResourceSetImpl();
		Resource res = ress.createResource(uri);

		// fill resource
		for (ASGAnnotation anno : annotations)
		{
			// rename annotation type (due to usage of specific created
			// annotation classes)
			// TODO: this is evil!
			String name = AnnotationsPackage.eINSTANCE.getASGAnnotation().getName();
			if (anno instanceof SetInstanceAnnotation)
			{
				name = AnnotationsPackage.eINSTANCE.getSetInstanceAnnotation().getName();
			}
			else if (anno instanceof TemporaryAnnotation)
			{
				name = AnnotationsPackage.eINSTANCE.getTemporaryAnnotation().getName();
			}

			anno.eClass().setName(name);
			anno.eClass().getEPackage().setName(AnnotationsPackage.eNAME);
			anno.eClass().getEPackage().setNsPrefix(AnnotationsPackage.eNS_PREFIX);
			anno.eClass().getEPackage().setNsURI(AnnotationsPackage.eNS_URI);

			res.getContents().add(anno);
		}

		try
		{
			res.save(Collections.emptyMap());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	private List<ASGAnnotation> getAnnotations(DetectPatternsJob job)
	{
		Map<PSPatternSpecification, Collection<ASGAnnotation>> results = job.getEngine().getResults();
		ArrayList<ASGAnnotation> annotations = new ArrayList<ASGAnnotation>();

		if (results != null)
		{
			for (PSPatternSpecification key : results.keySet())
			{
				for (ASGAnnotation anno : results.get(key))
				{
					annotations.add(anno);
				}
			}
		}

		// sort them (by pattern)
		Collections.sort(annotations, new Comparator<ASGAnnotation>()
		{
			@Override
			public int compare(ASGAnnotation one, ASGAnnotation two)
			{
				return one.getPattern().getName().compareTo(two.getPattern().getName());
			}
		});
		return annotations;
	}

	/**
	 * Tries to open the annotations view when the DetectPatternsJob is run.
	 * 
	 * @param job
	 *            The DetectPatternsJob
	 * @throws PartInitException
	 *             If the view that is registered under the corresponding ID is
	 *             not the correct one.
	 */
	protected void configureAnnotationsView(final DetectPatternsJob job)
	{

		IViewPart part = null;
		try
		{
			part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(AnnotationView.ID);
		}
		catch (PartInitException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		final AnnotationView annotations = (AnnotationView) part;
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable()
		{
			@Override
			public void run()
			{
				if (annotations != null)
				{
					annotations.switchToInference(job.getEngine());
				}
			}
		});
	}

	/**
	 * Configures the matching views that visualize the matched patterns and the
	 * matched objects after the detection.
	 */
	private static final String VID_MATCHED_PATTERNS = "org.reclipse.ui.views.structure.inference.matching.pattern"; //$NON-NLS-1$
	private static final String VID_MATCHED_OBJECTS = "org.reclipse.ui.views.structure.inference.matching.ast"; //$NON-NLS-1$

	protected void configureMatchingViews()
	{
		final InferenceProgressListener mPatternView = (InferenceProgressListener) getMatchingView(VID_MATCHED_PATTERNS);
		if (mPatternView != null)
		{
			mPatternView.init();
		}

		final InferenceProgressListener mObjectsView = (InferenceProgressListener) getMatchingView(VID_MATCHED_OBJECTS);
		if (mObjectsView != null)
		{
			mObjectsView.init();
		}
	}

	protected IViewPart getMatchingView(String id)
	{
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(id);
	}

	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}

}
