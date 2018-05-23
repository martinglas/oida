package net.bhl.oida.bridge;

import org.eclipse.e4.core.di.InjectorFactory;
import org.osgi.framework.BundleContext;

import net.bhl.oida.bridge.service.OIDABridge;
import net.bhl.oida.util.bundle.AbstractOIDAActivator;

public class Activator extends AbstractOIDAActivator {
    public final static String OIDA_EXTENSIONPOINT_ID_MODEL_CHANGEHANDLER = "oida.bridge.model.changehandler";
    public final static String OIDA_MODEL_RENAMERSTRATEGY = "oida.bridge.model.renamerstrategy";
    public final static String OIDA_MODEL_STRUCTURINGSTRATEGY = "oida.bridge.model.structuringstrategy";
    public static final String OIDA_METAMODELONTOLOGY_PROVIDER_EXTENSIONPOINT_ID = "oida.bridge.model.metamodelontologyprovider";

    public final static String OIDA_RECOMMENDER_PRIMARY_EXTENSIONPOINT_ID = "oida.bridge.recommender.primary";

    public final static String OIDA_RECOMMENDER_SECONDARY_CLASS_EXTENSIONPOINT_ID = "oida.bridge.recommender.secondary.class";
    public final static String OIDA_RECOMMENDER_SECONDARY_OBJECTPROPERTY_EXTENSIONPOINT_ID = "oida.bridge.recommender.secondary.objectproperty";
    public final static String OIDA_RECOMMENDER_SECONDARY_DATATYPEPROPERTY_EXTENSIONPOINT_ID = "oida.bridge.recommender.secondary.datatypeproperty";

    private static BundleContext context;

    public static BundleContext getBundleContext() {
	return context;
    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {
	super.start(bundleContext);
	InjectorFactory.getDefault().addBinding(OIDABridge.class).implementedBy(OIDABridge.class);
    }
}
