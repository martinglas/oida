/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.ui.providers;

import oida.model.integration.matching.diffmodel.ReferenceMatching;
import oida.model.integration.modelprovider.ModelProviderHelper;

import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class NewElementsTableLabelProvider extends ObservableMapLabelProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// no image to show
		return null;
	}

	/**
	 * @param attributeMap
	 */
	public NewElementsTableLabelProvider(IObservableMap attributeMap) {
		super(attributeMap);
	}

	/**
	 * @param attributeMap
	 */
	public NewElementsTableLabelProvider(IObservableMap[] attributeMap) {
		super(attributeMap);
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// each element comes from the ContentProvider.getElements(Object)
		if (!(element instanceof ReferenceMatching)) {
			return "";
		}

		ReferenceMatching referenceMatching = (ReferenceMatching) element;
		switch (columnIndex) {
		case 0:
			return ModelProviderHelper.getModelElementName(referenceMatching.getTargetReferenceMapping()
				.getSourceEObject());
		case 1:
			return referenceMatching.getSourceReferenceMapping().getEClassMapping().getReferneceIndividual()
				.getLocalName();
		case 2:
			if (referenceMatching.getSourceReferenceMapping().getSourceEObject().eContainer() != null) {
				return ModelProviderHelper.getModelElementName(referenceMatching.getSourceReferenceMapping()
					.getSourceEObject().eContainer());
			} else {
				return "Not set";
			}
		case 3: {
			if (referenceMatching.isConfirmed())
				return "Confirmed";
			if (referenceMatching.isReported())
				return "Reported";

			return "Unclear";
		}

		default:
			break;
		}
		return "";
	}
}
