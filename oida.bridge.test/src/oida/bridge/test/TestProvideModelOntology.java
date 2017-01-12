package oida.bridge.test;

import static org.junit.Assert.fail;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.junit.Before;
import org.junit.Test;

import de.symo.model.element.ElementFactory;
import de.symo.model.element.MetaData;
import de.symo.model.symo.ProjectRepository;
import de.symo.model.symo.SymoFactory;
import de.symo.model.symo.SystemElement;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.IOntologyManagerFactory;
import oida.ontology.owl.manager.OwlOntologyManagerFactory;
import oida.ontologyMgr.OntologyFile;
import oida.ontologyMgr.OntologyMgrFactory;
import oida.test.util.OntologyTestHelper;


public class TestProvideModelOntology {

	private IOntologyManager ontologyManager;
	private OntologyFile testFileEntry;
	private OntologyFile loadingTestFileEntry;
	
	private ProjectRepository projectRepository;
	
	private final static String MODEL_ONTOLOGY_TEST_FILE_NAME="test_model_ontology.owl";
	
	@Before
	public void prepare(){
		IOntologyManagerFactory ontologyManagerFactory = new OwlOntologyManagerFactory();
		ontologyManager = ontologyManagerFactory.getNewManager();
		
		testFileEntry = OntologyMgrFactory.eINSTANCE.createOntologyFile();
		testFileEntry.setPath(OntologyTestHelper.getTestOntologyFilePath());
		testFileEntry.setFileName(MODEL_ONTOLOGY_TEST_FILE_NAME);
		
		loadingTestFileEntry = OntologyMgrFactory.eINSTANCE.createOntologyFile();
		loadingTestFileEntry.setPath(OntologyTestHelper.getTestOntologyFilePath());
		loadingTestFileEntry.setFileName(OntologyTestHelper.getTestReferenceOntologyFileName());
		
		//Create ProjectRepository and meta data
		projectRepository=SymoFactory.eINSTANCE.createProjectRepository();
		MetaData projectName=ElementFactory.eINSTANCE.createMetaData();
		//TODO the following attribute should be part of the meta model ontology
		projectName.setName("Project name");
		
		projectName.setValue("Test project");
		projectRepository.getMetaData().add(projectName);
		
		//TODO Hooking the listener to ARepository Instances should be a standard feature.
		EContentAdapter adapter = new EContentAdapter() {
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				ChangeHandler.handle(notification);
				// System.out.println("Notification received from the data model.
				// Data model has changed!!!");
			}
		};
		projectRepository.eAdapters().add(adapter);
		
		
		//create Component Repository
	}
	
	@Test
	public void test() {
		//TODO Load tool ontology has been loaded into ontology manager on startup.
		SymoFactory symoFactory=SymoFactory.eINSTANCE;
		

		
		
		
		SystemElement leftWingSystemElement = symoFactory.createSystemElement();
		leftWingSystemElement.setName("Left wing");
		
		projectRepository.getSystems().add(leftWingSystemElement);
		
		
		

		
		//Notification ADD object named "leftWing"
		//Get ontology name from project container (Assumption: objects can only be part of one project)
		//Get OntologyClass SystemElement of ModelClass of leftWing and create Individual leftWing as instance of SystemElement Class

		
		fail("Not yet implemented");
	}

}
