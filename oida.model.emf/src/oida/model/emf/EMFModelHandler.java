package oida.model.emf;

import org.eclipse.emf.ecore.EObject;

public class EMFModelHandler {
	private EObject object;

	public EObject getObject() {
		return object;
	}

	public EMFModelHandler() {
	}
	
	public void initialize(EObject object) {
		this.object = object;
	}
}
