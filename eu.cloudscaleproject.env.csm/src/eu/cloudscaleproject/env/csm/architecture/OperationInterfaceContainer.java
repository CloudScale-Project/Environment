/**
 */
package eu.cloudscaleproject.env.csm.architecture;

import org.eclipse.emf.common.util.EList;

import eu.cloudscaleproject.env.csm.core.Entity;
import eu.cloudscaleproject.env.csm.core.OperationInterface;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Operation Interface Container</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer#getProvidedInterfaces <em>Provided Interfaces</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer#getRequiredInterfaces <em>Required Interfaces</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getOperationInterfaceContainer()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface OperationInterfaceContainer extends Entity {
	/**
	 * Returns the value of the '<em><b>Provided Interfaces</b></em>'
	 * containment reference list. The list contents are of type
	 * {@link eu.cloudscaleproject.env.csm.core.OperationInterface}. It is
	 * bidirectional and its opposite is '
	 * {@link eu.cloudscaleproject.env.csm.core.OperationInterface#getProvidingContainer
	 * <em>Providing Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Provided Interfaces</em>' containment
	 *         reference list.
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getOperationInterfaceContainer_ProvidedInterfaces()
	 * @see eu.cloudscaleproject.env.csm.core.OperationInterface#getProvidingContainer
	 * @model opposite="providingContainer" containment="true"
	 * @generated
	 */
	EList<OperationInterface> getProvidedInterfaces();

	/**
	 * Returns the value of the '<em><b>Required Interfaces</b></em>' reference
	 * list. The list contents are of type
	 * {@link eu.cloudscaleproject.env.csm.core.OperationInterface}. It is
	 * bidirectional and its opposite is '
	 * {@link eu.cloudscaleproject.env.csm.core.OperationInterface#getRequiringContainer
	 * <em>Requiring Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Required Interfaces</em>' reference list.
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getOperationInterfaceContainer_RequiredInterfaces()
	 * @see eu.cloudscaleproject.env.csm.core.OperationInterface#getRequiringContainer
	 * @model opposite="requiringContainer"
	 * @generated
	 */
	EList<OperationInterface> getRequiredInterfaces();

} // OperationInterfaceContainer
