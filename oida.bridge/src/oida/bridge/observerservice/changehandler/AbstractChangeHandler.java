package oida.bridge.observerservice.changehandler;

import org.eclipse.emf.ecore.EObject;

import de.symo.model.base.ModelHelper;
import oida.bridge.util.BridgeHelper;
import oida.ontology.manager.IOntologyManager;

/**
 * 
 * @author Michael Shamiyeh, Martin Glas
 * @since 2017-01-20
 *
 */
public abstract class AbstractChangeHandler implements IChangeHandler {
	private IOntologyManager manager;
	private EObject rootContainerEObject; 
	
	@Override
	public void setOntologyManager(IOntologyManager manager) {
		this.manager = manager;
	}
	
	protected IOntologyManager getOntologyManager() {
		return manager;
	}
	
	protected EObject getRootContainer(EObject eObject){
		if (rootContainerEObject==null){
			this.rootContainerEObject=ModelHelper.getRootContainer(eObject);
		}
		return this.rootContainerEObject;
	}
	
	protected String getModelOntologyNameSpace(EObject eObject) {
		return BridgeHelper.getNSfromRootElementName(ModelHelper.getElementName(getRootContainer(eObject)));
	}
}
