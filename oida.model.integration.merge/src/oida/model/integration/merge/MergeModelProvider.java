/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.merge;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import oida.model.integration.matching.diffmodel.ReferenceMatching;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

public class MergeModelProvider {

	private MergeModel mergeModel;

	public MergeModelProvider(MergeModel mergeModel) {
		setMergeModel(mergeModel);
	}

	private void setMergeModel(MergeModel mergeModel) {
		propertyChangeSupport.firePropertyChange("mergeModel", this.mergeModel,
				this.mergeModel = mergeModel);

	}

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public List<EObject> getConflictingEObjects() {
		List<EObject> resultList = new ArrayList<EObject>();
		for (ReferenceMatching referenceMatching : mergeModel
				.getConflictingMatchings()) {
			resultList.add(referenceMatching.getTargetReferenceMapping()
					.getSourceEObject());
		}

		return resultList;

	}

	public List<EAttribute> getCurrentTargetAttributes() {
		List<EAttribute> resultList = new ArrayList<EAttribute>();
		return resultList;

	}

	public List<Object> getCurrentSourceAttributeValues() {
		List<Object> resultList = new ArrayList<Object>();
		// convert source attribute values to the datatype of the target

		return resultList;
	}

}
