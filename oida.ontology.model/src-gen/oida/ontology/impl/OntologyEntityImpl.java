/**
 */
package oida.ontology.impl;

import oida.ontology.Ontology;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Entity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link oida.ontology.impl.OntologyEntityImpl#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link oida.ontology.impl.OntologyEntityImpl#getContainingOntology <em>Containing Ontology</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OntologyEntityImpl extends OntologyItemImpl implements OntologyEntity {
	/**
	 * The default value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrefix()
	 * @generated
	 * @ordered
	 */
	protected static final String PREFIX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrefix()
	 * @generated
	 * @ordered
	 */
	protected String prefix = PREFIX_EDEFAULT;

	/**
	 * The cached value of the '{@link #getContainingOntology() <em>Containing Ontology</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainingOntology()
	 * @generated
	 * @ordered
	 */
	protected Ontology containingOntology;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OntologyEntityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OntologyPackage.Literals.ONTOLOGY_ENTITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrefix(String newPrefix) {
		String oldPrefix = prefix;
		prefix = newPrefix;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntologyPackage.ONTOLOGY_ENTITY__PREFIX, oldPrefix, prefix));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ontology getContainingOntology() {
		if (containingOntology != null && containingOntology.eIsProxy()) {
			InternalEObject oldContainingOntology = (InternalEObject)containingOntology;
			containingOntology = (Ontology)eResolveProxy(oldContainingOntology);
			if (containingOntology != oldContainingOntology) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntologyPackage.ONTOLOGY_ENTITY__CONTAINING_ONTOLOGY, oldContainingOntology, containingOntology));
			}
		}
		return containingOntology;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ontology basicGetContainingOntology() {
		return containingOntology;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContainingOntology(Ontology newContainingOntology) {
		Ontology oldContainingOntology = containingOntology;
		containingOntology = newContainingOntology;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntologyPackage.ONTOLOGY_ENTITY__CONTAINING_ONTOLOGY, oldContainingOntology, containingOntology));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OntologyPackage.ONTOLOGY_ENTITY__PREFIX:
				return getPrefix();
			case OntologyPackage.ONTOLOGY_ENTITY__CONTAINING_ONTOLOGY:
				if (resolve) return getContainingOntology();
				return basicGetContainingOntology();
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
			case OntologyPackage.ONTOLOGY_ENTITY__PREFIX:
				setPrefix((String)newValue);
				return;
			case OntologyPackage.ONTOLOGY_ENTITY__CONTAINING_ONTOLOGY:
				setContainingOntology((Ontology)newValue);
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
			case OntologyPackage.ONTOLOGY_ENTITY__PREFIX:
				setPrefix(PREFIX_EDEFAULT);
				return;
			case OntologyPackage.ONTOLOGY_ENTITY__CONTAINING_ONTOLOGY:
				setContainingOntology((Ontology)null);
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
			case OntologyPackage.ONTOLOGY_ENTITY__PREFIX:
				return PREFIX_EDEFAULT == null ? prefix != null : !PREFIX_EDEFAULT.equals(prefix);
			case OntologyPackage.ONTOLOGY_ENTITY__CONTAINING_ONTOLOGY:
				return containingOntology != null;
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
		result.append(" (prefix: ");
		result.append(prefix);
		result.append(')');
		return result.toString();
	}

} //OntologyEntityImpl
