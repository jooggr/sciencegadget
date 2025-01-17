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
package com.sciencegadgets.client.equationbrowser;

public enum AlgebraPracticeProblems {
	AdditionOPeasyCP(
			Subject.Arithmetic,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Sum\"><div class=\"sg-in-sum Number\">?</div><div class=\"sg-in-sum Operation\">+</div><div class=\"sg-in-sum Number\">?</div><div class=\"sg-in-sum Operation\">+</div><div class=\"sg-in-sum Number\">?</div><div class=\"sg-in-sum Operation\">+</div><div class=\"sg-in-sum Number\">?</div></div></div>", //
			"<sget:equation id=\"ML55\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML56\">a</sget:var><sget:op id=\"ML57\">=</sget:op><sget:sum id=\"ML58\"><sget:num unit=\"\" randomness=\"N_1_9_0\" id=\"ML64\">?</sget:num><sget:op id=\"ML60\">+</sget:op><sget:num id=\"ML65\" unit=\"\" randomness=\"N_1_9_0\">?</sget:num><sget:op id=\"ML62\">+</sget:op><sget:num id=\"ML66\" unit=\"\" randomness=\"N_1_9_0\">?</sget:num><sget:op id=\"ML68\">+</sget:op><sget:num id=\"ML69\" unit=\"\" randomness=\"N_1_9_0\">?</sget:num></sget:sum></sget:equation>",//
			null), //
	AdditionOPmediumCP(
			Subject.Arithmetic,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Sum\"><div class=\"sg-in-sum Number\">?</div><div class=\"sg-in-sum Operation\">+</div><div class=\"sg-in-sum Number\">?</div><div class=\"sg-in-sum Operation\">+</div><div class=\"sg-in-sum Number\">?</div></div></div>", //
			"<sget:equation id=\"ML40\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML41\">a</sget:var><sget:op id=\"ML42\">=</sget:op><sget:sum id=\"ML57\"><sget:num unit=\"\" randomness=\"N_9_100_0\" id=\"ML56\">?</sget:num><sget:op id=\"ML58\">+</sget:op><sget:num id=\"ML60\" unit=\"\" randomness=\"N_9_100_0\">?</sget:num><sget:op id=\"ML62\">+</sget:op><sget:num id=\"ML63\" unit=\"\" randomness=\"N_9_100_0\">?</sget:num></sget:sum></sget:equation>",//
			null), //
	AdditionOPhardCP(
			Subject.Arithmetic,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Sum\"><div class=\"sg-in-sum Number\">?</div><div class=\"sg-in-sum Operation\">+</div><div class=\"sg-in-sum Number\">?</div></div></div>", //
			"<sget:equation id=\"ML40\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML41\">a</sget:var><sget:op id=\"ML42\">=</sget:op><sget:sum id=\"ML57\"><sget:num unit=\"\" randomness=\"N_100_100000_0\" id=\"ML64\">?</sget:num><sget:op id=\"ML58\">+</sget:op><sget:num id=\"ML65\" unit=\"\" randomness=\"N_100_100000_0\">?</sget:num></sget:sum></sget:equation>",//
			null), //
	SubtractionOPresultCOL_positiveCP(
			Subject.Arithmetic,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Sum\"><div class=\"sg-in-sum Number\">?</div><div class=\"sg-in-sum Operation\">-</div><div class=\"sg-in-sum Number\">?</div></div></div>", //
			"<sget:equation id=\"ML40\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML41\">a</sget:var><sget:op id=\"ML42\">=</sget:op><sget:sum id=\"ML43\"><sget:num unit=\"\" randomness=\"N_50_100_0\" id=\"ML49\">?</sget:num><sget:op id=\"ML45\">-</sget:op><sget:num unit=\"\" randomness=\"N_1_49_0\" id=\"ML50\">?</sget:num></sget:sum></sget:equation>",//
			null), //
	SubtractionOPresultCOL_negativeCP(
			Subject.Arithmetic,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Sum\"><div class=\"sg-in-sum Number\">?</div><div class=\"sg-in-sum Operation\">-</div><div class=\"sg-in-sum Number\">?</div></div></div>",//
			"<sget:equation id=\"ML40\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML41\">a</sget:var><sget:op id=\"ML42\">=</sget:op><sget:sum id=\"ML50\"><sget:num unit=\"\" randomness=\"N_1_49_0\" id=\"ML53\">?</sget:num><sget:op id=\"ML51\">-</sget:op><sget:num unit=\"\" randomness=\"N_50_100_0\" id=\"ML54\">?</sget:num></sget:sum></sget:equation>", //
			null), //
	AdditionOPnegativesCP(
			Subject.Arithmetic,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Sum\"><div class=\"sg-in-sum Number\">?</div><div class=\"sg-in-sum Operation\">+</div><div class=\"sg-in-sum Number\">?</div></div></div>",//
			"<sget:equation id=\"ML40\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML41\">a</sget:var><sget:op id=\"ML42\">=</sget:op><sget:sum id=\"ML50\"><sget:num unit=\"\" randomness=\"S_1_50_0\" id=\"ML55\">?</sget:num><sget:op id=\"ML51\">+</sget:op><sget:num id=\"ML56\" unit=\"\" randomness=\"A_1_50_0\">?</sget:num></sget:sum></sget:equation>",//
			null), //
	SubtractionOPnegativesCP(
			Subject.Arithmetic,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Sum\"><div class=\"sg-in-sum Number\">?</div><div class=\"sg-in-sum Operation\">-</div><div class=\"sg-in-sum Number\">?</div></div></div>",//
			"<sget:equation id=\"ML40\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML41\">a</sget:var><sget:op id=\"ML42\">=</sget:op><sget:sum id=\"ML50\"><sget:num unit=\"\" randomness=\"S_1_50_0\" id=\"ML55\">?</sget:num><sget:op id=\"ML51\">-</sget:op><sget:num id=\"ML56\" unit=\"\" randomness=\"A_1_50_0\">?</sget:num></sget:sum></sget:equation>",//
			null), //
	Multiplication(
			Subject.Arithmetic,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Term\"><div class=\"sg-in-term Number\">?</div><div class=\"sg-in-term Operation\">·</div><div class=\"sg-in-term Number\">?</div></div></div>", //
			"<sget:equation id=\"ML40\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML41\">a</sget:var><sget:op id=\"ML42\">=</sget:op><sget:term id=\"ML59\"><sget:num unit=\"\" randomness=\"N_2_10_0\" id=\"ML58\">?</sget:num><sget:op id=\"ML60\">·</sget:op><sget:num id=\"ML62\" unit=\"\" randomness=\"N_2_10_0\">?</sget:num></sget:term></sget:equation>",//
			null), //
	MultiplicationOPnegativesCP(
			Subject.Arithmetic,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Term\"><div class=\"sg-in-term Number\">?</div><div class=\"sg-in-term Operation\">·</div><div class=\"sg-in-term Number\">?</div></div></div>",//
			"<sget:equation id=\"ML40\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML41\">a</sget:var><sget:op id=\"ML42\">=</sget:op><sget:term id=\"ML59\"><sget:num unit=\"\" randomness=\"S_2_10_0\" id=\"ML58\">?</sget:num><sget:op id=\"ML60\">·</sget:op><sget:num id=\"ML62\" unit=\"\" randomness=\"S_2_10_0\">?</sget:num></sget:term></sget:equation>",//
			null), //
	Division(
			Subject.Arithmetic,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Fraction\"><div class=\"sg-in-fraction-numerator Number\">?</div><div class=\"sg-in-fraction-denominator Number\">?</div></div></div>",//
			"<sget:equation id=\"ML40\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML41\">a</sget:var><sget:op id=\"ML42\">=</sget:op><sget:frac id=\"ML63\"><sget:num unit=\"\" randomness=\"N_10_100_0\" id=\"ML68\">?</sget:num><sget:num id=\"ML65\" unit=\"\" randomness=\"N_2_10_0\">?</sget:num></sget:frac></sget:equation>",//
			null), //
	DivisionOPnegativesCP(
			Subject.Arithmetic,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Fraction\"><div class=\"sg-in-fraction-numerator Number\">?</div><div class=\"sg-in-fraction-denominator Number\">?</div></div></div>", //
			"<sget:equation id=\"ML40\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML41\">a</sget:var><sget:op id=\"ML42\">=</sget:op><sget:frac id=\"ML63\"><sget:num unit=\"\" randomness=\"S_10_100_0\" id=\"ML68\">?</sget:num><sget:num id=\"ML65\" unit=\"\" randomness=\"S_2_10_0\">?</sget:num></sget:frac></sget:equation>",//
			null), //
	Cancellation(
			Subject.Arithmetic,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Fraction\"><div class=\"sg-in-fraction-numerator Term\"><div class=\"sg-in-term Variable\">b</div><div class=\"sg-in-term Operation\">·</div><div class=\"sg-in-term Variable\">c</div></div><div class=\"sg-in-fraction-denominator Term\"><div class=\"sg-in-term Variable\">b</div><div class=\"sg-in-term Operation\">·</div><div class=\"sg-in-term Variable\">c</div></div></div></div>",//
			"<sget:equation id=\"ML40\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML41\">a</sget:var><sget:op id=\"ML42\">=</sget:op><sget:frac id=\"ML63\"><sget:term id=\"ML70\"><sget:var unit=\"\" id=\"ML69\">b</sget:var><sget:op id=\"ML71\">·</sget:op><sget:var unit=\"\" id=\"ML73\">c</sget:var></sget:term><sget:term id=\"ML75\"><sget:var unit=\"\" id=\"ML74\">b</sget:var><sget:op id=\"ML76\">·</sget:op><sget:var unit=\"\" id=\"ML78\">c</sget:var></sget:term></sget:frac></sget:equation>",//
			null), //
	Addition_with_Zero(
			Subject.Special_Numbers,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Sum\"><div class=\"sg-in-sum Variable\">b</div><div class=\"sg-in-sum Operation\">+</div><div class=\"sg-in-sum Number\">0</div></div></div>",
			"<sget:equation id=\"ML40\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML41\">a</sget:var><sget:op id=\"ML42\">=</sget:op><sget:sum id=\"ML131\"><sget:var unit=\"\" id=\"ML106\">b</sget:var><sget:op id=\"ML132\">+</sget:op><sget:num unit=\"\" value=\"0\" id=\"ML134\">0</sget:num></sget:sum></sget:equation>",
			"<sget:equation id=\"ML40\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML41\">a</sget:var><sget:op id=\"ML42\">=</sget:op><sget:var unit=\"\" id=\"ML106\">b</sget:var></sget:equation>"), //
	Multiplication_with_Zero(
			Subject.Special_Numbers,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Term\"><div class=\"sg-in-term Variable\">b</div><div class=\"sg-in-term Operation\">·</div><div class=\"sg-in-term Number\">0</div></div></div>",
			"<sget:equation id=\"ML40\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML41\">a</sget:var><sget:op id=\"ML42\">=</sget:op><sget:term id=\"ML135\"><sget:var unit=\"\" id=\"ML106\">b</sget:var><sget:op id=\"ML136\">·</sget:op><sget:num unit=\"\" value=\"0\" id=\"ML138\">0</sget:num></sget:term></sget:equation>",
			null), //
	Multiplication_with_One(
			Subject.Special_Numbers,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Term\"><div class=\"sg-in-term Variable\">b</div><div class=\"sg-in-term Operation\">·</div><div class=\"sg-in-term Number\">1</div></div></div>",
			"<sget:equation id=\"ML140\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML141\">a</sget:var><sget:op id=\"ML142\">=</sget:op><sget:term id=\"ML143\"><sget:var id=\"ML144\" unit=\"\">b</sget:var><sget:op id=\"ML145\">·</sget:op><sget:num id=\"ML146\" unit=\"\" value=\"1\">1</sget:num></sget:term></sget:equation>",
			"<sget:equation id=\"ML40\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML41\">a</sget:var><sget:op id=\"ML42\">=</sget:op><sget:var unit=\"\" id=\"ML106\">b</sget:var></sget:equation>"), //
	Multiplication_with_Negative_One(
			Subject.Special_Numbers,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Term\"><div class=\"sg-in-term Number\"><div class=\"sg-fenced\">-1</div></div><div class=\"sg-in-term Operation\">·</div><div class=\"sg-in-term Variable\">b</div></div></div>",
			"<sget:equation id=\"ML187\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML188\">a</sget:var><sget:op id=\"ML189\">=</sget:op><sget:term id=\"ML190\"><sget:num id=\"ML191\" unit=\"\" value=\"-1\">-1</sget:num><sget:op id=\"ML192\">·</sget:op><sget:var id=\"ML193\" unit=\"\">b</sget:var></sget:term></sget:equation>",
			"<sget:equation id=\"ML172\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML173\">a</sget:var><sget:op id=\"ML174\">=</sget:op><sget:var unit=\"\" id=\"ML186\">-b</sget:var></sget:equation>"), //
	Addition_of_Fractions(
			Subject.Fractions,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Sum\"><div class=\"sg-in-sum Fraction\"><div class=\"sg-in-fraction-numerator Variable\">b</div><div class=\"sg-in-fraction-denominator Variable\">d</div></div><div class=\"sg-in-sum Operation\">+</div><div class=\"sg-in-sum Fraction\"><div class=\"sg-in-fraction-numerator Variable\">c</div><div class=\"sg-in-fraction-denominator Variable\">d</div></div></div></div>",
			"<sget:equation id=\"ML271\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML272\">a</sget:var><sget:op id=\"ML273\">=</sget:op><sget:sum id=\"ML274\"><sget:frac id=\"ML275\"><sget:var id=\"ML276\" unit=\"\">b</sget:var><sget:var id=\"ML277\" unit=\"\">d</sget:var></sget:frac><sget:op id=\"ML278\">+</sget:op><sget:frac id=\"ML279\"><sget:var id=\"ML280\" unit=\"\">c</sget:var><sget:var id=\"ML281\" unit=\"\">d</sget:var></sget:frac></sget:sum></sget:equation>",
			"<sget:equation id=\"ML172\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML173\">a</sget:var><sget:op id=\"ML174\">=</sget:op><sget:frac id=\"ML203\"><sget:sum id=\"ML311\"><sget:var unit=\"\" id=\"ML196\">b</sget:var><sget:op id=\"ML201\">+</sget:op><sget:var unit=\"\" id=\"ML269\">c</sget:var></sget:sum><sget:var unit=\"\" id=\"ML270\">d</sget:var></sget:frac></sget:equation>"), //
	Common_Denominator(
			Subject.Fractions,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Sum\"><div class=\"sg-in-sum Fraction\"><div class=\"sg-in-fraction-numerator Variable\">b</div><div class=\"sg-in-fraction-denominator Variable\">d</div></div><div class=\"sg-in-sum Operation\">+</div><div class=\"sg-in-sum Variable\">c</div></div></div>",
			"<sget:equation id=\"ML467\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML468\">a</sget:var><sget:op id=\"ML469\">=</sget:op><sget:sum id=\"ML470\"><sget:frac id=\"ML471\"><sget:var id=\"ML472\" unit=\"\">b</sget:var><sget:var id=\"ML473\" unit=\"\">d</sget:var></sget:frac><sget:op id=\"ML474\">+</sget:op><sget:var id=\"ML475\" unit=\"\">c</sget:var></sget:sum></sget:equation>",
			"<sget:equation id=\"ML172\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML173\">a</sget:var><sget:op id=\"ML174\">=</sget:op><sget:frac id=\"ML479\"><sget:sum id=\"ML519\"><sget:var unit=\"\" id=\"ML407\">b</sget:var><sget:op id=\"ML412\">+</sget:op><sget:term id=\"ML476\"><sget:var unit=\"\" id=\"ML414\">c</sget:var><sget:op id=\"ML477\">·</sget:op><sget:var id=\"ML478\" unit=\"\">d</sget:var></sget:term></sget:sum><sget:var id=\"ML480\" unit=\"\">d</sget:var></sget:frac></sget:equation>"), //
	Multiplication_of_Fractions(
			Subject.Fractions,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Term\"><div class=\"sg-in-term Fraction\"><div class=\"sg-in-fraction-numerator Variable\">b</div><div class=\"sg-in-fraction-denominator Variable\">d</div></div><div class=\"sg-in-term Operation\">·</div><div class=\"sg-in-term Fraction\"><div class=\"sg-in-fraction-numerator Variable\">c</div><div class=\"sg-in-fraction-denominator Variable\">e</div></div></div></div>",
			"<sget:equation id=\"ML536\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML537\">a</sget:var><sget:op id=\"ML538\">=</sget:op><sget:term id=\"ML539\"><sget:frac id=\"ML540\"><sget:var id=\"ML541\" unit=\"\">b</sget:var><sget:var id=\"ML542\" unit=\"\">d</sget:var></sget:frac><sget:op id=\"ML543\">·</sget:op><sget:frac id=\"ML544\"><sget:var id=\"ML545\" unit=\"\">c</sget:var><sget:var id=\"ML546\" unit=\"\">e</sget:var></sget:frac></sget:term></sget:equation>",
			"<sget:equation id=\"ML172\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML173\">a</sget:var><sget:op id=\"ML174\">=</sget:op><sget:frac id=\"ML533\"><sget:term id=\"ML547\"><sget:var unit=\"\" id=\"ML521\">b</sget:var><sget:op id=\"ML530\">·</sget:op><sget:var unit=\"\" id=\"ML532\">c</sget:var></sget:term><sget:term id=\"ML548\"><sget:var unit=\"\" id=\"ML524\">d</sget:var><sget:op id=\"ML549\">·</sget:op><sget:var unit=\"\" id=\"ML535\">e</sget:var></sget:term></sget:frac></sget:equation>"), //
	Multiplication_with_Fraction(
			Subject.Fractions,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Term\"><div class=\"sg-in-term Fraction\"><div class=\"sg-in-fraction-numerator Variable\">b</div><div class=\"sg-in-fraction-denominator Variable\">d</div></div><div class=\"sg-in-term Operation\">·</div><div class=\"sg-in-term Variable\">c</div></div></div>",
			"<sget:equation id=\"ML554\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML555\">a</sget:var><sget:op id=\"ML556\">=</sget:op><sget:term id=\"ML557\"><sget:frac id=\"ML558\"><sget:var id=\"ML559\" unit=\"\">b</sget:var><sget:var id=\"ML560\" unit=\"\">d</sget:var></sget:frac><sget:op id=\"ML561\">·</sget:op><sget:var id=\"ML562\" unit=\"\">c</sget:var></sget:term></sget:equation>",
			"<sget:equation id=\"ML172\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML173\">a</sget:var><sget:op id=\"ML174\">=</sget:op><sget:frac id=\"ML533\"><sget:term id=\"ML563\"><sget:var unit=\"\" id=\"ML521\">b</sget:var><sget:op id=\"ML551\">·</sget:op><sget:var unit=\"\" id=\"ML553\">c</sget:var></sget:term><sget:var unit=\"\" id=\"ML524\">d</sget:var></sget:frac></sget:equation>"), //
	Distribution(
			Subject.Distribution_and_Factoring,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Term\"><div class=\"sg-in-term Variable\">b</div><div class=\"sg-in-term Operation\">·</div><div class=\"sg-in-term Sum\"><div class=\"sg-fenced\"><div class=\"sg-in-sum Variable\">c</div><div class=\"sg-in-sum Operation\">+</div><div class=\"sg-in-sum Variable\">d</div></div></div></div></div>",
			"<sget:equation id=\"ML574\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML575\">a</sget:var><sget:op id=\"ML576\">=</sget:op><sget:term id=\"ML577\"><sget:var id=\"ML578\" unit=\"\">b</sget:var><sget:op id=\"ML579\">·</sget:op><sget:sum id=\"ML580\"><sget:var id=\"ML581\" unit=\"\">c</sget:var><sget:op id=\"ML582\">+</sget:op><sget:var id=\"ML583\" unit=\"\">d</sget:var></sget:sum></sget:term></sget:equation>",
			"<sget:equation id=\"ML172\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML173\">a</sget:var><sget:op id=\"ML174\">=</sget:op><sget:sum id=\"ML569\"><sget:term id=\"ML584\"><sget:var id=\"ML586\" unit=\"\">b</sget:var><sget:op id=\"ML585\">·</sget:op><sget:var unit=\"\" id=\"ML572\">c</sget:var></sget:term><sget:op id=\"ML570\">+</sget:op><sget:term id=\"ML587\"><sget:var id=\"ML589\" unit=\"\">b</sget:var><sget:op id=\"ML588\">·</sget:op><sget:var unit=\"\" id=\"ML573\">d</sget:var></sget:term></sget:sum></sget:equation>"), //
	Combining_Like_Terms(
			Subject.Distribution_and_Factoring,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Sum\"><div class=\"sg-in-sum Term\"><div class=\"sg-in-term Number\">?</div><div class=\"sg-in-term Operation\">·</div><div class=\"sg-in-term Variable\">b</div></div><div class=\"sg-in-sum Operation\">+</div><div class=\"sg-in-sum Term\"><div class=\"sg-in-term Number\">?</div><div class=\"sg-in-term Operation\">·</div><div class=\"sg-in-term Variable\">b</div></div></div></div>",
			"<sget:equation id=\"ML38\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML39\" unit=\"\">a</sget:var><sget:op id=\"ML40\">=</sget:op><sget:sum id=\"ML41\"><sget:term id=\"ML42\"><sget:num id=\"ML43\" unit=\"\" randomness=\"S_2_10_0\">?</sget:num><sget:op id=\"ML44\">·</sget:op><sget:var id=\"ML45\" unit=\"\">b</sget:var></sget:term><sget:op id=\"ML46\">+</sget:op><sget:term id=\"ML47\"><sget:num id=\"ML48\" unit=\"\" randomness=\"S_2_10_0\">?</sget:num><sget:op id=\"ML49\">·</sget:op><sget:var id=\"ML50\" unit=\"\">b</sget:var></sget:term></sget:sum></sget:equation>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var unit=\"\" id=\"ML0\">a</sget:var><sget:op id=\"ML34\">=</sget:op><sget:term id=\"ML22\"><sget:num unit=\"\" randomness=\"S_2_10_0\" id=\"ML26\">?</sget:num><sget:op id=\"ML25\">·</sget:op><sget:var unit=\"\" id=\"ML1\">b</sget:var></sget:term></sget:equation>"), //
	Factoring(
			Subject.Distribution_and_Factoring,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Sum\"><div class=\"sg-in-sum Term\"><div class=\"sg-in-term Variable\">b</div><div class=\"sg-in-term Operation\">·</div><div class=\"sg-in-term Variable\">c</div></div><div class=\"sg-in-sum Operation\">+</div><div class=\"sg-in-sum Term\"><div class=\"sg-in-term Variable\">b</div><div class=\"sg-in-term Operation\">·</div><div class=\"sg-in-term Variable\">d</div></div></div></div>",
			"<sget:equation id=\"ML590\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML591\">a</sget:var><sget:op id=\"ML592\">=</sget:op><sget:sum id=\"ML593\"><sget:term id=\"ML594\"><sget:var id=\"ML595\" unit=\"\">b</sget:var><sget:op id=\"ML596\">·</sget:op><sget:var id=\"ML597\" unit=\"\">c</sget:var></sget:term><sget:op id=\"ML598\">+</sget:op><sget:term id=\"ML599\"><sget:var id=\"ML600\" unit=\"\">b</sget:var><sget:op id=\"ML601\">·</sget:op><sget:var id=\"ML602\" unit=\"\">d</sget:var></sget:term></sget:sum></sget:equation>",
			"<sget:equation id=\"ML172\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML173\">a</sget:var><sget:op id=\"ML174\">=</sget:op><sget:term id=\"ML603\"><sget:var id=\"ML604\" unit=\"\">b</sget:var><sget:op id=\"ML605\">·</sget:op><sget:sum id=\"ML606\"><sget:var unit=\"\" id=\"ML572\">c</sget:var><sget:op id=\"ML570\">+</sget:op><sget:var unit=\"\" id=\"ML573\">d</sget:var></sget:sum></sget:term></sget:equation>"), //
	Expand(
			Subject.Exponentials,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Exponential\"><div class=\"sg-in-exponential-base Variable\">b</div><div class=\"sg-in-exponential-exponent Number\">2</div></div></div>",
			"<sget:equation id=\"ML6\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML7\" unit=\"\">a</sget:var><sget:op id=\"ML8\">=</sget:op><sget:exp id=\"ML9\"><sget:var id=\"ML10\" unit=\"\">b</sget:var><sget:num id=\"ML11\" unit=\"\" value=\"2\">2</sget:num></sget:exp></sget:equation>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var unit=\"\" id=\"ML0\">a</sget:var><sget:op id=\"ML34\">=</sget:op><sget:term id=\"ML28\"><sget:var id=\"ML30\" unit=\"\">b</sget:var><sget:op id=\"ML29\">·</sget:op><sget:var unit=\"\" id=\"ML1\">b</sget:var></sget:term></sget:equation>"), //
	Exponent_of_1(
			Subject.Exponentials,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Exponential\"><div class=\"sg-in-exponential-base Variable\">b</div><div class=\"sg-in-exponential-exponent Number\">1</div></div></div>",
			"<sget:equation id=\"ML63\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML64\" unit=\"\">a</sget:var><sget:op id=\"ML65\">=</sget:op><sget:exp id=\"ML66\"><sget:var id=\"ML67\" unit=\"\">b</sget:var><sget:num id=\"ML68\" unit=\"\" value=\"1\">1</sget:num></sget:exp></sget:equation>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var unit=\"\" id=\"ML31\">a</sget:var><sget:op id=\"ML34\">=</sget:op><sget:var unit=\"\" id=\"ML59\">b</sget:var></sget:equation>"), //
	Exponent_of_0(
			Subject.Exponentials,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Exponential\"><div class=\"sg-in-exponential-base Variable\">b</div><div class=\"sg-in-exponential-exponent Number\">0</div></div></div>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var unit=\"\" id=\"ML31\">a</sget:var><sget:op id=\"ML34\">=</sget:op><sget:exp id=\"ML35\"><sget:var unit=\"\" id=\"ML33\">b</sget:var><sget:num unit=\"\" value=\"0\" id=\"ML37\">0</sget:num></sget:exp></sget:equation>",
			null), //
	Evaluate_Exponential(
			Subject.Exponentials,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Exponential\"><div class=\"sg-in-exponential-base Number\">?</div><div class=\"sg-in-exponential-exponent Number\">2</div></div></div>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var unit=\"\" id=\"ML31\">a</sget:var><sget:op id=\"ML34\">=</sget:op><sget:exp id=\"ML83\"><sget:num unit=\"\" randomness=\"S_2_10_0\" id=\"ML82\">?</sget:num><sget:num unit=\"\" value=\"2\" id=\"ML85\">2</sget:num></sget:exp></sget:equation>",
			null), //
	Multiply_Exponentials_with_Similar_Bases(
			Subject.Exponentials,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Term\"><div class=\"sg-in-term Exponential\"><div class=\"sg-in-exponential-base Variable\">b</div><div class=\"sg-in-exponential-exponent Number\">?</div></div><div class=\"sg-in-term Operation\">·</div><div class=\"sg-in-term Exponential\"><div class=\"sg-in-exponential-base Variable\">b</div><div class=\"sg-in-exponential-exponent Number\">?</div></div></div></div>",
			"<sget:equation id=\"ML11\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML12\" unit=\"\">a</sget:var><sget:op id=\"ML13\">=</sget:op><sget:term id=\"ML14\"><sget:exp id=\"ML15\"><sget:var id=\"ML16\" unit=\"\">b</sget:var><sget:num id=\"ML17\" unit=\"\" randomness=\"S_2_10_0\">?</sget:num></sget:exp><sget:op id=\"ML18\">·</sget:op><sget:exp id=\"ML19\"><sget:var id=\"ML20\" unit=\"\">b</sget:var><sget:num id=\"ML21\" unit=\"\" randomness=\"S_2_10_0\">?</sget:num></sget:exp></sget:term></sget:equation>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var unit=\"\" id=\"ML0\">a</sget:var><sget:op id=\"ML34\">=</sget:op><sget:exp id=\"ML2\"><sget:var unit=\"\" id=\"ML1\">b</sget:var><sget:num unit=\"\" randomness=\"S_2_10_0\" id=\"ML4\">?</sget:num></sget:exp></sget:equation>"), //
	Divide_Exponentials_with_Similar_Bases(
			Subject.Exponentials,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Fraction\"><div class=\"sg-in-fraction-numerator Exponential\"><div class=\"sg-in-exponential-base Variable\">b</div><div class=\"sg-in-exponential-exponent Number\">?</div></div><div class=\"sg-in-fraction-denominator Exponential\"><div class=\"sg-in-exponential-base Variable\">b</div><div class=\"sg-in-exponential-exponent Number\">?</div></div></div></div>",
			"<sget:equation id=\"ML10\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML11\" unit=\"\">a</sget:var><sget:op id=\"ML12\">=</sget:op><sget:frac id=\"ML13\"><sget:exp id=\"ML14\"><sget:var id=\"ML15\" unit=\"\">b</sget:var><sget:num id=\"ML16\" unit=\"\" randomness=\"S_2_10_0\">?</sget:num></sget:exp><sget:exp id=\"ML17\"><sget:var id=\"ML18\" unit=\"\">b</sget:var><sget:num id=\"ML19\" unit=\"\" randomness=\"S_2_10_0\">?</sget:num></sget:exp></sget:frac></sget:equation>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var unit=\"\" id=\"ML0\">a</sget:var><sget:op id=\"ML34\">=</sget:op><sget:exp id=\"ML2\"><sget:var unit=\"\" id=\"ML1\">b</sget:var><sget:num unit=\"\" randomness=\"S_2_10_0\" id=\"ML4\">?</sget:num></sget:exp></sget:equation>"), //
	Exponential_with_Exponent(
			Subject.Exponentials,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Exponential\"><div class=\"sg-in-exponential-base Exponential\"><div class=\"sg-fenced\"><div class=\"sg-in-exponential-base Variable\">b</div><div class=\"sg-in-exponential-exponent Number\">?</div></div></div><div class=\"sg-in-exponential-exponent Number\">?</div></div></div>",
			"<sget:equation id=\"ML23\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML24\" unit=\"\">a</sget:var><sget:op id=\"ML25\">=</sget:op><sget:exp id=\"ML26\"><sget:exp id=\"ML27\"><sget:var id=\"ML28\" unit=\"\">b</sget:var><sget:num id=\"ML29\" unit=\"\" randomness=\"S_2_10_0\">?</sget:num></sget:exp><sget:num id=\"ML30\" unit=\"\" randomness=\"S_2_10_0\">?</sget:num></sget:exp></sget:equation>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var unit=\"\" id=\"ML0\">a</sget:var><sget:op id=\"ML34\">=</sget:op><sget:exp id=\"ML2\"><sget:var unit=\"\" id=\"ML1\">b</sget:var><sget:num unit=\"\" randomness=\"S_2_10_0\" id=\"ML4\">?</sget:num></sget:exp></sget:equation>"), //
	Multiply_Exponentials_with_Similar_Exponents(
			Subject.Exponentials,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Term\"><div class=\"sg-in-term Exponential\"><div class=\"sg-in-exponential-base Variable\">b</div><div class=\"sg-in-exponential-exponent Variable\">d</div></div><div class=\"sg-in-term Operation\">·</div><div class=\"sg-in-term Exponential\"><div class=\"sg-in-exponential-base Variable\">c</div><div class=\"sg-in-exponential-exponent Variable\">d</div></div></div></div>",
			"<sget:equation id=\"ML52\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML53\" unit=\"\">a</sget:var><sget:op id=\"ML54\">=</sget:op><sget:term id=\"ML55\"><sget:exp id=\"ML56\"><sget:var id=\"ML57\" unit=\"\">b</sget:var><sget:var id=\"ML58\" unit=\"\">d</sget:var></sget:exp><sget:op id=\"ML59\">·</sget:op><sget:exp id=\"ML60\"><sget:var id=\"ML61\" unit=\"\">c</sget:var><sget:var id=\"ML62\" unit=\"\">d</sget:var></sget:exp></sget:term></sget:equation>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var unit=\"\" id=\"ML0\">a</sget:var><sget:op id=\"ML34\">=</sget:op><sget:exp id=\"ML38\"><sget:term id=\"ML63\"><sget:var unit=\"\" id=\"ML1\">b</sget:var><sget:op id=\"ML33\">·</sget:op><sget:var unit=\"\" id=\"ML37\">c</sget:var></sget:term><sget:var unit=\"\" id=\"ML40\">d</sget:var></sget:exp></sget:equation>"), //
	Negative_Exponent(
			Subject.Exponentials,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Exponential\"><div class=\"sg-in-exponential-base Variable\">b</div><div class=\"sg-in-exponential-exponent Variable\"><div class=\"sg-fenced\">-c<div class=\"sg-subscript\"></div></div></div></div></div>",
			"<sget:equation id=\"ML114\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML115\" unit=\"\">a</sget:var><sget:op id=\"ML116\">=</sget:op><sget:exp id=\"ML117\"><sget:var id=\"ML118\" unit=\"\">b</sget:var><sget:var id=\"ML119\" unit=\"\">-c</sget:var></sget:exp></sget:equation>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var unit=\"\" id=\"ML0\">a</sget:var><sget:op id=\"ML34\">=</sget:op><sget:frac id=\"ML135\"><sget:num value=\"1\" id=\"ML136\">1</sget:num><sget:exp id=\"ML110\"><sget:var id=\"ML111\" unit=\"\">b</sget:var><sget:var unit=\"\" id=\"ML113\">c</sget:var></sget:exp></sget:frac></sget:equation>"), //
	Expression_with_Exponent(
			Subject.Exponentials,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Exponential\"><div class=\"sg-in-exponential-base Term\"><div class=\"sg-fenced\"><div class=\"sg-in-term Variable\">b</div><div class=\"sg-in-term Operation\">·</div><div class=\"sg-in-term Variable\">c</div></div></div><div class=\"sg-in-exponential-exponent Variable\">e</div></div></div>",
			"<sget:equation id=\"ML260\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML261\" unit=\"\">a</sget:var><sget:op id=\"ML262\">=</sget:op><sget:exp id=\"ML263\"><sget:term id=\"ML264\"><sget:var id=\"ML265\" unit=\"\">b</sget:var><sget:op id=\"ML266\">·</sget:op><sget:var id=\"ML267\" unit=\"\">c</sget:var></sget:term><sget:var id=\"ML268\" unit=\"\">e</sget:var></sget:exp></sget:equation>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var unit=\"\" id=\"ML0\">a</sget:var><sget:op id=\"ML34\">=</sget:op><sget:term id=\"ML233\"><sget:exp id=\"ML295\"><sget:var id=\"ML111\" unit=\"\">b</sget:var><sget:var id=\"ML296\" unit=\"\">e</sget:var></sget:exp><sget:op id=\"ML138\">·</sget:op><sget:exp id=\"ML297\"><sget:var unit=\"\" id=\"ML142\">c</sget:var><sget:var id=\"ML298\" unit=\"\">e</sget:var></sget:exp></sget:term></sget:equation>"), //
	Log_of_One(
			Subject.Logarithms,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Log\"><div class=\"sg-functionName\">log</div><div class=\"sg-in-log-base\">10</div><div class=\"sg-in-log Number\">1</div></div></div>",
			"<sget:equation id=\"ML302\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML303\" unit=\"\">a</sget:var><sget:op id=\"ML304\">=</sget:op><sget:log id=\"ML305\" base=\"10\"><sget:num id=\"ML306\" unit=\"\" value=\"1\">1</sget:num></sget:log></sget:equation>",
			null), //
	Log_same_Base_as_Argument(
			Subject.Logarithms,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Log\"><div class=\"sg-functionName\">log</div><div class=\"sg-in-log-base\">e</div><div class=\"sg-in-log Number\">e</div></div></div>",
			"<sget:equation id=\"ML373\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML374\" unit=\"\">a</sget:var><sget:op id=\"ML375\">=</sget:op><sget:log id=\"ML376\" base=\"e\"><sget:num id=\"ML377\" unit=\"\" value=\"2.71828182845904523536028747135266249\">e</sget:num></sget:log></sget:equation>",
			null), //
	Evaluate_Log(
			Subject.Logarithms,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Log\"><div class=\"sg-functionName\">log</div><div class=\"sg-in-log-base\">2</div><div class=\"sg-in-log Number\">8</div></div></div>",
			"<sget:equation id=\"ML400\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML401\" unit=\"\">a</sget:var><sget:op id=\"ML402\">=</sget:op><sget:log id=\"ML403\" base=\"2\"><sget:num id=\"ML404\" unit=\"\" value=\"8\">8</sget:num></sget:log></sget:equation>",
			null), //
	Add_Logs(
			Subject.Logarithms,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Sum\"><div class=\"sg-in-sum Log\"><div class=\"sg-functionName\">log</div><div class=\"sg-in-log-base\">e</div><div class=\"sg-in-log Variable\">b</div></div><div class=\"sg-in-sum Operation\">+</div><div class=\"sg-in-sum Log\"><div class=\"sg-functionName\">log</div><div class=\"sg-in-log-base\">e</div><div class=\"sg-in-log Variable\">c</div></div></div></div>",
			"<sget:equation id=\"ML9\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML10\" unit=\"\">a</sget:var><sget:op id=\"ML11\">=</sget:op><sget:sum id=\"ML12\"><sget:log id=\"ML13\" base=\"e\"><sget:var id=\"ML14\" unit=\"\">b</sget:var></sget:log><sget:op id=\"ML15\">+</sget:op><sget:log id=\"ML16\" base=\"e\"><sget:var id=\"ML17\" unit=\"\">c</sget:var></sget:log></sget:sum></sget:equation>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var unit=\"\" id=\"ML0\">a</sget:var><sget:op id=\"ML34\">=</sget:op><sget:log base=\"e\" id=\"ML1\"><sget:term id=\"ML64\"><sget:var unit=\"\" id=\"ML2\">b</sget:var><sget:op id=\"ML65\">·</sget:op><sget:var unit=\"\" id=\"ML8\">c</sget:var></sget:term></sget:log></sget:equation>"), //
	Subtract_Logs(
			Subject.Logarithms,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Sum\"><div class=\"sg-in-sum Log\"><div class=\"sg-functionName\">log</div><div class=\"sg-in-log-base\">e</div><div class=\"sg-in-log Variable\">b</div></div><div class=\"sg-in-sum Operation\">-</div><div class=\"sg-in-sum Log\"><div class=\"sg-functionName\">log</div><div class=\"sg-in-log-base\">e</div><div class=\"sg-in-log Variable\">c</div></div></div></div>",
			"<sget:equation id=\"ML109\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML110\" unit=\"\">a</sget:var><sget:op id=\"ML111\">=</sget:op><sget:sum id=\"ML112\"><sget:log id=\"ML113\" base=\"e\"><sget:var id=\"ML114\" unit=\"\">b</sget:var></sget:log><sget:op id=\"ML115\">-</sget:op><sget:log id=\"ML116\" base=\"e\"><sget:var id=\"ML117\" unit=\"\">c</sget:var></sget:log></sget:sum></sget:equation>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var unit=\"\" id=\"ML0\">a</sget:var><sget:op id=\"ML34\">=</sget:op><sget:log base=\"e\" id=\"ML1\"><sget:frac id=\"ML163\"><sget:var unit=\"\" id=\"ML2\">b</sget:var><sget:var unit=\"\" id=\"ML108\">c</sget:var></sget:frac></sget:log></sget:equation>"), //
	Multiply_with_log(
			Subject.Logarithms,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Term\"><div class=\"sg-in-term Variable\">b</div><div class=\"sg-in-term Operation\">·</div><div class=\"sg-in-term Log\"><div class=\"sg-functionName\">log</div><div class=\"sg-in-log-base\">e</div><div class=\"sg-in-log Variable\">c</div></div></div></div>",
			"<sget:equation id=\"ML7\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML8\" unit=\"\">a</sget:var><sget:op id=\"ML9\">=</sget:op><sget:term id=\"ML10\"><sget:var id=\"ML11\" unit=\"\">b</sget:var><sget:op id=\"ML12\">·</sget:op><sget:log id=\"ML13\" base=\"e\"><sget:var id=\"ML14\" unit=\"\">c</sget:var></sget:log></sget:term></sget:equation>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var unit=\"\" id=\"ML0\">a</sget:var><sget:op id=\"ML34\">=</sget:op><sget:log base=\"e\" id=\"ML5\"><sget:exp id=\"ML26\"><sget:var unit=\"\" id=\"ML6\">c</sget:var><sget:var unit=\"\" id=\"ML1\">b</sget:var></sget:exp></sget:log></sget:equation>"), //
	Log_Product(
			Subject.Logarithms,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Log\"><div class=\"sg-functionName\">log</div><div class=\"sg-in-log-base\">10</div><div class=\"sg-in-log Term\"><div class=\"sg-in-term Variable\">b</div><div class=\"sg-in-term Operation\">·</div><div class=\"sg-in-term Variable\">c</div></div></div></div>",
			"<sget:equation id=\"ML768\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML769\" unit=\"\">a</sget:var><sget:op id=\"ML770\">=</sget:op><sget:log id=\"ML771\" base=\"10\"><sget:term id=\"ML772\"><sget:var id=\"ML773\" unit=\"\">b</sget:var><sget:op id=\"ML774\">·</sget:op><sget:var id=\"ML775\" unit=\"\">c</sget:var></sget:term></sget:log></sget:equation>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var unit=\"\" id=\"ML370\">a</sget:var><sget:op id=\"ML34\">=</sget:op><sget:sum id=\"ML813\"><sget:log base=\"10\" id=\"ML814\"><sget:var unit=\"\" id=\"ML629\">b</sget:var></sget:log><sget:op id=\"ML815\">+</sget:op><sget:log base=\"10\" id=\"ML816\"><sget:var unit=\"\" id=\"ML767\">c</sget:var></sget:log></sget:sum></sget:equation>"), //
	Log_Quotient(
			Subject.Logarithms,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Log\"><div class=\"sg-functionName\">log</div><div class=\"sg-in-log-base\">10</div><div class=\"sg-in-log Fraction\"><div class=\"sg-in-fraction-numerator Variable\">b</div><div class=\"sg-in-fraction-denominator Variable\">c</div></div></div></div>",
			"<sget:equation id=\"ML866\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML867\" unit=\"\">a</sget:var><sget:op id=\"ML868\">=</sget:op><sget:log id=\"ML869\" base=\"10\"><sget:frac id=\"ML870\"><sget:var id=\"ML871\" unit=\"\">b</sget:var><sget:var id=\"ML872\" unit=\"\">c</sget:var></sget:frac></sget:log></sget:equation>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var unit=\"\" id=\"ML370\">a</sget:var><sget:op id=\"ML34\">=</sget:op><sget:sum id=\"ML905\"><sget:log base=\"10\" id=\"ML908\"><sget:var unit=\"\" id=\"ML629\">b</sget:var></sget:log><sget:op id=\"ML907\">-</sget:op><sget:log base=\"10\" id=\"ML906\"><sget:var unit=\"\" id=\"ML865\">c</sget:var></sget:log></sget:sum></sget:equation>"), //
	Log_Power(
			Subject.Logarithms,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Log\"><div class=\"sg-functionName\">log</div><div class=\"sg-in-log-base\">10</div><div class=\"sg-in-log Exponential\"><div class=\"sg-in-exponential-base Variable\">b</div><div class=\"sg-in-exponential-exponent Variable\">c</div></div></div></div>",
			"<sget:equation id=\"ML957\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML958\" unit=\"\">a</sget:var><sget:op id=\"ML959\">=</sget:op><sget:log id=\"ML960\" base=\"10\"><sget:exp id=\"ML961\"><sget:var id=\"ML962\" unit=\"\">b</sget:var><sget:var id=\"ML963\" unit=\"\">c</sget:var></sget:exp></sget:log></sget:equation>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var unit=\"\" id=\"ML370\">a</sget:var><sget:op id=\"ML34\">=</sget:op><sget:term id=\"ML994\"><sget:var unit=\"\" id=\"ML956\">c</sget:var><sget:op id=\"ML995\">·</sget:op><sget:log base=\"10\" id=\"ML908\"><sget:var unit=\"\" id=\"ML629\">b</sget:var></sget:log></sget:term></sget:equation>"), //
	Log_Inverse(
			Subject.Logarithms,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Log\"><div class=\"sg-functionName\">log</div><div class=\"sg-in-log-base\">e</div><div class=\"sg-in-log Exponential\"><div class=\"sg-in-exponential-base Number\">e</div><div class=\"sg-in-exponential-exponent Variable\">b</div></div></div></div>",
			"<sget:equation id=\"ML6\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML7\" unit=\"\">a</sget:var><sget:op id=\"ML8\">=</sget:op><sget:log id=\"ML9\" base=\"e\"><sget:exp id=\"ML10\"><sget:num id=\"ML11\" unit=\"\" value=\"2.71828182845904523536028747135266249\">e</sget:num><sget:var id=\"ML12\" unit=\"\">b</sget:var></sget:exp></sget:log></sget:equation>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var unit=\"\" id=\"ML0\">a</sget:var><sget:op id=\"ML34\">=</sget:op><sget:var unit=\"\" id=\"ML5\">b</sget:var></sget:equation>"), //
	Exponential_Inverse(
			Subject.Logarithms,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Exponential\"><div class=\"sg-in-exponential-base Number\">e</div><div class=\"sg-in-exponential-exponent Log\"><div class=\"sg-fenced\"><div class=\"sg-functionName\">log</div><div class=\"sg-in-log-base\">e</div><div class=\"sg-in-log Variable\">b</div></div></div></div></div>",
			"<sget:equation id=\"ML59\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML60\" unit=\"\">a</sget:var><sget:op id=\"ML61\">=</sget:op><sget:exp id=\"ML62\"><sget:num id=\"ML63\" unit=\"\" value=\"2.71828182845904523536028747135266249\">e</sget:num><sget:log id=\"ML64\" base=\"e\"><sget:var id=\"ML65\" unit=\"\">b</sget:var></sget:log></sget:exp></sget:equation>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var unit=\"\" id=\"ML0\">a</sget:var><sget:op id=\"ML34\">=</sget:op><sget:var unit=\"\" id=\"ML58\">b</sget:var></sget:equation>"), //
	Log_Change_Base(
			Subject.Logarithms,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Log\"><div class=\"sg-functionName\">log</div><div class=\"sg-in-log-base\">10</div><div class=\"sg-in-log Variable\">b</div></div></div>",
			"<sget:equation id=\"ML60\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML61\" unit=\"\">a</sget:var><sget:op id=\"ML62\">=</sget:op><sget:log id=\"ML63\" base=\"10\"><sget:var id=\"ML64\" unit=\"\">b</sget:var></sget:log></sget:equation>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var unit=\"\" id=\"ML0\">a</sget:var><sget:op id=\"ML34\">=</sget:op><sget:frac id=\"ML76\"><sget:log base=\"2\" id=\"ML58\"><sget:var unit=\"\" id=\"ML59\">b</sget:var></sget:log><sget:log base=\"2\" id=\"ML77\"><sget:num value=\"10\" id=\"ML78\">10</sget:num></sget:log></sget:frac></sget:equation>"), //
	Relating_the_functions(
			Subject.Trigonometry,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Trig\"><div class=\"sg-functionName\">tan</div><div class=\"sg-in-trig Variable\">θ</div></div></div>",
			"<sget:equation id=\"ML3\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML4\" unit=\"\">a</sget:var><sget:op id=\"ML5\">=</sget:op><sget:trig id=\"ML6\" function=\"tan\"><sget:var id=\"ML7\" unit=\"Angle^1\">θ</sget:var></sget:trig></sget:equation>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var unit=\"\" id=\"ML0\">a</sget:var><sget:op id=\"ML34\">=</sget:op><sget:frac id=\"ML8\"><sget:trig function=\"sin\" id=\"ML1\"><sget:var unit=\"Angle^1\" id=\"ML2\">θ</sget:var></sget:trig><sget:trig function=\"cos\" id=\"ML9\"><sget:var id=\"ML10\" unit=\"Angle^1\">θ</sget:var></sget:trig></sget:frac></sget:equation>"), //
	Reciprocal(
			Subject.Trigonometry,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Variable\">a</div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Trig\"><div class=\"sg-functionName\">sin</div><div class=\"sg-in-trig Variable\">θ</div></div></div>",
			"<sget:equation id=\"ML11\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var id=\"ML12\" unit=\"\">a</sget:var><sget:op id=\"ML13\">=</sget:op><sget:trig id=\"ML14\" function=\"sin\"><sget:var id=\"ML15\" unit=\"Angle^1\">θ</sget:var></sget:trig></sget:equation>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var unit=\"\" id=\"ML0\">a</sget:var><sget:op id=\"ML34\">=</sget:op><sget:frac id=\"ML16\"><sget:num value=\"1\" id=\"ML17\">1</sget:num><sget:trig function=\"csc\" id=\"ML1\"><sget:var unit=\"Angle^1\" id=\"ML2\">θ</sget:var></sget:trig></sget:frac></sget:equation>"), //
	// Inverse(Subject.Trigonometry, "", "",""),//
	Add(
			Subject.Solving,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Sum\"><div class=\"sg-in-sum Variable\">a</div><div class=\"sg-in-sum Operation\">-</div><div class=\"sg-in-sum Number\">?</div></div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Number\">?</div></div>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:sum id=\"ML1\"><sget:var unit=\"\" id=\"ML0\">a</sget:var><sget:op id=\"ML2\">-</sget:op><sget:num unit=\"\" randomness=\"N_2_10_0\" id=\"ML4\">?</sget:num></sget:sum><sget:op id=\"ML34\">=</sget:op><sget:num unit=\"\" randomness=\"N_2_10_0\" id=\"ML5\">?</sget:num></sget:equation>",
			null), //
	Subtract(
			Subject.Solving,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Sum\"><div class=\"sg-in-sum Variable\">a</div><div class=\"sg-in-sum Operation\">+</div><div class=\"sg-in-sum Number\">?</div></div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Number\">?</div></div>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:sum id=\"ML1\"><sget:var unit=\"\" id=\"ML0\">a</sget:var><sget:op id=\"ML2\">+</sget:op><sget:num unit=\"\" randomness=\"N_2_10_0\" id=\"ML4\">?</sget:num></sget:sum><sget:op id=\"ML34\">=</sget:op><sget:num unit=\"\" randomness=\"N_2_10_0\" id=\"ML5\">?</sget:num></sget:equation>",
			null), //
	Multiply(
			Subject.Solving,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Fraction\"><div class=\"sg-in-fraction-numerator Variable\">a</div><div class=\"sg-in-fraction-denominator Number\">?</div></div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Number\">?</div></div>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:frac id=\"ML6\"><sget:var unit=\"\" id=\"ML0\">a</sget:var><sget:num id=\"ML8\" unit=\"\" randomness=\"N_2_10_0\">?</sget:num></sget:frac><sget:op id=\"ML34\">=</sget:op><sget:num unit=\"\" randomness=\"N_2_10_0\" id=\"ML5\">?</sget:num></sget:equation>",
			null), //
	Divide(
			Subject.Solving,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation Term\"><div class=\"sg-in-term Variable\">a</div><div class=\"sg-in-term Operation\">·</div><div class=\"sg-in-term Number\">?</div></div><div class=\"sg-in-equation Operation\">=</div><div class=\"sg-in-equation Number\">?</div></div>",
			"<sget:equation id=\"ML21\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:term id=\"ML22\"><sget:var id=\"ML23\" unit=\"\">a</sget:var><sget:op id=\"ML24\">·</sget:op><sget:num id=\"ML25\" unit=\"\" randomness=\"N_2_10_0\">?</sget:num></sget:term><sget:op id=\"ML26\">=</sget:op><sget:num id=\"ML27\" unit=\"\" randomness=\"N_2_10_0\">?</sget:num></sget:equation>",
			"<sget:equation id=\"ML32\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:var unit=\"\" id=\"ML0\">a</sget:var><sget:op id=\"ML34\">=</sget:op><sget:frac id=\"ML28\"><sget:num unit=\"\" randomness=\"N_2_10_0\" id=\"ML5\">?</sget:num><sget:num id=\"ML20\" unit=\"\" randomness=\"N_2_10_0\">?</sget:num></sget:frac></sget:equation>"), //
	Inverse_Exponent(
			Subject.Solving,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation sg-Exponential\"><div class=\"sg-in-exponential-base sg-Variable\">a</div><div class=\"sg-in-exponential-exponent sg-Number\">2</div></div><div class=\"sg-in-equation sg-Operation\">=</div><div class=\"sg-in-equation sg-Number\">9</div></div>",
			"<sget:equation id=\"ML0\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:exp id=\"ML1\"><sget:var id=\"ML2\">a</sget:var><sget:num value=\"2\" id=\"ML3\">2</sget:num></sget:exp><sget:op id=\"ML4\">=</sget:op><sget:num value=\"9\" id=\"ML5\">9</sget:num></sget:equation>",
			null), //
	Raise(
			Subject.Solving,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation sg-Log\"><div class=\"sg-functionName\">log</div><div class=\"sg-in-log-base\">10</div><div class=\"sg-in-log sg-Variable\">a</div></div><div class=\"sg-in-equation sg-Operation\">=</div><div class=\"sg-in-equation sg-Number\">?</div></div>",
			"<sget:equation id=\"ML0\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:log id=\"ML4\" base=\"10\"><sget:var id=\"ML3\" unit=\"\">a</sget:var></sget:log><sget:op id=\"dummyNodeEquals\">=</sget:op><sget:num id=\"ML5\" randomness=\"S_0_10_0\" unit=\"\">?</sget:num></sget:equation>",
			null), //
	Log(
			Subject.Solving,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation sg-Exponential\"><div class=\"sg-in-exponential-base sg-Number\">10</div><div class=\"sg-in-exponential-exponent sg-Variable\">a</div></div><div class=\"sg-in-equation sg-Operation\">=</div><div class=\"sg-in-equation sg-Number\">?</div></div>",
			"<sget:equation id=\"ML0\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:exp id=\"ML8\"><sget:num id=\"ML7\" value=\"10\" unit=\"\">10</sget:num><sget:var id=\"ML10\" unit=\"\">a</sget:var></sget:exp><sget:op id=\"dummyNodeEquals\">=</sget:op><sget:num id=\"ML5\" randomness=\"S_0_10_0\" unit=\"\">?</sget:num></sget:equation>",
			null), //
	Inverse_Trig(
			Subject.Solving,
			"<div class=\"sg-Equation\"><div class=\"sg-in-equation sg-Trig\"><div class=\"sg-functionName\">sin</div><div class=\"sg-in-trig sg-Variable\">a</div></div><div class=\"sg-in-equation sg-Operation\">=</div><div class=\"sg-in-equation sg-Number\">?</div></div>",
			"<sget:equation id=\"ML0\" xmlns:sget=\"http://www.sciencegadgets.org/Data\"><sget:trig id=\"ML12\" function=\"sin\"><sget:var id=\"ML13\" unit=\"Angle^1\">a</sget:var></sget:trig><sget:op id=\"dummyNodeEquals\">=</sget:op><sget:num id=\"ML5\" randomness=\"S_0_10_0\" unit=\"\">?</sget:num></sget:equation>",
			null);

	private Subject subject;
	private String equationHTML;
	private String equationXML;
	private String goalXML;

	AlgebraPracticeProblems(Subject subject, String equationHTML,
			String equationXML, String goalXML) {
		this.subject = subject;
		this.equationHTML = equationHTML;
		this.equationXML = equationXML;
		this.goalXML = goalXML;
	}

	public Subject getSubject() {
		return subject;
	}

	public String getEquationHTML() {
		return equationHTML;
	}

	public String getEquationXML() {
		return equationXML;
	}

	public String getGoalXML() {
		return goalXML;
	}

	@Override
	public String toString() {

		return super.toString().replace("OP", "(").replace("CP", ")")
				.replace("_", " ").replace("NEG", "-").replace("COL", ":");
	}

	enum Subject {
		Arithmetic, Special_Numbers, Fractions, Distribution_and_Factoring, Exponentials, Logarithms, Trigonometry, Solving;
	}

}
