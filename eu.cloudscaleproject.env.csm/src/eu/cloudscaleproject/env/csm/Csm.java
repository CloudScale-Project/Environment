/**
 */
package eu.cloudscaleproject.env.csm;

import org.eclipse.emf.ecore.EObject;

import eu.cloudscaleproject.env.csm.architecture.Architecture;
import eu.cloudscaleproject.env.csm.definition.SystemDefinition;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Csm</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The CSM is the single root element of the XMI file which is generated for the CSM model. It consist of two main sub-models; Architecture and SystemDefinition.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.Csm#getArchitecture <em>Architecture</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.Csm#getDefinition <em>Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.CsmPackage#getCsm()
 * @model
 * @generated
 */
public interface Csm extends EObject {
	/**
	 * Returns the value of the '<em><b>Architecture</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Architecture</em>' containment reference.
	 * @see #setArchitecture(Architecture)
	 * @see eu.cloudscaleproject.env.csm.CsmPackage#getCsm_Architecture()
	 * @model containment="true"
	 * @generated
	 */
	Architecture getArchitecture();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.Csm#getArchitecture <em>Architecture</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Architecture</em>' containment reference.
	 * @see #getArchitecture()
	 * @generated
	 */
	void setArchitecture(Architecture value);

	/**
	 * Returns the value of the '<em><b>Definition</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Definition</em>' containment reference.
	 * @see #setDefinition(SystemDefinition)
	 * @see eu.cloudscaleproject.env.csm.CsmPackage#getCsm_Definition()
	 * @model containment="true"
	 * @generated
	 */
	SystemDefinition getDefinition();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.Csm#getDefinition <em>Definition</em>}' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Definition</em>' containment reference.
	 * @see #getDefinition()
	 * @generated
	 */
	void setDefinition(SystemDefinition value);

} // Csm
