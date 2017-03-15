package oida.core.util.bundle;

import org.eclipse.core.runtime.IExtensionRegistry;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-03-15
 *
 */
public abstract class AbstractOIDAActivator implements BundleActivator {
	private static BundleContext context;

	public static BundleContext getBundleContext() {
		return context;
	}
	
	private static IExtensionRegistry extensionRegistry;
	
	public static IExtensionRegistry getExtensionRegistry() {
		return extensionRegistry;
	}
	
	@Override
	public void start(BundleContext context) throws Exception {
		AbstractOIDAActivator.context = context;
		
		ServiceReference<?> serviceReference = getBundleContext().getServiceReference(IExtensionRegistry.class.getName());
		AbstractOIDAActivator.extensionRegistry = (IExtensionRegistry)AbstractOIDAActivator.getBundleContext().getService(serviceReference);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		AbstractOIDAActivator.context = null;
		AbstractOIDAActivator.extensionRegistry = null;
	}
}
