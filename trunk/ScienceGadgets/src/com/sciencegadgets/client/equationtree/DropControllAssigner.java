package com.sciencegadgets.client.equationtree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.allen_sauer.gwt.dnd.client.DragController;
import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.sciencegadgets.client.algebramanipulation.MLElementWrapper;
import com.sciencegadgets.client.algebramanipulation.dropcontrollers.AbstractMathDropController;
import com.sciencegadgets.client.equationtree.JohnTree.JohnNode;
import com.sciencegadgets.client.equationtree.JohnTree.Type;

public class DropControllAssigner {

	// private JohnNode jNode;

	public DropControllAssigner(LinkedList<MLElementWrapper> wrappers,
			Boolean hasJoiner) {
		assign(wrappers, hasJoiner);

	}

	public static void assign(LinkedList<MLElementWrapper> wrappers,
			Boolean hasJoiner) {
		// MathMLDropController dropC = null;
		// MathMLDropController dropCJ = null;
		// System.out.println("\n\n");
		JohnNode jNode;
		for (MLElementWrapper wrap : wrappers) {
			jNode = wrap.getJohnNode();

			if (Type.Number.equals(jNode.getType())) {
				List<JohnNode> siblings = jNode.getParent().getChildren();

				for (JohnNode sib : siblings) {
					if (Type.Number.equals(sib.getType()) && !jNode.equals(sib)) {

						wrap.addDropTarget(sib.getWrapper());
						if (hasJoiner) {
							wrap.getJoinedWrapper().addDropTarget(
									sib.getWrapper().getJoinedWrapper());
						}
					}
				}
			}
			// if (dropC != null) {
			// DropControllers.put(dropC, wrap.getDragControl());
			// if (hasJoiner) {
			// DropControllers.put(dropCJ,
			// wrap.getJoinedWrapper().getDragControl());
			// }
			// }
		}
	}
}