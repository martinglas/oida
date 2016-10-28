package oida.ontology.manager.reasoning;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.reasoner.ValidityReport;

public class ReasonerService extends Thread {

	private Reasoner reasoner;
	
	private InfModel inferenceModel;
	
	private ValidityReport results;
	
	public ReasonerService() {	
		reasoner = ReasonerRegistry.getOWLReasoner();
	}
	
	public void initialize(OntModel model) {
		
		inferenceModel = ModelFactory.createInfModel(reasoner, model);
	}
	
	@Override
	public void run() {
		results = inferenceModel.validate();
	}
}
