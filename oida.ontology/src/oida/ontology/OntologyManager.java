package oida.ontology;

import java.util.HashMap;

import oida.ontologyMgr.LocalOntology;
import oida.ontologyMgr.ManagedOntology;
import oida.ontologyMgr.OntologyMgrFactory;

/**
 * 
 * @since 2016-11-08
 * @author Michael.Shamiyeh
 *
 */
public class OntologyManager {
	
	private final String EXC_FILE_ERROR = "Error occured while loading the ontology file.";
	
	private static OntologyManager instance;
	public static OntologyManager getInstance() {
		if (instance == null)
			instance = new OntologyManager();
		
		return instance;
	}
	
	private HashMap<Integer, ManagedOntology> managedOntologies = new HashMap<Integer, ManagedOntology>();
	
	
	private OntologyManager() {
	}
	
	public int addOntology(LocalOntology ontology) throws OntologyManagerException {
		ManagedOntology mOnt = OntologyMgrFactory.eINSTANCE.createManagedOntology();
		mOnt.setLocalOntology(ontology);
		
		int id = managedOntologies.keySet().size() + 1;
		
		return id;
		
//		try {
//			//mOnt.setOntologyModel(Owl.loadFromOwlToOntModelWithImport(null, new URL(ontology.getPath() + ontology.getFile()), ModelFactory.createOntologyModel(ProfileRegistry.OWL_DL_LANG)));
//			
//			
//			managedOntologies.put(id, mOnt);
//			
//			return id;
//		} catch (MalformedURLException e) {
//			throw new OntologyManagerException(EXC_FILE_ERROR, e);
//		}
	}
	
//	public void LoadOntology(String path) {
//		jenaModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
//		jenaModel.read(path, null);
//	}
//	
//	public OntClass getThingClass() {
//		ExtendedIterator<OntClass> it = jenaModel.listNamedClasses();
//		
//		while (it.hasNext()) {
//		    OntClass ontclass = it.next();
//		    System.out.println(ontclass.getLocalName());
//		    if (ontclass.getSuperClass() == null)
//		    	return ontclass;
//		}
//		
//		return null;
//	}
//	
//	public List<OntResource> getAllResources() {
//		List<OntResource> ontologyResources = new ArrayList<OntResource>();
//		
//		ExtendedIterator<OntClass> it = jenaModel.listNamedClasses();
//		
//		while (it.hasNext())
//		{
//			OntClass cl = it.next();
//			@SuppressWarnings("unchecked")
//			ExtendedIterator<OntResource> itinst = (ExtendedIterator<OntResource>)cl.listInstances();
//			while (itinst.hasNext())
//				ontologyResources.add(itinst.next());
//			
//		    ontologyResources.add(cl);
//		}
//		
//		return ontologyResources;
//	}
//	
//	
//	public boolean isConcept(final String name) {
//		
//		boolean found = false;
//		
//		for(OntResource ontClass : getAllResources()) {			
//			if (name.equals(ontClass.getLocalName()) == true) {				
//				return true; 
//			}
//		}			
//
//		return found;
//	}
}
