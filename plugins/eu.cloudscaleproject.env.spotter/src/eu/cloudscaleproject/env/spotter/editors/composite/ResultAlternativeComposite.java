package eu.cloudscaleproject.env.spotter.editors.composite;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.lpe.common.util.LpeFileUtils;
import org.spotter.eclipse.ui.Activator;
import org.spotter.eclipse.ui.editors.HierarchyEditor;
import org.spotter.eclipse.ui.model.IExtensionItem;
import org.spotter.eclipse.ui.model.IExtensionItemFactory;
import org.spotter.eclipse.ui.model.ImmutableExtensionItemFactory;
import org.spotter.eclipse.ui.navigator.SpotterProjectParent;
import org.spotter.eclipse.ui.providers.ResultExtensionsImageProvider;
import org.spotter.eclipse.ui.providers.SpotterExtensionsLabelProvider;
import org.spotter.eclipse.ui.viewers.ExtensionsGroupViewer;
import org.spotter.shared.hierarchy.model.XPerformanceProblem;
import org.spotter.shared.result.ResultsLocationConstants;
import org.spotter.shared.result.model.ResultsContainer;
import org.spotter.shared.result.model.SpotterResult;

import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.spotter.SpotterClientController;
import eu.cloudscaleproject.env.spotter.Util;
import eu.cloudscaleproject.env.spotter.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.ui.ResultEditorView;

public class ResultAlternativeComposite extends ResultEditorView implements IRefreshable
{

	private static final String DLG_RESOURCE_TITLE = "Resource '%s' (%s)";

	private static final String EMPTY_RESULTS = "No results selected.";
	private static final String ERR_MSG_MISSING_REPORT = "Either file is missing or report is not set.";

	private static final String LABEL_NONE_SELECTED = "<none selected>";
	private static final String LABEL_DETECTED = "Detected";
	private static final String LABEL_NOT_DETECTED = "Not Detected";
	private static final String LABEL_NO_LOOKUP = "The corresponding result could not be looked up. (erroneous analysis)";
	private static final String LABEL_NO_INFO = "No description available.";
	private static final String NOT_AVAILABLE_IMG_TEXT = "not available";

	private static final String TAB_HIERARCHY_NAME = "Hierarchy";
	private static final String TAB_REPORT_NAME = "Report";

	private static final String RESOURCE_SEPARATOR_CHAR = "/";

	private final IExtensionItemFactory extensionItemFactory;

	private TreeViewer hierarchyTreeViewer;
	private ResultExtensionsImageProvider imageProvider;
	private Text textReport;
	private Label lblProblemName;
	private Label lblStatus;
	private Label lblDescription;
	private List listResources;
	private Canvas canvasRes;
	private Map<String, Shell> resourceShells;
	private ImageData resourceImageData;
	private Image resourceImage;

	private EditorInputFolder resultAlternative;
	private ResultsContainer resultsContainer;
	private XPerformanceProblem currentSelectedProblem;

	private Label lblMessage;

	private ListViewer listViewer;

	private Composite warningComposite;

	private CTabFolder mainTabFolder;

	PropertyChangeListener connecionListener = new PropertyChangeListener()
	{
		@Override
		public void propertyChange(PropertyChangeEvent evt)
		{
			if (ResultAlternativeComposite.this.isDisposed())
				return;
			Display.getDefault().asyncExec(new Runnable()
			{
				@Override
				public void run()
				{
					refresh();
				}
			});
		}
	};

	private ScrolledComposite detailsScrollComposite;

	public ResultAlternativeComposite(Composite parent, int style, ResultAlternative resultAlternative)
	{
		super(parent, style, resultAlternative);

		this.resultAlternative = resultAlternative;

		this.resourceShells = new HashMap<>();
		this.extensionItemFactory = new ImmutableExtensionItemFactory(resultAlternative.getName());

		getContainer().setLayout(new StackLayout());

		warningComposite = new Composite(getContainer(), SWT.BORDER);
		warningComposite.setLayout(new GridLayout(1, false));
		Label lblEmpty = new Label(warningComposite, SWT.CENTER);
		lblEmpty.setLayoutData(new GridData(SWT.CENTER, SWT.BOTTOM, true, true, 1, 1));
		lblEmpty.setText(
				"Spotter client not connected... \nTo enable editing, go to Server section and connect Spotter client.");

		Button btnStartServer = new Button(warningComposite, SWT.NONE);
		GridData gd_btnStartServer = new GridData(SWT.CENTER, SWT.TOP, true, true, 1, 1);
		gd_btnStartServer.verticalIndent = 20;
		gd_btnStartServer.heightHint = 42;
		btnStartServer.setLayoutData(gd_btnStartServer);
		btnStartServer.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				Util.startBuiltinServerAndConnectAsync(ResultAlternativeComposite.this.resultAlternative.getProject());
			}
		});

		SpotterClientController.getController(resultAlternative.getProject())
				.addPropertyChangeListener(SpotterClientController.PROP_CONNECTION, connecionListener);

		btnStartServer.setText("Start server");

		// mainComposite = new Composite(getContainer(), SWT.NONE);
		// mainComposite.setLayout(new GridLayout(1, false));

		mainTabFolder = new CTabFolder(getContainer(), SWT.BORDER);
		mainTabFolder.setTabHeight(32);

		createHierarchyTab(mainTabFolder);
		createReportTab(mainTabFolder);
		refresh();

		// Selections
		mainTabFolder.setSelection(0);

		updateCanvasImage();

		addDisposeListener(new DisposeListener()
		{
			@Override
			public void widgetDisposed(DisposeEvent e)
			{
				Set<Shell> shells = new HashSet<>();
				shells.addAll(resourceShells.values());
				for (Shell shell : shells) {
					shell.close();
				}

				SpotterClientController.getController(ResultAlternativeComposite.this.resultAlternative.getProject())
						.removePropertyChangeListener(SpotterClientController.PROP_CONNECTION, connecionListener);
			}
		});
	}

	@Override
	public void refresh()
	{
		if (SpotterClientController.getController(resultAlternative.getProject()).isConnected()) {
			((StackLayout) getContainer().getLayout()).topControl = mainTabFolder;
			getContainer().layout();
			updateTabs();
		} else {
			((StackLayout) getContainer().getLayout()).topControl = warningComposite;
			getContainer().layout();
		}
	}

	private void createHierarchyTab(CTabFolder folder)
	{
		CTabItem tabItem = new CTabItem(folder, SWT.NONE);
		tabItem.setText(TAB_HIERARCHY_NAME);
		
		Composite composite = new Composite(mainTabFolder, SWT.NONE);
		tabItem.setControl(composite);
		GridLayout gl_composite = new GridLayout(1, false);
		gl_composite.verticalSpacing = 10;
		composite.setLayout(gl_composite);
		
		Composite containerTable = new Composite(composite, SWT.NONE);
		GridData gd_containerTable = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_containerTable.heightHint = 80;
		containerTable.setLayoutData(gd_containerTable);
		containerTable.setLayout(new FillLayout());
		createTableComposite(containerTable);
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		GridData gd_composite_1 = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_composite_1.heightHint = 5;
		composite_1.setLayoutData(gd_composite_1);
		
		Composite containerDetails = new Composite(composite, SWT.NONE);
		GridData gd_containerDetails = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_containerDetails.heightHint = 120;
		containerDetails.setLayoutData(gd_containerDetails);
		containerDetails.setLayout(new FillLayout());
		createDetailsComposite(containerDetails);
		
		Composite containerCharts = new Composite(composite, SWT.NONE);
		containerCharts.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		containerCharts.setLayout(new FillLayout());
		createChartsComposite(containerCharts);


		addSelectionListeners();
	}
	
	private void createTableComposite (Composite container)
	{
		hierarchyTreeViewer = ExtensionsGroupViewer.createTreeViewer(container,
				extensionItemFactory.createExtensionItem(), null, false);
		SpotterExtensionsLabelProvider labelProvider = (SpotterExtensionsLabelProvider) hierarchyTreeViewer
				.getLabelProvider();
		imageProvider = new ResultExtensionsImageProvider();
		labelProvider.setImageProvider(imageProvider);
	}
	
	private void createDetailsComposite (Composite container)
	{
		detailsScrollComposite = new ScrolledComposite(container,
				SWT.BORDER | SWT.V_SCROLL);
		detailsScrollComposite.setExpandHorizontal(true);
		detailsScrollComposite.setExpandVertical(true);
		
		Composite compUpperPart = new Composite(detailsScrollComposite, SWT.NONE);
		//compUpperPart.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		compUpperPart.setLayout(new GridLayout(2, false));

		Label label = new Label(compUpperPart, SWT.NONE);
		label.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		label.setText("Problem Name:");
		lblProblemName = new Label(compUpperPart, SWT.NONE);
		lblProblemName.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, false));
		lblProblemName.setText(LABEL_NONE_SELECTED);

		label = new Label(compUpperPart, SWT.NONE);
		label.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		label.setText("Status:");
		lblStatus = new Label(compUpperPart, SWT.NONE);
		lblStatus.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, false));
		lblStatus.setText("");

		label = new Label(compUpperPart, SWT.NONE);
		label.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		label.setText("Description:");
		lblDescription = new Label(compUpperPart, SWT.WRAP);
		lblDescription.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, false));
		lblDescription.setText("");

		Label lblMessageTitle = new Label(compUpperPart, SWT.NONE);
		lblMessageTitle.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		lblMessageTitle.setText("Message:");
		lblMessage = new Label(compUpperPart, SWT.NONE);

		detailsScrollComposite.setContent(compUpperPart);
		detailsScrollComposite.setMinSize(compUpperPart.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
	
	private void createChartsComposite (Composite container)
	{
		Group grpResources = new Group(container, SWT.NONE);
		grpResources.setText("Resources");
		grpResources.setLayout(new FillLayout(SWT.HORIZONTAL));
		SashForm sashResources = new SashForm(grpResources, SWT.HORIZONTAL | SWT.SMOOTH);
		sashResources.setSashWidth(8);

		listViewer = new ListViewer(sashResources, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		listResources = listViewer.getList();// new List(sashResources,
												// SWT.BORDER | SWT.H_SCROLL |
												// SWT.V_SCROLL);
		listViewer.setContentProvider(new ArrayContentProvider());
		listViewer.setLabelProvider(new LabelProvider()
		{
			@Override
			public String getText(Object element)
			{
				return element.toString().replaceFirst("-org.spotter.demo.app.", "");
			}
		});
		canvasRes = new Canvas(sashResources, SWT.NONE);
		sashResources.setWeights(new int[] {3, 7});
		addCanvasListeners();
	}

	private void addCanvasListeners()
	{
		canvasRes.addMouseTrackListener(new MouseTrackAdapter()
		{
			private Shell shell;
			private Cursor savedCursor;

			@Override
			public void mouseEnter(MouseEvent e)
			{
				if (resourceImage != null) {
					shell = canvasRes.getShell();
					if (shell != null) {
						savedCursor = shell.getCursor();
						Cursor zoomCursor = Display.getDefault().getSystemCursor(SWT.CURSOR_HAND);
						shell.setCursor(zoomCursor);
					}
				}
			}

			@Override
			public void mouseExit(MouseEvent e)
			{
				if (shell != null) {
					if (!shell.isDisposed()) {
						shell.setCursor(savedCursor);
					}
					shell = null;
				}
				savedCursor = null;
			}
		});

		canvasRes.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseDown(MouseEvent e)
			{
				if (resourceImageData != null && !listViewer.getSelection().isEmpty()) {
					String resourceName = ((StructuredSelection) listViewer.getSelection()).getFirstElement()
							.toString();
					String resourceIdentifier = createResourceIdentifier(resourceName);
					if (resourceShells.containsKey(resourceIdentifier)) {
						resourceShells.get(resourceIdentifier).setFocus();
					} else {
						openResourcePopupShell(e.display, resourceIdentifier);
					}
				}
			}
		});

		canvasRes.addControlListener(new ControlAdapter()
		{
			@Override
			public void controlResized(ControlEvent e)
			{
				updateCanvasImage();
			}
		});
	}

	private void addSelectionListeners()
	{
		hierarchyTreeViewer.addPostSelectionChangedListener(new ISelectionChangedListener()
		{
			@Override
			public void selectionChanged(SelectionChangedEvent event)
			{
				IStructuredSelection sel = (IStructuredSelection) event.getSelection();
				currentSelectedProblem = null;
				if (!sel.isEmpty()) {
					IExtensionItem item = (IExtensionItem) sel.getFirstElement();
					Object problem = item.getModelWrapper().getXMLModel();
					if (problem instanceof XPerformanceProblem) {
						currentSelectedProblem = (XPerformanceProblem) problem;
					}
				}
				updateProblemDetails();
			}
		});

		hierarchyTreeViewer.addDoubleClickListener(new IDoubleClickListener()
		{
			@Override
			public void doubleClick(DoubleClickEvent event)
			{
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				Object selectedNode = selection.getFirstElement();
				hierarchyTreeViewer.setExpandedState(selectedNode, !hierarchyTreeViewer.getExpandedState(selectedNode));
			}
		});

		listViewer.addSelectionChangedListener(new ISelectionChangedListener()
		{
			@Override
			public void selectionChanged(SelectionChangedEvent event)
			{
				updateCanvasImage();
			}

		});
	}

	private void updateCanvasImage()
	{
		if (!listViewer.getSelection().isEmpty()) {
			String selection = ((StructuredSelection) listViewer.getSelection()).getFirstElement().toString();
			String prefix = getCurrentResourceFolder();
			String resourceFile = prefix + selection;
			if (resourceImage != null) {
				resourceImage.dispose();
				resourceImage = null;
			}
			resourceImageData = null;
			File file = new File(resourceFile);
			int canvasWidth = canvasRes.getBounds().width;
			int canvasHeight = canvasRes.getBounds().height;
			canvasWidth = Math.min(canvasWidth, canvasHeight * 8 / 5);

			boolean isCanvasVisible = canvasWidth > 0 && canvasHeight > 0;

			if (isCanvasVisible) {
				if (file.exists()) {
					resourceImageData = new ImageData(resourceFile);
					int width = Math.min(canvasWidth, resourceImageData.width);
					int height = Math.min(canvasHeight, resourceImageData.height);
					resourceImage = new Image(canvasRes.getDisplay(), resourceImageData.scaledTo(width, height));
				} else {
					// draw "not available image" picture using GC
					final Display display = canvasRes.getDisplay();
					final Rectangle bounds = canvasRes.getBounds();
					resourceImage = new Image(display, bounds);
					drawNotAvailableImage(resourceImage, display, bounds);
				}
			}

			canvasRes.setBackgroundImage(resourceImage);
			if (resourceImage != null)
				canvasRes.setSize(resourceImage.getBounds().width, resourceImage.getBounds().height);
		}
	}

	private void drawNotAvailableImage(Image image, Display display, Rectangle bounds)
	{
		GC gc = new GC(image);
		gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
		gc.setForeground(display.getSystemColor(SWT.COLOR_RED));

		gc.fillRectangle(0, 0, bounds.width, bounds.height);
		gc.drawLine(0, 0, bounds.width, bounds.height);
		gc.drawLine(0, bounds.height, bounds.width, 0);

		final int flags = SWT.DRAW_TRANSPARENT;
		Point textExtent = gc.textExtent(NOT_AVAILABLE_IMG_TEXT, flags);

		int x = (bounds.width - textExtent.x) / 2;
		int y = bounds.height - textExtent.y;
		gc.drawText(NOT_AVAILABLE_IMG_TEXT, x, y, flags);
		gc.dispose();
	}

	private void openResourcePopupShell(final Display display, final String resourceIdentifier)
	{
		final Shell popupShell = new Shell(SWT.BORDER | SWT.RESIZE | SWT.CLOSE);
		Label label = new Label(popupShell, SWT.NONE);
		Rectangle clientArea = display.getPrimaryMonitor().getClientArea();

		final Image image = new Image(display, createScaledImageData(clientArea));
		label.setImage(image);

		popupShell.setLayout(new FillLayout());
		popupShell.setImage(Activator.getImage(SpotterProjectParent.IMAGE_PATH));
		String resultAlternativeName = resultAlternative.getName();
		popupShell.setText(String.format(DLG_RESOURCE_TITLE, resourceIdentifier, resultAlternativeName));
		popupShell.pack();

		Rectangle shellRect = popupShell.getBounds();
		int x = clientArea.x + (clientArea.width - shellRect.width) / 2;
		int y = clientArea.y + (clientArea.height - shellRect.height) / 2;
		popupShell.setLocation(x, y);

		popupShell.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.keyCode == SWT.ESC) {
					popupShell.close();
				}
			}
		});
		popupShell.addShellListener(new ShellAdapter()
		{
			@Override
			public void shellClosed(ShellEvent e)
			{
				image.dispose();
				resourceShells.remove(resourceIdentifier);
			}
		});

		resourceShells.put(resourceIdentifier, popupShell);
		popupShell.open();
	}

	private ImageData createScaledImageData(Rectangle clientArea)
	{
		int width = resourceImageData.width;
		int height = resourceImageData.height;
		int maxImageWidth = clientArea.width;
		int maxImageHeight = clientArea.height;

		int scaledWidth = Math.min(width, maxImageWidth);
		int scaledHeight = Math.min(height, maxImageHeight);
		float wFactor = ((float) scaledWidth) / width;
		float hFactor = ((float) scaledHeight) / height;
		boolean scaleToFitWidth = wFactor <= hFactor;
		if (scaleToFitWidth) {
			width = scaledWidth;
			height *= wFactor;
		} else {
			width *= hFactor;
			height = scaledHeight;
		}

		return resourceImageData.scaledTo(width, height);
	}

	private String createResourceIdentifier(String resourceName)
	{
		return resultAlternative.getResource().getName() + RESOURCE_SEPARATOR_CHAR + resourceName;
	}

	private void updateProblemDetails()
	{
		listViewer.setInput(new String[0]);
		if (resourceImage != null) {
			resourceImage.dispose();
			resourceImage = null;
		}
		resourceImageData = null;
		canvasRes.setBackgroundImage(null);

		if (currentSelectedProblem == null) {
			lblProblemName.setText(LABEL_NONE_SELECTED);
			lblDescription.setText("");
			lblStatus.setText("");
			lblMessage.setText("");
		} else {
			String name = currentSelectedProblem.getExtensionName();
			lblProblemName.setText(name);
			String description = SpotterClientController.getController(resultAlternative.getProject()).getClient()
					.getExtensionDescription(name);
			lblDescription.setText(description == null ? LABEL_NO_INFO : description);

			String id = currentSelectedProblem.getUniqueId();
			SpotterResult spotterResult = resultsContainer == null ? null : resultsContainer.getResultsMap().get(id);
			if (spotterResult == null) {
				lblStatus.setText(LABEL_NO_LOOKUP);
				lblMessage.setText(LABEL_NO_LOOKUP);
			} else {
				lblStatus.setText(spotterResult.isDetected() ? LABEL_DETECTED : LABEL_NOT_DETECTED);
				lblMessage.setText(spotterResult.getMessage());
				populateResourcesList(spotterResult);
				updateCanvasImage();
			}
		}
		((Composite)detailsScrollComposite.getContent()).layout();
		detailsScrollComposite.setMinSize(detailsScrollComposite.getContent().computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	private void populateResourcesList(SpotterResult spotterResult)
	{
		listViewer.setInput(spotterResult.getResourceFiles().toArray(new String[0]));
		if (listResources.getItemCount() > 0)
			listResources.select(0);
	}

	private void createReportTab(CTabFolder folder)
	{
		CTabItem tabItem = new CTabItem(folder, SWT.NONE);
		tabItem.setText(TAB_REPORT_NAME);

		textReport = new Text(folder, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		textReport.setText(EMPTY_RESULTS);
		textReport.setEditable(false);

		tabItem.setControl(textReport);
	}

	private void updateTabs()
	{
		if (!SpotterClientController.getController(this.resultAlternative.getProject()).isConnected())
			return;

		if (resultsContainer == null) {
			IFile file = resultAlternative.getResource()
					.getFile(ResultsLocationConstants.RESULTS_SERIALIZATION_FILE_NAME);
			if (!file.exists())
				return;

			try {
				resultsContainer = (ResultsContainer) LpeFileUtils.readObject(file.getLocation().toFile());
				updateHierarchy();
				updateReport();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void updateHierarchy()
	{
		IExtensionItem input = null;

		if (resultsContainer != null) {
			XPerformanceProblem root = resultsContainer.getRootProblem();
			if (root != null) {
				String projectName = resultAlternative.getProject().getName();
				input = HierarchyEditor.createPerformanceProblemHierarchy(projectName, extensionItemFactory, root);
			}
		}

		imageProvider.setResultsContainer(resultsContainer);
		if (input == null) {
			input = extensionItemFactory.createExtensionItem();
		}
		hierarchyTreeViewer.setInput(input);
		hierarchyTreeViewer.expandAll();

		if (hierarchyTreeViewer.getTree().getItemCount() > 0) {
			hierarchyTreeViewer.getTree().setSelection(hierarchyTreeViewer.getTree().getItems()[0]);
			IExtensionItem iExtensionItem = input.getItems()[0];
			Object problem = iExtensionItem.getModelWrapper().getXMLModel();
			if (problem instanceof XPerformanceProblem) {
				currentSelectedProblem = (XPerformanceProblem) problem;
			}
			updateProblemDetails();
		}
	}

	private void updateReport()
	{
		if (resultsContainer != null && resultsContainer.getReport() != null) {
			textReport.setText(resultsContainer.getReport());
		} else {
			textReport.setText(ERR_MSG_MISSING_REPORT);
		}
	}

	private String getCurrentResourceFolder()
	{
		IFolder folder = resultAlternative.getResource();
		String currentRunFolder = folder.getLocation().toString() + "/";
		String subDirPath = getSubDirPathForProblem(currentSelectedProblem);

		return currentRunFolder + subDirPath;
	}

	private static String getSubDirPathForProblem(XPerformanceProblem problem)
	{
		// TODO: there must be a way to retrieve this path from the result
		// independent of the extension's implementation!
		String name = problem.getExtensionName();
		String idTag = "" + problem.getUniqueId().hashCode();

		String subDirPath = name + "-" + idTag + "/" + ResultsLocationConstants.RESULT_RESOURCES_SUB_DIR + "/";

		return subDirPath;
	}
}
