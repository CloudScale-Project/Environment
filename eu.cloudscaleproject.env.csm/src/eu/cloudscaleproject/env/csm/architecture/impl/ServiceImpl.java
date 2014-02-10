/**
 */
package eu.cloudscaleproject.env.csm.architecture.impl;

import org.eclipse.emf.ecore.EClass;

import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.Service;
import eu.cloudscaleproject.env.csm.core.impl.EntityImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Service</b></em>'. <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class ServiceImpl extends EntityImpl implements Service {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArchitecturePackage.Literals.SERVICE;
	}

} // ServiceImpl
