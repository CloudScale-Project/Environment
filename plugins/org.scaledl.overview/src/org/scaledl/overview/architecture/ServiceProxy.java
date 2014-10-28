/**
 */
package org.scaledl.overview.architecture;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service Proxy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The ServiceConnector represents external services (SaaS) that are used by the modelled system. It exposes interfaces (i.e. OperationInterface), which are required by the modules contained in the system components. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.architecture.ServiceProxy#getSoftwareService <em>Software Service</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.architecture.ArchitecturePackage#getServiceProxy()
 * @model
 * @generated
 */
public interface ServiceProxy extends Proxy {
	/**
	 * Returns the value of the '<em><b>Software Service</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Software Service</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Software Service</em>' containment reference.
	 * @see #setSoftwareService(SoftwareService)
	 * @see org.scaledl.overview.architecture.ArchitecturePackage#getServiceProxy_SoftwareService()
	 * @model containment="true"
	 * @generated
	 */
	SoftwareService getSoftwareService();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.architecture.ServiceProxy#getSoftwareService <em>Software Service</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Software Service</em>' containment reference.
	 * @see #getSoftwareService()
	 * @generated
	 */
	void setSoftwareService(SoftwareService value);

} // ServiceProxy
