/**
 */
package org.scaledl.overview;

import org.eclipse.emf.ecore.EObject;
import org.scaledl.overview.architecture.Architecture;
import org.scaledl.overview.deployment.Deployment;
import org.scaledl.overview.parametertype.ParameterType;
import org.scaledl.overview.specification.SystemSpecification;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Overview</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The CSM is the single root element of the XMI file which is generated for the CSM model. It consist of two main sub-models; Architecture and SystemDefinition.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.Overview#getArchitecture <em>Architecture</em>}</li>
 *   <li>{@link org.scaledl.overview.Overview#getDeployment <em>Deployment</em>}</li>
 *   <li>{@link org.scaledl.overview.Overview#getDefinition <em>Definition</em>}</li>
 *   <li>{@link org.scaledl.overview.Overview#getDataTypes <em>Data Types</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.OverviewPackage#getOverview()
 * @model
 * @generated
 */
public interface Overview extends EObject {
	/**
	 * Returns the value of the '<em><b>Architecture</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Architecture</em>' containment reference.
	 * @see #setArchitecture(Architecture)
	 * @see org.scaledl.overview.OverviewPackage#getOverview_Architecture()
	 * @model containment="true"
	 * @generated
	 */
	Architecture getArchitecture();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.Overview#getArchitecture <em>Architecture</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Architecture</em>' containment reference.
	 * @see #getArchitecture()
	 * @generated
	 */
	void setArchitecture(Architecture value);

	/**
	 * Returns the value of the '<em><b>Deployment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deployment</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deployment</em>' containment reference.
	 * @see #setDeployment(Deployment)
	 * @see org.scaledl.overview.OverviewPackage#getOverview_Deployment()
	 * @model containment="true"
	 * @generated
	 */
	Deployment getDeployment();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.Overview#getDeployment <em>Deployment</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deployment</em>' containment reference.
	 * @see #getDeployment()
	 * @generated
	 */
	void setDeployment(Deployment value);

	/**
	 * Returns the value of the '<em><b>Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Definition</em>' containment reference.
	 * @see #setDefinition(SystemSpecification)
	 * @see org.scaledl.overview.OverviewPackage#getOverview_Definition()
	 * @model containment="true"
	 * @generated
	 */
	SystemSpecification getDefinition();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.Overview#getDefinition <em>Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Definition</em>' containment reference.
	 * @see #getDefinition()
	 * @generated
	 */
	void setDefinition(SystemSpecification value);

	/**
	 * Returns the value of the '<em><b>Data Types</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Types</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Types</em>' containment reference.
	 * @see #setDataTypes(ParameterType)
	 * @see org.scaledl.overview.OverviewPackage#getOverview_DataTypes()
	 * @model containment="true"
	 * @generated
	 */
	ParameterType getDataTypes();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.Overview#getDataTypes <em>Data Types</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Types</em>' containment reference.
	 * @see #getDataTypes()
	 * @generated
	 */
	void setDataTypes(ParameterType value);

} // Overview
