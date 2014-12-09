/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import org.eclipse.emf.common.util.URI;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;

public class ReferenceOntologyModel {
	private OntologyModel ontologyModel;
	private String newClassName;

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);
	private String newIndividualName;
	private OntClass currentOntClass;

	public ReferenceOntologyModel() {
		this.ontologyModel = OntologyModel.getInstance();
	}

	public List<OntClass> getReferenceOntClasses() {
		return ontologyModel.getReferenceClasses();
	}

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public void setNewClassName(String newClassName) {
		propertyChangeSupport.firePropertyChange("newClassName",
				this.newClassName, this.newClassName = newClassName);
	}

	public String getNewClassName() {
		return this.newClassName;
	}

	public void setNewIndividualName(String newIndividualName) {
		propertyChangeSupport.firePropertyChange("newIndividualName",
				this.newIndividualName,
				this.newIndividualName = newIndividualName);
	}

	public String getNewIndividualName() {
		return this.newIndividualName;
	}

	public OntClass getCurrentOntClass() {
		return this.currentOntClass;
	}

	public void setCurrentOntClass(OntClass currentOntClass) {
		propertyChangeSupport.firePropertyChange("currentOntClass",
				this.currentOntClass, this.currentOntClass = currentOntClass);
	}

	public OntClass createNewReferenceClass(OntClass sourceOntClass) {
		OntClass newOntClass = createNewReferenceClass();

		if (sourceOntClass != null) {
			sourceOntClass.addEquivalentClass(newOntClass);
		}

		return newOntClass;
	}

	public Individual createNewReferenceIndividual(Individual sourceIndividual) {
		OntModel referenceOntModel = ontologyModel.getOntologyModel()
				.getImportedModel(
						ontologyModel.getReferenceOntologyURI().toString());
		URI newIndividualURI = URI.createURI(ontologyModel
				.getReferenceOntologyURI() + "#" + getNewIndividualName());
		Individual newReferenceIndividual = referenceOntModel.createIndividual(
				newIndividualURI.toString(), getCurrentOntClass());

		if (sourceIndividual != null) {
			sourceIndividual.addSameAs(newReferenceIndividual);
		}

		return newReferenceIndividual;

	}

	public OntClass createNewReferenceClass() {
		OntModel referenceOntModel = ontologyModel.getOntologyModel()
				.getImportedModel(
						ontologyModel.getReferenceOntologyURI().toString());
		URI newOntClassURI = URI.createURI(ontologyModel
				.getReferenceOntologyURI() + "#" + getNewClassName());
		OntClass newOntClass = referenceOntModel.createClass(newOntClassURI
				.toString());
		newOntClass.setSuperClass(getCurrentOntClass());
		return newOntClass;
	}
}
