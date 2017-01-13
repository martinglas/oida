package oida.bridge;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import oida.bridge.observerservice.IModelObserverService;
import oida.bridge.observerservice.ModelObserverService;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-01-13
 *
 */
public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		context.registerService(IModelObserverService.class.getName(), ModelObserverService.getInstance(), null);
		System.out.println("OIDA Bridge: Service registered.");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
	}
}
