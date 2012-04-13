package com.sciencegadgets.client;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sciencegadgets.client.AlgebraManipulation.AlgebraManipulator;
import com.sciencegadgets.client.EquationTree.JohnTree;
import com.sciencegadgets.client.EquationTree.TreeCanvas;

public class EquationBrowser implements EntryPoint {

	EquationDatabase data = new EquationDatabase();

	HorizontalPanel browserPanel = new HorizontalPanel();
	private Grid eqGrid = new Grid(1, 1);
	private Grid varGrid = new Grid(1, 1);
	private Grid sumGrid = new Grid(1, 2);
	private Grid algGrid = new Grid(1, 1);
	private CheckBox multiSwitch = new CheckBox("Multi-Select");
	private Set<String> selectedVars = new HashSet<String>();
	private RadioButton modeSelectAlg = new RadioButton("mode", "Algebra Mode");
	private RadioButton modeSelectSci = new RadioButton("mode", "Science Mode");
	private HTML labelSumEq = new HTML("");
	AbsolutePanel parsedTreePanel;

	public void onModuleLoad() {

		modeSelectAlg.setSize("50em", "10em");
		modeSelectSci.setSize("50em", "10em");
		browserPanel.setStylePrimaryName("browserPanel");

		RootPanel.get().add(modeSelectAlg);
		RootPanel.get().add(modeSelectSci);
		RootPanel.get().add(browserPanel);

		ModeSelectHandler modeSelectHandle = new ModeSelectHandler();
		modeSelectAlg.addClickHandler(modeSelectHandle);
		modeSelectSci.addClickHandler(modeSelectHandle);
	}

	private void createSciBrowser() {
		// First box, Variable list
		VerticalPanel vpVar = new VerticalPanel();
		Label labelVar = new Label("Variables");
		ScrollPanel spVar = new ScrollPanel(varGrid);
		varGrid.addClickHandler(new VarClickHandler(varGrid));
		vpVar.add(labelVar);
		vpVar.add(spVar);
		vpVar.add(multiSwitch);
		multiSwitch.addClickHandler(new MultiSwitchClickHandler());
		fillVarList();

		// Second box, Equation list
		VerticalPanel vpEq = new VerticalPanel();
		Label labelEq = new Label("Equations");
		ScrollPanel spEq = new ScrollPanel(eqGrid);
		eqGrid.addClickHandler(new EqClickHandler(eqGrid));
		vpEq.add(labelEq);
		vpEq.add(spEq);

		// Third box, equation summary
		VerticalPanel vpSum = new VerticalPanel();
		Label labelSum = new Label("Summary");
		ScrollPanel spSum = new ScrollPanel(sumGrid);
		labelSumEq.setHeight("2em");
		vpSum.add(labelSum);
		vpSum.add(labelSumEq);
		vpSum.add(spSum);

		// Assemble browserPanel
		browserPanel.add(vpVar);
		browserPanel.add(vpEq);
		browserPanel.add(vpSum);

		// Add styles
		vpVar.setStylePrimaryName("gridBox");
		vpEq.setStylePrimaryName("gridBox");
		vpSum.setStylePrimaryName("gridBox");

		spVar.setStylePrimaryName("sp");
		spEq.setStylePrimaryName("sp");
		spSum.setStylePrimaryName("sp");

		labelVar.setStylePrimaryName("rowHeader");
		labelEq.setStylePrimaryName("rowHeader");
		labelSum.setStylePrimaryName("rowHeader");

		parseJQMath(varGrid.getElement());

	}

	private void createAlgBrowser() {
		VerticalPanel vpAlg = new VerticalPanel();
		Label labelAlg = new Label("Algebra Practice");
		ScrollPanel spAlg = new ScrollPanel(algGrid);
		algGrid.addClickHandler(new EqClickHandler(algGrid));
		vpAlg.add(labelAlg);
		vpAlg.add(spAlg);

		vpAlg.setStylePrimaryName("gridBox");
		spAlg.setStylePrimaryName("sp");
		labelAlg.setStylePrimaryName("rowHeader");

		// Fill panel
		String[] algNameList = data.getAll(data.FLAG_ALGEBRA_NAME);
		algGrid.resizeRows(algNameList.length);
		for (int i = 0; i < algNameList.length; i++) {
			algGrid.setText(i, 0, algNameList[i]);
		}

		browserPanel.add(vpAlg);
	}

	/**
	 * Gets all the available variables and fills the list
	 * 
	 * @param varSet
	 *            - set of variables
	 */
	private void fillVarList() {
		String varHTML;
		String[] vars = data.getAll(data.FLAG_VARIABLE_LOOK);
		String[] desc = data.getAll(data.FLAG_VARIABLE_DESCRIPTION);
		varGrid.resizeRows(vars.length);

		for (int i = 0; i < vars.length; i++) {
			varHTML = "<span style=\"cursor:pointer;\">$" + vars[i] + "$ "
					+ desc[i] + "</span>";
			varGrid.setHTML(i, 0, varHTML);
		}
	}

	/**
	 * Finds all the equations with every variable in the given set, then fills
	 * the equation list
	 * 
	 * @param varSet
	 *            - set of variables
	 */
	private void onVarSelect(Set<String> varSet) {
		String eqHTML;
		String[] eqList = data.getEquationsByVariables(varSet);

		eqGrid.resizeRows(eqList.length);

		sumGrid.clear();
		for (int i = 0; i < eqList.length; i++) {
			eqHTML = "<span style=\"cursor:pointer;width:10em;\">$" + eqList[i]
					+ "$</span>";
			eqGrid.setHTML(i, 0, eqHTML);
			eqGrid.getCellFormatter().setAlignment(i, 0,
					HasHorizontalAlignment.ALIGN_CENTER,
					HasVerticalAlignment.ALIGN_MIDDLE);
		}
		parseJQMath(eqGrid.getElement());
	}

	/**
	 * Finds the variables with descriptions of the given equation to fill the
	 * variable description (varDesc) box
	 * 
	 * @param equation
	 */
	private void onEqSelect(String equation) {

		// Initial AlgOut line
		labelSumEq.setText("$" + equation + "$");
		HTML algOutFirstHTML = new HTML("$" + equation + "$");
		ScienceGadgets.algOut.clear(true);
		ScienceGadgets.algOut.resizeRows(1);
		ScienceGadgets.algOut.setWidget(0, 0, algOutFirstHTML);
		parseJQMath(labelSumEq.getElement());
		parseJQMath(algOutFirstHTML.getElement());

		HTML draggableEquation = new HTML();
		draggableEquation.setHTML("$" + equation + "$");
		parseJQMath(draggableEquation.getElement());

		// Make the tree on canvas
		JohnTree johnTree = new JohnTree(draggableEquation, false);
		ScienceGadgets.apTree.clear();
		TreeCanvas treeCanvas = new TreeCanvas(ScienceGadgets.apTree, johnTree);

		parsedTreePanel = new AbsolutePanel();
		parsedTreePanel.setStyleName("treePanel");
		RootPanel.get().add(parsedTreePanel);
		JohnTree jTree = new JohnTree(draggableEquation, true);
		TreeCanvas tCanvas = new TreeCanvas(parsedTreePanel, jTree);

		System.out.println(algOutFirstHTML.getHTML());

		// Make draggable algebra area
		ScienceGadgets.algDragPanel.add(new AlgebraManipulator(
				draggableEquation, johnTree.getWrappers(),
				ScienceGadgets.algDragPanel));

		// DropControllAssigner a = new DropControllAssigner(johnTree);

		/*
		 * MathTree mathTree = new MathTree(draggableEquation);
		 * RootPanel.get().add(mathTree.getTreeDrawing());
		 */

		if (modeSelectSci.getValue()) {
			fillSummary(equation);
		}
	}

	/**
	 * fills the summary box
	 */
	void fillSummary(String equation) {
		// fill variable summary
		String[] variables;
		try {
			variables = data.getVariablesByEquation(equation);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		int row = 0;
		String varHTML, descHTML;

		sumGrid.clear(true);
		sumGrid.resizeRows(variables.length);

		for (String var : variables) {
			varHTML = "<span class=\"var\">$" + var + "$ </span>";
			try {
				descHTML = "<span>$"
						+ data.getAttribute(data.FLAG_VARIABLE_DESCRIPTION, var)
						+ "$</span>";
			} catch (Exception e) {
				e.printStackTrace();
				descHTML = "<span></span>";
			}

			sumGrid.setHTML(row, 0, varHTML);
			sumGrid.setHTML(row, 1, descHTML);
			row++;
		}
		parseJQMath(sumGrid.getElement());

		// Fill varBox (algebra menu)
		/*
		 * scienceGadgets.varBox.clear(); scienceGadgets.varBox.addItem(""); for
		 * (int i = 0; i < sumGrid.getRowCount(); i++) {
		 * scienceGadgets.varBox.addItem(variables[i]); }
		 * parseJQMath(scienceGadgets.varBox.getElement());
		 */}

	/**
	 * Single selection handler for equation list
	 */
	class EqClickHandler implements ClickHandler {
		HTMLTable table;

		public EqClickHandler(HTMLTable table) {
			this.table = table;
		}

		public void onClick(ClickEvent event) {
			Cell clickedCell = table.getCellForEvent(event);

			if (clickedCell != null) {
				Element clickedEl = clickedCell.getElement();

				if (!clickedEl.getId().equals("selectedEq")) {
					com.google.gwt.dom.client.Element prevSel = Document.get()
							.getElementById("selectedEq");
					if (prevSel != null) {
						prevSel.setId("");
					}
					clickedEl.setId("selectedEq");
				}

				String equation = null;
				// String sendEq = null;

				// For Algebra practice mode
				if (table.equals(algGrid)) {
					equation = clickedEl.getInnerText();

					// For Science Mode
				} else if (table.equals(eqGrid)) {

					Element element = (Element) clickedEl.getElementsByTagName(
							"math").getItem(0);
					if (element == null) {
						Window.alert("Your browser may not show everything correctly. Try another browser");
						element = (Element) clickedEl.getElementsByTagName(
								"fmath").getItem(0);
					}
					equation = DOM.getElementAttribute(element, "alttext");
				}
				onEqSelect(equation);
			}
		}
	}

	/**
	 * The multi-selection handler for variable list
	 */
	class VarClickHandler implements ClickHandler {
		HTMLTable table;

		public VarClickHandler(HTMLTable table) {
			this.table = table;
		}

		public void onClick(ClickEvent event) {
			Cell clickedCell = table.getCellForEvent(event);

			if (clickedCell != null) {
				Element clicked = clickedCell.getElement();
				String varName = clicked.getInnerText().split(" ")[0];

				// Multi select
				if (multiSwitch.getValue()) {

					if (clicked.getClassName().equals("selectedVar")) {
						clicked.setClassName("");
						selectedVars.remove(varName);
					} else {
						clicked.setClassName("selectedVar");
						selectedVars.add(varName);
					}
					// Single select
				} else {
					if (clicked.getId().equals("selectedVar")) {
					} else {
						com.google.gwt.dom.client.Element prevSelect = Document
								.get().getElementById("selectedVar");
						if (prevSelect != null) {
							prevSelect.setId("");
						}
						selectedVars.clear();
						clicked.setId("selectedVar");
						selectedVars.add(varName);
					}
				}
				sumGrid.clear(true);
				labelSumEq.setText("");
				ScienceGadgets.algOut.clear(true);
				onVarSelect(selectedVars);
				com.google.gwt.dom.client.Element prevSel = Document.get()
						.getElementById("selectedEq");
				if (prevSel != null) {
					prevSel.setId("");
				}
			}
		}
	}

	class MultiSwitchClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			if (multiSwitch.getValue()) {
				// turning Multi-Select on
				com.google.gwt.dom.client.Element el = Document.get()
						.getElementById("selectedVar");
				if (el != null) {
					el.setClassName("selectedVar");
					el.setId("");
				}
			} else {
				// turning multi off
				eqGrid.clear(true);
				CellFormatter elm = varGrid.getCellFormatter();
				for (int i = 0; i < varGrid.getRowCount(); i++) {
					elm.getElement(i, 0).setClassName("");
				}
			}
		}
	}

	class ModeSelectHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			RadioButton clicked = ((RadioButton) event.getSource());

			if (clicked.equals(modeSelectAlg)) {
				browserPanel.clear();
				ScienceGadgets.algDragPanel.clear();
				ScienceGadgets.algOut.clear(true);
				ScienceGadgets.apTree.clear();
				createAlgBrowser();
			} else if (clicked.equals(modeSelectSci)) {
				browserPanel.clear();
				ScienceGadgets.algDragPanel.clear();
				ScienceGadgets.algOut.clear(true);
				ScienceGadgets.apTree.clear();
				createSciBrowser();
				sumGrid.clear(true);
			}
		}
	}

	/**
	 * JavaScript method in jqMath that would parse the given element and
	 * display all equations in standard mathematical symbols. This will parse
	 * every string surrounded by $'s
	 * <p>
	 * ex. $ K=1/2mÎ½^2 $
	 * </p>
	 * 
	 * @param element
	 *            - the web element to parse as math
	 */
	public static native void parseJQMath(Element element) /*-{
															$wnd.M.parseMath(element);
															}-*/;

}