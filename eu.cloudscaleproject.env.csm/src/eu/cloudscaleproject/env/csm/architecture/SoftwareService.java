/**
 */
package eu.cloudscaleproject.env.csm.architecture;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Software Service</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.SoftwareService#getSoftwareLayer <em>Software Layer</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getSoftwareService()
 * @model abstract="true"
 * @generated
 */
public interface SoftwareService extends Service, OperationInterfaceContainer {
	/**
	 * Returns the value of the '<em><b>Software Layer</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eu.cloudscaleproject.env.csm.architecture.SoftwareLayer#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Software Layer</em>' container reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Software Layer</em>' container reference.
	 * @see #setSoftwareLayer(SoftwareLayer)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getSoftwareService_SoftwareLayer()
	 * @see eu.cloudscaleproject.env.csm.architecture.SoftwareLayer#getServices
	 * @model opposite="services" transient="false"
	 * @generated
	 */
	SoftwareLayer getSoftwareLayer();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.SoftwareService#getSoftwareLayer <em>Software Layer</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Software Layer</em>' container reference.
	 * @see #getSoftwareLayer()
	 * @generated
	 */
	void setSoftwareLayer(SoftwareLayer value);

} // SoftwareService
