/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.transformation.typemapping;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EcorePackage;

/**
 * This class contains a Datatype provider which helps to translate datatypes in
 * owl to ECore datatypes.
 * 
 * @author Martin Glas
 * 
 */
public final class DatatypeProvider {

	private DatatypeProvider() {

	}

	/**
	 * This method returns standard datatypes by a given name.
	 * 
	 * @param typeName
	 *            the name of the datatype
	 * @return the EDatatype object
	 */
	public static EDataType getAttributeType(String typeName) {
		if (typeName.equals("double")) {
			return EcorePackage.eINSTANCE.getEDouble();

		}

		else if (typeName.equals("decimal")) {
			return EcorePackage.eINSTANCE.getEBigDecimal();

		}

		else if (typeName.equals("integer")) {
			return EcorePackage.eINSTANCE.getEBigInteger();

		}

		else if (typeName.equals("string")) {
			return EcorePackage.eINSTANCE.getEString();
		} else if ((typeName.equals("integer")) && (typeName.equals("int"))) {
			return EcorePackage.eINSTANCE.getEInt();
		}

		// else if (typeName.equals("javax.units.Unit")) {
		// return UnitsPackage.eINSTANCE.getUnit();
		// }

		System.out.println("Datatype could not be recognized");
		return EcorePackage.eINSTANCE.getEString();
	}

}
