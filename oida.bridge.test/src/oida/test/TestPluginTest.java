package oida.test;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.eclipse.emf.common.util.URI;
import org.junit.Test;

import oida.ontology.service.OIDAOntologyService;

public class TestPluginTest {
	@Inject 
    OIDAOntologyService ontologyService; 
	
	@Test
	public void test() {
		//ontologyService.initialize(URI.createURI("http://model.owl"));
		TotalObserver observ2 = new TotalObserver();
        observ2.doStuff();
		fail("Not yet implemented");
	}

}
