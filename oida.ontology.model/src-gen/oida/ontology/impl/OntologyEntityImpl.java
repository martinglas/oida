/**
 */
package oida.ontology.impl;

import oida.ontology.Ontology;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

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
		if (eContainerFeatureID() != OntologyPackage.ONTOLOGY_ENTITY__CONTAINING_ONTOLOGY) return null;
		return (Ontology)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContainingOntology(Ontology newContainingOntology, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newContainingOntology, OntologyPackage.ONTOLOGY_ENTITY__CONTAINING_ONTOLOGY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContainingOntology(Ontology newContainingOntology) {
		if (newContainingOntology != eInternalContainer() || (eContainerFeatureID() != OntologyPackage.ONTOLOGY_ENTITY__CONTAINING_ONTOLOGY && newContainingOntology != null)) {
			if (EcoreUtil.isAncestor(this, newContainingOntology))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newContainingOntology != null)
				msgs = ((InternalEObject)newContainingOntology).eInverseAdd(this, OntologyPackage.ONTOLOGY__ENTITIES, Ontology.class, msgs);
			msgs = basicSetContainingOntology(newContainingOntology, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntologyPackage.ONTOLOGY_ENTITY__CONTAINING_ONTOLOGY, newContainingOntology, newContainingOntology));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OntologyPackage.ONTOLOGY_ENTITY__CONTAINING_ONTOLOGY:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetContainingOntology((Ontology)otherEnd, msgs);
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
			case OntologyPackage.ONTOLOGY_ENTITY__CONTAINING_ONTOLOGY:
				return basicSetContainingOntology(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case OntologyPackage.ONTOLOGY_ENTITY__CONTAINING_ONTOLOGY:
				return eInternalContainer().eInverseRemove(this, OntologyPackage.ONTOLOGY__ENTITIES, Ontology.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
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
				return getContainingOntology();
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
				return getContainingOntology() != null;
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
