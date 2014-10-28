/**
 */
package org.scaledl.overview.architecture.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.scaledl.overview.architecture.ArchitectureFactory;
import org.scaledl.overview.architecture.ArchitecturePackage;
import org.scaledl.overview.architecture.CloudEnvironment;

import org.scaledl.overview.core.provider.EntityItemProvider;

import org.scaledl.overview.provider.OverviewEditPlugin;

/**
 * This is the item provider adapter for a {@link org.scaledl.overview.architecture.CloudEnvironment} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CloudEnvironmentItemProvider
	extends EntityItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CloudEnvironmentItemProvider(AdapterFactory adapterFactory) {
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

			addCloudEnvironmentDescriptorPropertyDescriptor(object);
			addAvailabilityZoneDescriptorPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Cloud Environment Descriptor feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCloudEnvironmentDescriptorPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CloudEnvironment_cloudEnvironmentDescriptor_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CloudEnvironment_cloudEnvironmentDescriptor_feature", "_UI_CloudEnvironment_type"),
				 ArchitecturePackage.Literals.CLOUD_ENVIRONMENT__CLOUD_ENVIRONMENT_DESCRIPTOR,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Availability Zone Descriptor feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAvailabilityZoneDescriptorPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CloudEnvironment_availabilityZoneDescriptor_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CloudEnvironment_availabilityZoneDescriptor_feature", "_UI_CloudEnvironment_type"),
				 ArchitecturePackage.Literals.CLOUD_ENVIRONMENT__AVAILABILITY_ZONE_DESCRIPTOR,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
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
			childrenFeatures.add(ArchitecturePackage.Literals.CLOUD_ENVIRONMENT__INTERNAL_CONNECTIONS);
			childrenFeatures.add(ArchitecturePackage.Literals.CLOUD_ENVIRONMENT__INFRASTRUCTURE_LAYER);
			childrenFeatures.add(ArchitecturePackage.Literals.CLOUD_ENVIRONMENT__PLATFORM_LAYER);
			childrenFeatures.add(ArchitecturePackage.Literals.CLOUD_ENVIRONMENT__SOFTWARE_LAYER);
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
	 * This returns CloudEnvironment.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/CloudEnvironment"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((CloudEnvironment)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_CloudEnvironment_type") :
			getString("_UI_CloudEnvironment_type") + " " + label;
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

		switch (notification.getFeatureID(CloudEnvironment.class)) {
			case ArchitecturePackage.CLOUD_ENVIRONMENT__INTERNAL_CONNECTIONS:
			case ArchitecturePackage.CLOUD_ENVIRONMENT__INFRASTRUCTURE_LAYER:
			case ArchitecturePackage.CLOUD_ENVIRONMENT__PLATFORM_LAYER:
			case ArchitecturePackage.CLOUD_ENVIRONMENT__SOFTWARE_LAYER:
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
				(ArchitecturePackage.Literals.CLOUD_ENVIRONMENT__INTERNAL_CONNECTIONS,
				 ArchitectureFactory.eINSTANCE.createInternalConnection()));

		newChildDescriptors.add
			(createChildParameter
				(ArchitecturePackage.Literals.CLOUD_ENVIRONMENT__INFRASTRUCTURE_LAYER,
				 ArchitectureFactory.eINSTANCE.createInfrastructureLayer()));

		newChildDescriptors.add
			(createChildParameter
				(ArchitecturePackage.Literals.CLOUD_ENVIRONMENT__PLATFORM_LAYER,
				 ArchitectureFactory.eINSTANCE.createPlatformLayer()));

		newChildDescriptors.add
			(createChildParameter
				(ArchitecturePackage.Literals.CLOUD_ENVIRONMENT__SOFTWARE_LAYER,
				 ArchitectureFactory.eINSTANCE.createSoftwareLayer()));
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
