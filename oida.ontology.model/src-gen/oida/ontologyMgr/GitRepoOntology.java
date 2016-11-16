/**
 */
package oida.ontologyMgr;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Git Repo Ontology</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link oida.ontologyMgr.GitRepoOntology#getRepoURL <em>Repo URL</em>}</li>
 *   <li>{@link oida.ontologyMgr.GitRepoOntology#getGitUsername <em>Git Username</em>}</li>
 *   <li>{@link oida.ontologyMgr.GitRepoOntology#getGitPassword <em>Git Password</em>}</li>
 * </ul>
 *
 * @see oida.ontologyMgr.OntologyMgrPackage#getGitRepoOntology()
 * @model
 * @generated
 */
public interface GitRepoOntology extends LocalOntology {
	/**
	 * Returns the value of the '<em><b>Repo URL</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Repo URL</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Repo URL</em>' attribute.
	 * @see #setRepoURL(String)
	 * @see oida.ontologyMgr.OntologyMgrPackage#getGitRepoOntology_RepoURL()
	 * @model
	 * @generated
	 */
	String getRepoURL();

	/**
	 * Sets the value of the '{@link oida.ontologyMgr.GitRepoOntology#getRepoURL <em>Repo URL</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Repo URL</em>' attribute.
	 * @see #getRepoURL()
	 * @generated
	 */
	void setRepoURL(String value);

	/**
	 * Returns the value of the '<em><b>Git Username</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Git Username</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Git Username</em>' attribute.
	 * @see #setGitUsername(String)
	 * @see oida.ontologyMgr.OntologyMgrPackage#getGitRepoOntology_GitUsername()
	 * @model
	 * @generated
	 */
	String getGitUsername();

	/**
	 * Sets the value of the '{@link oida.ontologyMgr.GitRepoOntology#getGitUsername <em>Git Username</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Git Username</em>' attribute.
	 * @see #getGitUsername()
	 * @generated
	 */
	void setGitUsername(String value);

	/**
	 * Returns the value of the '<em><b>Git Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Git Password</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Git Password</em>' attribute.
	 * @see #setGitPassword(String)
	 * @see oida.ontologyMgr.OntologyMgrPackage#getGitRepoOntology_GitPassword()
	 * @model
	 * @generated
	 */
	String getGitPassword();

	/**
	 * Sets the value of the '{@link oida.ontologyMgr.GitRepoOntology#getGitPassword <em>Git Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Git Password</em>' attribute.
	 * @see #getGitPassword()
	 * @generated
	 */
	void setGitPassword(String value);

} // GitRepoOntology
