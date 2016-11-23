/**
 */
package oida.ontology;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Individual</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link oida.ontology.OntologyIndividual#getTypes <em>Types</em>}</li>
 * </ul>
 *
 * @see oida.ontology.OntologyPackage#getOntologyIndividual()
 * @model
 * @generated
 */
public interface OntologyIndividual extends OntologyEntity {
	/**
	 * Returns the value of the '<em><b>Types</b></em>' reference list.
	 * The list contents are of type {@link oida.ontology.OntologyClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Types</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Types</em>' reference list.
	 * @see oida.ontology.OntologyPackage#getOntologyIndividual_Types()
	 * @model
	 * @generated
	 */
	EList<OntologyClass> getTypes();

} // OntologyIndividual
