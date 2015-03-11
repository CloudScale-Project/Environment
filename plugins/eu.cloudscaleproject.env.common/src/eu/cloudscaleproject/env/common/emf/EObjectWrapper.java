package eu.cloudscaleproject.env.common.emf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.EqualityHelper;

public class EObjectWrapper
{
	private EObject master;
	private List<? extends EObject> slaves;
	private EqualityHelper equalityHelper = new SpecialEqualityHelper();

	public EObjectWrapper(List<? extends EObject> slaves)
	{
		this(EcoreUtil.copy(slaves.get(0)), slaves);
	}

	public EObjectWrapper(EObject master, List<? extends EObject> slaves)
	{
		this.slaves = slaves;
		this.master = master;

		this.master.eAdapters().add(new EContentAdapter()
		{
			@Override
			public void notifyChanged(Notification notification)
			{
				super.notifyChanged(notification);
				
				// Mind only 1-6 : SET, UNSET, ADD, REMOVE, ADD_ALL, REMOVE_ALL
				if (notification.getEventType() > 6 || notification.getEventType()<1)
					return;

				for (EObject slave : EObjectWrapper.this.slaves)
				{
					if (notification.getNotifier() != EObjectWrapper.this.master)
					{
						if (((EObject)notification.getNotifier()).eContainer() == null) return;

						String relativeURIFragmentPath = EcoreUtil.getRelativeURIFragmentPath(
								EObjectWrapper.this.master,
								(EObject) notification.getNotifier());

						EObject subSlave = EcoreUtil.getEObject(slave, relativeURIFragmentPath);
						pushToSlave(subSlave, notification);
					}
					else
					{
						pushToSlave(slave, notification);
					}

				}
			}
		});
	}

	private void pushToSlave(EObject obj, Notification notification)
	{
		EStructuralFeature feature = (EStructuralFeature) notification.getFeature();
		switch (notification.getEventType())
		{
		case Notification.SET:
			if (feature instanceof EReference && ((EReference) feature).isContainment())
			{
				obj.eSet(feature, EcoreUtil.copy((EObject) notification.getNewValue()));
			}
			else
			{
				obj.eSet(feature, notification.getNewValue());
			}
			break;

		case Notification.UNSET:
			obj.eUnset(feature);
			break;

		case Notification.ADD:
		case Notification.ADD_MANY:
			addToSlave(obj, notification);
			break;

		case Notification.REMOVE:
		case Notification.REMOVE_MANY:
			removeFromSlave(obj, notification);
			break;

		default:
			throw new IllegalStateException();

		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addToSlave(EObject slave, Notification notification)
	{
		EReference eReference = (EReference) notification.getFeature();
		Collection refCollection = (Collection) slave.eGet(eReference);

		List<Object> masterRefs = new ArrayList<>();
		if (notification.getOldValue() instanceof Collection)
			masterRefs.addAll((Collection<? extends Object>) notification.getNewValue());
		else
			masterRefs.add(notification.getNewValue());

		for (Object masterToAdd : masterRefs)
		{
			if (eReference.isContainment())
				refCollection.add(EcoreUtil.copy((EObject) masterToAdd));
			else
				refCollection.add(masterToAdd);
		}
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void removeFromSlave(EObject slave, Notification notification)
	{
		EReference eReference = (EReference) notification.getFeature();
		Collection refCollection = (Collection) slave.eGet(eReference);

		List<Object> masterRefs = new ArrayList<>();
		if (notification.getOldValue() instanceof Collection)
			masterRefs.addAll((Collection<? extends Object>) notification.getOldValue());
		else
			masterRefs.add(notification.getOldValue());

		for (Object masterToRemove : masterRefs)
		{
			for (Object o : new ArrayList(refCollection))
			{
				equalityHelper.clear();
				if (equalityHelper.equals((EObject)masterToRemove, (EObject)o))
				{
					refCollection.remove(o);
					break;
				}
			}
		}
	}

	public EObject getMaster()
	{
		return master;
	}
	
	public List<? extends EObject> getSlaves()
	{
		return slaves;
	}
	
	public void setSlaves(List<? extends EObject> slaves)
	{
		this.slaves = slaves;
	}
	
	public EqualityHelper getEqualityHelper()
	{
		return equalityHelper;
	}
	
	public void setEqualityHelper(EqualityHelper equalityHelper)
	{
		this.equalityHelper = equalityHelper;
	}
	
	
	/**
	 * Ignore PCM id attribute and back/opposite references
	 */
	public static class SpecialEqualityHelper extends EqualityHelper
	{
		private static final long serialVersionUID = 1L;
		protected boolean haveEqualFeature(EObject eObject1, EObject eObject2, EStructuralFeature feature) {
                
                if (feature instanceof EReference)
                {
                        EReference ref = (EReference) feature;
                        if (ref.isContainer()) return true;
                }
                
                if (feature.getName().equals("id")) return true;
                
                boolean res = super.haveEqualFeature(eObject1, eObject2, feature);
                return res;
        };
	}
}
