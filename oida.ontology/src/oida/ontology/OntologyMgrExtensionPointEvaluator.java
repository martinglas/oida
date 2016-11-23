package oida.ontology;

import javax.inject.Inject;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;

import oida.ontology.manager.IOntologyManager;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2016-11-23
 *
 */
class OntologyMgrExtensionPointEvaluator {
	private IConfigurationElement configElement;

	@Inject
	public OntologyMgrExtensionPointEvaluator(IExtensionRegistry registry) {
		IConfigurationElement[] config = registry.getConfigurationElementsFor(Activator.ONTOLOGYMANAGER_EXTENSIONPOINT_ID);

		for (IConfigurationElement e : config) {
			try {
				if (e.createExecutableExtension("class") instanceof IOntologyManager) {
					configElement = e;
					return;
				}
			} catch (CoreException ex) { }
		}
	}

	public IOntologyManager getNewOntologyManager() {
		try {
			return (IOntologyManager)configElement.createExecutableExtension("class");
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		}
	}
}
