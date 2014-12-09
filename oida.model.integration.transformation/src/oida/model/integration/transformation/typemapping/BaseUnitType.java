/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.transformation.typemapping;

import javax.measure.unit.BaseUnit;

import com.hp.hpl.jena.datatypes.BaseDatatype;
import com.hp.hpl.jena.datatypes.DatatypeFormatException;
import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.graph.impl.LiteralLabel;

public class BaseUnitType extends BaseDatatype {

	public static final String theTypeURI = "java:javax.units.BaseUnit";
	public static final RDFDatatype theBaseUnitType = new BaseUnitType();

	private BaseUnitType() {
		super(theTypeURI);

	}

	/**
	 * Convert a value of this datatype out to lexical form.
	 */
	public String unparse(Object value) {
		BaseUnit baseUnit = (BaseUnit) value;
		return baseUnit.toString();
	}

	/**
	 * Parse a lexical form of this datatype to a value
	 * 
	 * @throws DatatypeFormatException
	 *             if the lexical form is not legal
	 */
	public Object parse(String lexicalForm) throws DatatypeFormatException {

		return BaseUnit.valueOf(lexicalForm);
	}

	/**
	 * Compares two instances of values of the given datatype. This does not
	 * allow rationals to be compared to other number formats, Lang tag is not
	 * significant.
	 */
	public boolean isEqual(LiteralLabel value1, LiteralLabel value2) {
		return value1.getDatatype() == value2.getDatatype()
				&& value1.getValue().equals(value2.getValue());
	}

	@Override
	public Class<?> getJavaClass() {
		return BaseUnit.class;
	}

}
