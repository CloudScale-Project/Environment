/**
 */
package eu.cloudscaleproject.env.method.common.method;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.Node#getId <em>Id</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.Node#getName <em>Name</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.Node#getDescription <em>Description</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.Node#getTooltip <em>Tooltip</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.Node#getColorText <em>Color Text</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.Node#getColorForeground <em>Color Foreground</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.Node#getColorBackground <em>Color Background</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.Node#getImage <em>Image</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.Node#isLayout <em>Layout</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.Node#getCommandId <em>Command Id</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.Node#getCommandParam <em>Command Param</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getNode()
 * @model
 * @generated
 */
public interface Node extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getNode_Id()
	 * @model default="" id="true" required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.method.common.method.Node#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The default value is <code>"name"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getNode_Name()
	 * @model default="name"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.method.common.method.Node#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getNode_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.method.common.method.Node#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Tooltip</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tooltip</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tooltip</em>' attribute.
	 * @see #setTooltip(String)
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getNode_Tooltip()
	 * @model default=""
	 * @generated
	 */
	String getTooltip();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.method.common.method.Node#getTooltip <em>Tooltip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tooltip</em>' attribute.
	 * @see #getTooltip()
	 * @generated
	 */
	void setTooltip(String value);

	/**
	 * Returns the value of the '<em><b>Color Text</b></em>' attribute.
	 * The default value is <code>"000000"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Color Text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Color Text</em>' attribute.
	 * @see #setColorText(String)
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getNode_ColorText()
	 * @model default="000000"
	 * @generated
	 */
	String getColorText();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.method.common.method.Node#getColorText <em>Color Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color Text</em>' attribute.
	 * @see #getColorText()
	 * @generated
	 */
	void setColorText(String value);

	/**
	 * Returns the value of the '<em><b>Color Foreground</b></em>' attribute.
	 * The default value is <code>"000000FF"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Color Foreground</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Color Foreground</em>' attribute.
	 * @see #setColorForeground(String)
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getNode_ColorForeground()
	 * @model default="000000FF"
	 * @generated
	 */
	String getColorForeground();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.method.common.method.Node#getColorForeground <em>Color Foreground</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color Foreground</em>' attribute.
	 * @see #getColorForeground()
	 * @generated
	 */
	void setColorForeground(String value);

	/**
	 * Returns the value of the '<em><b>Color Background</b></em>' attribute.
	 * The default value is <code>"FFFFFFFF"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Color Background</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Color Background</em>' attribute.
	 * @see #setColorBackground(String)
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getNode_ColorBackground()
	 * @model default="FFFFFFFF"
	 * @generated
	 */
	String getColorBackground();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.method.common.method.Node#getColorBackground <em>Color Background</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color Background</em>' attribute.
	 * @see #getColorBackground()
	 * @generated
	 */
	void setColorBackground(String value);

	/**
	 * Returns the value of the '<em><b>Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Image</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Image</em>' attribute.
	 * @see #setImage(String)
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getNode_Image()
	 * @model
	 * @generated
	 */
	String getImage();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.method.common.method.Node#getImage <em>Image</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Image</em>' attribute.
	 * @see #getImage()
	 * @generated
	 */
	void setImage(String value);

	/**
	 * Returns the value of the '<em><b>Layout</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Layout</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Layout</em>' attribute.
	 * @see #setLayout(boolean)
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getNode_Layout()
	 * @model default="true"
	 * @generated
	 */
	boolean isLayout();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.method.common.method.Node#isLayout <em>Layout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Layout</em>' attribute.
	 * @see #isLayout()
	 * @generated
	 */
	void setLayout(boolean value);

	/**
	 * Returns the value of the '<em><b>Command Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Command Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Command Id</em>' attribute.
	 * @see #setCommandId(String)
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getNode_CommandId()
	 * @model
	 * @generated
	 */
	String getCommandId();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.method.common.method.Node#getCommandId <em>Command Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Command Id</em>' attribute.
	 * @see #getCommandId()
	 * @generated
	 */
	void setCommandId(String value);

	/**
	 * Returns the value of the '<em><b>Command Param</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Command Param</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Command Param</em>' attribute list.
	 * @see eu.cloudscaleproject.env.method.common.method.MethodPackage#getNode_CommandParam()
	 * @model
	 * @generated
	 */
	EList<String> getCommandParam();

} // Node
