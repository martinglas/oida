package oida.ontology;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import oida.ontology.manager.IOntologyManagerFactory;
import oida.ontology.service.OIDAOntologyService;

/**
 * 
 * @author Michael.Shamiyeh
 * @since
 */
public class Activator implements BundleActivator {
	static String ONTOLOGYMANAGER_EXTENSIONPOINT_ID = "oida.ontology.manager";

	private static BundleContext context;

	private URI uri = URI.createFileURI(ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + "//OntLib.onl");

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
		Activator.context = bundleContext;

		ServiceReference<?> serviceReference = context.getServiceReference(IOntologyManagerFactory.class.getName());
		IOntologyManagerFactory factory = (IOntologyManagerFactory)context.getService(serviceReference);

		OIDAOntologyService oidaService = new OIDAOntologyService(factory);
		oidaService.initialize(uri);

		context.registerService(OIDAOntologyService.class.getName(), oidaService, null);
		System.out.println("OIDA Service registered.");
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
