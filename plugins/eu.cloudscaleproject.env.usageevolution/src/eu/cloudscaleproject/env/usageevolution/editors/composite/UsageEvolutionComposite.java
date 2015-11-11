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
import org.eclipse.swt.widgets.Group;

import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.common.ui.SplitComposite;
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
			Display.getDefault().asyncExec(new Runnable() {
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

		new TitleWidget(getHeader(), style, alt){
			@Override
			protected void initButtons()
			{
				// TODO Auto-generated method stub
				createSeparator();
				super.initButtons();
			}
		};
		new ValidationWidget(getFooter(), style, alt);

		Group containerEditor = new Group(getContainer(), SWT.NONE);
		containerEditor.setText("Usage evolution arrival rate editor");
		containerEditor.setLayout(new GridLayout());

		SplitComposite splitComposite = new SplitComposite(containerEditor, SWT.NONE);
		splitComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		//tree view
		treeviewEditor = new EMFEditableTreeviewComposite(alt, splitComposite, SWT.NONE);
		splitComposite.setTopControl(treeviewEditor);
		
		//property sheet page
		PropertyPageComposite pageSheet = new PropertyPageComposite(
				splitComposite, SWT.BORDER, treeviewEditor.getPropertySheetPage());
		splitComposite.setBottomControl(pageSheet);
		
		//plot
		plotCanvas = new PlotCanvas(containerEditor, SWT.NONE, true);
		plotCanvas.setSize(100, 150);
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
		
		if(isDisposed()){
			return;
		}
		
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
