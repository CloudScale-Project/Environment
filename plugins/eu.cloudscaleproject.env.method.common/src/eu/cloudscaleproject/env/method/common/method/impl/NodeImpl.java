/**
 */
package eu.cloudscaleproject.env.method.common.method.impl;

import eu.cloudscaleproject.env.method.common.method.MethodPackage;
import eu.cloudscaleproject.env.method.common.method.Node;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.impl.NodeImpl#getId <em>Id</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.impl.NodeImpl#getSource <em>Source</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.impl.NodeImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.impl.NodeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.impl.NodeImpl#getTooltip <em>Tooltip</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.impl.NodeImpl#getColorText <em>Color Text</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.impl.NodeImpl#getColorForeground <em>Color Foreground</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.impl.NodeImpl#getColorBackground <em>Color Background</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.impl.NodeImpl#getImage <em>Image</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.impl.NodeImpl#isLayout <em>Layout</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.impl.NodeImpl#getCommandId <em>Command Id</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.impl.NodeImpl#getCommandParam <em>Command Param</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeImpl extends MinimalEObjectImpl.Container implements Node {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected static final Object SOURCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected Object source = SOURCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "name";

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getTooltip() <em>Tooltip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTooltip()
	 * @generated
	 * @ordered
	 */
	protected static final String TOOLTIP_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getTooltip() <em>Tooltip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTooltip()
	 * @generated
	 * @ordered
	 */
	protected String tooltip = TOOLTIP_EDEFAULT;

	/**
	 * The default value of the '{@link #getColorText() <em>Color Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColorText()
	 * @generated
	 * @ordered
	 */
	protected static final String COLOR_TEXT_EDEFAULT = "000000";

	/**
	 * The cached value of the '{@link #getColorText() <em>Color Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColorText()
	 * @generated
	 * @ordered
	 */
	protected String colorText = COLOR_TEXT_EDEFAULT;

	/**
	 * The default value of the '{@link #getColorForeground() <em>Color Foreground</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColorForeground()
	 * @generated
	 * @ordered
	 */
	protected static final String COLOR_FOREGROUND_EDEFAULT = "000000FF";

	/**
	 * The cached value of the '{@link #getColorForeground() <em>Color Foreground</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColorForeground()
	 * @generated
	 * @ordered
	 */
	protected String colorForeground = COLOR_FOREGROUND_EDEFAULT;

	/**
	 * The default value of the '{@link #getColorBackground() <em>Color Background</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColorBackground()
	 * @generated
	 * @ordered
	 */
	protected static final String COLOR_BACKGROUND_EDEFAULT = "FFFFFFFF";

	/**
	 * The cached value of the '{@link #getColorBackground() <em>Color Background</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColorBackground()
	 * @generated
	 * @ordered
	 */
	protected String colorBackground = COLOR_BACKGROUND_EDEFAULT;

	/**
	 * The default value of the '{@link #getImage() <em>Image</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImage()
	 * @generated
	 * @ordered
	 */
	protected static final String IMAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImage() <em>Image</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImage()
	 * @generated
	 * @ordered
	 */
	protected String image = IMAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #isLayout() <em>Layout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLayout()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LAYOUT_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isLayout() <em>Layout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLayout()
	 * @generated
	 * @ordered
	 */
	protected boolean layout = LAYOUT_EDEFAULT;

	/**
	 * The default value of the '{@link #getCommandId() <em>Command Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommandId()
	 * @generated
	 * @ordered
	 */
	protected static final String COMMAND_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCommandId() <em>Command Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommandId()
	 * @generated
	 * @ordered
	 */
	protected String commandId = COMMAND_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCommandParam() <em>Command Param</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommandParam()
	 * @generated
	 * @ordered
	 */
	protected EList<String> commandParam;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	protected NodeImpl() {
		super();
		this.id = EcoreUtil.generateUUID();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MethodPackage.Literals.NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MethodPackage.NODE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(Object newSource) {
		Object oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MethodPackage.NODE__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MethodPackage.NODE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MethodPackage.NODE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTooltip() {
		return tooltip;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTooltip(String newTooltip) {
		String oldTooltip = tooltip;
		tooltip = newTooltip;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MethodPackage.NODE__TOOLTIP, oldTooltip, tooltip));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getColorText() {
		return colorText;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColorText(String newColorText) {
		String oldColorText = colorText;
		colorText = newColorText;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MethodPackage.NODE__COLOR_TEXT, oldColorText, colorText));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getColorForeground() {
		return colorForeground;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColorForeground(String newColorForeground) {
		String oldColorForeground = colorForeground;
		colorForeground = newColorForeground;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MethodPackage.NODE__COLOR_FOREGROUND, oldColorForeground, colorForeground));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getColorBackground() {
		return colorBackground;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColorBackground(String newColorBackground) {
		String oldColorBackground = colorBackground;
		colorBackground = newColorBackground;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MethodPackage.NODE__COLOR_BACKGROUND, oldColorBackground, colorBackground));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getImage() {
		return image;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImage(String newImage) {
		String oldImage = image;
		image = newImage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MethodPackage.NODE__IMAGE, oldImage, image));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLayout() {
		return layout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLayout(boolean newLayout) {
		boolean oldLayout = layout;
		layout = newLayout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MethodPackage.NODE__LAYOUT, oldLayout, layout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCommandId() {
		return commandId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCommandId(String newCommandId) {
		String oldCommandId = commandId;
		commandId = newCommandId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MethodPackage.NODE__COMMAND_ID, oldCommandId, commandId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getCommandParam() {
		if (commandParam == null) {
			commandParam = new EDataTypeUniqueEList<String>(String.class, this, MethodPackage.NODE__COMMAND_PARAM);
		}
		return commandParam;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MethodPackage.NODE__ID:
				return getId();
			case MethodPackage.NODE__SOURCE:
				return getSource();
			case MethodPackage.NODE__NAME:
				return getName();
			case MethodPackage.NODE__DESCRIPTION:
				return getDescription();
			case MethodPackage.NODE__TOOLTIP:
				return getTooltip();
			case MethodPackage.NODE__COLOR_TEXT:
				return getColorText();
			case MethodPackage.NODE__COLOR_FOREGROUND:
				return getColorForeground();
			case MethodPackage.NODE__COLOR_BACKGROUND:
				return getColorBackground();
			case MethodPackage.NODE__IMAGE:
				return getImage();
			case MethodPackage.NODE__LAYOUT:
				return isLayout();
			case MethodPackage.NODE__COMMAND_ID:
				return getCommandId();
			case MethodPackage.NODE__COMMAND_PARAM:
				return getCommandParam();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MethodPackage.NODE__ID:
				setId((String)newValue);
				return;
			case MethodPackage.NODE__SOURCE:
				setSource(newValue);
				return;
			case MethodPackage.NODE__NAME:
				setName((String)newValue);
				return;
			case MethodPackage.NODE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case MethodPackage.NODE__TOOLTIP:
				setTooltip((String)newValue);
				return;
			case MethodPackage.NODE__COLOR_TEXT:
				setColorText((String)newValue);
				return;
			case MethodPackage.NODE__COLOR_FOREGROUND:
				setColorForeground((String)newValue);
				return;
			case MethodPackage.NODE__COLOR_BACKGROUND:
				setColorBackground((String)newValue);
				return;
			case MethodPackage.NODE__IMAGE:
				setImage((String)newValue);
				return;
			case MethodPackage.NODE__LAYOUT:
				setLayout((Boolean)newValue);
				return;
			case MethodPackage.NODE__COMMAND_ID:
				setCommandId((String)newValue);
				return;
			case MethodPackage.NODE__COMMAND_PARAM:
				getCommandParam().clear();
				getCommandParam().addAll((Collection<? extends String>)newValue);
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
			case MethodPackage.NODE__ID:
				setId(ID_EDEFAULT);
				return;
			case MethodPackage.NODE__SOURCE:
				setSource(SOURCE_EDEFAULT);
				return;
			case MethodPackage.NODE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case MethodPackage.NODE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case MethodPackage.NODE__TOOLTIP:
				setTooltip(TOOLTIP_EDEFAULT);
				return;
			case MethodPackage.NODE__COLOR_TEXT:
				setColorText(COLOR_TEXT_EDEFAULT);
				return;
			case MethodPackage.NODE__COLOR_FOREGROUND:
				setColorForeground(COLOR_FOREGROUND_EDEFAULT);
				return;
			case MethodPackage.NODE__COLOR_BACKGROUND:
				setColorBackground(COLOR_BACKGROUND_EDEFAULT);
				return;
			case MethodPackage.NODE__IMAGE:
				setImage(IMAGE_EDEFAULT);
				return;
			case MethodPackage.NODE__LAYOUT:
				setLayout(LAYOUT_EDEFAULT);
				return;
			case MethodPackage.NODE__COMMAND_ID:
				setCommandId(COMMAND_ID_EDEFAULT);
				return;
			case MethodPackage.NODE__COMMAND_PARAM:
				getCommandParam().clear();
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
			case MethodPackage.NODE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case MethodPackage.NODE__SOURCE:
				return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
			case MethodPackage.NODE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case MethodPackage.NODE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case MethodPackage.NODE__TOOLTIP:
				return TOOLTIP_EDEFAULT == null ? tooltip != null : !TOOLTIP_EDEFAULT.equals(tooltip);
			case MethodPackage.NODE__COLOR_TEXT:
				return COLOR_TEXT_EDEFAULT == null ? colorText != null : !COLOR_TEXT_EDEFAULT.equals(colorText);
			case MethodPackage.NODE__COLOR_FOREGROUND:
				return COLOR_FOREGROUND_EDEFAULT == null ? colorForeground != null : !COLOR_FOREGROUND_EDEFAULT.equals(colorForeground);
			case MethodPackage.NODE__COLOR_BACKGROUND:
				return COLOR_BACKGROUND_EDEFAULT == null ? colorBackground != null : !COLOR_BACKGROUND_EDEFAULT.equals(colorBackground);
			case MethodPackage.NODE__IMAGE:
				return IMAGE_EDEFAULT == null ? image != null : !IMAGE_EDEFAULT.equals(image);
			case MethodPackage.NODE__LAYOUT:
				return layout != LAYOUT_EDEFAULT;
			case MethodPackage.NODE__COMMAND_ID:
				return COMMAND_ID_EDEFAULT == null ? commandId != null : !COMMAND_ID_EDEFAULT.equals(commandId);
			case MethodPackage.NODE__COMMAND_PARAM:
				return commandParam != null && !commandParam.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: ");
		result.append(id);
		result.append(", source: ");
		result.append(source);
		result.append(", name: ");
		result.append(name);
		result.append(", description: ");
		result.append(description);
		result.append(", tooltip: ");
		result.append(tooltip);
		result.append(", colorText: ");
		result.append(colorText);
		result.append(", colorForeground: ");
		result.append(colorForeground);
		result.append(", colorBackground: ");
		result.append(colorBackground);
		result.append(", image: ");
		result.append(image);
		result.append(", layout: ");
		result.append(layout);
		result.append(", commandId: ");
		result.append(commandId);
		result.append(", commandParam: ");
		result.append(commandParam);
		result.append(')');
		return result.toString();
	}

} //NodeImpl
