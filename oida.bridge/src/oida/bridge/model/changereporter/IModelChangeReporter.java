package oida.bridge.model.changereporter;

import oida.bridge.IOIDABridge;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-03-03
 * 
 * @param <T>
 */
public interface IModelChangeReporter<T> {
	void registerOIDABridge(IOIDABridge bridge);
	
	void addModelForObservation(T modelObject);
	
	void releaseObservedModel(T modelObject);
}
