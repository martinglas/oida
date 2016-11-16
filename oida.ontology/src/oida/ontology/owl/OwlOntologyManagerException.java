package oida.ontology.owl;

/**
 * 
 * @since 2016-11-10
 * @author Michael.Shamiyeh
 *
 */
@SuppressWarnings("serial")
public class OwlOntologyManagerException extends Exception {
	public OwlOntologyManagerException() {
    }

    public OwlOntologyManagerException(String message) {
        super(message);
    }

    public OwlOntologyManagerException(Throwable cause) {
        super(cause);
    }

    public OwlOntologyManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
