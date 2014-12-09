/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package oida.model.integration.matching.diffmodel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see oida.model.integration.matching.diffmodel.DiffmodelPackage
 * @generated
 */
public interface DiffmodelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DiffmodelFactory eINSTANCE = oida.model.integration.matching.diffmodel.impl.DiffmodelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Reference Matching</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reference Matching</em>'.
	 * @generated
	 */
	ReferenceMatching createReferenceMatching();

	/**
	 * Returns a new object of class '<em>Diff Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Diff Model</em>'.
	 * @generated
	 */
	DiffModel createDiffModel();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DiffmodelPackage getDiffmodelPackage();

} //DiffmodelFactory
