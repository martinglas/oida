/**
 */
package oida;

import oida.ontology.Ontology;

import oida.ontologyMgr.LocalOntologyEntry;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Managed Ontology</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link oida.ManagedOntology#getOntology <em>Ontology</em>}</li>
 *   <li>{@link oida.ManagedOntology#getOntologyEntry <em>Ontology Entry</em>}</li>
 * </ul>
 *
 * @see oida.OidaPackage#getManagedOntology()
 * @model
 * @generated
 */
public interface ManagedOntology extends EObject {
	/**
	 * Returns the value of the '<em><b>Ontology</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ontology</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ontology</em>' reference.
	 * @see #setOntology(Ontology)
	 * @see oida.OidaPackage#getManagedOntology_Ontology()
	 * @model
	 * @generated
	 */
	Ontology getOntology();

	/**
	 * Sets the value of the '{@link oida.ManagedOntology#getOntology <em>Ontology</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ontology</em>' reference.
	 * @see #getOntology()
	 * @generated
	 */
	void setOntology(Ontology value);

	/**
	 * Returns the value of the '<em><b>Ontology Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ontology Entry</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ontology Entry</em>' reference.
	 * @see #setOntologyEntry(LocalOntologyEntry)
	 * @see oida.OidaPackage#getManagedOntology_OntologyEntry()
	 * @model
	 * @generated
	 */
	LocalOntologyEntry getOntologyEntry();

	/**
	 * Sets the value of the '{@link oida.ManagedOntology#getOntologyEntry <em>Ontology Entry</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ontology Entry</em>' reference.
	 * @see #getOntologyEntry()
	 * @generated
	 */
	void setOntologyEntry(LocalOntologyEntry value);

} // ManagedOntology
