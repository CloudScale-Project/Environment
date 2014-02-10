/**
 */
package eu.cloudscaleproject.env.csm.definition.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.DefinitionPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Computing Infrastructure Service Descriptor</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.definition.impl.ComputingInfrastructureServiceDescriptorImpl#getMemory <em>Memory</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.definition.impl.ComputingInfrastructureServiceDescriptorImpl#getCpu <em>Cpu</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.definition.impl.ComputingInfrastructureServiceDescriptorImpl#getCpuUnits <em>Cpu Units</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.definition.impl.ComputingInfrastructureServiceDescriptorImpl#getStorage <em>Storage</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComputingInfrastructureServiceDescriptorImpl extends
		InfrastructureServiceDescriptorImpl implements
		ComputingInfrastructureServiceDescriptor {
	/**
	 * The default value of the '{@link #getMemory() <em>Memory</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getMemory()
	 * @generated
	 * @ordered
	 */
	protected static final int MEMORY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMemory() <em>Memory</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getMemory()
	 * @generated
	 * @ordered
	 */
	protected int memory = MEMORY_EDEFAULT;

	/**
	 * The default value of the '{@link #getCpu() <em>Cpu</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getCpu()
	 * @generated
	 * @ordered
	 */
	protected static final int CPU_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCpu() <em>Cpu</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getCpu()
	 * @generated
	 * @ordered
	 */
	protected int cpu = CPU_EDEFAULT;

	/**
	 * The default value of the '{@link #getCpuUnits() <em>Cpu Units</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getCpuUnits()
	 * @generated
	 * @ordered
	 */
	protected static final int CPU_UNITS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCpuUnits() <em>Cpu Units</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getCpuUnits()
	 * @generated
	 * @ordered
	 */
	protected int cpuUnits = CPU_UNITS_EDEFAULT;

	/**
	 * The default value of the '{@link #getStorage() <em>Storage</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getStorage()
	 * @generated
	 * @ordered
	 */
	protected static final int STORAGE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getStorage() <em>Storage</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getStorage()
	 * @generated
	 * @ordered
	 */
	protected int storage = STORAGE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ComputingInfrastructureServiceDescriptorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DefinitionPackage.Literals.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getMemory() {
		return memory;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setMemory(int newMemory) {
		int oldMemory = memory;
		memory = newMemory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__MEMORY, oldMemory, memory));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getCpu() {
		return cpu;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setCpu(int newCpu) {
		int oldCpu = cpu;
		cpu = newCpu;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__CPU, oldCpu, cpu));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getCpuUnits() {
		return cpuUnits;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setCpuUnits(int newCpuUnits) {
		int oldCpuUnits = cpuUnits;
		cpuUnits = newCpuUnits;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__CPU_UNITS, oldCpuUnits, cpuUnits));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getStorage() {
		return storage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setStorage(int newStorage) {
		int oldStorage = storage;
		storage = newStorage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__STORAGE, oldStorage, storage));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__MEMORY:
				return getMemory();
			case DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__CPU:
				return getCpu();
			case DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__CPU_UNITS:
				return getCpuUnits();
			case DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__STORAGE:
				return getStorage();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__MEMORY:
				setMemory((Integer)newValue);
				return;
			case DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__CPU:
				setCpu((Integer)newValue);
				return;
			case DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__CPU_UNITS:
				setCpuUnits((Integer)newValue);
				return;
			case DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__STORAGE:
				setStorage((Integer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__MEMORY:
				setMemory(MEMORY_EDEFAULT);
				return;
			case DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__CPU:
				setCpu(CPU_EDEFAULT);
				return;
			case DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__CPU_UNITS:
				setCpuUnits(CPU_UNITS_EDEFAULT);
				return;
			case DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__STORAGE:
				setStorage(STORAGE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__MEMORY:
				return memory != MEMORY_EDEFAULT;
			case DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__CPU:
				return cpu != CPU_EDEFAULT;
			case DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__CPU_UNITS:
				return cpuUnits != CPU_UNITS_EDEFAULT;
			case DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__STORAGE:
				return storage != STORAGE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (memory: ");
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

} // ComputingInfrastructureServiceDescriptorImpl
