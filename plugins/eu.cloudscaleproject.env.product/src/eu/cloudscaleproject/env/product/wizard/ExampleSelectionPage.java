package eu.cloudscaleproject.env.product.wizard;

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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import eu.cloudscaleproject.env.product.wizard.examples.AbstractExampleProjectWizard;

public class ExampleSelectionPage extends WizardSelectionPage
{

	ExampleWizardNode selectedWizardNode;
	private List<ExampleWizardNode> nodes;
	private TableViewer projectType;
	private Label lblDescription;

	protected ExampleSelectionPage(List<ExampleWizardNode> nodes)
	{
		super("Example Selection Page");
		setTitle("Example Selection Page");
		setDescription("Select CloudScale Example.");

		this.nodes = nodes;
	}

	@Override
	public void createControl(Composite parent)
	{
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

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
					return ((ExampleWizardNode) element).getName();
				}
				return super.getText(element);
			}
		});

		projectType.setInput(nodes.toArray(new ExampleWizardNode[0]));
		setControl(composite);
		
		lblDescription = new Label(composite, SWT.WRAP);
		lblDescription.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		lblDescription.setText("Description");

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

				lblDescription.setText(String.format("%s\n\n%s", selectedWizardNode.getName(), selectedWizardNode.getDescription()));
				setSelectedNode(selectedWizardNode);
			}
		}
	}

	static class ExampleWizardNode implements IWizardNode
	{

		private AbstractExampleProjectWizard wizard;

		public ExampleWizardNode(AbstractExampleProjectWizard wizard)
		{
			this.wizard = wizard;
			wizard.addPages();
		}

		public String getName()
		{
			return wizard.getName();
		}

		public String getDescription()
		{
			return wizard.getDescription();
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