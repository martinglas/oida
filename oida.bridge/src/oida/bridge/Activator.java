package oida.bridge;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import oida.bridge.observerservice.IModelObserverService;
import oida.bridge.observerservice.changehandler.IChangeHandler;
import oida.bridge.observerservice.emf.EMFModelObserverService;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-01-13
 *
 */
public class Activator implements BundleActivator {
	static String OIDABRIDGE_CHANGEHANDLER_EXTENSIONPOINT_ID = "oida.bridge.changehandler";

	@Override
	public void start(BundleContext context) throws Exception {
		context.registerService(IModelObserverService.class.getName(), EMFModelObserverService.getInstance(), null);
		System.out.println("OIDA Bridge: Service registered.");
		
		ServiceReference<?> serviceReference = context.getServiceReference(IExtensionRegistry.class.getName());
		IExtensionRegistry registry = (IExtensionRegistry)context.getService(serviceReference);

		if (registry != null) {
			IConfigurationElement[] config = registry.getConfigurationElementsFor(OIDABRIDGE_CHANGEHANDLER_EXTENSIONPOINT_ID);
			try {
				for (IConfigurationElement e : config) {
					System.out.println("OIDA Bridge: Evaluating model change handler extensions.");
					final Object o = e.createExecutableExtension("class");
					if (o instanceof IChangeHandler) {
						EMFModelObserverService	.getInstance().setChangeHandler((IChangeHandler)o);
						System.out.println("OIDA Bridge: Change handler set: '" + o.getClass().getName() + "'.");
						break;
					}
				}
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	@Override
	public void stop(BundleContext context) throws Exception {
	}
}
