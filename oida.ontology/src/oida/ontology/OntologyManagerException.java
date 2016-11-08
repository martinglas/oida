package oida.ontology;

/**
 *
 * @since 2016-11-08
 * @author Michael.Shamiyeh
 *
 */
public class OntologyManagerException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OntologyManagerException(String message, Exception inner) {
		super(message, inner);
	}
}
