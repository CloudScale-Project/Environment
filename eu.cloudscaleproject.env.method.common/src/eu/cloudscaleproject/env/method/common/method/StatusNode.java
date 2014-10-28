/**
 */
package eu.cloudscaleproject.env.method.common.method;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Status Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.StatusNode#isDone <em>Done</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.StatusNode#isDirty <em>Dirty</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.StatusNode#getWarnings <em>Warnings</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getStatusNode()
 * @model
 * @generated
 */
public interface StatusNode extends Node {
	/**
	 * Returns the value of the '<em><b>Done</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Done</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Done</em>' attribute.
	 * @see #setDone(boolean)
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getStatusNode_Done()
	 * @model default="false" transient="true"
	 * @generated
	 */
	boolean isDone();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.method.common.method.StatusNode#isDone <em>Done</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Done</em>' attribute.
	 * @see #isDone()
	 * @generated
	 */
	void setDone(boolean value);

	/**
	 * Returns the value of the '<em><b>Dirty</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dirty</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dirty</em>' attribute.
	 * @see #setDirty(boolean)
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getStatusNode_Dirty()
	 * @model default="false" transient="true"
	 * @generated
	 */
	boolean isDirty();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.method.common.method.StatusNode#isDirty <em>Dirty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dirty</em>' attribute.
	 * @see #isDirty()
	 * @generated
	 */
	void setDirty(boolean value);

	/**
	 * Returns the value of the '<em><b>Warnings</b></em>' containment reference list.
	 * The list contents are of type {@link eu.cloudscaleproject.env.method.common.method.Warning}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Warnings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Warnings</em>' containment reference list.
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getStatusNode_Warnings()
	 * @model containment="true" transient="true"
	 * @generated
	 */
	EList<Warning> getWarnings();

} // StatusNode
