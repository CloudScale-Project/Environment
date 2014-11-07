/**
 */
package eu.cloudscaleproject.env.method.common.method.impl;

import eu.cloudscaleproject.env.method.common.method.Command;
import eu.cloudscaleproject.env.method.common.method.Link;
import eu.cloudscaleproject.env.method.common.method.LinkedObject;
import eu.cloudscaleproject.env.method.common.method.MethodPackage;
import eu.cloudscaleproject.env.method.common.method.Requirement;
import eu.cloudscaleproject.env.method.common.method.Section;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Section</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.impl.SectionImpl#getNext <em>Next</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.impl.SectionImpl#getPrevious <em>Previous</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.impl.SectionImpl#isValid <em>Valid</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.impl.SectionImpl#isInProgress <em>In Progress</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.impl.SectionImpl#getCommands <em>Commands</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.method.common.method.impl.SectionImpl#getRequirements <em>Requirements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SectionImpl extends StatusNodeImpl implements Section {
	/**
	 * The cached value of the '{@link #getNext() <em>Next</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNext()
	 * @generated
	 * @ordered
	 */
	protected EList<Link> next;

	/**
	 * The cached value of the '{@link #getPrevious() <em>Previous</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrevious()
	 * @generated
	 * @ordered
	 */
	protected EList<Link> previous;

	/**
	 * The default value of the '{@link #isValid() <em>Valid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isValid()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VALID_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isValid() <em>Valid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isValid()
	 * @generated
	 * @ordered
	 */
	protected boolean valid = VALID_EDEFAULT;

	/**
	 * The default value of the '{@link #isInProgress() <em>In Progress</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInProgress()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IN_PROGRESS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInProgress() <em>In Progress</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInProgress()
	 * @generated
	 * @ordered
	 */
	protected boolean inProgress = IN_PROGRESS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCommands() <em>Commands</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommands()
	 * @generated
	 * @ordered
	 */
	protected EList<Command> commands;

	/**
	 * The cached value of the '{@link #getRequirements() <em>Requirements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequirements()
	 * @generated
	 * @ordered
	 */
	protected EList<Requirement> requirements;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MethodPackage.Literals.SECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Link> getNext() {
		if (next == null) {
			next = new EObjectResolvingEList<Link>(Link.class, this, MethodPackage.SECTION__NEXT);
		}
		return next;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Link> getPrevious() {
		if (previous == null) {
			previous = new EObjectResolvingEList<Link>(Link.class, this, MethodPackage.SECTION__PREVIOUS);
		}
		return previous;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValid(boolean newValid) {
		boolean oldValid = valid;
		valid = newValid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MethodPackage.SECTION__VALID, oldValid, valid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInProgress() {
		return inProgress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInProgress(boolean newInProgress) {
		boolean oldInProgress = inProgress;
		inProgress = newInProgress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MethodPackage.SECTION__IN_PROGRESS, oldInProgress, inProgress));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Command> getCommands() {
		if (commands == null) {
			commands = new EObjectContainmentEList<Command>(Command.class, this, MethodPackage.SECTION__COMMANDS);
		}
		return commands;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Requirement> getRequirements() {
		if (requirements == null) {
			requirements = new EObjectContainmentEList<Requirement>(Requirement.class, this, MethodPackage.SECTION__REQUIREMENTS);
		}
		return requirements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MethodPackage.SECTION__COMMANDS:
				return ((InternalEList<?>)getCommands()).basicRemove(otherEnd, msgs);
			case MethodPackage.SECTION__REQUIREMENTS:
				return ((InternalEList<?>)getRequirements()).basicRemove(otherEnd, msgs);
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
			case MethodPackage.SECTION__NEXT:
				return getNext();
			case MethodPackage.SECTION__PREVIOUS:
				return getPrevious();
			case MethodPackage.SECTION__VALID:
				return isValid();
			case MethodPackage.SECTION__IN_PROGRESS:
				return isInProgress();
			case MethodPackage.SECTION__COMMANDS:
				return getCommands();
			case MethodPackage.SECTION__REQUIREMENTS:
				return getRequirements();
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
			case MethodPackage.SECTION__NEXT:
				getNext().clear();
				getNext().addAll((Collection<? extends Link>)newValue);
				return;
			case MethodPackage.SECTION__PREVIOUS:
				getPrevious().clear();
				getPrevious().addAll((Collection<? extends Link>)newValue);
				return;
			case MethodPackage.SECTION__VALID:
				setValid((Boolean)newValue);
				return;
			case MethodPackage.SECTION__IN_PROGRESS:
				setInProgress((Boolean)newValue);
				return;
			case MethodPackage.SECTION__COMMANDS:
				getCommands().clear();
				getCommands().addAll((Collection<? extends Command>)newValue);
				return;
			case MethodPackage.SECTION__REQUIREMENTS:
				getRequirements().clear();
				getRequirements().addAll((Collection<? extends Requirement>)newValue);
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
			case MethodPackage.SECTION__NEXT:
				getNext().clear();
				return;
			case MethodPackage.SECTION__PREVIOUS:
				getPrevious().clear();
				return;
			case MethodPackage.SECTION__VALID:
				setValid(VALID_EDEFAULT);
				return;
			case MethodPackage.SECTION__IN_PROGRESS:
				setInProgress(IN_PROGRESS_EDEFAULT);
				return;
			case MethodPackage.SECTION__COMMANDS:
				getCommands().clear();
				return;
			case MethodPackage.SECTION__REQUIREMENTS:
				getRequirements().clear();
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
			case MethodPackage.SECTION__NEXT:
				return next != null && !next.isEmpty();
			case MethodPackage.SECTION__PREVIOUS:
				return previous != null && !previous.isEmpty();
			case MethodPackage.SECTION__VALID:
				return valid != VALID_EDEFAULT;
			case MethodPackage.SECTION__IN_PROGRESS:
				return inProgress != IN_PROGRESS_EDEFAULT;
			case MethodPackage.SECTION__COMMANDS:
				return commands != null && !commands.isEmpty();
			case MethodPackage.SECTION__REQUIREMENTS:
				return requirements != null && !requirements.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == LinkedObject.class) {
			switch (derivedFeatureID) {
				case MethodPackage.SECTION__NEXT: return MethodPackage.LINKED_OBJECT__NEXT;
				case MethodPackage.SECTION__PREVIOUS: return MethodPackage.LINKED_OBJECT__PREVIOUS;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == LinkedObject.class) {
			switch (baseFeatureID) {
				case MethodPackage.LINKED_OBJECT__NEXT: return MethodPackage.SECTION__NEXT;
				case MethodPackage.LINKED_OBJECT__PREVIOUS: return MethodPackage.SECTION__PREVIOUS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (valid: ");
		result.append(valid);
		result.append(", inProgress: ");
		result.append(inProgress);
		result.append(')');
		return result.toString();
	}

} //SectionImpl
