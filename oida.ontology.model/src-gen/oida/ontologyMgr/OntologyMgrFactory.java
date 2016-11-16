/**
 */
package oida.ontologyMgr;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see oida.ontologyMgr.OntologyMgrPackage
 * @generated
 */
public interface OntologyMgrFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OntologyMgrFactory eINSTANCE = oida.ontologyMgr.impl.OntologyMgrFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Library</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Library</em>'.
	 * @generated
	 */
	Library createLibrary();

	/**
	 * Returns a new object of class '<em>Local Ontology</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Local Ontology</em>'.
	 * @generated
	 */
	LocalOntology createLocalOntology();

	/**
	 * Returns a new object of class '<em>Git Repo Ontology</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Git Repo Ontology</em>'.
	 * @generated
	 */
	GitRepoOntology createGitRepoOntology();

	/**
	 * Returns a new object of class '<em>Managed Ontology</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Managed Ontology</em>'.
	 * @generated
	 */
	ManagedOntology createManagedOntology();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	OntologyMgrPackage getOntologyMgrPackage();

} //OntologyMgrFactory
