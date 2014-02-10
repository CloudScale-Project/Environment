/**
 */
package eu.cloudscaleproject.env.csm.core.provider;


import eu.cloudscaleproject.env.csm.CsmFactory;

import eu.cloudscaleproject.env.csm.architecture.ArchitectureFactory;

import eu.cloudscaleproject.env.csm.core.CoreFactory;
import eu.cloudscaleproject.env.csm.core.CorePackage;

import eu.cloudscaleproject.env.csm.definition.DefinitionFactory;

import eu.cloudscaleproject.env.csm.provider.CsmEditPlugin;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link java.util.Map.Entry} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AEEntryItemProvider
	extends ItemProviderAdapter
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
	public AEEntryItemProvider(AdapterFactory adapterFactory) {
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

			addKeyPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Key feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addKeyPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AEEntry_key_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AEEntry_key_feature", "_UI_AEEntry_type"),
				 CorePackage.Literals.AE_ENTRY__KEY,
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
			childrenFeatures.add(CorePackage.Literals.AE_ENTRY__VALUE);
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
	 * This returns AEEntry.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/AEEntry"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		Map.Entry<?, ?> aeEntry = (Map.Entry<?, ?>)object;
		return "" + aeEntry.getKey() + " -> " + aeEntry.getValue();
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

		switch (notification.getFeatureID(Map.Entry.class)) {
			case CorePackage.AE_ENTRY__KEY:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case CorePackage.AE_ENTRY__VALUE:
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
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 CoreFactory.eINSTANCE.create(CorePackage.Literals.AV_ENTRY)));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 CoreFactory.eINSTANCE.create(CorePackage.Literals.AE_ENTRY)));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 CoreFactory.eINSTANCE.createOperation()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 CoreFactory.eINSTANCE.createOperationInterface()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 CsmFactory.eINSTANCE.createCsm()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 ArchitectureFactory.eINSTANCE.createArchitecture()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 ArchitectureFactory.eINSTANCE.createCloudEnvironment()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 ArchitectureFactory.eINSTANCE.createInfrastructureLayer()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 ArchitectureFactory.eINSTANCE.createPlatformLayer()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 ArchitectureFactory.eINSTANCE.createSoftwareLayer()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 ArchitectureFactory.eINSTANCE.createDeployableRuntimeService()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 ArchitectureFactory.eINSTANCE.createDeployableSupportService()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 ArchitectureFactory.eINSTANCE.createExternalRuntimeService()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 ArchitectureFactory.eINSTANCE.createExternalSupportService()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 ArchitectureFactory.eINSTANCE.createExternalSoftwareService()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 ArchitectureFactory.eINSTANCE.createDeployableSoftwareService()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 ArchitectureFactory.eINSTANCE.createComputingInfrastructureService()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 ArchitectureFactory.eINSTANCE.createInternalConnection()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 ArchitectureFactory.eINSTANCE.createHybridConnection()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 ArchitectureFactory.eINSTANCE.createExternalConnection()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 ArchitectureFactory.eINSTANCE.createServiceProxy()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 ArchitectureFactory.eINSTANCE.createUsageProxy()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 DefinitionFactory.eINSTANCE.createSystemDefinition()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 DefinitionFactory.eINSTANCE.createCloudDefinition()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 DefinitionFactory.eINSTANCE.createSoftwareDefinition()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 DefinitionFactory.eINSTANCE.createDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 DefinitionFactory.eINSTANCE.createCloudEnvironmentDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 DefinitionFactory.eINSTANCE.createRegionDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 DefinitionFactory.eINSTANCE.createAvailabilityZoneDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 DefinitionFactory.eINSTANCE.createInfrastructureServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 DefinitionFactory.eINSTANCE.createNetworkInfrastructureServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 DefinitionFactory.eINSTANCE.createComputingInfrastructureServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 DefinitionFactory.eINSTANCE.createDeployableRuntimeServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 DefinitionFactory.eINSTANCE.createExternalRuntimeServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 DefinitionFactory.eINSTANCE.createDeployableSupportServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(CorePackage.Literals.AE_ENTRY__VALUE,
				 DefinitionFactory.eINSTANCE.createExternalSupportServiceDescriptor()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return CsmEditPlugin.INSTANCE;
	}

}
