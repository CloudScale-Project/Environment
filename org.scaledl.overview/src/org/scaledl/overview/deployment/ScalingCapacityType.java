/**
 */
package org.scaledl.overview.deployment;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Scaling Capacity Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.scaledl.overview.deployment.DeploymentPackage#getScalingCapacityType()
 * @model
 * @generated
 */
public enum ScalingCapacityType implements Enumerator {
	/**
	 * The '<em><b>CHANGE IN CAPACITY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CHANGE_IN_CAPACITY_VALUE
	 * @generated
	 * @ordered
	 */
	CHANGE_IN_CAPACITY(0, "CHANGE_IN_CAPACITY", "CHANGE_IN_CAPACITY"),

	/**
	 * The '<em><b>EXACT CAPACITY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EXACT_CAPACITY_VALUE
	 * @generated
	 * @ordered
	 */
	EXACT_CAPACITY(1, "EXACT_CAPACITY", "EXACT_CAPACITY"),

	/**
	 * The '<em><b>PRECENT CHANGE IN CAPACITY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PRECENT_CHANGE_IN_CAPACITY_VALUE
	 * @generated
	 * @ordered
	 */
	PRECENT_CHANGE_IN_CAPACITY(2, "PRECENT_CHANGE_IN_CAPACITY", "PRECENT_CHANGE_IN_CAPACITY");

	/**
	 * The '<em><b>CHANGE IN CAPACITY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CHANGE IN CAPACITY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CHANGE_IN_CAPACITY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CHANGE_IN_CAPACITY_VALUE = 0;

	/**
	 * The '<em><b>EXACT CAPACITY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>EXACT CAPACITY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EXACT_CAPACITY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int EXACT_CAPACITY_VALUE = 1;

	/**
	 * The '<em><b>PRECENT CHANGE IN CAPACITY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PRECENT CHANGE IN CAPACITY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PRECENT_CHANGE_IN_CAPACITY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PRECENT_CHANGE_IN_CAPACITY_VALUE = 2;

	/**
	 * An array of all the '<em><b>Scaling Capacity Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ScalingCapacityType[] VALUES_ARRAY =
		new ScalingCapacityType[] {
			CHANGE_IN_CAPACITY,
			EXACT_CAPACITY,
			PRECENT_CHANGE_IN_CAPACITY,
		};

	/**
	 * A public read-only list of all the '<em><b>Scaling Capacity Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ScalingCapacityType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Scaling Capacity Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ScalingCapacityType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ScalingCapacityType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Scaling Capacity Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ScalingCapacityType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ScalingCapacityType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Scaling Capacity Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ScalingCapacityType get(int value) {
		switch (value) {
			case CHANGE_IN_CAPACITY_VALUE: return CHANGE_IN_CAPACITY;
			case EXACT_CAPACITY_VALUE: return EXACT_CAPACITY;
			case PRECENT_CHANGE_IN_CAPACITY_VALUE: return PRECENT_CHANGE_IN_CAPACITY;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ScalingCapacityType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //ScalingCapacityType
