package oida.bridge.test;

import static org.junit.Assert.fail;

import org.junit.Test;

import oida.ontology.service.OIDAOntologyService;

public class TestPluginTest {
	
	@Test
	public void test(OIDAOntologyService ontologyService) {
		
		TotalObserver observ2 = new TotalObserver();
        observ2.doStuff();
		fail("Not yet implemented");
	}

}
