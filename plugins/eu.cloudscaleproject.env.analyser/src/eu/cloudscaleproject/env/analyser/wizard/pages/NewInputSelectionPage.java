package eu.cloudscaleproject.env.analyser.wizard.pages;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardSelectionPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import eu.cloudscaleproject.env.analyser.wizard.CreateEmptyInputAltWizard;
import eu.cloudscaleproject.env.analyser.wizard.CreateImportInputAltWizard;
import eu.cloudscaleproject.env.analyser.wizard.util.AnalyserWizardNode;

public class NewInputSelectionPage extends WizardSelectionPage{
	
	private List<AnalyserWizardNode> nodes = new ArrayList<AnalyserWizardNode>();
	
	private AnalyserWizardNode selectedWizardNode;
	private TableViewer tableViewer;
	private Label lblDescription;

	public NewInputSelectionPage(IProject project)
	{
		super("New input alternative selection page");
		setTitle("New input alternative selection page");
		setDescription("Create new input alternative");

		nodes.add(new CreateEmptyInputAltNode(project));
		nodes.add(new CreateImportInputAltNode(project));
	}

	@Override
	public void createControl(Composite parent)
	{
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));

		tableViewer = new TableViewer(container);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.widthHint = 200;
		gridData.grabExcessHorizontalSpace = false;
		tableViewer.getTable().setLayoutData(gridData);
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setLabelProvider(new LabelProvider()
		{
			@Override
			public String getText(Object element)
			{
				if (element instanceof AnalyserWizardNode)
				{
					return ((AnalyserWizardNode) element).getName();
				}
				return super.getText(element);
			}
		});

		tableViewer.setInput(nodes.toArray(new AnalyserWizardNode[0]));
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
			if (o instanceof AnalyserWizardNode)
			{
				selectedWizardNode = (AnalyserWizardNode) o;

				lblDescription.setText(String.format("%s\n\n%s", selectedWizardNode.getName(), selectedWizardNode.getDescription()));
				setSelectedNode(selectedWizardNode);
			}
		}
	}

	private static class CreateEmptyInputAltNode extends AnalyserWizardNode
	{
		private final IProject project;
		

		public CreateEmptyInputAltNode(IProject project)
		{
			this.project = project;
		}

		@Override
		public IWizard createWizard() {
			return new CreateEmptyInputAltWizard(project);
		}

		@Override
		public String getName() {
			return "Empty input alternative";
		}

		@Override
		public String getDescription() {
			return "Create new input alternative with empty PCM models.";
		}

	}
	
	private static class CreateImportInputAltNode extends AnalyserWizardNode
	{
		private final IProject project;
		

		public CreateImportInputAltNode(IProject project)
		{
			this.project = project;
		}

		@Override
		public IWizard createWizard() {
			return new CreateImportInputAltWizard(project);
		}

		@Override
		public String getName() {
			return "Create from existing PCM";
		}

		@Override
		public String getDescription() {
			return "Creates new input alternative from existing PCM model";
		}

	}

}
