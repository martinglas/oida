package oida.bridge;

import org.eclipse.e4.core.di.InjectorFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import oida.bridge.service.IOIDABridge;
import oida.bridge.service.OIDABridge;

public class Activator implements BundleActivator {
	public final static String OIDA_MODEL_CHANGEHANDLER_RENAMER_EXTENSIONPOINT_ID = "oida.bridge.model.changehandler.renamer";
	
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

}
