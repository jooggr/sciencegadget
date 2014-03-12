package com.sciencegadgets.shared;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.DOM;
import com.sciencegadgets.client.CSS;
import com.sciencegadgets.client.algebra.MathTree.MathNode;

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
public class UnitHTML {

	public static final String UNIT_CLASSNAME = CSS.UNIT;
	public static final String UNIT_NODE_DELIMITER = "-unitNode-";
	private static final double UNIT_SIZE_SMALL = 40;// %

	public static Element create(MathNode mathNode) {
		return create(mathNode.getUnitAttribute());
	}

	public static Element create(MathNode mathNode,
			String nodeId, boolean hasSmallUnits) {
		return create(mathNode.getUnitAttribute(), nodeId,
				hasSmallUnits);
	}

	public static Element create(UnitAttribute dataUnit) {
		return create(dataUnit, null);
	}

	public static Element create(UnitAttribute dataUnit,
			String nodeId) {
		return create(dataUnit, nodeId, true);
	}

	public static Element create(UnitAttribute dataUnit,
			String nodeId, boolean hasSmallUnits) {

		Element numerator = DOM.createDiv();
		Element denominator = DOM.createDiv();

		UnitMultiple[] dataUnitArray = dataUnit.getUnitMultiples();

		for (UnitMultiple unitMultiple : dataUnitArray) {
			if ("".equals(unitMultiple.toString())) {
				continue;
			}

			UnitName name = unitMultiple.getUnitName();
			String exponent = unitMultiple.getUnitExponent();

			String symbol = name.getSymbol();
			if ("".equals(symbol)) {
				symbol = name.toString();
			}
			
			Element unitDiv = DOM.createDiv();
			unitDiv.setAttribute("id", symbol + UNIT_NODE_DELIMITER + nodeId);
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
			symbolDiv.addClassName(TypeML.Exponential.asChild(true));
			symbolDiv.setInnerText(symbol);
			unitDiv.appendChild(symbolDiv);

			Element expDiv = DOM.createDiv();
			expDiv.addClassName(TypeML.Exponential.asChild(false));
			expDiv.setInnerText(exponent);
			unitDiv.appendChild(expDiv);
		}

		if (denominator.getChildCount() > 0) {
			Element frac = DOM.createDiv();
			frac.addClassName(TypeML.Term.asChild());
			numerator.addClassName(TypeML.Fraction.asChild(true));
			denominator.addClassName(TypeML.Fraction.asChild(false));
			frac.appendChild(numerator);
			frac.appendChild(denominator);
			frac.addClassName(UNIT_CLASSNAME);
			if (hasSmallUnits) {
				frac.getStyle().setFontSize(UNIT_SIZE_SMALL, Unit.PCT);
			}

			if (numerator.getChildCount() == 0) {
				Element oneDiv = DOM.createDiv();
				oneDiv.setInnerText("1");
				numerator.appendChild(oneDiv);
			}
			return frac;
		} else {
			numerator.addClassName(TypeML.Term.asChild());
			numerator.addClassName(UNIT_CLASSNAME);
			if (hasSmallUnits) {
				numerator.getStyle().setFontSize(UNIT_SIZE_SMALL, Unit.PCT);
			}
			return numerator;
		}
	}
}