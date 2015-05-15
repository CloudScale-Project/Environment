package eu.cloudscaleproject.env.staticspotter.editors.composites;

import java.util.Collection;

import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.ui.views.annotations.AnnotationView;

import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.staticspotter.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.staticspotter.util.Util;
import eu.cloudscaleproject.env.toolchain.ui.TitleComposite;



public class SingleResultComposite extends TitleComposite implements IRefreshable{

	private ResultAlternative resultFolder;
	private AnnotationView annotationView;
	private Collection<ASGAnnotation> annotations;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SingleResultComposite(Composite parent, int style, ResultAlternative rif) {
		super(parent, style, rif);
		setLayout(new GridLayout(1, false));
		
		this.resultFolder = rif;
		
		init();
		initAnnotationView();
	}
	
	private void init()
	{
			this.annotations = Util.loadAnnotations(this.resultFolder);
			if (annotationView != null)
                annotationView.loadAnnotations(annotations);
	}

	private void initAnnotationView()
	{
		this.annotationView = new AnnotationView()
		{
			@Override
			public IWorkbenchPartSite getSite()
			{
				return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getSite();

			}
		};

		Display.getDefault().asyncExec(new Runnable()
		{
			@Override
			public void run()
			{
				StackLayout stackLayout = new StackLayout();
				getContainer().setLayout(stackLayout);
				annotationView.createPartControl(getContainer());

				Control annotationTable = ((Composite)getContainer().getChildren()[0]).getChildren()[0];
				annotationTable.setParent(getContainer());
				
				stackLayout.topControl = annotationTable;
				getContainer().layout();
			}

		});
	}
	
	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		//resultFolder.load();
		init();
	}
}
