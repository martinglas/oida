package oida.bridge;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import oida.bridge.model.renamer.IRenamerStrategy;
import oida.bridge.service.IOIDABridge;
import oida.bridge.service.OIDABridge;
import oida.ontology.service.OIDAOntologyService;

public class Activator implements BundleActivator {
	private final String OIDA_MODEL_CHANGEHANDLER_RENAMER_EXTENSIONPOINT_ID = "oida.bridge.model.changehandler.renamer";
	
	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		OIDAOntologyService.getInstance();
		Activator.context = bundleContext;
		
		IOIDABridge bridge = OIDABridge.getInstance();
		bridge.setOntologyService(OIDAOntologyService.getInstance());
		context.registerService(IOIDABridge.class.getName(), bridge, null);
		System.out.println("OIDA Bridge: Service registered.");

		ServiceReference<?> serviceReference = bundleContext.getServiceReference(IExtensionRegistry.class.getName());
		IExtensionRegistry registry = (IExtensionRegistry)context.getService(serviceReference);

		if (registry != null) {
			IConfigurationElement[] config = registry.getConfigurationElementsFor(OIDA_MODEL_CHANGEHANDLER_RENAMER_EXTENSIONPOINT_ID);
			try {
				for (IConfigurationElement e : config) {
					System.out.println("OIDA Bridge: Evaluating model change handler renamer strategy extensions.");
					final Object o = e.createExecutableExtension("class");
					if (o instanceof IRenamerStrategy) {
						bridge.registerRenamerStrategy((IRenamerStrategy)o);
						System.out.println("OIDA Bridge: Renamer strategy set: '" + o.getClass().getName() + "'.");
						break;
					}
				}
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
