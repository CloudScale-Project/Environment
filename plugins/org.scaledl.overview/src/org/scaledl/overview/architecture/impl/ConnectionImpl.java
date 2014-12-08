/**
 */
package org.scaledl.overview.architecture.impl;

import org.eclipse.emf.ecore.EClass;

import org.scaledl.overview.architecture.ArchitecturePackage;
import org.scaledl.overview.architecture.Connection;

import org.scaledl.overview.core.impl.EntityImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class ConnectionImpl extends EntityImpl implements Connection {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArchitecturePackage.Literals.CONNECTION;
	}

} //ConnectionImpl
