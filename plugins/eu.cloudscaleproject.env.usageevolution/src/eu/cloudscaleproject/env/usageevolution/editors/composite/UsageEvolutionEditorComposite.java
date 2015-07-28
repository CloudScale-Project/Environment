package eu.cloudscaleproject.env.usageevolution.editors.composite;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.generator.editor.views.PlotCanvas;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.ProjectEditorSelectionService;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.ui.InputEditorView;
import eu.cloudscaleproject.env.toolchain.util.EMFEditableTreeviewComposite;
import eu.cloudscaleproject.env.usageevolution.UsageEvolutionAlternative;

public class UsageEvolutionEditorComposite extends InputEditorView implements IPropertySheetPageProvider, ISelectable, IRefreshable
{

	// private static final String PLOTVIEWID =
	// "tools.descartes.dlim.generator.editor.views.PlotView";

	private final IProject project;
	private final UsageEvolutionAlternative alternative;

	private final PlotCanvas plotCanvas;
	private final EMFEditableTreeviewComposite treeviewEditor;
	
	private PropertyChangeListener alternativeListener = new PropertyChangeListener()
	{
		@Override
		public void propertyChange(PropertyChangeEvent evt)
		{
			Display.getDefault().syncExec(new Runnable() {
				@Override
				public void run() {
					updatePlotCanvas();
				}
			});
		}
	};

	public UsageEvolutionEditorComposite(final UsageEvolutionAlternative alt, Composite parent, int style)
	{
		super(parent, style, alt);

		this.project = alt.getProject();
		this.alternative = alt;

		getContainer().setLayout(new GridLayout(1, false));

		Label lblTitle = new Label(getContainer(), SWT.NONE);
		lblTitle.setText("Usage evolution arrival rate editor:");
		lblTitle.setLayoutData(new GridData());

		treeviewEditor = new EMFEditableTreeviewComposite(alt, getContainer(), SWT.NONE);
		GridData gd_tree = new GridData(SWT.FILL, SWT.FILL, true, false);
		gd_tree.heightHint = 120;
		gd_tree.minimumHeight = 120;
		treeviewEditor.setLayoutData(gd_tree);

		plotCanvas = new PlotCanvas(getContainer(), SWT.NONE, true);
		plotCanvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		updatePlotCanvas();
		
		alternative.addPropertyChangeListener(alternativeListener);
		
		addDisposeListener(new DisposeListener()
		{
			@Override
			public void widgetDisposed(DisposeEvent e)
			{
				alternative.removePropertyChangeListener(alternativeListener);
			}
		});
	}
	
	private void updatePlotCanvas ()
	{
		Resource modelResource = this.alternative.getModelResource(ToolchainUtils.KEY_FILE_LIMBO);
	
		if(modelResource == null){
			return;
		}
		
		EList<EObject> contents = modelResource.getContents();
		if (!contents.isEmpty())
		{
			plotCanvas.setRootSequence((Sequence) contents.get(0));
			plotCanvas.redraw();
		}
	}

	@Override
	public void onSelect()
	{
		ProjectEditorSelectionService.getInstance().reloadPropertySheetPage();
		ValidationDiagramService.showStatus(project, CSTool.USAGEEVOLUTION.getID(), alternative);
	}

	@Override
	public IPropertySheetPage getPropertySheetPage()
	{
		return treeviewEditor.getPropertySheetPage();
	}

	@Override
	public void refresh()
	{
		updatePlotCanvas();
		ProjectEditorSelectionService.getInstance().reloadPropertySheetPage();
	}
}
