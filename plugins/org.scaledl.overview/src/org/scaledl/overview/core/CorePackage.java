/**
 */
package org.scaledl.overview.core;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * This package contains several core classes that are used by classes from other two packages (i.e. Architecture and Definition package). It provides abstract base class Entity, used by all other classes, providing them id, name and description attributes. Additionally, the package provides business logic related classes; modules, operation interfaces and operations. 
 * <!-- end-model-doc -->
 * @see org.scaledl.overview.core.CoreFactory
 * @model kind="package"
 * @generated
 */
public interface CorePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "core";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org.scaledl.overview/ScaleDLOverviewComponentModel/Core/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "core";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CorePackage eINSTANCE = org.scaledl.overview.core.impl.CorePackageImpl.init();

	/**
	 * The meta object id for the '{@link org.scaledl.overview.core.impl.AVEntryImpl <em>AV Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.core.impl.AVEntryImpl
	 * @see org.scaledl.overview.core.impl.CorePackageImpl#getAVEntry()
	 * @generated
	 */
	int AV_ENTRY = 0;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AV_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AV_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>AV Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AV_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.core.impl.AEEntryImpl <em>AE Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.core.impl.AEEntryImpl
	 * @see org.scaledl.overview.core.impl.CorePackageImpl#getAEEntry()
	 * @generated
	 */
	int AE_ENTRY = 1;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AE_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AE_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>AE Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AE_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.core.impl.EntityImpl <em>Entity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.core.impl.EntityImpl
	 * @see org.scaledl.overview.core.impl.CorePackageImpl#getEntity()
	 * @generated
	 */
	int ENTITY = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__NAME = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__AV_MAP = 3;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__AE_MAP = 4;

	/**
	 * The number of structural features of the '<em>Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.core.MeasurementMetric_NOTUSED <em>Measurement Metric NOTUSED</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.core.MeasurementMetric_NOTUSED
	 * @see org.scaledl.overview.core.impl.CorePackageImpl#getMeasurementMetric_NOTUSED()
	 * @generated
	 */
	int MEASUREMENT_METRIC_NOTUSED = 3;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.core.StatisticType_NOTUSED <em>Statistic Type NOTUSED</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.core.StatisticType_NOTUSED
	 * @see org.scaledl.overview.core.impl.CorePackageImpl#getStatisticType_NOTUSED()
	 * @generated
	 */
	int STATISTIC_TYPE_NOTUSED = 4;


	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>AV Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AV Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyId="true" keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueDataType="org.eclipse.emf.ecore.EJavaObject" valueRequired="true"
	 * @generated
	 */
	EClass getAVEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getAVEntry()
	 * @generated
	 */
	EAttribute getAVEntry_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getAVEntry()
	 * @generated
	 */
	EAttribute getAVEntry_Value();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>AE Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AE Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyId="true" keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueType="org.eclipse.emf.ecore.EObject" valueRequired="true"
	 * @generated
	 */
	EClass getAEEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getAEEntry()
	 * @generated
	 */
	EAttribute getAEEntry_Key();

	/**
	 * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getAEEntry()
	 * @generated
	 */
	EReference getAEEntry_Value();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.core.Entity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity</em>'.
	 * @see org.scaledl.overview.core.Entity
	 * @generated
	 */
	EClass getEntity();

	/**
	 * Returns the meta object for the attribute '{@link org.scaledl.overview.core.Entity#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.scaledl.overview.core.Entity#getId()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.scaledl.overview.core.Entity#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.scaledl.overview.core.Entity#getName()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.scaledl.overview.core.Entity#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.scaledl.overview.core.Entity#getDescription()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_Description();

	/**
	 * Returns the meta object for the map '{@link org.scaledl.overview.core.Entity#getAvMap <em>Av Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Av Map</em>'.
	 * @see org.scaledl.overview.core.Entity#getAvMap()
	 * @see #getEntity()
	 * @generated
	 */
	EReference getEntity_AvMap();

	/**
	 * Returns the meta object for the map '{@link org.scaledl.overview.core.Entity#getAeMap <em>Ae Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Ae Map</em>'.
	 * @see org.scaledl.overview.core.Entity#getAeMap()
	 * @see #getEntity()
	 * @generated
	 */
	EReference getEntity_AeMap();

	/**
	 * Returns the meta object for enum '{@link org.scaledl.overview.core.MeasurementMetric_NOTUSED <em>Measurement Metric NOTUSED</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Measurement Metric NOTUSED</em>'.
	 * @see org.scaledl.overview.core.MeasurementMetric_NOTUSED
	 * @generated
	 */
	EEnum getMeasurementMetric_NOTUSED();

	/**
	 * Returns the meta object for enum '{@link org.scaledl.overview.core.StatisticType_NOTUSED <em>Statistic Type NOTUSED</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Statistic Type NOTUSED</em>'.
	 * @see org.scaledl.overview.core.StatisticType_NOTUSED
	 * @generated
	 */
	EEnum getStatisticType_NOTUSED();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CoreFactory getCoreFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.scaledl.overview.core.impl.AVEntryImpl <em>AV Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.core.impl.AVEntryImpl
		 * @see org.scaledl.overview.core.impl.CorePackageImpl#getAVEntry()
		 * @generated
		 */
		EClass AV_ENTRY = eINSTANCE.getAVEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AV_ENTRY__KEY = eINSTANCE.getAVEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AV_ENTRY__VALUE = eINSTANCE.getAVEntry_Value();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.core.impl.AEEntryImpl <em>AE Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.core.impl.AEEntryImpl
		 * @see org.scaledl.overview.core.impl.CorePackageImpl#getAEEntry()
		 * @generated
		 */
		EClass AE_ENTRY = eINSTANCE.getAEEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AE_ENTRY__KEY = eINSTANCE.getAEEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AE_ENTRY__VALUE = eINSTANCE.getAEEntry_Value();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.core.impl.EntityImpl <em>Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.core.impl.EntityImpl
		 * @see org.scaledl.overview.core.impl.CorePackageImpl#getEntity()
		 * @generated
		 */
		EClass ENTITY = eINSTANCE.getEntity();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY__ID = eINSTANCE.getEntity_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY__NAME = eINSTANCE.getEntity_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY__DESCRIPTION = eINSTANCE.getEntity_Description();

		/**
		 * The meta object literal for the '<em><b>Av Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY__AV_MAP = eINSTANCE.getEntity_AvMap();

		/**
		 * The meta object literal for the '<em><b>Ae Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY__AE_MAP = eINSTANCE.getEntity_AeMap();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.core.MeasurementMetric_NOTUSED <em>Measurement Metric NOTUSED</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.core.MeasurementMetric_NOTUSED
		 * @see org.scaledl.overview.core.impl.CorePackageImpl#getMeasurementMetric_NOTUSED()
		 * @generated
		 */
		EEnum MEASUREMENT_METRIC_NOTUSED = eINSTANCE.getMeasurementMetric_NOTUSED();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.core.StatisticType_NOTUSED <em>Statistic Type NOTUSED</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.core.StatisticType_NOTUSED
		 * @see org.scaledl.overview.core.impl.CorePackageImpl#getStatisticType_NOTUSED()
		 * @generated
		 */
		EEnum STATISTIC_TYPE_NOTUSED = eINSTANCE.getStatisticType_NOTUSED();

	}

} //CorePackage
