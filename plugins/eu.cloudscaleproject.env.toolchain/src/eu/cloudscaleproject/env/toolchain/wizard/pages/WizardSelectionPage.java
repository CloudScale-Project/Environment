package eu.cloudscaleproject.env.toolchain.wizard.pages;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class WizardSelectionPage extends org.eclipse.jface.wizard.WizardSelectionPage{
	
	private List<WizardNode> nodes = new ArrayList<WizardNode>();
	
	private WizardNode selectedWizardNode;
	private TableViewer tableViewer;
	private Label lblDescription;
	
	public WizardSelectionPage(String title, String description, List<WizardNode> nodes) {
		super(title);
		
		setTitle(title);
		setDescription(description);
		
		this.nodes.addAll(nodes);
	}
	
	@Override
	public void createControl(Composite parent)
	{
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));

		tableViewer = new TableViewer(container);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.widthHint = 220;
		gridData.grabExcessHorizontalSpace = false;
		tableViewer.getTable().setLayoutData(gridData);
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setLabelProvider(new LabelProvider()
		{
			@Override
			public String getText(Object element)
			{
				if (element instanceof WizardNode)
				{
					return ((WizardNode) element).getName();
				}
				return super.getText(element);
			}
		});

		tableViewer.setInput(nodes.toArray(new WizardNode[0]));
		setControl(container);
		
		lblDescription = new Label(container, SWT.WRAP);
		lblDescription.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		lblDescription.setText("Description");

		tableViewer.addSelectionChangedListener(new ISelectionChangedListener()
		{
			@Override
			public void selectionChanged(SelectionChangedEvent event)
			{
				updateSelection();
			}
		});
		
		tableViewer.getTable().select(0);
		updateSelection();
		
		setControl(container);
	}

	private void updateSelection()
	{
		ISelection selection = tableViewer.getSelection();
		if (!selection.isEmpty() && selection instanceof IStructuredSelection)
		{
			Object o = ((IStructuredSelection) selection).getFirstElement();
			if (o instanceof WizardNode)
			{
				selectedWizardNode = (WizardNode) o;

				lblDescription.setText(String.format("%s\n\n%s", selectedWizardNode.getName(), selectedWizardNode.getDescription()));
				setSelectedNode(selectedWizardNode);
			}
		}
	}

}
