package oida.bridge.observerservice.changehandler;

import oida.ontology.manager.IOntologyManager;

/**
 * 
 * @author Michael Shamiyeh, Martin Glas
 * @since 2017-01-20
 *
 */
public abstract class AbstractChangeHandler implements IChangeHandler {
	private IOntologyManager manager;
	
	@Override
	public void setOntologyManager(IOntologyManager manager) {
		this.manager = manager;
	}
	
	protected IOntologyManager getOntologyManager() {
		return manager;
	}
}
