/**
 */
package eu.cloudscaleproject.env.csm.architecture;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Software Layer</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.SoftwareLayer#getServices <em>Services</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.SoftwareLayer#getCloudProvider <em>Cloud Provider</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getSoftwareLayer()
 * @model
 * @generated
 */
public interface SoftwareLayer extends EObject {
	/**
	 * Returns the value of the '<em><b>Services</b></em>' containment reference list.
	 * The list contents are of type {@link eu.cloudscaleproject.env.csm.architecture.SoftwareService}.
	 * It is bidirectional and its opposite is '{@link eu.cloudscaleproject.env.csm.architecture.SoftwareService#getSoftwareLayer <em>Software Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Services</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Services</em>' containment reference list.
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getSoftwareLayer_Services()
	 * @see eu.cloudscaleproject.env.csm.architecture.SoftwareService#getSoftwareLayer
	 * @model opposite="softwareLayer" containment="true"
	 * @generated
	 */
	EList<SoftwareService> getServices();

	/**
	 * Returns the value of the '<em><b>Cloud Provider</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getSoftwareLayer <em>Software Layer</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cloud Provider</em>' container reference.
	 * @see #setCloudProvider(CloudEnvironment)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getSoftwareLayer_CloudProvider()
	 * @see eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getSoftwareLayer
	 * @model opposite="softwareLayer" required="true" transient="false"
	 * @generated
	 */
	CloudEnvironment getCloudProvider();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.SoftwareLayer#getCloudProvider <em>Cloud Provider</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cloud Provider</em>' container reference.
	 * @see #getCloudProvider()
	 * @generated
	 */
	void setCloudProvider(CloudEnvironment value);

} // SoftwareLayer
