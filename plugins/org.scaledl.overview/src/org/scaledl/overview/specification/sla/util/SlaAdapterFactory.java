/**
 */
package org.scaledl.overview.specification.sla.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.scaledl.overview.specification.sla.PriceModel;
import org.scaledl.overview.specification.sla.Sla;
import org.scaledl.overview.specification.sla.SlaPackage;
import org.scaledl.overview.specification.sla.Slo;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.scaledl.overview.specification.sla.SlaPackage
 * @generated
 */
public class SlaAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SlaPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SlaAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = SlaPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SlaSwitch<Adapter> modelSwitch =
		new SlaSwitch<Adapter>() {
			@Override
			public Adapter caseSla(Sla object) {
				return createSlaAdapter();
			}
			@Override
			public Adapter casePriceModel(PriceModel object) {
				return createPriceModelAdapter();
			}
			@Override
			public Adapter caseSlo(Slo object) {
				return createSloAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.sla.Sla <em>Sla</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.sla.Sla
	 * @generated
	 */
	public Adapter createSlaAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.sla.PriceModel <em>Price Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.sla.PriceModel
	 * @generated
	 */
	public Adapter createPriceModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.sla.Slo <em>Slo</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.sla.Slo
	 * @generated
	 */
	public Adapter createSloAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //SlaAdapterFactory
