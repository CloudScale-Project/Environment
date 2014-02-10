/**
 */
package eu.cloudscaleproject.env.csm.architecture;

import eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>External Connection</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The ExternalConnection represents the dependency between component and connector (see Connector) and therefore the connection that links outside environment with the modelled system.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.ExternalConnection#getProxy <em>Proxy</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.ExternalConnection#getPlatformService <em>Platform Service</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.ExternalConnection#getIsOutbound <em>Is Outbound</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.ExternalConnection#getDescriptor <em>Descriptor</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getExternalConnection()
 * @model
 * @generated
 */
public interface ExternalConnection extends Connection {
	/**
	 * Returns the value of the '<em><b>Proxy</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Proxy</em>' reference.
	 * @see #setProxy(Proxy)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getExternalConnection_Proxy()
	 * @model
	 * @generated
	 */
	Proxy getProxy();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.ExternalConnection#getProxy <em>Proxy</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Proxy</em>' reference.
	 * @see #getProxy()
	 * @generated
	 */
	void setProxy(Proxy value);

	/**
	 * Returns the value of the '<em><b>Platform Service</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Platform Service</em>' reference.
	 * @see #setPlatformService(PlatformService)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getExternalConnection_PlatformService()
	 * @model
	 * @generated
	 */
	PlatformService getPlatformService();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.ExternalConnection#getPlatformService <em>Platform Service</em>}' reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Platform Service</em>' reference.
	 * @see #getPlatformService()
	 * @generated
	 */
	void setPlatformService(PlatformService value);

	/**
	 * Returns the value of the '<em><b>Is Outbound</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Is Outbound</em>' attribute.
	 * @see #setIsOutbound(Boolean)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getExternalConnection_IsOutbound()
	 * @model required="true"
	 * @generated
	 */
	Boolean getIsOutbound();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.ExternalConnection#getIsOutbound <em>Is Outbound</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Is Outbound</em>' attribute.
	 * @see #getIsOutbound()
	 * @generated
	 */
	void setIsOutbound(Boolean value);

	/**
	 * Returns the value of the '<em><b>Descriptor</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Descriptor</em>' reference.
	 * @see #setDescriptor(NetworkInfrastructureServiceDescriptor)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getExternalConnection_Descriptor()
	 * @model required="true"
	 * @generated
	 */
	NetworkInfrastructureServiceDescriptor getDescriptor();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.ExternalConnection#getDescriptor <em>Descriptor</em>}' reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Descriptor</em>' reference.
	 * @see #getDescriptor()
	 * @generated
	 */
	void setDescriptor(NetworkInfrastructureServiceDescriptor value);

} // ExternalConnection
