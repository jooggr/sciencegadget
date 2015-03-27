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