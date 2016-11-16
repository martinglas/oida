/**
 */
package oida.ontologyMgr;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see oida.ontologyMgr.OntologyMgrFactory
 * @model kind="package"
 * @generated
 */
public interface OntologyMgrPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ontologyMgr";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.bauhaus-luftfahrt.net/oida/ontologyMgr";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ontologyMgr";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OntologyMgrPackage eINSTANCE = oida.ontologyMgr.impl.OntologyMgrPackageImpl.init();

	/**
	 * The meta object id for the '{@link oida.ontologyMgr.impl.LibraryImpl <em>Library</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oida.ontologyMgr.impl.LibraryImpl
	 * @see oida.ontologyMgr.impl.OntologyMgrPackageImpl#getLibrary()
	 * @generated
	 */
	int LIBRARY = 0;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__OWNER = 0;

	/**
	 * The feature id for the '<em><b>Creation Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__CREATION_DATE = 1;

	/**
	 * The feature id for the '<em><b>Ontologies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__ONTOLOGIES = 2;

	/**
	 * The feature id for the '<em><b>Active Ontology</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__ACTIVE_ONTOLOGY = 3;

	/**
	 * The number of structural features of the '<em>Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link oida.ontologyMgr.impl.LocalOntologyImpl <em>Local Ontology</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oida.ontologyMgr.impl.LocalOntologyImpl
	 * @see oida.ontologyMgr.impl.OntologyMgrPackageImpl#getLocalOntology()
	 * @generated
	 */
	int LOCAL_ONTOLOGY = 1;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_ONTOLOGY__PATH = 0;

	/**
	 * The feature id for the '<em><b>File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_ONTOLOGY__FILE = 1;

	/**
	 * The feature id for the '<em><b>Maintenance File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_ONTOLOGY__MAINTENANCE_FILE = 2;

	/**
	 * The number of structural features of the '<em>Local Ontology</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_ONTOLOGY_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Local Ontology</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_ONTOLOGY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link oida.ontologyMgr.impl.GitRepoOntologyImpl <em>Git Repo Ontology</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oida.ontologyMgr.impl.GitRepoOntologyImpl
	 * @see oida.ontologyMgr.impl.OntologyMgrPackageImpl#getGitRepoOntology()
	 * @generated
	 */
	int GIT_REPO_ONTOLOGY = 2;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPO_ONTOLOGY__PATH = LOCAL_ONTOLOGY__PATH;

	/**
	 * The feature id for the '<em><b>File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPO_ONTOLOGY__FILE = LOCAL_ONTOLOGY__FILE;

	/**
	 * The feature id for the '<em><b>Maintenance File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPO_ONTOLOGY__MAINTENANCE_FILE = LOCAL_ONTOLOGY__MAINTENANCE_FILE;

	/**
	 * The feature id for the '<em><b>Repo URL</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPO_ONTOLOGY__REPO_URL = LOCAL_ONTOLOGY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Git Username</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPO_ONTOLOGY__GIT_USERNAME = LOCAL_ONTOLOGY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Git Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPO_ONTOLOGY__GIT_PASSWORD = LOCAL_ONTOLOGY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Git Repo Ontology</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPO_ONTOLOGY_FEATURE_COUNT = LOCAL_ONTOLOGY_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Git Repo Ontology</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPO_ONTOLOGY_OPERATION_COUNT = LOCAL_ONTOLOGY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link oida.ontologyMgr.impl.ManagedOntologyImpl <em>Managed Ontology</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oida.ontologyMgr.impl.ManagedOntologyImpl
	 * @see oida.ontologyMgr.impl.OntologyMgrPackageImpl#getManagedOntology()
	 * @generated
	 */
	int MANAGED_ONTOLOGY = 3;

	/**
	 * The feature id for the '<em><b>Is Consistent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_ONTOLOGY__IS_CONSISTENT = 0;

	/**
	 * The feature id for the '<em><b>Local Ontology</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_ONTOLOGY__LOCAL_ONTOLOGY = 1;

	/**
	 * The number of structural features of the '<em>Managed Ontology</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_ONTOLOGY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Managed Ontology</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_ONTOLOGY_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link oida.ontologyMgr.Library <em>Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Library</em>'.
	 * @see oida.ontologyMgr.Library
	 * @generated
	 */
	EClass getLibrary();

	/**
	 * Returns the meta object for the attribute '{@link oida.ontologyMgr.Library#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Owner</em>'.
	 * @see oida.ontologyMgr.Library#getOwner()
	 * @see #getLibrary()
	 * @generated
	 */
	EAttribute getLibrary_Owner();

	/**
	 * Returns the meta object for the attribute '{@link oida.ontologyMgr.Library#getCreationDate <em>Creation Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Creation Date</em>'.
	 * @see oida.ontologyMgr.Library#getCreationDate()
	 * @see #getLibrary()
	 * @generated
	 */
	EAttribute getLibrary_CreationDate();

	/**
	 * Returns the meta object for the containment reference list '{@link oida.ontologyMgr.Library#getOntologies <em>Ontologies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ontologies</em>'.
	 * @see oida.ontologyMgr.Library#getOntologies()
	 * @see #getLibrary()
	 * @generated
	 */
	EReference getLibrary_Ontologies();

	/**
	 * Returns the meta object for the reference '{@link oida.ontologyMgr.Library#getActiveOntology <em>Active Ontology</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Active Ontology</em>'.
	 * @see oida.ontologyMgr.Library#getActiveOntology()
	 * @see #getLibrary()
	 * @generated
	 */
	EReference getLibrary_ActiveOntology();

	/**
	 * Returns the meta object for class '{@link oida.ontologyMgr.LocalOntology <em>Local Ontology</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Local Ontology</em>'.
	 * @see oida.ontologyMgr.LocalOntology
	 * @generated
	 */
	EClass getLocalOntology();

	/**
	 * Returns the meta object for the attribute '{@link oida.ontologyMgr.LocalOntology#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see oida.ontologyMgr.LocalOntology#getPath()
	 * @see #getLocalOntology()
	 * @generated
	 */
	EAttribute getLocalOntology_Path();

	/**
	 * Returns the meta object for the attribute '{@link oida.ontologyMgr.LocalOntology#getFile <em>File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File</em>'.
	 * @see oida.ontologyMgr.LocalOntology#getFile()
	 * @see #getLocalOntology()
	 * @generated
	 */
	EAttribute getLocalOntology_File();

	/**
	 * Returns the meta object for the attribute '{@link oida.ontologyMgr.LocalOntology#getMaintenanceFile <em>Maintenance File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maintenance File</em>'.
	 * @see oida.ontologyMgr.LocalOntology#getMaintenanceFile()
	 * @see #getLocalOntology()
	 * @generated
	 */
	EAttribute getLocalOntology_MaintenanceFile();

	/**
	 * Returns the meta object for class '{@link oida.ontologyMgr.GitRepoOntology <em>Git Repo Ontology</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Git Repo Ontology</em>'.
	 * @see oida.ontologyMgr.GitRepoOntology
	 * @generated
	 */
	EClass getGitRepoOntology();

	/**
	 * Returns the meta object for the attribute '{@link oida.ontologyMgr.GitRepoOntology#getRepoURL <em>Repo URL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Repo URL</em>'.
	 * @see oida.ontologyMgr.GitRepoOntology#getRepoURL()
	 * @see #getGitRepoOntology()
	 * @generated
	 */
	EAttribute getGitRepoOntology_RepoURL();

	/**
	 * Returns the meta object for the attribute '{@link oida.ontologyMgr.GitRepoOntology#getGitUsername <em>Git Username</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Git Username</em>'.
	 * @see oida.ontologyMgr.GitRepoOntology#getGitUsername()
	 * @see #getGitRepoOntology()
	 * @generated
	 */
	EAttribute getGitRepoOntology_GitUsername();

	/**
	 * Returns the meta object for the attribute '{@link oida.ontologyMgr.GitRepoOntology#getGitPassword <em>Git Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Git Password</em>'.
	 * @see oida.ontologyMgr.GitRepoOntology#getGitPassword()
	 * @see #getGitRepoOntology()
	 * @generated
	 */
	EAttribute getGitRepoOntology_GitPassword();

	/**
	 * Returns the meta object for class '{@link oida.ontologyMgr.ManagedOntology <em>Managed Ontology</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Managed Ontology</em>'.
	 * @see oida.ontologyMgr.ManagedOntology
	 * @generated
	 */
	EClass getManagedOntology();

	/**
	 * Returns the meta object for the attribute '{@link oida.ontologyMgr.ManagedOntology#isIsConsistent <em>Is Consistent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Consistent</em>'.
	 * @see oida.ontologyMgr.ManagedOntology#isIsConsistent()
	 * @see #getManagedOntology()
	 * @generated
	 */
	EAttribute getManagedOntology_IsConsistent();

	/**
	 * Returns the meta object for the reference '{@link oida.ontologyMgr.ManagedOntology#getLocalOntology <em>Local Ontology</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Local Ontology</em>'.
	 * @see oida.ontologyMgr.ManagedOntology#getLocalOntology()
	 * @see #getManagedOntology()
	 * @generated
	 */
	EReference getManagedOntology_LocalOntology();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	OntologyMgrFactory getOntologyMgrFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link oida.ontologyMgr.impl.LibraryImpl <em>Library</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oida.ontologyMgr.impl.LibraryImpl
		 * @see oida.ontologyMgr.impl.OntologyMgrPackageImpl#getLibrary()
		 * @generated
		 */
		EClass LIBRARY = eINSTANCE.getLibrary();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LIBRARY__OWNER = eINSTANCE.getLibrary_Owner();

		/**
		 * The meta object literal for the '<em><b>Creation Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LIBRARY__CREATION_DATE = eINSTANCE.getLibrary_CreationDate();

		/**
		 * The meta object literal for the '<em><b>Ontologies</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIBRARY__ONTOLOGIES = eINSTANCE.getLibrary_Ontologies();

		/**
		 * The meta object literal for the '<em><b>Active Ontology</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIBRARY__ACTIVE_ONTOLOGY = eINSTANCE.getLibrary_ActiveOntology();

		/**
		 * The meta object literal for the '{@link oida.ontologyMgr.impl.LocalOntologyImpl <em>Local Ontology</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oida.ontologyMgr.impl.LocalOntologyImpl
		 * @see oida.ontologyMgr.impl.OntologyMgrPackageImpl#getLocalOntology()
		 * @generated
		 */
		EClass LOCAL_ONTOLOGY = eINSTANCE.getLocalOntology();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCAL_ONTOLOGY__PATH = eINSTANCE.getLocalOntology_Path();

		/**
		 * The meta object literal for the '<em><b>File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCAL_ONTOLOGY__FILE = eINSTANCE.getLocalOntology_File();

		/**
		 * The meta object literal for the '<em><b>Maintenance File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCAL_ONTOLOGY__MAINTENANCE_FILE = eINSTANCE.getLocalOntology_MaintenanceFile();

		/**
		 * The meta object literal for the '{@link oida.ontologyMgr.impl.GitRepoOntologyImpl <em>Git Repo Ontology</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oida.ontologyMgr.impl.GitRepoOntologyImpl
		 * @see oida.ontologyMgr.impl.OntologyMgrPackageImpl#getGitRepoOntology()
		 * @generated
		 */
		EClass GIT_REPO_ONTOLOGY = eINSTANCE.getGitRepoOntology();

		/**
		 * The meta object literal for the '<em><b>Repo URL</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_REPO_ONTOLOGY__REPO_URL = eINSTANCE.getGitRepoOntology_RepoURL();

		/**
		 * The meta object literal for the '<em><b>Git Username</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_REPO_ONTOLOGY__GIT_USERNAME = eINSTANCE.getGitRepoOntology_GitUsername();

		/**
		 * The meta object literal for the '<em><b>Git Password</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_REPO_ONTOLOGY__GIT_PASSWORD = eINSTANCE.getGitRepoOntology_GitPassword();

		/**
		 * The meta object literal for the '{@link oida.ontologyMgr.impl.ManagedOntologyImpl <em>Managed Ontology</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oida.ontologyMgr.impl.ManagedOntologyImpl
		 * @see oida.ontologyMgr.impl.OntologyMgrPackageImpl#getManagedOntology()
		 * @generated
		 */
		EClass MANAGED_ONTOLOGY = eINSTANCE.getManagedOntology();

		/**
		 * The meta object literal for the '<em><b>Is Consistent</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANAGED_ONTOLOGY__IS_CONSISTENT = eINSTANCE.getManagedOntology_IsConsistent();

		/**
		 * The meta object literal for the '<em><b>Local Ontology</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MANAGED_ONTOLOGY__LOCAL_ONTOLOGY = eINSTANCE.getManagedOntology_LocalOntology();

	}

} //OntologyMgrPackage
