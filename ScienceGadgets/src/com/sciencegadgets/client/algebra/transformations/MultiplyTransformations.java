package com.sciencegadgets.client.algebra.transformations;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.sciencegadgets.client.JSNICalls;
import com.sciencegadgets.client.Moderator;
import com.sciencegadgets.client.algebra.AlgebraActivity;
import com.sciencegadgets.client.algebra.MathTree.MathNode;
import com.sciencegadgets.shared.MathAttribute;
import com.sciencegadgets.shared.TypeML;
import com.sciencegadgets.shared.TypeML.Operator;
import com.sciencegadgets.shared.UnitUtil;

public class MultiplyTransformations extends Transformations {

	private static final long serialVersionUID = 3127633894356779264L;
	
	MathNode left;
	MathNode operation;
	MathNode right;
	MathNode parent;

	TypeML leftType;
	TypeML rightType;
	
	public MultiplyTransformations(MathNode left, MathNode multiplyNode,
			MathNode right) {

		this.left = left;
		this.operation = multiplyNode;
		this.right = right;

		this.leftType = left.getType();
		this.rightType = right.getType();

		add(multiplySpecialNumber_check());
		add(multiplyNumbers_check());
		add(multiplyFraction_check());
		add(multiplyDistribution_check());
		add(multiplyCombineBases_check());
		add(multiplyCombineExponents_check());
		add(logRule_check());

	}
	/**
	 * Multiplying by one of the specially designated numbers (-1, 0 1)
	 */
	private MultiplyTransformButton multiplySpecialNumber_check() {

		if (!TypeML.Number.equals(leftType) && !TypeML.Number.equals(rightType)) {
			return null;
		}

		final int same = 0;
		BigDecimal negOne = new BigDecimal(-1);
		BigDecimal zero = new BigDecimal(0);
		BigDecimal one = new BigDecimal(1);

		try {
			BigDecimal rightValue = new BigDecimal(right.getSymbol());
			if (rightValue.compareTo(zero) == same) {
				return new MultiplyZeroButton(this, left, right);
			} else if (rightValue.compareTo(one) == same) {
				return new MultiplyOneButton(this, left, right);
			} else if (rightValue.compareTo(negOne) == same) {
				return new MultiplyNegOneButton(this, left, right);
			}

			BigDecimal leftValue = new BigDecimal(left.getSymbol());
			if (leftValue.compareTo(zero) == same) {
				return new MultiplyZeroButton(this, right, left);
			} else if (leftValue.compareTo(one) == same) {
				return new MultiplyOneButton(this, right, left);
			} else if (leftValue.compareTo(negOne) == same) {
				return new MultiplyNegOneButton(this, right, left);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			JSNICalls
					.error("A number node couldn't be parsed: " + e.toString());
			return null;
		}
		return null;
	}
	/**
	 * Multiply two numbers, prompts for the product unless the user skill level is high enough
	 */
	private MultiplyTransformButton multiplyNumbers_check() {
		
		if (!(TypeML.Number.equals(leftType) && TypeML.Number.equals(rightType))) {
			return null;
		}
		
		try {
			return new MultiplyNumbersButton(this);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			JSNICalls
			.error("A number node couldn't be parsed: " + e.toString());
			return null;
		}
	}

	/**
	 * x &middot; (y+z) = xy + xz
	 */
	private MultiplyTransformButton multiplyDistribution_check() {

		if (TypeML.Sum.equals(rightType)) {
			return new MultiplyDistributionButton(this, left, right, true);
		} else if (TypeML.Sum.equals(leftType)) {
			return new MultiplyDistributionButton(this, right, left, false);
		} else {
			return null;
		}
	}

	/**
	 * x<sup>a</sup> &middot; y<sup>a</sup> = (x &middot; y)<sup>a</sup>
	 */
	private MultiplyTransformButton multiplyCombineExponents_check() {

		if (!TypeML.Exponential.equals(leftType)
				|| !TypeML.Exponential.equals(rightType)) {
			return null;
		}

		if (left.getChildAt(1).isLike(right.getChildAt(1))) {
			return new MultiplyCombineExponentsButton(this);
		} else {
			return null;
		}
	}

	/**
	 * x<sup>a</sup> &middot; x<sup>b</sup> = x<sup>a+b</sup><br/>
	 * If either or both sides is not an exponential, it is encased and raised
	 * to the power of 1 first <br/>
	 * ex: x &middot; x = x<sup>1+1</sup> <br/>
	 * ex: x<sup>a</sup> &middot; x = x<sup>a+1</sup>
	 */
	private MultiplyTransformButton multiplyCombineBases_check() {

		// May not already be in exponent eg. a = a^1
		// could factor out entire side rather than just base
		MathNode leftBase;
		if (TypeML.Exponential.equals(left.getType())) {
			leftBase = left.getChildAt(0);
		} else {
			leftBase = left;
		}
		MathNode rightBase;
		if (TypeML.Exponential.equals(right.getType())) {
			rightBase = right.getChildAt(0);
		} else {
			rightBase = right;
		}

		if (leftBase.isLike(rightBase)) {
			return new MultiplyCombineBasesButton(this);
		} else {
			return null;
		}
	}

	/**
	 * x/y &middot; a/b = (xa)/(yb)<br/>
	 * x &middot; a/b = (xa)/b
	 */
	private MultiplyTransformButton multiplyFraction_check() {
		if (TypeML.Fraction.equals(rightType)
				&& TypeML.Fraction.equals(leftType)) {
			return new MultiplyFractionsButton(this);
		} else {
			if (TypeML.Fraction.equals(rightType)) {
				return new MultiplyWithFractionButton(this, left, right,
						true);
			} else if (TypeML.Fraction.equals(leftType)) {
				return new MultiplyWithFractionButton(this, right, left,
						false);
			} else {
				return null;
			}
		}
	}

	/**
	 * x &middot; log<sub>b</sub>(a) = log<sub>b</sub>(a<sup>x</sup>)
	 */
	private MultiplyTransformButton logRule_check() {

		if (TypeML.Log.equals(rightType)) {
			return new MultiplyLogRuleButton(this, left, right);
		} else if (TypeML.Log.equals(leftType)) {
			return new MultiplyLogRuleButton(this, right, left);
		} else {
			return null;
		}
	}

}

// ////////////////////////////////////////////////
// Transform buttons
// ///////////////////////////////////////////////
class MultiplyTransformButton extends Button {
	final MathNode left;
	final MathNode right;
	final MathNode operation;
	final MathNode parent;

	MultiplyTransformButton(MultiplyTransformations context, String html) {
		super(html);
		this.left = context.left;
		this.right = context.right;
		this.operation = context.operation;
		this.parent = operation.getParent();

		left.highlight();
		operation.highlight();
		right.highlight();
	}
}

/**
 * 0 &middot; x = 0<br/>
 * x &middot; 0 = 0
 */
class MultiplyZeroButton extends MultiplyTransformButton {
	MultiplyZeroButton(MultiplyTransformations context,
			final MathNode other, final MathNode zero) {
		super(context, "x·0=0");

		addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {


				String otherSymbol = other.getSymbol();

				// Remove residual operations
				MathNode first = zero.getIndex() < other.getIndex() ? zero
						: other;
				MathNode second = zero.getIndex() > other.getIndex() ? zero
						: other;
				MathNode firstOp = first.getPrevSibling();
				MathNode secondNext = second.getNextSibling();
				if (firstOp != null
						&& TypeML.Operation.equals(firstOp.getType())) {
					firstOp.remove();
				} else if (secondNext != null
						&& TypeML.Operation.equals(secondNext.getType())) {
					secondNext.remove();
				}

				zero.remove();
				operation.remove();
				other.remove();

				parent.decase();

				Moderator.reloadEquationPanel(otherSymbol + " "
						+ operation.toString() + " 0 = 0", Rule.MULTIPLICATION);
			}
		});
	}
}

/**
 * x &middot; 1 = x<br/>
 * 1 &middot; x = x
 */
class MultiplyOneButton extends MultiplyTransformButton {
	MultiplyOneButton(MultiplyTransformations context, final MathNode other,
			final MathNode one) {
		super(context, "x·1=x");

		addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {

				String otherSymbol = other.getSymbol();

				one.remove();
				operation.remove();

				parent.decase();

				Moderator.reloadEquationPanel(otherSymbol + " "
						+ operation.toString() + " 1 = " + otherSymbol,
						Rule.MULTIPLICATION);
			}
		});
	}
}

/**
 * x &middot; -1 = -x<br/>
 * -1 &middot; x = -x
 */
class MultiplyNegOneButton extends MultiplyTransformButton {
	MultiplyNegOneButton(MultiplyTransformations context,
			final MathNode other, final MathNode negOne) {
		super(context, "-1·x=-x");

		addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {

				String otherSymbol = other.getSymbol();

				AlgebraicTransformations.propagateNegative(other);

				operation.remove();
				negOne.remove();

				parent.decase();

				Moderator.reloadEquationPanel(otherSymbol + " "
						+ operation.toString() + " -1 = -" + otherSymbol,
						Rule.MULTIPLICATION);
			}
		});
	}
}

/**
 * Prompt for product or automatically multiply if user skill level is high
 * enough
 */
class MultiplyNumbersButton extends MultiplyTransformButton {
	MultiplyNumbersButton(MultiplyTransformations context) {
		super(context, "# · #");

		addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				final BigDecimal leftValue = new BigDecimal(left.getSymbol());
				final BigDecimal rightValue = new BigDecimal(right.getSymbol());
				final BigDecimal totalValue = leftValue.multiply(rightValue);

				if (Moderator.isInEasyMode) {
					multiplyNumbers(left, right, totalValue, leftValue,
							rightValue);

				} else {// prompt

					String question = leftValue.toString() + " "
							+ operation.getSymbol() + " "
							+ rightValue.toString() + " = ";
					NumberPrompt prompt = new NumberPrompt(question, totalValue) {
						@Override
						void onCorrect() {
							multiplyNumbers(left, right, totalValue, leftValue,
									rightValue);
						}
					};

					prompt.appear();
				}

			}
		});
	}

	void multiplyNumbers(MathNode left, MathNode right, BigDecimal totalValue,
			BigDecimal leftValue, BigDecimal rightValue) {

		totalValue = totalValue.stripTrailingZeros();

		right.setSymbol(totalValue.stripTrailingZeros().toEngineeringString());

		// Combine Units
		HashMap<String, Integer> leftUnitMap = UnitUtil.getUnitMap(left);
		HashMap<String, Integer> rightUnitMap = UnitUtil.getUnitMap(right);
		Set<String> rightKeySet = rightUnitMap.keySet();
		for (String untBase : leftUnitMap.keySet()) {
			Integer newRightExp = leftUnitMap.get(untBase);
			if (rightKeySet.contains(untBase)) {
				newRightExp += rightUnitMap.get(untBase);
			}
			rightUnitMap.put(untBase, newRightExp);
		}
		String combinedUnit = UnitUtil.getUnitAttribute(rightUnitMap);
		right.setAttribute(MathAttribute.Unit, combinedUnit);

		left.remove();
		operation.remove();

		parent.decase();

		Moderator.reloadEquationPanel(leftValue.stripTrailingZeros()
				.toEngineeringString()
				+ " "
				+ operation.toString()
				+ " "
				+ rightValue.stripTrailingZeros().toEngineeringString()
				+ " = "
				+ totalValue.stripTrailingZeros().toEngineeringString(),
				Rule.MULTIPLICATION);
	}
}

/**
 * x &middot; (y+z) = xy + xz
 */
class MultiplyDistributionButton extends MultiplyTransformButton {
	MultiplyDistributionButton(MultiplyTransformations context,
			final MathNode dist, final MathNode sum, final boolean isRightSum) {
		super(context, "x·(y+z)=xy+xz");

		addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {

				for (MathNode sumChild : sum.getChildren()) {
					if (!TypeML.Operation.equals(sumChild.getType())) {

						MathNode sumChildCasings = sumChild.encase(TypeML.Term);

						int index = isRightSum ? 0 : -1;
						sumChildCasings.addBefore(index, operation.getType(),
								operation.getSymbol());
						sumChildCasings.addBefore(index, dist.clone());
					}
				}

				dist.remove();
				operation.remove();

				parent.decase();

				Moderator.reloadEquationPanel("Distribute",
						Rule.DISTRIBUTIVE_PROPERTY);
			}
		});
	}
}

/**
 * x<sup>a</sup> &middot; y<sup>a</sup> = (x &middot; y)<sup>a</sup>
 */
class MultiplyCombineExponentsButton extends MultiplyTransformButton {
	MultiplyCombineExponentsButton(MultiplyTransformations context) {
		super(context, "<sup>a</sup>·y<sup>a</sup>=(x·y)<sup>a</sup>");

		addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {

				MathNode leftBase = left.getChildAt(0);
				MathNode rightBase = right.getChildAt(0);

				MathNode rightCasing = rightBase.encase(TypeML.Term);

				rightCasing.addBefore(0, operation);
				rightCasing.addBefore(0, leftBase);

				left.remove();

				parent.decase();

				Moderator.reloadEquationPanel("Combine Exponents",
						Rule.EXPONENT_PROPERTIES);

			}
		});
	}
}

/**
 * x<sup>a</sup> &middot; x<sup>b</sup> = x<sup>a+b</sup><br/>
 * If either or both sides is not an exponential, it is encased and raised to
 * the power of 1 first <br/>
 * ex: x &middot; x = x<sup>1+1</sup> <br/>
 * ex: x<sup>a</sup> &middot; x = x<sup>a+1</sup>
 */
class MultiplyCombineBasesButton extends MultiplyTransformButton {
	MultiplyCombineBasesButton(MultiplyTransformations context) {
		super(context, "x<sup>a</sup>·x<sup>b</sup>=x<sup>a+b</sup>");

		addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {

				MathNode leftExponential = left;
				MathNode rightExponential = right;
				if (!TypeML.Exponential.equals(left.getType())) {
					leftExponential = left.encase(TypeML.Exponential);
					leftExponential.append(TypeML.Number, "1");
				}
				if (!TypeML.Exponential.equals(right.getType())) {
					rightExponential = right.encase(TypeML.Exponential);
					rightExponential.append(TypeML.Number, "1");
				}

				MathNode leftExp = leftExponential.getChildAt(1);
				MathNode rightExp = rightExponential.getChildAt(1);

				MathNode rightCasing = rightExp.encase(TypeML.Sum);

				rightCasing.addBefore(0, TypeML.Operation,
						Operator.PLUS.getSign());
				rightCasing.addBefore(0, leftExp);

				leftExponential.remove();
				operation.remove();

				parent.decase();

				Moderator.reloadEquationPanel("Combine Bases",
						Rule.EXPONENT_PROPERTIES);
			}
		});
	}
}

/**
 * x &middot; a/b = (xa)/b
 */
class MultiplyWithFractionButton extends MultiplyTransformButton {
	MultiplyWithFractionButton(MultiplyTransformations context,
			final MathNode nonFrac, final MathNode fraction,
			final boolean isRightFraction) {
		super(context, "x·a/b=(xa)/b");

		addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {

				MathNode numerator = fraction.getChildAt(0);
				numerator = numerator.encase(TypeML.Term);

				int index = isRightFraction ? 0 : -1;
				numerator.addBefore(index, operation);
				numerator.addBefore(index, nonFrac);

				parent.decase();

				Moderator.reloadEquationPanel("Multiply with Fraction",
						Rule.FRACTION_MULTIPLICATION);
			}
		});
	}
}

/**
 * x/y &middot; a/b = (xa)/(yb)
 */
class MultiplyFractionsButton extends MultiplyTransformButton {
	MultiplyFractionsButton(MultiplyTransformations context) {
		super(context, "x/y·a/b=(xa)/(yb)");

		addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {

				MathNode numerator = right.getChildAt(0);
				numerator = numerator.encase(TypeML.Term);
				numerator.addBefore(0, operation);
				numerator.addBefore(0, left.getChildAt(0));

				MathNode denominator = right.getChildAt(1);
				denominator = denominator.encase(TypeML.Term);
				denominator.addBefore(0, TypeML.Operation,
						operation.getSymbol());
				denominator.addBefore(0, left.getChildAt(1));

				left.remove();

				parent.decase();

				Moderator.reloadEquationPanel("Multiply Fractions",
						Rule.FRACTION_MULTIPLICATION);
			}
		});
	}
}

/**
 * x &middot; log<sub>b</sub>(a) = log<sub>b</sub>(a<sup>x</sup>)
 */
class MultiplyLogRuleButton extends MultiplyTransformButton {
	MultiplyLogRuleButton(MultiplyTransformations context, final MathNode other, final MathNode log) {
		super(context, "x·log<sub>b</sub>(a)=log<sub>b</sub>(a<sup>x</sup>)");

		addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				
				MathNode exp = log.getFirstChild().encase(TypeML.Exponential);
				exp.append(other);
				
				operation.remove();

				parent.decase();

				Moderator.reloadEquationPanel("Log Power Rule",
						Rule.LOGARITHM);
			}
		});
	}
}
