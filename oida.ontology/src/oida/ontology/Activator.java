package oida.ontology;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import oida.ontology.service.OIDAOntologyService;

public class Activator implements BundleActivator {
	//private static String MANAGERCLIENT_EXTENSIONPOINT_ID = "oida.ontology.manage";
	
	private static BundleContext context;

	private URI uri = URI.createFileURI(ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + "//OntLib.onl");
	
	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		
		OIDAOntologyService oidaService = new OIDAOntologyService();
		
		try {
		oidaService.loadOIDAModel(uri);
		} catch (Exception e) {}
		
		if (oidaService.getLibrary() == null)
			oidaService.initializeOIDAModel(uri);
		
		context.registerService(OIDAOntologyService.class.getName(), oidaService, null);
		System.out.println("OIDA Service registered.");
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}
}
