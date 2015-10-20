package eu.cloudscaleproject.env.toolchain.ui;


import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.common.IconSetResources;
import eu.cloudscaleproject.env.common.IconSetResources.COLOR;
import eu.cloudscaleproject.env.common.IconSetResources.SIZE;
import eu.cloudscaleproject.env.toolchain.ToolchainExtensions;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IConfigAlternative;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.resources.types.IInputAlternative;
import eu.cloudscaleproject.env.toolchain.ui.dialogs.ShowConfigAlternativesDialog;
import eu.cloudscaleproject.env.toolchain.ui.dialogs.ShowResultAlternativesDialog;
import eu.cloudscaleproject.env.toolchain.ui.widgets.TitleWidget;
import eu.cloudscaleproject.env.toolchain.ui.widgets.ValidationWidget;

public class InputEditorView extends AbstractEditorView
{
	private IInputAlternative alternative;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public InputEditorView(Composite parent, int style, final IInputAlternative input)
	{
		super(parent, style, input);
		
		this.alternative = input;
		
		new TitleWidget(getHeader(), SWT.NONE, input){
			@Override
			protected void initButtons() {
				CLabel lblConfigurations = createContextButton("Configurations", IconSetResources.getImage("gear", COLOR.BLUE, SIZE.SIZE_24));
				CLabel lblResults = createContextButton("Results", IconSetResources.getImage("stats_3", COLOR.BLUE, SIZE.SIZE_24));
				
				lblConfigurations.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						int res = new ShowConfigAlternativesDialog(input.getConfigAlternatives()).open();
						if(res == ShowConfigAlternativesDialog.CREATE_ID)
							createConfigAlternative();
					}
				});

				lblResults.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						List<IEditorInputResource> l = new LinkedList<>();
						for (IConfigAlternative ca : input.getConfigAlternatives())
						{
							l.addAll(ca.getResults());
						}
						new ShowResultAlternativesDialog(l).open();
					}
				});

				createSeparator();
				super.initButtons();
			}
		};
		
		new ValidationWidget(getFooter(), style, input);
	}
	
	private void createConfigAlternative()
	{
		ResourceProvider resourceProvider = ResourceRegistry.getInstance().getResourceProvider(alternative.getProject(), alternative.getConfigAlternativeID());
		IConfigurationElement e = ToolchainExtensions.getInstance().findResourceProviderFactoryElement(alternative.getConfigAlternativeID());

		String wizardCmd = e.getAttribute("wizard");
		if (wizardCmd == null)
		{
			throw new IllegalStateException("Wizard command missing...");
		}
		
		
		IEclipseContext staticContext = EclipseContextFactory.create();
		staticContext.set(ResourceProvider.class, resourceProvider);
		staticContext.set(IEditorInputResource.class, alternative);

		CommandExecutor.getInstance().execute(wizardCmd, staticContext);
	}
}
