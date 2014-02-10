/**
 */
package eu.cloudscaleproject.env.csm.architecture;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Platform Layer</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.PlatformLayer#getServices <em>Services</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.PlatformLayer#getCloudEnvironment <em>Cloud Environment</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getPlatformLayer()
 * @model
 * @generated
 */
public interface PlatformLayer extends EObject {
	/**
	 * Returns the value of the '<em><b>Services</b></em>' containment reference list.
	 * The list contents are of type {@link eu.cloudscaleproject.env.csm.architecture.PlatformService}.
	 * It is bidirectional and its opposite is '{@link eu.cloudscaleproject.env.csm.architecture.PlatformService#getPlatformLayer <em>Platform Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Services</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Services</em>' containment reference list.
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getPlatformLayer_Services()
	 * @see eu.cloudscaleproject.env.csm.architecture.PlatformService#getPlatformLayer
	 * @model opposite="platformLayer" containment="true"
	 * @generated
	 */
	EList<PlatformService> getServices();

	/**
	 * Returns the value of the '<em><b>Cloud Environment</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getPlatformLayer <em>Platform Layer</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cloud Environment</em>' container reference.
	 * @see #setCloudEnvironment(CloudEnvironment)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getPlatformLayer_CloudEnvironment()
	 * @see eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getPlatformLayer
	 * @model opposite="platformLayer" required="true" transient="false"
	 * @generated
	 */
	CloudEnvironment getCloudEnvironment();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.PlatformLayer#getCloudEnvironment <em>Cloud Environment</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cloud Environment</em>' container reference.
	 * @see #getCloudEnvironment()
	 * @generated
	 */
	void setCloudEnvironment(CloudEnvironment value);

} // PlatformLayer
