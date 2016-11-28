/**
 */
package oida.ontology.impl;

import java.util.Collection;

import oida.ontology.Ontology;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyPackage;

import oida.ontologyMgr.LocalOntologyEntry;
import oida.ontologyMgr.OntologyMgrPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ontology</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link oida.ontology.impl.OntologyImpl#getEntities <em>Entities</em>}</li>
 *   <li>{@link oida.ontology.impl.OntologyImpl#getNrOfClasses <em>Nr Of Classes</em>}</li>
 *   <li>{@link oida.ontology.impl.OntologyImpl#getNrOfIndividuals <em>Nr Of Individuals</em>}</li>
 *   <li>{@link oida.ontology.impl.OntologyImpl#getOntologyEntry <em>Ontology Entry</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OntologyImpl extends OntologyItemImpl implements Ontology {
	/**
	 * The cached value of the '{@link #getEntities() <em>Entities</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntities()
	 * @generated
	 * @ordered
	 */
	protected EList<OntologyEntity> entities;

	/**
	 * The default value of the '{@link #getNrOfClasses() <em>Nr Of Classes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNrOfClasses()
	 * @generated
	 * @ordered
	 */
	protected static final int NR_OF_CLASSES_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNrOfClasses() <em>Nr Of Classes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNrOfClasses()
	 * @generated
	 * @ordered
	 */
	protected int nrOfClasses = NR_OF_CLASSES_EDEFAULT;

	/**
	 * The default value of the '{@link #getNrOfIndividuals() <em>Nr Of Individuals</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNrOfIndividuals()
	 * @generated
	 * @ordered
	 */
	protected static final int NR_OF_INDIVIDUALS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNrOfIndividuals() <em>Nr Of Individuals</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNrOfIndividuals()
	 * @generated
	 * @ordered
	 */
	protected int nrOfIndividuals = NR_OF_INDIVIDUALS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOntologyEntry() <em>Ontology Entry</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOntologyEntry()
	 * @generated
	 * @ordered
	 */
	protected LocalOntologyEntry ontologyEntry;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OntologyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OntologyPackage.Literals.ONTOLOGY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OntologyEntity> getEntities() {
		if (entities == null) {
			entities = new EObjectContainmentWithInverseEList<OntologyEntity>(OntologyEntity.class, this, OntologyPackage.ONTOLOGY__ENTITIES, OntologyPackage.ONTOLOGY_ENTITY__CONTAINING_ONTOLOGY);
		}
		return entities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNrOfClasses() {
		return nrOfClasses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNrOfClasses(int newNrOfClasses) {
		int oldNrOfClasses = nrOfClasses;
		nrOfClasses = newNrOfClasses;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntologyPackage.ONTOLOGY__NR_OF_CLASSES, oldNrOfClasses, nrOfClasses));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNrOfIndividuals() {
		return nrOfIndividuals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNrOfIndividuals(int newNrOfIndividuals) {
		int oldNrOfIndividuals = nrOfIndividuals;
		nrOfIndividuals = newNrOfIndividuals;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntologyPackage.ONTOLOGY__NR_OF_INDIVIDUALS, oldNrOfIndividuals, nrOfIndividuals));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocalOntologyEntry getOntologyEntry() {
		if (ontologyEntry != null && ontologyEntry.eIsProxy()) {
			InternalEObject oldOntologyEntry = (InternalEObject)ontologyEntry;
			ontologyEntry = (LocalOntologyEntry)eResolveProxy(oldOntologyEntry);
			if (ontologyEntry != oldOntologyEntry) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntologyPackage.ONTOLOGY__ONTOLOGY_ENTRY, oldOntologyEntry, ontologyEntry));
			}
		}
		return ontologyEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocalOntologyEntry basicGetOntologyEntry() {
		return ontologyEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOntologyEntry(LocalOntologyEntry newOntologyEntry, NotificationChain msgs) {
		LocalOntologyEntry oldOntologyEntry = ontologyEntry;
		ontologyEntry = newOntologyEntry;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OntologyPackage.ONTOLOGY__ONTOLOGY_ENTRY, oldOntologyEntry, newOntologyEntry);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOntologyEntry(LocalOntologyEntry newOntologyEntry) {
		if (newOntologyEntry != ontologyEntry) {
			NotificationChain msgs = null;
			if (ontologyEntry != null)
				msgs = ((InternalEObject)ontologyEntry).eInverseRemove(this, OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__MANAGED_ONTOLOGY, LocalOntologyEntry.class, msgs);
			if (newOntologyEntry != null)
				msgs = ((InternalEObject)newOntologyEntry).eInverseAdd(this, OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__MANAGED_ONTOLOGY, LocalOntologyEntry.class, msgs);
			msgs = basicSetOntologyEntry(newOntologyEntry, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntologyPackage.ONTOLOGY__ONTOLOGY_ENTRY, newOntologyEntry, newOntologyEntry));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OntologyPackage.ONTOLOGY__ENTITIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getEntities()).basicAdd(otherEnd, msgs);
			case OntologyPackage.ONTOLOGY__ONTOLOGY_ENTRY:
				if (ontologyEntry != null)
					msgs = ((InternalEObject)ontologyEntry).eInverseRemove(this, OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__MANAGED_ONTOLOGY, LocalOntologyEntry.class, msgs);
				return basicSetOntologyEntry((LocalOntologyEntry)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OntologyPackage.ONTOLOGY__ENTITIES:
				return ((InternalEList<?>)getEntities()).basicRemove(otherEnd, msgs);
			case OntologyPackage.ONTOLOGY__ONTOLOGY_ENTRY:
				return basicSetOntologyEntry(null, msgs);
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
			case OntologyPackage.ONTOLOGY__ENTITIES:
				return getEntities();
			case OntologyPackage.ONTOLOGY__NR_OF_CLASSES:
				return getNrOfClasses();
			case OntologyPackage.ONTOLOGY__NR_OF_INDIVIDUALS:
				return getNrOfIndividuals();
			case OntologyPackage.ONTOLOGY__ONTOLOGY_ENTRY:
				if (resolve) return getOntologyEntry();
				return basicGetOntologyEntry();
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
			case OntologyPackage.ONTOLOGY__ENTITIES:
				getEntities().clear();
				getEntities().addAll((Collection<? extends OntologyEntity>)newValue);
				return;
			case OntologyPackage.ONTOLOGY__NR_OF_CLASSES:
				setNrOfClasses((Integer)newValue);
				return;
			case OntologyPackage.ONTOLOGY__NR_OF_INDIVIDUALS:
				setNrOfIndividuals((Integer)newValue);
				return;
			case OntologyPackage.ONTOLOGY__ONTOLOGY_ENTRY:
				setOntologyEntry((LocalOntologyEntry)newValue);
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
			case OntologyPackage.ONTOLOGY__ENTITIES:
				getEntities().clear();
				return;
			case OntologyPackage.ONTOLOGY__NR_OF_CLASSES:
				setNrOfClasses(NR_OF_CLASSES_EDEFAULT);
				return;
			case OntologyPackage.ONTOLOGY__NR_OF_INDIVIDUALS:
				setNrOfIndividuals(NR_OF_INDIVIDUALS_EDEFAULT);
				return;
			case OntologyPackage.ONTOLOGY__ONTOLOGY_ENTRY:
				setOntologyEntry((LocalOntologyEntry)null);
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
			case OntologyPackage.ONTOLOGY__ENTITIES:
				return entities != null && !entities.isEmpty();
			case OntologyPackage.ONTOLOGY__NR_OF_CLASSES:
				return nrOfClasses != NR_OF_CLASSES_EDEFAULT;
			case OntologyPackage.ONTOLOGY__NR_OF_INDIVIDUALS:
				return nrOfIndividuals != NR_OF_INDIVIDUALS_EDEFAULT;
			case OntologyPackage.ONTOLOGY__ONTOLOGY_ENTRY:
				return ontologyEntry != null;
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
		result.append(" (nrOfClasses: ");
		result.append(nrOfClasses);
		result.append(", nrOfIndividuals: ");
		result.append(nrOfIndividuals);
		result.append(')');
		return result.toString();
	}

} //OntologyImpl
