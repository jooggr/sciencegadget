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
package com.sciencegadgets.client.algebra;

import java.math.BigDecimal;

import com.google.gwt.dom.client.Node;
import com.sciencegadgets.client.JSNICalls;
import com.sciencegadgets.client.algebra.EquationTree.EquationNode;
import com.sciencegadgets.client.algebra.edit.RandomSpecPanel;
import com.sciencegadgets.shared.MathAttribute;
import com.sciencegadgets.shared.TrigFunctions;
import com.sciencegadgets.shared.TypeSGET;
import com.sciencegadgets.shared.dimensions.CommonConstants;
import com.sciencegadgets.shared.dimensions.UnitAttribute;
import com.sciencegadgets.shared.dimensions.UnitEnum;
import com.sciencegadgets.shared.dimensions.UnitMap;
import com.sciencegadgets.shared.dimensions.UnitName;

public class EquationValidator {

	/**
	 * Validates the structure of the node, looks at:<br/>
	 * · proper number of children<br/>
	 * · numbers can be parsed<br/>
	 * · no sums within sums<br/>
	 * · no terms within terms<br/>
	 * · log must have base attribute<br/>
	 * · trig must have function attribute<br/>
	 * 
	 * @return True if well formed<br/>
	 *         False if invalid
	 */
	public void validateEquationNode(EquationNode node)
			throws IllegalStateException {

		TypeSGET type = node.getType();
		boolean isInEditMode = node.getTree().isInEditMode();

		int childCount = node.getChildCount();
		boolean isWrongChildren = false;

		// Confirm the correct number of children
		switch (type.childRequirement()) {
		case EQUATION:
			if (childCount != 3)
				isWrongChildren = true;
			break;
		case TERMINAL:
			if (childCount != 0
					|| node.getXMLNode().getChild(0).getNodeType() != Node.TEXT_NODE) {
				isWrongChildren = true;
			}
			break;
		case UNARY:
			if (childCount != 1)
				isWrongChildren = true;
			break;
		case BINARY:
			if (childCount != 2)
				isWrongChildren = true;
			break;
		case SEQUENCE:
			if (childCount < 3) {
				isWrongChildren = true;
			}
			break;
		}

		if (isWrongChildren) {
			String errorMessage = "Wrong number of children in type: " + type
					+ " which is a " + type.childRequirement()
					+ "function, but has (" + childCount + ") children: "
					+ node.toString();
			throw new IllegalStateException(errorMessage, new Throwable(
					errorMessage));
		}

		switch (type) {
		case Number:
			// Confirm that the symbol is a number in solve mode
			if (!isInEditMode) {
				try {
					String symbol = node.getSymbol();
					if (!RandomSpecPanel.RANDOM_SYMBOL.equals(symbol)) {
						Double.parseDouble(symbol);
					}
				} catch (NumberFormatException e) {
					try {
						new BigDecimal(node.getAttribute(MathAttribute.Value));
					} catch (NumberFormatException ex) {
						String errorMessage = "The number node must have a valid number: "
								+ node.getAttribute(MathAttribute.Value);
						throw new IllegalStateException(errorMessage,
								new Throwable(errorMessage));
					}
				}
			}
			break;
		case Sum:
		case Term:// Confirm that there are < 3 children
			if (type.equals(node.getParentType())) {
				if (TypeSGET.Term.equals(type)) {
					String errorMessage = "There shouldn't be a term in a term"
							+ node.getParent().toString();
					throw new IllegalStateException(errorMessage,
							new Throwable(errorMessage));
				} else if (TypeSGET.Sum.equals(type)) {
					String errorMessage = "There shouldn't be a sum in a sum: "
							+ node.getParent().toString();
					throw new IllegalStateException(errorMessage,
							new Throwable(errorMessage));
				}
			}
			break;
		case Equation:
			node.getTree().checkSideForm();
			break;
		case Log:// Confirm the base is a number
			if (!isInEditMode) {
				try {
					Double.parseDouble(node.getAttribute(MathAttribute.LogBase));
				} catch (NumberFormatException e) {
					if (!"e".equals(node.getAttribute(MathAttribute.LogBase))) {
						String errorMessage = "The base of a log must be a number or e: "
								+ node.getParent().toString();
						throw new IllegalStateException(errorMessage,
								new Throwable(errorMessage));
					}
				}
			}
			break;
		case Trig:// Confirm the function attribute exists
			if (!isInEditMode) {
				if ("".equals(node.getAttribute(MathAttribute.Function))) {
					String errorMessage = "Trig functions must have function attribute: "
							+ node.getParent().toString();
					throw new IllegalStateException(errorMessage,
							new Throwable(errorMessage));
				}
			}
			break;
		}
	}

	public void validateQuantityKinds(EquationTree equationTree)
			throws IllegalStateException {
		getQuantityKind(equationTree.getRoot());
	}

	private UnitMap getQuantityKind(EquationNode node)
			throws IllegalStateException {

		switch (node.getType()) {
		case Number:
		case Variable:
			return new UnitMap(node).getQuantityKindMap();
		case Term:
			UnitMap termMap = new UnitMap();
			for (EquationNode child : node.getChildren()) {
				if (TypeSGET.Operation.equals(child.getType())) {
					continue;
				}
				UnitMap childMap = getQuantityKind(child);
				termMap = termMap.getMultiple(childMap);
			}
			return termMap;
		case Equation:
		case Sum:
			UnitMap sumMap = null;
			for (EquationNode child : node.getChildren()) {
				if (TypeSGET.Operation.equals(child.getType())
						|| "0".equals(child.getAttribute(MathAttribute.Value))) {
					sumMap = new UnitMap(true);
					continue;
				}
				UnitMap childMap = getQuantityKind(child);
				if (sumMap == null) {
					sumMap = childMap;
				} else if (!sumMap.isConvertableTo(childMap)) {
					Throwable t = new Throwable();
					String s = "";
					for (StackTraceElement a : t.getStackTrace()) {
						s = s + "\n" + a.toString();
					}
					JSNICalls.log("Validate sum eq: " + s);
					throw new IllegalStateException(
							"Units must be similar in sides of the equation and in sums, these are not convertable:\n"
									+ sumMap + "\n" + childMap,
							new Throwable(
									"All sums and equations must contain equivalent derived quantity kinds for all of it's children: \n"
											+ "full: "
											+ sumMap
											+ " -vs- "
											+ childMap
											+ "\nbase: "
											+ sumMap.getBaseQKMap()
											+ " -vs- "
											+ childMap.getBaseQKMap()
											+ "\nof node: "
											+ node
											+ "\ntested child: " + child));
				}
			}
			if (sumMap == null) {
				return new UnitMap();
			} else {
				return sumMap;
			}
		case Exponential:
			EquationNode base = node.getChildAt(0);
			EquationNode exp = node.getChildAt(1);
			UnitMap baseMap = getQuantityKind(base);
			UnitMap expMap = getQuantityKind(exp);

			if (expMap.size() != 0) {
				throw new IllegalStateException("Exponents can't have units",
						new Throwable("Exponents can't have units: \n"
								+ "attribute: " + expMap + "\nof node: " + exp));
			}

			if ((baseMap.size() == 0)) {
				return baseMap;
			}

			if (TypeSGET.Number.equals(exp.getType())) {
				try {
					Integer expValue = Integer.parseInt(exp
							.getAttribute(MathAttribute.Value));
					baseMap = baseMap.getExponential(expValue, 1);
					return baseMap;
				} catch (NumberFormatException e) {
					throw new IllegalStateException(
							"Exponent should be number",
							new Throwable(
									"The base of a non-integer exponential has units: \n"
											+ baseMap + "\nof node: " + node));
				}
			} else if(TypeSGET.Fraction.equals(exp.getType())){
				EquationNode expNum = exp.getChildAt(0);
				EquationNode expDen = exp.getChildAt(1);
				if(TypeSGET.Number.equals(expNum.getType()) && TypeSGET.Number.equals(expDen.getType())) {
					try {
						Integer expNumValue = Integer.parseInt(expNum
								.getAttribute(MathAttribute.Value));
						Integer expDenValue = Integer.parseInt(expDen
								.getAttribute(MathAttribute.Value));
						baseMap = baseMap.getExponential(expNumValue, expDenValue);
						return baseMap;
					} catch (NumberFormatException e) {
						throw new IllegalStateException(
								"Exponent parts should me number",
								new Throwable(
										"The base of a non-integer exponential has units: \n"
												+ baseMap + "\nof node: " + node));
					}
				}
			} else {
				throw new IllegalStateException(
						"Bases of exponentials can only have units if the exponent is a constant integer",
						new Throwable(
								"The base of a variable exponential has units: \n"
										+ baseMap + "\nof node: " + node));
			}
			// unreachable
		case Fraction:
			EquationNode numerator = node.getChildAt(0);
			EquationNode denominator = node.getChildAt(1);
			UnitMap divisionMap = getQuantityKind(numerator).getDivision(
					getQuantityKind(denominator));

			return divisionMap;
		case Log:
			UnitMap logArgumentMap = getQuantityKind(node.getChildAt(0));
			if (logArgumentMap.size() == 0) {
				return logArgumentMap;
			} else {
				throw new IllegalStateException(
						"The argument of a logarithm can't have units",
						new Throwable("The child of a log has units: \n"
								+ logArgumentMap + "\nof node: " + node));

			}
			// unreachable
		case Trig:
			try {
				TrigFunctions function = TrigFunctions.valueOf(node
						.getAttribute(MathAttribute.Function));
				if (function.isArc()) {
					UnitMap angleMap = new UnitMap();
					angleMap.put(new UnitName(UnitEnum.Angle_2370.getQuantityKindName()), 1);
					return angleMap;
//					return getQuantityKind(node.getChildAt(0));
				} else {
					UnitMap trigArgumentMap = getQuantityKind(node
							.getChildAt(0));
					UnitMap comparison = new UnitMap();
					comparison.put(new UnitName(UnitAttribute.ANGLE), 1);
					if (comparison.isConvertableTo(trigArgumentMap)) {
						break;
					} else {
						throw new IllegalStateException(
								"The argument of a trigonometric function must have the unit type of "
										+ UnitAttribute.ANGLE, new Throwable(
										"The child of a trig function doesn't have units of "
												+ UnitAttribute.ANGLE + ": \n"
												+ trigArgumentMap
												+ "\nof node: " + node));
					}
				}
			} catch (IllegalArgumentException e) {
				throw new IllegalStateException(
						"Trig node doesn't have function attribute",
						new Throwable(
								"Trig node doesn't have function attribute "
										+ "\n node: " + node));
			}
		}
		
		return new UnitMap();
	}
}
