/**
 */
package eu.cloudscaleproject.env.method.common.method;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see eu.cloudscaleproject.env.method.common.method.MethodFactory
 * @model kind="package"
 * @generated
 */
public interface MethodPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "method";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://eu.cloudscale.env.method/MethodComponentModel/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "method";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MethodPackage eINSTANCE = eu.cloudscaleproject.env.method.common.method.impl.MethodPackageImpl.init();

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.method.common.method.impl.MethodImpl <em>Method</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.method.common.method.impl.MethodImpl
	 * @see eu.cloudscaleproject.env.method.common.method.impl.MethodPackageImpl#getMethod()
	 * @generated
	 */
	int METHOD = 0;

	/**
	 * The feature id for the '<em><b>Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__LINKS = 0;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__NODES = 1;

	/**
	 * The number of structural features of the '<em>Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.method.common.method.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.method.common.method.impl.NodeImpl
	 * @see eu.cloudscaleproject.env.method.common.method.impl.MethodPackageImpl#getNode()
	 * @generated
	 */
	int NODE = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__TOOLTIP = 3;

	/**
	 * The feature id for the '<em><b>Color Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__COLOR_TEXT = 4;

	/**
	 * The feature id for the '<em><b>Color Foreground</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__COLOR_FOREGROUND = 5;

	/**
	 * The feature id for the '<em><b>Color Background</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__COLOR_BACKGROUND = 6;

	/**
	 * The feature id for the '<em><b>Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__IMAGE = 7;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__LAYOUT = 8;

	/**
	 * The feature id for the '<em><b>Command Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__COMMAND_ID = 9;

	/**
	 * The feature id for the '<em><b>Command Param</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__COMMAND_PARAM = 10;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = 11;

	/**
	 * The number of operations of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.method.common.method.LinkedObject <em>Linked Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.method.common.method.LinkedObject
	 * @see eu.cloudscaleproject.env.method.common.method.impl.MethodPackageImpl#getLinkedObject()
	 * @generated
	 */
	int LINKED_OBJECT = 2;

	/**
	 * The feature id for the '<em><b>Next</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINKED_OBJECT__NEXT = 0;

	/**
	 * The feature id for the '<em><b>Previous</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINKED_OBJECT__PREVIOUS = 1;

	/**
	 * The number of structural features of the '<em>Linked Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINKED_OBJECT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Linked Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINKED_OBJECT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.method.common.method.impl.ContainerImpl <em>Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.method.common.method.impl.ContainerImpl
	 * @see eu.cloudscaleproject.env.method.common.method.impl.MethodPackageImpl#getContainer()
	 * @generated
	 */
	int CONTAINER = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ID = NODE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__NAME = NODE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__DESCRIPTION = NODE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__TOOLTIP = NODE__TOOLTIP;

	/**
	 * The feature id for the '<em><b>Color Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__COLOR_TEXT = NODE__COLOR_TEXT;

	/**
	 * The feature id for the '<em><b>Color Foreground</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__COLOR_FOREGROUND = NODE__COLOR_FOREGROUND;

	/**
	 * The feature id for the '<em><b>Color Background</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__COLOR_BACKGROUND = NODE__COLOR_BACKGROUND;

	/**
	 * The feature id for the '<em><b>Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__IMAGE = NODE__IMAGE;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__LAYOUT = NODE__LAYOUT;

	/**
	 * The feature id for the '<em><b>Command Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__COMMAND_ID = NODE__COMMAND_ID;

	/**
	 * The feature id for the '<em><b>Command Param</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__COMMAND_PARAM = NODE__COMMAND_PARAM;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__CHILDREN = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Commands</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__COMMANDS = NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_FEATURE_COUNT = NODE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_OPERATION_COUNT = NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.method.common.method.impl.StatusNodeImpl <em>Status Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.method.common.method.impl.StatusNodeImpl
	 * @see eu.cloudscaleproject.env.method.common.method.impl.MethodPackageImpl#getStatusNode()
	 * @generated
	 */
	int STATUS_NODE = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_NODE__ID = NODE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_NODE__NAME = NODE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_NODE__DESCRIPTION = NODE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_NODE__TOOLTIP = NODE__TOOLTIP;

	/**
	 * The feature id for the '<em><b>Color Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_NODE__COLOR_TEXT = NODE__COLOR_TEXT;

	/**
	 * The feature id for the '<em><b>Color Foreground</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_NODE__COLOR_FOREGROUND = NODE__COLOR_FOREGROUND;

	/**
	 * The feature id for the '<em><b>Color Background</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_NODE__COLOR_BACKGROUND = NODE__COLOR_BACKGROUND;

	/**
	 * The feature id for the '<em><b>Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_NODE__IMAGE = NODE__IMAGE;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_NODE__LAYOUT = NODE__LAYOUT;

	/**
	 * The feature id for the '<em><b>Command Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_NODE__COMMAND_ID = NODE__COMMAND_ID;

	/**
	 * The feature id for the '<em><b>Command Param</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_NODE__COMMAND_PARAM = NODE__COMMAND_PARAM;

	/**
	 * The feature id for the '<em><b>Done</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_NODE__DONE = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dirty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_NODE__DIRTY = NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Warnings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_NODE__WARNINGS = NODE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Status Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_NODE_FEATURE_COUNT = NODE_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Status Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_NODE_OPERATION_COUNT = NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.method.common.method.impl.ActionImpl <em>Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.method.common.method.impl.ActionImpl
	 * @see eu.cloudscaleproject.env.method.common.method.impl.MethodPackageImpl#getAction()
	 * @generated
	 */
	int ACTION = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ID = STATUS_NODE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__NAME = STATUS_NODE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__DESCRIPTION = STATUS_NODE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__TOOLTIP = STATUS_NODE__TOOLTIP;

	/**
	 * The feature id for the '<em><b>Color Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__COLOR_TEXT = STATUS_NODE__COLOR_TEXT;

	/**
	 * The feature id for the '<em><b>Color Foreground</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__COLOR_FOREGROUND = STATUS_NODE__COLOR_FOREGROUND;

	/**
	 * The feature id for the '<em><b>Color Background</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__COLOR_BACKGROUND = STATUS_NODE__COLOR_BACKGROUND;

	/**
	 * The feature id for the '<em><b>Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__IMAGE = STATUS_NODE__IMAGE;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__LAYOUT = STATUS_NODE__LAYOUT;

	/**
	 * The feature id for the '<em><b>Command Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__COMMAND_ID = STATUS_NODE__COMMAND_ID;

	/**
	 * The feature id for the '<em><b>Command Param</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__COMMAND_PARAM = STATUS_NODE__COMMAND_PARAM;

	/**
	 * The feature id for the '<em><b>Done</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__DONE = STATUS_NODE__DONE;

	/**
	 * The feature id for the '<em><b>Dirty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__DIRTY = STATUS_NODE__DIRTY;

	/**
	 * The feature id for the '<em><b>Warnings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__WARNINGS = STATUS_NODE__WARNINGS;

	/**
	 * The feature id for the '<em><b>Next</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__NEXT = STATUS_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Previous</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__PREVIOUS = STATUS_NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FEATURE_COUNT = STATUS_NODE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_OPERATION_COUNT = STATUS_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.method.common.method.impl.SectionImpl <em>Section</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.method.common.method.impl.SectionImpl
	 * @see eu.cloudscaleproject.env.method.common.method.impl.MethodPackageImpl#getSection()
	 * @generated
	 */
	int SECTION = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ID = STATUS_NODE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__NAME = STATUS_NODE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__DESCRIPTION = STATUS_NODE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__TOOLTIP = STATUS_NODE__TOOLTIP;

	/**
	 * The feature id for the '<em><b>Color Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__COLOR_TEXT = STATUS_NODE__COLOR_TEXT;

	/**
	 * The feature id for the '<em><b>Color Foreground</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__COLOR_FOREGROUND = STATUS_NODE__COLOR_FOREGROUND;

	/**
	 * The feature id for the '<em><b>Color Background</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__COLOR_BACKGROUND = STATUS_NODE__COLOR_BACKGROUND;

	/**
	 * The feature id for the '<em><b>Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__IMAGE = STATUS_NODE__IMAGE;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__LAYOUT = STATUS_NODE__LAYOUT;

	/**
	 * The feature id for the '<em><b>Command Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__COMMAND_ID = STATUS_NODE__COMMAND_ID;

	/**
	 * The feature id for the '<em><b>Command Param</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__COMMAND_PARAM = STATUS_NODE__COMMAND_PARAM;

	/**
	 * The feature id for the '<em><b>Done</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__DONE = STATUS_NODE__DONE;

	/**
	 * The feature id for the '<em><b>Dirty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__DIRTY = STATUS_NODE__DIRTY;

	/**
	 * The feature id for the '<em><b>Warnings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__WARNINGS = STATUS_NODE__WARNINGS;

	/**
	 * The feature id for the '<em><b>Next</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__NEXT = STATUS_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Previous</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__PREVIOUS = STATUS_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__VALID = STATUS_NODE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>In Progress</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__IN_PROGRESS = STATUS_NODE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Commands</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__COMMANDS = STATUS_NODE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Requirements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__REQUIREMENTS = STATUS_NODE_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Section</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION_FEATURE_COUNT = STATUS_NODE_FEATURE_COUNT + 6;

	/**
	 * The number of operations of the '<em>Section</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION_OPERATION_COUNT = STATUS_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.method.common.method.impl.RequirementImpl <em>Requirement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.method.common.method.impl.RequirementImpl
	 * @see eu.cloudscaleproject.env.method.common.method.impl.MethodPackageImpl#getRequirement()
	 * @generated
	 */
	int REQUIREMENT = 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__ID = STATUS_NODE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__NAME = STATUS_NODE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__DESCRIPTION = STATUS_NODE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__TOOLTIP = STATUS_NODE__TOOLTIP;

	/**
	 * The feature id for the '<em><b>Color Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__COLOR_TEXT = STATUS_NODE__COLOR_TEXT;

	/**
	 * The feature id for the '<em><b>Color Foreground</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__COLOR_FOREGROUND = STATUS_NODE__COLOR_FOREGROUND;

	/**
	 * The feature id for the '<em><b>Color Background</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__COLOR_BACKGROUND = STATUS_NODE__COLOR_BACKGROUND;

	/**
	 * The feature id for the '<em><b>Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__IMAGE = STATUS_NODE__IMAGE;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__LAYOUT = STATUS_NODE__LAYOUT;

	/**
	 * The feature id for the '<em><b>Command Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__COMMAND_ID = STATUS_NODE__COMMAND_ID;

	/**
	 * The feature id for the '<em><b>Command Param</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__COMMAND_PARAM = STATUS_NODE__COMMAND_PARAM;

	/**
	 * The feature id for the '<em><b>Done</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__DONE = STATUS_NODE__DONE;

	/**
	 * The feature id for the '<em><b>Dirty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__DIRTY = STATUS_NODE__DIRTY;

	/**
	 * The feature id for the '<em><b>Warnings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__WARNINGS = STATUS_NODE__WARNINGS;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__POSITION = STATUS_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Requirement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_FEATURE_COUNT = STATUS_NODE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Requirement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_OPERATION_COUNT = STATUS_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.method.common.method.impl.WarningImpl <em>Warning</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.method.common.method.impl.WarningImpl
	 * @see eu.cloudscaleproject.env.method.common.method.impl.MethodPackageImpl#getWarning()
	 * @generated
	 */
	int WARNING = 8;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WARNING__ID = 0;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WARNING__MESSAGE = 1;

	/**
	 * The feature id for the '<em><b>Commands</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WARNING__COMMANDS = 2;

	/**
	 * The number of structural features of the '<em>Warning</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WARNING_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Warning</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WARNING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.method.common.method.impl.CommandImpl <em>Command</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.method.common.method.impl.CommandImpl
	 * @see eu.cloudscaleproject.env.method.common.method.impl.MethodPackageImpl#getCommand()
	 * @generated
	 */
	int COMMAND = 9;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__ID = NODE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__NAME = NODE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__DESCRIPTION = NODE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__TOOLTIP = NODE__TOOLTIP;

	/**
	 * The feature id for the '<em><b>Color Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__COLOR_TEXT = NODE__COLOR_TEXT;

	/**
	 * The feature id for the '<em><b>Color Foreground</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__COLOR_FOREGROUND = NODE__COLOR_FOREGROUND;

	/**
	 * The feature id for the '<em><b>Color Background</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__COLOR_BACKGROUND = NODE__COLOR_BACKGROUND;

	/**
	 * The feature id for the '<em><b>Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__IMAGE = NODE__IMAGE;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__LAYOUT = NODE__LAYOUT;

	/**
	 * The feature id for the '<em><b>Command Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__COMMAND_ID = NODE__COMMAND_ID;

	/**
	 * The feature id for the '<em><b>Command Param</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__COMMAND_PARAM = NODE__COMMAND_PARAM;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__POSITION = NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Command</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_FEATURE_COUNT = NODE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Command</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_OPERATION_COUNT = NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.method.common.method.impl.LinkImpl <em>Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.method.common.method.impl.LinkImpl
	 * @see eu.cloudscaleproject.env.method.common.method.impl.MethodPackageImpl#getLink()
	 * @generated
	 */
	int LINK = 10;

	/**
	 * The feature id for the '<em><b>Start</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__START = 0;

	/**
	 * The feature id for the '<em><b>End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__END = 1;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__REQUIRED = 2;

	/**
	 * The number of structural features of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_OPERATION_COUNT = 0;

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.method.common.method.Method <em>Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Method
	 * @generated
	 */
	EClass getMethod();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.cloudscaleproject.env.method.common.method.Method#getLinks <em>Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Links</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Method#getLinks()
	 * @see #getMethod()
	 * @generated
	 */
	EReference getMethod_Links();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.cloudscaleproject.env.method.common.method.Method#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Method#getNodes()
	 * @see #getMethod()
	 * @generated
	 */
	EReference getMethod_Nodes();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.method.common.method.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.method.common.method.Node#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Node#getId()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Id();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.method.common.method.Node#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Node#getName()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.method.common.method.Node#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Node#getDescription()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Description();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.method.common.method.Node#getTooltip <em>Tooltip</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tooltip</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Node#getTooltip()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Tooltip();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.method.common.method.Node#getColorText <em>Color Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color Text</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Node#getColorText()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_ColorText();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.method.common.method.Node#getColorForeground <em>Color Foreground</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color Foreground</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Node#getColorForeground()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_ColorForeground();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.method.common.method.Node#getColorBackground <em>Color Background</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color Background</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Node#getColorBackground()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_ColorBackground();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.method.common.method.Node#getImage <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Image</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Node#getImage()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Image();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.method.common.method.Node#isLayout <em>Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Layout</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Node#isLayout()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Layout();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.method.common.method.Node#getCommandId <em>Command Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Command Id</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Node#getCommandId()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_CommandId();

	/**
	 * Returns the meta object for the attribute list '{@link eu.cloudscaleproject.env.method.common.method.Node#getCommandParam <em>Command Param</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Command Param</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Node#getCommandParam()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_CommandParam();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.method.common.method.LinkedObject <em>Linked Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Linked Object</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.LinkedObject
	 * @generated
	 */
	EClass getLinkedObject();

	/**
	 * Returns the meta object for the reference list '{@link eu.cloudscaleproject.env.method.common.method.LinkedObject#getNext <em>Next</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Next</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.LinkedObject#getNext()
	 * @see #getLinkedObject()
	 * @generated
	 */
	EReference getLinkedObject_Next();

	/**
	 * Returns the meta object for the reference list '{@link eu.cloudscaleproject.env.method.common.method.LinkedObject#getPrevious <em>Previous</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Previous</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.LinkedObject#getPrevious()
	 * @see #getLinkedObject()
	 * @generated
	 */
	EReference getLinkedObject_Previous();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.method.common.method.Container <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Container
	 * @generated
	 */
	EClass getContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.cloudscaleproject.env.method.common.method.Container#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Container#getChildren()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_Children();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.cloudscaleproject.env.method.common.method.Container#getCommands <em>Commands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Commands</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Container#getCommands()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_Commands();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.method.common.method.StatusNode <em>Status Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Status Node</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.StatusNode
	 * @generated
	 */
	EClass getStatusNode();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.method.common.method.StatusNode#isDone <em>Done</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Done</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.StatusNode#isDone()
	 * @see #getStatusNode()
	 * @generated
	 */
	EAttribute getStatusNode_Done();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.method.common.method.StatusNode#isDirty <em>Dirty</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dirty</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.StatusNode#isDirty()
	 * @see #getStatusNode()
	 * @generated
	 */
	EAttribute getStatusNode_Dirty();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.cloudscaleproject.env.method.common.method.StatusNode#getWarnings <em>Warnings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Warnings</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.StatusNode#getWarnings()
	 * @see #getStatusNode()
	 * @generated
	 */
	EReference getStatusNode_Warnings();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.method.common.method.Action <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Action
	 * @generated
	 */
	EClass getAction();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.method.common.method.Section <em>Section</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Section</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Section
	 * @generated
	 */
	EClass getSection();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.method.common.method.Section#isValid <em>Valid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Valid</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Section#isValid()
	 * @see #getSection()
	 * @generated
	 */
	EAttribute getSection_Valid();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.method.common.method.Section#isInProgress <em>In Progress</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>In Progress</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Section#isInProgress()
	 * @see #getSection()
	 * @generated
	 */
	EAttribute getSection_InProgress();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.cloudscaleproject.env.method.common.method.Section#getCommands <em>Commands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Commands</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Section#getCommands()
	 * @see #getSection()
	 * @generated
	 */
	EReference getSection_Commands();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.cloudscaleproject.env.method.common.method.Section#getRequirements <em>Requirements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Requirements</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Section#getRequirements()
	 * @see #getSection()
	 * @generated
	 */
	EReference getSection_Requirements();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.method.common.method.Requirement <em>Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Requirement</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Requirement
	 * @generated
	 */
	EClass getRequirement();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.method.common.method.Requirement#getPosition <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Position</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Requirement#getPosition()
	 * @see #getRequirement()
	 * @generated
	 */
	EAttribute getRequirement_Position();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.method.common.method.Warning <em>Warning</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Warning</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Warning
	 * @generated
	 */
	EClass getWarning();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.method.common.method.Warning#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Warning#getId()
	 * @see #getWarning()
	 * @generated
	 */
	EAttribute getWarning_Id();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.method.common.method.Warning#getMessage <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Warning#getMessage()
	 * @see #getWarning()
	 * @generated
	 */
	EAttribute getWarning_Message();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.cloudscaleproject.env.method.common.method.Warning#getCommands <em>Commands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Commands</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Warning#getCommands()
	 * @see #getWarning()
	 * @generated
	 */
	EReference getWarning_Commands();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.method.common.method.Command <em>Command</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Command</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Command
	 * @generated
	 */
	EClass getCommand();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.method.common.method.Command#getPosition <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Position</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Command#getPosition()
	 * @see #getCommand()
	 * @generated
	 */
	EAttribute getCommand_Position();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.method.common.method.Link <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Link</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Link
	 * @generated
	 */
	EClass getLink();

	/**
	 * Returns the meta object for the reference '{@link eu.cloudscaleproject.env.method.common.method.Link#getStart <em>Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Start</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Link#getStart()
	 * @see #getLink()
	 * @generated
	 */
	EReference getLink_Start();

	/**
	 * Returns the meta object for the reference '{@link eu.cloudscaleproject.env.method.common.method.Link#getEnd <em>End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>End</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Link#getEnd()
	 * @see #getLink()
	 * @generated
	 */
	EReference getLink_End();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.method.common.method.Link#isRequired <em>Required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Required</em>'.
	 * @see eu.cloudscaleproject.env.method.common.method.Link#isRequired()
	 * @see #getLink()
	 * @generated
	 */
	EAttribute getLink_Required();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MethodFactory getMethodFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.method.common.method.impl.MethodImpl <em>Method</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.method.common.method.impl.MethodImpl
		 * @see eu.cloudscaleproject.env.method.common.method.impl.MethodPackageImpl#getMethod()
		 * @generated
		 */
		EClass METHOD = eINSTANCE.getMethod();

		/**
		 * The meta object literal for the '<em><b>Links</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD__LINKS = eINSTANCE.getMethod_Links();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD__NODES = eINSTANCE.getMethod_Nodes();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.method.common.method.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.method.common.method.impl.NodeImpl
		 * @see eu.cloudscaleproject.env.method.common.method.impl.MethodPackageImpl#getNode()
		 * @generated
		 */
		EClass NODE = eINSTANCE.getNode();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__ID = eINSTANCE.getNode_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__NAME = eINSTANCE.getNode_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__DESCRIPTION = eINSTANCE.getNode_Description();

		/**
		 * The meta object literal for the '<em><b>Tooltip</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__TOOLTIP = eINSTANCE.getNode_Tooltip();

		/**
		 * The meta object literal for the '<em><b>Color Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__COLOR_TEXT = eINSTANCE.getNode_ColorText();

		/**
		 * The meta object literal for the '<em><b>Color Foreground</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__COLOR_FOREGROUND = eINSTANCE.getNode_ColorForeground();

		/**
		 * The meta object literal for the '<em><b>Color Background</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__COLOR_BACKGROUND = eINSTANCE.getNode_ColorBackground();

		/**
		 * The meta object literal for the '<em><b>Image</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__IMAGE = eINSTANCE.getNode_Image();

		/**
		 * The meta object literal for the '<em><b>Layout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__LAYOUT = eINSTANCE.getNode_Layout();

		/**
		 * The meta object literal for the '<em><b>Command Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__COMMAND_ID = eINSTANCE.getNode_CommandId();

		/**
		 * The meta object literal for the '<em><b>Command Param</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__COMMAND_PARAM = eINSTANCE.getNode_CommandParam();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.method.common.method.LinkedObject <em>Linked Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.method.common.method.LinkedObject
		 * @see eu.cloudscaleproject.env.method.common.method.impl.MethodPackageImpl#getLinkedObject()
		 * @generated
		 */
		EClass LINKED_OBJECT = eINSTANCE.getLinkedObject();

		/**
		 * The meta object literal for the '<em><b>Next</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINKED_OBJECT__NEXT = eINSTANCE.getLinkedObject_Next();

		/**
		 * The meta object literal for the '<em><b>Previous</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINKED_OBJECT__PREVIOUS = eINSTANCE.getLinkedObject_Previous();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.method.common.method.impl.ContainerImpl <em>Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.method.common.method.impl.ContainerImpl
		 * @see eu.cloudscaleproject.env.method.common.method.impl.MethodPackageImpl#getContainer()
		 * @generated
		 */
		EClass CONTAINER = eINSTANCE.getContainer();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__CHILDREN = eINSTANCE.getContainer_Children();

		/**
		 * The meta object literal for the '<em><b>Commands</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__COMMANDS = eINSTANCE.getContainer_Commands();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.method.common.method.impl.StatusNodeImpl <em>Status Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.method.common.method.impl.StatusNodeImpl
		 * @see eu.cloudscaleproject.env.method.common.method.impl.MethodPackageImpl#getStatusNode()
		 * @generated
		 */
		EClass STATUS_NODE = eINSTANCE.getStatusNode();

		/**
		 * The meta object literal for the '<em><b>Done</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATUS_NODE__DONE = eINSTANCE.getStatusNode_Done();

		/**
		 * The meta object literal for the '<em><b>Dirty</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATUS_NODE__DIRTY = eINSTANCE.getStatusNode_Dirty();

		/**
		 * The meta object literal for the '<em><b>Warnings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATUS_NODE__WARNINGS = eINSTANCE.getStatusNode_Warnings();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.method.common.method.impl.ActionImpl <em>Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.method.common.method.impl.ActionImpl
		 * @see eu.cloudscaleproject.env.method.common.method.impl.MethodPackageImpl#getAction()
		 * @generated
		 */
		EClass ACTION = eINSTANCE.getAction();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.method.common.method.impl.SectionImpl <em>Section</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.method.common.method.impl.SectionImpl
		 * @see eu.cloudscaleproject.env.method.common.method.impl.MethodPackageImpl#getSection()
		 * @generated
		 */
		EClass SECTION = eINSTANCE.getSection();

		/**
		 * The meta object literal for the '<em><b>Valid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECTION__VALID = eINSTANCE.getSection_Valid();

		/**
		 * The meta object literal for the '<em><b>In Progress</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECTION__IN_PROGRESS = eINSTANCE.getSection_InProgress();

		/**
		 * The meta object literal for the '<em><b>Commands</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECTION__COMMANDS = eINSTANCE.getSection_Commands();

		/**
		 * The meta object literal for the '<em><b>Requirements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECTION__REQUIREMENTS = eINSTANCE.getSection_Requirements();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.method.common.method.impl.RequirementImpl <em>Requirement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.method.common.method.impl.RequirementImpl
		 * @see eu.cloudscaleproject.env.method.common.method.impl.MethodPackageImpl#getRequirement()
		 * @generated
		 */
		EClass REQUIREMENT = eINSTANCE.getRequirement();

		/**
		 * The meta object literal for the '<em><b>Position</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIREMENT__POSITION = eINSTANCE.getRequirement_Position();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.method.common.method.impl.WarningImpl <em>Warning</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.method.common.method.impl.WarningImpl
		 * @see eu.cloudscaleproject.env.method.common.method.impl.MethodPackageImpl#getWarning()
		 * @generated
		 */
		EClass WARNING = eINSTANCE.getWarning();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WARNING__ID = eINSTANCE.getWarning_Id();

		/**
		 * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WARNING__MESSAGE = eINSTANCE.getWarning_Message();

		/**
		 * The meta object literal for the '<em><b>Commands</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WARNING__COMMANDS = eINSTANCE.getWarning_Commands();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.method.common.method.impl.CommandImpl <em>Command</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.method.common.method.impl.CommandImpl
		 * @see eu.cloudscaleproject.env.method.common.method.impl.MethodPackageImpl#getCommand()
		 * @generated
		 */
		EClass COMMAND = eINSTANCE.getCommand();

		/**
		 * The meta object literal for the '<em><b>Position</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMAND__POSITION = eINSTANCE.getCommand_Position();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.method.common.method.impl.LinkImpl <em>Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.method.common.method.impl.LinkImpl
		 * @see eu.cloudscaleproject.env.method.common.method.impl.MethodPackageImpl#getLink()
		 * @generated
		 */
		EClass LINK = eINSTANCE.getLink();

		/**
		 * The meta object literal for the '<em><b>Start</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK__START = eINSTANCE.getLink_Start();

		/**
		 * The meta object literal for the '<em><b>End</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK__END = eINSTANCE.getLink_End();

		/**
		 * The meta object literal for the '<em><b>Required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINK__REQUIRED = eINSTANCE.getLink_Required();

	}

} //MethodPackage
