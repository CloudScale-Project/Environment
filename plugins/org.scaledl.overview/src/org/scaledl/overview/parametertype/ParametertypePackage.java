/**
 */
package org.scaledl.overview.parametertype;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.scaledl.overview.core.CorePackage;

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
 * @see org.scaledl.overview.parametertype.ParametertypeFactory
 * @model kind="package"
 * @generated
 */
public interface ParametertypePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "parametertype";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org.scaledl.overview/ScaleDLOverviewComponentModel/DataType/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "parametertype";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ParametertypePackage eINSTANCE = org.scaledl.overview.parametertype.impl.ParametertypePackageImpl.init();

	/**
	 * The meta object id for the '{@link org.scaledl.overview.parametertype.impl.ParameterTypeImpl <em>Parameter Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.parametertype.impl.ParameterTypeImpl
	 * @see org.scaledl.overview.parametertype.impl.ParametertypePackageImpl#getParameterType()
	 * @generated
	 */
	int PARAMETER_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_TYPE__TYPES = 0;

	/**
	 * The number of structural features of the '<em>Parameter Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.parametertype.impl.ParameterImpl <em>Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.parametertype.impl.ParameterImpl
	 * @see org.scaledl.overview.parametertype.impl.ParametertypePackageImpl#getParameter()
	 * @generated
	 */
	int PARAMETER = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The number of structural features of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.parametertype.impl.PrimitiveParameterImpl <em>Primitive Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.parametertype.impl.PrimitiveParameterImpl
	 * @see org.scaledl.overview.parametertype.impl.ParametertypePackageImpl#getPrimitiveParameter()
	 * @generated
	 */
	int PRIMITIVE_PARAMETER = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_PARAMETER__ID = PARAMETER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_PARAMETER__NAME = PARAMETER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_PARAMETER__DESCRIPTION = PARAMETER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_PARAMETER__AV_MAP = PARAMETER__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_PARAMETER__AE_MAP = PARAMETER__AE_MAP;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_PARAMETER__TYPE = PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Primitive Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_PARAMETER_FEATURE_COUNT = PARAMETER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.parametertype.impl.CompositeParameterImpl <em>Composite Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.parametertype.impl.CompositeParameterImpl
	 * @see org.scaledl.overview.parametertype.impl.ParametertypePackageImpl#getCompositeParameter()
	 * @generated
	 */
	int COMPOSITE_PARAMETER = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_PARAMETER__ID = PARAMETER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_PARAMETER__NAME = PARAMETER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_PARAMETER__DESCRIPTION = PARAMETER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_PARAMETER__AV_MAP = PARAMETER__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_PARAMETER__AE_MAP = PARAMETER__AE_MAP;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_PARAMETER__PARAMETERS = PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_PARAMETER__EXTENDS = PARAMETER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Composite Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_PARAMETER_FEATURE_COUNT = PARAMETER_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.parametertype.impl.CollectionParameterImpl <em>Collection Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.parametertype.impl.CollectionParameterImpl
	 * @see org.scaledl.overview.parametertype.impl.ParametertypePackageImpl#getCollectionParameter()
	 * @generated
	 */
	int COLLECTION_PARAMETER = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_PARAMETER__ID = PARAMETER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_PARAMETER__NAME = PARAMETER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_PARAMETER__DESCRIPTION = PARAMETER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_PARAMETER__AV_MAP = PARAMETER__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_PARAMETER__AE_MAP = PARAMETER__AE_MAP;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_PARAMETER__PARAMETER = PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Collection Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_PARAMETER_FEATURE_COUNT = PARAMETER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.parametertype.TypeEnum <em>Type Enum</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.parametertype.TypeEnum
	 * @see org.scaledl.overview.parametertype.impl.ParametertypePackageImpl#getTypeEnum()
	 * @generated
	 */
	int TYPE_ENUM = 5;


	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.parametertype.ParameterType <em>Parameter Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter Type</em>'.
	 * @see org.scaledl.overview.parametertype.ParameterType
	 * @generated
	 */
	EClass getParameterType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.scaledl.overview.parametertype.ParameterType#getTypes <em>Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Types</em>'.
	 * @see org.scaledl.overview.parametertype.ParameterType#getTypes()
	 * @see #getParameterType()
	 * @generated
	 */
	EReference getParameterType_Types();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.parametertype.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter</em>'.
	 * @see org.scaledl.overview.parametertype.Parameter
	 * @generated
	 */
	EClass getParameter();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.parametertype.PrimitiveParameter <em>Primitive Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive Parameter</em>'.
	 * @see org.scaledl.overview.parametertype.PrimitiveParameter
	 * @generated
	 */
	EClass getPrimitiveParameter();

	/**
	 * Returns the meta object for the attribute '{@link org.scaledl.overview.parametertype.PrimitiveParameter#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.scaledl.overview.parametertype.PrimitiveParameter#getType()
	 * @see #getPrimitiveParameter()
	 * @generated
	 */
	EAttribute getPrimitiveParameter_Type();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.parametertype.CompositeParameter <em>Composite Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composite Parameter</em>'.
	 * @see org.scaledl.overview.parametertype.CompositeParameter
	 * @generated
	 */
	EClass getCompositeParameter();

	/**
	 * Returns the meta object for the reference list '{@link org.scaledl.overview.parametertype.CompositeParameter#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parameters</em>'.
	 * @see org.scaledl.overview.parametertype.CompositeParameter#getParameters()
	 * @see #getCompositeParameter()
	 * @generated
	 */
	EReference getCompositeParameter_Parameters();

	/**
	 * Returns the meta object for the reference '{@link org.scaledl.overview.parametertype.CompositeParameter#getExtends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Extends</em>'.
	 * @see org.scaledl.overview.parametertype.CompositeParameter#getExtends()
	 * @see #getCompositeParameter()
	 * @generated
	 */
	EReference getCompositeParameter_Extends();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.parametertype.CollectionParameter <em>Collection Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection Parameter</em>'.
	 * @see org.scaledl.overview.parametertype.CollectionParameter
	 * @generated
	 */
	EClass getCollectionParameter();

	/**
	 * Returns the meta object for the reference '{@link org.scaledl.overview.parametertype.CollectionParameter#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parameter</em>'.
	 * @see org.scaledl.overview.parametertype.CollectionParameter#getParameter()
	 * @see #getCollectionParameter()
	 * @generated
	 */
	EReference getCollectionParameter_Parameter();

	/**
	 * Returns the meta object for enum '{@link org.scaledl.overview.parametertype.TypeEnum <em>Type Enum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Type Enum</em>'.
	 * @see org.scaledl.overview.parametertype.TypeEnum
	 * @generated
	 */
	EEnum getTypeEnum();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ParametertypeFactory getParametertypeFactory();

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
		 * The meta object literal for the '{@link org.scaledl.overview.parametertype.impl.ParameterTypeImpl <em>Parameter Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.parametertype.impl.ParameterTypeImpl
		 * @see org.scaledl.overview.parametertype.impl.ParametertypePackageImpl#getParameterType()
		 * @generated
		 */
		EClass PARAMETER_TYPE = eINSTANCE.getParameterType();

		/**
		 * The meta object literal for the '<em><b>Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAMETER_TYPE__TYPES = eINSTANCE.getParameterType_Types();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.parametertype.impl.ParameterImpl <em>Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.parametertype.impl.ParameterImpl
		 * @see org.scaledl.overview.parametertype.impl.ParametertypePackageImpl#getParameter()
		 * @generated
		 */
		EClass PARAMETER = eINSTANCE.getParameter();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.parametertype.impl.PrimitiveParameterImpl <em>Primitive Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.parametertype.impl.PrimitiveParameterImpl
		 * @see org.scaledl.overview.parametertype.impl.ParametertypePackageImpl#getPrimitiveParameter()
		 * @generated
		 */
		EClass PRIMITIVE_PARAMETER = eINSTANCE.getPrimitiveParameter();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIMITIVE_PARAMETER__TYPE = eINSTANCE.getPrimitiveParameter_Type();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.parametertype.impl.CompositeParameterImpl <em>Composite Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.parametertype.impl.CompositeParameterImpl
		 * @see org.scaledl.overview.parametertype.impl.ParametertypePackageImpl#getCompositeParameter()
		 * @generated
		 */
		EClass COMPOSITE_PARAMETER = eINSTANCE.getCompositeParameter();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_PARAMETER__PARAMETERS = eINSTANCE.getCompositeParameter_Parameters();

		/**
		 * The meta object literal for the '<em><b>Extends</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_PARAMETER__EXTENDS = eINSTANCE.getCompositeParameter_Extends();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.parametertype.impl.CollectionParameterImpl <em>Collection Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.parametertype.impl.CollectionParameterImpl
		 * @see org.scaledl.overview.parametertype.impl.ParametertypePackageImpl#getCollectionParameter()
		 * @generated
		 */
		EClass COLLECTION_PARAMETER = eINSTANCE.getCollectionParameter();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLLECTION_PARAMETER__PARAMETER = eINSTANCE.getCollectionParameter_Parameter();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.parametertype.TypeEnum <em>Type Enum</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.parametertype.TypeEnum
		 * @see org.scaledl.overview.parametertype.impl.ParametertypePackageImpl#getTypeEnum()
		 * @generated
		 */
		EEnum TYPE_ENUM = eINSTANCE.getTypeEnum();

	}

} //ParametertypePackage
