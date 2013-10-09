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
package com.sciencegadgets.client.algebra;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;
import com.sciencegadgets.client.algebra.MathTree.MathNode;

public class WrapDragController extends PickupDragController {

	LinkedList<DropController> dropControllers = null;

	public WrapDragController(AbsolutePanel boundaryPanel,
			boolean allowDroppingOnBoundaryPanel) {
		super(boundaryPanel, allowDroppingOnBoundaryPanel);

		this.setBehaviorDragStartSensitivity(5);
		this.setBehaviorDragProxy(true);
	}

	@Override
	public void dragEnd() {
		super.dragEnd();

		for (DropController dropC : dropControllers) {
			dropC.getDropTarget().removeStyleName("selectedWrapper");
		}
	}

	@Override
	public void dragStart() {
		super.dragStart();

		if (AlgebraActivity.isInEasyMode) {
			for (DropController dropC : dropControllers) {
				dropC.getDropTarget().addStyleName("selectedWrapper");
			}
		}
	}

	@Override
	public void registerDropController(DropController dropC) {
		super.registerDropController(dropC);
		if (dropControllers == null) {
			dropControllers = new LinkedList<DropController>();
		}
		dropControllers.add(dropC);
	}

	@Override
	public void unregisterDropControllers() {
		super.unregisterDropControllers();
		if (dropControllers != null) {
			dropControllers.clear();
		}
	}

	@Override
	public void unregisterDropController(DropController dropC) {
		super.unregisterDropController(dropC);
		if (dropControllers != null) {
			dropControllers.remove(dropC);
		}
	}

}