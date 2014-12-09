/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.merge;

import static oida.model.integration.merge.MergeModel.NAME_ATTRIBUTE;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import oida.model.integration.matching.diffmodel.ReferenceMatching;

import org.eclipse.emf.ecore.EAttribute;

public class IntegratedAttributes {

	private List<IntegratedAttribute> integratedAttributes;

	public void setReferenceMatching(ReferenceMatching referenceMatching) {
		List<IntegratedAttribute> newIntegratedAttributes = new ArrayList<IntegratedAttribute>();

		for (EAttribute eAttribute : referenceMatching
				.getTargetReferenceMapping().getReferenceEObject().eClass()
				.getEAllAttributes()) {
			if (!eAttribute.getName().equals(NAME_ATTRIBUTE)) {
				newIntegratedAttributes.add(new IntegratedAttribute(
						referenceMatching, eAttribute));
			}

		}
		setIntegratedAttributes(newIntegratedAttributes);
	}

	private void setIntegratedAttributes(
			List<IntegratedAttribute> newIntegratedAttributes) {
		propertyChangeSupport.firePropertyChange("integratedAttributes",
				this.integratedAttributes,
				this.integratedAttributes = newIntegratedAttributes);

	}

	public IntegratedAttributes() {
		setIntegratedAttributes(new ArrayList<IntegratedAttribute>());
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

	public List<IntegratedAttribute> getIntegratedAttributes() {
		return integratedAttributes;
	}
}
