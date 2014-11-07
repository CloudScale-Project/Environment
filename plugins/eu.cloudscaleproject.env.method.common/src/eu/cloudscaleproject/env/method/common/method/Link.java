/**
 */
package eu.cloudscaleproject.env.method.common.method;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.Link#getStart <em>Start</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.Link#getEnd <em>End</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.Link#isRequired <em>Required</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getLink()
 * @model
 * @generated
 */
public interface Link extends EObject {
	/**
	 * Returns the value of the '<em><b>Start</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start</em>' reference.
	 * @see #setStart(LinkedObject)
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getLink_Start()
	 * @model required="true"
	 * @generated
	 */
	LinkedObject getStart();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.method.common.method.Link#getStart <em>Start</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start</em>' reference.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(LinkedObject value);

	/**
	 * Returns the value of the '<em><b>End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End</em>' reference.
	 * @see #setEnd(LinkedObject)
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getLink_End()
	 * @model required="true"
	 * @generated
	 */
	LinkedObject getEnd();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.method.common.method.Link#getEnd <em>End</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End</em>' reference.
	 * @see #getEnd()
	 * @generated
	 */
	void setEnd(LinkedObject value);

	/**
	 * Returns the value of the '<em><b>Required</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Required</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required</em>' attribute.
	 * @see #setRequired(boolean)
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getLink_Required()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isRequired();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.method.common.method.Link#isRequired <em>Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Required</em>' attribute.
	 * @see #isRequired()
	 * @generated
	 */
	void setRequired(boolean value);

} // Link
