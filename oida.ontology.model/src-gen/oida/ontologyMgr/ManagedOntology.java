/**
 */
package oida.ontologyMgr;

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
 *   <li>{@link oida.ontologyMgr.ManagedOntology#isIsConsistent <em>Is Consistent</em>}</li>
 *   <li>{@link oida.ontologyMgr.ManagedOntology#getLocalOntology <em>Local Ontology</em>}</li>
 * </ul>
 *
 * @see oida.ontologyMgr.OntologyMgrPackage#getManagedOntology()
 * @model
 * @generated
 */
public interface ManagedOntology extends EObject {
	/**
	 * Returns the value of the '<em><b>Is Consistent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Consistent</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Consistent</em>' attribute.
	 * @see #setIsConsistent(boolean)
	 * @see oida.ontologyMgr.OntologyMgrPackage#getManagedOntology_IsConsistent()
	 * @model
	 * @generated
	 */
	boolean isIsConsistent();

	/**
	 * Sets the value of the '{@link oida.ontologyMgr.ManagedOntology#isIsConsistent <em>Is Consistent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Consistent</em>' attribute.
	 * @see #isIsConsistent()
	 * @generated
	 */
	void setIsConsistent(boolean value);

	/**
	 * Returns the value of the '<em><b>Local Ontology</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Local Ontology</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Local Ontology</em>' reference.
	 * @see #setLocalOntology(LocalOntology)
	 * @see oida.ontologyMgr.OntologyMgrPackage#getManagedOntology_LocalOntology()
	 * @model required="true"
	 * @generated
	 */
	LocalOntology getLocalOntology();

	/**
	 * Sets the value of the '{@link oida.ontologyMgr.ManagedOntology#getLocalOntology <em>Local Ontology</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Local Ontology</em>' reference.
	 * @see #getLocalOntology()
	 * @generated
	 */
	void setLocalOntology(LocalOntology value);

} // ManagedOntology
