package eu.cloudscaleproject.env.extractor.wizard;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.scaledl.overview.Overview;
import org.scaledl.overview.application.ApplicationFactory;
import org.scaledl.overview.application.Operation;
import org.scaledl.overview.application.OperationInterface;
import org.scaledl.overview.architecture.ArchitectureFactory;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.SoftwareService;
import org.scaledl.overview.converter.ConverterService;
import org.scaledl.overview.parametertype.ParametertypeFactory;
import org.scaledl.overview.parametertype.PrimitiveParameter;
import org.scaledl.overview.parametertype.TypeEnum;
import org.scaledl.overview.util.OverviewUtil;

import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.system.System;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.extractor.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.extractor.wizard.util.IWizardPageControll;
import eu.cloudscaleproject.env.extractor.wizard.util.WizardData;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class SelectModelWizardPage extends WizardPage implements
		IWizardPageControll {
	private WizardData data;
	private Text txtRepositoryModelLoc;
	private Text txtSystemModelLoc;
	private org.eclipse.swt.widgets.List resultsList;
	private Button rbResults;
	private Button rbExternal;
	private Composite stackedComposite;
	private Group grpExternal;
	private Group grpResults;
	private ResultAlternative result;

	/**
	 * Create the wizard.
	 */
	public SelectModelWizardPage(WizardData data) {
		super("selectModelPage");
		setTitle("Select PCM model");
		setDescription("System and repository PCM model selection.");

		setPageComplete(false);

		this.data = data;
	}

	/**
	 * Create contents of the wizard.
	 * 
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
		container.setLayout(new GridLayout(1, false));

		Group grpSelectInput = new Group(container, SWT.NONE);
		grpSelectInput.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		grpSelectInput.setText("Select Input");

		rbResults = new Button(grpSelectInput, SWT.RADIO);
		rbResults.setBounds(10, 21, 135, 35);
		rbResults.setText("Extractor results");

		rbExternal = new Button(grpSelectInput, SWT.RADIO);
		rbExternal.setBounds(151, 21, 135, 35);
		rbExternal.setText("External models");

		stackedComposite = new Composite(container, SWT.NONE);
		stackedComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));
		StackLayout sl_stackedComposite = new StackLayout();
		sl_stackedComposite.marginHeight = 10;
		stackedComposite.setLayout(sl_stackedComposite);

		grpExternal = new Group(stackedComposite, SWT.NONE);
		grpExternal.setText("Applicaiton PCM model");
		grpExternal.setLayout(new GridLayout(3, false));

		Label label = new Label(grpExternal, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		label.setText("Repository model:");

		txtRepositoryModelLoc = new Text(grpExternal, SWT.BORDER);
		txtRepositoryModelLoc.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 1, 1));

		Button btnSelectRepositoryModel = new Button(grpExternal, SWT.NONE);
		btnSelectRepositoryModel.setText("Browse...");

		Label label_1 = new Label(grpExternal, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		label_1.setText("System model:");

		txtSystemModelLoc = new Text(grpExternal, SWT.BORDER);
		txtSystemModelLoc.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 1, 1));

		Button btnSelectSystemModel = new Button(grpExternal, SWT.NONE);
		btnSelectSystemModel.setText("Browse...");

		grpResults = new Group(stackedComposite, SWT.NONE);
		grpResults.setText("Extractor results");
		grpResults.setLayout(new GridLayout(1, false));

		resultsList = new org.eclipse.swt.widgets.List(grpResults, SWT.BORDER);
		resultsList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));

		ResourceProvider resourceProvider = ResourceRegistry.getInstance().getResourceProvider(
				this.data.getProject(),
				ToolchainUtils.EXTRACTOR_RES_ID);

		final List<IEditorInputResource> results = resourceProvider.getResources();
		for (IEditorInputResource rpf : results)
		{
			resultsList.add(rpf.getName());
		}
		resultsList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (resultsList.getSelection().length > 0)
					setResult((ResultAlternative)results.get(resultsList.getSelectionIndex()));
					checkComplete();
			};
		});

		btnSelectSystemModel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getShell());
				fd.setFilterExtensions(new String[]{"*.system"});
				fd.open();
				String loc = fd.getFilterPath() + File.separator +fd.getFileName();
				
				txtSystemModelLoc.setText(loc);

				if (txtRepositoryModelLoc.getText().isEmpty())
				{
					String loc2 = fd.getFilterPath() + File.separator +fd.getFileName().replace(".system", ".repository");
					
					if (new File (loc2).exists())
						txtRepositoryModelLoc.setText(loc2);
				}
				java.lang.System.out.println(loc);
				checkComplete();
				
			}
		});
		
		btnSelectRepositoryModel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getShell());
				fd.setFilterExtensions(new String[]{"*.repository"});
				String res = fd.open();
				
				String loc = "";

				if (res != null)
				{
					loc = fd.getFilterPath() + File.separator +fd.getFileName();
				}
				
				txtRepositoryModelLoc.setText(loc);

				if (!loc.isEmpty() && txtSystemModelLoc.getText().isEmpty())
				{
					String loc2 = fd.getFilterPath() + File.separator +fd.getFileName().replace(".repository", ".system");
					
					if (new File (loc2).exists())
						txtSystemModelLoc.setText(loc2);
				}
				java.lang.System.out.println(loc);
				checkComplete();
			}
		});
		

		// resultsList.set
		// SingleResultComposite resultComposite = new
		// SingleResultComposite(this.data.getProject, (String) null, composite,
		// 0);
		// resultComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false,
		// true, 1, 1));

		SelectionListener rbListener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StackLayout sl = (StackLayout) stackedComposite.getLayout();

				if (rbExternal.getSelection()) {
					sl.topControl = grpExternal;
				} else {
					sl.topControl = grpResults;
				}

				checkComplete();
				stackedComposite.layout();
			}
		};

		rbExternal.addSelectionListener(rbListener);
		rbResults.addSelectionListener(rbListener);

		rbResults.setSelection(true);
		StackLayout sl = (StackLayout) stackedComposite.getLayout();
		sl.topControl = grpResults;
		stackedComposite.layout();
	}

	private void setResult(ResultAlternative res) {
		this.result = res;

	}

	private void clearData() {
		this.data.setRepositoryModel(null);
		this.data.setSystemModel(null);
		this.data.setOverviewModel(null);
	}

	@Override
	public void performUpdate() {
		// TODO Auto-generated method stub

	}

	public void performBack() {
		// TODO Auto-generated method stub

	}

	public void performNext() {
		if (this.data.getRepositoryModel() != null
				&& this.data.getSystemModel() != null) {
			java.lang.System.out.println("Skiping complete page procedure.");
			return;
		}

		if (rbExternal.getSelection())
			initExternalModel();
		else
			initResultsModel();

		prepareOverviewModel();
	}

	private void initExternalModel() {
		String repositoryLoc = txtRepositoryModelLoc.getText();
		String systemLoc = txtSystemModelLoc.getText();

		final URI repositoryURI = URI.createFileURI(repositoryLoc);
		final URI systemURI = URI.createFileURI(systemLoc);

		loadExternalModels(repositoryURI, systemURI);
	}

	private void initResultsModel() {
		if (this.resultsList.getSelection().length == 0)
			throw new IllegalStateException();

		IFile repositoryFile = (IFile)this.result.getSubResource(ToolchainUtils.KEY_FILE_REPOSITORY);
		IFile systemFile = (IFile)this.result.getSubResource(ToolchainUtils.KEY_FILE_SYSTEM);

		Resource repositoryResource = (ExplorerProjectPaths.getEmfResource(result.getResourceSet(), repositoryFile));
		Resource systemResource = (ExplorerProjectPaths.getEmfResource(result.getResourceSet(), systemFile));

		EObject rep = repositoryResource.getContents().get(0);
		EObject sys = systemResource.getContents().get(0);

		EcoreUtil.resolve(rep, repositoryResource);
		EcoreUtil.resolve(sys, systemResource);

		this.data.setRepositoryModel((Repository) rep);
		this.data.setSystemModel((System) sys);
	}

	private void loadExternalModels(URI repositoryURI, URI systemURI) {
		ResourceSet resSet = new ResourceSetImpl();
		final Resource systemRes = resSet.createResource(systemURI);
		final Resource repositoryRes = resSet.createResource(repositoryURI);

		repositoryRes.unload();
		systemRes.unload();
		try {
			repositoryRes.load(null);
			systemRes.load(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		EObject sys = systemRes.getContents().get(0);
		EObject rep = repositoryRes.getContents().get(0);

		this.data.setRepositoryModel((Repository) rep);
		this.data.setSystemModel((System) sys);
	}

	private void prepareOverviewModel() {
		Overview overview = OverviewUtil.createOverviewModel();

		CloudEnvironment cloudEnvironment = ArchitectureFactory.eINSTANCE
				.createCloudEnvironment();
		cloudEnvironment.setSoftwareLayer(ArchitectureFactory.eINSTANCE
				.createSoftwareLayer());
		cloudEnvironment.setPlatformLayer(ArchitectureFactory.eINSTANCE
				.createPlatformLayer());
		cloudEnvironment.setInfrastructureLayer(ArchitectureFactory.eINSTANCE
				.createInfrastructureLayer());
		overview.getArchitecture().getCloudEnvironments().add(cloudEnvironment);

		this.data.setOverviewModel(overview);

		SoftwareService ss = ArchitectureFactory.eINSTANCE
				.createSoftwareService();
		CloudEnvironment ce = overview.getArchitecture().getCloudEnvironments()
				.get(0);
		ce.getSoftwareLayer().getServices().add(ss);
		this.data.setSoftwareService(ss);

		List<EObject> toImport = new ArrayList<EObject>();
		toImport.add(this.data.getRepositoryModel());
		toImport.add(this.data.getSystemModel());

		ConverterService.getInstance().addExternalModel(ss, toImport, null);
		addFakeIneterfaces(ss);
		
	}

	private void checkComplete() {
		this.clearData();
		if (rbExternal.getSelection()) {
			if (txtSystemModelLoc.getText().isEmpty()
					|| txtRepositoryModelLoc.getText().isEmpty()) {
				setPageComplete(false);
			} else {
				setPageComplete(true);
			}
		}
		else 
		{
			setPageComplete(resultsList.getSelection().length > 0);
		}
	}
	
	private void addFakeIneterfaces(SoftwareService ss)
	{
		if (ss.getRequiredInterfaces().isEmpty())
		{
			OperationInterface oi = ApplicationFactory.eINSTANCE.createOperationInterface();
			oi.setName("I_Database");
			
			oi.getOperations().add(createFakeOperation("getBook", TypeEnum.STRING, TypeEnum.INT));
			oi.getOperations().add(createFakeOperation("getUser", TypeEnum.STRING, TypeEnum.INT));
			oi.getOperations().add(createFakeOperation("getChart", TypeEnum.STRING, TypeEnum.INT));
			oi.getOperations().add(createFakeOperation("doCheckout", TypeEnum.BOOL, TypeEnum.INT, TypeEnum.STRING, TypeEnum.STRING));
			
			ss.getRequiredInterfaces().add(oi);
		}
		
	}
	
	private Operation createFakeOperation(String name, TypeEnum ret, TypeEnum ... par)
	{
		Operation o = ApplicationFactory.eINSTANCE.createOperation();
		o.setName(name);
		for (TypeEnum tt : par)
		{
			PrimitiveParameter pp = ParametertypeFactory.eINSTANCE.createPrimitiveParameter();
			pp.setType(tt);
			o.getParameters().add(pp);
		}
		

		PrimitiveParameter pp = ParametertypeFactory.eINSTANCE.createPrimitiveParameter();
		pp.setType(ret);
		o.setReturnParameter(pp);
		
		return o;
	}
}
