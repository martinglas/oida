/**
 */
package oida.ontologyMgr.impl;

import java.util.Collection;

import oida.ontologyMgr.Library;
import oida.ontologyMgr.LocalOntology;
import oida.ontologyMgr.OntologyMgrPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link oida.ontologyMgr.impl.LibraryImpl#getOwner <em>Owner</em>}</li>
 *   <li>{@link oida.ontologyMgr.impl.LibraryImpl#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link oida.ontologyMgr.impl.LibraryImpl#getOntologies <em>Ontologies</em>}</li>
 *   <li>{@link oida.ontologyMgr.impl.LibraryImpl#getActiveOntology <em>Active Ontology</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LibraryImpl extends MinimalEObjectImpl.Container implements Library {
	/**
	 * The default value of the '{@link #getOwner() <em>Owner</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwner()
	 * @generated
	 * @ordered
	 */
	protected static final String OWNER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOwner() <em>Owner</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwner()
	 * @generated
	 * @ordered
	 */
	protected String owner = OWNER_EDEFAULT;

	/**
	 * The default value of the '{@link #getCreationDate() <em>Creation Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationDate()
	 * @generated
	 * @ordered
	 */
	protected static final String CREATION_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreationDate() <em>Creation Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationDate()
	 * @generated
	 * @ordered
	 */
	protected String creationDate = CREATION_DATE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOntologies() <em>Ontologies</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOntologies()
	 * @generated
	 * @ordered
	 */
	protected EList<LocalOntology> ontologies;

	/**
	 * The cached value of the '{@link #getActiveOntology() <em>Active Ontology</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActiveOntology()
	 * @generated
	 * @ordered
	 */
	protected LocalOntology activeOntology;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LibraryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OntologyMgrPackage.Literals.LIBRARY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwner(String newOwner) {
		String oldOwner = owner;
		owner = newOwner;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntologyMgrPackage.LIBRARY__OWNER, oldOwner, owner));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreationDate(String newCreationDate) {
		String oldCreationDate = creationDate;
		creationDate = newCreationDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntologyMgrPackage.LIBRARY__CREATION_DATE, oldCreationDate, creationDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<LocalOntology> getOntologies() {
		if (ontologies == null) {
			ontologies = new EObjectContainmentEList<LocalOntology>(LocalOntology.class, this, OntologyMgrPackage.LIBRARY__ONTOLOGIES);
		}
		return ontologies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocalOntology getActiveOntology() {
		if (activeOntology != null && activeOntology.eIsProxy()) {
			InternalEObject oldActiveOntology = (InternalEObject)activeOntology;
			activeOntology = (LocalOntology)eResolveProxy(oldActiveOntology);
			if (activeOntology != oldActiveOntology) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntologyMgrPackage.LIBRARY__ACTIVE_ONTOLOGY, oldActiveOntology, activeOntology));
			}
		}
		return activeOntology;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocalOntology basicGetActiveOntology() {
		return activeOntology;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActiveOntology(LocalOntology newActiveOntology) {
		LocalOntology oldActiveOntology = activeOntology;
		activeOntology = newActiveOntology;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntologyMgrPackage.LIBRARY__ACTIVE_ONTOLOGY, oldActiveOntology, activeOntology));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OntologyMgrPackage.LIBRARY__ONTOLOGIES:
				return ((InternalEList<?>)getOntologies()).basicRemove(otherEnd, msgs);
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
			case OntologyMgrPackage.LIBRARY__OWNER:
				return getOwner();
			case OntologyMgrPackage.LIBRARY__CREATION_DATE:
				return getCreationDate();
			case OntologyMgrPackage.LIBRARY__ONTOLOGIES:
				return getOntologies();
			case OntologyMgrPackage.LIBRARY__ACTIVE_ONTOLOGY:
				if (resolve) return getActiveOntology();
				return basicGetActiveOntology();
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
			case OntologyMgrPackage.LIBRARY__OWNER:
				setOwner((String)newValue);
				return;
			case OntologyMgrPackage.LIBRARY__CREATION_DATE:
				setCreationDate((String)newValue);
				return;
			case OntologyMgrPackage.LIBRARY__ONTOLOGIES:
				getOntologies().clear();
				getOntologies().addAll((Collection<? extends LocalOntology>)newValue);
				return;
			case OntologyMgrPackage.LIBRARY__ACTIVE_ONTOLOGY:
				setActiveOntology((LocalOntology)newValue);
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
			case OntologyMgrPackage.LIBRARY__OWNER:
				setOwner(OWNER_EDEFAULT);
				return;
			case OntologyMgrPackage.LIBRARY__CREATION_DATE:
				setCreationDate(CREATION_DATE_EDEFAULT);
				return;
			case OntologyMgrPackage.LIBRARY__ONTOLOGIES:
				getOntologies().clear();
				return;
			case OntologyMgrPackage.LIBRARY__ACTIVE_ONTOLOGY:
				setActiveOntology((LocalOntology)null);
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
			case OntologyMgrPackage.LIBRARY__OWNER:
				return OWNER_EDEFAULT == null ? owner != null : !OWNER_EDEFAULT.equals(owner);
			case OntologyMgrPackage.LIBRARY__CREATION_DATE:
				return CREATION_DATE_EDEFAULT == null ? creationDate != null : !CREATION_DATE_EDEFAULT.equals(creationDate);
			case OntologyMgrPackage.LIBRARY__ONTOLOGIES:
				return ontologies != null && !ontologies.isEmpty();
			case OntologyMgrPackage.LIBRARY__ACTIVE_ONTOLOGY:
				return activeOntology != null;
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
		result.append(" (owner: ");
		result.append(owner);
		result.append(", creationDate: ");
		result.append(creationDate);
		result.append(')');
		return result.toString();
	}

} //LibraryImpl
