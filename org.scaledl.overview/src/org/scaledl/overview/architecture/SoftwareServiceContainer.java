/**
 */
package org.scaledl.overview.architecture;

import org.eclipse.emf.common.util.EList;
import org.scaledl.overview.core.Entity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Software Service Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.architecture.SoftwareServiceContainer#getSoftwareServices <em>Software Services</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.architecture.ArchitecturePackage#getSoftwareServiceContainer()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface SoftwareServiceContainer extends Entity {
	/**
	 * Returns the value of the '<em><b>Software Services</b></em>' reference list.
	 * The list contents are of type {@link org.scaledl.overview.architecture.SoftwareService}.
	 * It is bidirectional and its opposite is '{@link org.scaledl.overview.architecture.SoftwareService#getRuntimePlatformService <em>Runtime Platform Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Software Services</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Software Services</em>' reference list.
	 * @see org.scaledl.overview.architecture.ArchitecturePackage#getSoftwareServiceContainer_SoftwareServices()
	 * @see org.scaledl.overview.architecture.SoftwareService#getRuntimePlatformService
	 * @model opposite="runtimePlatformService"
	 * @generated
	 */
	EList<SoftwareService> getSoftwareServices();

} // SoftwareServiceContainer
