package oida.bridge.model.ontology;

import java.util.Optional;

import oida.ontology.Ontology;
import oida.ontology.OntologyAnnotationProperty;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.OntologyManagerException;
import oida.ontology.predefined.AbstractPredefinedOntology;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-21
 *
 */
public class OIDAModelBaseOntology extends AbstractPredefinedOntology {
	public static final String OIDA_MODELONTOLOGY_IRI = "http://www.bauhaus-luftfahrt.net/oida/modelontology";
	public static final String OIDA_MODELONTOLOGY_PREFIX = "oida";
	
	public static final String OIDA_MODELONTOLOGY_NAME_ANNOTATION = "oida_name";
	
	private OntologyAnnotationProperty nameAnnotationProperty;
	
	public OntologyAnnotationProperty getNameAnnotationProperty() {
		return nameAnnotationProperty;
	}
	
	@Override
	protected boolean checkPreDefinedOntology(Ontology ontology) {
		Optional<OntologyAnnotationProperty> emfNameAnnotationPropertyOptional = ontology.getAnnotationProperties().stream().filter(p -> p.getName().equals(OIDA_MODELONTOLOGY_NAME_ANNOTATION)).findFirst();
		if (emfNameAnnotationPropertyOptional.isPresent())
			nameAnnotationProperty = emfNameAnnotationPropertyOptional.get();
		else
			return false;
		
		return true;
	}

	@Override
	protected void initializeOntology(IOntologyManager ontologyManager) throws OntologyManagerException {
		ontologyManager.addNamespace(OIDA_MODELONTOLOGY_PREFIX, OIDA_MODELONTOLOGY_IRI, false);
		
		nameAnnotationProperty = ontologyManager.createAnnotationProperty(OIDA_MODELONTOLOGY_NAME_ANNOTATION, OIDA_MODELONTOLOGY_PREFIX);
		
		ontologyManager.saveOntology();
	}
}
