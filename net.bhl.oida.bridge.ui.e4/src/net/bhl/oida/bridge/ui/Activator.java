package net.bhl.oida.bridge.ui;

import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {
    public final static String OIDA_EXTENSIONPOINT_ID_UIARRANGEMENTS = "net.bhl.oida.bridge.ui.e4.uiarrangements";
    
    private static BundleContext context;

    // The plug-in ID
    public static final String PLUGIN_ID = "oida.bridge.ui.e4"; //$NON-NLS-1$

    // The shared instance
    private static Activator plugin;

    private static IExtensionRegistry extensionRegistry;

    public static IExtensionRegistry getExtensionRegistry() {
	return extensionRegistry;
    }

    public Activator() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.
     * BundleContext)
     */
    public void start(BundleContext context) throws Exception {
	super.start(context);
	plugin = this;

	Activator.context = context;

	ServiceReference<?> serviceReference = Activator.context.getServiceReference(IExtensionRegistry.class.getName());
	Activator.extensionRegistry = (IExtensionRegistry)Activator.context.getService(serviceReference);

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.
     * BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
	Activator.context = null;
	Activator.extensionRegistry = null;
	plugin = null;
	super.stop(context);
    }

    /**
     * Returns the shared instance
     *
     * @return the shared instance
     */
    public static Activator getDefault() {
	return plugin;
    }

}
