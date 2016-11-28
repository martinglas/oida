package oida.ontology.owl.manager;

import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.IOntologyManagerFactory;

public class OwlOntologyManagerFactory implements IOntologyManagerFactory {
	public OwlOntologyManagerFactory() {
	}
	
	@Override
	public IOntologyManager getNewManager() {
		return (IOntologyManager)new OwlOntologyManager();
	}
}
