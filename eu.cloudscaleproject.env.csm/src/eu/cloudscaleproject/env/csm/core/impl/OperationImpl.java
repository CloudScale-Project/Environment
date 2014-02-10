/**
 */
package eu.cloudscaleproject.env.csm.core.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import eu.cloudscaleproject.env.csm.core.CorePackage;
import eu.cloudscaleproject.env.csm.core.Operation;
import eu.cloudscaleproject.env.csm.core.TypeEnum;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Operation</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.core.impl.OperationImpl#getMethodName <em>Method Name</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.core.impl.OperationImpl#getReturnValue <em>Return Value</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.core.impl.OperationImpl#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OperationImpl extends EntityImpl implements Operation {
	/**
	 * The default value of the '{@link #getMethodName() <em>Method Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getMethodName()
	 * @generated
	 * @ordered
	 */
	protected static final String METHOD_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMethodName() <em>Method Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getMethodName()
	 * @generated
	 * @ordered
	 */
	protected String methodName = METHOD_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getReturnValue() <em>Return Value</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getReturnValue()
	 * @generated
	 * @ordered
	 */
	protected static final TypeEnum RETURN_VALUE_EDEFAULT = TypeEnum.INT;

	/**
	 * The cached value of the '{@link #getReturnValue() <em>Return Value</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getReturnValue()
	 * @generated
	 * @ordered
	 */
	protected TypeEnum returnValue = RETURN_VALUE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<TypeEnum> parameters;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.OPERATION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethodName(String newMethodName) {
		String oldMethodName = methodName;
		methodName = newMethodName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.OPERATION__METHOD_NAME, oldMethodName, methodName));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public TypeEnum getReturnValue() {
		return returnValue;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturnValue(TypeEnum newReturnValue) {
		TypeEnum oldReturnValue = returnValue;
		returnValue = newReturnValue == null ? RETURN_VALUE_EDEFAULT : newReturnValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.OPERATION__RETURN_VALUE, oldReturnValue, returnValue));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TypeEnum> getParameters() {
		if (parameters == null) {
			parameters = new EDataTypeUniqueEList<TypeEnum>(TypeEnum.class, this, CorePackage.OPERATION__PARAMETERS);
		}
		return parameters;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CorePackage.OPERATION__METHOD_NAME:
				return getMethodName();
			case CorePackage.OPERATION__RETURN_VALUE:
				return getReturnValue();
			case CorePackage.OPERATION__PARAMETERS:
				return getParameters();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CorePackage.OPERATION__METHOD_NAME:
				setMethodName((String)newValue);
				return;
			case CorePackage.OPERATION__RETURN_VALUE:
				setReturnValue((TypeEnum)newValue);
				return;
			case CorePackage.OPERATION__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends TypeEnum>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CorePackage.OPERATION__METHOD_NAME:
				setMethodName(METHOD_NAME_EDEFAULT);
				return;
			case CorePackage.OPERATION__RETURN_VALUE:
				setReturnValue(RETURN_VALUE_EDEFAULT);
				return;
			case CorePackage.OPERATION__PARAMETERS:
				getParameters().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CorePackage.OPERATION__METHOD_NAME:
				return METHOD_NAME_EDEFAULT == null ? methodName != null : !METHOD_NAME_EDEFAULT.equals(methodName);
			case CorePackage.OPERATION__RETURN_VALUE:
				return returnValue != RETURN_VALUE_EDEFAULT;
			case CorePackage.OPERATION__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (methodName: ");
		result.append(methodName);
		result.append(", returnValue: ");
		result.append(returnValue);
		result.append(", parameters: ");
		result.append(parameters);
		result.append(')');
		return result.toString();
	}

} // OperationImpl
