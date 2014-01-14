package com.sciencegadgets.client.algebra;

import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sciencegadgets.client.Moderator;
import com.sciencegadgets.client.ToggleSlide;
import com.sciencegadgets.client.algebra.edit.ChangeNodeMenu;

public class OptionsHandler implements ClickHandler {

	public static PopupPanel optionsPopup = new PopupPanel();
	OptionsPanel optionsPanel = new OptionsPanel();
	Button closeOption = new Button("Close",new CloseClickHandler());
	ToggleSlide easyHardOption;
	ToggleSlide editSolveOption;
	AlgebraActivity algebraActivity;

	public OptionsHandler(AlgebraActivity algebraActivity) {
		this.algebraActivity = algebraActivity;
		
		optionsPopup.clear();

		AbsolutePanel mainPanel = Moderator.scienceGadgetArea;

		int optionsButtonHeight = algebraActivity.upperEqArea.getOffsetHeight();
		optionsPopup.getElement().getStyle().setBackgroundColor("white");
		optionsPopup.setPixelSize(mainPanel.getOffsetWidth() / 4,
				mainPanel.getOffsetHeight() - optionsButtonHeight);
		optionsPopup.setPopupPosition(mainPanel.getAbsoluteLeft(),
				mainPanel.getAbsoluteTop() + optionsButtonHeight);
		optionsPopup.getElement().getStyle().setZIndex(10);

		optionsPanel.getElement().getStyle().setOverflowY(Overflow.SCROLL);
		optionsPopup.add(optionsPanel);
		
		optionsPopup.hide();
		optionsPopup.setAutoHideEnabled(true);
	    }

	@Override
	public void onClick(ClickEvent event) {
		optionsPanel.clear();
		
		optionsPanel.add(closeOption);

		if (!algebraActivity.inEditMode) {
			easyHardOption = new ToggleSlide("Easy", "Hard", Moderator.isInEasyMode, new EasyHardClickHandler());
			optionsPanel.add(easyHardOption);
		}
		
		editSolveOption = new ToggleSlide("Edit", "Solve", algebraActivity.inEditMode, new EditSolveClickHandler(algebraActivity));
		optionsPanel.add(editSolveOption);
		
		optionsPopup.show();
	}

}

class OptionsPanel extends FlowPanel{
	@Override
	public void add(Widget w) {
		w.setSize("100%", "15%");
		super.add(w);
	}
}

class CloseClickHandler implements ClickHandler {
	
	@Override
	public void onClick(ClickEvent event) {
		OptionsHandler.optionsPopup.hide();
	}
	
}
class EasyHardClickHandler implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		OptionsHandler.optionsPopup.hide();
		Moderator.isInEasyMode = !Moderator.isInEasyMode;
		Moderator.reloadEquationPanel(null, null);
	}

}

class EditSolveClickHandler implements ClickHandler {
	AlgebraActivity algebraActivity;
	EditSolveClickHandler(AlgebraActivity algebraActivity){
		this.algebraActivity = algebraActivity;
	}
	@Override
	public void onClick(ClickEvent event) {
		String equation = algebraActivity.getMathTree().getRoot().toString();
		if (equation.contains(ChangeNodeMenu.NOT_SET)) {
			Window.alert("All new entities (" + ChangeNodeMenu.NOT_SET
					+ ") must be set or removed before solving");
			return;
		}

		OptionsHandler.optionsPopup.hide();
		if (EquationPanel.selectedWrapper != null) {
			EquationPanel.selectedWrapper.unselect();
		}
		Moderator.makeAlgebra(Moderator.getCurrentMathTree().getMathMLClone(), !algebraActivity.inEditMode);
	}

}
