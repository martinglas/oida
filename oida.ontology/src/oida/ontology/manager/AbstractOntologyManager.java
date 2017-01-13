package oida.ontology.manager;

import java.io.File;
import java.io.IOException;

import oida.ontology.Ontology;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyIndividual;
import oida.ontologyMgr.OntologyFile;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2016-11-23
 *
 */
public abstract class AbstractOntologyManager implements IOntologyManager {
	
	private Ontology ontology;
	private OntologyFile ontologyFile;
	
	public Ontology getOntology() {
		return ontology;
	}

	protected void setOntology(Ontology ontology) {
		this.ontology = ontology;
	}
	
	@Override
	public OntologyFile getOntologyEntry() {
		return ontologyFile;
	}

	protected void setOntologyEntry(OntologyFile ontologyFile) {
		this.ontologyFile = ontologyFile;
	}
	
	protected File getOntologyFile(OntologyFile ontologyFile) {
		return getOntologyFile(ontologyFile, false);
	}
	
	protected File getOntologyFile(OntologyFile ontologyFile, boolean createIfNotExisting) {
		if (ontologyFile.getPath() == null) {
			System.out.println("SYMO4PD OIDA Ontology Manager [getOntologyFile]: Ontology file path is not set.");
			return null;
		}
		
		if (ontologyFile.getFileName() == null) {
			System.out.println("SYMO4PD OIDA Ontology Manager [getOntologyFile]: Ontology filename is not set.");
			return null;
		}
		
		File file = new File(ontologyFile.getPath() + ontologyFile.getFileName());
		
		if (!file.exists() && createIfNotExisting) {
			try {
				file.createNewFile();
				System.out.println("SYMO4PD OIDA Ontology Manager [getOntologyFile]: Ontology file '" + file.getAbsolutePath() + "' has been created.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return file;
		}
		else {
			System.out.println("SYMO4PD OIDA Ontology Manager [getOntologyFile]: Ontology file '" + file.getAbsolutePath() + "' doesn't exist and has NOT been created.");
			return null;
		}
	}
	
	@Override
	public OntologyClass createSubClass(String name, OntologyClass superClass) {
		return createSubClass(name, "", superClass);
	}

	@Override
	public OntologyClass createSubClass(String name, String prefix, OntologyClass superClass) {
		OntologyClass subClass = createClass(name, prefix);
		assignSubClassToSuperClass(subClass, superClass);
		return subClass;
	}

	@Override
	public OntologyIndividual createIndividualOfClass(String individualName, String className) {
		return createIndividualOfClass(individualName, createClass(className));
	}
	
	@Override
	public OntologyIndividual createIndividualOfClass(String individualName, String individualPrefix, String className, String classPrefix) {
		return createIndividualOfClass(individualName, individualPrefix, createClass(className, classPrefix));
	}
	
	@Override
	public OntologyIndividual createIndividualOfClass(String individualName, OntologyClass clazz) {
		OntologyIndividual individual = createIndividual(individualName);
		assignIndividualToClass(individual, clazz);
		return individual;
	}
	
	@Override
	public OntologyIndividual createIndividualOfClass(String individualName, String individualPrefix, OntologyClass clazz) {
		OntologyIndividual individual = createIndividual(individualName, individualPrefix);
		assignIndividualToClass(individual, clazz);
		return individual;
	}
}
