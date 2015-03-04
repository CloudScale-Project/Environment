package eu.cloudscaleproject.env.staticspotter.editors.composites;

import java.util.Collection;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.ui.views.annotations.AnnotationView;

import eu.cloudscaleproject.env.common.ui.TitleComposite;
import eu.cloudscaleproject.env.staticspotter.ResultPersistenceFolder;
import eu.cloudscaleproject.env.staticspotter.util.Util;



public class SingleResultComposite extends TitleComposite {

	private ResultPersistenceFolder resultFolder;
	private AnnotationView annotationView;
	private Collection<ASGAnnotation> annotations;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SingleResultComposite(Composite parent, int style, ResultPersistenceFolder rif) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		this.resultFolder = rif;
		
		init();
		initAnnotationView();
	}
	
	private void init()
	{
		setTitle(this.resultFolder.getName());

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
				annotationView.createPartControl(getContainer());
				annotationView.loadAnnotations(annotations);
				getContainer().layout();
			}

		});
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		resultFolder.load();
		init();
		super.update();
	}
}
