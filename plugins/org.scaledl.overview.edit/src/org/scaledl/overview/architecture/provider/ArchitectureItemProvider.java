/**
 */
package org.scaledl.overview.architecture.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.scaledl.overview.application.ApplicationFactory;
import org.scaledl.overview.architecture.Architecture;
import org.scaledl.overview.architecture.ArchitectureFactory;
import org.scaledl.overview.architecture.ArchitecturePackage;
import org.scaledl.overview.core.provider.EntityItemProvider;
import org.scaledl.overview.provider.OverviewEditPlugin;

/**
 * This is the item provider adapter for a {@link org.scaledl.overview.architecture.Architecture} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ArchitectureItemProvider
	extends EntityItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArchitectureItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ArchitecturePackage.Literals.ARCHITECTURE__CLOUD_ENVIRONMENTS);
			childrenFeatures.add(ArchitecturePackage.Literals.ARCHITECTURE__PROXIES);
			childrenFeatures.add(ArchitecturePackage.Literals.ARCHITECTURE__USAGE_CONNECTIONS);
			childrenFeatures.add(ArchitecturePackage.Literals.ARCHITECTURE__EXTERNAL_CONNECTIONS);
			childrenFeatures.add(ArchitecturePackage.Literals.ARCHITECTURE__UNRESOLVED_OPERATION_INTERFACES);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Architecture.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Architecture"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Architecture)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Architecture_type") :
			getString("_UI_Architecture_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Architecture.class)) {
			case ArchitecturePackage.ARCHITECTURE__CLOUD_ENVIRONMENTS:
			case ArchitecturePackage.ARCHITECTURE__PROXIES:
			case ArchitecturePackage.ARCHITECTURE__USAGE_CONNECTIONS:
			case ArchitecturePackage.ARCHITECTURE__EXTERNAL_CONNECTIONS:
			case ArchitecturePackage.ARCHITECTURE__UNRESOLVED_OPERATION_INTERFACES:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(ArchitecturePackage.Literals.ARCHITECTURE__CLOUD_ENVIRONMENTS,
				 ArchitectureFactory.eINSTANCE.createCloudEnvironment()));

		newChildDescriptors.add
			(createChildParameter
				(ArchitecturePackage.Literals.ARCHITECTURE__PROXIES,
				 ArchitectureFactory.eINSTANCE.createServiceProxy()));

		newChildDescriptors.add
			(createChildParameter
				(ArchitecturePackage.Literals.ARCHITECTURE__PROXIES,
				 ArchitectureFactory.eINSTANCE.createUsageProxy()));

		newChildDescriptors.add
			(createChildParameter
				(ArchitecturePackage.Literals.ARCHITECTURE__USAGE_CONNECTIONS,
				 ArchitectureFactory.eINSTANCE.createExternalConnection()));

		newChildDescriptors.add
			(createChildParameter
				(ArchitecturePackage.Literals.ARCHITECTURE__EXTERNAL_CONNECTIONS,
				 ArchitectureFactory.eINSTANCE.createExternalConnection()));

		newChildDescriptors.add
			(createChildParameter
				(ArchitecturePackage.Literals.ARCHITECTURE__UNRESOLVED_OPERATION_INTERFACES,
				 ApplicationFactory.eINSTANCE.createOperationInterface()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == ArchitecturePackage.Literals.ARCHITECTURE__USAGE_CONNECTIONS ||
			childFeature == ArchitecturePackage.Literals.ARCHITECTURE__EXTERNAL_CONNECTIONS;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return OverviewEditPlugin.INSTANCE;
	}

}
