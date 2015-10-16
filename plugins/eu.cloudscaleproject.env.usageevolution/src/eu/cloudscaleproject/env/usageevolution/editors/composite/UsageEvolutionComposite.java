package eu.cloudscaleproject.env.usageevolution.editors.composite;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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

import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.ui.AbstractEditorView;
import eu.cloudscaleproject.env.toolchain.ui.widgets.TitleWidget;
import eu.cloudscaleproject.env.toolchain.ui.widgets.ValidationWidget;
import eu.cloudscaleproject.env.toolchain.util.EMFEditableTreeviewComposite;
import eu.cloudscaleproject.env.toolchain.util.PropertyPageComposite;
import eu.cloudscaleproject.env.usageevolution.UsageEvolutionAlternative;
import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.generator.editor.views.PlotCanvas;

public class UsageEvolutionComposite extends AbstractEditorView implements ISelectable, IRefreshable
{
	
	private final UsageEvolutionAlternative alternative;

	private final PlotCanvas plotCanvas;
	private final EMFEditableTreeviewComposite treeviewEditor;
	
	private PropertyChangeListener alternativeListener = new PropertyChangeListener() {
		
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

	public UsageEvolutionComposite(Composite parent, int style, final UsageEvolutionAlternative alt){
		
		super(parent, style, alt);
		this.alternative = alt;

		new TitleWidget(getHeader(), style, alt);
		getContainer().setLayout(new GridLayout(1, false));
		new ValidationWidget(getFooter(), style, alt);

		Label lblTitle = new Label(getContainer(), SWT.NONE);
		lblTitle.setText("Usage evolution arrival rate editor:");
		lblTitle.setLayoutData(new GridData());

		//tree view
		treeviewEditor = new EMFEditableTreeviewComposite(alt, getContainer(), SWT.NONE);
		GridData gd_tree = new GridData(SWT.FILL, SWT.FILL, true, false);
		gd_tree.heightHint = 120;
		gd_tree.minimumHeight = 120;
		treeviewEditor.setLayoutData(gd_tree);
		
		//property sheet page
		PropertyPageComposite pageSheet = new PropertyPageComposite(
				getContainer(), SWT.BORDER, treeviewEditor.getPropertySheetPage());
		pageSheet.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		//plot
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
	
	private void updatePlotCanvas () {
		
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
	public void onSelect() {
	}

	@Override
	public void refresh() {
		updatePlotCanvas();
	}
}
