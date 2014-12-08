package eu.cloudscaleproject.env.spotter.editors.composite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
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
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.lpe.common.util.LpeFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spotter.eclipse.ui.Activator;
import org.spotter.eclipse.ui.ServiceClientWrapper;
import org.spotter.eclipse.ui.editors.HierarchyEditor;
import org.spotter.eclipse.ui.model.IExtensionItem;
import org.spotter.eclipse.ui.model.IExtensionItemFactory;
import org.spotter.eclipse.ui.model.ImmutableExtensionItemFactory;
import org.spotter.eclipse.ui.navigator.SpotterProjectParent;
import org.spotter.eclipse.ui.providers.ResultExtensionsImageProvider;
import org.spotter.eclipse.ui.providers.SpotterExtensionsLabelProvider;
import org.spotter.eclipse.ui.util.DialogUtils;
import org.spotter.eclipse.ui.util.WidgetUtils;
import org.spotter.eclipse.ui.view.ResultsView;
import org.spotter.eclipse.ui.viewers.ExtensionsGroupViewer;
import org.spotter.shared.hierarchy.model.XPerformanceProblem;
import org.spotter.shared.result.ResultsLocationConstants;
import org.spotter.shared.result.model.ResultsContainer;
import org.spotter.shared.result.model.SpotterResult;

import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;

public class ResultDataComposite extends Composite{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResultsView.class);

	//private static final String RESULTS_VIEW_TITLE = "Results";
	private static final String DLG_RESOURCE_TITLE = "Resource '%s' (%s)";
	private static final int RESOURCES_LIST_RATIO = 45;
	private static final int RESOURCES_CANVAS_RATIO = 55;

	//private static final String RESULTS_CONTENT_DESC_TEMPLATE = "DynamicSpotter Run '%s' of project '%s'";
	//private static final String RESULTS_EMPTY_CONTENT_DESC = "None selected.";
	private static final String EMPTY_RESULTS = "No results selected.";
	private static final String ERR_MSG_PARSE_ERROR = "An error occured while parsing the file '%s'.";
	private static final String ERR_MSG_RES_REFRESH = "Error occured while refreshing resource!";
	private static final String ERR_MSG_MISSING_REPORT = "Either file is missing or report is not set.";
	private static final String ERR_MSG_MISSING_SER_FILE = "Could not find the spotter serialization file.";

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

	private Group grpDetails;
	private TreeViewer hierarchyTreeViewer;
	private ResultExtensionsImageProvider imageProvider;
	private Text textReport;
	private Label lblProblemName;
	private Label lblStatus;
	private Label lblDescription;
	private Text textResult;
	private List listResources;
	private Canvas canvasRes;
	private Map<String, Shell> resourceShells;
	private ImageData resourceImageData;
	private Image resourceImage;

	private ServiceClientWrapper client;
	private EditorInputFolder resultAlternative;
	private ResultsContainer resultsContainer;
	private XPerformanceProblem currentSelectedProblem;

	public ResultDataComposite(Composite parent, int style) {
		super(parent, style);
		
		this.client = null;
		this.resultAlternative = null;
		this.resourceShells = new HashMap<>();
		this.extensionItemFactory = new ImmutableExtensionItemFactory();
		
		TabFolder folder = new TabFolder(parent, SWT.NONE);
		createHierarchyTab(folder);
		createReportTab(folder);
		
		addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent e) {
				Set<Shell> shells = new HashSet<>();
				shells.addAll(resourceShells.values());
				for (Shell shell : shells) {
					shell.close();
				}
			}
		});
	}
	
	/**
	 * Sets the run result to show.
	 * 
	 * @param resultAlternative
	 *            the run result item to set
	 */
	public void setResult(EditorInputFolder resultAlternative) {
		this.resultAlternative = resultAlternative;
		if (resultAlternative != null) {
			this.client = Activator.getDefault().getClient(resultAlternative.getResource().getProject().getName());
		} else {
			this.client = null;
		}
		if(this.resultAlternative.getResource().getFile(ResultsLocationConstants.RESULTS_SERIALIZATION_FILE_NAME).exists()){
			updateTabs();
		}
	}
	
	@Override
	public void update() {
		super.update();
		if(this.resultAlternative != null && this.resultAlternative.getResource()
				.getFile(ResultsLocationConstants.RESULTS_SERIALIZATION_FILE_NAME).exists()){
			updateTabs();
		}
	}

	/**
	 * Returns the run result currently shown.
	 * 
	 * @return the run result currently shown or <code>null</code> if none
	 */
	public EditorInputFolder getResult() {
		return resultAlternative;
	}

	/**
	 * Resets the results view and deletes its contents if the given project
	 * matches the current content's associated project. If <code>project</code>
	 * is <code>null</code>, then the view is reset regardless of its current
	 * content.
	 * 
	 * @param project
	 *            the project to match for the reset or <code>null</code> to
	 *            match any
	 */
	/*
	public static void reset(IProject project) {
		for (IWorkbenchWindow window : PlatformUI.getWorkbench().getWorkbenchWindows()) {
			for (IWorkbenchPage page : window.getPages()) {
				ResultsView resultsView = (ResultsView) page.findView(ResultsView.VIEW_ID);
				if (resultsView != null) {
					IEditorInputResource result = resultsView.getResult();
					if (project == null || result != null && project.equals(result.getProject())) {
						resultsView.setResult(null);
					}
				}
			}
		}
	}
	*/

	/**
	 * Resets the results view and deletes its contents if the given project
	 * matches the current content's associated folder. If <code>folder</code>
	 * is <code>null</code>, then the view is reset regardless of its current
	 * content.
	 * 
	 * @param folder
	 *            the folder to match for the reset or <code>null</code> to
	 *            match any
	 */
	/*
	public static void reset(IFolder folder) {
		for (IWorkbenchWindow window : PlatformUI.getWorkbench().getWorkbenchWindows()) {
			for (IWorkbenchPage page : window.getPages()) {
				ResultsView resultsView = (ResultsView) page.findView(ResultsView.VIEW_ID);
				if (resultsView != null) {
					IEditorInputResource result = resultsView.getResult();
					if (folder == null || result != null && folder.equals(result.getResultFolder())) {
						resultsView.setResult(null);
					}
				}
			}
		}
	}
	*/

	private void createHierarchyTab(TabFolder folder) {
		TabItem tabItem = new TabItem(folder, SWT.NONE);
		tabItem.setText(TAB_HIERARCHY_NAME);

		Composite parent = new Composite(folder, SWT.NONE);
		parent.setLayout(new FillLayout());
		SashForm container = new SashForm(parent, SWT.VERTICAL | SWT.SMOOTH);

		hierarchyTreeViewer = ExtensionsGroupViewer.createTreeViewer(container,
				extensionItemFactory.createExtensionItem());
		SpotterExtensionsLabelProvider labelProvider = (SpotterExtensionsLabelProvider) hierarchyTreeViewer
				.getLabelProvider();
		imageProvider = new ResultExtensionsImageProvider();
		labelProvider.setImageProvider(imageProvider);

		grpDetails = new Group(container, SWT.NONE);
		grpDetails.setText("Details");
		grpDetails.setLayout(WidgetUtils.createGridLayout(1, true));

		createHierarchyDetailsUpperPart(grpDetails);
		createHierarchyDetailsLowerPart(grpDetails);

		container.setWeights(new int[] { 1, 2 });

		addSelectionListeners();
		tabItem.setControl(parent);
	}

	private void createHierarchyDetailsUpperPart(Composite parent) {
		Composite compUpperPart = new Composite(parent, SWT.NONE);
		compUpperPart.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
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
	}

	private void createHierarchyDetailsLowerPart(Composite parent) {
		Composite compLowerPart = new Composite(parent, SWT.NONE);
		compLowerPart.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		compLowerPart.setLayout(new FillLayout(SWT.HORIZONTAL));
		SashForm sashContainer = new SashForm(compLowerPart, SWT.HORIZONTAL | SWT.SMOOTH);

		Group grpResult = new Group(sashContainer, SWT.NONE);
		grpResult.setText("Result Message");
		grpResult.setLayout(new FillLayout());

		textResult = new Text(grpResult, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		textResult.setText("");
		textResult.setEditable(false);

		Group grpResources = new Group(sashContainer, SWT.NONE);
		grpResources.setText("Resources");
		grpResources.setLayout(new FillLayout(SWT.HORIZONTAL));
		SashForm sashResources = new SashForm(grpResources, SWT.HORIZONTAL | SWT.SMOOTH);

		listResources = new List(sashResources, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		canvasRes = new Canvas(sashResources, SWT.NONE);

		sashResources.setWeights(new int[] { RESOURCES_LIST_RATIO, RESOURCES_CANVAS_RATIO });
		addCanvasListeners();
	}

	private void addCanvasListeners() {
		canvasRes.addMouseTrackListener(new MouseTrackAdapter() {
			private Shell shell;
			private Cursor savedCursor;

			@Override
			public void mouseEnter(MouseEvent e) {
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
			public void mouseExit(MouseEvent e) {
				if (shell != null) {
					if (!shell.isDisposed()) {
						shell.setCursor(savedCursor);
					}
					shell = null;
				}
				savedCursor = null;
			}
		});

		canvasRes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (resourceImageData != null && listResources.getSelectionCount() > 0) {
					String resourceName = listResources.getSelection()[0];
					String resourceIdentifier = createResourceIdentifier(resourceName);
					if (resourceShells.containsKey(resourceIdentifier)) {
						resourceShells.get(resourceIdentifier).setFocus();
					} else {
						openResourcePopupShell(e.display, resourceIdentifier);
					}
				}
			}
		});

		canvasRes.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				updateCanvasImage();
			}
		});
	}

	private void addSelectionListeners() {
		hierarchyTreeViewer.addPostSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
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

		hierarchyTreeViewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				Object selectedNode = selection.getFirstElement();
				hierarchyTreeViewer.setExpandedState(selectedNode, !hierarchyTreeViewer.getExpandedState(selectedNode));
			}
		});

		listResources.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				updateCanvasImage();
			}
		});
	}

	private void updateCanvasImage() {
		if (listResources.getSelectionCount() > 0) {
			String selection = listResources.getSelection()[0];
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
		}
	}

	private void drawNotAvailableImage(Image image, Display display, Rectangle bounds) {
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

	private void openResourcePopupShell(final Display display, final String resourceIdentifier) {
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

		popupShell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.ESC) {
					popupShell.close();
				}
			}
		});
		popupShell.addShellListener(new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent e) {
				image.dispose();
				resourceShells.remove(resourceIdentifier);
			}
		});

		resourceShells.put(resourceIdentifier, popupShell);
		popupShell.open();
	}

	private ImageData createScaledImageData(Rectangle clientArea) {
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

	private String createResourceIdentifier(String resourceName) {
		return resultAlternative.getResource().getName() + RESOURCE_SEPARATOR_CHAR + resourceName;
	}

	private void updateProblemDetails() {
		listResources.removeAll();
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
			textResult.setText("");
		} else {
			String name = currentSelectedProblem.getExtensionName();
			lblProblemName.setText(name);
			String description = client.getExtensionDescription(name);
			lblDescription.setText(description == null ? LABEL_NO_INFO : description);

			String id = currentSelectedProblem.getUniqueId();
			SpotterResult spotterResult = resultsContainer == null ? null : resultsContainer.getResultsMap().get(id);
			if (spotterResult == null) {
				lblStatus.setText(LABEL_NO_LOOKUP);
				textResult.setText(LABEL_NO_LOOKUP);
			} else {
				lblStatus.setText(spotterResult.isDetected() ? LABEL_DETECTED : LABEL_NOT_DETECTED);
				textResult.setText(spotterResult.getMessage());
				populateResourcesList(spotterResult);
			}
		}
		grpDetails.layout();
	}

	private void populateResourcesList(SpotterResult spotterResult) {
		for (String resourceFile : spotterResult.getResourceFiles()) {
			listResources.add(resourceFile);
		}
	}

	private void createReportTab(TabFolder folder) {
		TabItem tabItem = new TabItem(folder, SWT.NONE);
		tabItem.setText(TAB_REPORT_NAME);

		textReport = new Text(folder, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		textReport.setText(EMPTY_RESULTS);
		textReport.setEditable(false);

		tabItem.setControl(textReport);
	}

	private void updateTabs() {
		if (resultAlternative == null) {
			//setContentDescription(RESULTS_EMPTY_CONTENT_DESC);
			resetHierarchy();
			resetReport();
		} else {
			//String contentDescription = String.format(RESULTS_CONTENT_DESC_TEMPLATE, runResultItem.getText(),
			//		runResultItem.getProject().getName());
			//setContentDescription(contentDescription);
			if (updateResultsContainer()) {
				updateHierarchy();
				updateReport();
			}
		}
	}

	private void resetHierarchy() {
		hierarchyTreeViewer.setInput(extensionItemFactory.createExtensionItem());
		lblProblemName.setText(LABEL_NONE_SELECTED);
		lblDescription.setText("");
		lblStatus.setText("");
		textResult.setText("");
		listResources.removeAll();

		if (resourceImage != null) {
			resourceImage.dispose();
			resourceImage = null;
		}
		resourceImageData = null;
		canvasRes.setBackgroundImage(null);
	}

	private void resetReport() {
		textReport.setText(EMPTY_RESULTS);
	}

	private boolean updateResultsContainer() {

		IFile file = resultAlternative.getResource().getFile(ResultsLocationConstants.RESULTS_SERIALIZATION_FILE_NAME);
		IFolder resultFolder = resultAlternative.getResource();

		resultsContainer = null;
		String errorMsg = null;
		Exception exception = null;
		try {
			File containerFile = new File(file.getLocation().toString());
			if (!containerFile.exists()) {
				try {
					if (!resultFolder.isSynchronized(IResource.DEPTH_INFINITE)) {
						resultFolder.refreshLocal(IResource.DEPTH_INFINITE, null);
					}

					if (resultFolder.exists()) {
						resultFolder.delete(true, null);
					}
					//SpotterProjectResults parent = (SpotterProjectResults) confAlternative.getParent();
					//parent.refreshChildren();
				} catch (CoreException e) {
					LOGGER.error("Error while deleting result folder.", e);
				}
			}

			if (!file.isSynchronized(IResource.DEPTH_ZERO)) {
				file.refreshLocal(IResource.DEPTH_ZERO, null);
			}
			resultsContainer = (ResultsContainer) LpeFileUtils.readObject(containerFile);
		} catch (CoreException e) {
			errorMsg = ERR_MSG_RES_REFRESH;
			exception = e;
			LOGGER.error(ERR_MSG_RES_REFRESH, e);
		} catch (FileNotFoundException e) {
			errorMsg = ERR_MSG_MISSING_SER_FILE;
			exception = e;
			LOGGER.error(DialogUtils.appendCause(ERR_MSG_MISSING_SER_FILE, e.getMessage()));
		} catch (IOException | ClassNotFoundException e) {
			errorMsg = String.format(ERR_MSG_PARSE_ERROR, file.getLocation());
			exception = e;
			LOGGER.error(errorMsg, e);
		}

		if (errorMsg != null) {
			resultsContainer = null;
			DialogUtils.handleError(errorMsg, exception);
			setResult(null);
		}

		return errorMsg == null;
	}

	private void updateHierarchy() {
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
	}

	private void updateReport() {
		if (resultsContainer != null && resultsContainer.getReport() != null) {
			textReport.setText(resultsContainer.getReport());
		} else {
			textReport.setText(ERR_MSG_MISSING_REPORT);
		}
	}

	private String getCurrentResourceFolder() {
		IFolder folder = resultAlternative.getResource();
		String currentRunFolder = folder.getLocation().toString() + "/";
		String subDirPath = getSubDirPathForProblem(currentSelectedProblem);

		return currentRunFolder + subDirPath;
	}

	private static String getSubDirPathForProblem(XPerformanceProblem problem) {
		// TODO: there must be a way to retrieve this path from the result
		// independent of the extension's implementation!
		String name = problem.getExtensionName();
		String idTag = "" + problem.getUniqueId().hashCode();

		String subDirPath = name + "-" + idTag + "/" + ResultsLocationConstants.RESULT_RESOURCES_SUB_DIR + "/";

		return subDirPath;
	}
}
