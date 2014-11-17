package eu.cloudscaleproject.env.spotter.editors.composite;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spotter.eclipse.ui.Activator;
import org.spotter.eclipse.ui.ServiceClientWrapper;
import org.spotter.eclipse.ui.editors.HierarchyEditor;
import org.spotter.eclipse.ui.model.ExtensionItem;
import org.spotter.eclipse.ui.model.IExtensionItem;
import org.spotter.eclipse.ui.navigator.SpotterProjectParent;
import org.spotter.eclipse.ui.providers.ResultExtensionsImageProvider;
import org.spotter.eclipse.ui.providers.SpotterExtensionsLabelProvider;
import org.spotter.eclipse.ui.util.DialogUtils;
import org.spotter.eclipse.ui.util.WidgetUtils;
import org.spotter.eclipse.ui.viewers.ExtensionsGroupViewer;
import org.spotter.shared.hierarchy.model.XPerformanceProblem;
import org.spotter.shared.result.ResultsLocationConstants;
import org.spotter.shared.result.model.ResultsContainer;
import org.spotter.shared.result.model.SpotterResult;

public class ResultComposite extends Composite{
	
	public static final String VIEW_ID = "org.spotter.eclipse.ui.view.resultsView";

	private static final Logger LOGGER = LoggerFactory.getLogger(ResultComposite.class);

	private static final String RESULTS_VIEW_TITLE = "Results";
	private static final String DLG_RESOURCE_TITLE = "Resource '%s' (%s)";
	private static final int DLG_RESOURCE_TOP_MARGIN = 40;
	private static final int RESOURCES_LIST_RATIO = 45;
	private static final int RESOURCES_CANVAS_RATIO = 55;

	//private static final String RESULTS_CONTENT_DESC_TEMPLATE = "DynamicSpotter Run '%s' of alternative '%s'";
	private static final String EMPTY_RESULTS = "No results selected.";
	private static final String ERR_MSG_IO_ERROR = "An I/O error occured while reading the file '%s'.";
	private static final String ERR_MSG_MISSING_REPORT = "Could not find the spotter report file.";
	private static final String ERR_MSG_MISSING_SER_FILE = "Could not find the spotter serialization file.";

	private static final String LABEL_NONE_SELECTED = "<none selected>";
	private static final String LABEL_DETECTED = "Detected";
	private static final String LABEL_NOT_DETECTED = "Not Detected";
	private static final String LABEL_NO_LOOKUP = "The corresponding result could not be looked up. (erroneous analysis)";
	private static final String LABEL_NO_INFO = "No description available.";

	private static final String TAB_HIERARCHY_NAME = "Hierarchy";
	private static final String TAB_REPORT_NAME = "Report";

	private static final String RESOURCE_SEPARATOR_CHAR = "/";

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
	private Map<String, Shell> resourceShells = new HashMap<>();
	private ImageData resourceImageData;
	private Image resourceImage;

	private ResultsContainer resultsContainer;
	private XPerformanceProblem currentSelectedProblem;
	
	private final IFolder resultFolder;
	private final String clientName;
	
	public ResultComposite(Composite parent, int style, String clientName, IFolder resFolder) {
		super(parent, style);
		this.resultFolder = resFolder;
		this.clientName = clientName;
		
		this.setLayout(new FillLayout());
		
		TabFolder tabFolder = new TabFolder(this, SWT.NONE);
		createHierarchyTab(tabFolder);
		createReportTab(tabFolder);
		
		updateTabs();
	}
	
	private void updateTabs() {
		
		ServiceClientWrapper client = Activator.getDefault().getClient(clientName);
		if(client.testConnection(false)){
			updateHierarchy();
			updateReport();
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
	
	private void createHierarchyTab(TabFolder folder) {
		TabItem tabItem = new TabItem(folder, SWT.NONE);
		tabItem.setText(TAB_HIERARCHY_NAME);

		Composite parent = new Composite(folder, SWT.NONE);
		parent.setLayout(new FillLayout());
		SashForm container = new SashForm(parent, SWT.VERTICAL | SWT.SMOOTH);

		hierarchyTreeViewer = ExtensionsGroupViewer.createTreeViewer(container, new ExtensionItem());
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
	
	private void updateReport() {
		IFile file = resultFolder.getFile(ResultsLocationConstants.TXT_REPORT_FILE_NAME);
		
		StringBuilder sb = new StringBuilder();
		try {
			if (!file.isSynchronized(IResource.DEPTH_ZERO)) {
				file.refreshLocal(IResource.DEPTH_ZERO, null);
			}
			BufferedInputStream bufferedInStream = new BufferedInputStream(file.getContents());
			int readByte;
			while ((readByte = bufferedInStream.read()) != -1) {
				sb.append((char) readByte);
			}
			bufferedInStream.close();
			textReport.setText(sb.toString());
		} catch (CoreException e) {
			String text = ERR_MSG_MISSING_REPORT + " (" + file.getName() + ")";
			LOGGER.error(text + (e.getMessage() != null ? " (" + e.getMessage() + ")" : ""));
			textReport.setText(text);
			DialogUtils.openWarning(RESULTS_VIEW_TITLE, text);
		} catch (IOException e) {
			String text = String.format(ERR_MSG_IO_ERROR, file.getName());
			LOGGER.error(text + (e.getMessage() != null ? " (" + e.getMessage() + ")" : ""));
			textReport.setText(text);
			DialogUtils.openWarning(RESULTS_VIEW_TITLE, text);
		}
	}
	
	private void updateHierarchy() {
		IFile file = resultFolder.getFile(ResultsLocationConstants.RESULTS_SERIALIZATION_FILE_NAME);

		IExtensionItem input = null;
		try {
			if (!file.isSynchronized(IResource.DEPTH_ZERO)) {
				file.refreshLocal(IResource.DEPTH_ZERO, null);
			}
			BufferedInputStream bufferedInStream = new BufferedInputStream(file.getContents());
			ObjectInputStream objectIn = new ObjectInputStream(bufferedInStream);
			resultsContainer = (ResultsContainer) objectIn.readObject();
			objectIn.close();
			bufferedInStream.close();

			XPerformanceProblem root = resultsContainer.getRootProblem();
			if (root != null) {
				IProject project = resultFolder.getProject();
				//input = HierarchyEditor.createPerformanceProblemHierarchy(project.getName(), root);
			}
		} catch (CoreException e) {
			resultsContainer = null;
			String text = ERR_MSG_MISSING_SER_FILE + " (" + file.getName() + ")";
			LOGGER.error(text + (e.getMessage() != null ? " (" + e.getMessage() + ")" : ""));
			DialogUtils.openWarning(RESULTS_VIEW_TITLE, text);
		} catch (IOException | ClassNotFoundException e) {
			resultsContainer = null;
			String text = String.format(ERR_MSG_IO_ERROR, file.getName());
			LOGGER.error(text + (e.getMessage() != null ? " (" + e.getMessage() + ")" : ""));
			DialogUtils.openWarning(RESULTS_VIEW_TITLE, text);
		} finally {
			imageProvider.setResultsContainer(resultsContainer);
			if (input == null) {
				input = new ExtensionItem();
			}
			hierarchyTreeViewer.setInput(input);
			hierarchyTreeViewer.expandAll();
		}
	}
	
	/*
	private void resetHierarchy() {
		hierarchyTreeViewer.setInput(new ExtensionItem());
		lblProblemName.setText(LABEL_NONE_SELECTED);
		lblDescription.setText("");
		lblStatus.setText("");
		textResult.setText("");
		listResources.removeAll();
		for (Shell shell : resourceShells.values()) {
			shell.close();
		}
		if (resourceImage != null) {
			resourceImage.dispose();
		}
		resourceImage = null;
		resourceImageData = null;
		canvasRes.setBackgroundImage(null);
	}

	private void resetReport() {
		textReport.setText(EMPTY_RESULTS);
	}
	*/
	
	private String createResourceIdentifier(String resourceName) {
		return resultFolder.getName() + RESOURCE_SEPARATOR_CHAR + resourceName;
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
						openResourcePopupShell(resourceIdentifier);
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
					ExtensionItem item = (ExtensionItem) sel.getFirstElement();
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
	
	private void openResourcePopupShell(final String resourceIdentifier) {
		final Shell popupShell = new Shell(SWT.ON_TOP | SWT.RESIZE | SWT.CLOSE);
		Label label = new Label(popupShell, SWT.NONE);
		Display display = Display.getDefault();
		Rectangle displayRect = display.getClientArea();

		final Image image = new Image(display, createScaledImageData(displayRect));
		label.setImage(image);

		popupShell.setLayout(new FillLayout());
		popupShell.setImage(Activator.getImage(SpotterProjectParent.IMAGE_PATH));
		popupShell.setText(String.format(DLG_RESOURCE_TITLE, resourceIdentifier, resultFolder.getName()));
		popupShell.pack();

		Rectangle splashRect = popupShell.getBounds();
		int x = (displayRect.width + displayRect.x - splashRect.width) / 2;
		int y = (displayRect.height + displayRect.y - splashRect.height) / 2;
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
	
	private String getCurrentResourceFolder() {		
		String currentRunFolder = resultFolder.getLocation().toString() + "/";
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
	
	private void updateCanvasImage() {
		if (listResources.getSelectionCount() > 0) {
			String selection = listResources.getSelection()[0];
			String prefix = getCurrentResourceFolder();
			String resourceFile = prefix + selection;
			if (resourceImage != null) {
				resourceImage.dispose();
				resourceImageData = null;
				resourceImage = null;
			}
			File file = new File(resourceFile);
			int canvasWidth = canvasRes.getBounds().width;
			int canvasHeight = canvasRes.getBounds().height;
			if (file.exists() && canvasWidth > 0 && canvasHeight > 0) {
				resourceImageData = new ImageData(resourceFile);
				int width = Math.min(canvasWidth, resourceImageData.width);
				int height = Math.min(canvasHeight, resourceImageData.height);
				resourceImage = new Image(canvasRes.getDisplay(), resourceImageData.scaledTo(width, height));
			} // else {
				// TODO: draw "not available image" picture using GC
			// }
			canvasRes.setBackgroundImage(resourceImage);
		}
	}
	
	private void updateProblemDetails() {
		listResources.removeAll();
		if (resourceImage != null) {
			resourceImage.dispose();
			resourceImageData = null;
			resourceImage = null;
		}
		canvasRes.setBackgroundImage(null);

		if (currentSelectedProblem == null) {
			lblProblemName.setText(LABEL_NONE_SELECTED);
			lblDescription.setText("");
			lblStatus.setText("");
			textResult.setText("");
		} else {
			String name = currentSelectedProblem.getExtensionName();
			lblProblemName.setText(name);
			String description = Activator.getDefault().getClient(clientName).getExtensionDescription(name);
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
	
	private ImageData createScaledImageData(Rectangle displayRect) {
		int width = resourceImageData.width;
		int height = resourceImageData.height;
		int maxImageWidth = displayRect.width + displayRect.x;
		int maxImageHeight = displayRect.height + displayRect.y - DLG_RESOURCE_TOP_MARGIN;

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

}
