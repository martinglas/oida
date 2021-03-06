package net.bhl.oida.bridge.model.ontology;

import java.util.Optional;

import net.bhl.oida.ontology.manager.IOntologyManager;
import net.bhl.oida.ontology.manager.OntologyManagerException;
import net.bhl.oida.ontology.model.owlontology.Ontology;
import net.bhl.oida.ontology.model.owlontology.OntologyAnnotationProperty;
import net.bhl.oida.ontology.predefined.AbstractPredefinedOntology;
import net.bhl.oida.util.constants.OntologyConstants;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-21
 *
 */
public class OIDAModelBaseOntology extends AbstractPredefinedOntology {
    public static final String OIDA_MODELONTOLOGY_IRI = OntologyConstants.OIDA_ONTOLOGY_BASE_IRI + "modelontology";
    public static final String OIDA_MODELONTOLOGY_PREFIX = OIDA_MODELONTOLOGY_IRI;

    public static final String OIDA_MODELONTOLOGY_NAME_ANNOTATION = "oida_name";

    private OntologyAnnotationProperty nameAnnotationProperty;

    public OntologyAnnotationProperty getNameAnnotationProperty() {
	return nameAnnotationProperty;
    }

    private static OIDAModelBaseOntology INSTANCE;

    public static OIDAModelBaseOntology getInstance() {
	if (INSTANCE == null)
	    INSTANCE = new OIDAModelBaseOntology();

	return INSTANCE;
    }

    private OIDAModelBaseOntology() {
    }

    @Override
    protected boolean checkPreDefinedOntology(Ontology ontology) {
	Optional<OntologyAnnotationProperty> emfNameAnnotationPropertyOptional = ontology.getAnnotationProperties().stream().filter(p -> p.getName().equals(OIDA_MODELONTOLOGY_NAME_ANNOTATION))
		.findFirst();
	if (emfNameAnnotationPropertyOptional.isPresent())
	    nameAnnotationProperty = emfNameAnnotationPropertyOptional.get();
	else
	    return false;

	return true;
    }

    @Override
    protected void initializeOntology(IOntologyManager ontologyManager) throws OntologyManagerException {
	nameAnnotationProperty = ontologyManager.createAnnotationProperty(OIDA_MODELONTOLOGY_NAME_ANNOTATION, OIDA_MODELONTOLOGY_PREFIX);

	ontologyManager.saveLocalOntology();
    }
}
