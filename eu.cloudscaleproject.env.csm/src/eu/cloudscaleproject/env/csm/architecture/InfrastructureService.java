/**
 */
package eu.cloudscaleproject.env.csm.architecture;


/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Infrastructure Service</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.InfrastructureService#getInfrastructureLayer <em>Infrastructure Layer</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getInfrastructureService()
 * @model abstract="true"
 * @generated
 */
public interface InfrastructureService extends Service {
	/**
	 * Returns the value of the '<em><b>Infrastructure Layer</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eu.cloudscaleproject.env.csm.architecture.InfrastructureLayer#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Infrastructure Layer</em>' container reference
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Infrastructure Layer</em>' container reference.
	 * @see #setInfrastructureLayer(InfrastructureLayer)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getInfrastructureService_InfrastructureLayer()
	 * @see eu.cloudscaleproject.env.csm.architecture.InfrastructureLayer#getServices
	 * @model opposite="services" transient="false"
	 * @generated
	 */
	InfrastructureLayer getInfrastructureLayer();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.InfrastructureService#getInfrastructureLayer <em>Infrastructure Layer</em>}' container reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Infrastructure Layer</em>' container reference.
	 * @see #getInfrastructureLayer()
	 * @generated
	 */
	void setInfrastructureLayer(InfrastructureLayer value);

} // InfrastructureService
