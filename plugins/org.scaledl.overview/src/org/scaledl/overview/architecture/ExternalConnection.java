/**
 */
package org.scaledl.overview.architecture;

import org.scaledl.overview.application.OperationInterfaceContainer;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>External Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The HybridConnection represents dependency between two components from different cloud environments (i.e. CloudProvider). This connection enables modeling of hybrid clouds.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.architecture.ExternalConnection#getSource <em>Source</em>}</li>
 *   <li>{@link org.scaledl.overview.architecture.ExternalConnection#getTarget <em>Target</em>}</li>
 *   <li>{@link org.scaledl.overview.architecture.ExternalConnection#getBandwidth <em>Bandwidth</em>}</li>
 *   <li>{@link org.scaledl.overview.architecture.ExternalConnection#getLatency <em>Latency</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.architecture.ArchitecturePackage#getExternalConnection()
 * @model
 * @generated
 */
public interface ExternalConnection extends Connection {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(OperationInterfaceContainer)
	 * @see org.scaledl.overview.architecture.ArchitecturePackage#getExternalConnection_Source()
	 * @model required="true"
	 * @generated
	 */
	OperationInterfaceContainer getSource();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.architecture.ExternalConnection#getSource <em>Source</em>}' reference.
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
	 * @see org.scaledl.overview.architecture.ArchitecturePackage#getExternalConnection_Target()
	 * @model required="true"
	 * @generated
	 */
	OperationInterfaceContainer getTarget();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.architecture.ExternalConnection#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(OperationInterfaceContainer value);

	/**
	 * Returns the value of the '<em><b>Bandwidth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bandwidth</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bandwidth</em>' attribute.
	 * @see #setBandwidth(int)
	 * @see org.scaledl.overview.architecture.ArchitecturePackage#getExternalConnection_Bandwidth()
	 * @model required="true"
	 * @generated
	 */
	int getBandwidth();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.architecture.ExternalConnection#getBandwidth <em>Bandwidth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bandwidth</em>' attribute.
	 * @see #getBandwidth()
	 * @generated
	 */
	void setBandwidth(int value);

	/**
	 * Returns the value of the '<em><b>Latency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Latency</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Latency</em>' attribute.
	 * @see #setLatency(int)
	 * @see org.scaledl.overview.architecture.ArchitecturePackage#getExternalConnection_Latency()
	 * @model required="true"
	 * @generated
	 */
	int getLatency();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.architecture.ExternalConnection#getLatency <em>Latency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Latency</em>' attribute.
	 * @see #getLatency()
	 * @generated
	 */
	void setLatency(int value);

} // ExternalConnection
