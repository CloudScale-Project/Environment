/**
 */
package eu.cloudscaleproject.env.csm;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * @see eu.cloudscaleproject.env.csm.CsmPackage
 * @generated
 */
public interface CsmFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	CsmFactory eINSTANCE = eu.cloudscaleproject.env.csm.impl.CsmFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Csm</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Csm</em>'.
	 * @generated
	 */
	Csm createCsm();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	CsmPackage getCsmPackage();

} // CsmFactory
