package oida.ontology.manager;

public class OIDAOntologyManager {
	private static OIDAOntologyManager instance;
	public static OIDAOntologyManager getInstance() {
		if (instance == null)
			instance = new OIDAOntologyManager();
		
		return instance;
	}
	
	private OIDAOntologyManager() { 
		
	}
}
