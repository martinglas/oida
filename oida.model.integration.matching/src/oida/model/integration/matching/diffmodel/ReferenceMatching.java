/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package oida.model.integration.matching.diffmodel;

import oida.model.integration.transformation.mapping.ReferenceMapping;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference Matching</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link oida.model.integration.matching.diffmodel.ReferenceMatching#getSourceReferenceMapping <em>Source Reference Mapping</em>}</li>
 *   <li>{@link oida.model.integration.matching.diffmodel.ReferenceMatching#getTargetReferenceMapping <em>Target Reference Mapping</em>}</li>
 *   <li>{@link oida.model.integration.matching.diffmodel.ReferenceMatching#isConfirmed <em>Confirmed</em>}</li>
 *   <li>{@link oida.model.integration.matching.diffmodel.ReferenceMatching#isReported <em>Reported</em>}</li>
 *   <li>{@link oida.model.integration.matching.diffmodel.ReferenceMatching#getComment <em>Comment</em>}</li>
 * </ul>
 * </p>
 *
 * @see oida.model.integration.matching.diffmodel.DiffmodelPackage#getReferenceMatching()
 * @model
 * @generated
 */
public interface ReferenceMatching extends EObject {
	/**
	 * Returns the value of the '<em><b>Source Reference Mapping</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Reference Mapping</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Reference Mapping</em>' reference.
	 * @see #setSourceReferenceMapping(ReferenceMapping)
	 * @see oida.model.integration.matching.diffmodel.DiffmodelPackage#getReferenceMatching_SourceReferenceMapping()
	 * @model
	 * @generated
	 */
	ReferenceMapping getSourceReferenceMapping();

	/**
	 * Sets the value of the '{@link oida.model.integration.matching.diffmodel.ReferenceMatching#getSourceReferenceMapping <em>Source Reference Mapping</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Reference Mapping</em>' reference.
	 * @see #getSourceReferenceMapping()
	 * @generated
	 */
	void setSourceReferenceMapping(ReferenceMapping value);

	/**
	 * Returns the value of the '<em><b>Target Reference Mapping</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Reference Mapping</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Reference Mapping</em>' reference.
	 * @see #setTargetReferenceMapping(ReferenceMapping)
	 * @see oida.model.integration.matching.diffmodel.DiffmodelPackage#getReferenceMatching_TargetReferenceMapping()
	 * @model
	 * @generated
	 */
	ReferenceMapping getTargetReferenceMapping();

	/**
	 * Sets the value of the '{@link oida.model.integration.matching.diffmodel.ReferenceMatching#getTargetReferenceMapping <em>Target Reference Mapping</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Reference Mapping</em>' reference.
	 * @see #getTargetReferenceMapping()
	 * @generated
	 */
	void setTargetReferenceMapping(ReferenceMapping value);

	/**
	 * Returns the value of the '<em><b>Confirmed</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Confirmed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Confirmed</em>' attribute.
	 * @see #setConfirmed(boolean)
	 * @see oida.model.integration.matching.diffmodel.DiffmodelPackage#getReferenceMatching_Confirmed()
	 * @model default="false"
	 * @generated
	 */
	boolean isConfirmed();

	/**
	 * Sets the value of the '{@link oida.model.integration.matching.diffmodel.ReferenceMatching#isConfirmed <em>Confirmed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Confirmed</em>' attribute.
	 * @see #isConfirmed()
	 * @generated
	 */
	void setConfirmed(boolean value);

	/**
	 * Returns the value of the '<em><b>Reported</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reported</em>' attribute.
	 * @see #setReported(boolean)
	 * @see oida.model.integration.matching.diffmodel.DiffmodelPackage#getReferenceMatching_Reported()
	 * @model default="false"
	 * @generated
	 */
	boolean isReported();

	/**
	 * Sets the value of the '{@link oida.model.integration.matching.diffmodel.ReferenceMatching#isReported <em>Reported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reported</em>' attribute.
	 * @see #isReported()
	 * @generated
	 */
	void setReported(boolean value);

	/**
	 * Returns the value of the '<em><b>Comment</b></em>' attribute.
	 * The default value is <code>" "</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comment</em>' attribute.
	 * @see #setComment(String)
	 * @see oida.model.integration.matching.diffmodel.DiffmodelPackage#getReferenceMatching_Comment()
	 * @model default=" "
	 * @generated
	 */
	String getComment();

	/**
	 * Sets the value of the '{@link oida.model.integration.matching.diffmodel.ReferenceMatching#getComment <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Comment</em>' attribute.
	 * @see #getComment()
	 * @generated
	 */
	void setComment(String value);

} // ReferenceMatching
