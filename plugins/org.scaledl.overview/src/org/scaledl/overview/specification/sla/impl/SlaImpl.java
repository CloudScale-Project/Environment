/**
 */
package org.scaledl.overview.specification.sla.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.scaledl.overview.specification.sla.PriceModel;
import org.scaledl.overview.specification.sla.Sla;
import org.scaledl.overview.specification.sla.SlaPackage;
import org.scaledl.overview.specification.sla.Slo;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sla</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.specification.sla.impl.SlaImpl#getPriceModel <em>Price Model</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.sla.impl.SlaImpl#getSlo <em>Slo</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SlaImpl extends EObjectImpl implements Sla {
	/**
	 * The cached value of the '{@link #getPriceModel() <em>Price Model</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriceModel()
	 * @generated
	 * @ordered
	 */
	protected PriceModel priceModel;

	/**
	 * The cached value of the '{@link #getSlo() <em>Slo</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSlo()
	 * @generated
	 * @ordered
	 */
	protected Slo slo;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SlaImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SlaPackage.Literals.SLA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PriceModel getPriceModel() {
		return priceModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPriceModel(PriceModel newPriceModel, NotificationChain msgs) {
		PriceModel oldPriceModel = priceModel;
		priceModel = newPriceModel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SlaPackage.SLA__PRICE_MODEL, oldPriceModel, newPriceModel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPriceModel(PriceModel newPriceModel) {
		if (newPriceModel != priceModel) {
			NotificationChain msgs = null;
			if (priceModel != null)
				msgs = ((InternalEObject)priceModel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SlaPackage.SLA__PRICE_MODEL, null, msgs);
			if (newPriceModel != null)
				msgs = ((InternalEObject)newPriceModel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SlaPackage.SLA__PRICE_MODEL, null, msgs);
			msgs = basicSetPriceModel(newPriceModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SlaPackage.SLA__PRICE_MODEL, newPriceModel, newPriceModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Slo getSlo() {
		return slo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSlo(Slo newSlo, NotificationChain msgs) {
		Slo oldSlo = slo;
		slo = newSlo;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SlaPackage.SLA__SLO, oldSlo, newSlo);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSlo(Slo newSlo) {
		if (newSlo != slo) {
			NotificationChain msgs = null;
			if (slo != null)
				msgs = ((InternalEObject)slo).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SlaPackage.SLA__SLO, null, msgs);
			if (newSlo != null)
				msgs = ((InternalEObject)newSlo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SlaPackage.SLA__SLO, null, msgs);
			msgs = basicSetSlo(newSlo, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SlaPackage.SLA__SLO, newSlo, newSlo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SlaPackage.SLA__PRICE_MODEL:
				return basicSetPriceModel(null, msgs);
			case SlaPackage.SLA__SLO:
				return basicSetSlo(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SlaPackage.SLA__PRICE_MODEL:
				return getPriceModel();
			case SlaPackage.SLA__SLO:
				return getSlo();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SlaPackage.SLA__PRICE_MODEL:
				setPriceModel((PriceModel)newValue);
				return;
			case SlaPackage.SLA__SLO:
				setSlo((Slo)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case SlaPackage.SLA__PRICE_MODEL:
				setPriceModel((PriceModel)null);
				return;
			case SlaPackage.SLA__SLO:
				setSlo((Slo)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SlaPackage.SLA__PRICE_MODEL:
				return priceModel != null;
			case SlaPackage.SLA__SLO:
				return slo != null;
		}
		return super.eIsSet(featureID);
	}

} //SlaImpl
