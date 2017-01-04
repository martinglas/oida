package oida.ontology.owl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import oida.ontology.Ontology;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyIndividual;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.IOntologyManagerFactory;
import oida.ontology.manager.OntologyManagerException;
import oida.ontology.owl.manager.OwlOntologyManagerFactory;
import oida.ontologyMgr.LocalOntologyEntry;
import oida.ontologyMgr.OntologyMgrFactory;

public class ManagerTest {
	private final String TESTPATH = "C:\\Users\\Michael\\Desktop\\ontology\\";
	private final String TESTFILE = "TestOntology.owl";
	private final String TEST_LOADFILE = "SYMO4PDReference.owl";
	
	private IOntologyManagerFactory factory;
	private IOntologyManager manager;
	
	private LocalOntologyEntry testFileEntry;
	private LocalOntologyEntry loadingTestFileEntry;

	@Before
	public void setUp() throws Exception {
		factory = new OwlOntologyManagerFactory();
		manager = factory.getNewManager();
		
		testFileEntry = OntologyMgrFactory.eINSTANCE.createLocalOntologyEntry();
		testFileEntry.setPath(TESTPATH);
		testFileEntry.setFile(TESTFILE);
		
		loadingTestFileEntry = OntologyMgrFactory.eINSTANCE.createLocalOntologyEntry();
		loadingTestFileEntry.setPath(TESTPATH);
		loadingTestFileEntry.setFile(TEST_LOADFILE);
	}
	
	@Test
	public void testOwlOntologyManager() {
		assertNotNull(manager);
	}
	
	@Test
	public void testCreateOntology() {
		try {
			Ontology ontology = manager.createOntology(testFileEntry);
			assertNotNull(ontology);
		} catch (OntologyManagerException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testLoadOntology() {
		try {
			Ontology ontology = manager.loadOntology(loadingTestFileEntry, false);
			assertNotNull(ontology);
		} catch (OntologyManagerException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testSaveOntology() {
		try {
			manager.createOntology(testFileEntry);
			manager.saveOntology();
			
			assertTrue(new File(TESTPATH + TESTFILE).exists());
		} catch (OntologyManagerException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testClassAndIndividualDeclarations()  {
		try {
			manager.createOntology(testFileEntry);
			
			manager.addNamespace("bhl", "http://www.bauhaus-luftfahrt.net");
			manager.addNamespace("air", "https://www.airbus.com");
			
			OntologyClass aircraft = manager.createClass("Aircraft");
			OntologyClass cLiner = manager.createClass("C-Liner", "bhl");
			OntologyClass a320_bhl = manager.createClass("A320", "bhl");
			OntologyClass a320_air = manager.createSubClass("A320", "air", aircraft);
			
			manager.assignSubClassToSuperClass(cLiner, aircraft);
			manager.assignSubClassToSuperClass(a320_bhl, aircraft);
			
			manager.createIndividual("Class-less Aircraft");
			OntologyIndividual ind = manager.createIndividual("Common Aircraft", "bhl");
			manager.assignIndividualToClass(ind, a320_air);
			
			manager.createIndividualOfClass("Real C-Liner", cLiner);
			manager.createIndividualOfClass("Real A320 BHL", "bhl", "A320", "bhl");		
			
			manager.saveOntology();
		} catch (OntologyManagerException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetClassString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClassStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllClasses() {
		fail("Not yet implemented");
	}
}
