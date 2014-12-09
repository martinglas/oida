/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package oida.model.integration.matching.diffmodel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Diff Model</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link oida.model.integration.matching.diffmodel.DiffModel#getReferenceMatchings
 * <em>Reference Matchings</em>}</li>
 * <li>
 * {@link oida.model.integration.matching.diffmodel.DiffModel#getTheirMatchings
 * <em>Their Matchings</em>}</li>
 * </ul>
 * </p>
 * 
 * @see oida.model.integration.matching.diffmodel.DiffmodelPackage#getDiffModel()
 * @model
 * @generated
 */
public interface DiffModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Reference Matchings</b></em>'
	 * containment reference list. The list contents are of type
	 * {@link oida.model.integration.matching.diffmodel.ReferenceMatching}
	 * . <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference Matchings</em>' containment
	 * reference list isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Reference Matchings</em>' containment
	 *         reference list.
	 * @see oida.model.integration.matching.diffmodel.DiffmodelPackage#getDiffModel_ReferenceMatchings()
	 * @model containment="true"
	 * @generated
	 */
	EList<ReferenceMatching> getReferenceMatchings();

	/**
	 * Returns the value of the '<em><b>Their Matchings</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link oida.model.integration.matching.diffmodel.ReferenceMatching}
	 * . <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Their Matchings</em>' containment reference
	 * list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Their Matchings</em>' containment reference
	 *         list.
	 * @see oida.model.integration.matching.diffmodel.DiffmodelPackage#getDiffModel_TheirMatchings()
	 * @model containment="true"
	 * @generated
	 */
	EList<ReferenceMatching> getTheirMatchings();

} // DiffModel
