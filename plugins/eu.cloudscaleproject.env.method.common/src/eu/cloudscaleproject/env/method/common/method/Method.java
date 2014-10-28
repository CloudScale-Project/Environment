/**
 */
package eu.cloudscaleproject.env.method.common.method;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.Method#getSectionConnectors <em>Section Connectors</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.Method#getNodes <em>Nodes</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getMethod()
 * @model
 * @generated
 */
public interface Method extends EObject {
	/**
	 * Returns the value of the '<em><b>Section Connectors</b></em>' containment reference list.
	 * The list contents are of type {@link eu.cloudscaleproject.env.method.common.method.SectionConnector}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Section Connectors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Section Connectors</em>' containment reference list.
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getMethod_SectionConnectors()
	 * @model containment="true"
	 * @generated
	 */
	EList<SectionConnector> getSectionConnectors();

	/**
	 * Returns the value of the '<em><b>Nodes</b></em>' containment reference list.
	 * The list contents are of type {@link eu.cloudscaleproject.env.method.common.method.Node}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nodes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nodes</em>' containment reference list.
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getMethod_Nodes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Node> getNodes();

} // Method
