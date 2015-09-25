package eu.cloudscaleproject.env.staticspotter.editors.composites.annotations;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.ui.views.annotations.AntecedentContainer;
import org.reclipse.structure.inference.ui.views.annotations.ConsequentContainer;
import org.reclipse.structure.specification.PSPatternSpecification;

public class AnnotationsTreeLabelProvider extends LabelProvider implements ITableLabelProvider
{
	private AnnotationComposite view;

	public AnnotationsTreeLabelProvider(AnnotationComposite view)
	{
		this.view = view;
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @Override public Image getColumnImage(Object element, int index) { if
	 * (index == 0) { if (element instanceof PSPatternSpecification) { return
	 * InferenceUIPlugin.getInstance().getImage(
	 * InferenceConstants.IMG_ANNOTATION_COLLECTION); }
	 * 
	 * if (element instanceof ASGAnnotation) { if
	 * (view.isVisible((ASGAnnotation) element)) { return
	 * InferenceUIPlugin.getInstance().getImage(
	 * InferenceConstants.IMG_ANNOTATION_VISIBLE); } else { return
	 * InferenceUIPlugin.getInstance().getImage(
	 * InferenceConstants.IMG_ANNOTATION_INVISIBLE); } }
	 * 
	 * if (element instanceof AntecedentContainer) { return
	 * InferenceUIPlugin.getInstance().getImage(
	 * InferenceConstants.IMG_ANTECEDENT_COLLECTION); }
	 * 
	 * if (element instanceof ConsequentContainer) { return
	 * InferenceUIPlugin.getInstance().getImage(
	 * InferenceConstants.IMG_CONSEQUENT_COLLECTION); }
	 * 
	 * if (element instanceof AnnotatedContainer) { return
	 * view.getImage(((AnnotatedContainer) element).getElement()); } }
	 * 
	 * return null; }
	 */

	@Override
	public String getColumnText(Object element, int index)
	{
		switch (index)
		{
		case 0:
			return getText(element);
		case 1:
			if (element instanceof ASGAnnotation)
			{
				double rank = ((ASGAnnotation) element).getAnnotationRanking();
				return String.format("%1$.2f", rank) + "%"; //$NON-NLS-1$ //$NON-NLS-2$
			}
		case 2:
			if (element instanceof ASGAnnotation)
			{
				return view.getAnnotatedText((ASGAnnotation) element);
			}

		default:
			return ""; //$NON-NLS-1$
		}
	}

	@Override
	public String getText(Object element)
	{
		if (element instanceof PSPatternSpecification)
		{
			// get pattern
			PSPatternSpecification pattern = (PSPatternSpecification) element;

			// get count
			int count = view.getAnnotations().get(pattern).size();

			// create string
			StringBuilder text = new StringBuilder();

			text.append(pattern.getName());
			text.append(" ("); //$NON-NLS-1$
			text.append(count);
			text.append(" "); //$NON-NLS-1$
			if (count == 1)
			{
				text.append("annotation");
			} else
			{
				text.append("annotations");
			}
			text.append(")"); //$NON-NLS-1$

			return text.toString();
		}

		if (element instanceof ASGAnnotation)
		{
			return ((ASGAnnotation) element).getPattern().getName();
		}

		if (element instanceof AntecedentContainer)
		{
			return "Antecedent Annotations";
		}

		if (element instanceof ConsequentContainer)
		{
			return "Consequent Annotations";
		}

		if (element instanceof AnnotatedContainer)
		{
			return view.getText((AnnotatedContainer) element);
		}

		return getText(element);
	}

}