/**
 */
package org.scaledl.overview.architecture;

import org.scaledl.overview.application.OperationInterfaceContainer;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Software Service</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.architecture.SoftwareService#getRuntimePlatformService <em>Runtime Platform Service</em>}</li>
 *   <li>{@link org.scaledl.overview.architecture.SoftwareService#getSoftwareLayer <em>Software Layer</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.architecture.ArchitecturePackage#getSoftwareService()
 * @model
 * @generated
 */
public interface SoftwareService extends Service, OperationInterfaceContainer {
	/**
	 * Returns the value of the '<em><b>Runtime Platform Service</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.scaledl.overview.architecture.SoftwareServiceContainer#getSoftwareServices <em>Software Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Runtime Platform Service</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Runtime Platform Service</em>' reference.
	 * @see #setRuntimePlatformService(SoftwareServiceContainer)
	 * @see org.scaledl.overview.architecture.ArchitecturePackage#getSoftwareService_RuntimePlatformService()
	 * @see org.scaledl.overview.architecture.SoftwareServiceContainer#getSoftwareServices
	 * @model opposite="softwareServices"
	 * @generated
	 */
	SoftwareServiceContainer getRuntimePlatformService();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.architecture.SoftwareService#getRuntimePlatformService <em>Runtime Platform Service</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Runtime Platform Service</em>' reference.
	 * @see #getRuntimePlatformService()
	 * @generated
	 */
	void setRuntimePlatformService(SoftwareServiceContainer value);

	/**
	 * Returns the value of the '<em><b>Software Layer</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.scaledl.overview.architecture.SoftwareLayer#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Software Layer</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Software Layer</em>' container reference.
	 * @see #setSoftwareLayer(SoftwareLayer)
	 * @see org.scaledl.overview.architecture.ArchitecturePackage#getSoftwareService_SoftwareLayer()
	 * @see org.scaledl.overview.architecture.SoftwareLayer#getServices
	 * @model opposite="services" transient="false"
	 * @generated
	 */
	SoftwareLayer getSoftwareLayer();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.architecture.SoftwareService#getSoftwareLayer <em>Software Layer</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Software Layer</em>' container reference.
	 * @see #getSoftwareLayer()
	 * @generated
	 */
	void setSoftwareLayer(SoftwareLayer value);

} // SoftwareService
