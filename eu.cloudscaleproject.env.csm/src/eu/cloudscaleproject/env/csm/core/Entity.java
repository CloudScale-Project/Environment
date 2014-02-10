/**
 */
package eu.cloudscaleproject.env.csm.core;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Entity</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Entity is a meta-class high up the CSM meta-class hierarchy and represents all entities of the CSM that have an id, name and description. Additionally the Entity contains key-value set, which can be used to extend the model with additional information.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.core.Entity#getId <em>Id</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.core.Entity#getName <em>Name</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.core.Entity#getDescription <em>Description</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.core.Entity#getAvMap <em>Av Map</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.core.Entity#getAeMap <em>Ae Map</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.core.CorePackage#getEntity()
 * @model abstract="true"
 * @generated
 */
public interface Entity extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #isSetId()
	 * @see #unsetId()
	 * @see #setId(String)
	 * @see eu.cloudscaleproject.env.csm.core.CorePackage#getEntity_Id()
	 * @model default="" unsettable="true" id="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.core.Entity#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #isSetId()
	 * @see #unsetId()
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Unsets the value of the '{@link eu.cloudscaleproject.env.csm.core.Entity#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isSetId()
	 * @see #getId()
	 * @see #setId(String)
	 * @generated
	 */
	void unsetId();

	/**
	 * Returns whether the value of the '{@link eu.cloudscaleproject.env.csm.core.Entity#getId <em>Id</em>}' attribute is set.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return whether the value of the '<em>Id</em>' attribute is set.
	 * @see #unsetId()
	 * @see #getId()
	 * @see #setId(String)
	 * @generated
	 */
	boolean isSetId();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see eu.cloudscaleproject.env.csm.core.CorePackage#getEntity_Name()
	 * @model default=""
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.core.Entity#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute. The
	 * default value is <code>""</code>. <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see eu.cloudscaleproject.env.csm.core.CorePackage#getEntity_Description()
	 * @model default=""
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.core.Entity#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Av Map</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.Object},
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Av Map</em>' map.
	 * @see eu.cloudscaleproject.env.csm.core.CorePackage#getEntity_AvMap()
	 * @model mapType="eu.cloudscaleproject.env.csm.core.AVEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EJavaObject>"
	 * @generated
	 */
	EMap<String, Object> getAvMap();

	/**
	 * Returns the value of the '<em><b>Ae Map</b></em>' map. The key is of type
	 * {@link java.lang.String}, and the value is of type
	 * {@link org.eclipse.emf.ecore.EObject}, <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Ae Map</em>' map.
	 * @see eu.cloudscaleproject.env.csm.core.CorePackage#getEntity_AeMap()
	 * @model mapType=
	 *        "eu.cloudscaleproject.env.csm.core.AEEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EObject>"
	 * @generated
	 */
	EMap<String, EObject> getAeMap();

} // Entity
