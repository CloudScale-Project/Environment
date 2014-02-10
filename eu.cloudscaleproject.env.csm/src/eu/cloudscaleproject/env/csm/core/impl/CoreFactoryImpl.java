/**
 */
package eu.cloudscaleproject.env.csm.core.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import eu.cloudscaleproject.env.csm.core.CoreFactory;
import eu.cloudscaleproject.env.csm.core.CorePackage;
import eu.cloudscaleproject.env.csm.core.Operation;
import eu.cloudscaleproject.env.csm.core.OperationInterface;
import eu.cloudscaleproject.env.csm.core.TypeEnum;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class CoreFactoryImpl extends EFactoryImpl implements CoreFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public static CoreFactory init() {
		try {
			CoreFactory theCoreFactory = (CoreFactory)EPackage.Registry.INSTANCE.getEFactory("http://eu.cloudscaleproject.env/CloudscaleComponentModel/Core/1.0"); 
			if (theCoreFactory != null) {
				return theCoreFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CoreFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public CoreFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CorePackage.AV_ENTRY: return (EObject)createAVEntry();
			case CorePackage.AE_ENTRY: return (EObject)createAEEntry();
			case CorePackage.OPERATION: return createOperation();
			case CorePackage.OPERATION_INTERFACE: return createOperationInterface();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case CorePackage.TYPE_ENUM:
				return createTypeEnumFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case CorePackage.TYPE_ENUM:
				return convertTypeEnumToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, EObject> createAEEntry() {
		AEEntryImpl aeEntry = new AEEntryImpl();
		return aeEntry;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, Object> createAVEntry() {
		AVEntryImpl avEntry = new AVEntryImpl();
		return avEntry;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Operation createOperation() {
		OperationImpl operation = new OperationImpl();
		return operation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public OperationInterface createOperationInterface() {
		OperationInterfaceImpl operationInterface = new OperationInterfaceImpl();
		return operationInterface;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public TypeEnum createTypeEnumFromString(EDataType eDataType,
			String initialValue) {
		TypeEnum result = TypeEnum.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeEnumToString(EDataType eDataType,
			Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public CorePackage getCorePackage() {
		return (CorePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CorePackage getPackage() {
		return CorePackage.eINSTANCE;
	}

} // CoreFactoryImpl
