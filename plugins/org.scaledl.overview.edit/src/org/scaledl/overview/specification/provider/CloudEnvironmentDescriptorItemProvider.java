/**
 */
package org.scaledl.overview.specification.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.scaledl.overview.core.provider.EntityItemProvider;
import org.scaledl.overview.provider.OverviewEditPlugin;
import org.scaledl.overview.specification.CloudEnvironmentDescriptor;
import org.scaledl.overview.specification.SpecificationFactory;
import org.scaledl.overview.specification.SpecificationPackage;

/**
 * This is the item provider adapter for a {@link org.scaledl.overview.specification.CloudEnvironmentDescriptor} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CloudEnvironmentDescriptorItemProvider
	extends EntityItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CloudEnvironmentDescriptorItemProvider(AdapterFactory adapterFactory) {
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

			addProviderIDPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Provider ID feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addProviderIDPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Descriptor_providerID_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Descriptor_providerID_feature", "_UI_Descriptor_type"),
				 SpecificationPackage.Literals.DESCRIPTOR__PROVIDER_ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
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
			childrenFeatures.add(SpecificationPackage.Literals.CLOUD_ENVIRONMENT_DESCRIPTOR__AVAILABILITY_ZONES);
			childrenFeatures.add(SpecificationPackage.Literals.CLOUD_ENVIRONMENT_DESCRIPTOR__REGIONS);
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
	 * This returns CloudEnvironmentDescriptor.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/CloudEnvironmentDescriptor"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((CloudEnvironmentDescriptor)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_CloudEnvironmentDescriptor_type") :
			getString("_UI_CloudEnvironmentDescriptor_type") + " " + label;
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

		switch (notification.getFeatureID(CloudEnvironmentDescriptor.class)) {
			case SpecificationPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__PROVIDER_ID:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case SpecificationPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__AVAILABILITY_ZONES:
			case SpecificationPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__REGIONS:
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
				(SpecificationPackage.Literals.CLOUD_ENVIRONMENT_DESCRIPTOR__AVAILABILITY_ZONES,
				 SpecificationFactory.eINSTANCE.createAvailabilityZoneDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(SpecificationPackage.Literals.CLOUD_ENVIRONMENT_DESCRIPTOR__REGIONS,
				 SpecificationFactory.eINSTANCE.createRegionDescriptor()));
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
