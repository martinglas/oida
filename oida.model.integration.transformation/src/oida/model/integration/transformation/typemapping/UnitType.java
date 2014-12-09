/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.transformation.typemapping;

import javax.measure.unit.Unit;

import com.hp.hpl.jena.datatypes.BaseDatatype;
import com.hp.hpl.jena.datatypes.DatatypeFormatException;
import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.graph.impl.LiteralLabel;

public class UnitType extends BaseDatatype {

	public static final String theTypeURI = "java:javax.units.Unit";
	public static final RDFDatatype theUnitType = new UnitType();

	private UnitType() {
		super(theTypeURI);

	}

	/**
	 * Convert a value of this datatype out to lexical form.
	 */
	public String unparse(Object value) {
		Unit unit = (Unit) value;
		return unit.toString();
	}

	/**
	 * Parse a lexical form of this datatype to a value
	 * 
	 * @throws DatatypeFormatException
	 *             if the lexical form is not legal
	 */
	public Object parse(String lexicalForm) throws DatatypeFormatException {

		return Unit.valueOf(lexicalForm);
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

	public Class<?> getJavaClass() {
		return Unit.class;
	}

}
