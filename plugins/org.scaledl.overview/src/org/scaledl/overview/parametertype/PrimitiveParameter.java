/**
 */
package org.scaledl.overview.parametertype;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Primitive Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.parametertype.PrimitiveParameter#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.parametertype.ParametertypePackage#getPrimitiveParameter()
 * @model
 * @generated
 */
public interface PrimitiveParameter extends Parameter {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.scaledl.overview.parametertype.TypeEnum}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.scaledl.overview.parametertype.TypeEnum
	 * @see #setType(TypeEnum)
	 * @see org.scaledl.overview.parametertype.ParametertypePackage#getPrimitiveParameter_Type()
	 * @model
	 * @generated
	 */
	TypeEnum getType();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.parametertype.PrimitiveParameter#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.scaledl.overview.parametertype.TypeEnum
	 * @see #getType()
	 * @generated
	 */
	void setType(TypeEnum value);

} // PrimitiveParameter
