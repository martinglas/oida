package oida.bridge.recommender;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;

import oida.ontology.Ontology;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-03-10
 *
 */
public abstract class AbstractRecommender extends EContentAdapter implements IRecommender {
	private Ontology referenceOntology;
	private Ontology modelOntology;
	
	protected Ontology getReferenceOntology() {
		return referenceOntology;
	}
	
	protected void setReferenceOntology(Ontology referenceOntology) {
		this.referenceOntology = referenceOntology;
	}
	
	protected Ontology getModelOntology() {
		return modelOntology;
	}
	
	protected void setModelOntology(Ontology modelOntology) {
		this.modelOntology = modelOntology;
	}
	
	protected void connectReferenceOntologyAdapter() {
		if (this.referenceOntology != null)
			this.referenceOntology.eAdapters().add(this);
	}
	
	protected void disconnectReferenceOntologyAdapter() {
		if (this.referenceOntology != null)
			this.referenceOntology.eAdapters().remove(this);
	}
	
	@Override
	public abstract void notifyChanged(Notification notification);
}
