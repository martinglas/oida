/**
 */
package oida.ontologyMgr;

import oida.ontology.Ontology;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ontology File</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link oida.ontologyMgr.OntologyFile#getPath <em>Path</em>}</li>
 *   <li>{@link oida.ontologyMgr.OntologyFile#getFileName <em>File Name</em>}</li>
 *   <li>{@link oida.ontologyMgr.OntologyFile#getManagedOntology <em>Managed Ontology</em>}</li>
 * </ul>
 *
 * @see oida.ontologyMgr.OntologyMgrPackage#getOntologyFile()
 * @model
 * @generated
 */
public interface OntologyFile extends EObject {
	/**
	 * Returns the value of the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path</em>' attribute.
	 * @see #setPath(String)
	 * @see oida.ontologyMgr.OntologyMgrPackage#getOntologyFile_Path()
	 * @model
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link oida.ontologyMgr.OntologyFile#getPath <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(String value);

	/**
	 * Returns the value of the '<em><b>File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File Name</em>' attribute.
	 * @see #setFileName(String)
	 * @see oida.ontologyMgr.OntologyMgrPackage#getOntologyFile_FileName()
	 * @model
	 * @generated
	 */
	String getFileName();

	/**
	 * Sets the value of the '{@link oida.ontologyMgr.OntologyFile#getFileName <em>File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Name</em>' attribute.
	 * @see #getFileName()
	 * @generated
	 */
	void setFileName(String value);

	/**
	 * Returns the value of the '<em><b>Managed Ontology</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Managed Ontology</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Managed Ontology</em>' reference.
	 * @see #setManagedOntology(Ontology)
	 * @see oida.ontologyMgr.OntologyMgrPackage#getOntologyFile_ManagedOntology()
	 * @model
	 * @generated
	 */
	Ontology getManagedOntology();

	/**
	 * Sets the value of the '{@link oida.ontologyMgr.OntologyFile#getManagedOntology <em>Managed Ontology</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Managed Ontology</em>' reference.
	 * @see #getManagedOntology()
	 * @generated
	 */
	void setManagedOntology(Ontology value);

} // OntologyFile
