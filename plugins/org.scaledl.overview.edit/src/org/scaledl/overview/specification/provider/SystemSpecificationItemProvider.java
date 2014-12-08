/**
 */
package org.scaledl.overview.specification.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.scaledl.overview.specification.SpecificationFactory;
import org.scaledl.overview.specification.SpecificationPackage;
import org.scaledl.overview.specification.SystemSpecification;

/**
 * This is the item provider adapter for a {@link org.scaledl.overview.specification.SystemSpecification} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SystemSpecificationItemProvider
	extends SpecificationItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemSpecificationItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(SpecificationPackage.Literals.SYSTEM_SPECIFICATION__DESCRIPTORS);
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
	 * This returns SystemSpecification.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/SystemSpecification"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_SystemSpecification_type");
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

		switch (notification.getFeatureID(SystemSpecification.class)) {
			case SpecificationPackage.SYSTEM_SPECIFICATION__DESCRIPTORS:
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
				(SpecificationPackage.Literals.SYSTEM_SPECIFICATION__DESCRIPTORS,
				 SpecificationFactory.eINSTANCE.createCloudEnvironmentDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(SpecificationPackage.Literals.SYSTEM_SPECIFICATION__DESCRIPTORS,
				 SpecificationFactory.eINSTANCE.createRegionDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(SpecificationPackage.Literals.SYSTEM_SPECIFICATION__DESCRIPTORS,
				 SpecificationFactory.eINSTANCE.createAvailabilityZoneDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(SpecificationPackage.Literals.SYSTEM_SPECIFICATION__DESCRIPTORS,
				 SpecificationFactory.eINSTANCE.createNetworkInfrastructureServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(SpecificationPackage.Literals.SYSTEM_SPECIFICATION__DESCRIPTORS,
				 SpecificationFactory.eINSTANCE.createComputingInfrastructureServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(SpecificationPackage.Literals.SYSTEM_SPECIFICATION__DESCRIPTORS,
				 SpecificationFactory.eINSTANCE.createComputingResourceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(SpecificationPackage.Literals.SYSTEM_SPECIFICATION__DESCRIPTORS,
				 SpecificationFactory.eINSTANCE.createPlatformServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(SpecificationPackage.Literals.SYSTEM_SPECIFICATION__DESCRIPTORS,
				 SpecificationFactory.eINSTANCE.createPlatformRuntimeServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(SpecificationPackage.Literals.SYSTEM_SPECIFICATION__DESCRIPTORS,
				 SpecificationFactory.eINSTANCE.createPlatformSupportServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(SpecificationPackage.Literals.SYSTEM_SPECIFICATION__DESCRIPTORS,
				 SpecificationFactory.eINSTANCE.createProvidedPlatformServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(SpecificationPackage.Literals.SYSTEM_SPECIFICATION__DESCRIPTORS,
				 SpecificationFactory.eINSTANCE.createProvidedPlatformRuntimeServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(SpecificationPackage.Literals.SYSTEM_SPECIFICATION__DESCRIPTORS,
				 SpecificationFactory.eINSTANCE.createProvidedPlatformSupportServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(SpecificationPackage.Literals.SYSTEM_SPECIFICATION__DESCRIPTORS,
				 SpecificationFactory.eINSTANCE.createSoftwareServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(SpecificationPackage.Literals.SYSTEM_SPECIFICATION__DESCRIPTORS,
				 SpecificationFactory.eINSTANCE.createProvidedSoftwareServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(SpecificationPackage.Literals.SYSTEM_SPECIFICATION__DESCRIPTORS,
				 SpecificationFactory.eINSTANCE.createExternalSoftwareServiceDescriptor()));
	}

}
