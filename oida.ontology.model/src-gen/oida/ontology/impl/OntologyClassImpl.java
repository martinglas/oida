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
 *   <li>{@link oida.ontology.impl.OntologyClassImpl#getSuperClasses <em>Super Classes</em>}</li>
 *   <li>{@link oida.ontology.impl.OntologyClassImpl#getSubClasses <em>Sub Classes</em>}</li>
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
	 * The cached value of the '{@link #getSuperClasses() <em>Super Classes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuperClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<OntologyClass> superClasses;
	/**
	 * The cached value of the '{@link #getSubClasses() <em>Sub Classes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<OntologyClass> subClasses;

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
	public EList<OntologyClass> getSuperClasses() {
		if (superClasses == null) {
			superClasses = new EObjectResolvingEList<OntologyClass>(OntologyClass.class, this, OntologyPackage.ONTOLOGY_CLASS__SUPER_CLASSES);
		}
		return superClasses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OntologyClass> getSubClasses() {
		if (subClasses == null) {
			subClasses = new EObjectResolvingEList<OntologyClass>(OntologyClass.class, this, OntologyPackage.ONTOLOGY_CLASS__SUB_CLASSES);
		}
		return subClasses;
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
			case OntologyPackage.ONTOLOGY_CLASS__SUPER_CLASSES:
				return getSuperClasses();
			case OntologyPackage.ONTOLOGY_CLASS__SUB_CLASSES:
				return getSubClasses();
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
			case OntologyPackage.ONTOLOGY_CLASS__SUPER_CLASSES:
				getSuperClasses().clear();
				getSuperClasses().addAll((Collection<? extends OntologyClass>)newValue);
				return;
			case OntologyPackage.ONTOLOGY_CLASS__SUB_CLASSES:
				getSubClasses().clear();
				getSubClasses().addAll((Collection<? extends OntologyClass>)newValue);
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
			case OntologyPackage.ONTOLOGY_CLASS__SUPER_CLASSES:
				getSuperClasses().clear();
				return;
			case OntologyPackage.ONTOLOGY_CLASS__SUB_CLASSES:
				getSubClasses().clear();
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
			case OntologyPackage.ONTOLOGY_CLASS__SUPER_CLASSES:
				return superClasses != null && !superClasses.isEmpty();
			case OntologyPackage.ONTOLOGY_CLASS__SUB_CLASSES:
				return subClasses != null && !subClasses.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //OntologyClassImpl
