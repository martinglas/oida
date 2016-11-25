/**
 */
package oida.impl;

import oida.ManagedOntology;
import oida.OIDASystem;
import oida.OidaPackage;

import oida.ontologyMgr.Library;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>OIDA System</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link oida.impl.OIDASystemImpl#getOntologyLibrary <em>Ontology Library</em>}</li>
 *   <li>{@link oida.impl.OIDASystemImpl#getReferenceOntology <em>Reference Ontology</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OIDASystemImpl extends MinimalEObjectImpl.Container implements OIDASystem {
	/**
	 * The cached value of the '{@link #getOntologyLibrary() <em>Ontology Library</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOntologyLibrary()
	 * @generated
	 * @ordered
	 */
	protected Library ontologyLibrary;

	/**
	 * The cached value of the '{@link #getReferenceOntology() <em>Reference Ontology</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceOntology()
	 * @generated
	 * @ordered
	 */
	protected ManagedOntology referenceOntology;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OIDASystemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OidaPackage.Literals.OIDA_SYSTEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Library getOntologyLibrary() {
		if (ontologyLibrary != null && ontologyLibrary.eIsProxy()) {
			InternalEObject oldOntologyLibrary = (InternalEObject)ontologyLibrary;
			ontologyLibrary = (Library)eResolveProxy(oldOntologyLibrary);
			if (ontologyLibrary != oldOntologyLibrary) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OidaPackage.OIDA_SYSTEM__ONTOLOGY_LIBRARY, oldOntologyLibrary, ontologyLibrary));
			}
		}
		return ontologyLibrary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Library basicGetOntologyLibrary() {
		return ontologyLibrary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOntologyLibrary(Library newOntologyLibrary) {
		Library oldOntologyLibrary = ontologyLibrary;
		ontologyLibrary = newOntologyLibrary;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OidaPackage.OIDA_SYSTEM__ONTOLOGY_LIBRARY, oldOntologyLibrary, ontologyLibrary));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManagedOntology getReferenceOntology() {
		if (referenceOntology != null && referenceOntology.eIsProxy()) {
			InternalEObject oldReferenceOntology = (InternalEObject)referenceOntology;
			referenceOntology = (ManagedOntology)eResolveProxy(oldReferenceOntology);
			if (referenceOntology != oldReferenceOntology) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OidaPackage.OIDA_SYSTEM__REFERENCE_ONTOLOGY, oldReferenceOntology, referenceOntology));
			}
		}
		return referenceOntology;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManagedOntology basicGetReferenceOntology() {
		return referenceOntology;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferenceOntology(ManagedOntology newReferenceOntology) {
		ManagedOntology oldReferenceOntology = referenceOntology;
		referenceOntology = newReferenceOntology;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OidaPackage.OIDA_SYSTEM__REFERENCE_ONTOLOGY, oldReferenceOntology, referenceOntology));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OidaPackage.OIDA_SYSTEM__ONTOLOGY_LIBRARY:
				if (resolve) return getOntologyLibrary();
				return basicGetOntologyLibrary();
			case OidaPackage.OIDA_SYSTEM__REFERENCE_ONTOLOGY:
				if (resolve) return getReferenceOntology();
				return basicGetReferenceOntology();
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
			case OidaPackage.OIDA_SYSTEM__ONTOLOGY_LIBRARY:
				setOntologyLibrary((Library)newValue);
				return;
			case OidaPackage.OIDA_SYSTEM__REFERENCE_ONTOLOGY:
				setReferenceOntology((ManagedOntology)newValue);
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
			case OidaPackage.OIDA_SYSTEM__ONTOLOGY_LIBRARY:
				setOntologyLibrary((Library)null);
				return;
			case OidaPackage.OIDA_SYSTEM__REFERENCE_ONTOLOGY:
				setReferenceOntology((ManagedOntology)null);
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
			case OidaPackage.OIDA_SYSTEM__ONTOLOGY_LIBRARY:
				return ontologyLibrary != null;
			case OidaPackage.OIDA_SYSTEM__REFERENCE_ONTOLOGY:
				return referenceOntology != null;
		}
		return super.eIsSet(featureID);
	}

} //OIDASystemImpl
