/**
 */
package eu.cloudscaleproject.env.csm.architecture;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Internal Connection</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The InternalConnection represents dependency between two components from the same cloud environment (i.e. CloudProvider). 
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.InternalConnection#getSourcePlatformService <em>Source Platform Service</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.InternalConnection#getDestinationPlatformService <em>Destination Platform Service</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getInternalConnection()
 * @model
 * @generated
 */
public interface InternalConnection extends Connection {
	/**
	 * Returns the value of the '<em><b>Source Platform Service</b></em>'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Source Platform Service</em>' reference.
	 * @see #setSourcePlatformService(PlatformService)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getInternalConnection_SourcePlatformService()
	 * @model required="true"
	 * @generated
	 */
	PlatformService getSourcePlatformService();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.InternalConnection#getSourcePlatformService <em>Source Platform Service</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Platform Service</em>' reference.
	 * @see #getSourcePlatformService()
	 * @generated
	 */
	void setSourcePlatformService(PlatformService value);

	/**
	 * Returns the value of the '<em><b>Destination Platform Service</b></em>'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Destination Platform Service</em>'
	 *         reference.
	 * @see #setDestinationPlatformService(PlatformService)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getInternalConnection_DestinationPlatformService()
	 * @model required="true"
	 * @generated
	 */
	PlatformService getDestinationPlatformService();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.InternalConnection#getDestinationPlatformService <em>Destination Platform Service</em>}' reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Destination Platform Service</em>' reference.
	 * @see #getDestinationPlatformService()
	 * @generated
	 */
	void setDestinationPlatformService(PlatformService value);

} // InternalConnection
