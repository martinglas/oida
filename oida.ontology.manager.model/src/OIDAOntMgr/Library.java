/**
 */
package OIDAOntMgr;

import java.util.Date;

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
 *   <li>{@link OIDAOntMgr.Library#getOwner <em>Owner</em>}</li>
 *   <li>{@link OIDAOntMgr.Library#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link OIDAOntMgr.Library#getOntologies <em>Ontologies</em>}</li>
 * </ul>
 *
 * @see OIDAOntMgr.OIDAOntMgrPackage#getLibrary()
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
	 * @see OIDAOntMgr.OIDAOntMgrPackage#getLibrary_Owner()
	 * @model
	 * @generated
	 */
	String getOwner();

	/**
	 * Sets the value of the '{@link OIDAOntMgr.Library#getOwner <em>Owner</em>}' attribute.
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
	 * @see #setCreationDate(Date)
	 * @see OIDAOntMgr.OIDAOntMgrPackage#getLibrary_CreationDate()
	 * @model
	 * @generated
	 */
	Date getCreationDate();

	/**
	 * Sets the value of the '{@link OIDAOntMgr.Library#getCreationDate <em>Creation Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Creation Date</em>' attribute.
	 * @see #getCreationDate()
	 * @generated
	 */
	void setCreationDate(Date value);

	/**
	 * Returns the value of the '<em><b>Ontologies</b></em>' reference list.
	 * The list contents are of type {@link OIDAOntMgr.LocalOntology}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ontologies</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ontologies</em>' reference list.
	 * @see OIDAOntMgr.OIDAOntMgrPackage#getLibrary_Ontologies()
	 * @model
	 * @generated
	 */
	EList<LocalOntology> getOntologies();

} // Library
