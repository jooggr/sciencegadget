/*   Copyright 2012 John Gralyan
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.sciencegadgets.client.equationbrowser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * This class holds all the data and retrieval methods for Equations, Variables,
 * Quantities and Units. Anytime a new Equation is added to the EQUATIONS array,
 * the variables that are not already in VARIABLES must be added. The
 * Associations must be added to ASS_EQ_VAR. The same idea is true for all these
 * arrays.
 * 
 * ex. F=MA in EQUATIONS. F, M, A each in a new entry in VARIABLES. (F=MA's
 * index)(F's index) in a new entry in ASS_EQ_VAR for each variable.
 * 
 * @author John Gralyan
 * 
 */
public final class EquationDatabase {

	// flags for methods
	public final byte FLAG_EQUATION = 1;
	public final byte FLAG_VARIABLE = 2;
	public final byte FLAG_QUANTITY = 3;
	public final byte FLAG_UNIT = 4;
	public final byte FLAG_ALGEBRA = 5;
	public final byte[] FLAG_EQUATION_JQMATH = { FLAG_EQUATION, 0 };
	public final byte[] FLAG_EQUATION_ML = { FLAG_EQUATION, 1 };
	public final byte[] FLAG_VARIABLE_LOOK = { FLAG_VARIABLE, 0 };
	public final byte[] FLAG_VARIABLE_DESCRIPTION = { FLAG_VARIABLE, 1 };
	public final byte[] FLAG_ALGEBRA_NAME = { FLAG_ALGEBRA, 0 };
	public final byte[] FLAG_ALGEBRA_EQUATION = { FLAG_ALGEBRA, 1 };

	// tables of one-to-one relationships

	// 0-equation 1-description 2
	private final String[][] EQUATIONS = {
			// Newtonian Mechanics

			/* 0 */{
					"ν=ν_0 + at",
					"<math alttext=\"ν=ν_0 + at\"><mrow><mi>ν</mi><mo>=</mo><mrow><msub><mi>ν</mi><mn>0</mn></msub><mo>+</mo><mrow><mi>a</mi><mi>t</mi></mrow></mrow></mrow></math>" },
			/* 1 */{
					"x=x_0 + ν_0t + 1/2at^2",
					"<math alttext=\"x=x_0 + ν_0t + 1/2at^2\"><mrow><mi>x</mi><mo>=</mo><mrow><mrow><msub><mi>x</mi><mn>0</mn></msub><mo>+</mo><mrow><msub><mi>ν</mi><mn>0</mn></msub><mi>t</mi></mrow></mrow><mo>+</mo><mrow><mrow><mfrac><mn>1</mn><mn>2</mn></mfrac><mi>a</mi></mrow><msup><mi>t</mi><mn>2</mn></msup></mrow></mrow></mrow></math>" },
			/* 2 */{
					"ν^2=ν_0^2 + 2a(x - x_0)",
					"<math alttext=\"ν^2=ν_0^2 + 2a(x - x_0)\"><mrow><msup><mi>ν</mi><mn>2</mn></msup><mo>=</mo><mrow><msubsup><mi>ν</mi><mn>0</mn><mn>2</mn></msubsup><mo>+</mo><mrow><mrow><mn>2</mn><mi>a</mi></mrow><mrow><mo>(</mo><mrow><mi>x</mi><mo>−</mo><msub><mi>x</mi><mn>0</mn></msub></mrow><mo>)</mo></mrow></mrow></mrow></mrow></math>" },
			/* 3 */{
					"F_\\net = ma",
					"<math alttext=\"F_\\net = ma\"><mrow><msub><mi>F</mi><mi class=\"ma-repel-adj\">net</mi></msub><mo>=</mo><mrow><mi>m</mi><mi>a</mi></mrow></mrow></math>" },
			/* 4 */{
					"F_\\fric ≤ μN",
					"<math alttext=\"F_\\fric ≤ μN\"><mrow><msub><mi>F</mi><mi class=\"ma-repel-adj\">fric</mi></msub><mo>≤</mo><mrow><mi>μ</mi><mi>N</mi></mrow></mrow></math>" },
			/* 5 */{
					"a_c = ν^2/r",
					"<math alttext=\"a_c = ν^2/r\"><mrow><msub><mi>a</mi><mi>c</mi></msub><mo>=</mo><mfrac><msup><mi>ν</mi><mn>2</mn></msup><mi>r</mi></mfrac></mrow></math>" },
			/* 6 */{
					"τ=rF{\\sinθ}",
					"<math alttext=\"τ=rF{\\sinθ}\"><mrow><mi>τ</mi><mo>=</mo><mrow class=\"ma-repel-adj\"><mrow><mi>r</mi><mi>F</mi></mrow><mspace width=\".17em\"></mspace><mrow class=\"ma-repel-adj\"><mi class=\"ma-repel-adj\">sin</mi><mspace width=\".17em\"></mspace><mi>θ</mi></mrow></mrow></mrow></math>" },
			/* 7 */{
					"p=mν",
					"<math alttext=\"p=mν\"><mrow><mi>p</mi><mo>=</mo><mrow><mi>m</mi><mi>ν</mi></mrow></mrow></math>" },
			/* 8 */{
					"J=F{Δt}",
					"<math alttext=\"J=F{Δt}\"><mrow><mi>J</mi><mo>=</mo><mrow><mi>F</mi><mrow><mi>Δ</mi><mi>t</mi></mrow></mrow></mrow></math>" },
			/* 9 */{
					"K=1/2mν^2",
					"<math alttext=\"K=1/2mν^2\"><mrow><mi>K</mi><mo>=</mo><mrow><mrow><mfrac><mn>1</mn><mn>2</mn></mfrac><mi>m</mi></mrow><msup><mi>ν</mi><mn>2</mn></msup></mrow></mrow></math>" },
			/* 10 */{
					"\\U_g=mgh",
					"<math alttext=\"\\U_g=mgh\"><mrow><msub><mi class=\"ma-upright\" mathvariant=\"normal\">U</mi><mi>g</mi></msub><mo>=</mo><mrow><mrow><mi>m</mi><mi>g</mi></mrow><mi>h</mi></mrow></mrow></math>" },
			/* 11 */{
					"W=F{Δr}{\\cosθ}",
					"<math alttext=\"W=F{Δr}{\\cosθ}\"><mrow><mi>W</mi><mo>=</mo><mrow class=\"ma-repel-adj\"><mrow><mi>F</mi><mrow><mi>Δ</mi><mi>r</mi></mrow></mrow><mspace width=\".17em\"></mspace><mrow class=\"ma-repel-adj\"><mi class=\"ma-repel-adj\">cos</mi><mspace width=\".17em\"></mspace><mi>θ</mi></mrow></mrow></mrow></math>" },
			/* 12 */{
					"P_\\avg=W/{Δt}",
					"<math alttext=\"P_\\avg=W/{Δt}\"><mrow><msub><mi>P</mi><mi class=\"ma-repel-adj\">avg</mi></msub><mo>=</mo><mfrac><mi>W</mi><mrow><mi>Δ</mi><mi>t</mi></mrow></mfrac></mrow></math>" },
			/* 13 */{
					"P=Fν{\\cosθ}",
					"<math alttext=\"P=Fν{\\cosθ}\"><mrow><mi>P</mi><mo>=</mo><mrow class=\"ma-repel-adj\"><mrow><mi>F</mi><mi>ν</mi></mrow><mspace width=\".17em\"></mspace><mrow class=\"ma-repel-adj\"><mi class=\"ma-repel-adj\">cos</mi><mspace width=\".17em\"></mspace><mi>θ</mi></mrow></mrow></mrow></math>" },
			/* 14 */{
					"F_s=-kx",
					"<math alttext=\"F_s=-kx\"><mrow><msub><mi>F</mi><mi>s</mi></msub><mo>=</mo><mrow><mo>−</mo><mrow><mi>k</mi><mi>x</mi></mrow></mrow></mrow></math>" },
			/* 15 */{
					"U_s=1/2kx^2",
					"<math alttext=\"U_s=1/2kx^2\"><mrow><msub><mi>U</mi><mi>s</mi></msub><mo>=</mo><mrow><mrow><mfrac><mn>1</mn><mn>2</mn></mfrac><mi>k</mi></mrow><msup><mi>x</mi><mn>2</mn></msup></mrow></mrow></math>" },
			/* 16 */{
					"T_s=2π√{m/k}",
					"<math alttext=\"T_s=2π√{m/k}\"><mrow><msub><mi>T</mi><mi>s</mi></msub><mo>=</mo><mrow><mrow><mn>2</mn><mi>π</mi></mrow><msqrt><mfrac><mi>m</mi><mi>k</mi></mfrac></msqrt></mrow></mrow></math>" },
			/* 17 */{
					"T_p=2π√{l/g}",
					"<math alttext=\"T_p=2π√{l/g}\"><mrow><msub><mi>T</mi><mi>p</mi></msub><mo>=</mo><mrow><mrow><mn>2</mn><mi>π</mi></mrow><msqrt><mfrac><mi>l</mi><mi>g</mi></mfrac></msqrt></mrow></mrow></math>" },
			/* 18 */{
					"T=1/f",
					"<math alttext=\"T=1/f\"><mrow><mi>T</mi><mo>=</mo><mfrac><mn>1</mn><mi>f</mi></mfrac></mrow></math>" },
			/* 19 */{
					"F_G=-{Gm_1m_2}/{r^2}",
					"<math alttext=\"F_G=-{Gm_1m_2}/{r^2}\"><mrow><msub><mi>F</mi><mi>G</mi></msub><mo>=</mo><mrow><mo>−</mo><mfrac><mrow><mrow><mi>G</mi><msub><mi>m</mi><mn>1</mn></msub></mrow><msub><mi>m</mi><mn>2</mn></msub></mrow><msup><mi>r</mi><mn>2</mn></msup></mfrac></mrow></mrow></math>" },
			/* 20 */{
					"U_G=-{Gm_1m_2}/{r}",
					"<math alttext=\"U_G=-{Gm_1m_2}/{r}\"><mrow><msub><mi>U</mi><mi>G</mi></msub><mo>=</mo><mrow><mo>−</mo><mfrac><mrow><mrow><mi>G</mi><msub><mi>m</mi><mn>1</mn></msub></mrow><msub><mi>m</mi><mn>2</mn></msub></mrow><mi>r</mi></mfrac></mrow></mrow></math>" },

	/**/};

	private final String[][] VARIABLES = {
			// 0-variable 1-description
			/* 0 */{ "a", "Acceleration" },
			/* 1 */{ "F", "Force" },
			/* 2 */{ "f", "Frequency" },
			/* 3 */{ "h", "Height" },
			/* 4 */{ "J", "Impulse" },
			/* 5 */{ "K", "Kinetic Energy" },
			/* 6 */{ "k", "Spring Constant" },
			/* 7 */{ "l", "Length" },
			/* 8 */{ "m", "Mass" },
			/* 9 */{ "N", "Normal Force" },
			/* 10 */{ "P", "Power" },
			/* 11 */{ "p", "Momentum" },
			/* 12 */{ "r", "Radius" },
			/* 13 */{ "T", "period" },
			/* 14 */{ "t", "Time" },
			/* 15 */{ "U", "Potential Energy" },
			/* 16 */{ "ν", "Velocity" },
			/* 17 */{ "W", "Work Done On System" },
			/* 18 */{ "x", "Position" },
			/* 19 */{ "μ", "Coefficient Of Friction" },
			/* 20 */{ "θ", "Angle" },
			/* 21 */{ "τ", "Torque" }
	/**/};

	/**
	 * Unknown variables must be upper case. Lower case letters may be replaced by randomly generated numbers
	 */
	private final String[][] ALGEBRA = {
			/* 0 */{ "X=a+b+c+d", "<math alttext=\"X=a+b+c+d\"><mrow><mi>X</mi><mo>=</mo><mrow><mrow><mrow><mi>a</mi><mo>+</mo><mi>b</mi></mrow><mo>+</mo><mi>c</mi></mrow><mo>+</mo><mi>d</mi></mrow></mrow></math>" },
			/* 1 */{ "X=a*b*c*d", "<math alttext=\"X=a*b*c*d\"><mrow><mi>X</mi><mo>=</mo><mrow><mrow><mrow><mi>a</mi><mo>*</mo><mi>b</mi></mrow><mo>*</mo><mi>c</mi></mrow><mo>*</mo><mi>d</mi></mrow></mrow></math>" },
			/* 2 */{ "X=a+b*c", "<math alttext=\"X=a+b*c\"><mrow><mi>X</mi><mo>=</mo><mrow><mi>a</mi><mo>+</mo><mrow><mi>b</mi><mo>*</mo><mi>c</mi></mrow></mrow></mrow></math>" },
			/* 3 */{ "X=(a+b)*c", 
				"<math alttext=\"X=(a+b)*c\" id=\"0\">" +
					
					"<mfenced open=\"\" close=\"\" id=\"0.0\">" +
						"<mi id=\"0.0.0\">X</mi>" +
					"</mfenced>" +
					
					"<mo id=\"0.0.1\">=</mo>" +
					
					"<mfenced open=\"\" close=\"\" separators=\"+-\" id=\"0.0.2.0\">" +
						"<mi id=\"0.0.2.0.1.0\">a</mi>" +
						"<mfenced open=\"(\" close=\")\" separators=\"*\" id=\"0.0.2.0.9\">" +
							"<mi id=\"0.0.2.0.1.2\">b</mi>" +
							"<mfrac id=\"0.0.2.0.2.3\">" +
								"<mi id=\"0.0.2.0.2.0\">c</mi>" +
								"<mfrac id=\"0.0.2.0.2.3.9\">" +
									"<mi id=\"0.0.2.0.2.1\">c</mi>" +
									"<mi id=\"0.0.2.0.2.1.9\">p</mi>" +
								"</mfrac>" +
							"</mfrac>" +
							"</mfenced>" +
						"<mi id=\"0.0.2.0.1.0.9\">f</mi>" +
					"</mfenced>" +
					
					"</math>" },
			/* 4 */{ "X+a=b+c*e", "<math alttext=\"X+a=b+c*e\"><mrow><mrow><mi>X</mi><mo>+</mo><mi>a</mi></mrow><mo>=</mo><mrow><mi>b</mi><mo>+</mo><mrow><mi>c</mi><mo>*</mo><mi>e</mi></mrow></mrow></mrow></math>" },
			/* 5 */{ "a+b-X=c*d/e", "<math alttext=\"a+b-X=c*d/e\"><mrow><mrow><mrow><mi>a</mi><mo>+</mo><mi>b</mi></mrow><mo>−</mo><mi>X</mi></mrow><mo>=</mo><mrow><mi>c</mi><mo>*</mo><mfrac><mi>d</mi><mi>e</mi></mfrac></mrow></mrow></math>" },
			/* 6 */{ "a+X*b=(c+d)*(e+f)", "<math alttext=\"a+X*b=(c+d)*(e+f)\"><mrow><mrow><mi>a</mi><mo>+</mo><mrow><mi>X</mi><mo>*</mo><mi>b</mi></mrow></mrow><mo>=</mo><mrow><mrow><mo>(</mo><mrow><mi>c</mi><mo>+</mo><mi>d</mi></mrow><mo>)</mo></mrow><mo>*</mo><mrow><mo>(</mo><mrow><mi>e</mi><mo>+</mo><mi>f</mi></mrow><mo>)</mo></mrow></mrow></mrow></math>" },
			/* 7 */{ "a-X=c*d+e*f", "<math alttext=\"a-X=c*d+e*f\"><mrow><mrow><mi>a</mi><mo>−</mo><mi>X</mi></mrow><mo>=</mo><mrow><mrow><mi>c</mi><mo>*</mo><mi>d</mi></mrow><mo>+</mo><mrow><mi>e</mi><mo>*</mo><mi>f</mi></mrow></mrow></mrow></math>" },
			/* 8 */{ "a*X*b+c*d=e+f*g/h", "<math alttext=\"a*X*b+c*d=e+f*g/h\"><mrow><mrow><mrow><mrow><mi>a</mi><mo>*</mo><mi>X</mi></mrow><mo>*</mo><mi>b</mi></mrow><mo>+</mo><mrow><mi>c</mi><mo>*</mo><mi>d</mi></mrow></mrow><mo>=</mo><mrow><mi>e</mi><mo>+</mo><mrow><mi>f</mi><mo>*</mo><mfrac><mi>g</mi><mi>h</mi></mfrac></mrow></mrow></mrow></math>" },

	/**/};

	private enum V {
		// 0-variable 1-description
		/* 0 */a("Acceleration"),
		/* 1 */F("Force"),
		/* 2 */f("Frequency"),
		/* 3 */h("Height"),
		/* 4 */J("Impulse"),
		/* 5 */K("Kinetic Energy"),
		/* 6 */k("Spring Constant"),
		/* 7 */l("Length"),
		/* 8 */m("Mass"),
		/* 9 */N("Normal Force"),
		/* 10 */P("Power"),
		/* 11 */p("Momentum"),
		/* 12 */r("Radius"),
		/* 13 */T("period"),
		/* 14 */t("Time"),
		/* 15 */U("Potential Energy"),
		/* 16 */ν("Velocity"),
		/* 17 */W("Work on System"),
		/* 18 */x("Position"),
		/* 19 */μ("Coef. of Friction"),
		/* 20 */θ("Angle"),
		/* 21 */τ("Torque");

		private final short i;

		V(String name) {
			this.i = (short) this.ordinal();
		}
		/**/
	};

	private final String[][] QUANTITIES = {
			// 0-quantity 1-description
			/* 0 */{ "a", "Acceleration" },
			/* 1 */{ "F", "Force" },
			/* 2 */{ "f", "Frequency" },
			/* 3 */// { "h", "Height" },
			/* 4 */{ "J", "Impulse" },
			/* 5 */{ "K", "Energy" },
			/* 6 */{ "k", "Spring Constant" },
			/* 7 */{ "l", "Length" },
			/* 8 */{ "m", "Mass" },
			/* 9 */{ "N", "Normal Force" },
			/* 10 */{ "P", "Power" },
			/* 11 */{ "p", "Momentum" },
			/* 12 */{ "r", "Radius" },
			/* 13 */{ "T", "period" },
			/* 14 */{ "t", "Time" },
			/* 15 */{ "U", "Potential Energy" },
			/* 16 */{ "ν", "Velocity" },
			/* 17 */{ "W", "Work Done On System" },
			/* 18 */{ "x", "Position" },
			/* 19 */{ "μ", "Coefficient Of Friction" },
			/* 20 */{ "θ", "Angle" },
			/* 21 */{ "τ", "Torque" }
	/**/};
	private final String[][] UNITS = {
	/* 0 */{ "0", "?" },
	/* 1 */{ "1", "?" },
	/* 2 */{ "2", "?" }
	/**/};

	public final String[] functions = { "+", "-", "x", "÷" };

	// TODO mabe by enum just to see the variables, make into numbers for
	// deployment
	// association between {(0)equations, (1)variables}
	private final short[][] ASS_EQ_VAR = {

	/* 0 ν=ν_0 + at */{ 0, V.ν.i }, { 0, V.a.i }, { 0, V.t.i },
	/* 1 x=x_0 + ν_0t + 1/2at^2 */{ 1, V.x.i }, { 1, V.a.i }, { 1, V.ν.i },
			{ 1, V.t.i },
			/* 2 ν^2=ν_0^2 + 2a(x - x_0) */{ 2, V.ν.i }, { 2, V.a.i },
			{ 2, V.x.i },
			/* 3 ∑F=F_{net} = ma */{ 3, V.F.i }, { 3, V.m.i }, { 3, V.a.i },
			/* 4 F_{fric}≤μN */{ 4, V.F.i }, { 4, V.μ.i }, { 4, V.N.i },
			/* 5 a_c={ν^2}/r */{ 5, V.a.i }, { 5, V.ν.i }, { 5, V.r.i },
			/* 6 τ=rF sinθ */{ 6, V.τ.i }, { 6, V.r.i }, { 6, V.F.i },
			{ 6, V.θ.i },
			/* 7 p=mν */{ 7, V.p.i }, { 7, V.m.i }, { 7, V.ν.i },
			/* 8 J=F\\Δt = \\Δp */{ 8, V.J.i }, { 8, V.F.i }, { 8, V.t.i },
			{ 8, V.p.i },
			/* 9 K=1/2mν^2 */{ 9, V.K.i }, { 9, V.m.i }, { 9, V.ν.i },
			/* 10 \\U_g=mgh */{ 10, V.U.i }, { 10, V.m.i }, { 10, V.h.i },
			/* 11 W=F\\Δrcosθ */{ 11, V.W.i }, { 11, V.F.i }, { 11, V.r.i },
			{ 11, V.θ.i },
			/* 12 P_{avg}=W/Δt */{ 12, V.P.i }, { 12, V.W.i }, { 12, V.t.i },
			/* 13 P=Fνcosθ */{ 13, V.P.i }, { 13, V.F.i }, { 13, V.ν.i },
			{ 13, V.θ.i },
			/* 14 F_s=-kx */{ 14, V.F.i }, { 14, V.k.i }, { 14, V.x.i },
			/* 15 U_s=1/2kx^2 */{ 15, V.U.i }, { 15, V.k.i }, { 15, V.x.i },
			/* 16 T_s=2π√{m/k} */{ 16, V.T.i }, { 16, V.m.i }, { 16, V.k.i },
			/* 17 T_p=2π√{l/g} */{ 17, V.T.i }, { 17, V.l.i },
			/* 18 T=1/f */{ 18, V.T.i }, { 18, V.f.i },
			/* 19 F_G=-{Gm_1m_2}/{r^2} */{ 19, V.F.i }, { 19, V.m.i },
			{ 19, V.r.i },
			/* 20 U_G=-{Gm_1m_2}/{r} */{ 20, V.U.i }, { 20, V.m.i },
			{ 20, V.r.i },
	/**/};

	// association of (0)variables (1)quantities
	private final short[][] ASS_QUAN_VAR = {
	/**/{ 0, 0 },
	/**/{ 0, 0 },
	/**/{ 0, 0 } };

	// association of (0)quantities (1)units
	private final short[][] ASS_QUAN_UNIT = {
	/**/{ 0, 0 },
	/**/{ 0, 0 },
	/**/{ 0, 0 } };

	/**
	 * 
	 * @param type
	 *            Equation Database flag: EQUATION or VARIABLE or QUANTITY or
	 *            UNIT
	 * @return An array of all the elements in the database of the type
	 *         specified
	 */
	public final String[] getAll(byte[] attributeFlag) {
		String[] list = null;
		String[][] array = null;

		if (attributeFlag[0] == FLAG_EQUATION) {
			array = EQUATIONS;
		} else if (attributeFlag[0] == FLAG_VARIABLE) {
			array = VARIABLES;
		} else if (attributeFlag[0] == FLAG_QUANTITY) {
			array = QUANTITIES;
		} else if (attributeFlag[0] == FLAG_UNIT) {
			array = UNITS;
		} else if (attributeFlag[0] == FLAG_ALGEBRA) {
			array = ALGEBRA;
		}

		list = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			list[i] = array[i][attributeFlag[1]];
		}
		return list;
	}

	/**
	 * 
	 * @param attributeFlag
	 *            FLAG_EQUATION_DESCRIPTION
	 * @param element
	 *            - the element (eg. the equation, variable...) whose attribute
	 *            you're searching for
	 * @return
	 * @throws ElementNotFoundExeption
	 */
	public final String getAttribute(byte[] attributeFlag, String element)
			throws ElementNotFoundExeption {
		String[][] array = null;

		if (attributeFlag[0] == FLAG_EQUATION) {
			array = EQUATIONS;
		} else if (attributeFlag[0] == FLAG_VARIABLE) {
			array = VARIABLES;
		} else if (attributeFlag[0] == FLAG_ALGEBRA) {
			array = ALGEBRA;
		}

		short index;
		try {
			index = getElementIndex(attributeFlag[0], element);
		} catch (ElementNotFoundExeption e) {
			e.printStackTrace();
			throw new ElementNotFoundExeption("no such element: " + element);
		}
		String attribute = array[index][attributeFlag[1]];

		return attribute;
	}

	public String getAlgAttribute(byte[] attribute, String el)
			throws ElementNotFoundExeption {
		if (attribute == FLAG_ALGEBRA_NAME) {
			for (int i = 0; i < ALGEBRA.length; i++) {
				if (ALGEBRA[i][1].equals(el)) {
					return ALGEBRA[i][0];
				}
			}
		} else if (attribute == FLAG_ALGEBRA_EQUATION) {
			for (int i = 0; i < ALGEBRA.length; i++) {
				if (ALGEBRA[i][0].equals(el)) {
					return ALGEBRA[i][1];
				}
			}
		}
		throw new ElementNotFoundExeption();
	}

	/**
	 * Finds all the equations containing a given variable
	 * 
	 * @param variable
	 * @return String[] of equations
	 * @throws ElementNotFoundExeption
	 */
	public final String[] getEquationsByVariable(String variable)
			throws ElementNotFoundExeption {

		short indexOfVar;
		try {
			indexOfVar = getElementIndex(FLAG_VARIABLE, variable);
		} catch (ElementNotFoundExeption e) {
			e.printStackTrace();
			throw new ElementNotFoundExeption("no such variable: " + variable);
		}
		List<String> eqStrings = new LinkedList<String>();
		List<Short> eqIndexes = new LinkedList<Short>();

		for (int i = 0; i < ASS_EQ_VAR.length; i++) {
			if (ASS_EQ_VAR[i][1] == (indexOfVar)) {
				eqIndexes.add(ASS_EQ_VAR[i][0]);
			}
		}

		for (int i = 0; i < eqIndexes.size(); i++) {
			eqStrings.add(EQUATIONS[eqIndexes.get(i)][0]);
		}
		return eqStrings.toArray(new String[0]);

	}

	/**
	 * Finds all the variables in an equation
	 * 
	 * @param equation
	 * @return String[] of variables
	 * @throws ElementNotFoundExeption
	 */
	public final String[] getVariablesByEquation(String equation)
			throws ElementNotFoundExeption {
		short indexOfEq;
		try {
			indexOfEq = getElementIndex(FLAG_EQUATION, equation);
		} catch (ElementNotFoundExeption e) {
			e.printStackTrace();
			throw new ElementNotFoundExeption("no such equation:" + equation);
		}
		List<Short> varIndexes = new LinkedList<Short>();

		for (int i = 0; i < ASS_EQ_VAR.length; i++) {
			if (ASS_EQ_VAR[i][0] == (indexOfEq)) {
				varIndexes.add(ASS_EQ_VAR[i][1]);
			}
		}
		String[] varStrings = new String[varIndexes.size()];

		for (int i = 0; i < varIndexes.size(); i++) {
			varStrings[i] = VARIABLES[varIndexes.get(i)][0];
		}
		return varStrings;
	}

	/**
	 * Finds all the variables of a given quantity
	 * 
	 * @param quantity
	 * @return String[] of variables
	 * @throws ElementNotFoundExeption
	 */
	public final String[] getVariablesByQuantity(String quantity)
			throws ElementNotFoundExeption {

		short indexOfQuan;
		try {
			indexOfQuan = getElementIndex(FLAG_QUANTITY, quantity);
		} catch (ElementNotFoundExeption e) {
			e.printStackTrace();
			throw new ElementNotFoundExeption("no such quantity: " + quantity);
		}
		List<Short> varIndexes = new LinkedList<Short>();

		for (int i = 0; i < ASS_QUAN_VAR.length; i++) {
			if (ASS_QUAN_VAR[i][0] == (indexOfQuan)) {
				varIndexes.add(ASS_QUAN_VAR[i][0]);
			}
		}
		String[] varStrings = new String[varIndexes.size()];

		for (int i = 0; i < varIndexes.size(); i++) {
			varStrings[i] = QUANTITIES[varIndexes.get(i)][0];
		}
		return varStrings;
	}

	/**
	 * Finds all the units of a given quantity
	 * 
	 * @param quantity
	 * @return String[] of variables
	 * @throws Exception
	 */
	public final String[] getUnitsByQuantity(String quantity)
			throws ElementNotFoundExeption {

		short indexOfQuan;
		try {
			indexOfQuan = getElementIndex(FLAG_QUANTITY, quantity);
		} catch (ElementNotFoundExeption e) {
			e.printStackTrace();
			throw new ElementNotFoundExeption("no such quantity:" + quantity);
		}
		List<Short> quIndexes = new LinkedList<Short>();

		for (int i = 0; i < ASS_QUAN_UNIT.length; i++) {
			if (ASS_QUAN_UNIT[i][0] == (indexOfQuan)) {
				quIndexes.add(ASS_QUAN_UNIT[i][1]);
			}
		}
		String[] quStrings = new String[quIndexes.size()];

		for (int i = 0; i < quIndexes.size(); i++) {
			quStrings[i] = QUANTITIES[quIndexes.get(i)][0];
		}
		return quStrings;
	}

	/**
	 * Finds all (and only) the equations containing <b> all </b> given
	 * variables
	 * 
	 * @param varStringsSet
	 * @return String[] of equations
	 */
	public final String[] getEquationsByVariables(Set<String> varStringsSet) {
		/*
		 * This method will loop through the array association of Equations and
		 * Variables for every variable(EQUATION_VARIABLE) in the set(vars).
		 * When it encounters the same variable, it adds the associated equation
		 * to the map or increases the count(map value).
		 */
		List<String> eqStrings = new LinkedList<String>();

		// map of equations and count of associated variables in the set (vars)
		HashMap<Short, Byte> map = new HashMap<Short, Byte>();

		Iterator<String> varsIterator = varStringsSet.iterator();

		outerLoop: while (varsIterator.hasNext()) {
			short varCurrentIndex;
			try {
				varCurrentIndex = getElementIndex(FLAG_VARIABLE,
						varsIterator.next());
			} catch (ElementNotFoundExeption e) {
				e.printStackTrace();
				continue outerLoop;
			}
			for (int i = 0; i < ASS_EQ_VAR.length; i++) {
				if (ASS_EQ_VAR[i][1] == varCurrentIndex) {
					short currentEqIndex = ASS_EQ_VAR[i][0];
					if (map.containsKey(currentEqIndex)) {
						Byte count = map.get(currentEqIndex);
						count++;
						map.put(currentEqIndex, count);
						if (varStringsSet.size() == count) {
							eqStrings.add(EQUATIONS[currentEqIndex][0]);
						}
					} else {
						map.put(currentEqIndex, (byte) 1);
						if (varStringsSet.size() == 1) {
							eqStrings.add(EQUATIONS[currentEqIndex][0]);
						}
					}
				}
			}
		}

		return eqStrings.toArray(new String[0]);

	}

	/**
	 * Finds the index of the given element in the array of it's type
	 * 
	 * @param typeOfElementFlag
	 *            : FLAG_EQUATION, FLAG_VARIABLE, FLAG_QUANTITY or FLAG_UNIT
	 * @param element
	 *            - the equation, variable... to search
	 * @return index of the element in the array
	 *         <p>
	 *         -1 if it's not found
	 *         </p>
	 * @throws ElementNotFoundExeption
	 */
	private final short getElementIndex(byte typeOfElementFlag, String element)
			throws ElementNotFoundExeption {
		String[][] array = null;
		short index = -1;

		if (typeOfElementFlag == FLAG_EQUATION) {
			array = EQUATIONS;
		} else if (typeOfElementFlag == FLAG_VARIABLE) {
			array = VARIABLES;
		} else if (typeOfElementFlag == FLAG_QUANTITY) {
			array = QUANTITIES;
		} else if (typeOfElementFlag == FLAG_UNIT) {
			array = UNITS;
		} else if (typeOfElementFlag == FLAG_ALGEBRA) {
			array = ALGEBRA;
		}

		for (short i = 0; i < array.length; i++) {
			if (element.equals(array[i][0])) {
				return (index = i);
			}
		}
		if (index == -1) {
			throw new ElementNotFoundExeption("element not found");
		}
		return -1;
	}

}

/**
 * 
 * Exception to be thrown when an element is not found in the database
 * 
 */
class ElementNotFoundExeption extends Exception {
	ElementNotFoundExeption() {
		super();
	}

	ElementNotFoundExeption(String s) {
		super(s);
	}

	private static final long serialVersionUID = 1137715992680750870L;
}
