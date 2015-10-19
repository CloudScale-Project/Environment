package eu.cloudscaleproject.env.toolchain.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.common.IconSetResources;
import eu.cloudscaleproject.env.common.IconSetResources.COLOR;
import eu.cloudscaleproject.env.common.IconSetResources.SIZE;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IResultAlternative;
import eu.cloudscaleproject.env.toolchain.ui.widgets.TitleWidget;

public class ResultEditorView extends AbstractEditorView
{
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ResultEditorView(Composite parent, int style, final IResultAlternative result)
	{
		super(parent, style, result);
		
		new TitleWidget(getHeader(), SWT.NONE, result){
			@Override
			protected void initButtons() {
				CLabel lblUp = createContextButton("Input", IconSetResources.getImage("go_out", COLOR.BLUE, SIZE.SIZE_24));
				CLabel lblConfiguration = createContextButton("Configuration", IconSetResources.getImage("gear", COLOR.BLUE, SIZE.SIZE_24));
				createSeparator();

				lblUp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						ResourceRegistry.getInstance().openResourceEditor(((IResultAlternative)result).getInputAlternative());
					}
				});
				lblConfiguration.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						ResourceRegistry.getInstance().openResourceEditor(((IResultAlternative)result).getConfigAlternative());
					}
				});
				super.initButtons();
			}
		};
		
		getFooter().dispose();
		//new ValidationWidget(getFooter(), style, input);
	}
}
