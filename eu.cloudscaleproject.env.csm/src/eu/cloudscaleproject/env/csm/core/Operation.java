/**
 */
package eu.cloudscaleproject.env.csm.core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Operation</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The operation represents specific operation signature in association to parameters and return value.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.core.Operation#getMethodName <em>Method Name</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.core.Operation#getReturnValue <em>Return Value</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.core.Operation#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.core.CorePackage#getOperation()
 * @model
 * @generated
 */
public interface Operation extends Entity {
	/**
	 * Returns the value of the '<em><b>Method Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Method Name</em>' attribute.
	 * @see #setMethodName(String)
	 * @see eu.cloudscaleproject.env.csm.core.CorePackage#getOperation_MethodName()
	 * @model
	 * @generated
	 */
	String getMethodName();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.core.Operation#getMethodName <em>Method Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Method Name</em>' attribute.
	 * @see #getMethodName()
	 * @generated
	 */
	void setMethodName(String value);

	/**
	 * Returns the value of the '<em><b>Return Value</b></em>' attribute. The
	 * literals are from the enumeration
	 * {@link eu.cloudscaleproject.env.csm.core.TypeEnum}. <!-- begin-user-doc
	 * --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Return Value</em>' attribute.
	 * @see eu.cloudscaleproject.env.csm.core.TypeEnum
	 * @see #setReturnValue(TypeEnum)
	 * @see eu.cloudscaleproject.env.csm.core.CorePackage#getOperation_ReturnValue()
	 * @model
	 * @generated
	 */
	TypeEnum getReturnValue();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.core.Operation#getReturnValue <em>Return Value</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Return Value</em>' attribute.
	 * @see eu.cloudscaleproject.env.csm.core.TypeEnum
	 * @see #getReturnValue()
	 * @generated
	 */
	void setReturnValue(TypeEnum value);

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' attribute list. The
	 * list contents are of type
	 * {@link eu.cloudscaleproject.env.csm.core.TypeEnum}. The literals are from
	 * the enumeration {@link eu.cloudscaleproject.env.csm.core.TypeEnum}. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Parameters</em>' attribute list.
	 * @see eu.cloudscaleproject.env.csm.core.TypeEnum
	 * @see eu.cloudscaleproject.env.csm.core.CorePackage#getOperation_Parameters()
	 * @model
	 * @generated
	 */
	EList<TypeEnum> getParameters();

} // Operation
