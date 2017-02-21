package oida.ontology.service;

import org.eclipse.emf.ecore.util.EContentAdapter;

import oida.ontology.OntologyObjectProperty;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.OntologyManagerException;
import oida.ontologyMgr.OntologyFile;
import oida.util.OIDAUtil;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 13.12.2016
 *
 */
public abstract class AbstractOIDAOntologyService extends EContentAdapter implements IOIDAOntologyService {
	
	public static final String MEREOLOGY_NAMESPACE = "http://www.bauhaus-luftfahrt.net/ontologies/merology.owl";
	public static final String MEREOLOGY_PREFIX = "mer";
	
	@Override
	public IOntologyManager getMereology() throws OntologyManagerException {
		OntologyFile mereologyFile = OIDAUtil.getOntologyFile(OIDAUtil.getOIDAWorkPath(), OIDAUtil.MEREOLOGY_FILENAME);
		
		IOntologyManager mereologyOntology = getOntologyManager(mereologyFile, true);
		
		if (mereologyOntology.getOntology().getNrOfObjectProperties() == 0) {
			mereologyOntology.addNamespace(MEREOLOGY_PREFIX, MEREOLOGY_NAMESPACE, false);
			
			OntologyObjectProperty hasPartObjectProperty = mereologyOntology.createObjectProperty("hasPart", MEREOLOGY_PREFIX);
			mereologyOntology.setObjectPropertyCharacteristics(hasPartObjectProperty, false, false, true, false, false, false, false);
			
			OntologyObjectProperty hasPartDirectlyObjectProperty = mereologyOntology.createObjectProperty("hasPart_directly", MEREOLOGY_PREFIX);
			mereologyOntology.assignSubObjectPropertyToSuperObjectProperty(hasPartDirectlyObjectProperty, hasPartObjectProperty);
			
			OntologyObjectProperty partOfObjectProperty = mereologyOntology.createObjectProperty("partOf", MEREOLOGY_PREFIX);
			mereologyOntology.setObjectPropertyCharacteristics(partOfObjectProperty, false, false, true, false, false, false, false);
			mereologyOntology.assignInverseObjectProperty(partOfObjectProperty, hasPartObjectProperty);
			
			OntologyObjectProperty partOfDirectlyObjectProperty = mereologyOntology.createObjectProperty("partOf_directly", MEREOLOGY_PREFIX);
			mereologyOntology.assignSubObjectPropertyToSuperObjectProperty(partOfDirectlyObjectProperty, partOfObjectProperty);
			mereologyOntology.assignInverseObjectProperty(partOfDirectlyObjectProperty, hasPartDirectlyObjectProperty);
			
			mereologyOntology.saveOntology();
		}
		
		return mereologyOntology;
	}
}
