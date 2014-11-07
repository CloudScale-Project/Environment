/**
 */
package eu.cloudscaleproject.env.method.common.method;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Warning</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.Warning#getId <em>Id</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.Warning#getMessage <em>Message</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.Warning#getCommands <em>Commands</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getWarning()
 * @model
 * @generated
 */
public interface Warning extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getWarning_Id()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.method.common.method.Warning#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message</em>' attribute.
	 * @see #setMessage(String)
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getWarning_Message()
	 * @model required="true"
	 * @generated
	 */
	String getMessage();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.method.common.method.Warning#getMessage <em>Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message</em>' attribute.
	 * @see #getMessage()
	 * @generated
	 */
	void setMessage(String value);

	/**
	 * Returns the value of the '<em><b>Commands</b></em>' containment reference list.
	 * The list contents are of type {@link eu.cloudscaleproject.env.method.common.method.Command}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Commands</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Commands</em>' containment reference list.
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getWarning_Commands()
	 * @model containment="true"
	 * @generated
	 */
	EList<Command> getCommands();

} // Warning
