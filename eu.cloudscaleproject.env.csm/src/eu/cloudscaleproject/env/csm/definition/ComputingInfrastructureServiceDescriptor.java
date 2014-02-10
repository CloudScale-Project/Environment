/**
 */
package eu.cloudscaleproject.env.csm.definition;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Computing Infrastructure Service Descriptor</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The InstanceDescriptor specify hardware properties of the instances in IaaS cloud environment.  Currently following properties are defined: cpu, cpuUnits, memory and storage. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor#getMemory <em>Memory</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor#getCpu <em>Cpu</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor#getCpuUnits <em>Cpu Units</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor#getStorage <em>Storage</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.definition.DefinitionPackage#getComputingInfrastructureServiceDescriptor()
 * @model
 * @generated
 */
public interface ComputingInfrastructureServiceDescriptor extends
		InfrastructureServiceDescriptor {
	/**
	 * Returns the value of the '<em><b>Memory</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Memory</em>' attribute.
	 * @see #setMemory(int)
	 * @see eu.cloudscaleproject.env.csm.definition.DefinitionPackage#getComputingInfrastructureServiceDescriptor_Memory()
	 * @model
	 * @generated
	 */
	int getMemory();

	/**
	 * Sets the value of the '
	 * {@link eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor#getMemory
	 * <em>Memory</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Memory</em>' attribute.
	 * @see #getMemory()
	 * @generated
	 */
	void setMemory(int value);

	/**
	 * Returns the value of the '<em><b>Cpu</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Cpu</em>' attribute.
	 * @see #setCpu(int)
	 * @see eu.cloudscaleproject.env.csm.definition.DefinitionPackage#getComputingInfrastructureServiceDescriptor_Cpu()
	 * @model
	 * @generated
	 */
	int getCpu();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor#getCpu <em>Cpu</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cpu</em>' attribute.
	 * @see #getCpu()
	 * @generated
	 */
	void setCpu(int value);

	/**
	 * Returns the value of the '<em><b>Cpu Units</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Cpu Units</em>' attribute.
	 * @see #setCpuUnits(int)
	 * @see eu.cloudscaleproject.env.csm.definition.DefinitionPackage#getComputingInfrastructureServiceDescriptor_CpuUnits()
	 * @model
	 * @generated
	 */
	int getCpuUnits();

	/**
	 * Sets the value of the '
	 * {@link eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor#getCpuUnits
	 * <em>Cpu Units</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Cpu Units</em>' attribute.
	 * @see #getCpuUnits()
	 * @generated
	 */
	void setCpuUnits(int value);

	/**
	 * Returns the value of the '<em><b>Storage</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Storage</em>' attribute.
	 * @see #setStorage(int)
	 * @see eu.cloudscaleproject.env.csm.definition.DefinitionPackage#getComputingInfrastructureServiceDescriptor_Storage()
	 * @model
	 * @generated
	 */
	int getStorage();

	/**
	 * Sets the value of the '
	 * {@link eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor#getStorage
	 * <em>Storage</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Storage</em>' attribute.
	 * @see #getStorage()
	 * @generated
	 */
	void setStorage(int value);

} // ComputingInfrastructureServiceDescriptor
