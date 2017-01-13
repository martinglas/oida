package oida.bridge.test;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EContentAdapter;

import de.symo.model.element.ElementFactory;
import de.symo.model.element.Parameter;
import de.symo.model.element.Transition;
import de.symo.model.element.Value;
import de.symo.model.symo.ComponentRepository;
import de.symo.model.symo.StateMaschine;
import de.symo.model.symo.SymoFactory;
import de.symo.model.symo.SystemElement;
import oida.ontology.Ontology;
import oida.ontology.OntologyClass;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.OntologyManagerException;
import oida.ontology.owl.manager.OwlOntologyManagerFactory;
import oida.ontologyMgr.OntologyFile;
import oida.ontologyMgr.OntologyMgrFactory;
import oida.test.util.OntologyTestHelper;

public class TotalObserver {
	private OwlOntologyManagerFactory managerFactory;
	
	private IOntologyManager ontologyManager;

	private ComponentRepository componentRepository;

	public TotalObserver() {
		managerFactory = new OwlOntologyManagerFactory();
		
		OntologyFile entry = OntologyMgrFactory.eINSTANCE.createOntologyFile();
		//TODO get path to local reference ontology
		
		
		String testOntologyPath=OntologyTestHelper.getTestOntologyFilePath();
		entry.setPath(testOntologyPath);
		entry.setFileName("aircraft.owl");
		
		
		
		
		ontologyManager = managerFactory.getNewManager();
		try {
			Ontology testOntology=ontologyManager.loadOntology(entry, true);
			EList<OntologyClass> ontologyClasses=testOntology.getClasses();
			
			
			for (OntologyClass ontologyClass:ontologyClasses){
				System.out.println(ontologyClass.getName());
			}
			
			
			
			OntologyClass aircraftClass=ontologyManager.getClass("Aircraft");
			aircraftClass.getName();
			
		} catch (OntologyManagerException e) {
			e.printStackTrace();
		}
		
		SymoFactory factory = SymoFactory.eINSTANCE;
		componentRepository = factory.createComponentRepository();

		EContentAdapter adapter = new EContentAdapter() {
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				ChangeHandler.handle(notification);
				// System.out.println("Notification received from the data model.
				// Data model has changed!!!");
			}
		};
		componentRepository.eAdapters().add(adapter);
	}
	

   
	public void doStuff() {
		try {
			ontologyManager.saveOntology();
		} catch (OntologyManagerException e) {
			e.printStackTrace();
		}

		// ontologyService.initialize(URI.createURI("http://model.owl"));
		SymoFactory factory = SymoFactory.eINSTANCE;
		SystemElement person = factory.createSystemElement();
		person.setName("Lars");
		System.out.println("I'm adding a person.");
		componentRepository.getComponents().add(person);
		System.out.println("I'm changing a entry");
		SystemElement systemElement2 = componentRepository.getComponents().get(0);
		systemElement2.setName("Lars2");
		// create Parameter1
		Parameter parameter = ElementFactory.eINSTANCE.createParameter();
		parameter.setName("Parameter 1");
		systemElement2.getParameters().add(parameter);

		// create Value
		Value value = ElementFactory.eINSTANCE.createValue();
		value.setValue("20.223");
		parameter.setValue(value);

		// create State machine
		StateMaschine stateMachine = factory.createStateMaschine();
		stateMachine.setName("Component State Machine");
		systemElement2.getBehaviors().add(stateMachine);

		// Create Transition
		Transition transition = ElementFactory.eINSTANCE.createTransition();
		transition.setName("Test transition");
		stateMachine.getTransitions().add(transition);

		// connect Transition to Value
		transition.setParameters(parameter);

		// disconnect Transition from the Value
		transition.setParameters(null);

		// reconnect Transition to Value
		transition.setParameters(parameter);

		// delete Value
		componentRepository.getComponents().remove(systemElement2);
	}
}