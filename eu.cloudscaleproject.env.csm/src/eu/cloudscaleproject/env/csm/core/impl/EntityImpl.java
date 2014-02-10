/**
 */
package eu.cloudscaleproject.env.csm.core.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.cloudscaleproject.env.csm.core.CorePackage;
import eu.cloudscaleproject.env.csm.core.Entity;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Entity</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.core.impl.EntityImpl#getId <em>Id</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.core.impl.EntityImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.core.impl.EntityImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.core.impl.EntityImpl#getAvMap <em>Av Map</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.core.impl.EntityImpl#getAeMap <em>Ae Map</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class EntityImpl extends EObjectImpl implements Entity {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * This is true if the Id attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean idESet;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAvMap() <em>Av Map</em>}' map. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getAvMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, Object> avMap;

	/**
	 * The cached value of the '{@link #getAeMap() <em>Ae Map</em>}' map. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getAeMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, EObject> aeMap;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	protected EntityImpl() {
		super();
		this.id = EcoreUtil.generateUUID();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.ENTITY;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		boolean oldIdESet = idESet;
		idESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ENTITY__ID, oldId, id, !oldIdESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetId() {
		String oldId = id;
		boolean oldIdESet = idESet;
		id = ID_EDEFAULT;
		idESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CorePackage.ENTITY__ID, oldId, ID_EDEFAULT, oldIdESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetId() {
		return idESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ENTITY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ENTITY__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, Object> getAvMap() {
		if (avMap == null) {
			avMap = new EcoreEMap<String,Object>(CorePackage.Literals.AV_ENTRY, AVEntryImpl.class, this, CorePackage.ENTITY__AV_MAP);
		}
		return avMap;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, EObject> getAeMap() {
		if (aeMap == null) {
			aeMap = new EcoreEMap<String,EObject>(CorePackage.Literals.AE_ENTRY, AEEntryImpl.class, this, CorePackage.ENTITY__AE_MAP);
		}
		return aeMap;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CorePackage.ENTITY__AV_MAP:
				return ((InternalEList<?>)getAvMap()).basicRemove(otherEnd, msgs);
			case CorePackage.ENTITY__AE_MAP:
				return ((InternalEList<?>)getAeMap()).basicRemove(otherEnd, msgs);
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
			case CorePackage.ENTITY__ID:
				return getId();
			case CorePackage.ENTITY__NAME:
				return getName();
			case CorePackage.ENTITY__DESCRIPTION:
				return getDescription();
			case CorePackage.ENTITY__AV_MAP:
				if (coreType) return getAvMap();
				else return getAvMap().map();
			case CorePackage.ENTITY__AE_MAP:
				if (coreType) return getAeMap();
				else return getAeMap().map();
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
			case CorePackage.ENTITY__ID:
				setId((String)newValue);
				return;
			case CorePackage.ENTITY__NAME:
				setName((String)newValue);
				return;
			case CorePackage.ENTITY__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case CorePackage.ENTITY__AV_MAP:
				((EStructuralFeature.Setting)getAvMap()).set(newValue);
				return;
			case CorePackage.ENTITY__AE_MAP:
				((EStructuralFeature.Setting)getAeMap()).set(newValue);
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
			case CorePackage.ENTITY__ID:
				unsetId();
				return;
			case CorePackage.ENTITY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CorePackage.ENTITY__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case CorePackage.ENTITY__AV_MAP:
				getAvMap().clear();
				return;
			case CorePackage.ENTITY__AE_MAP:
				getAeMap().clear();
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
			case CorePackage.ENTITY__ID:
				return isSetId();
			case CorePackage.ENTITY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CorePackage.ENTITY__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case CorePackage.ENTITY__AV_MAP:
				return avMap != null && !avMap.isEmpty();
			case CorePackage.ENTITY__AE_MAP:
				return aeMap != null && !aeMap.isEmpty();
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
		result.append(" (id: ");
		if (idESet) result.append(id); else result.append("<unset>");
		result.append(", name: ");
		result.append(name);
		result.append(", description: ");
		result.append(description);
		result.append(')');
		return result.toString();
	}

} // EntityImpl
