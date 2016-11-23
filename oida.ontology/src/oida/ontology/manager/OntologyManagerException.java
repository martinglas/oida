package oida.ontology.manager;

/**
 * 
 * @since 2016-11-10
 * @author Michael.Shamiyeh
 *
 */
@SuppressWarnings("serial")
public class OntologyManagerException extends Exception {
	public OntologyManagerException() {
    }

    public OntologyManagerException(String message) {
        super(message);
    }

    public OntologyManagerException(Throwable cause) {
        super(cause);
    }

    public OntologyManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
