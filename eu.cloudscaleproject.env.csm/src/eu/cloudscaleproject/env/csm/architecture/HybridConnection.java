/**
 */
package eu.cloudscaleproject.env.csm.architecture;

import eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Hybrid Connection</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The HybridConnection represents dependency between two components from different cloud environments (i.e. CloudProvider). This connection enables modeling of hybrid clouds.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.HybridConnection#getSourcePlatformService <em>Source Platform Service</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.HybridConnection#getTargetPlatformService <em>Target Platform Service</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.HybridConnection#getDescriptor <em>Descriptor</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getHybridConnection()
 * @model
 * @generated
 */
public interface HybridConnection extends Connection {
	/**
	 * Returns the value of the '<em><b>Source Platform Service</b></em>'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Source Platform Service</em>' reference.
	 * @see #setSourcePlatformService(PlatformService)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getHybridConnection_SourcePlatformService()
	 * @model required="true"
	 * @generated
	 */
	PlatformService getSourcePlatformService();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.HybridConnection#getSourcePlatformService <em>Source Platform Service</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Platform Service</em>' reference.
	 * @see #getSourcePlatformService()
	 * @generated
	 */
	void setSourcePlatformService(PlatformService value);

	/**
	 * Returns the value of the '<em><b>Target Platform Service</b></em>'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Target Platform Service</em>' reference.
	 * @see #setTargetPlatformService(PlatformService)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getHybridConnection_TargetPlatformService()
	 * @model required="true"
	 * @generated
	 */
	PlatformService getTargetPlatformService();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.HybridConnection#getTargetPlatformService <em>Target Platform Service</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Platform Service</em>' reference.
	 * @see #getTargetPlatformService()
	 * @generated
	 */
	void setTargetPlatformService(PlatformService value);

	/**
	 * Returns the value of the '<em><b>Descriptor</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Descriptor</em>' reference.
	 * @see #setDescriptor(NetworkInfrastructureServiceDescriptor)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getHybridConnection_Descriptor()
	 * @model required="true"
	 * @generated
	 */
	NetworkInfrastructureServiceDescriptor getDescriptor();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.HybridConnection#getDescriptor <em>Descriptor</em>}' reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Descriptor</em>' reference.
	 * @see #getDescriptor()
	 * @generated
	 */
	void setDescriptor(NetworkInfrastructureServiceDescriptor value);

} // HybridConnection
