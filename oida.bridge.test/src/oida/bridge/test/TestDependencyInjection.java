package oida.bridge.test;

import static org.junit.Assert.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.junit.Test;

import oida.bridge.dependencytest.InjectedTestClass;
import oida.ontology.service.IOIDAOntologyService;

public class TestDependencyInjection {


	
	
	@Test
	public void test() {
		
		InjectedTestClass testClass=new InjectedTestClass();
		testClass.tryIt();
		
		
		fail("Not yet implemented");
	}

}
