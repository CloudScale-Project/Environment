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
import org.scaledl.overview.specification.ServiceSpecification;
import org.scaledl.overview.specification.SpecificationFactory;
import org.scaledl.overview.specification.SpecificationPackage;

/**
 * This is the item provider adapter for a {@link org.scaledl.overview.specification.ServiceSpecification} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ServiceSpecificationItemProvider
	extends SpecificationItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceSpecificationItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(SpecificationPackage.Literals.SERVICE_SPECIFICATION__SERVICE_DESCRIPTORS);
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
	 * This returns ServiceSpecification.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ServiceSpecification"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_ServiceSpecification_type");
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

		switch (notification.getFeatureID(ServiceSpecification.class)) {
			case SpecificationPackage.SERVICE_SPECIFICATION__SERVICE_DESCRIPTORS:
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
				(SpecificationPackage.Literals.SERVICE_SPECIFICATION__SERVICE_DESCRIPTORS,
				 SpecificationFactory.eINSTANCE.createPlatformServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(SpecificationPackage.Literals.SERVICE_SPECIFICATION__SERVICE_DESCRIPTORS,
				 SpecificationFactory.eINSTANCE.createPlatformRuntimeServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(SpecificationPackage.Literals.SERVICE_SPECIFICATION__SERVICE_DESCRIPTORS,
				 SpecificationFactory.eINSTANCE.createPlatformSupportServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(SpecificationPackage.Literals.SERVICE_SPECIFICATION__SERVICE_DESCRIPTORS,
				 SpecificationFactory.eINSTANCE.createProvidedPlatformServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(SpecificationPackage.Literals.SERVICE_SPECIFICATION__SERVICE_DESCRIPTORS,
				 SpecificationFactory.eINSTANCE.createProvidedPlatformRuntimeServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(SpecificationPackage.Literals.SERVICE_SPECIFICATION__SERVICE_DESCRIPTORS,
				 SpecificationFactory.eINSTANCE.createProvidedPlatformSupportServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(SpecificationPackage.Literals.SERVICE_SPECIFICATION__SERVICE_DESCRIPTORS,
				 SpecificationFactory.eINSTANCE.createSoftwareServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(SpecificationPackage.Literals.SERVICE_SPECIFICATION__SERVICE_DESCRIPTORS,
				 SpecificationFactory.eINSTANCE.createProvidedSoftwareServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(SpecificationPackage.Literals.SERVICE_SPECIFICATION__SERVICE_DESCRIPTORS,
				 SpecificationFactory.eINSTANCE.createExternalSoftwareServiceDescriptor()));
	}

}
