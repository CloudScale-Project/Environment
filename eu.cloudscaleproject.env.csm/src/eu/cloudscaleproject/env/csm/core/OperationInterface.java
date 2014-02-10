/**
 */
package eu.cloudscaleproject.env.csm.core;

import org.eclipse.emf.common.util.EList;

import eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Operation Interface</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This entity models represents provided or required services, used by modules (see Module), as a set of operation signatures (see Operation). 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.core.OperationInterface#getOperations <em>Operations</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.core.OperationInterface#getProvidingContainer <em>Providing Container</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.core.OperationInterface#getRequiringContainer <em>Requiring Container</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.core.CorePackage#getOperationInterface()
 * @model
 * @generated
 */
public interface OperationInterface extends Entity {
	/**
	 * Returns the value of the '<em><b>Operations</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link eu.cloudscaleproject.env.csm.core.Operation}. <!-- begin-user-doc
	 * --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Operations</em>' containment reference
	 *         list.
	 * @see eu.cloudscaleproject.env.csm.core.CorePackage#getOperationInterface_Operations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Operation> getOperations();

	/**
	 * Returns the value of the '<em><b>Providing Container</b></em>' container
	 * reference. It is bidirectional and its opposite is '
	 * {@link eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer#getProvidedInterfaces
	 * <em>Provided Interfaces</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Providing Container</em>' container
	 *         reference.
	 * @see #setProvidingContainer(OperationInterfaceContainer)
	 * @see eu.cloudscaleproject.env.csm.core.CorePackage#getOperationInterface_ProvidingContainer()
	 * @see eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer#getProvidedInterfaces
	 * @model opposite="providedInterfaces" transient="false"
	 * @generated
	 */
	OperationInterfaceContainer getProvidingContainer();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.core.OperationInterface#getProvidingContainer <em>Providing Container</em>}' container reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Providing Container</em>' container reference.
	 * @see #getProvidingContainer()
	 * @generated
	 */
	void setProvidingContainer(OperationInterfaceContainer value);

	/**
	 * Returns the value of the '<em><b>Requiring Container</b></em>' reference list.
	 * The list contents are of type {@link eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer}.
	 * It is bidirectional and its opposite is '{@link eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer#getRequiredInterfaces <em>Required Interfaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requiring Container</em>' reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requiring Container</em>' reference list.
	 * @see eu.cloudscaleproject.env.csm.core.CorePackage#getOperationInterface_RequiringContainer()
	 * @see eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer#getRequiredInterfaces
	 * @model opposite="requiredInterfaces"
	 * @generated
	 */
	EList<OperationInterfaceContainer> getRequiringContainer();

} // OperationInterface
