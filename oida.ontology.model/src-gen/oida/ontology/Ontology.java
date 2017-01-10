/**
 */
package oida.ontology;

import oida.ontologyMgr.LocalOntologyEntry;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ontology</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link oida.ontology.Ontology#getClasses <em>Classes</em>}</li>
 *   <li>{@link oida.ontology.Ontology#getIndividuals <em>Individuals</em>}</li>
 *   <li>{@link oida.ontology.Ontology#getNrOfClasses <em>Nr Of Classes</em>}</li>
 *   <li>{@link oida.ontology.Ontology#getNrOfIndividuals <em>Nr Of Individuals</em>}</li>
 *   <li>{@link oida.ontology.Ontology#getOntologyEntry <em>Ontology Entry</em>}</li>
 * </ul>
 *
 * @see oida.ontology.OntologyPackage#getOntology()
 * @model
 * @generated
 */
public interface Ontology extends OntologyItem {
	/**
	 * Returns the value of the '<em><b>Classes</b></em>' containment reference list.
	 * The list contents are of type {@link oida.ontology.OntologyClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classes</em>' containment reference list.
	 * @see oida.ontology.OntologyPackage#getOntology_Classes()
	 * @model containment="true"
	 * @generated
	 */
	EList<OntologyClass> getClasses();

	/**
	 * Returns the value of the '<em><b>Individuals</b></em>' containment reference list.
	 * The list contents are of type {@link oida.ontology.OntologyIndividual}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Individuals</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Individuals</em>' containment reference list.
	 * @see oida.ontology.OntologyPackage#getOntology_Individuals()
	 * @model containment="true"
	 * @generated
	 */
	EList<OntologyIndividual> getIndividuals();

	/**
	 * Returns the value of the '<em><b>Nr Of Classes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nr Of Classes</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nr Of Classes</em>' attribute.
	 * @see #setNrOfClasses(long)
	 * @see oida.ontology.OntologyPackage#getOntology_NrOfClasses()
	 * @model
	 * @generated
	 */
	long getNrOfClasses();

	/**
	 * Sets the value of the '{@link oida.ontology.Ontology#getNrOfClasses <em>Nr Of Classes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nr Of Classes</em>' attribute.
	 * @see #getNrOfClasses()
	 * @generated
	 */
	void setNrOfClasses(long value);

	/**
	 * Returns the value of the '<em><b>Nr Of Individuals</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nr Of Individuals</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nr Of Individuals</em>' attribute.
	 * @see #setNrOfIndividuals(long)
	 * @see oida.ontology.OntologyPackage#getOntology_NrOfIndividuals()
	 * @model
	 * @generated
	 */
	long getNrOfIndividuals();

	/**
	 * Sets the value of the '{@link oida.ontology.Ontology#getNrOfIndividuals <em>Nr Of Individuals</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nr Of Individuals</em>' attribute.
	 * @see #getNrOfIndividuals()
	 * @generated
	 */
	void setNrOfIndividuals(long value);

	/**
	 * Returns the value of the '<em><b>Ontology Entry</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link oida.ontologyMgr.LocalOntologyEntry#getManagedOntology <em>Managed Ontology</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ontology Entry</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ontology Entry</em>' reference.
	 * @see #setOntologyEntry(LocalOntologyEntry)
	 * @see oida.ontology.OntologyPackage#getOntology_OntologyEntry()
	 * @see oida.ontologyMgr.LocalOntologyEntry#getManagedOntology
	 * @model opposite="managedOntology"
	 * @generated
	 */
	LocalOntologyEntry getOntologyEntry();

	/**
	 * Sets the value of the '{@link oida.ontology.Ontology#getOntologyEntry <em>Ontology Entry</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ontology Entry</em>' reference.
	 * @see #getOntologyEntry()
	 * @generated
	 */
	void setOntologyEntry(LocalOntologyEntry value);

} // Ontology
