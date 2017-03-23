/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.owl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import oida.ontology.OntologyClass;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.IOntologyManagerFactory;
import oida.ontologyMgr.OntologyFile;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-02-02
 *
 */
public class OwlManagerTest {
	private static final String TESTONTOLOGY_IRI = "http://de.oida/owl/testontology";
	private static final String TESTFILE = "TestOntology.owl";
	
	private static final String NAMESPACE_DEFAULT = "http://de.oida/test";
	private static final String NAMESPACE_CARS = "http://de.oida/test/cars";
	private static final String NAMESPACE_AIRCRAFT = "http://de.oida/test/aircraft";
	
	private static final String PREFIX_A = "a";
	private static final String PREFIX_B = "b";
	
	private static final String CLASSNAME_VEHICLE = "Vehicle";
	private static final String CLASSNAME_AIRCRAFT = "Aircraft";
	private static final String CLASSNAME_FIXEDWING = "FixedWing";
	
	private IOntologyManagerFactory factory;
	private IOntologyManager manager;
	
	private OntologyFile testFileEntry;
	private OntologyFile loadingTestFileEntry;

	@Before
	public void setUp() throws Exception {
//		factory = new OwlOntologyManagerFactory();
//		manager = factory.getNewManager();
//		
//		manager.createOntology(TESTONTOLOGY_IRI);
//		
//		testFileEntry = OntologyMgrFactory.eINSTANCE.createOntologyFile();
//		testFileEntry.setPath(OntologyTestHelper.getTestOntologyFilePath());
//		testFileEntry.setFileName(TESTFILE);
//		
//		loadingTestFileEntry = OntologyMgrFactory.eINSTANCE.createOntologyFile();
//		loadingTestFileEntry.setPath(OntologyTestHelper.getTestOntologyFilePath());
//		loadingTestFileEntry.setFileName(OntologyTestHelper.getTestReferenceOntologyFileName());
	}

	@Test
	public void testInitializeReasoner() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAddGlobalIRIToLocalPathMapping() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCreateOntology() {
		assertNotNull(manager.getOntology());
	}

	@Test
	public void testSaveAndLoadOntology() {
//		try {
//			manager.saveOntology(testFileEntry);
//			assertTrue(new File(OntologyTestHelper.getTestOntologyFilePath() + TESTFILE).exists());
//			
//			Ontology loadedOntology = manager.loadOntology(testFileEntry);
//			assertNotNull(loadedOntology);
//		} catch (OntologyManagerException e) {
//			e.printStackTrace();
//			fail(e.getMessage());
//		}
	}
	
	@Test
	public void testNamespacePrefixManagementMethods() {
		manager.addNamespace("", NAMESPACE_DEFAULT, true);
		manager.addNamespace(PREFIX_A, NAMESPACE_CARS);
		manager.addNamespace(PREFIX_B, NAMESPACE_AIRCRAFT);
		
		assertEquals(NAMESPACE_CARS, manager.getNamespace(PREFIX_A)); 
		assertEquals(NAMESPACE_DEFAULT, manager.getDefaultNamespace());
		
		assertTrue(manager.isNamespaceExisting(PREFIX_A));
		assertFalse(manager.isNamespaceExisting(PREFIX_B + "a"));
		
		assertEquals(8, manager.getAllNamespaces().keySet().size());
	}

	@Test
	public void testCreateClass() {
		manager.createClass(CLASSNAME_VEHICLE);

		assertNotNull(manager.getClass(CLASSNAME_VEHICLE));
		assertEquals(1, manager.getAllClasses().count());
	}

	@Test
	public void testAssignSubClassToSuperClass() {
		manager.createClass(CLASSNAME_AIRCRAFT);
		manager.createClass(CLASSNAME_FIXEDWING);
		
		manager.assignSubClassToSuperClass(manager.getClass(CLASSNAME_FIXEDWING), manager.getClass(CLASSNAME_AIRCRAFT));
		
		assertEquals(manager.getClass(CLASSNAME_FIXEDWING), manager.getClass(CLASSNAME_AIRCRAFT).getSubClasses().get(0));
	}
	
	@Test
	public void testRenameFunctionality() {
		OntologyClass aClass = manager.createClass("A");
		OntologyClass cClass = manager.createSubClass("C", aClass);
		
		assertNotNull(manager.getClass("A"));
		
		manager.renameEntity(aClass, "B");
		
		assertNotNull(manager.getClass("B"));
		assertEquals(aClass, cClass.getSuperClasses().get(0));
	}

	@Test
	public void testCreateIndividualString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCreateIndividualStringString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAssignIndividualToClass() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetIndividualString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetIndividualStringString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetAllIndividuals() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCreateObjectPropertyStringString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAssignObjectPropertyRange() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAssignObjectPropertyDomain() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCreateAnnotationPropertyStringString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAnnotateClass() {
		fail("Not yet implemented"); // TODO
	}

}
