/**
 */
package org.scaledl.overview.application;

import org.eclipse.emf.common.util.EList;
import org.scaledl.overview.core.Entity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Interface Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.application.OperationInterfaceContainer#getProvidedInterfaces <em>Provided Interfaces</em>}</li>
 *   <li>{@link org.scaledl.overview.application.OperationInterfaceContainer#getRequiredInterfaces <em>Required Interfaces</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.application.ApplicationPackage#getOperationInterfaceContainer()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface OperationInterfaceContainer extends Entity {
	/**
	 * Returns the value of the '<em><b>Provided Interfaces</b></em>' containment reference list.
	 * The list contents are of type {@link org.scaledl.overview.application.OperationInterface}.
	 * It is bidirectional and its opposite is '{@link org.scaledl.overview.application.OperationInterface#getProvidingContainer <em>Providing Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Provided Interfaces</em>' containment reference list.
	 * @see org.scaledl.overview.application.ApplicationPackage#getOperationInterfaceContainer_ProvidedInterfaces()
	 * @see org.scaledl.overview.application.OperationInterface#getProvidingContainer
	 * @model opposite="providingContainer" containment="true"
	 * @generated
	 */
	EList<OperationInterface> getProvidedInterfaces();

	/**
	 * Returns the value of the '<em><b>Required Interfaces</b></em>' reference list.
	 * The list contents are of type {@link org.scaledl.overview.application.OperationInterface}.
	 * It is bidirectional and its opposite is '{@link org.scaledl.overview.application.OperationInterface#getRequiringContainer <em>Requiring Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Required Interfaces</em>' reference list.
	 * @see org.scaledl.overview.application.ApplicationPackage#getOperationInterfaceContainer_RequiredInterfaces()
	 * @see org.scaledl.overview.application.OperationInterface#getRequiringContainer
	 * @model opposite="requiringContainer"
	 * @generated
	 */
	EList<OperationInterface> getRequiredInterfaces();

} // OperationInterfaceContainer
