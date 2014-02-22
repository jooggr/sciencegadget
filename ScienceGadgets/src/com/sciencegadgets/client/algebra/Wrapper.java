package com.sciencegadgets.client.algebra;

import java.util.LinkedList;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasTouchEndHandlers;
import com.google.gwt.event.dom.client.HasTouchStartHandlers;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;
import com.sciencegadgets.client.CSS;
import com.sciencegadgets.client.Moderator;
import com.sciencegadgets.client.algebra.MathTree.MathNode;
import com.sciencegadgets.client.algebra.transformations.AssociativeDropController;
import com.sciencegadgets.client.conversion.ConversionWrapper;
import com.sciencegadgets.client.conversion.ReorderDropController;
import com.sciencegadgets.shared.TypeML;

public class Wrapper extends HTML implements HasClickHandlers,
		HasTouchStartHandlers, HasTouchEndHandlers {
	
	protected MathNode node;
	protected AbsolutePanel parentPanel;
	protected WrapDragController dragController = null;
	public boolean moved = false;

	public Wrapper(MathNode node, final AbsolutePanel parentPanel,
			Element element) {
		super(element);

		this.node = node;
		this.parentPanel = parentPanel;

		onAttach();

		node.wrap(this);

		// zIndex eqPanel=1 wrapper=2 menu=3
		this.getElement().getStyle().setZIndex(2);

		if (Moderator.isTouch) {
			addTouchEndHandler(new WrapperTouchEndHandler());
		} else {
			addClickHandler(new WrapperClickHandler());
		}

	}

	public MathNode getNode() {
		return node;
	}

	public Wrapper getNextSiblingWrapper() throws IndexOutOfBoundsException {
		return node.getNextSibling().getWrapper();
	}

	public Wrapper getPrevSiblingWrapper() throws IndexOutOfBoundsException {
		return node.getPrevSibling().getWrapper();
	}

	public Wrapper getParentWrapper() {
		return node.getParent().getWrapper();
	}

	public void select() {
		this.getElement().addClassName(CSS.SELECTED_WRAPPER);
	}

	public void unselect() {
		this.getElement().removeClassName(CSS.SELECTED_WRAPPER);
	}

	@Override
	protected void onUnload() {
		removeDropTargets();
		removeDragController();
		dragController = null;
		super.onUnload();
	}

	// /////////////////////////////////////////////////////////////////////
	// Drag & Drop
	// ////////////////////////////////////////////////////////////////////

	public void addAssociativeDragDrop() {

		// Add associative drag and drop
		if ((TypeML.Sum.equals(node.getParentType()) || TypeML.Term.equals(node
				.getParentType())) && !TypeML.Operation.equals(node.getType())) {

			addDragController();

			LinkedList<MathNode> siblings = node.getParent().getChildren();
			siblings.remove(node);
			for (MathNode dropNode : siblings) {
				if (!TypeML.Operation.equals(dropNode.getType())) {
					DropController controller = null;
					Wrapper dropWraper = dropNode.getWrapper();
					if (dropWraper instanceof ConversionWrapper) {
						controller = new ReorderDropController(
								(ConversionWrapper) dropWraper);
					} else if (dropWraper instanceof EquationWrapper) {
						controller = new AssociativeDropController(
								(EquationWrapper) dropWraper);
					} else {
						continue;
					}
					dragController.registerDropController(controller);
				}
			}
		}
	}

	public WrapDragController getDragControl() {
		return dragController;
	}

	public WrapDragController addDragController() {

		if (dragController == null) {
			dragController = new WrapDragController(parentPanel, false);
			dragController.makeDraggable(this);
		}

		return dragController;
	}

	public void removeDragController() {
		if (dragController != null) {
			dragController.makeNotDraggable(this);
			dragController = null;
		}
	}

	public void removeDropTargets() {
		if (dragController != null) {
			dragController.unregisterDropControllers();
		}
	}

	// /////////////////////////////////////////////////////////////////////
	// Inner Classes
	// ////////////////////////////////////////////////////////////////////
	public class WrapperClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			event.stopPropagation();
			System.out.println(" ");
			System.out.println("wrapper "+node.toString());
			System.out.println(" ");
			select();
		}
	}
	
	class WrapperTouchEndHandler implements TouchEndHandler {
		@Override
		public void onTouchEnd(TouchEndEvent event) {
			event.stopPropagation();
			if(!moved) {
				select();
			}
			moved = false;
		}
	}

}
