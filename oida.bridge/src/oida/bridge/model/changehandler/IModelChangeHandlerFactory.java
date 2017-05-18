package oida.bridge.model.changehandler;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-21
 *
 */
public interface IModelChangeHandlerFactory {
	/**
	 * Creates a new instance of a model change handler.
	 * 
	 * @return Model Change Handler object.
	 */
	IModelChangeHandler getChangeHandler();
}
