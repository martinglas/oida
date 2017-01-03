package oida.ontology.test;

import static org.junit.Assert.*;

import org.eclipse.emf.common.util.EList;
import org.junit.Before;
import org.junit.Test;

import oida.ontology.Ontology;
import oida.ontology.OntologyClass;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.OntologyManagerException;
import oida.ontology.owl.manager.OwlOntologyManagerFactory;
import oida.ontologyMgr.LocalOntologyEntry;
import oida.ontologyMgr.OntologyMgrFactory;
import oida.test.util.OntologyTestHelper;

public class TestOntologyManager {
	
	private OwlOntologyManagerFactory managerFactory;
	
	private IOntologyManager ontologyManager;
	private Ontology testOntology;

	@Before
	public void prepare(){
		
		managerFactory = new OwlOntologyManagerFactory();
		
		LocalOntologyEntry entry = OntologyMgrFactory.eINSTANCE.createLocalOntologyEntry();

		
		
		String testOntologyPath=OntologyTestHelper.getTestOntologyPath();
		entry.setPath(testOntologyPath);
		entry.setFile("aircraft.owl");
		
		
		
		
		ontologyManager = managerFactory.getNewManager();
		try {
			testOntology=ontologyManager.loadOntology(entry, true);
		} catch (OntologyManagerException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test() {
			OntologyClass aircraftClass=ontologyManager.getClass("Aircraft");
			assertNotNull(aircraftClass);
			assertEquals("Aircraft", aircraftClass.getName());
			
			
	}

}
