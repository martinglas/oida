/**
 */
package oida.ontologyMgr;

import oida.ontology.Ontology;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Local Ontology Entry</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link oida.ontologyMgr.LocalOntologyEntry#getPath <em>Path</em>}</li>
 *   <li>{@link oida.ontologyMgr.LocalOntologyEntry#getFile <em>File</em>}</li>
 *   <li>{@link oida.ontologyMgr.LocalOntologyEntry#getMaintenanceFile <em>Maintenance File</em>}</li>
 *   <li>{@link oida.ontologyMgr.LocalOntologyEntry#getManagedOntology <em>Managed Ontology</em>}</li>
 * </ul>
 *
 * @see oida.ontologyMgr.OntologyMgrPackage#getLocalOntologyEntry()
 * @model
 * @generated
 */
public interface LocalOntologyEntry extends EObject {
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
	 * @see oida.ontologyMgr.OntologyMgrPackage#getLocalOntologyEntry_Path()
	 * @model id="true"
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link oida.ontologyMgr.LocalOntologyEntry#getPath <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(String value);

	/**
	 * Returns the value of the '<em><b>File</b></em>' attribute.
	 * The default value is <code>"ontology.owl"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File</em>' attribute.
	 * @see #setFile(String)
	 * @see oida.ontologyMgr.OntologyMgrPackage#getLocalOntologyEntry_File()
	 * @model default="ontology.owl"
	 * @generated
	 */
	String getFile();

	/**
	 * Sets the value of the '{@link oida.ontologyMgr.LocalOntologyEntry#getFile <em>File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File</em>' attribute.
	 * @see #getFile()
	 * @generated
	 */
	void setFile(String value);

	/**
	 * Returns the value of the '<em><b>Maintenance File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maintenance File</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maintenance File</em>' attribute.
	 * @see #setMaintenanceFile(String)
	 * @see oida.ontologyMgr.OntologyMgrPackage#getLocalOntologyEntry_MaintenanceFile()
	 * @model
	 * @generated
	 */
	String getMaintenanceFile();

	/**
	 * Sets the value of the '{@link oida.ontologyMgr.LocalOntologyEntry#getMaintenanceFile <em>Maintenance File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maintenance File</em>' attribute.
	 * @see #getMaintenanceFile()
	 * @generated
	 */
	void setMaintenanceFile(String value);

	/**
	 * Returns the value of the '<em><b>Managed Ontology</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link oida.ontology.Ontology#getOntologyEntry <em>Ontology Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Managed Ontology</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Managed Ontology</em>' reference.
	 * @see #setManagedOntology(Ontology)
	 * @see oida.ontologyMgr.OntologyMgrPackage#getLocalOntologyEntry_ManagedOntology()
	 * @see oida.ontology.Ontology#getOntologyEntry
	 * @model opposite="ontologyEntry"
	 * @generated
	 */
	Ontology getManagedOntology();

	/**
	 * Sets the value of the '{@link oida.ontologyMgr.LocalOntologyEntry#getManagedOntology <em>Managed Ontology</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Managed Ontology</em>' reference.
	 * @see #getManagedOntology()
	 * @generated
	 */
	void setManagedOntology(Ontology value);

} // LocalOntologyEntry
