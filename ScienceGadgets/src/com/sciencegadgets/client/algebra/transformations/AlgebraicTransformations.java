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
package com.sciencegadgets.client.algebra.transformations;

import java.util.LinkedHashSet;

import com.google.gwt.user.client.ui.Label;
import com.sciencegadgets.client.JSNICalls;
import com.sciencegadgets.client.Moderator;
import com.sciencegadgets.client.algebra.EquationTree.EquationNode;
import com.sciencegadgets.client.entities.users.Badge;
import com.sciencegadgets.client.ui.CSS;
import com.sciencegadgets.shared.TypeSGET;
import com.sciencegadgets.shared.TypeSGET.Operator;

/**
 * This class contains the set of static methods used to perform algebraic
 * changes by multiple type specific TransformationsList's
 */
public class AlgebraicTransformations {

	public static Label response = new Label();

	/**
	 * Performs the operation specified by the node
	 * 
	 * @param opNode
	 *            - operation to perform
	 */
	public static TransformationList<? extends TransformationButton> operation(
			EquationNode opNode) {

		if (opNode.getPrevSibling() != null && opNode.getNextSibling() != null) {
			switch (opNode.getOperation()) {
			case CROSS:
			case DOT:
			case SPACE:
				return new MultiplyTransformations(opNode);
			case MINUS:
			case PLUS:
				return new AdditionTransformations(opNode);
			}
		}
		return null;
	}

	/**
	 * Propagates negative into the node depending on its type<br/>
	 * <b>operation</b> - do nothing<br/>
	 * <b>number or variable</b> - change symbol<br/>
	 * <b>term</b> - propagate to first child<br/>
	 * <b>sum</b> - propagate to each child<br/>
	 * <b>fraction</b> - propagate to first child<br/>
	 * <b>everything else</b> - encase in term with the number negative one<br/>
	 * 
	 * @param toNegate
	 */
	public static void propagateNegative(EquationNode toNegate) {

		switch (toNegate.getType()) {
		case Operation:
			break;
		case Number:
		case Variable:
			String symbol = toNegate.getSymbol();
			if (symbol.startsWith(Operator.MINUS.getSign())) {
				symbol = symbol.replaceFirst(Operator.MINUS.getSign(), "");
			} else {
				symbol = Operator.MINUS.getSign() + symbol;
			}
			toNegate.setSymbol(symbol);
			break;
		case Term:
			propagateNegative(toNegate.getFirstChild());
			break;
		case Sum:
			for (EquationNode sumChild : toNegate.getChildren()) {
				if (TypeSGET.Operation.equals(sumChild.getType())) {
					continue;
				}
				EquationNode prevOp = sumChild.getPrevSibling();
				if (prevOp != null
						&& TypeSGET.Operation.equals(prevOp.getType())) {
					Operator opValue = prevOp.getOperation();
					opValue = Operator.PLUS.equals(opValue) ? Operator.MINUS
							: Operator.PLUS;
					prevOp.setSymbol(opValue.getSign());
				} else {
					propagateNegative(sumChild);
				}
			}
			break;
		case Fraction:
			propagateNegative(toNegate.getFirstChild());
			break;
		default:
			EquationNode casing = toNegate.encase(TypeSGET.Term);
			casing.addFirst(TypeSGET.Operation, Operator.getMultiply()
					.getSign());
			casing.addFirst(TypeSGET.Number, "-1");
			break;
		}
	}

	public static LinkedHashSet<Integer> FIND_PRIME_FACTORS(Integer number) {
		LinkedHashSet<Integer> factors = new LinkedHashSet<Integer>();

		if (number < 0) {
			number = Math.abs(number);
		}
		factors.add(1);

		int start = 2;
		byte inc = 1;
		if (number % 2 == 1) {// odd numbers can't have even factors
			start = 3;
			inc = 2;
		}
		for (int i = start; i <= Math.sqrt(number); i = i + inc) {
			if (number % i == 0) {
				factors.add(i);
			}
		}
		return factors;
	}

	/**
	 * Reciprocates the given node and simplifies in one of two ways:<br/>
	 * 1) If the node is in a fraction or a term in a fraction, it is moved over
	 * to the other side of the fraction.<br/>
	 * 2) Encases the node in a fraction and adds the number one to the
	 * numerator
	 */
	public static void reciprocate(EquationNode toReciprocate) {

		EquationNode parent = toReciprocate.getParent();

		if (TypeSGET.Fraction.equals(parent.getType())) {

			boolean inNumerator = toReciprocate.getIndex() == 0;
			EquationNode otherSide = inNumerator ? toReciprocate
					.getNextSibling() : toReciprocate.getPrevSibling();
			otherSide = otherSide.encase(TypeSGET.Term);

			otherSide.append(TypeSGET.Operation, Operator.getMultiply()
					.getSign());
			otherSide.append(toReciprocate);

			if (inNumerator) {
				parent.addFirst(TypeSGET.Number, "1");
			} else {
				parent.replace(otherSide);
			}

		} else if (TypeSGET.Term.equals(parent.getType())
				&& TypeSGET.Fraction.equals(parent.getParentType())) {

			if (toReciprocate.getIndex() == 0) {
				toReciprocate.getNextSibling().remove();
			} else {
				toReciprocate.getPrevSibling().remove();
			}

			boolean inNumerator = parent.getIndex() == 0;
			EquationNode otherSide = inNumerator ? parent.getNextSibling()
					: parent.getPrevSibling();

			otherSide = otherSide.encase(TypeSGET.Term);
			otherSide.append(TypeSGET.Operation, Operator.getMultiply()
					.getSign());
			otherSide.append(toReciprocate);

			parent.decase();

		} else {
			EquationNode frac = toReciprocate.encase(TypeSGET.Fraction);
			frac.addFirst(TypeSGET.Number, "1");
		}
	}

	/**
	 * Separates a negative number into the product of -1 and the absolute value
	 * of the number
	 * 
	 * @param negNode
	 *            - node of negative number
	 */
	public static TransformationButton separateNegative_check(
			EquationNode negNode,
			TransformationList<TransformationButton> context) {
		if (negNode.getSymbol().startsWith(TypeSGET.Operator.MINUS.getSign())
				&& !negNode.getSymbol().equals("-1")) {
			return new SeperateNegButton(negNode, context);
		}
		return null;
	}
}

// /////////////////////////////////////////////////////////////
// Transformation Buttons
// ////////////////////////////////////////////////////////////

/**
 * -x = -1 &middot; x
 * 
 */
class SeperateNegButton extends TransformationButton {
	private EquationNode negNode;

	SeperateNegButton(final EquationNode negNode,
			TransformationList<TransformationButton> context) {
		super("Seperate (-)", context);
		addStyleName(CSS.NUMBER);
		this.negNode = negNode;
	}

	@Override
	public Badge getAssociatedBadge() {
		return null;
	}

	@Override
	public boolean meetsAutoTransform() {
		return true;
	}

	@Override
	public void transform() {

		EquationNode prevSib = negNode.getPrevSibling();
		String newSymbol = negNode.getSymbol().replaceFirst(
				TypeSGET.Operator.MINUS.getSign(), "");
		negNode.setSymbol(newSymbol);

		if (prevSib != null && TypeSGET.Operation.equals(prevSib.getType()) && TypeSGET.Sum.equals(negNode.getParentType())) {
			if (Operator.PLUS.getSign().equals(prevSib.getSymbol())) {
				prevSib.setSymbol(Operator.MINUS.getSign());
			} else if (Operator.MINUS.getSign().equals(prevSib.getSymbol())) {
				prevSib.setSymbol(Operator.PLUS.getSign());
			} else {
				JSNICalls
						.error("The previous operator contains neither a plus or minus");
			}
		} else {
			EquationNode parent = negNode.getParent();
			parent = negNode.encase(TypeSGET.Term);
			int nodeIndex = negNode.getIndex();
			parent.addBefore(nodeIndex, TypeSGET.Operation, TypeSGET.Operator
					.getMultiply().getSign());
			parent.addBefore(nodeIndex, TypeSGET.Number, "-1");
		}
		Moderator.reloadEquationPanel("-" + newSymbol + " = -1"
				+ Operator.getMultiply().getSign() + newSymbol, negNode.getId());
	}
}


