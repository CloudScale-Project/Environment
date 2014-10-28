/**
 */
package eu.cloudscaleproject.env.method.common.method;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Section Connector</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.SectionConnector#getStart <em>Start</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.SectionConnector#getEnd <em>End</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.SectionConnector#isRequired <em>Required</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getSectionConnector()
 * @model
 * @generated
 */
public interface SectionConnector extends EObject {
	/**
	 * Returns the value of the '<em><b>Start</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start</em>' reference.
	 * @see #setStart(LinkedNode)
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getSectionConnector_Start()
	 * @model required="true"
	 * @generated
	 */
	LinkedNode getStart();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.method.common.method.SectionConnector#getStart <em>Start</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start</em>' reference.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(LinkedNode value);

	/**
	 * Returns the value of the '<em><b>End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End</em>' reference.
	 * @see #setEnd(LinkedNode)
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getSectionConnector_End()
	 * @model required="true"
	 * @generated
	 */
	LinkedNode getEnd();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.method.common.method.SectionConnector#getEnd <em>End</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End</em>' reference.
	 * @see #getEnd()
	 * @generated
	 */
	void setEnd(LinkedNode value);

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
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getSectionConnector_Required()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isRequired();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.method.common.method.SectionConnector#isRequired <em>Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Required</em>' attribute.
	 * @see #isRequired()
	 * @generated
	 */
	void setRequired(boolean value);

} // SectionConnector
