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
package com.admin.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.sciencegadgets.client.algebra.EquationTree;
import com.sciencegadgets.client.algebra.EquationTree.EquationNode;
import com.sciencegadgets.client.algebra.transformations.AdditionTransformations;
import com.sciencegadgets.shared.TypeSGET.Operator;

public class TestBot_Addition {

	public static void deployTestBot() throws Exception {
		String sum = "<mfenced separators=\"\" open=\"\" close=\"\"><mi>s</mi><mo>+</mo><mi>u</mi><mo>+</mo><mrow><mi>m</mi><mo>\u00B7</mo><mi>m</mi></mrow></mfenced>";
		String term = "<mrow><mi>a</mi><mo>\u00B7</mo><msup><mi>a</mi><mi>a</mi></msup><mo>\u00B7</mo><mi>a</mi></mrow>";
		String exp = "<msup><mi>a</mi><mi>a</mi></msup>";
		String frac = "<mfrac><mrow><mi>a</mi><mo>\u00B7</mo><mi>a</mi><mo>\u00B7</mo><mi>a</mi></mrow><mrow><mi>d</mi><mo>\u00B7</mo><mi>e</mi><mo>\u00B7</mo><mi>n</mi></mrow></mfrac>";
		String var = "<mi>a</mi>";
		String num = "<mn>7</mn>";

		String a = "<mn>0</mn>";
		String b = "<mn>7.333</mn>";
		String c = "<mn>-7</mn>";
		String d = "<mn>-7.33</mn>";

		String fracA = "<mfrac><mrow><mi>a</mi><mo>\u00B7</mo><mi>n</mi><mo>\u00B7</mo><mi>a</mi></mrow><mrow><mi>d</mi><mo>\u00B7</mo><mi>e</mi><mo>\u00B7</mo><mi>n</mi></mrow></mfrac>";
		String fracB = "<mfrac><mrow><mi>a</mi><mo>\u00B7</mo><mi>a</mi><mo>\u00B7</mo><mi>a</mi></mrow><mrow><mi>d</mi><mo>\u00B7</mo><mi>n</mi><mo>\u00B7</mo><mi>e</mi></mrow></mfrac>";

		String allTerm = "<mrow>" + exp + "<mo>\u00B7</mo>" + frac
				+ "<mo>\u00B7</mo>" + var + "<mo>\u00B7</mo>" + num + "</mrow>";
		String allTermm = "<mrow>" + exp + "<mo>\u00B7</mo>" + frac
				+ "<mo>\u00B7</mo>" + var + "<mo>\u00B7</mo>" + var + "</mrow>";
		// String baseTest = "<msup>" + type + "<mn>2</mn></msup>";

		// String[] types = { a, b, c, d };
		// String[] types = { term, exp, frac, var, num };
		String[] types = { term, exp, frac, var, num };
		for (String type : types) {
			// for (String tipe : types) {

			String leftTest = a;
			String rightTest = type;

			botCase(leftTest, rightTest);
		}
		// }

	}

	private static void botCase(String leftTest, String rightTest)
			throws Exception {

		Element leftElement = (Element) new HTML(leftTest).getElement()
				.getFirstChildElement().cloneNode(true);
		Element multiplyElement = (Element) new HTML("<mo>+</mo>").getElement()
				.getFirstChildElement().cloneNode(true);
		Element rightElement = (Element) new HTML(rightTest).getElement()
				.getFirstChildElement().cloneNode(true);

		HTML disp = new HTML(
				"<math><mpadded class=\"parentDummy\"></mpadded><mo>=</mo><mi>inEquation</mi></math>");
		Element mathElement = disp.getElement().getFirstChildElement();
		Element parentElement = new HTML(
				"<mfenced separators=\"\" open=\"\" close=\"\"><mi>L</mi><mo>( </mo><mo> )</mo><mi>R</mi></mfenced>")
				.getElement().getFirstChildElement();
		Element parentdummy = mathElement.getElementsByTagName("mpadded")
				.getItem(0);
		Element grandParentElement = parentdummy.getParentElement();
		grandParentElement.insertBefore(parentElement, parentdummy);
		parentdummy.removeFromParent();

		parentElement.insertAfter(leftElement, parentElement
				.getElementsByTagName("mo").getItem(0));
		parentElement.insertAfter(multiplyElement, leftElement);
		parentElement.insertAfter(rightElement, multiplyElement);

		EquationTree mathTree = new EquationTree(mathElement, false);
		EquationNode leftNode = mathTree
				.getNodeById(leftElement.getAttribute("id"));
		EquationNode rightNode = mathTree.getNodeById(rightElement
				.getAttribute("id"));
		EquationNode operation = mathTree.getNodeById(multiplyElement.getAttribute("id"));
		EquationNode parent = operation.getParent();
		EquationNode grandParent = parent.getParent();

		boolean isMinus = !Operator.PLUS.getSign().equals(operation.getSymbol());

		if (isMinus && !Operator.MINUS.getSign().equals(operation.getSymbol())) {
			throw new Exception(
					"Sign is neither Operator.MINUS or Operator.PLUS: "
							+ operation.toString());
		}

		HTML dispBefore = new HTML(disp.getElement().getFirstChildElement()
				.getString());

		RootPanel.get().add(dispBefore);
//		assign(leftNode, operation, rightNode, !isMinus);
		RootPanel.get().add(
				new HTML(disp.getElement().getFirstChildElement().getString()));
		RootPanel.get().add(new HTML("&nbsp;"));
	}
}
