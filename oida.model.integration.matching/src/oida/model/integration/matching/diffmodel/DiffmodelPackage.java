/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package oida.model.integration.matching.diffmodel;

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
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see oida.model.integration.matching.diffmodel.DiffmodelFactory
 * @model kind="package"
 * @generated
 */
public interface DiffmodelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "diffmodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://bhl.net/cdt/matching.diffmodel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "net.bhl.cdt.matching.diffmodel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DiffmodelPackage eINSTANCE = oida.model.integration.matching.diffmodel.impl.DiffmodelPackageImpl.init();

	/**
	 * The meta object id for the '{@link oida.model.integration.matching.diffmodel.impl.ReferenceMatchingImpl <em>Reference Matching</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oida.model.integration.matching.diffmodel.impl.ReferenceMatchingImpl
	 * @see oida.model.integration.matching.diffmodel.impl.DiffmodelPackageImpl#getReferenceMatching()
	 * @generated
	 */
	int REFERENCE_MATCHING = 0;

	/**
	 * The feature id for the '<em><b>Source Reference Mapping</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_MATCHING__SOURCE_REFERENCE_MAPPING = 0;

	/**
	 * The feature id for the '<em><b>Target Reference Mapping</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_MATCHING__TARGET_REFERENCE_MAPPING = 1;

	/**
	 * The feature id for the '<em><b>Confirmed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_MATCHING__CONFIRMED = 2;

	/**
	 * The feature id for the '<em><b>Reported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_MATCHING__REPORTED = 3;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_MATCHING__COMMENT = 4;

	/**
	 * The number of structural features of the '<em>Reference Matching</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_MATCHING_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link oida.model.integration.matching.diffmodel.impl.DiffModelImpl <em>Diff Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oida.model.integration.matching.diffmodel.impl.DiffModelImpl
	 * @see oida.model.integration.matching.diffmodel.impl.DiffmodelPackageImpl#getDiffModel()
	 * @generated
	 */
	int DIFF_MODEL = 1;

	/**
	 * The feature id for the '<em><b>Reference Matchings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIFF_MODEL__REFERENCE_MATCHINGS = 0;

	/**
	 * The feature id for the '<em><b>Their Matchings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIFF_MODEL__THEIR_MATCHINGS = 1;

	/**
	 * The number of structural features of the '<em>Diff Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIFF_MODEL_FEATURE_COUNT = 2;


	/**
	 * Returns the meta object for class '{@link oida.model.integration.matching.diffmodel.ReferenceMatching <em>Reference Matching</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference Matching</em>'.
	 * @see oida.model.integration.matching.diffmodel.ReferenceMatching
	 * @generated
	 */
	EClass getReferenceMatching();

	/**
	 * Returns the meta object for the reference '{@link oida.model.integration.matching.diffmodel.ReferenceMatching#getSourceReferenceMapping <em>Source Reference Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source Reference Mapping</em>'.
	 * @see oida.model.integration.matching.diffmodel.ReferenceMatching#getSourceReferenceMapping()
	 * @see #getReferenceMatching()
	 * @generated
	 */
	EReference getReferenceMatching_SourceReferenceMapping();

	/**
	 * Returns the meta object for the reference '{@link oida.model.integration.matching.diffmodel.ReferenceMatching#getTargetReferenceMapping <em>Target Reference Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target Reference Mapping</em>'.
	 * @see oida.model.integration.matching.diffmodel.ReferenceMatching#getTargetReferenceMapping()
	 * @see #getReferenceMatching()
	 * @generated
	 */
	EReference getReferenceMatching_TargetReferenceMapping();

	/**
	 * Returns the meta object for the attribute '{@link oida.model.integration.matching.diffmodel.ReferenceMatching#isConfirmed <em>Confirmed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Confirmed</em>'.
	 * @see oida.model.integration.matching.diffmodel.ReferenceMatching#isConfirmed()
	 * @see #getReferenceMatching()
	 * @generated
	 */
	EAttribute getReferenceMatching_Confirmed();

	/**
	 * Returns the meta object for the attribute '{@link oida.model.integration.matching.diffmodel.ReferenceMatching#isReported <em>Reported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reported</em>'.
	 * @see oida.model.integration.matching.diffmodel.ReferenceMatching#isReported()
	 * @see #getReferenceMatching()
	 * @generated
	 */
	EAttribute getReferenceMatching_Reported();

	/**
	 * Returns the meta object for the attribute '{@link oida.model.integration.matching.diffmodel.ReferenceMatching#getComment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Comment</em>'.
	 * @see oida.model.integration.matching.diffmodel.ReferenceMatching#getComment()
	 * @see #getReferenceMatching()
	 * @generated
	 */
	EAttribute getReferenceMatching_Comment();

	/**
	 * Returns the meta object for class '{@link oida.model.integration.matching.diffmodel.DiffModel <em>Diff Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diff Model</em>'.
	 * @see oida.model.integration.matching.diffmodel.DiffModel
	 * @generated
	 */
	EClass getDiffModel();

	/**
	 * Returns the meta object for the containment reference list '{@link oida.model.integration.matching.diffmodel.DiffModel#getReferenceMatchings <em>Reference Matchings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Reference Matchings</em>'.
	 * @see oida.model.integration.matching.diffmodel.DiffModel#getReferenceMatchings()
	 * @see #getDiffModel()
	 * @generated
	 */
	EReference getDiffModel_ReferenceMatchings();

	/**
	 * Returns the meta object for the containment reference list '{@link oida.model.integration.matching.diffmodel.DiffModel#getTheirMatchings <em>Their Matchings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Their Matchings</em>'.
	 * @see oida.model.integration.matching.diffmodel.DiffModel#getTheirMatchings()
	 * @see #getDiffModel()
	 * @generated
	 */
	EReference getDiffModel_TheirMatchings();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DiffmodelFactory getDiffmodelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link oida.model.integration.matching.diffmodel.impl.ReferenceMatchingImpl <em>Reference Matching</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oida.model.integration.matching.diffmodel.impl.ReferenceMatchingImpl
		 * @see oida.model.integration.matching.diffmodel.impl.DiffmodelPackageImpl#getReferenceMatching()
		 * @generated
		 */
		EClass REFERENCE_MATCHING = eINSTANCE.getReferenceMatching();

		/**
		 * The meta object literal for the '<em><b>Source Reference Mapping</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE_MATCHING__SOURCE_REFERENCE_MAPPING = eINSTANCE.getReferenceMatching_SourceReferenceMapping();

		/**
		 * The meta object literal for the '<em><b>Target Reference Mapping</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE_MATCHING__TARGET_REFERENCE_MAPPING = eINSTANCE.getReferenceMatching_TargetReferenceMapping();

		/**
		 * The meta object literal for the '<em><b>Confirmed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_MATCHING__CONFIRMED = eINSTANCE.getReferenceMatching_Confirmed();

		/**
		 * The meta object literal for the '<em><b>Reported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_MATCHING__REPORTED = eINSTANCE.getReferenceMatching_Reported();

		/**
		 * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_MATCHING__COMMENT = eINSTANCE.getReferenceMatching_Comment();

		/**
		 * The meta object literal for the '{@link oida.model.integration.matching.diffmodel.impl.DiffModelImpl <em>Diff Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oida.model.integration.matching.diffmodel.impl.DiffModelImpl
		 * @see oida.model.integration.matching.diffmodel.impl.DiffmodelPackageImpl#getDiffModel()
		 * @generated
		 */
		EClass DIFF_MODEL = eINSTANCE.getDiffModel();

		/**
		 * The meta object literal for the '<em><b>Reference Matchings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIFF_MODEL__REFERENCE_MATCHINGS = eINSTANCE.getDiffModel_ReferenceMatchings();

		/**
		 * The meta object literal for the '<em><b>Their Matchings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIFF_MODEL__THEIR_MATCHINGS = eINSTANCE.getDiffModel_TheirMatchings();

	}

} //DiffmodelPackage
