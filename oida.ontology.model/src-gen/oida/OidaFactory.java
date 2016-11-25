/**
 */
package oida;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see oida.OidaPackage
 * @generated
 */
public interface OidaFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OidaFactory eINSTANCE = oida.impl.OidaFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>OIDA System</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>OIDA System</em>'.
	 * @generated
	 */
	OIDASystem createOIDASystem();

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
	OidaPackage getOidaPackage();

} //OidaFactory
