/**
 */
package oida.ontologyMgr.impl;

import oida.ontologyMgr.LocalOntology;
import oida.ontologyMgr.ManagedOntology;
import oida.ontologyMgr.OntologyMgrPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Managed Ontology</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link oida.ontologyMgr.impl.ManagedOntologyImpl#isIsConsistent <em>Is Consistent</em>}</li>
 *   <li>{@link oida.ontologyMgr.impl.ManagedOntologyImpl#getLocalOntology <em>Local Ontology</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ManagedOntologyImpl extends MinimalEObjectImpl.Container implements ManagedOntology {
	/**
	 * The default value of the '{@link #isIsConsistent() <em>Is Consistent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsConsistent()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_CONSISTENT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsConsistent() <em>Is Consistent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsConsistent()
	 * @generated
	 * @ordered
	 */
	protected boolean isConsistent = IS_CONSISTENT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLocalOntology() <em>Local Ontology</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalOntology()
	 * @generated
	 * @ordered
	 */
	protected LocalOntology localOntology;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ManagedOntologyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OntologyMgrPackage.Literals.MANAGED_ONTOLOGY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsConsistent() {
		return isConsistent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsConsistent(boolean newIsConsistent) {
		boolean oldIsConsistent = isConsistent;
		isConsistent = newIsConsistent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntologyMgrPackage.MANAGED_ONTOLOGY__IS_CONSISTENT, oldIsConsistent, isConsistent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocalOntology getLocalOntology() {
		if (localOntology != null && localOntology.eIsProxy()) {
			InternalEObject oldLocalOntology = (InternalEObject)localOntology;
			localOntology = (LocalOntology)eResolveProxy(oldLocalOntology);
			if (localOntology != oldLocalOntology) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntologyMgrPackage.MANAGED_ONTOLOGY__LOCAL_ONTOLOGY, oldLocalOntology, localOntology));
			}
		}
		return localOntology;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocalOntology basicGetLocalOntology() {
		return localOntology;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocalOntology(LocalOntology newLocalOntology) {
		LocalOntology oldLocalOntology = localOntology;
		localOntology = newLocalOntology;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntologyMgrPackage.MANAGED_ONTOLOGY__LOCAL_ONTOLOGY, oldLocalOntology, localOntology));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OntologyMgrPackage.MANAGED_ONTOLOGY__IS_CONSISTENT:
				return isIsConsistent();
			case OntologyMgrPackage.MANAGED_ONTOLOGY__LOCAL_ONTOLOGY:
				if (resolve) return getLocalOntology();
				return basicGetLocalOntology();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case OntologyMgrPackage.MANAGED_ONTOLOGY__IS_CONSISTENT:
				setIsConsistent((Boolean)newValue);
				return;
			case OntologyMgrPackage.MANAGED_ONTOLOGY__LOCAL_ONTOLOGY:
				setLocalOntology((LocalOntology)newValue);
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
			case OntologyMgrPackage.MANAGED_ONTOLOGY__IS_CONSISTENT:
				setIsConsistent(IS_CONSISTENT_EDEFAULT);
				return;
			case OntologyMgrPackage.MANAGED_ONTOLOGY__LOCAL_ONTOLOGY:
				setLocalOntology((LocalOntology)null);
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
			case OntologyMgrPackage.MANAGED_ONTOLOGY__IS_CONSISTENT:
				return isConsistent != IS_CONSISTENT_EDEFAULT;
			case OntologyMgrPackage.MANAGED_ONTOLOGY__LOCAL_ONTOLOGY:
				return localOntology != null;
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
		result.append(" (IsConsistent: ");
		result.append(isConsistent);
		result.append(')');
		return result.toString();
	}

} //ManagedOntologyImpl
