package eu.cloudscaleproject.env.staticspotter.editors.composites;

import java.util.Collection;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.reclipse.structure.inference.annotations.ASGAnnotation;

import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.staticspotter.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.staticspotter.editors.composites.annotations.AnnotationComposite;
import eu.cloudscaleproject.env.staticspotter.util.Util;
import eu.cloudscaleproject.env.toolchain.ui.TitleEditorView;



public class SingleResultComposite extends TitleEditorView implements IRefreshable{

	private ResultAlternative resultFolder;
	private Collection<ASGAnnotation> annotations;
	private AnnotationComposite annotationComposite;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SingleResultComposite(Composite parent, int style, ResultAlternative rif) {
		super(parent, style, rif);
		setLayout(new GridLayout(1, false));
		
		this.resultFolder = rif;
		
		this.annotationComposite = new AnnotationComposite(getContainer());
		
		init();
	}
	
	private void init ()
	{
		if (this.annotations == null)
		{
			this.annotations = Util.loadAnnotations(this.resultFolder);
			if (this.annotations != null) this.annotationComposite.loadAnnotations(annotations);
		}
	}
	
	@Override
	public void refresh() {
		init(); // Workaround - composite can be created before results are available
	}
}
