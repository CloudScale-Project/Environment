/**
 */
package eu.cloudscaleproject.env.csm.definition;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>System Definition</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The SystemDefinition is part of the CSM and is container for all descriptors referenced by the architecture classes. The main purpose of a such container is to be able to reference same descriptor more than once, and thus providing easier maintenance of a system specifications in the case of change (e.g. AWS upgrades it’s instances - more memory and storage).
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.definition.SystemDefinition#getDescriptors <em>Descriptors</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.definition.DefinitionPackage#getSystemDefinition()
 * @model
 * @generated
 */
public interface SystemDefinition extends Definition {
	/**
	 * Returns the value of the '<em><b>Descriptors</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link eu.cloudscaleproject.env.csm.definition.Descriptor}. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Descriptors</em>' containment reference
	 *         list.
	 * @see eu.cloudscaleproject.env.csm.definition.DefinitionPackage#getSystemDefinition_Descriptors()
	 * @model containment="true"
	 * @generated
	 */
	EList<Descriptor> getDescriptors();

} // SystemDefinition
