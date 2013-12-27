package com.sciencegadgets.shared;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.DOM;
import com.sciencegadgets.client.algebra.MathTree.MathNode;
import com.sciencegadgets.shared.TypeML.TypeArgument;

/**
 * <b>data-unit</b> attribute <b>= </b>id^exp*id^exp... <br/>
 * <br/>
 * <b>basic</b> Unit<b>=</b> quantityKind_symbol^exp <br/>
 * <b>basic</b> Quantity<b>=</b> quantityKind^exp <br/>
 * <br/>
 * examples: <br/>
 * <b>liters:</b> Volume_L_1 <br/>
 * <b>meters per seconds squared:</b> Length_m^1*Time_s^-2
 */
public class UnitUtil {

	public static final String UNIT_CLASSNAME = "unit";
	public static final String UNIT_NODE_DELIMITER = "-unitNode-";
	private static final double UNIT_SIZE_SMALL = 40;//%
	
	public static final String BASE_DELIMITER = "*";
	public static final String BASE_DELIMITER_REGEX = "\\*";
	public static final String EXP_DELIMITER = "^";
	public static final String EXP_DELIMITER_REGEX = "\\^";
	public static final String NAME_DELIMITER = "_";

	public static Element element_From_MathNode(MathNode mathNode) {
		return element_From_attribute(mathNode.getUnitAttribute());
	}
	public static Element element_From_MathNode(MathNode mathNode, String nodeId, boolean hasSmallUnits) {
		return element_From_attribute(mathNode.getUnitAttribute(),nodeId,hasSmallUnits);
	}

	public static Element element_From_attribute(String dataUnit) {
		return element_From_attribute(dataUnit, null);
	}
	public static Element element_From_attribute(String dataUnit, String nodeId) {
		return element_From_attribute(dataUnit, nodeId, true);
	}

		public static Element element_From_attribute(String dataUnit, String nodeId, boolean hasSmallUnits) {

		Element numerator = DOM.createDiv();
		Element denominator = DOM.createDiv();

		String[] dataUnitArray = dataUnit.split(BASE_DELIMITER_REGEX);

		for (String basic : dataUnitArray) {
			if ("".equals(basic)) {
				continue;
			}

			String[] baseAndExp = basic.split(EXP_DELIMITER_REGEX);
			String name = baseAndExp[0];
			String exponent = baseAndExp[1];

			String[] nameParts = name.split(NAME_DELIMITER);

			// If Unit: (baseParts ==Unit.getId()== QuantityKind_Symbol)
			// Else QuantityKind: (baseParts == QuantityKind)
			String symbol = nameParts.length == 2 ? nameParts[1] : name;

			Element unitDiv = DOM.createDiv();
			unitDiv.setAttribute("id", symbol+UNIT_NODE_DELIMITER+nodeId);
			unitDiv.addClassName(TypeML.Term.asChild());

			if (exponent.startsWith("-")) {// Negative
				exponent = exponent.replace("-", "");
				denominator.appendChild(unitDiv);
			} else {
				numerator.appendChild(unitDiv);
			}
			if ("1".equals(exponent)) {
				exponent = TypeML.Operator.SPACE.getSign();
			}

			Element symbolDiv = DOM.createDiv();
			symbolDiv.addClassName(TypeML.Exponential.asChild(TypeArgument.BASE));
			symbolDiv.setInnerText(symbol);
			unitDiv.appendChild(symbolDiv);

			Element expDiv = DOM.createDiv();
			expDiv.addClassName(TypeML.Exponential.asChild(TypeArgument.EXPONENT));
			expDiv.setInnerText(exponent);
			unitDiv.appendChild(expDiv);
		}

		if (denominator.getChildCount() > 0) {
			Element frac = DOM.createDiv();
			frac.addClassName(TypeML.Term.asChild());
			numerator.addClassName(TypeML.Fraction.asChild(TypeArgument.NUMERATOR));
			denominator
					.addClassName(TypeML.Fraction.asChild(TypeArgument.DENOMINATOR));
			frac.appendChild(numerator);
			frac.appendChild(denominator);
			frac.addClassName(UNIT_CLASSNAME);
			if(hasSmallUnits) {
				frac.getStyle().setFontSize(UNIT_SIZE_SMALL , Unit.PCT);
			}
			
			if(numerator.getChildCount()==0) {
				Element oneDiv = DOM.createDiv();
				oneDiv.setInnerText("1");
				numerator.appendChild(oneDiv);
			}
			return frac;
		} else {
			numerator.addClassName(TypeML.Term.asChild());
			numerator.addClassName(UNIT_CLASSNAME);
			if(hasSmallUnits) {
				numerator.getStyle().setFontSize(UNIT_SIZE_SMALL , Unit.PCT);
			}
			return numerator;
		}
	}

	public static String[] getUnitNames(String dataUnit_attribute) {
		String[] bases = dataUnit_attribute.split(BASE_DELIMITER_REGEX);
		for(int i=0 ; i < bases.length ; i++) {
			String name = bases[i].split(EXP_DELIMITER_REGEX)[0];
			bases[i] = name;
		}
		return bases;
	}
	public static String getExponent(String dataUnit_attribute) {
		return dataUnit_attribute.split(EXP_DELIMITER_REGEX)[1];
	}

	public static String getQuantityKind(String unitName) {
		return unitName.split(NAME_DELIMITER)[0];
	}
	public static String getSymbol(String unitName) {
		return unitName.split(NAME_DELIMITER)[1];
	}
	public static String[] getUnitNames(MathNode mNode) {
		String[] bases = getUnits(mNode);
		for(int i=0 ; i < bases.length ; i++) {
			String name = bases[i].split(EXP_DELIMITER_REGEX)[0];
			bases[i] = name;
		}
		return bases;
	}
	public static String[] getUnits(MathNode mNode) {
		String att = mNode.getUnitAttribute();
		return att.split(BASE_DELIMITER_REGEX);
		
	}
}