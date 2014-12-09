/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.merge;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;



import oida.model.integration.matching.diffmodel.ReferenceMatching;
import oida.model.integration.merge.util.MergeHelper;

import org.eclipse.emf.ecore.EAttribute;

public class IntegratedAttribute {

	private String attributeName = "";
	private String myAttributeValue = "";
	private String theirAttributeValue = "";

	public IntegratedAttribute(ReferenceMatching referenceMatching,
			EAttribute referenceEAttribute) {
		EAttribute targetEAttribute = MergeHelper.getTargetEAttribute(
				referenceMatching, referenceEAttribute);
		if (targetEAttribute != null) {
			setAttributeName(targetEAttribute.getName());

			Object targetEAttributeValue = referenceMatching
					.getTargetReferenceMapping().getSourceEObject()
					.eGet(targetEAttribute);

			if (targetEAttributeValue != null) {
				setMyAttributeValue(targetEAttributeValue.toString());
			} else {
				setMyAttributeValue("");
			}
		}

		Object sourceEAttributeValue = referenceMatching
				.getSourceReferenceMapping().getReferenceEObject()
				.eGet(referenceEAttribute);
		if (sourceEAttributeValue != null) {
			setTheirAttributeValue(sourceEAttributeValue.toString());
		} else {
			setTheirAttributeValue("");
		}
	}

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	private void setAttributeName(String name) {
		propertyChangeSupport.firePropertyChange("attributeName",
				this.attributeName, this.attributeName = name);
	}

	public String getAttributeName() {
		return attributeName;
	}

	public String getMyAttributeValue() {
		return myAttributeValue;
	}

	private void setMyAttributeValue(String myAttributeValue) {
		propertyChangeSupport
				.firePropertyChange("myAttributeValue", this.myAttributeValue,
						this.myAttributeValue = myAttributeValue);
	}

	public String getTheirAttributeValue() {
		return theirAttributeValue;
	}

	private void setTheirAttributeValue(String theirAttributeValue) {
		propertyChangeSupport.firePropertyChange("theirAttributeValue",
				this.theirAttributeValue,
				this.theirAttributeValue = theirAttributeValue);
	}

}
