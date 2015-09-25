package eu.cloudscaleproject.env.staticspotter.editors.composites.annotations;


import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.reclipse.structure.inference.ui.views.annotations.AntecedentContainer;
import org.reclipse.structure.inference.ui.views.annotations.ConsequentContainer;


public class AnnotationsViewerComparator extends ViewerComparator
{
   private static final int DESCENDING = 1;

   private int index;

   private int direction;

   private AnnotationsTreeLabelProvider labelProvider;


   public AnnotationsViewerComparator(AnnotationsTreeLabelProvider labelProvider)
   {
      this.labelProvider = labelProvider;
      this.index = 0;
   }


   @Override
   public int category(Object element)
   {
      if (element instanceof AntecedentContainer)
      {
         return 1;
      }

      if (element instanceof ConsequentContainer)
      {
         return 2;
      }

      return 3;
   }


   @Override
   public int compare(Viewer viewer, Object e1, Object e2)
   {
      // check categories
      int cat1 = category(e1);
      int cat2 = category(e2);

      if (cat1 != cat2)
      {
         return cat1 - cat2;
      }

      // get texts
      String text1 = labelProvider.getColumnText(e1, index);
      String text2 = labelProvider.getColumnText(e2, index);

      // get compare value
      int value = text1.compareTo(text2);

      // flip direction
      if (direction == DESCENDING)
      {
         return -value;
      }
      else
      {
         return value;
      }
   }


   public int getDirection()
   {
      return direction == 1 ? SWT.DOWN : SWT.UP;
   }


   public void setColumn(int column)
   {
      if (column == index)
      {
         // toggle the direction
         direction = 1 - direction;
      }
      else
      {
         // do an ascending sort
         index = column;
         direction = DESCENDING;
      }
   }
}
