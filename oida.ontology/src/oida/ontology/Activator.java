package oida.ontology;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import oida.ontology.manager.client.AbstractOntologyManagerClient;

public class Activator implements BundleActivator, Observer {
	private static String MANAGERCLIENT_EXTENSIONPOINT_ID = "oida.ontology.manage";
	
	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		
		IExtensionRegistry registry = (IExtensionRegistry)bundleContext.getService(bundleContext.getServiceReference(IExtensionRegistry.class.getName()));
		
		IConfigurationElement[] config = registry.getConfigurationElementsFor(MANAGERCLIENT_EXTENSIONPOINT_ID);
		try {
			for (IConfigurationElement e : config) {
				System.out.println("Evaluating extension");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof AbstractOntologyManagerClient) {
					registerOntologyManagerClient((AbstractOntologyManagerClient)o);
				}
			}
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private void registerOntologyManagerClient(AbstractOntologyManagerClient client) {
		client.addObserver(this);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("Ontology of " + arg0.toString() + " changed!");
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}
}
