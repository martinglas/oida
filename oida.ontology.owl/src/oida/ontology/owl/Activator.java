package oida.ontology.owl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import oida.ontology.manager.IOntologyManagerFactory;
import oida.ontology.owl.manager.OwlOntologyManagerFactory;

public class Activator implements BundleActivator {

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
		
		OwlOntologyManagerFactory mgrFactory = new OwlOntologyManagerFactory();
		context.registerService(IOntologyManagerFactory.class.getName(), mgrFactory, null);
		System.out.println("OIDA Ontology Manager Factory registered.");
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
