/**
 */
package eu.cloudscaleproject.env.csm.definition.provider;


import eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.DefinitionPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ComputingInfrastructureServiceDescriptorItemProvider
	extends InfrastructureServiceDescriptorItemProvider
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
	public ComputingInfrastructureServiceDescriptorItemProvider(AdapterFactory adapterFactory) {
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

			addMemoryPropertyDescriptor(object);
			addCpuPropertyDescriptor(object);
			addCpuUnitsPropertyDescriptor(object);
			addStoragePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
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
				 getString("_UI_ComputingInfrastructureServiceDescriptor_memory_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ComputingInfrastructureServiceDescriptor_memory_feature", "_UI_ComputingInfrastructureServiceDescriptor_type"),
				 DefinitionPackage.Literals.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__MEMORY,
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
				 getString("_UI_ComputingInfrastructureServiceDescriptor_cpu_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ComputingInfrastructureServiceDescriptor_cpu_feature", "_UI_ComputingInfrastructureServiceDescriptor_type"),
				 DefinitionPackage.Literals.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__CPU,
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
				 getString("_UI_ComputingInfrastructureServiceDescriptor_cpuUnits_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ComputingInfrastructureServiceDescriptor_cpuUnits_feature", "_UI_ComputingInfrastructureServiceDescriptor_type"),
				 DefinitionPackage.Literals.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__CPU_UNITS,
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
				 getString("_UI_ComputingInfrastructureServiceDescriptor_storage_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ComputingInfrastructureServiceDescriptor_storage_feature", "_UI_ComputingInfrastructureServiceDescriptor_type"),
				 DefinitionPackage.Literals.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__STORAGE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns ComputingInfrastructureServiceDescriptor.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ComputingInfrastructureServiceDescriptor"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((ComputingInfrastructureServiceDescriptor)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_ComputingInfrastructureServiceDescriptor_type") :
			getString("_UI_ComputingInfrastructureServiceDescriptor_type") + " " + label;
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

		switch (notification.getFeatureID(ComputingInfrastructureServiceDescriptor.class)) {
			case DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__MEMORY:
			case DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__CPU:
			case DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__CPU_UNITS:
			case DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__STORAGE:
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

}
