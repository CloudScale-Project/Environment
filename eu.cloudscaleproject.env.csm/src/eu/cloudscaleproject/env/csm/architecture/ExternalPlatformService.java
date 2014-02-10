/**
 */
package eu.cloudscaleproject.env.csm.architecture;

import eu.cloudscaleproject.env.csm.definition.ExternalPlatformServiceDescriptor;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>External Platform Service</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.ExternalPlatformService#getDescriptor <em>Descriptor</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getExternalPlatformService()
 * @model abstract="true"
 * @generated
 */
public interface ExternalPlatformService extends PlatformService {
	/**
	 * Returns the value of the '<em><b>Descriptor</b></em>' reference. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptor</em>' reference isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Descriptor</em>' reference.
	 * @see #setDescriptor(ExternalPlatformServiceDescriptor)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getExternalPlatformService_Descriptor()
	 * @model required="true"
	 * @generated
	 */
	ExternalPlatformServiceDescriptor getDescriptor();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.ExternalPlatformService#getDescriptor <em>Descriptor</em>}' reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Descriptor</em>' reference.
	 * @see #getDescriptor()
	 * @generated
	 */
	void setDescriptor(ExternalPlatformServiceDescriptor value);

} // ExternalPlatformService
