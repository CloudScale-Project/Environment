/**
 */
package eu.cloudscaleproject.env.csm.architecture;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Platform Service</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.PlatformService#getPlatformLayer <em>Platform Layer</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getPlatformService()
 * @model abstract="true"
 * @generated
 */
public interface PlatformService extends Service {
	/**
	 * Returns the value of the '<em><b>Platform Layer</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eu.cloudscaleproject.env.csm.architecture.PlatformLayer#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Platform Layer</em>' container reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Platform Layer</em>' container reference.
	 * @see #setPlatformLayer(PlatformLayer)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getPlatformService_PlatformLayer()
	 * @see eu.cloudscaleproject.env.csm.architecture.PlatformLayer#getServices
	 * @model opposite="services" transient="false"
	 * @generated
	 */
	PlatformLayer getPlatformLayer();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.PlatformService#getPlatformLayer <em>Platform Layer</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Platform Layer</em>' container reference.
	 * @see #getPlatformLayer()
	 * @generated
	 */
	void setPlatformLayer(PlatformLayer value);

} // PlatformService
