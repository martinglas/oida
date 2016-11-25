package oida.ontology.service;

import oida.OIDASystem;
import oida.ontologyMgr.Library;

public interface IOIDAOntologyService {
	public OIDASystem getSystemModel();
	
	public Library getLibrary();
}
