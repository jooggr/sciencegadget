package com.sciencegadgets.client.algebra.edit;

import java.util.HashMap;
import java.util.NoSuchElementException;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sciencegadgets.client.Moderator;
import com.sciencegadgets.client.algebra.EquationPanel;
import com.sciencegadgets.client.algebra.MathMLBindingTree;
import com.sciencegadgets.client.algebra.Wrapper;
import com.sciencegadgets.client.algebra.MathMLBindingTree.MathMLBindingNode;
import com.sciencegadgets.client.algebra.MathMLBindingTree.Operator;
import com.sciencegadgets.client.algebra.MathMLBindingTree.Type;

public class ChangeNodeMenu extends FlowPanel {

	public static final String NOT_SET = "???";
	private HashMap<Type, Button> menuMap = new HashMap<Type, Button>();
	private Button removeButton;

	public ChangeNodeMenu() {

		setSize("100%", "100%");

		Type[] types = MathMLBindingTree.Type.values();
		
		double buttonWidthPercent = 100.0 / types.length;

		// Change buttons
		for (MathMLBindingTree.Type type : types) {
			if (Type.Operation.equals(type))
				continue;

			ChangeNodeHandler changeHandler = new ChangeNodeHandler(type);
			Button changeButton = new Button(type.toString(), changeHandler);
			changeButton.setSize(buttonWidthPercent+"%", "100%");
			menuMap.put(type, changeButton);
			this.add(changeButton);
		}

		// Remove button
		RemoveNodeHandler removeHandler = new RemoveNodeHandler();
		Button removeButton = new Button("Remove", removeHandler);
		removeButton.setSize(buttonWidthPercent+"%", "100%");
		removeButton.getElement().getStyle().setColor("red");
		this.removeButton = removeButton;
		this.add(removeButton);
	}

		


	public Button getButton(Type menuOption) {
		Button button;

		if (menuOption == null) {
			button = removeButton;
		} else {
			button = menuMap.get(menuOption);
		}
		return button;
	}

	public void setEnable(Type menuOption, boolean isEnabled) {
		Button button = getButton(menuOption);
		if(button != null){
		button.setEnabled(isEnabled);
	}}

	public boolean isEnabled(Type menuOption) {
		Button button = getButton(menuOption);
		return button.isEnabled();
	}

	private class RemoveNodeHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
//			EditWrapper selectedWrapper = (EditWrapper) Wrapper.selectedWrapper;
			
			if (EquationPanel.selectedWrapper != null) {
				MathMLBindingNode node = EquationPanel.selectedWrapper.getNode();
				MathMLBindingNode parent = node.getParent();

				switch (parent.getType()) {
				case Term:
				case Sum:
					if (parent.getChildCount() > 4) {
						try {
							MathMLBindingNode prevSib = node.getPrevSibling();
							if (Type.Operation.equals(prevSib.getType()))
								prevSib.remove();
						} catch (IndexOutOfBoundsException e) {
							MathMLBindingNode nextSib = node.getNextSibling();
							if (Type.Operation.equals(nextSib.getType()))
								nextSib.remove();
						}
						node.remove();
						Moderator.reloadEquationPanel("");
					} else {
						Label responseNotes = new Label(
								"You should just change the parent");
						((EditWrapper) EquationPanel.selectedWrapper).getEditMenu().setResponse(responseNotes);
					}
					break;
				case Exponential:
				case Fraction:
					MathMLBindingNode newParent = parent.getParent();
					
					switch (node.getIndex()) {
					case 0:
						newParent.add(parent.getIndex(), node.getNextSibling());
						break;
					case 1:
						newParent.add(parent.getIndex(), node.getPrevSibling());
						break;
					}
					parent.remove();
					Moderator.reloadEquationPanel("");
					break;
				}
			}
		}
	}

	private class ChangeNodeHandler implements ClickHandler {
		MathMLBindingTree.Type type;

		ChangeNodeHandler(MathMLBindingTree.Type type) {
			this.type = type;
		}

		@Override
		public void onClick(ClickEvent event) {
//			EditWrapper selectedWrapper = (EditWrapper) EquationPanel.selectedWrapper;

			if (EquationPanel.selectedWrapper != null) {
				MathMLBindingNode node = EquationPanel.selectedWrapper.getNode();
				MathMLBindingNode parent = node.getParent();
				int index = node.getIndex();

				try {
					Operator operator = null;

					switch (type) {
					case Number:
						parent.add(node.getIndex(), type, "1");
						node.remove();
						break;
					case Variable:
						parent.add(node.getIndex(), type, "x");
						node.remove();
						break;

					case Sum:
						operator = Operator.PLUS;
					case Term:
						if (operator == null)
							operator = Operator.getMultiply();
					case Exponential:
					case Fraction:
						MathMLBindingNode newNode = Moderator.jTree.NEW_NODE(
								type, "");

						newNode.add(node);
						if (operator != null){
							newNode.add(-1, Type.Operation, operator.getSign());
						}
						newNode.add(-1, Type.Variable, NOT_SET);
						parent.add(index, newNode);

					}
					Moderator.reloadEquationPanel("");
					
				} catch (NoSuchElementException e) {
					e.printStackTrace();
				}
			}
		}
	}
}