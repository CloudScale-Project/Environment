package eu.cloudscaleproject.env.example.common.wizard;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import eu.cloudscaleproject.env.example.common.Example;
import eu.cloudscaleproject.env.example.common.ExampleService;
import eu.cloudscaleproject.env.example.common.Example.Resource;

public class ResourceSelectionPage extends WizardPage
{
	private Table table;
	private Example example;
	private TableViewer tableViewer;
	private Browser browser;
	private Label lblResrouceSelection;
	private Label lblDescriptionreadme;

	/**
	 * Create the wizard.
	 */
	public ResourceSelectionPage(Example example)
	{
		super("wizardPage");

		this.example = example;

		setTitle("Example - " + example.getName());
		setDescription("Chose example sub projects to import into the workspace.");
	}

	/**
	 * Create contents of the wizard.
	 * 
	 * @param parent
	 */
	public void createControl(Composite parent)
	{
		Composite composite = new Composite(parent, SWT.NULL);

		setControl(composite);
		composite.setLayout(new GridLayout(2, false));

		lblResrouceSelection = new Label(composite, SWT.NONE);
		lblResrouceSelection.setFont(SWTResourceManager.getFont("Sans", 10, SWT.NORMAL));
		lblResrouceSelection.setText("Project selection");

		lblDescriptionreadme = new Label(composite, SWT.NONE);
		lblDescriptionreadme.setFont(SWTResourceManager.getFont("Sans", 10, SWT.NORMAL));
		lblDescriptionreadme.setText("Description (README)");

		tableViewer = new TableViewer(composite, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_table.widthHint = 200;
		table.setLayoutData(gd_table);

		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setLabelProvider(new LabelProvider()
		{
			@Override
			public String getText(Object element)
			{
				if (element instanceof Example.Resource)
				{
					return ((Example.Resource) element).getName();
				}
				return super.getText(element);
			}
		});

		tableViewer.setInput(example.getResoruces().toArray(new Example.Resource[0]));

		Composite container = new Composite(composite, SWT.BORDER);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		browser = new Browser(container, SWT.NONE);


		// Prevent un-check, if TableItem is grayed ==> Example project already imported
		tableViewer.getTable().addListener(SWT.Selection, new Listener()
		{
			public void handleEvent(Event event)
			{
				if (event.detail == SWT.CHECK)
				{
					if (((TableItem)event.item).getGrayed())
					{
						((TableItem)event.item).setChecked(true);
					}
					
					checkComplete();
				}
			}
		});

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

		checkResourceProjects();
		checkComplete();
	}
	
	private void checkResourceProjects()
	{
		for (int i = 0; i < table.getItemCount(); ++i)
		{
			Resource resource = example.getResoruces().get(i);
			TableItem item = table.getItem(i);


			IProject project = ExampleService.getInstance().getProject(resource);
			if (project != null)
			{
				item.setGrayed(true);
				item.setChecked(true);
			} else
			{
				switch (resource.getType()){
					case ENVIRONMENT: 
					case SOURCE: item.setChecked(true);
					default: break;
				}
			}
		}
	}

	private void updateSelection()
	{
		ISelection selection = tableViewer.getSelection();
		if (!selection.isEmpty() && selection instanceof IStructuredSelection)
		{
			Example.Resource r = (Example.Resource) ((IStructuredSelection) selection).getFirstElement();
			browser.setUrl(r.getReadme().toExternalForm());
		}
	}

	public List<Resource> getSelectedResources()
	{
		LinkedList<Resource> l = new LinkedList<>();
		for (int i = 0; i < table.getItemCount(); ++i)
		{
			Resource resource = example.getResoruces().get(i);
			TableItem item = table.getItem(i);
			if (item.getChecked() && !item.getGrayed())
			{
				l.add(resource);
			}
		}

		return l;
	}

	private void checkComplete() {
		setPageComplete(!getSelectedResources().isEmpty());
	}
}
