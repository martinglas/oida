/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.merge;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import oida.model.integration.matching.diffmodel.ReferenceMatching;

public class MergeCounters {
	private int numberOfConfirmedMatchings;
	private int numberOfReportedMatchings;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);
	private List<ReferenceMatching> currentMatchings;
	private String confirmationStatus = "";
	private String reportingStatus = "";

	public MergeCounters() {
		setNumberOfConfirmedMatchings(0);
		setNumberOfConfirmedMatchings(0);
	}

	public void setCurrentMatchings(List<ReferenceMatching> currentMatchings) {
		propertyChangeSupport
				.firePropertyChange("currentMatchings", this.currentMatchings,
						this.currentMatchings = currentMatchings);
		int nConfirmed = 0;
		int nReported = 0;
		for (ReferenceMatching referenceMatching : getCurrentMatchings()) {

			if (referenceMatching.isConfirmed()) {
				nConfirmed++;
			}
			if (referenceMatching.isReported()) {
				nReported++;
			}
		}
		setNumberOfConfirmedMatchings(nConfirmed);
		setNumberOfReportedMatchings(nReported);

	}

	private List<ReferenceMatching> getCurrentMatchings() {
		return this.currentMatchings;
	}

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public void setNumberOfConfirmedMatchings(int newValue) {
		propertyChangeSupport.firePropertyChange("numberOfConfirmedMatchings",
				this.numberOfConfirmedMatchings,
				this.numberOfConfirmedMatchings = newValue);

	}

	public void setNumberOfReportedMatchings(int newValue) {
		propertyChangeSupport.firePropertyChange("numberOfReportedMatchings",
				this.numberOfReportedMatchings,
				this.numberOfReportedMatchings = newValue);

	}

	public void increaseNumberOfReportedMatchings() {
		propertyChangeSupport.firePropertyChange("numberOfReportedMatchings",
				this.numberOfReportedMatchings,
				this.numberOfReportedMatchings++);

	}

	public void increaseNumberOfConfirmedMatchings() {
		propertyChangeSupport.firePropertyChange("numberOfConfirmedMatchings",
				this.numberOfConfirmedMatchings,
				this.numberOfConfirmedMatchings++);

	}

	public void decreaseNumberOfConfirmedMatchings() {
		if (this.numberOfConfirmedMatchings > 0) {
			propertyChangeSupport.firePropertyChange(
					"numberOfConfirmedMatchings",
					this.numberOfConfirmedMatchings,
					this.numberOfConfirmedMatchings--);
		}

	}

	public int getNumberOfConfirmedMatchings() {
		return numberOfConfirmedMatchings;
	}

	public int getNumberOfReportedMatchings() {
		return numberOfReportedMatchings;
	}

	public void decreaseNumberOfReportedMatchings() {
		if (this.numberOfReportedMatchings > 0) {
			propertyChangeSupport.firePropertyChange(
					"numberOfReportedMatchings",
					this.numberOfReportedMatchings,
					this.numberOfReportedMatchings--);
		}

	}

	public void updateStatus() {
		setConfirmationStatus(getNumberOfConfirmedMatchings() + " of "
				+ currentMatchings.size() + " items confirmed");
		setReportingStatus(getNumberOfReportedMatchings() + " of "
				+ currentMatchings.size() + " items reported");
	}

	public void setConfirmationStatus(String newConfirmationStatus) {
		propertyChangeSupport.firePropertyChange("confirmationStatus",
				this.confirmationStatus,
				this.confirmationStatus = newConfirmationStatus);

	}

	public void setReportingStatus(String newReportingStatus) {
		propertyChangeSupport
				.firePropertyChange("reportingStatus", this.reportingStatus,
						this.reportingStatus = newReportingStatus);

	}

	public String getConfirmationStatus() {
		return confirmationStatus;
	}

	public String getReportingStatus() {
		return reportingStatus;
	}

}
