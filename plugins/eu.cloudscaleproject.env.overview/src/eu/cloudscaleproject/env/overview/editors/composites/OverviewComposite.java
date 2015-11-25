package eu.cloudscaleproject.env.overview.editors.composites;

import org.eclipse.core.resources.IFile;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.common.ui.SplitComposite;
import eu.cloudscaleproject.env.overview.OverviewAlternative;
import eu.cloudscaleproject.env.toolchain.ui.AbstractEditorView;
import eu.cloudscaleproject.env.toolchain.ui.widgets.TitleWidget;
import eu.cloudscaleproject.env.toolchain.ui.widgets.ValidationWidget;
import eu.cloudscaleproject.env.toolchain.util.EMFEditableTreeviewComposite;
import eu.cloudscaleproject.env.toolchain.util.PropertyPageComposite;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class OverviewComposite extends AbstractEditorView implements ISelectable {

	private EMFEditableTreeviewComposite treeviewComposite;
	private OverviewAlternative alternative;
	
	public OverviewComposite(Composite parent, int style, OverviewAlternative input) {
		super(parent, style, input);
		
		this.alternative = input;
		
		new TitleWidget(getHeader(), style, input){
			@Override
			protected void initButtons()
			{
				CLabel lblOpenDiagram = createContextButton("Open diagram", null );
				createSeparator();

				lblOpenDiagram.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						openDiagram();
					}
				});
				super.initButtons();
			}
			
		};
		Composite mainContainer = new Composite(getContainer(), SWT.NONE);
		mainContainer.setLayout(new FillLayout());
		
		new ValidationWidget(getFooter(), style, input);

		{
			SplitComposite splitComposite = new SplitComposite(mainContainer, SWT.VERTICAL);
			
			//Tree view
			this.treeviewComposite = new EMFEditableTreeviewComposite(input, splitComposite, SWT.NONE);
			splitComposite.setTopControl(treeviewComposite);
			
			//Property sheet page
			PropertyPageComposite pageSheet = new PropertyPageComposite(
					splitComposite, SWT.BORDER, treeviewComposite.getPropertySheetPage());
			splitComposite.setBottomControl(pageSheet);
		}
		
		/*
		CTabFolder tabFolder = new CTabFolder(mainContainer, SWT.NONE);
		
		CTabItem diagramTabItem = new CTabItem(tabFolder, SWT.NONE);
		diagramTabItem.setText("Diagram editor");

		CTabItem modelTabItem = new CTabItem(tabFolder, SWT.NONE);
		modelTabItem.setText("Model editor");

		//Diagram editor
		{
			SplitComposite splitComposite = new SplitComposite(tabFolder, SWT.HORIZONTAL);
			
			//Diagram
			OverviewDiagramComposite diagramComposite = new OverviewDiagramComposite(splitComposite, SWT.NONE);
			
        	IFile file = this.alternative.getResource().getFile("overview.sdlo_diagram");
			URI diagramUri = URI.createPlatformResourceURI(file.getFullPath().toString(), false);
			
			DiagramEditorInput editorInput = new DiagramEditorInput(diagramUri, null);
			diagramComposite.setInput(input.getEditingDomain(), editorInput);
			splitComposite.setTopControl(diagramComposite);
			
			//Palette
			Composite paletteComposite = new Composite(splitComposite, SWT.NONE);
			diagramComposite.initializePalette(paletteComposite);
			splitComposite.setBottomControl(paletteComposite);
			
			splitComposite.setSizeRatio(80);
			diagramTabItem.setControl(splitComposite);
		}
		
		//Model editor
		{
			SplitComposite splitComposite = new SplitComposite(tabFolder, SWT.VERTICAL);
			
			//Tree view
			this.treeviewComposite = new EMFEditableTreeviewComposite(input, splitComposite, SWT.NONE);
			splitComposite.setTopControl(treeviewComposite);
			
			//Property sheet page
			PropertyPageComposite pageSheet = new PropertyPageComposite(
					splitComposite, SWT.BORDER, treeviewComposite.getPropertySheetPage());
			splitComposite.setBottomControl(pageSheet);
			
			modelTabItem.setControl(splitComposite);
		}
		
		tabFolder.setSelection(diagramTabItem);
		tabFolder.layout();
		*/
		
	}
	
	@Override
	public void onSelect() {
	}

	private void openDiagram()
	{
        try
        {
        	IFile file = this.alternative.getResource().getFile("overview.sdlo_diagram");
			IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), file);
        }
        catch (PartInitException e)
        {
                e.printStackTrace();
        }
	}

}