/**
 */
package org.scaledl.overview.deployment;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Scaling Policy Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.scaledl.overview.deployment.DeploymentPackage#getScalingPolicyType()
 * @model
 * @generated
 */
public enum ScalingPolicyType implements Enumerator {
	/**
	 * The '<em><b>SCALING UP POLICY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SCALING_UP_POLICY_VALUE
	 * @generated
	 * @ordered
	 */
	SCALING_UP_POLICY(0, "SCALING_UP_POLICY", "SCALING_UP_POLICY"),

	/**
	 * The '<em><b>SCALING DOWN POLICY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SCALING_DOWN_POLICY_VALUE
	 * @generated
	 * @ordered
	 */
	SCALING_DOWN_POLICY(1, "SCALING_DOWN_POLICY", "SCALING_DOWN_POLICY"),

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
	 * The '<em><b>SCALING UP POLICY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SCALING UP POLICY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SCALING_UP_POLICY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SCALING_UP_POLICY_VALUE = 0;

	/**
	 * The '<em><b>SCALING DOWN POLICY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SCALING DOWN POLICY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SCALING_DOWN_POLICY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SCALING_DOWN_POLICY_VALUE = 1;

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
	 * An array of all the '<em><b>Scaling Policy Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ScalingPolicyType[] VALUES_ARRAY =
		new ScalingPolicyType[] {
			SCALING_UP_POLICY,
			SCALING_DOWN_POLICY,
			PRECENT_CHANGE_IN_CAPACITY,
		};

	/**
	 * A public read-only list of all the '<em><b>Scaling Policy Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ScalingPolicyType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Scaling Policy Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ScalingPolicyType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ScalingPolicyType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Scaling Policy Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ScalingPolicyType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ScalingPolicyType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Scaling Policy Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ScalingPolicyType get(int value) {
		switch (value) {
			case SCALING_UP_POLICY_VALUE: return SCALING_UP_POLICY;
			case SCALING_DOWN_POLICY_VALUE: return SCALING_DOWN_POLICY;
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
	private ScalingPolicyType(int value, String name, String literal) {
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
	
} //ScalingPolicyType
