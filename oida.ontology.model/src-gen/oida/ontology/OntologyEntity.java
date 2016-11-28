/**
 */
package oida.ontology;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link oida.ontology.OntologyEntity#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link oida.ontology.OntologyEntity#getContainingOntology <em>Containing Ontology</em>}</li>
 * </ul>
 *
 * @see oida.ontology.OntologyPackage#getOntologyEntity()
 * @model
 * @generated
 */
public interface OntologyEntity extends OntologyItem {
	/**
	 * Returns the value of the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prefix</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prefix</em>' attribute.
	 * @see #setPrefix(String)
	 * @see oida.ontology.OntologyPackage#getOntologyEntity_Prefix()
	 * @model
	 * @generated
	 */
	String getPrefix();

	/**
	 * Sets the value of the '{@link oida.ontology.OntologyEntity#getPrefix <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prefix</em>' attribute.
	 * @see #getPrefix()
	 * @generated
	 */
	void setPrefix(String value);

	/**
	 * Returns the value of the '<em><b>Containing Ontology</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link oida.ontology.Ontology#getEntities <em>Entities</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containing Ontology</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containing Ontology</em>' container reference.
	 * @see #setContainingOntology(Ontology)
	 * @see oida.ontology.OntologyPackage#getOntologyEntity_ContainingOntology()
	 * @see oida.ontology.Ontology#getEntities
	 * @model opposite="entities" required="true" transient="false"
	 * @generated
	 */
	Ontology getContainingOntology();

	/**
	 * Sets the value of the '{@link oida.ontology.OntologyEntity#getContainingOntology <em>Containing Ontology</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Containing Ontology</em>' container reference.
	 * @see #getContainingOntology()
	 * @generated
	 */
	void setContainingOntology(Ontology value);

} // OntologyEntity
