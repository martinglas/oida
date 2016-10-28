/**
 */
package OIDAOntMgr;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Local Ontology</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link OIDAOntMgr.LocalOntology#getPath <em>Path</em>}</li>
 *   <li>{@link OIDAOntMgr.LocalOntology#getFile <em>File</em>}</li>
 *   <li>{@link OIDAOntMgr.LocalOntology#getMaintenanceFile <em>Maintenance File</em>}</li>
 * </ul>
 *
 * @see OIDAOntMgr.OIDAOntMgrPackage#getLocalOntology()
 * @model
 * @generated
 */
public interface LocalOntology extends EObject {
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
	 * @see OIDAOntMgr.OIDAOntMgrPackage#getLocalOntology_Path()
	 * @model
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link OIDAOntMgr.LocalOntology#getPath <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(String value);

	/**
	 * Returns the value of the '<em><b>File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File</em>' attribute.
	 * @see #setFile(String)
	 * @see OIDAOntMgr.OIDAOntMgrPackage#getLocalOntology_File()
	 * @model
	 * @generated
	 */
	String getFile();

	/**
	 * Sets the value of the '{@link OIDAOntMgr.LocalOntology#getFile <em>File</em>}' attribute.
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
	 * @see OIDAOntMgr.OIDAOntMgrPackage#getLocalOntology_MaintenanceFile()
	 * @model
	 * @generated
	 */
	String getMaintenanceFile();

	/**
	 * Sets the value of the '{@link OIDAOntMgr.LocalOntology#getMaintenanceFile <em>Maintenance File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maintenance File</em>' attribute.
	 * @see #getMaintenanceFile()
	 * @generated
	 */
	void setMaintenanceFile(String value);

} // LocalOntology
