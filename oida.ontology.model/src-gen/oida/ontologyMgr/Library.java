/**
 */
package oida.ontologyMgr;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link oida.ontologyMgr.Library#getOwner <em>Owner</em>}</li>
 *   <li>{@link oida.ontologyMgr.Library#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link oida.ontologyMgr.Library#getOntologies <em>Ontologies</em>}</li>
 *   <li>{@link oida.ontologyMgr.Library#getActiveOntology <em>Active Ontology</em>}</li>
 * </ul>
 *
 * @see oida.ontologyMgr.OntologyMgrPackage#getLibrary()
 * @model
 * @generated
 */
public interface Library extends EObject {
	/**
	 * Returns the value of the '<em><b>Owner</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owner</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owner</em>' attribute.
	 * @see #setOwner(String)
	 * @see oida.ontologyMgr.OntologyMgrPackage#getLibrary_Owner()
	 * @model
	 * @generated
	 */
	String getOwner();

	/**
	 * Sets the value of the '{@link oida.ontologyMgr.Library#getOwner <em>Owner</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owner</em>' attribute.
	 * @see #getOwner()
	 * @generated
	 */
	void setOwner(String value);

	/**
	 * Returns the value of the '<em><b>Creation Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Creation Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Creation Date</em>' attribute.
	 * @see #setCreationDate(String)
	 * @see oida.ontologyMgr.OntologyMgrPackage#getLibrary_CreationDate()
	 * @model
	 * @generated
	 */
	String getCreationDate();

	/**
	 * Sets the value of the '{@link oida.ontologyMgr.Library#getCreationDate <em>Creation Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Creation Date</em>' attribute.
	 * @see #getCreationDate()
	 * @generated
	 */
	void setCreationDate(String value);

	/**
	 * Returns the value of the '<em><b>Ontologies</b></em>' containment reference list.
	 * The list contents are of type {@link oida.ontologyMgr.LocalOntology}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ontologies</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ontologies</em>' containment reference list.
	 * @see oida.ontologyMgr.OntologyMgrPackage#getLibrary_Ontologies()
	 * @model containment="true"
	 * @generated
	 */
	EList<LocalOntology> getOntologies();

	/**
	 * Returns the value of the '<em><b>Active Ontology</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Active Ontology</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active Ontology</em>' reference.
	 * @see #setActiveOntology(LocalOntology)
	 * @see oida.ontologyMgr.OntologyMgrPackage#getLibrary_ActiveOntology()
	 * @model
	 * @generated
	 */
	LocalOntology getActiveOntology();

	/**
	 * Sets the value of the '{@link oida.ontologyMgr.Library#getActiveOntology <em>Active Ontology</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active Ontology</em>' reference.
	 * @see #getActiveOntology()
	 * @generated
	 */
	void setActiveOntology(LocalOntology value);

} // Library
