/**
 */
package OIDAOntMgr;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
 * @see OIDAOntMgr.OIDAOntMgrFactory
 * @model kind="package"
 * @generated
 */
public interface OIDAOntMgrPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "OIDAOntMgr";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.bauhaus-luftfahrt.net/oida/ontmgr/model";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "OIDAOntMgr";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OIDAOntMgrPackage eINSTANCE = OIDAOntMgr.impl.OIDAOntMgrPackageImpl.init();

	/**
	 * The meta object id for the '{@link OIDAOntMgr.impl.LibraryImpl <em>Library</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see OIDAOntMgr.impl.LibraryImpl
	 * @see OIDAOntMgr.impl.OIDAOntMgrPackageImpl#getLibrary()
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
	 * The feature id for the '<em><b>Ontologies</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__ONTOLOGIES = 2;

	/**
	 * The number of structural features of the '<em>Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link OIDAOntMgr.impl.LocalOntologyImpl <em>Local Ontology</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see OIDAOntMgr.impl.LocalOntologyImpl
	 * @see OIDAOntMgr.impl.OIDAOntMgrPackageImpl#getLocalOntology()
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
	 * The meta object id for the '{@link OIDAOntMgr.impl.GitRepoOntologyImpl <em>Git Repo Ontology</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see OIDAOntMgr.impl.GitRepoOntologyImpl
	 * @see OIDAOntMgr.impl.OIDAOntMgrPackageImpl#getGitRepoOntology()
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
	 * The meta object id for the '{@link OIDAOntMgr.impl.ManagedOntologyImpl <em>Managed Ontology</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see OIDAOntMgr.impl.ManagedOntologyImpl
	 * @see OIDAOntMgr.impl.OIDAOntMgrPackageImpl#getManagedOntology()
	 * @generated
	 */
	int MANAGED_ONTOLOGY = 3;

	/**
	 * The feature id for the '<em><b>Local Ontology</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_ONTOLOGY__LOCAL_ONTOLOGY = 0;

	/**
	 * The feature id for the '<em><b>Ontolgy Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_ONTOLOGY__ONTOLGY_MODEL = 1;

	/**
	 * The feature id for the '<em><b>Is Consistent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_ONTOLOGY__IS_CONSISTENT = 2;

	/**
	 * The number of structural features of the '<em>Managed Ontology</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_ONTOLOGY_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Managed Ontology</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_ONTOLOGY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '<em>Ont Model</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.apache.jena.ontology.OntModel
	 * @see OIDAOntMgr.impl.OIDAOntMgrPackageImpl#getOntModel()
	 * @generated
	 */
	int ONT_MODEL = 4;


	/**
	 * Returns the meta object for class '{@link OIDAOntMgr.Library <em>Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Library</em>'.
	 * @see OIDAOntMgr.Library
	 * @generated
	 */
	EClass getLibrary();

	/**
	 * Returns the meta object for the attribute '{@link OIDAOntMgr.Library#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Owner</em>'.
	 * @see OIDAOntMgr.Library#getOwner()
	 * @see #getLibrary()
	 * @generated
	 */
	EAttribute getLibrary_Owner();

	/**
	 * Returns the meta object for the attribute '{@link OIDAOntMgr.Library#getCreationDate <em>Creation Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Creation Date</em>'.
	 * @see OIDAOntMgr.Library#getCreationDate()
	 * @see #getLibrary()
	 * @generated
	 */
	EAttribute getLibrary_CreationDate();

	/**
	 * Returns the meta object for the reference list '{@link OIDAOntMgr.Library#getOntologies <em>Ontologies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Ontologies</em>'.
	 * @see OIDAOntMgr.Library#getOntologies()
	 * @see #getLibrary()
	 * @generated
	 */
	EReference getLibrary_Ontologies();

	/**
	 * Returns the meta object for class '{@link OIDAOntMgr.LocalOntology <em>Local Ontology</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Local Ontology</em>'.
	 * @see OIDAOntMgr.LocalOntology
	 * @generated
	 */
	EClass getLocalOntology();

	/**
	 * Returns the meta object for the attribute '{@link OIDAOntMgr.LocalOntology#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see OIDAOntMgr.LocalOntology#getPath()
	 * @see #getLocalOntology()
	 * @generated
	 */
	EAttribute getLocalOntology_Path();

	/**
	 * Returns the meta object for the attribute '{@link OIDAOntMgr.LocalOntology#getFile <em>File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File</em>'.
	 * @see OIDAOntMgr.LocalOntology#getFile()
	 * @see #getLocalOntology()
	 * @generated
	 */
	EAttribute getLocalOntology_File();

	/**
	 * Returns the meta object for the attribute '{@link OIDAOntMgr.LocalOntology#getMaintenanceFile <em>Maintenance File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maintenance File</em>'.
	 * @see OIDAOntMgr.LocalOntology#getMaintenanceFile()
	 * @see #getLocalOntology()
	 * @generated
	 */
	EAttribute getLocalOntology_MaintenanceFile();

	/**
	 * Returns the meta object for class '{@link OIDAOntMgr.GitRepoOntology <em>Git Repo Ontology</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Git Repo Ontology</em>'.
	 * @see OIDAOntMgr.GitRepoOntology
	 * @generated
	 */
	EClass getGitRepoOntology();

	/**
	 * Returns the meta object for the attribute '{@link OIDAOntMgr.GitRepoOntology#getRepoURL <em>Repo URL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Repo URL</em>'.
	 * @see OIDAOntMgr.GitRepoOntology#getRepoURL()
	 * @see #getGitRepoOntology()
	 * @generated
	 */
	EAttribute getGitRepoOntology_RepoURL();

	/**
	 * Returns the meta object for the attribute '{@link OIDAOntMgr.GitRepoOntology#getGitUsername <em>Git Username</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Git Username</em>'.
	 * @see OIDAOntMgr.GitRepoOntology#getGitUsername()
	 * @see #getGitRepoOntology()
	 * @generated
	 */
	EAttribute getGitRepoOntology_GitUsername();

	/**
	 * Returns the meta object for the attribute '{@link OIDAOntMgr.GitRepoOntology#getGitPassword <em>Git Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Git Password</em>'.
	 * @see OIDAOntMgr.GitRepoOntology#getGitPassword()
	 * @see #getGitRepoOntology()
	 * @generated
	 */
	EAttribute getGitRepoOntology_GitPassword();

	/**
	 * Returns the meta object for class '{@link OIDAOntMgr.ManagedOntology <em>Managed Ontology</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Managed Ontology</em>'.
	 * @see OIDAOntMgr.ManagedOntology
	 * @generated
	 */
	EClass getManagedOntology();

	/**
	 * Returns the meta object for the reference '{@link OIDAOntMgr.ManagedOntology#getLocalOntology <em>Local Ontology</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Local Ontology</em>'.
	 * @see OIDAOntMgr.ManagedOntology#getLocalOntology()
	 * @see #getManagedOntology()
	 * @generated
	 */
	EReference getManagedOntology_LocalOntology();

	/**
	 * Returns the meta object for the attribute '{@link OIDAOntMgr.ManagedOntology#getOntolgyModel <em>Ontolgy Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ontolgy Model</em>'.
	 * @see OIDAOntMgr.ManagedOntology#getOntolgyModel()
	 * @see #getManagedOntology()
	 * @generated
	 */
	EAttribute getManagedOntology_OntolgyModel();

	/**
	 * Returns the meta object for the attribute '{@link OIDAOntMgr.ManagedOntology#isIsConsistent <em>Is Consistent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Consistent</em>'.
	 * @see OIDAOntMgr.ManagedOntology#isIsConsistent()
	 * @see #getManagedOntology()
	 * @generated
	 */
	EAttribute getManagedOntology_IsConsistent();

	/**
	 * Returns the meta object for data type '{@link org.apache.jena.ontology.OntModel <em>Ont Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Ont Model</em>'.
	 * @see org.apache.jena.ontology.OntModel
	 * @model instanceClass="org.apache.jena.ontology.OntModel"
	 * @generated
	 */
	EDataType getOntModel();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	OIDAOntMgrFactory getOIDAOntMgrFactory();

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
		 * The meta object literal for the '{@link OIDAOntMgr.impl.LibraryImpl <em>Library</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see OIDAOntMgr.impl.LibraryImpl
		 * @see OIDAOntMgr.impl.OIDAOntMgrPackageImpl#getLibrary()
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
		 * The meta object literal for the '<em><b>Ontologies</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIBRARY__ONTOLOGIES = eINSTANCE.getLibrary_Ontologies();

		/**
		 * The meta object literal for the '{@link OIDAOntMgr.impl.LocalOntologyImpl <em>Local Ontology</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see OIDAOntMgr.impl.LocalOntologyImpl
		 * @see OIDAOntMgr.impl.OIDAOntMgrPackageImpl#getLocalOntology()
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
		 * The meta object literal for the '{@link OIDAOntMgr.impl.GitRepoOntologyImpl <em>Git Repo Ontology</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see OIDAOntMgr.impl.GitRepoOntologyImpl
		 * @see OIDAOntMgr.impl.OIDAOntMgrPackageImpl#getGitRepoOntology()
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
		 * The meta object literal for the '{@link OIDAOntMgr.impl.ManagedOntologyImpl <em>Managed Ontology</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see OIDAOntMgr.impl.ManagedOntologyImpl
		 * @see OIDAOntMgr.impl.OIDAOntMgrPackageImpl#getManagedOntology()
		 * @generated
		 */
		EClass MANAGED_ONTOLOGY = eINSTANCE.getManagedOntology();

		/**
		 * The meta object literal for the '<em><b>Local Ontology</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MANAGED_ONTOLOGY__LOCAL_ONTOLOGY = eINSTANCE.getManagedOntology_LocalOntology();

		/**
		 * The meta object literal for the '<em><b>Ontolgy Model</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANAGED_ONTOLOGY__ONTOLGY_MODEL = eINSTANCE.getManagedOntology_OntolgyModel();

		/**
		 * The meta object literal for the '<em><b>Is Consistent</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANAGED_ONTOLOGY__IS_CONSISTENT = eINSTANCE.getManagedOntology_IsConsistent();

		/**
		 * The meta object literal for the '<em>Ont Model</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.apache.jena.ontology.OntModel
		 * @see OIDAOntMgr.impl.OIDAOntMgrPackageImpl#getOntModel()
		 * @generated
		 */
		EDataType ONT_MODEL = eINSTANCE.getOntModel();

	}

} //OIDAOntMgrPackage
