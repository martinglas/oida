package oida.bridge.test;

import static org.junit.Assert.fail;

import org.junit.Test;

import oida.ontology.service.IOIDAOntologyService;

public class TestPluginTest {
	
	@Test
	public void test(IOIDAOntologyService ontologyService) {
		
		TotalObserver observ2 = new TotalObserver();
        observ2.doStuff();
		fail("Not yet implemented");
	}

}
