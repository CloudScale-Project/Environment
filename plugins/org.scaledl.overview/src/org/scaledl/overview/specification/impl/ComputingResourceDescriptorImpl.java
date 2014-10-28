/**
 */
package org.scaledl.overview.specification.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.scaledl.overview.core.impl.EntityImpl;
import org.scaledl.overview.specification.ComputingResourceDescriptor;
import org.scaledl.overview.specification.SpecificationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Computing Resource Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.specification.impl.ComputingResourceDescriptorImpl#getProviderID <em>Provider ID</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.impl.ComputingResourceDescriptorImpl#isEditable <em>Editable</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.impl.ComputingResourceDescriptorImpl#getMemory <em>Memory</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.impl.ComputingResourceDescriptorImpl#getCpu <em>Cpu</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.impl.ComputingResourceDescriptorImpl#getCpuUnits <em>Cpu Units</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.impl.ComputingResourceDescriptorImpl#getStorage <em>Storage</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComputingResourceDescriptorImpl extends EntityImpl implements ComputingResourceDescriptor {
	/**
	 * The default value of the '{@link #getProviderID() <em>Provider ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProviderID()
	 * @generated
	 * @ordered
	 */
	protected static final String PROVIDER_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProviderID() <em>Provider ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProviderID()
	 * @generated
	 * @ordered
	 */
	protected String providerID = PROVIDER_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #isEditable() <em>Editable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEditable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EDITABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEditable() <em>Editable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEditable()
	 * @generated
	 * @ordered
	 */
	protected boolean editable = EDITABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMemory() <em>Memory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMemory()
	 * @generated
	 * @ordered
	 */
	protected static final int MEMORY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMemory() <em>Memory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMemory()
	 * @generated
	 * @ordered
	 */
	protected int memory = MEMORY_EDEFAULT;

	/**
	 * The default value of the '{@link #getCpu() <em>Cpu</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCpu()
	 * @generated
	 * @ordered
	 */
	protected static final int CPU_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCpu() <em>Cpu</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCpu()
	 * @generated
	 * @ordered
	 */
	protected int cpu = CPU_EDEFAULT;

	/**
	 * The default value of the '{@link #getCpuUnits() <em>Cpu Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCpuUnits()
	 * @generated
	 * @ordered
	 */
	protected static final int CPU_UNITS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCpuUnits() <em>Cpu Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCpuUnits()
	 * @generated
	 * @ordered
	 */
	protected int cpuUnits = CPU_UNITS_EDEFAULT;

	/**
	 * The default value of the '{@link #getStorage() <em>Storage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStorage()
	 * @generated
	 * @ordered
	 */
	protected static final int STORAGE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getStorage() <em>Storage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStorage()
	 * @generated
	 * @ordered
	 */
	protected int storage = STORAGE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComputingResourceDescriptorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SpecificationPackage.Literals.COMPUTING_RESOURCE_DESCRIPTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getProviderID() {
		return providerID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProviderID(String newProviderID) {
		String oldProviderID = providerID;
		providerID = newProviderID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__PROVIDER_ID, oldProviderID, providerID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditable(boolean newEditable) {
		boolean oldEditable = editable;
		editable = newEditable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__EDITABLE, oldEditable, editable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMemory() {
		return memory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMemory(int newMemory) {
		int oldMemory = memory;
		memory = newMemory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__MEMORY, oldMemory, memory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCpu() {
		return cpu;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCpu(int newCpu) {
		int oldCpu = cpu;
		cpu = newCpu;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__CPU, oldCpu, cpu));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCpuUnits() {
		return cpuUnits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCpuUnits(int newCpuUnits) {
		int oldCpuUnits = cpuUnits;
		cpuUnits = newCpuUnits;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__CPU_UNITS, oldCpuUnits, cpuUnits));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getStorage() {
		return storage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStorage(int newStorage) {
		int oldStorage = storage;
		storage = newStorage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__STORAGE, oldStorage, storage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__PROVIDER_ID:
				return getProviderID();
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__EDITABLE:
				return isEditable();
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__MEMORY:
				return getMemory();
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__CPU:
				return getCpu();
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__CPU_UNITS:
				return getCpuUnits();
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__STORAGE:
				return getStorage();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__PROVIDER_ID:
				setProviderID((String)newValue);
				return;
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__EDITABLE:
				setEditable((Boolean)newValue);
				return;
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__MEMORY:
				setMemory((Integer)newValue);
				return;
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__CPU:
				setCpu((Integer)newValue);
				return;
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__CPU_UNITS:
				setCpuUnits((Integer)newValue);
				return;
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__STORAGE:
				setStorage((Integer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__PROVIDER_ID:
				setProviderID(PROVIDER_ID_EDEFAULT);
				return;
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__EDITABLE:
				setEditable(EDITABLE_EDEFAULT);
				return;
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__MEMORY:
				setMemory(MEMORY_EDEFAULT);
				return;
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__CPU:
				setCpu(CPU_EDEFAULT);
				return;
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__CPU_UNITS:
				setCpuUnits(CPU_UNITS_EDEFAULT);
				return;
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__STORAGE:
				setStorage(STORAGE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__PROVIDER_ID:
				return PROVIDER_ID_EDEFAULT == null ? providerID != null : !PROVIDER_ID_EDEFAULT.equals(providerID);
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__EDITABLE:
				return editable != EDITABLE_EDEFAULT;
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__MEMORY:
				return memory != MEMORY_EDEFAULT;
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__CPU:
				return cpu != CPU_EDEFAULT;
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__CPU_UNITS:
				return cpuUnits != CPU_UNITS_EDEFAULT;
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR__STORAGE:
				return storage != STORAGE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (providerID: ");
		result.append(providerID);
		result.append(", editable: ");
		result.append(editable);
		result.append(", memory: ");
		result.append(memory);
		result.append(", cpu: ");
		result.append(cpu);
		result.append(", cpuUnits: ");
		result.append(cpuUnits);
		result.append(", storage: ");
		result.append(storage);
		result.append(')');
		return result.toString();
	}

} //ComputingResourceDescriptorImpl
