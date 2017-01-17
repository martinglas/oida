package oida.ontology.manager;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;

import oida.ontology.Ontology;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyFactory;
import oida.ontology.OntologyIndividual;
import oida.ontology.OntologyPackage;
import oida.ontologyMgr.OntologyFile;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2016-11-23
 *
 */
public abstract class AbstractOntologyManager extends EContentAdapter implements IOntologyManager {
	
	private Ontology ontology;
	
	public Ontology getOntology() {
		return ontology;
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getEventType() == Notification.ADD) {
			if (notification.getFeature().equals(OntologyPackage.eINSTANCE.getOntology_Individuals()))
				ontology.setNrOfIndividuals(ontology.getNrOfIndividuals() + 1);
			if (notification.getFeature().equals(OntologyPackage.eINSTANCE.getOntology_Classes()))
				ontology.setNrOfClasses(ontology.getNrOfClasses() + 1);
		}
		else if (notification.getEventType() == Notification.REMOVE) {
			if (notification.getFeature().equals(OntologyPackage.eINSTANCE.getOntology_Individuals()))
				ontology.setNrOfIndividuals(ontology.getNrOfIndividuals() - 1);
			if (notification.getFeature().equals(OntologyPackage.eINSTANCE.getOntology_Classes()))
				ontology.setNrOfClasses(ontology.getNrOfClasses() - 1);
		}
	}

	protected void setOntology(Ontology ontology) {
		if (this.ontology != null)
			this.ontology.eResource().eAdapters().remove(this);
		
		this.ontology = ontology;
		this.ontology.eAdapters().add(this);
	}
	
	protected File getOntologyFile(OntologyFile ontologyFile) {
		return getOntologyFile(ontologyFile, false);
	}
	
	protected File getOntologyFile(OntologyFile ontologyFile, boolean createIfNotExisting) {
		if (ontologyFile.getPath() == null) {
			System.out.println("OIDA Ontology Manager [getOntologyFile]: Ontology file path is not set.");
			return null;
		}
		
		if (ontologyFile.getFileName() == null) {
			System.out.println("OIDA Ontology Manager [getOntologyFile]: Ontology filename is not set.");
			return null;
		}
		
		File file = new File(ontologyFile.getPath() + ontologyFile.getFileName());
		
		if (!file.exists()) {
			if (createIfNotExisting) {
				try {
					file.createNewFile();
					System.out.println("OIDA Ontology Manager [getOntologyFile]: Ontology file '" + file.getAbsolutePath() + "' has been created.");
					return file;
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
			else {
				System.out.println("OIDA Ontology Manager [getOntologyFile]: Ontology file '" + file.getAbsolutePath() + "' doesn't exist and has NOT been created.");
				return null;
			}
		}
		else	
			return file;
	}
	
	@Override
	public Ontology loadOntology(OntologyFile ontologyFile) throws OntologyManagerException {
		return loadOntology(ontologyFile, false);
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
	
	protected Ontology generateInternalOntologyObject(OntologyFile file, String name, long nrOfClasses, long nrOfIndividuals) {
		Ontology newOntology = OntologyFactory.eINSTANCE.createOntology();
		newOntology.setOntologyEntry(file);
		newOntology.setName(name);
		newOntology.setNrOfClasses(nrOfClasses);
		newOntology.setNrOfIndividuals(nrOfIndividuals);
		return newOntology;
	}
	
	protected OntologyClass generateInternalClassObject(Ontology ontology, String prefix, String className) {
		OntologyClass newClass = OntologyFactory.eINSTANCE.createOntologyClass();
		newClass.setContainingOntology(ontology);
		newClass.setPrefix(prefix);
		newClass.setName(className);
		
		ontology.getClasses().add(newClass);

		return newClass;
	}
	
	protected OntologyIndividual generateInternalIndividualObject(Ontology ontology, String prefix, String individualName) {
		OntologyIndividual newIndividual = OntologyFactory.eINSTANCE.createOntologyIndividual();
		newIndividual.setContainingOntology(ontology);
		newIndividual.setPrefix(prefix);
		newIndividual.setName(individualName);
		
		ontology.getIndividuals().add(newIndividual);
		
		return newIndividual;
	}
}
