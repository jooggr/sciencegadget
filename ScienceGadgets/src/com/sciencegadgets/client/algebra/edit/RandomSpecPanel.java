package com.sciencegadgets.client.algebra.edit;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sciencegadgets.client.ui.CSS;

public class RandomSpecPanel extends FlowPanel {

	RandomSpecPanel randSpec = this;

	public static final String ALWAYS = "A";
	public static final String SOMETIMES = "S";
	public static final String NEVER = "N";
	
	public static final String RANDOM_SYMBOL = "?";
	public static final String DELIMITER = "_";

	private RadioButton neverNeg;
	private RadioButton sometimesNeg;
	private RadioButton alwaysNeg;
	private DoubleBox upparBound;
	private DoubleBox lowerBound;

	private IntegerBox decPlace;
	private Label response;
	Button setRandonButton = new Button("Set Random");

	public RandomSpecPanel() {

		this.getStyleElement().getStyle().setBackgroundColor("#ADD850");
		this.setWidth("100%");
		this.setHeight("100%");
		
		VerticalPanel mainPanel = new VerticalPanel();
		ScrollPanel scrollPanel = new ScrollPanel(mainPanel);

		Label label1 = new Label("Negativity");
		label1.setStyleName(CSS.ROW_HEADER);
		mainPanel.add(label1);

		neverNeg = new RadioButton("neg", "Never");
		sometimesNeg = new RadioButton("neg", "Sometimes");
		alwaysNeg = new RadioButton("neg", "Always");
		HorizontalPanel negativeSelection = new HorizontalPanel();
		negativeSelection.add(neverNeg);
		negativeSelection.add(sometimesNeg);
		negativeSelection.add(alwaysNeg);
		mainPanel.add(negativeSelection);

		Label label2 = new Label("Range");
		label2.setStyleName(CSS.ROW_HEADER);
		label2.getElement().getStyle().setMarginTop(5, Unit.PX);
		mainPanel.add(label2);

		HorizontalPanel rangeSelection = new HorizontalPanel();
		lowerBound = new DoubleBox();
		lowerBound.setSize("50px", "30px");
		rangeSelection.add(lowerBound);
		rangeSelection.add(new Label("\u2264 x \u003C"));
		upparBound = new DoubleBox();
		upparBound.setSize("50px", "30px");
		rangeSelection.add(upparBound);
		mainPanel.add(rangeSelection);

		Label label3 = new Label(
				"Decimal places");
		label3.setStyleName(CSS.ROW_HEADER);
		label3.getElement().getStyle().setMarginTop(5, Unit.PX);
		mainPanel.add(label3);

		decPlace = new IntegerBox();
		decPlace.setSize("50px", "30px");
		decPlace.setText("0");
		mainPanel.add(decPlace);

		setRandonButton.setSize("100%", "100px");
		mainPanel.add(setRandonButton);

		response = new Label("");
		response.getElement().getStyle().setColor("red");
		mainPanel.add(response);

		this.add(scrollPanel);

	}
	
	public void addOkClickHandler(ClickHandler handler) {
		setRandonButton.addClickHandler(handler);
	}

	public String getRandomness() {

			String neg = null;
			if (alwaysNeg.getValue()) {
				neg = ALWAYS;
			} else if (sometimesNeg.getValue()) {
				neg = SOMETIMES;
			} else if (neverNeg.getValue()) {
				neg = NEVER;
			}

			Double upperB = upparBound.getValue();
			Double lowerB = lowerBound.getValue();

			Integer decP = decPlace.getValue();
			
			//Only proceed if all the input was valid
			if (neg == null || upperB == null || lowerB == null || decP == null) {
				response.setText("All the fields must be filled");
				return null;
			} else if (upperB < lowerB) {
				response.setText("The upper bound must be greater than the lower bound");
				return null;
			} else if (upperB < 0 || lowerB < 0) {
				response.setText("The bounds shouldn't be negative");
				return null;
			} else {
				response.setText("");
				return neg + DELIMITER + lowerB + DELIMITER + upperB + DELIMITER + decP;
			}

	}

}