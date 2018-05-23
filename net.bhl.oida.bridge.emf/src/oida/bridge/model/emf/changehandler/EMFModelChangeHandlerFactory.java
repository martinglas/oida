package oida.bridge.model.emf.changehandler;

import net.bhl.oida.bridge.model.changehandler.IModelChangeHandler;
import net.bhl.oida.bridge.model.changehandler.IModelChangeHandlerFactory;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-07
 *
 */
public class EMFModelChangeHandlerFactory implements IModelChangeHandlerFactory {
	@Override
	public IModelChangeHandler getChangeHandler() {
		EMFModelChangeHandler changeHandler = new EMFModelChangeHandler();
		return changeHandler;
	}
}
