/**
 */
package eu.cloudscaleproject.env.csm.core;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>AV Entry</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link eu.cloudscaleproject.env.csm.core.AVEntry#getKey <em>Key</em>}</li>
 * <li>{@link eu.cloudscaleproject.env.csm.core.AVEntry#getValue <em>Value</em>}
 * </li>
 * </ul>
 * </p>
 * 
 * @see eu.cloudscaleproject.env.csm.core.CorePackage#getAVEntry()
 * @model
 * @generated
 */
public interface AVEntry extends EObject {
	/**
	 * Returns the value of the '<em><b>Key</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Key</em>' attribute.
	 * @see #setKey(String)
	 * @see eu.cloudscaleproject.env.csm.core.CorePackage#getAVEntry_Key()
	 * @model required="true"
	 * @generated
	 */
	String getKey();

	/**
	 * Sets the value of the '
	 * {@link eu.cloudscaleproject.env.csm.core.AVEntry#getKey <em>Key</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Key</em>' attribute.
	 * @see #getKey()
	 * @generated
	 */
	void setKey(String value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(Object)
	 * @see eu.cloudscaleproject.env.csm.core.CorePackage#getAVEntry_Value()
	 * @model required="true"
	 * @generated
	 */
	Object getValue();

	/**
	 * Sets the value of the '
	 * {@link eu.cloudscaleproject.env.csm.core.AVEntry#getValue <em>Value</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(Object value);

} // AVEntry
