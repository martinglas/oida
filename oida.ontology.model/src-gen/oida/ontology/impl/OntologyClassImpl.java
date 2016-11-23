/**
 */
package oida.ontology.impl;

import java.util.Collection;

import oida.ontology.OntologyClass;
import oida.ontology.OntologyIndividual;
import oida.ontology.OntologyPackage;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link oida.ontology.impl.OntologyClassImpl#getIndividuals <em>Individuals</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OntologyClassImpl extends OntologyEntityImpl implements OntologyClass {
	/**
	 * The cached value of the '{@link #getIndividuals() <em>Individuals</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndividuals()
	 * @generated
	 * @ordered
	 */
	protected EList<OntologyIndividual> individuals;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OntologyClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OntologyPackage.Literals.ONTOLOGY_CLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OntologyIndividual> getIndividuals() {
		if (individuals == null) {
			individuals = new EObjectResolvingEList<OntologyIndividual>(OntologyIndividual.class, this, OntologyPackage.ONTOLOGY_CLASS__INDIVIDUALS);
		}
		return individuals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OntologyPackage.ONTOLOGY_CLASS__INDIVIDUALS:
				return getIndividuals();
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
			case OntologyPackage.ONTOLOGY_CLASS__INDIVIDUALS:
				getIndividuals().clear();
				getIndividuals().addAll((Collection<? extends OntologyIndividual>)newValue);
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
			case OntologyPackage.ONTOLOGY_CLASS__INDIVIDUALS:
				getIndividuals().clear();
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
			case OntologyPackage.ONTOLOGY_CLASS__INDIVIDUALS:
				return individuals != null && !individuals.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //OntologyClassImpl
