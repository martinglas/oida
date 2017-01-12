/**
 */
package oida.ontologyMgr;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Local Reference Ontology File Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link oida.ontologyMgr.LocalReferenceOntologyFileSet#getMaintenanceFileName <em>Maintenance File Name</em>}</li>
 * </ul>
 *
 * @see oida.ontologyMgr.OntologyMgrPackage#getLocalReferenceOntologyFileSet()
 * @model
 * @generated
 */
public interface LocalReferenceOntologyFileSet extends OntologyFile {
	/**
	 * Returns the value of the '<em><b>Maintenance File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maintenance File Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maintenance File Name</em>' attribute.
	 * @see #setMaintenanceFileName(String)
	 * @see oida.ontologyMgr.OntologyMgrPackage#getLocalReferenceOntologyFileSet_MaintenanceFileName()
	 * @model
	 * @generated
	 */
	String getMaintenanceFileName();

	/**
	 * Sets the value of the '{@link oida.ontologyMgr.LocalReferenceOntologyFileSet#getMaintenanceFileName <em>Maintenance File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maintenance File Name</em>' attribute.
	 * @see #getMaintenanceFileName()
	 * @generated
	 */
	void setMaintenanceFileName(String value);

} // LocalReferenceOntologyFileSet
