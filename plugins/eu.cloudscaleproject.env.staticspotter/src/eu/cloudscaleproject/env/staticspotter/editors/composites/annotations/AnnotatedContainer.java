package eu.cloudscaleproject.env.staticspotter.editors.composites.annotations;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.reclipse.structure.inference.annotations.ASGAnnotation;


public class AnnotatedContainer
{
   private final AnnotationComposite view;

   private final ASGAnnotation annotation;

   private final EObject element;

   private final List<String> keys;


   public AnnotatedContainer(AnnotationComposite view, ASGAnnotation annotation,
         EObject element)
   {
      this.view = view;
      this.annotation = annotation;
      this.element = element;
      this.keys = new ArrayList<String>();

      // get all keys
      Collection<String> allKeys = annotation.getAnnotatedElements().keySet();
      for (String key : allKeys)
      {
         // get elements
         Collection<EObject> annotatedList = annotation.getAnnotatedElements()
               .get(key);

         // collect keys
         for (EObject annotated : annotatedList)
         {
            if (annotated.equals(element))
            {
               keys.add(key);
            }
         }
      }

      // sort the keys
      Collections.sort(keys);
   }


   public Collection<String> getKeys()
   {
      return keys;
   }


   public EObject getElement()
   {
      return element;
   }


   public Collection<ASGAnnotation> getAnnotatedBy()
   {
      return view.getAnnotatedBy(element, annotation);
   }
}
