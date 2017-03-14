package oida.bridge;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.e4.core.di.InjectorFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import oida.bridge.service.IOIDABridge;
import oida.bridge.service.OIDABridge;

public class Activator implements BundleActivator {
	public final static String OIDA_MODEL_RENAMERSTRATEGY = "oida.bridge.model.renamerstrategy";
	public final static String OIDA_MODEL_STRUCTURINGSTRATEGY = "oida.bridge.model.structuringstrategy";
	public final static String OIDA_RECOMMENDER_EXTENSIONPOINT_ID = "oida.bridge.recommender";
	
	private static BundleContext context;

	public static BundleContext getBundleContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		InjectorFactory.getDefault().addBinding(IOIDABridge.class).implementedBy(OIDABridge.class);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T loadSingleExtension(Class<T> type, String extensionPointId) throws CoreException {
		ServiceReference<?> serviceReference = getBundleContext().getServiceReference(IExtensionRegistry.class.getName());
		IExtensionRegistry registry = (IExtensionRegistry)Activator.getBundleContext().getService(serviceReference);

		if (registry != null) {
			IConfigurationElement[] config = registry.getConfigurationElementsFor(extensionPointId);
			for (IConfigurationElement e : config) {
					final Object o = e.createExecutableExtension("class");
					if (type.isAssignableFrom(o.getClass()))
						return (T)o;
			}
		}

		return null;
	}
}
