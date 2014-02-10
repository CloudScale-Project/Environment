/**
 */
package eu.cloudscaleproject.env.csm.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import eu.cloudscaleproject.env.csm.Csm;
import eu.cloudscaleproject.env.csm.CsmPackage;
import eu.cloudscaleproject.env.csm.architecture.Architecture;
import eu.cloudscaleproject.env.csm.definition.SystemDefinition;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Csm</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.impl.CsmImpl#getArchitecture <em>Architecture</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.impl.CsmImpl#getDefinition <em>Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CsmImpl extends EObjectImpl implements Csm {
	/**
	 * The cached value of the '{@link #getArchitecture() <em>Architecture</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getArchitecture()
	 * @generated
	 * @ordered
	 */
	protected Architecture architecture;

	/**
	 * The cached value of the '{@link #getDefinition() <em>Definition</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDefinition()
	 * @generated
	 * @ordered
	 */
	protected SystemDefinition definition;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected CsmImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CsmPackage.Literals.CSM;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Architecture getArchitecture() {
		return architecture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetArchitecture(Architecture newArchitecture,
			NotificationChain msgs) {
		Architecture oldArchitecture = architecture;
		architecture = newArchitecture;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CsmPackage.CSM__ARCHITECTURE, oldArchitecture, newArchitecture);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setArchitecture(Architecture newArchitecture) {
		if (newArchitecture != architecture) {
			NotificationChain msgs = null;
			if (architecture != null)
				msgs = ((InternalEObject)architecture).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CsmPackage.CSM__ARCHITECTURE, null, msgs);
			if (newArchitecture != null)
				msgs = ((InternalEObject)newArchitecture).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CsmPackage.CSM__ARCHITECTURE, null, msgs);
			msgs = basicSetArchitecture(newArchitecture, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CsmPackage.CSM__ARCHITECTURE, newArchitecture, newArchitecture));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public SystemDefinition getDefinition() {
		return definition;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefinition(SystemDefinition newDefinition,
			NotificationChain msgs) {
		SystemDefinition oldDefinition = definition;
		definition = newDefinition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CsmPackage.CSM__DEFINITION, oldDefinition, newDefinition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefinition(SystemDefinition newDefinition) {
		if (newDefinition != definition) {
			NotificationChain msgs = null;
			if (definition != null)
				msgs = ((InternalEObject)definition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CsmPackage.CSM__DEFINITION, null, msgs);
			if (newDefinition != null)
				msgs = ((InternalEObject)newDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CsmPackage.CSM__DEFINITION, null, msgs);
			msgs = basicSetDefinition(newDefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CsmPackage.CSM__DEFINITION, newDefinition, newDefinition));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CsmPackage.CSM__ARCHITECTURE:
				return basicSetArchitecture(null, msgs);
			case CsmPackage.CSM__DEFINITION:
				return basicSetDefinition(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CsmPackage.CSM__ARCHITECTURE:
				return getArchitecture();
			case CsmPackage.CSM__DEFINITION:
				return getDefinition();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CsmPackage.CSM__ARCHITECTURE:
				setArchitecture((Architecture)newValue);
				return;
			case CsmPackage.CSM__DEFINITION:
				setDefinition((SystemDefinition)newValue);
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
			case CsmPackage.CSM__ARCHITECTURE:
				setArchitecture((Architecture)null);
				return;
			case CsmPackage.CSM__DEFINITION:
				setDefinition((SystemDefinition)null);
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
			case CsmPackage.CSM__ARCHITECTURE:
				return architecture != null;
			case CsmPackage.CSM__DEFINITION:
				return definition != null;
		}
		return super.eIsSet(featureID);
	}

} // CsmImpl
