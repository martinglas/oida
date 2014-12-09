/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.ui.providers;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import oida.ontology.matcher.MatchRecommendation;

public class MappingLabelProvider {
	private MatchRecommendation matchRecommendation;
	private String sourceLabel = "No source element selected";
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	public MappingLabelProvider(MatchRecommendation matchRecommendation) {
		this.matchRecommendation = matchRecommendation;

	}

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public void setSourceLabel(String label) {
		propertyChangeSupport.firePropertyChange("sourceLabel",
				this.sourceLabel, this.sourceLabel = label);
	}

	public String getSourceLabel() {

		return sourceLabel;
	}

	public String getTargetLabel() {
		String resultLabel = matchRecommendation.getTarget().getLocalName();
		if (resultLabel != null) {
			return resultLabel;
		}

		else {
			resultLabel = "No target element selected";
		}
		return resultLabel;
	}

}
