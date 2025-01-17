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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.sciencegadgets.client.JSNICalls;
import com.sciencegadgets.client.Moderator;
import com.sciencegadgets.client.algebra.EquationTree.EquationNode;
import com.sciencegadgets.client.entities.users.Badge;
import com.sciencegadgets.client.ui.CSS;
import com.sciencegadgets.shared.MathAttribute;
import com.sciencegadgets.shared.TrigFunctions;
import com.sciencegadgets.shared.TypeSGET;
import com.sciencegadgets.shared.TypeSGET.Operator;
import com.sciencegadgets.shared.dimensions.CommonConstants;
import com.sciencegadgets.shared.dimensions.UnitAttribute;
import com.sciencegadgets.shared.dimensions.UnitEnum;

public class TrigTransformations extends
		TransformationList<TrigTransformButton> {

	private static final long serialVersionUID = 2158189067374424843L;

	EquationNode trig;
	EquationNode argument;

	TypeSGET argumentType;

	TrigFunctions function;

	public TrigTransformations(EquationNode trigNode) {
		super(trigNode);

		trig = trigNode;
		argument = trigNode.getChildAt(0);

		argumentType = argument.getType();

		function = TrigFunctions.valueOf(trigNode
				.getAttribute(MathAttribute.Function));

		add(trigEvaluate_check());
		add(trigDefinition_check());
		add(trigReciprocal_check());
		add(inverseTrig_check());
	}

	TrigEvaluateButton trigEvaluate_check() {
		if (TypeSGET.Number.equals(argument.getType())) {
			String unit = argument.getAttribute(MathAttribute.Unit);
			if (unit.contains(UnitEnum.Angle_deg.getUnitName())) {
				return new TrigEvaluateButton(this, true);
			} else if ("".equals(unit)
					|| unit.contains(UnitEnum.Angle_rad.getUnitName())) {
				return new TrigEvaluateButton(this, false);
			}
		}
		return null;
	}

	TrigReciprocalButton trigReciprocal_check() {
		if (!function.isArc()) {
			return new TrigReciprocalButton(this);
		}
		return null;
	}

	TrigDefineButton trigDefinition_check() {
		if (!function.isArc()) {
			return new TrigDefineButton(this);
		}
		return null;
	}

	/**
	 * Check if function within its inverse function or function of its inverse<br/>
	 * sin(arcsin(x)) = x<br/>
	 * arcsin(sin(x)) = x<br/>
	 */
	TrigUnravelButton inverseTrig_check() {
		EquationNode trigChild = trig.getFirstChild();
		if (TypeSGET.Trig.equals(trigChild.getType())) {
			String trigChildFunc = trigChild
					.getAttribute(MathAttribute.Function);
			String trigChildFuncInverse = TrigFunctions
					.getInverseName(trigChildFunc);
			String trigFunc = trig.getAttribute(MathAttribute.Function);
			if (trigFunc.equals(trigChildFuncInverse)) {
				return new TrigUnravelButton(trig, trigChild.getFirstChild(),
						this);
			}
		}
		return null;
	}
}

abstract// ////////////////////////////////////////////////
// Transform buttons
// ///////////////////////////////////////////////
class TrigTransformButton extends TransformationButton {
	final EquationNode trig;
	final EquationNode argument;

	final TypeSGET argumentType;
	final TrigFunctions function;

	protected boolean reloadAlgebraActivity;
	protected TrigTransformations previewContext;

	TrigTransformButton(TrigTransformations context) {
		super(context);
		addStyleName(CSS.TRIG);

		this.trig = context.trig;
		this.argument = context.argument;
		this.argumentType = context.argumentType;
		this.function = context.function;

		this.reloadAlgebraActivity = context.reloadAlgebraActivity;

	}

	@Override
	protected void onTransformationEnd(String changeComment,
			EquationNode nodeToSelect) {
		if (reloadAlgebraActivity) {
			trig.highlight();
		}
		super.onTransformationEnd(changeComment, nodeToSelect);
	}

	@Override
	TransformationButton getPreviewButton(EquationNode trig) {
		previewContext = new TrigTransformations(trig);
		previewContext.reloadAlgebraActivity = false;
		return null;
	}
}

/**
 * All 3 derivations of each of the following<br/>
 * tan(x) = sin(x)/cos(x)<br/>
 * cot(x) = csc(x)/sec(x)<br/>
 * <br/>
 * tanh(x) = sinh(x)/cosh(x)<br/>
 * coth(x) = csch(x)/sec(x)<br/>
 */
class TrigDefineButton extends TrigTransformButton {

	private TrigFunctions[] funcDef;
	private boolean defIsTerm;

	public TrigDefineButton(TrigTransformations context) {
		super(context);

		funcDef = function.getDefinition();

		defIsTerm = funcDef[1] != null;
		String html = defIsTerm ? "*" + funcDef[1] : "/" + funcDef[2];
		html = funcDef[0] + html;

		setHTML(html);
	}

	@Override
	public Badge getAssociatedBadge() {
		return Badge.TRIGONOMETRIC_FUNCTIONS;
	}

	@Override
	public void transform() {
		trig.setSymbol(funcDef[0].toString());
		EquationNode otherTrig;

		if (defIsTerm) {
			EquationNode term = trig.encase(TypeSGET.Term);
			int trigIndex = trig.getIndex();
			otherTrig = term.addAfter(trigIndex, TypeSGET.Trig,
					funcDef[1].toString());
			term.addAfter(trigIndex, TypeSGET.Operation, Operator.getMultiply()
					.getSign());
		} else {
			EquationNode frac = trig.encase(TypeSGET.Fraction);
			otherTrig = frac.addAfter(0, TypeSGET.Trig, funcDef[2].toString());
		}
		otherTrig.append(argument.clone());

		onTransformationEnd(getHTML(), otherTrig);
	}

	@Override
	TransformationButton getPreviewButton(EquationNode operation) {
		super.getPreviewButton(operation);
		return previewContext.trigDefinition_check();
	}
}

//
/**
 * Both ways for each:<br/>
 * sin(x) = 1/csc(x)<br/>
 * cos(x) = 1/sec(x)<br/>
 * tan(x) = 1/cot(x)<br/>
 * <br/>
 * sinh(x) = 1/csch(x)<br/>
 * cosh(x) = 1/sech(x)<br/>
 * tanh(x) = 1/coth(x)<br/>
 */
class TrigReciprocalButton extends TrigTransformButton {
	TrigFunctions reciprocalFunction;

	public TrigReciprocalButton(TrigTransformations context) {
		super(context);

		reciprocalFunction = function.getReciprocal();
		setHTML("1/" + reciprocalFunction);

		addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
			}
		});
	}

	@Override
	public Badge getAssociatedBadge() {
		return Badge.TRIG_FUNCTIONS_RECIPROCAL;
	}

	@Override
	public void transform() {
		AlgebraicTransformations.reciprocate(trig);

		trig.setAttribute(MathAttribute.Function, reciprocalFunction.toString());

		onTransformationEnd(getHTML(), trig);
	}

	@Override
	TransformationButton getPreviewButton(EquationNode operation) {
		super.getPreviewButton(operation);
		return previewContext.trigReciprocal_check();
	}
}

/**
 * sin(arcsin(x)) = x<br/>
 * arcsin(sin(x)) = x<br/>
 */
class TrigUnravelButton extends TrigTransformButton {

	private EquationNode toReplace;
	private EquationNode replacement;

	public TrigUnravelButton(final EquationNode toReplace,
			final EquationNode replacement, TrigTransformations context) {
		super(context);
		setHTML(replacement.getHTMLString(true, true));
		this.toReplace = toReplace;
		this.replacement = replacement;
	}

	@Override
	public Badge getAssociatedBadge() {
		return Badge.TRIG_FUNCTIONS_INVERSE;
	}

	@Override
	public void transform() {
		String changeComment = toReplace.getHTMLString(true, true) + " = "
				+ replacement.getHTMLString(true, true);
		toReplace.replace(replacement);
		onTransformationEnd(changeComment, replacement);
	}
}

/**
 * Argument must be a number node
 *
 */
class TrigEvaluateButton extends TrigTransformButton {
	Boolean isDegrees;

	public TrigEvaluateButton(TrigTransformations context, Boolean isDegrees) {
		super(context);
		this.isDegrees = isDegrees;
	}

	@Override
	public Badge getAssociatedBadge() {
		return Badge.TRIG_EVALUATE;
	}

	@Override
	public boolean meetsAutoTransform() {
		return true;
	}

	@Override
	public void transform() {
		String changeComment = trig.getHTMLString(true, true) + " = ";

		try {
			String argStr = argument.getAttribute(MathAttribute.Value);
			Double argDouble = Double.parseDouble(argStr);
			TrigFunctions func = TrigFunctions.valueOf(trig
					.getAttribute(MathAttribute.Function));
			Double eval = isDegrees ? func.operateDeg(argDouble) : func
					.operateRad(argDouble);
			EquationNode evaluated = trig.replace(TypeSGET.Number, eval + "");
			onTransformationEnd(changeComment + eval, evaluated);
		} catch (NumberFormatException e) {
		}

	}

	@Override
	TransformationButton getPreviewButton(EquationNode operation) {
		super.getPreviewButton(operation);
		return previewContext.trigEvaluate_check();
	}
}
