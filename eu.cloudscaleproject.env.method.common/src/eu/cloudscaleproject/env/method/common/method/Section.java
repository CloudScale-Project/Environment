/**
 */
package eu.cloudscaleproject.env.method.common.method;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Section</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.Section#isValid <em>Valid</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.Section#isInProgress <em>In Progress</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.Section#getCommands <em>Commands</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.Section#getRequirements <em>Requirements</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getSection()
 * @model
 * @generated
 */
public interface Section extends StatusNode, LinkedNode {
	/**
	 * Returns the value of the '<em><b>Valid</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Valid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Valid</em>' attribute.
	 * @see #setValid(boolean)
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getSection_Valid()
	 * @model default="true" transient="true"
	 * @generated
	 */
	boolean isValid();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.method.common.method.Section#isValid <em>Valid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Valid</em>' attribute.
	 * @see #isValid()
	 * @generated
	 */
	void setValid(boolean value);

	/**
	 * Returns the value of the '<em><b>In Progress</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Progress</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Progress</em>' attribute.
	 * @see #setInProgress(boolean)
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getSection_InProgress()
	 * @model default="false" transient="true"
	 * @generated
	 */
	boolean isInProgress();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.method.common.method.Section#isInProgress <em>In Progress</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>In Progress</em>' attribute.
	 * @see #isInProgress()
	 * @generated
	 */
	void setInProgress(boolean value);

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
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getSection_Commands()
	 * @model containment="true"
	 * @generated
	 */
	EList<Command> getCommands();

	/**
	 * Returns the value of the '<em><b>Requirements</b></em>' containment reference list.
	 * The list contents are of type {@link eu.cloudscaleproject.env.method.common.method.Requirement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requirements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requirements</em>' containment reference list.
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getSection_Requirements()
	 * @model containment="true"
	 * @generated
	 */
	EList<Requirement> getRequirements();

} // Section
