/**
 */
package org.scaledl.overview.architecture;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Software Layer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.architecture.SoftwareLayer#getServices <em>Services</em>}</li>
 *   <li>{@link org.scaledl.overview.architecture.SoftwareLayer#getCloudEnvironment <em>Cloud Environment</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.architecture.ArchitecturePackage#getSoftwareLayer()
 * @model
 * @generated
 */
public interface SoftwareLayer extends EObject {
	/**
	 * Returns the value of the '<em><b>Services</b></em>' containment reference list.
	 * The list contents are of type {@link org.scaledl.overview.architecture.SoftwareService}.
	 * It is bidirectional and its opposite is '{@link org.scaledl.overview.architecture.SoftwareService#getSoftwareLayer <em>Software Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Services</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Services</em>' containment reference list.
	 * @see org.scaledl.overview.architecture.ArchitecturePackage#getSoftwareLayer_Services()
	 * @see org.scaledl.overview.architecture.SoftwareService#getSoftwareLayer
	 * @model opposite="softwareLayer" containment="true"
	 * @generated
	 */
	EList<SoftwareService> getServices();

	/**
	 * Returns the value of the '<em><b>Cloud Environment</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.scaledl.overview.architecture.CloudEnvironment#getSoftwareLayer <em>Software Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cloud Environment</em>' container reference.
	 * @see #setCloudEnvironment(CloudEnvironment)
	 * @see org.scaledl.overview.architecture.ArchitecturePackage#getSoftwareLayer_CloudEnvironment()
	 * @see org.scaledl.overview.architecture.CloudEnvironment#getSoftwareLayer
	 * @model opposite="softwareLayer" required="true" transient="false"
	 * @generated
	 */
	CloudEnvironment getCloudEnvironment();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.architecture.SoftwareLayer#getCloudEnvironment <em>Cloud Environment</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cloud Environment</em>' container reference.
	 * @see #getCloudEnvironment()
	 * @generated
	 */
	void setCloudEnvironment(CloudEnvironment value);

} // SoftwareLayer
