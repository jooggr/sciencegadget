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

import java.util.HashMap;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import com.sciencegadgets.client.Moderator;
import com.sciencegadgets.client.entities.DataModerator;
import com.sciencegadgets.client.ui.CSS;
import com.sciencegadgets.client.ui.SelectionPanel;
import com.sciencegadgets.client.ui.SelectionPanel.Cell;
import com.sciencegadgets.client.ui.SelectionPanel.SelectionHandler;

public class ScienceBrowser extends FlowPanel {

	private SelectionPanel sciBrowseVar = new SelectionPanel("Variables");
	private SelectionPanel sciBrowseEq = new SelectionPanel("Equations");
	private FlowPanel sciBrowseSum = new FlowPanel();
	public Grid sumGrid = new Grid(1, 4);
	private CheckBox multiSwitch = new CheckBox("Multi-Select");
	private HashMap<TextBox, Element> inputBinding = new HashMap<TextBox, Element>();
	private Button sumButton = new Button("Use");
	public static HTML labelSumEq = new HTML("");
	private HomeBrowser equationBrowser;

	public ScienceBrowser(final HomeBrowser equationBrowser) {
		super();
		this.equationBrowser = equationBrowser;
		getElement().setId(CSS.SCI_BROWSER);

		// (1) First box, Variable list
		sciBrowseVar.addSelectionHandler(new SelectionHandler() {

			@Override
			public void onSelect(Cell selected) {
				DataModerator.fill_EquationsByQuantities(selected.getValue(),
						sciBrowseEq);
			}
		});
//		DataModerator.fill_Quantities(sciBrowseVar);

		// (2) Second box, Equation list
		sciBrowseEq.addSelectionHandler(new SelectionHandler() {

			@Override
			public void onSelect(Cell selected) {
				String equationXMLStr = selected.getValue();
				if (equationXMLStr != null) {
					// Element mathxml = (Element)
					// XMLParser.parse(mathmlStr).getDocumentElement();
					Element equationXML = new HTML(equationXMLStr)
							.getElement().getFirstChildElement();
					
					Window.alert("NotWorking, see Science Browser");
					//TODO deal with equation
//					Moderator.switchToAlgebra(equationXML, equationBrowser.inEditMode, true);
				}
			}
		});

		sciBrowseVar.getElement().setId(CSS.SCI_BROWSER_VAR);
		sciBrowseEq.getElement().setId(CSS.SCI_BROWSER_EQ);
		sciBrowseSum.getElement().setId(CSS.SCI_BROWSER_SUM);

		// Assemble browserPanel
		this.add(sciBrowseVar);
		this.add(sciBrowseEq);
		this.add(sciBrowseSum);

	}

	/**
	 * The multi-selection handler for variable list
	 */
	// class VarClickHandler implements ClickHandler {
	// Grid table;
	//
	// public VarClickHandler(HTMLTable table) {
	// this.table = (Grid) table;
	// }
	//
	// public void onClick(ClickEvent event) {
	// Cell clickedCell = table.getCellForEvent(event);
	//
	// if (clickedCell != null) {
	//
	// // The variable symbol and name should be in diferent columns
	// // but how do you get a cell element in a table?
	// // if(clickedCell.getCellIndex() != 0){
	// // table.getHTML(clickedCell.getRowIndex(), 0;)
	// // }
	//
	// Element clicked = clickedCell.getElement();
	// String varName = clicked.getInnerText().split(" ")[0];
	//
	// // Multi select
	// if (multiSwitch.getValue()) {
	//
	// if (clicked.getClassName().equals("selectedVar")) {
	// clicked.setClassName("");
	// selectedVars.remove(varName);
	// } else {
	// clicked.setClassName("selectedVar");
	// selectedVars.add(varName);
	// }
	// // Single select
	// } else {
	// if (clicked.getId().equals("selectedVar")) {
	// } else {
	// com.google.gwt.dom.client.Element prevSelect = Document
	// .get().getElementById("selectedVar");
	// if (prevSelect != null) {
	// prevSelect.setId("");
	// }
	// selectedVars.clear();
	// clicked.setId("selectedVar");
	// selectedVars.add(varName);
	// }
	// }
	// sumGrid.clear(true);
	// labelSumEq.setText("");
	// sumButton.setVisible(false);
	// // AlgOut.algOut.clear(true);
	// com.google.gwt.dom.client.Element prevSel = Document.get()
	// .getElementById("selectedEq");
	// // onVarSelect(selectedVars);
	// // onVarSelect(selectedVars.toArray(new String[0]));
	//
	// if (prevSel != null) {
	// prevSel.setId("");
	// }
	// }
	// }
	// }

	/**
	 * fills the summary box
	 */
	void fillSummary(Element mathml) {

		labelSumEq.getElement().appendChild(mathml);
		sumButton.setVisible(true);

		// fill variable summary
		// NodeList<com.google.gwt.dom.client.Element> varNodes = labelSumEq
		// .getElement().getElementsByTagName("mi");
		//
		// sumGrid.clear(true);
		// sumGrid.resizeRows(varNodes.getLength());
		//
		// inputBinding.clear();
		//
		// for (int i = 0; i < varNodes.getLength(); i++) {
		// Label varLabel = new Label(varNodes.getItem(i).getInnerText());
		//
		// TextBox valueInput = new TextBox();
		// inputBinding.put(valueInput, (Element) varNodes.getItem(i));
		// valueInput.setWidth("4em");
		//
		// Button findButton = new Button("Find");
		// findButton.addClickHandler(new FindClickHandler(valueInput));
		//
		// sumGrid.setWidget(i, 0, varLabel);
		// // sumGrid.setHTML(i, 1, descHTML);
		// sumGrid.setWidget(i, 2, valueInput);
		// sumGrid.setWidget(i, 3, findButton);
		// }

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
				// eqGrid.clear(true);
				// CellFormatter elm = varGrid.getCellFormatter();
				// for (int i = 0; i < varGrid.getRowCount(); i++) {
				// elm.getElement(i, 0).setClassName("");
				// }
			}
		}
	}

	class FindClickHandler implements ClickHandler {
		TextBox textBox;

		FindClickHandler(TextBox textBox) {
			this.textBox = textBox;
		}

		@Override
		public void onClick(ClickEvent arg0) {

			boolean unknownExists = false;

			for (TextBox box : inputBinding.keySet()) {
				if (!box.isEnabled()) {
					unknownExists = true;
				}
			}

			if (unknownExists) {
				Window.alert("There should only be one unknown variable to find");
			} else {
				textBox.setText("???");
				textBox.setEnabled(false);
			}
		}
	}

	class UseEquation implements ClickHandler {

		@Override
		public void onClick(ClickEvent arg0) {
			

			// Replace known variables with given values inputed
			for (TextBox box : inputBinding.keySet()) {
				if (box.isEnabled()) {
					try {
						float value = Float.parseFloat(box.getText());

						Element oldElement = inputBinding.get(box);
						Element newElement = DOM.createElement("mi");
						oldElement.getParentElement().replaceChild(newElement,
								oldElement);
						// oldElement.setInnerText(value+"");
						newElement.setInnerText(value + "");
					} catch (NumberFormatException e) {
						Window.alert("All values should be numbers (except for unknown variable to find)");
						return;
					}
				}
			}
			
			Window.alert("Not Working, see ScienceBrowser");
			//TODO deal with equation
//			Moderator.switchToAlgebra(labelSumEq.getElement()
//					.getFirstChildElement(), equationBrowser.inEditMode, true);
		}

	}
}
