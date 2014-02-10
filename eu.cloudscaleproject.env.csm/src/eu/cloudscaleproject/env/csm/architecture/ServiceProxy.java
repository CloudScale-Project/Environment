/**
 */
package eu.cloudscaleproject.env.csm.architecture;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Service Proxy</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The ServiceConnector represents external services (SaaS) that are used by the modelled system. It exposes interfaces (i.e. OperationInterface), which are required by the modules contained in the system components. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.ServiceProxy#getSoftwareService <em>Software Service</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getServiceProxy()
 * @model
 * @generated
 */
public interface ServiceProxy extends Proxy {
	/**
	 * Returns the value of the '<em><b>Software Service</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link eu.cloudscaleproject.env.csm.architecture.ExternalSoftwareService#getServiceProxy <em>Service Proxy</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Software Service</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Software Service</em>' containment reference.
	 * @see #setSoftwareService(ExternalSoftwareService)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getServiceProxy_SoftwareService()
	 * @see eu.cloudscaleproject.env.csm.architecture.ExternalSoftwareService#getServiceProxy
	 * @model opposite="serviceProxy" containment="true"
	 * @generated
	 */
	ExternalSoftwareService getSoftwareService();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.ServiceProxy#getSoftwareService <em>Software Service</em>}' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Software Service</em>' containment reference.
	 * @see #getSoftwareService()
	 * @generated
	 */
	void setSoftwareService(ExternalSoftwareService value);

} // ServiceProxy
