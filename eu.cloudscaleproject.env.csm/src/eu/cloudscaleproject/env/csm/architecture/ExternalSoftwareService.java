/**
 */
package eu.cloudscaleproject.env.csm.architecture;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>External Software Service</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.ExternalSoftwareService#getServiceProxy <em>Service Proxy</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getExternalSoftwareService()
 * @model
 * @generated
 */
public interface ExternalSoftwareService extends SoftwareService {

	/**
	 * Returns the value of the '<em><b>Service Proxy</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eu.cloudscaleproject.env.csm.architecture.ServiceProxy#getSoftwareService <em>Software Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service Proxy</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service Proxy</em>' container reference.
	 * @see #setServiceProxy(ServiceProxy)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getExternalSoftwareService_ServiceProxy()
	 * @see eu.cloudscaleproject.env.csm.architecture.ServiceProxy#getSoftwareService
	 * @model opposite="softwareService" required="true" transient="false"
	 * @generated
	 */
	ServiceProxy getServiceProxy();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.ExternalSoftwareService#getServiceProxy <em>Service Proxy</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Proxy</em>' container reference.
	 * @see #getServiceProxy()
	 * @generated
	 */
	void setServiceProxy(ServiceProxy value);
} // ExternalSoftwareService
