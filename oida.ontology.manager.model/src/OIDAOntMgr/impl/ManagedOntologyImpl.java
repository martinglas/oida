/**
 */
package OIDAOntMgr.impl;

import OIDAOntMgr.LocalOntology;
import OIDAOntMgr.ManagedOntology;
import OIDAOntMgr.OIDAOntMgrPackage;

import org.apache.jena.ontology.OntModel;

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
 *   <li>{@link OIDAOntMgr.impl.ManagedOntologyImpl#getLocalOntology <em>Local Ontology</em>}</li>
 *   <li>{@link OIDAOntMgr.impl.ManagedOntologyImpl#getOntolgyModel <em>Ontolgy Model</em>}</li>
 *   <li>{@link OIDAOntMgr.impl.ManagedOntologyImpl#isIsConsistent <em>Is Consistent</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ManagedOntologyImpl extends MinimalEObjectImpl.Container implements ManagedOntology {
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
	 * The default value of the '{@link #getOntolgyModel() <em>Ontolgy Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOntolgyModel()
	 * @generated
	 * @ordered
	 */
	protected static final OntModel ONTOLGY_MODEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOntolgyModel() <em>Ontolgy Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOntolgyModel()
	 * @generated
	 * @ordered
	 */
	protected OntModel ontolgyModel = ONTOLGY_MODEL_EDEFAULT;

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
		return OIDAOntMgrPackage.Literals.MANAGED_ONTOLOGY;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OIDAOntMgrPackage.MANAGED_ONTOLOGY__LOCAL_ONTOLOGY, oldLocalOntology, localOntology));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OIDAOntMgrPackage.MANAGED_ONTOLOGY__LOCAL_ONTOLOGY, oldLocalOntology, localOntology));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OntModel getOntolgyModel() {
		return ontolgyModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOntolgyModel(OntModel newOntolgyModel) {
		OntModel oldOntolgyModel = ontolgyModel;
		ontolgyModel = newOntolgyModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OIDAOntMgrPackage.MANAGED_ONTOLOGY__ONTOLGY_MODEL, oldOntolgyModel, ontolgyModel));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OIDAOntMgrPackage.MANAGED_ONTOLOGY__IS_CONSISTENT, oldIsConsistent, isConsistent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OIDAOntMgrPackage.MANAGED_ONTOLOGY__LOCAL_ONTOLOGY:
				if (resolve) return getLocalOntology();
				return basicGetLocalOntology();
			case OIDAOntMgrPackage.MANAGED_ONTOLOGY__ONTOLGY_MODEL:
				return getOntolgyModel();
			case OIDAOntMgrPackage.MANAGED_ONTOLOGY__IS_CONSISTENT:
				return isIsConsistent();
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
			case OIDAOntMgrPackage.MANAGED_ONTOLOGY__LOCAL_ONTOLOGY:
				setLocalOntology((LocalOntology)newValue);
				return;
			case OIDAOntMgrPackage.MANAGED_ONTOLOGY__ONTOLGY_MODEL:
				setOntolgyModel((OntModel)newValue);
				return;
			case OIDAOntMgrPackage.MANAGED_ONTOLOGY__IS_CONSISTENT:
				setIsConsistent((Boolean)newValue);
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
			case OIDAOntMgrPackage.MANAGED_ONTOLOGY__LOCAL_ONTOLOGY:
				setLocalOntology((LocalOntology)null);
				return;
			case OIDAOntMgrPackage.MANAGED_ONTOLOGY__ONTOLGY_MODEL:
				setOntolgyModel(ONTOLGY_MODEL_EDEFAULT);
				return;
			case OIDAOntMgrPackage.MANAGED_ONTOLOGY__IS_CONSISTENT:
				setIsConsistent(IS_CONSISTENT_EDEFAULT);
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
			case OIDAOntMgrPackage.MANAGED_ONTOLOGY__LOCAL_ONTOLOGY:
				return localOntology != null;
			case OIDAOntMgrPackage.MANAGED_ONTOLOGY__ONTOLGY_MODEL:
				return ONTOLGY_MODEL_EDEFAULT == null ? ontolgyModel != null : !ONTOLGY_MODEL_EDEFAULT.equals(ontolgyModel);
			case OIDAOntMgrPackage.MANAGED_ONTOLOGY__IS_CONSISTENT:
				return isConsistent != IS_CONSISTENT_EDEFAULT;
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
		result.append(" (OntolgyModel: ");
		result.append(ontolgyModel);
		result.append(", IsConsistent: ");
		result.append(isConsistent);
		result.append(')');
		return result.toString();
	}

} //ManagedOntologyImpl
