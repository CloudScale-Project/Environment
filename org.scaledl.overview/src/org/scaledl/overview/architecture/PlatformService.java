/**
 */
package org.scaledl.overview.architecture;

import org.scaledl.overview.specification.PlatformServiceDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Platform Service</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.architecture.PlatformService#getDescriptor <em>Descriptor</em>}</li>
 *   <li>{@link org.scaledl.overview.architecture.PlatformService#getComputingInfrastructureService <em>Computing Infrastructure Service</em>}</li>
 *   <li>{@link org.scaledl.overview.architecture.PlatformService#getPlatformLayer <em>Platform Layer</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.architecture.ArchitecturePackage#getPlatformService()
 * @model abstract="true"
 * @generated
 */
public interface PlatformService extends Service {
	/**
	 * Returns the value of the '<em><b>Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptor</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descriptor</em>' reference.
	 * @see #setDescriptor(PlatformServiceDescriptor)
	 * @see org.scaledl.overview.architecture.ArchitecturePackage#getPlatformService_Descriptor()
	 * @model required="true"
	 * @generated
	 */
	PlatformServiceDescriptor getDescriptor();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.architecture.PlatformService#getDescriptor <em>Descriptor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Descriptor</em>' reference.
	 * @see #getDescriptor()
	 * @generated
	 */
	void setDescriptor(PlatformServiceDescriptor value);

	/**
	 * Returns the value of the '<em><b>Computing Infrastructure Service</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.scaledl.overview.architecture.ComputingInfrastructureService#getPlatformServices <em>Platform Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Computing Infrastructure Service</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Computing Infrastructure Service</em>' reference.
	 * @see #setComputingInfrastructureService(ComputingInfrastructureService)
	 * @see org.scaledl.overview.architecture.ArchitecturePackage#getPlatformService_ComputingInfrastructureService()
	 * @see org.scaledl.overview.architecture.ComputingInfrastructureService#getPlatformServices
	 * @model opposite="platformServices"
	 * @generated
	 */
	ComputingInfrastructureService getComputingInfrastructureService();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.architecture.PlatformService#getComputingInfrastructureService <em>Computing Infrastructure Service</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Computing Infrastructure Service</em>' reference.
	 * @see #getComputingInfrastructureService()
	 * @generated
	 */
	void setComputingInfrastructureService(ComputingInfrastructureService value);

	/**
	 * Returns the value of the '<em><b>Platform Layer</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.scaledl.overview.architecture.PlatformLayer#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Platform Layer</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Platform Layer</em>' container reference.
	 * @see #setPlatformLayer(PlatformLayer)
	 * @see org.scaledl.overview.architecture.ArchitecturePackage#getPlatformService_PlatformLayer()
	 * @see org.scaledl.overview.architecture.PlatformLayer#getServices
	 * @model opposite="services" transient="false"
	 * @generated
	 */
	PlatformLayer getPlatformLayer();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.architecture.PlatformService#getPlatformLayer <em>Platform Layer</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Platform Layer</em>' container reference.
	 * @see #getPlatformLayer()
	 * @generated
	 */
	void setPlatformLayer(PlatformLayer value);

} // PlatformService
