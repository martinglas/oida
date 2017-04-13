package oida.util.bundle;

import org.eclipse.core.runtime.IExtensionRegistry;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTracker;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-03-15
 *
 */
public abstract class AbstractOIDAActivator implements BundleActivator {
	private static BundleContext context;
	
	private ServiceTracker logServiceTracker;
	private static LogService logService;

	public static BundleContext getBundleContext() {
		return context;
	}
	
	public static LogService getLogService() {
		return logService;
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
		
		logServiceTracker = new ServiceTracker(context, LogService.class.getName(), null);
		logServiceTracker.open();
		
		logService = (LogService)logServiceTracker.getService();
		if(logService != null)
			logService.log(LogService.LOG_INFO, "Yee ha, I'm logging!");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		AbstractOIDAActivator.context = null;
		AbstractOIDAActivator.extensionRegistry = null;
		
		logServiceTracker.close();
		logServiceTracker = null;
	}
}
