package eu.cloudscaleproject.env.staticspotter.editors.composites.annotations;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.ui.views.annotations.AntecedentContainer;
import org.reclipse.structure.inference.ui.views.annotations.ConsequentContainer;
import org.reclipse.structure.specification.PSPatternSpecification;


public class AnnotationsTreeContentProvider implements ITreeContentProvider
{

   private AnnotationComposite view;


   public AnnotationsTreeContentProvider(AnnotationComposite view)
   {
      this.view = view;
   }


   @Override
   public void dispose()
   {
      // nothing
   }


   @Override
   public Object[] getChildren(Object element)
   {
      if (element instanceof PSPatternSpecification)
      {
         // get pattern
         PSPatternSpecification pattern = (PSPatternSpecification) element;

         // get annotations
         Set<ASGAnnotation> annotations = view.getAnnotations().get(pattern);

         return annotations.toArray(new Object[annotations.size()]);
      }

      if (element instanceof ASGAnnotation)
      {
         // get annotation
         ASGAnnotation annotation = (ASGAnnotation) element;

         // create children
         List<Object> children = new ArrayList<Object>();

         // add antecedents
         if (!annotation.getAntecedentAnnos().isEmpty())
         {
            children.add(new AntecedentContainer(annotation));
         }

         // add consequents
         if (!annotation.getConsequentAnnos().isEmpty())
         {
            children.add(new ConsequentContainer(annotation));
         }

         // add annotated
         Set<String> keys = annotation.getAnnotatedElements().keySet();

         for (String key : keys)
         {
            // get list
            List<EObject> elements = annotation.getAnnotatedElements().get(key);

            if (elements != null && !elements.isEmpty())
            {
               for (EObject eObject : elements)
               {
                  children
                        .add(new AnnotatedContainer(view, annotation, eObject));
               }
            }
         }

         return children.toArray(new Object[children.size()]);
      }

      if (element instanceof AntecedentContainer)
      {
         // get children
         Collection<ASGAnnotation> children = ((AntecedentContainer) element)
               .getChildren();

         return children.toArray(new Object[children.size()]);
      }

      if (element instanceof ConsequentContainer)
      {
         // get children
         Collection<ASGAnnotation> children = ((ConsequentContainer) element)
               .getChildren();

         return children.toArray(new Object[children.size()]);
      }

      if (element instanceof AnnotatedContainer)
      {
         Collection<ASGAnnotation> children = ((AnnotatedContainer) element)
               .getAnnotatedBy();

         return children.toArray(new Object[children.size()]);
      }

      return new Object[0];
   }


   @Override
   public Object[] getElements(Object element)
   {
      if (element instanceof Map<?, ?>)
      {
         // get map
         Map<?, ?> map = (Map<?, ?>) element;

         // get keys
         Set<?> keys = map.keySet();

         // return them
         return keys.toArray(new Object[keys.size()]);
      }

      return new Object[0];
   }


   @Override
   public Object getParent(Object element)
   {
      return null;
   }


   @Override
   public boolean hasChildren(Object element)
   {
      return getChildren(element) != null && getChildren(element).length > 0;
   }


   @Override
   public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
   {
      // nothing
   }
}
