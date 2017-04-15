package oida.bridge;

import org.eclipse.e4.core.di.InjectorFactory;
import org.osgi.framework.BundleContext;

import oida.bridge.service.IOIDABridge;
import oida.bridge.service.OIDABridge;
import oida.util.bundle.AbstractOIDAActivator;

public class Activator extends AbstractOIDAActivator {
	public final static String OIDA_MODEL_RENAMERSTRATEGY = "oida.bridge.model.renamerstrategy";
	public final static String OIDA_MODEL_STRUCTURINGSTRATEGY = "oida.bridge.model.structuringstrategy";
	public final static String OIDA_RECOMMENDER_PRIMARY_EXTENSIONPOINT_ID = "oida.bridge.recommender.primary";
	public final static String OIDA_RECOMMENDER_SECONDARY_EXTENSIONPOINT_ID = "oida.bridge.recommender.secondary";
	
	private static BundleContext context;

	public static BundleContext getBundleContext() {
		return context;
	}

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		super.start(bundleContext);
		InjectorFactory.getDefault().addBinding(IOIDABridge.class).implementedBy(OIDABridge.class);
	}
}
