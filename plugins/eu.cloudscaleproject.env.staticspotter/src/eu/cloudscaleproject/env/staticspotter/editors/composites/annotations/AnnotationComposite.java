package eu.cloudscaleproject.env.staticspotter.editors.composites.annotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.PlatformUI;
import org.reclipse.metamodel.AbstractElementLabeler;
import org.reclipse.metamodel.MetaModel;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.ui.views.annotations.AnnotationsViewerFilter;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.util.ModelHelper;

public class AnnotationComposite extends Composite {

	private Map<PSPatternSpecification, Set<ASGAnnotation>> annotations;

	private Map<EObject, Collection<ASGAnnotation>> annotatedByCache;

	private AbstractElementLabeler labeler;

	private boolean showLongNames;

	private TreeViewer viewer;

	private AnnotationsViewerFilter filter;

	private AnnotationsViewerComparator comparator;

	private AnnotationsTreeLabelProvider labelProvider;

	public AnnotationComposite(Composite parent) {
		super(parent, SWT.BORDER);

		showLongNames = false;
		annotations = new HashMap<PSPatternSpecification, Set<ASGAnnotation>>();
		annotatedByCache = new HashMap<EObject, Collection<ASGAnnotation>>();
		initControl();

	}

	private void addAnnotation(ASGAnnotation annotation) {
		if (labeler == null) {
			MetaModel mm = ModelHelper.getMetaModel(annotation.getPattern());
			labeler = mm.getLabeler();
		}

		// get pattern
		PSPatternSpecification pattern = annotation.getPattern();

		// get existing annotations
		Set<ASGAnnotation> existing = annotations.get(pattern);
		if (existing == null) {
			// create new set
			existing = new HashSet<ASGAnnotation>();
			annotations.put(pattern, existing);
		}

		// add the annotation
		existing.add(annotation);

		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				// fill viewer
				viewer.setInput(annotations);
				viewer.refresh();
			}
		});
	}

	public void initControl() {
		// create providers
		labelProvider = new AnnotationsTreeLabelProvider(this);
		comparator = new AnnotationsViewerComparator(labelProvider);
		filter = new AnnotationsViewerFilter();

		GridLayoutFactory.fillDefaults().applyTo(this);

		// create tree
		Tree tree = new Tree(this, SWT.FULL_SELECTION | SWT.SINGLE);
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(tree);

		// create viewer
		viewer = new TreeViewer(tree);
		viewer.setContentProvider(new AnnotationsTreeContentProvider(this));
		viewer.setFilters(new ViewerFilter[] { filter });
		viewer.setComparator(comparator);
		viewer.setLabelProvider(labelProvider);

		// create columns
		TreeColumn annoColumn = new TreeColumn(tree, SWT.LEAD);
		annoColumn.setText("Annotation");
		annoColumn.setWidth(300);
		annoColumn.setMoveable(true);
		annoColumn.setResizable(true);
		annoColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comparator.setColumn(0);
				int dir = comparator.getDirection();
				viewer.getTree().setSortDirection(dir);
				viewer.refresh();
			}
		});

		TreeColumn ratingColumn = new TreeColumn(tree, SWT.LEAD);
		ratingColumn.setText("Rating");
		ratingColumn.setWidth(52);
		ratingColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comparator.setColumn(1);
				int dir = comparator.getDirection();
				viewer.getTree().setSortDirection(dir);
				viewer.refresh();
			}
		});

		TreeColumn elementsColumn = new TreeColumn(tree, SWT.LEAD);
		elementsColumn.setText("Annotated Elements");
		elementsColumn.setWidth(500);
		elementsColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comparator.setColumn(2);
				int dir = comparator.getDirection();
				viewer.getTree().setSortDirection(dir);
				viewer.refresh();
			}
		});

		// set sort column
		tree.setSortDirection(SWT.UP);
		tree.setSortColumn(annoColumn);
	}

	public Collection<ASGAnnotation> getAnnotatedBy(EObject element, ASGAnnotation hide) {
		// get from cache
		Collection<ASGAnnotation> annotatedBy = annotatedByCache.get(element);

		if (annotatedBy == null) {
			// create list
			annotatedBy = new ArrayList<ASGAnnotation>();

			// go through all patterns
			for (Set<ASGAnnotation> annotationList : annotations.values()) {
				// go through all annotations
				for (ASGAnnotation annotation : annotationList) {
					// filter itself
					if (!hide.equals(annotation)) {
						// go through all annotated keys
						Collection<String> keys = annotation.getAnnotatedElements().keySet();
						for (String key : keys) {
							// get annotated elements
							Collection<EObject> annotatedList = annotation.getAnnotatedElements().get(key);
							for (EObject annotated : annotatedList) {
								if (element.equals(annotated)) {
									annotatedBy.add(annotation);
								}
							}
						}
					}
				}
			}

			// push it into the cache
			annotatedByCache.put(element, annotatedBy);
		}

		return annotatedBy;
	}

	public String getAnnotatedText(ASGAnnotation annotation) {
		// create text
		StringBuilder text = new StringBuilder();

		// get keys
		Collection<String> keys = annotation.getAnnotatedElements().keySet();
		int keyCount = 1;
		int empties = keys.size();
		for (String key : keys) {
			// get elements
			Collection<EObject> annotated = annotation.getAnnotatedElements().get(key);

			// check
			if (annotated != null && !annotated.isEmpty()) {
				text.append(key);
				text.append("="); //$NON-NLS-1$
				int annotatedCount = 1;
				for (EObject element : annotated) {
					text.append(getText(element));
					if (annotatedCount < annotated.size()) {
						text.append("; "); //$NON-NLS-1$
					}
					annotatedCount++;
				}
			} else {
				empties--;
			}

			if (keyCount < empties) {
				text.append(", "); //$NON-NLS-1$
			}
			keyCount++;
		}

		return text.toString();
	}

	public Map<PSPatternSpecification, Set<ASGAnnotation>> getAnnotations() {
		return annotations;
	}

	public Image getImage(EObject element) {
		if (labeler == null) {
			return null;
		}

		return labeler.getImage(element);
	}

	public String getText(AnnotatedContainer annotated) {
		StringBuilder text = new StringBuilder();

		text.append("["); //$NON-NLS-1$
		int keyCount = 1;
		for (String key : annotated.getKeys()) {
			text.append(key);
			if (keyCount < annotated.getKeys().size()) {
				text.append(", "); //$NON-NLS-1$
			}
			keyCount++;
		}
		text.append("] "); //$NON-NLS-1$

		text.append(getText(annotated.getElement()));

		return text.toString();
	}

	private String getText(EObject element) {
		if (labeler == null) {
			return element.toString();
		}

		if (showLongNames) {
			return labeler.getFullText(element);
		} else {
			return labeler.getText(element);
		}
	}

	public boolean isEmpty() {
		return annotations == null || annotations.isEmpty();
	}

	public boolean isVisible(ASGAnnotation element) {
		return false;
	}

	public void loadAnnotations(final Collection<ASGAnnotation> annos) {
		// clear the existing
		annotations.clear();

		// collect the annotations
		for (ASGAnnotation annotation : annos) {
			addAnnotation(annotation);
		}
	}

	private void refresh() {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				if (!viewer.getControl().isDisposed()) {
					viewer.refresh();
				}
			}
		});
	}

	public void setFiltering(int state) {
		switch (state) {
		case 0:
		default:
			filter.showAll();
			break;
		case 1:
			filter.showOnlyAntecendents();
			break;
		case 2:
			filter.showOnlyConsequents();
			break;
		}

		refresh();
	}

	public void setFiltering(Map<String, Double> filtering) {
		// create returning list
		Collection<ASGAnnotation> hiding = new ArrayList<ASGAnnotation>();

		// get annotation lists
		Set<PSPatternSpecification> patterns = annotations.keySet();

		// go through those lists
		for (PSPatternSpecification pattern : patterns) {
			// check name
			if (filtering.containsKey(pattern.getName())) {
				// get filtering value
				double filter = filtering.get(pattern.getName());

				// get annotations
				Set<ASGAnnotation> list = annotations.get(pattern);

				// go through the annotations
				for (ASGAnnotation anno : list) {
					// get the rating
					double rating = anno.getAnnotationRanking();

					if (filter == -1 || rating < filter) {
						hiding.add(anno);
					}
				}
			}
		}

		filter.hideAnnotations(hiding);
		refresh();
	}

	public void toggleNames() {
		showLongNames = !showLongNames;
		refresh();
	}
}
