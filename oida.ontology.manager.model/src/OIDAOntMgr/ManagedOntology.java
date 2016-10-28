/**
 */
package OIDAOntMgr;

import org.apache.jena.ontology.OntModel;

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
 *   <li>{@link OIDAOntMgr.ManagedOntology#getLocalOntology <em>Local Ontology</em>}</li>
 *   <li>{@link OIDAOntMgr.ManagedOntology#getOntolgyModel <em>Ontolgy Model</em>}</li>
 *   <li>{@link OIDAOntMgr.ManagedOntology#isIsConsistent <em>Is Consistent</em>}</li>
 * </ul>
 *
 * @see OIDAOntMgr.OIDAOntMgrPackage#getManagedOntology()
 * @model
 * @generated
 */
public interface ManagedOntology extends EObject {
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
	 * @see OIDAOntMgr.OIDAOntMgrPackage#getManagedOntology_LocalOntology()
	 * @model required="true"
	 * @generated
	 */
	LocalOntology getLocalOntology();

	/**
	 * Sets the value of the '{@link OIDAOntMgr.ManagedOntology#getLocalOntology <em>Local Ontology</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Local Ontology</em>' reference.
	 * @see #getLocalOntology()
	 * @generated
	 */
	void setLocalOntology(LocalOntology value);

	/**
	 * Returns the value of the '<em><b>Ontolgy Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ontolgy Model</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ontolgy Model</em>' attribute.
	 * @see #setOntolgyModel(OntModel)
	 * @see OIDAOntMgr.OIDAOntMgrPackage#getManagedOntology_OntolgyModel()
	 * @model dataType="OIDAOntMgr.OntModel"
	 * @generated
	 */
	OntModel getOntolgyModel();

	/**
	 * Sets the value of the '{@link OIDAOntMgr.ManagedOntology#getOntolgyModel <em>Ontolgy Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ontolgy Model</em>' attribute.
	 * @see #getOntolgyModel()
	 * @generated
	 */
	void setOntolgyModel(OntModel value);

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
	 * @see OIDAOntMgr.OIDAOntMgrPackage#getManagedOntology_IsConsistent()
	 * @model
	 * @generated
	 */
	boolean isIsConsistent();

	/**
	 * Sets the value of the '{@link OIDAOntMgr.ManagedOntology#isIsConsistent <em>Is Consistent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Consistent</em>' attribute.
	 * @see #isIsConsistent()
	 * @generated
	 */
	void setIsConsistent(boolean value);

} // ManagedOntology
