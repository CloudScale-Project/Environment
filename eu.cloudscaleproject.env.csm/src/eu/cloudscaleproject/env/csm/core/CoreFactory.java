/**
 */
package eu.cloudscaleproject.env.csm.core;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * @see eu.cloudscaleproject.env.csm.core.CorePackage
 * @generated
 */
public interface CoreFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	CoreFactory eINSTANCE = eu.cloudscaleproject.env.csm.core.impl.CoreFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Operation</em>'.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation</em>'.
	 * @generated
	 */
	Operation createOperation();

	/**
	 * Returns a new object of class '<em>Operation Interface</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Operation Interface</em>'.
	 * @generated
	 */
	OperationInterface createOperationInterface();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	CorePackage getCorePackage();

} // CoreFactory
