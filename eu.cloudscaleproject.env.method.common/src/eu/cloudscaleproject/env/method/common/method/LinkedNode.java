/**
 */
package eu.cloudscaleproject.env.method.common.method;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Linked Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.LinkedNode#getNext <em>Next</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.LinkedNode#getPrevious <em>Previous</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getLinkedNode()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface LinkedNode extends EObject {
	/**
	 * Returns the value of the '<em><b>Next</b></em>' reference list.
	 * The list contents are of type {@link eu.cloudscaleproject.env.method.common.method.SectionConnector}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Next</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Next</em>' reference list.
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getLinkedNode_Next()
	 * @model
	 * @generated
	 */
	EList<SectionConnector> getNext();

	/**
	 * Returns the value of the '<em><b>Previous</b></em>' reference list.
	 * The list contents are of type {@link eu.cloudscaleproject.env.method.common.method.SectionConnector}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Previous</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Previous</em>' reference list.
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getLinkedNode_Previous()
	 * @model
	 * @generated
	 */
	EList<SectionConnector> getPrevious();

} // LinkedNode
