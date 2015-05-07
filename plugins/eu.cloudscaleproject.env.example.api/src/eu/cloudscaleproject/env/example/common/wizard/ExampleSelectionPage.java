package eu.cloudscaleproject.env.example.common.wizard;

import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardNode;
import org.eclipse.jface.wizard.WizardSelectionPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import eu.cloudscaleproject.env.example.common.Example;

public class ExampleSelectionPage extends WizardSelectionPage
{

	ExampleWizardNode selectedWizardNode;
	private List<ExampleWizardNode> nodes;
	private TableViewer projectType;
	private Browser browser;

	protected ExampleSelectionPage(List<ExampleWizardNode> nodes)
	{
		super("Example selection");
		setTitle("Example selection");
		setDescription("Choose CloudScale example from list of available examples.");

		this.nodes = nodes;
		
	}

	@Override
	public void createControl(Composite parent)
	{
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		Label lblExampleSelection = new Label(composite, SWT.NONE);
		lblExampleSelection.setFont(SWTResourceManager.getFont("Sans", 10, SWT.NORMAL));
		lblExampleSelection.setText("Example selection");
		
		Label lblDescription = new Label(composite, SWT.NONE);
		lblDescription.setFont(SWTResourceManager.getFont("Sans", 10, SWT.NORMAL));
		lblDescription.setText("Description (README)");

		projectType = new TableViewer(composite);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.widthHint = 200;
		gridData.grabExcessHorizontalSpace = false;
		projectType.getTable().setLayoutData(gridData);
		projectType.setContentProvider(new ArrayContentProvider());
		projectType.setLabelProvider(new LabelProvider()
		{
			@Override
			public String getText(Object element)
			{
				if (element instanceof ExampleWizardNode)
				{
					return ((ExampleWizardNode) element).getExample().getName();
				}
				return super.getText(element);
			}
		});

		projectType.setInput(nodes.toArray(new ExampleWizardNode[0]));
		setControl(composite);

		Composite container = new Composite(composite, SWT.BORDER);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		browser = new Browser(container, SWT.NONE);
		
		projectType.addSelectionChangedListener(new ISelectionChangedListener()
		{
			@Override
			public void selectionChanged(SelectionChangedEvent event)
			{
				updateSelection();
			}
		});
		
		projectType.getTable().select(0);
		updateSelection();
	}
	
	private void updateSelection()
	{
		ISelection selection = projectType.getSelection();
		if (!selection.isEmpty() && selection instanceof IStructuredSelection)
		{
			Object o = ((IStructuredSelection) selection).getFirstElement();
			if (o instanceof ExampleWizardNode)
			{
				selectedWizardNode = (ExampleWizardNode) o;
				browser.setUrl(selectedWizardNode.getExample().getReadme().toExternalForm());
				setSelectedNode(selectedWizardNode);
			}
		}
	}


	static class ExampleWizardNode implements IWizardNode
	{

		private ExampleProjectWizard wizard;

		public ExampleWizardNode(ExampleProjectWizard wizard)
		{
			this.wizard = wizard;
			wizard.addPages();
		}

		public Example getExample()
		{
			return wizard.getExample();
		}


		@Override
		public IWizard getWizard()
		{
			return wizard;
		}

		@Override
		public boolean isContentCreated()
		{
			return true;
		}

		@Override
		public void dispose()
		{

		}

		@Override
		public Point getExtent()
		{
			return new Point(-1, -1);
		}

	}
}