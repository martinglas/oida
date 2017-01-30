package oida.bridge.dependencytest;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import oida.ontology.service.IOIDAOntologyService;

public class InjectedTestClass {
	
	
	
	
	@Inject
	IOIDAOntologyService oidaService;
	
	

	public void tryIt() {
		// Guice injector
		oidaService.getManagedOntologies();
		oidaService.getManagedOntologies();
		
	}
}
