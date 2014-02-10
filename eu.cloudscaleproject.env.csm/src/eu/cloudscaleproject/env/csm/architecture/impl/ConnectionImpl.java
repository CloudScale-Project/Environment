/**
 */
package eu.cloudscaleproject.env.csm.architecture.impl;

import org.eclipse.emf.ecore.EClass;

import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.Connection;
import eu.cloudscaleproject.env.csm.core.impl.EntityImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Connection</b></em>'. <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class ConnectionImpl extends EntityImpl implements Connection {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArchitecturePackage.Literals.CONNECTION;
	}

} // ConnectionImpl
