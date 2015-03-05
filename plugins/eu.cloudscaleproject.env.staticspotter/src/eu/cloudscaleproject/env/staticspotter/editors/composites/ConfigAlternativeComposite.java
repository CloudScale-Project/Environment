package eu.cloudscaleproject.env.staticspotter.editors.composites;

import javax.inject.Inject;

import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wb.swt.SWTResourceManager;
import org.reclipse.structure.inference.DetectPatternsJob;
import org.reclipse.structure.inference.ui.views.annotations.AnnotationView;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.staticspotter.ConfigPersistenceFolder;
import eu.cloudscaleproject.env.staticspotter.util.Util;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.ui.RunComposite;

public class ConfigAlternativeComposite extends RunComposite
{

	@Optional
	@Inject
	private CommandExecutor executor;

	private Combo combo;
	private ConfigPersistenceFolder configPersistenceFolder;

	private ComboViewer comboViewer;

	private Composite annotationViewComposite;

	private AnnotationView annotationView;

	private EditorInputFolder extractorResult;

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

		getContainer().setLayout(new GridLayout(3, false));

		Label lblInput = new Label(getContainer(), SWT.NONE);
		lblInput.setFont(SWTResourceManager.getFont("Sans", 11, SWT.NORMAL));
		lblInput.setText("Extractor result:");

		comboViewer = new ComboViewer(getContainer(), SWT.NONE);
		combo = comboViewer.getCombo();
		GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_combo.widthHint = 170;
		combo.setLayoutData(gd_combo);

		new Label(getContainer(), SWT.NONE);
		new Label(getContainer(), SWT.NONE);
		new Label(getContainer(), SWT.NONE);

		annotationViewComposite = new Composite(getContainer(), SWT.NONE);
		annotationViewComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
		annotationViewComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));

		loadInputs();
		initAnnotationView();
		setTitle(configPersistenceFolder.getName());
		
		comboViewer.addSelectionChangedListener(new ISelectionChangedListener()
		{
			@Override
			public void selectionChanged(SelectionChangedEvent event)
			{
				extractorResult = (EditorInputFolder) ((IStructuredSelection) comboViewer.getSelection()).getFirstElement();
			}
		});
	}

	@Override
	protected IStatus doRun(IProgressMonitor m)
	{
		final DetectPatternsJob detectPaternJob = Util.createDetectPaternJob(configPersistenceFolder, extractorResult);
		
		Display.getDefault().asyncExec(new Runnable()
		{
			@Override
			public void run()
			{
				annotationView.switchToInference(detectPaternJob.getEngine());
				annotationView.init();
			}
		});

		IStatus status = detectPaternJob.run(m);
		if (status.isOK())
		{
			// Collect results
			Util.saveAnnotations(configPersistenceFolder, detectPaternJob);
		}

		return status;
	}

	@Override
	public void update()
	{
		this.configPersistenceFolder.load();
		setTitle(configPersistenceFolder.getName());
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
				return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getSite();
			}
		};

		Display.getDefault().asyncExec(new Runnable()
		{
			@Override
			public void run()
			{
				StackLayout stackLayout = new StackLayout();
				annotationViewComposite.setLayout(stackLayout);
				annotationView.createPartControl(annotationViewComposite);

				Control annotationTable = ((Composite)annotationViewComposite.getChildren()[0]).getChildren()[0];
				annotationTable.setParent(annotationViewComposite);
				
				stackLayout.topControl = annotationTable;
				annotationViewComposite.layout();
			}

		});
	}

	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}

}
