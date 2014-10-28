/**
 */
package org.scaledl.overview.architecture;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>External Software Service</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.architecture.ExternalSoftwareService#getServiceProxy <em>Service Proxy</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.architecture.ArchitecturePackage#getExternalSoftwareService()
 * @model
 * @generated
 */
public interface ExternalSoftwareService extends ProvidedSoftwareService {
	/**
	 * Returns the value of the '<em><b>Service Proxy</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service Proxy</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service Proxy</em>' reference.
	 * @see #setServiceProxy(ServiceProxy)
	 * @see org.scaledl.overview.architecture.ArchitecturePackage#getExternalSoftwareService_ServiceProxy()
	 * @model required="true"
	 * @generated
	 */
	ServiceProxy getServiceProxy();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.architecture.ExternalSoftwareService#getServiceProxy <em>Service Proxy</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Proxy</em>' reference.
	 * @see #getServiceProxy()
	 * @generated
	 */
	void setServiceProxy(ServiceProxy value);

} // ExternalSoftwareService
