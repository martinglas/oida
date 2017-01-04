/**
 */
package oida.ontology;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link oida.ontology.OntologyClass#getIndividuals <em>Individuals</em>}</li>
 *   <li>{@link oida.ontology.OntologyClass#getSuperClasses <em>Super Classes</em>}</li>
 *   <li>{@link oida.ontology.OntologyClass#getSubClasses <em>Sub Classes</em>}</li>
 * </ul>
 *
 * @see oida.ontology.OntologyPackage#getOntologyClass()
 * @model
 * @generated
 */
public interface OntologyClass extends OntologyEntity {
	/**
	 * Returns the value of the '<em><b>Individuals</b></em>' reference list.
	 * The list contents are of type {@link oida.ontology.OntologyIndividual}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Individuals</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Individuals</em>' reference list.
	 * @see oida.ontology.OntologyPackage#getOntologyClass_Individuals()
	 * @model
	 * @generated
	 */
	EList<OntologyIndividual> getIndividuals();

	/**
	 * Returns the value of the '<em><b>Super Classes</b></em>' reference list.
	 * The list contents are of type {@link oida.ontology.OntologyClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super Classes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super Classes</em>' reference list.
	 * @see oida.ontology.OntologyPackage#getOntologyClass_SuperClasses()
	 * @model
	 * @generated
	 */
	EList<OntologyClass> getSuperClasses();

	/**
	 * Returns the value of the '<em><b>Sub Classes</b></em>' reference list.
	 * The list contents are of type {@link oida.ontology.OntologyClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Classes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Classes</em>' reference list.
	 * @see oida.ontology.OntologyPackage#getOntologyClass_SubClasses()
	 * @model
	 * @generated
	 */
	EList<OntologyClass> getSubClasses();

} // OntologyClass
