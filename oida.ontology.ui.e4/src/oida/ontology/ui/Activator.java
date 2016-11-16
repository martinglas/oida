package oida.ontology.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import oida.ontology.ui.provider.IOidaUIDataProvider;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {
	// The plug-in ID
	public static final String PLUGIN_ID = "oida.ontology.ui.e4"; //$NON-NLS-1$
	
	// OIDA UI Data Provider Extension point id:
	public static final String EXTP_UIDATAPROVIDER = "oida.ontolog.ui.e4.dataprovider"; //$NON-NLS-1$
	
	// The shared instance
	private static Activator plugin;
	
	public static IOidaUIDataProvider provider;

	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		IExtensionRegistry registry = (IExtensionRegistry)context.getService(context.getServiceReference(IExtensionRegistry.class.getName()));
		
		IConfigurationElement[] config = registry.getConfigurationElementsFor(EXTP_UIDATAPROVIDER);
		try {
			for (IConfigurationElement e : config) {
				System.out.println("Evaluating extension");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof IOidaUIDataProvider) {
					provider = (IOidaUIDataProvider)o;
					break;
				}
			}
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
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
