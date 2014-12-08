/**
 */
package org.scaledl.overview.architecture;

import org.scaledl.overview.application.OperationInterfaceContainer;

import org.scaledl.overview.specification.NetworkInfrastructureServiceDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Internal Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The InternalConnection represents dependency between two components from the same cloud environment (i.e. CloudProvider). 
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.architecture.InternalConnection#getDescriptor <em>Descriptor</em>}</li>
 *   <li>{@link org.scaledl.overview.architecture.InternalConnection#getSource <em>Source</em>}</li>
 *   <li>{@link org.scaledl.overview.architecture.InternalConnection#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.architecture.ArchitecturePackage#getInternalConnection()
 * @model
 * @generated
 */
public interface InternalConnection extends Connection {
	/**
	 * Returns the value of the '<em><b>Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Descriptor</em>' reference.
	 * @see #setDescriptor(NetworkInfrastructureServiceDescriptor)
	 * @see org.scaledl.overview.architecture.ArchitecturePackage#getInternalConnection_Descriptor()
	 * @model required="true"
	 * @generated
	 */
	NetworkInfrastructureServiceDescriptor getDescriptor();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.architecture.InternalConnection#getDescriptor <em>Descriptor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Descriptor</em>' reference.
	 * @see #getDescriptor()
	 * @generated
	 */
	void setDescriptor(NetworkInfrastructureServiceDescriptor value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(OperationInterfaceContainer)
	 * @see org.scaledl.overview.architecture.ArchitecturePackage#getInternalConnection_Source()
	 * @model required="true"
	 * @generated
	 */
	OperationInterfaceContainer getSource();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.architecture.InternalConnection#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(OperationInterfaceContainer value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(OperationInterfaceContainer)
	 * @see org.scaledl.overview.architecture.ArchitecturePackage#getInternalConnection_Target()
	 * @model required="true"
	 * @generated
	 */
	OperationInterfaceContainer getTarget();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.architecture.InternalConnection#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(OperationInterfaceContainer value);

} // InternalConnection
