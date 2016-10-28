/**
 */
package OIDAOntMgr.impl;

import OIDAOntMgr.GitRepoOntology;
import OIDAOntMgr.OIDAOntMgrPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Git Repo Ontology</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link OIDAOntMgr.impl.GitRepoOntologyImpl#getRepoURL <em>Repo URL</em>}</li>
 *   <li>{@link OIDAOntMgr.impl.GitRepoOntologyImpl#getGitUsername <em>Git Username</em>}</li>
 *   <li>{@link OIDAOntMgr.impl.GitRepoOntologyImpl#getGitPassword <em>Git Password</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GitRepoOntologyImpl extends LocalOntologyImpl implements GitRepoOntology {
	/**
	 * The default value of the '{@link #getRepoURL() <em>Repo URL</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepoURL()
	 * @generated
	 * @ordered
	 */
	protected static final String REPO_URL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRepoURL() <em>Repo URL</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepoURL()
	 * @generated
	 * @ordered
	 */
	protected String repoURL = REPO_URL_EDEFAULT;

	/**
	 * The default value of the '{@link #getGitUsername() <em>Git Username</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGitUsername()
	 * @generated
	 * @ordered
	 */
	protected static final String GIT_USERNAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGitUsername() <em>Git Username</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGitUsername()
	 * @generated
	 * @ordered
	 */
	protected String gitUsername = GIT_USERNAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getGitPassword() <em>Git Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGitPassword()
	 * @generated
	 * @ordered
	 */
	protected static final String GIT_PASSWORD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGitPassword() <em>Git Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGitPassword()
	 * @generated
	 * @ordered
	 */
	protected String gitPassword = GIT_PASSWORD_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GitRepoOntologyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OIDAOntMgrPackage.Literals.GIT_REPO_ONTOLOGY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRepoURL() {
		return repoURL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRepoURL(String newRepoURL) {
		String oldRepoURL = repoURL;
		repoURL = newRepoURL;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OIDAOntMgrPackage.GIT_REPO_ONTOLOGY__REPO_URL, oldRepoURL, repoURL));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGitUsername() {
		return gitUsername;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGitUsername(String newGitUsername) {
		String oldGitUsername = gitUsername;
		gitUsername = newGitUsername;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OIDAOntMgrPackage.GIT_REPO_ONTOLOGY__GIT_USERNAME, oldGitUsername, gitUsername));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGitPassword() {
		return gitPassword;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGitPassword(String newGitPassword) {
		String oldGitPassword = gitPassword;
		gitPassword = newGitPassword;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OIDAOntMgrPackage.GIT_REPO_ONTOLOGY__GIT_PASSWORD, oldGitPassword, gitPassword));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OIDAOntMgrPackage.GIT_REPO_ONTOLOGY__REPO_URL:
				return getRepoURL();
			case OIDAOntMgrPackage.GIT_REPO_ONTOLOGY__GIT_USERNAME:
				return getGitUsername();
			case OIDAOntMgrPackage.GIT_REPO_ONTOLOGY__GIT_PASSWORD:
				return getGitPassword();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case OIDAOntMgrPackage.GIT_REPO_ONTOLOGY__REPO_URL:
				setRepoURL((String)newValue);
				return;
			case OIDAOntMgrPackage.GIT_REPO_ONTOLOGY__GIT_USERNAME:
				setGitUsername((String)newValue);
				return;
			case OIDAOntMgrPackage.GIT_REPO_ONTOLOGY__GIT_PASSWORD:
				setGitPassword((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case OIDAOntMgrPackage.GIT_REPO_ONTOLOGY__REPO_URL:
				setRepoURL(REPO_URL_EDEFAULT);
				return;
			case OIDAOntMgrPackage.GIT_REPO_ONTOLOGY__GIT_USERNAME:
				setGitUsername(GIT_USERNAME_EDEFAULT);
				return;
			case OIDAOntMgrPackage.GIT_REPO_ONTOLOGY__GIT_PASSWORD:
				setGitPassword(GIT_PASSWORD_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case OIDAOntMgrPackage.GIT_REPO_ONTOLOGY__REPO_URL:
				return REPO_URL_EDEFAULT == null ? repoURL != null : !REPO_URL_EDEFAULT.equals(repoURL);
			case OIDAOntMgrPackage.GIT_REPO_ONTOLOGY__GIT_USERNAME:
				return GIT_USERNAME_EDEFAULT == null ? gitUsername != null : !GIT_USERNAME_EDEFAULT.equals(gitUsername);
			case OIDAOntMgrPackage.GIT_REPO_ONTOLOGY__GIT_PASSWORD:
				return GIT_PASSWORD_EDEFAULT == null ? gitPassword != null : !GIT_PASSWORD_EDEFAULT.equals(gitPassword);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (repoURL: ");
		result.append(repoURL);
		result.append(", gitUsername: ");
		result.append(gitUsername);
		result.append(", gitPassword: ");
		result.append(gitPassword);
		result.append(')');
		return result.toString();
	}

} //GitRepoOntologyImpl
