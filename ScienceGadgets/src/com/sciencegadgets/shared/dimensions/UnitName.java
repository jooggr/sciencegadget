/*******************************************************************************
 *     This file is part of ScienceGadgets, a collection of educational tools
 *     Copyright (C) 2012-2015 by John Gralyan
 *
 *     ScienceGadgets is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of
 *     the License, or (at your option) any later version.
 *
 *     ScienceGadgets is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *     
 *     Contact us at info@sciencegadgets.org
 *******************************************************************************/
package com.sciencegadgets.shared.dimensions;

/**
 * [quantityKind] + UnitAttribute.NAME_DELIMITER + [symbol]
 * 
 * <br/>
 * examples: <br/>
 * <b>liter:</b> Volume_L <br/>
 * <b>meter:</b> Length_m
 */
public class UnitName {

	String unitName;

	public UnitName(String unitName) {
		this.unitName = unitName;
	}

	@Override
	public String toString() {
		return unitName;
	}

	public String getSymbol() {
		String[] parts = unitName.split(UnitAttribute.NAME_DELIMITER);
		if (parts.length > 1) {
			return parts[1];
		} else {
			return "";
		}
	}

	public String getQuantityKind() {
		return unitName.split(UnitAttribute.NAME_DELIMITER)[0];
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UnitName) {
			if (unitName.equals(((UnitName) obj).toString())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}