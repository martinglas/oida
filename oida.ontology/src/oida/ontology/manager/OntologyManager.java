package oida.ontology.manager;

import java.util.Observable;

import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.e4.core.di.annotations.Execute;

import oida.ontologyMgr.LocalOntology;

public class OntologyManager extends Observable implements IListChangeListener<LocalOntology> {
	private static final String MANAGER_EXTENSIONPOINT_ID = "oida.ontology.managerextensionpoint";

	@Execute
	public void execute(IExtensionRegistry registry) {
		IConfigurationElement[] config = registry.getConfigurationElementsFor(MANAGER_EXTENSIONPOINT_ID);
		try {
			for (IConfigurationElement e : config) {
				System.out.println("Evaluating extension");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof IOntologyManagerClient) {
					registerOntologyManagerClient((IOntologyManagerClient)o);
				}
			}
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void registerOntologyManagerClient(IOntologyManagerClient client) {
		client.getOntologyList().addListChangeListener(this);
	}
	
	@Override
	public void handleListChange(ListChangeEvent<? extends LocalOntology> event) {
		System.out.println("Ontology List changed!!!");
	}
}
