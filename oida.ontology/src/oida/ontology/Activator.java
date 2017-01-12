package oida.ontology;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.emf.common.util.URI;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import oida.ontology.manager.IOntologyManagerFactory;
import oida.ontology.service.IOIDAOntologyService;
import oida.ontology.service.OIDAOntologyService;

/**
 * 
 * @author Michael.Shamiyeh
 * @since
 */
public class Activator implements BundleActivator {
	static String ONTOLOGYMANAGERFACTORY_EXTENSIONPOINT_ID = "oida.ontology.managerFactory";

	private static BundleContext context;

	private URI uri = URI.createFileURI(ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + "OntLib.onl");

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("SYMO4PD OIDA Service: Initialization started.");
		Activator.context = bundleContext;

		ServiceReference<?> serviceReference = context.getServiceReference(IExtensionRegistry.class.getName());
		IExtensionRegistry registry = (IExtensionRegistry)context.getService(serviceReference);

		OIDAOntologyService oidaService = new OIDAOntologyService();
		
		if (registry != null) {
			IConfigurationElement[] config = registry.getConfigurationElementsFor(ONTOLOGYMANAGERFACTORY_EXTENSIONPOINT_ID);
			try {
				for (IConfigurationElement e : config) {
					System.out.println("SYMO4PD OIDA Service: Evaluating ontology manager extensions.");
					final Object o = e.createExecutableExtension("class");
					if (o instanceof IOntologyManagerFactory) {
						oidaService.initialize(uri, (IOntologyManagerFactory)o);
						System.out.println("SYMO4PD OIDA Service: Initialized with manager '" + o.getClass().getName() + "'.");
						break;
					}
				}
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}

		context.registerService(IOIDAOntologyService.class.getName(), oidaService, null);
		System.out.println("SYMO4PD OIDA Service: Service registered.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}
}
