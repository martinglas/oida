package oida.ontology.manager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.apache.jena.ontology.ProfileRegistry;
import org.apache.jena.rdf.model.ModelFactory;

import OIDAOntMgr.LocalOntology;
import OIDAOntMgr.ManagedOntology;
import OIDAOntMgr.OIDAOntMgrFactory;
import oida.ontology.io.Owl;

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
		ManagedOntology mOnt = OIDAOntMgrFactory.eINSTANCE.createManagedOntology();
		mOnt.setLocalOntology(ontology);
		
		try {
			mOnt.setOntolgyModel(Owl.loadFromOwlToOntModelWithImport(null, new URL(ontology.getPath() + ontology.getFile()), ModelFactory.createOntologyModel(ProfileRegistry.OWL_DL_LANG)));
			
			int id = managedOntologies.keySet().size() + 1;
			managedOntologies.put(id, mOnt);
			
			return id;
		} catch (MalformedURLException e) {
			throw new OntologyManagerException(EXC_FILE_ERROR, e);
		}
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
