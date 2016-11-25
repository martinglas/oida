/**
 */
package oida.impl;

import oida.ManagedOntology;
import oida.OidaPackage;

import oida.ontology.Ontology;

import oida.ontologyMgr.LocalOntologyEntry;

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
 *   <li>{@link oida.impl.ManagedOntologyImpl#getOntology <em>Ontology</em>}</li>
 *   <li>{@link oida.impl.ManagedOntologyImpl#getOntologyEntry <em>Ontology Entry</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ManagedOntologyImpl extends MinimalEObjectImpl.Container implements ManagedOntology {
	/**
	 * The cached value of the '{@link #getOntology() <em>Ontology</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOntology()
	 * @generated
	 * @ordered
	 */
	protected Ontology ontology;

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
		return OidaPackage.Literals.MANAGED_ONTOLOGY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ontology getOntology() {
		if (ontology != null && ontology.eIsProxy()) {
			InternalEObject oldOntology = (InternalEObject)ontology;
			ontology = (Ontology)eResolveProxy(oldOntology);
			if (ontology != oldOntology) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OidaPackage.MANAGED_ONTOLOGY__ONTOLOGY, oldOntology, ontology));
			}
		}
		return ontology;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ontology basicGetOntology() {
		return ontology;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOntology(Ontology newOntology) {
		Ontology oldOntology = ontology;
		ontology = newOntology;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OidaPackage.MANAGED_ONTOLOGY__ONTOLOGY, oldOntology, ontology));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OidaPackage.MANAGED_ONTOLOGY__ONTOLOGY_ENTRY, oldOntologyEntry, ontologyEntry));
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
	public void setOntologyEntry(LocalOntologyEntry newOntologyEntry) {
		LocalOntologyEntry oldOntologyEntry = ontologyEntry;
		ontologyEntry = newOntologyEntry;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OidaPackage.MANAGED_ONTOLOGY__ONTOLOGY_ENTRY, oldOntologyEntry, ontologyEntry));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OidaPackage.MANAGED_ONTOLOGY__ONTOLOGY:
				if (resolve) return getOntology();
				return basicGetOntology();
			case OidaPackage.MANAGED_ONTOLOGY__ONTOLOGY_ENTRY:
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
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case OidaPackage.MANAGED_ONTOLOGY__ONTOLOGY:
				setOntology((Ontology)newValue);
				return;
			case OidaPackage.MANAGED_ONTOLOGY__ONTOLOGY_ENTRY:
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
			case OidaPackage.MANAGED_ONTOLOGY__ONTOLOGY:
				setOntology((Ontology)null);
				return;
			case OidaPackage.MANAGED_ONTOLOGY__ONTOLOGY_ENTRY:
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
			case OidaPackage.MANAGED_ONTOLOGY__ONTOLOGY:
				return ontology != null;
			case OidaPackage.MANAGED_ONTOLOGY__ONTOLOGY_ENTRY:
				return ontologyEntry != null;
		}
		return super.eIsSet(featureID);
	}

} //ManagedOntologyImpl
