/**
 */
package org.scaledl.overview.application;

import org.eclipse.emf.common.util.EList;
import org.scaledl.overview.core.Entity;
import org.scaledl.overview.parametertype.Parameter;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The operation represents specific operation signature in association to parameters and return value.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.application.Operation#getReturnParameter <em>Return Parameter</em>}</li>
 *   <li>{@link org.scaledl.overview.application.Operation#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.application.ApplicationPackage#getOperation()
 * @model
 * @generated
 */
public interface Operation extends Entity {
	/**
	 * Returns the value of the '<em><b>Return Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Return Parameter</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Return Parameter</em>' reference.
	 * @see #setReturnParameter(Parameter)
	 * @see org.scaledl.overview.application.ApplicationPackage#getOperation_ReturnParameter()
	 * @model
	 * @generated
	 */
	Parameter getReturnParameter();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.application.Operation#getReturnParameter <em>Return Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Return Parameter</em>' reference.
	 * @see #getReturnParameter()
	 * @generated
	 */
	void setReturnParameter(Parameter value);

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' reference list.
	 * The list contents are of type {@link org.scaledl.overview.parametertype.Parameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' reference list.
	 * @see org.scaledl.overview.application.ApplicationPackage#getOperation_Parameters()
	 * @model
	 * @generated
	 */
	EList<Parameter> getParameters();

} // Operation
