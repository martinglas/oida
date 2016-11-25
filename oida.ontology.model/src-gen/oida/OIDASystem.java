/**
 */
package oida;

import oida.ontologyMgr.Library;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>OIDA System</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link oida.OIDASystem#getOntologyLibrary <em>Ontology Library</em>}</li>
 *   <li>{@link oida.OIDASystem#getReferenceOntology <em>Reference Ontology</em>}</li>
 * </ul>
 *
 * @see oida.OidaPackage#getOIDASystem()
 * @model
 * @generated
 */
public interface OIDASystem extends EObject {
	/**
	 * Returns the value of the '<em><b>Ontology Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ontology Library</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ontology Library</em>' reference.
	 * @see #setOntologyLibrary(Library)
	 * @see oida.OidaPackage#getOIDASystem_OntologyLibrary()
	 * @model
	 * @generated
	 */
	Library getOntologyLibrary();

	/**
	 * Sets the value of the '{@link oida.OIDASystem#getOntologyLibrary <em>Ontology Library</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ontology Library</em>' reference.
	 * @see #getOntologyLibrary()
	 * @generated
	 */
	void setOntologyLibrary(Library value);

	/**
	 * Returns the value of the '<em><b>Reference Ontology</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference Ontology</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference Ontology</em>' reference.
	 * @see #setReferenceOntology(ManagedOntology)
	 * @see oida.OidaPackage#getOIDASystem_ReferenceOntology()
	 * @model
	 * @generated
	 */
	ManagedOntology getReferenceOntology();

	/**
	 * Sets the value of the '{@link oida.OIDASystem#getReferenceOntology <em>Reference Ontology</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference Ontology</em>' reference.
	 * @see #getReferenceOntology()
	 * @generated
	 */
	void setReferenceOntology(ManagedOntology value);

} // OIDASystem
