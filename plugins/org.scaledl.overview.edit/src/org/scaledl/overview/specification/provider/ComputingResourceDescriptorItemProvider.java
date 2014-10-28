/**
 */
package org.scaledl.overview.specification.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.scaledl.overview.core.provider.EntityItemProvider;

import org.scaledl.overview.provider.OverviewEditPlugin;

import org.scaledl.overview.specification.ComputingResourceDescriptor;
import org.scaledl.overview.specification.SpecificationPackage;

/**
 * This is the item provider adapter for a {@link org.scaledl.overview.specification.ComputingResourceDescriptor} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ComputingResourceDescriptorItemProvider
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
	public ComputingResourceDescriptorItemProvider(AdapterFactory adapterFactory) {
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
			addEditablePropertyDescriptor(object);
			addMemoryPropertyDescriptor(object);
			addCpuPropertyDescriptor(object);
			addCpuUnitsPropertyDescriptor(object);
			addStoragePropertyDescriptor(object);
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
	 * This adds a property descriptor for the Editable feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEditablePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComputingResourceDescriptor_editable_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ComputingResourceDescriptor_editable_feature", "_UI_ComputingResourceDescriptor_type"),
				 SpecificationPackage.Literals.COMPUTING_RESOURCE_DESCRIPTOR__EDITABLE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Memory feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMemoryPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComputingResourceDescriptor_memory_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ComputingResourceDescriptor_memory_feature", "_UI_ComputingResourceDescriptor_type"),
				 SpecificationPackage.Literals.COMPUTING_RESOURCE_DESCRIPTOR__MEMORY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Cpu feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCpuPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComputingResourceDescriptor_cpu_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ComputingResourceDescriptor_cpu_feature", "_UI_ComputingResourceDescriptor_type"),
				 SpecificationPackage.Literals.COMPUTING_RESOURCE_DESCRIPTOR__CPU,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Cpu Units feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCpuUnitsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComputingResourceDescriptor_cpuUnits_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ComputingResourceDescriptor_cpuUnits_feature", "_UI_ComputingResourceDescriptor_type"),
				 SpecificationPackage.Literals.COMPUTING_RESOURCE_DESCRIPTOR__CPU_UNITS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Storage feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStoragePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComputingResourceDescriptor_storage_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ComputingResourceDescriptor_storage_feature", "_UI_ComputingResourceDescriptor_type"),
				 SpecificationPackage.Literals.COMPUTING_RESOURCE_DESCRIPTOR__STORAGE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns ComputingResourceDescriptor.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ComputingResourceDescriptor"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((ComputingResourceDescriptor)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_ComputingResourceDescriptor_type") :
			getString("_UI_ComputingResourceDescriptor_type") + " " + label;
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

		switch (notification.getFeatureID(ComputingResourceDescriptor.class)) {
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__PROVIDER_ID:
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__EDITABLE:
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__MEMORY:
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__CPU:
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__CPU_UNITS:
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__STORAGE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
