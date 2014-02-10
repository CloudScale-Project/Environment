/**
 */
package eu.cloudscaleproject.env.csm.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import eu.cloudscaleproject.env.csm.Csm;
import eu.cloudscaleproject.env.csm.CsmFactory;
import eu.cloudscaleproject.env.csm.CsmPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class CsmFactoryImpl extends EFactoryImpl implements CsmFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public static CsmFactory init() {
		try {
			CsmFactory theCsmFactory = (CsmFactory)EPackage.Registry.INSTANCE.getEFactory("http://eu.cloudscaleproject.env/CloudscaleComponentModel/1.0"); 
			if (theCsmFactory != null) {
				return theCsmFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CsmFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public CsmFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CsmPackage.CSM: return createCsm();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Csm createCsm() {
		CsmImpl csm = new CsmImpl();
		return csm;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public CsmPackage getCsmPackage() {
		return (CsmPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CsmPackage getPackage() {
		return CsmPackage.eINSTANCE;
	}

} // CsmFactoryImpl
