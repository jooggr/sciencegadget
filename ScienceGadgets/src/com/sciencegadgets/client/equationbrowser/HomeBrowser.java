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

import java.util.ArrayList;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.sciencegadgets.client.Moderator.ActivityType;
import com.sciencegadgets.client.ui.CSS;
import com.sciencegadgets.client.ui.CommunistPanel;
import com.sciencegadgets.client.ui.FitParentHTML;
import com.sciencegadgets.client.ui.SelectionButton;

public class HomeBrowser extends FlowPanel {

	// CommunistPanel activitySelectionPanel = new CommunistPanel(false);
	SimplePanel activityDetailsPanel = new SimplePanel();

	AlgebraBrowser algebraBrowser = new AlgebraBrowser("Algebra Practice",
			ActivityType.interactiveequation);
	VideoPanel videoPanel = new VideoPanel();
	RandomEquationPanel generatePanel = new RandomEquationPanel();
	ConversionSpecification conversionSpec = new ConversionSpecification();
	// public ChallengeBrowser challengeBrowser = new ChallengeBrowser();
	public MakeEquationBrowser makeEquationBrowser = new MakeEquationBrowser();
	// ScienceBrowser scienceBrowser = new ScienceBrowser(this);

//	static ActivityButton selectedActivityButton = null;

	public HomeBrowser() {

		// Set up browsers
		this.getElement().setId(CSS.HOME_BROWSER);

		// Anchor title = new Anchor("ScienceGadgets", "/project/index.html");
		HTMLPanel title = new HTMLPanel("h1", "ScienceGadgets");
		title.addStyleName(CSS.MAIN_TITLE);
//		this.add(title);

//		HTMLPanel headline = new HTMLPanel(
//				"h2",
//				"A set of <a href=\"https://github.com/gralyan/ScienceGadgets\">Open Source</a>"
//						+ " educational tools dedicated to helping redesign the next generation of learning strategies");
		Label headline = new Label("sdgh");
		headline.addStyleName(CSS.MAIN_HEADLINE);
		this.add(headline);
		
//		this.add(videoPanel);

		// activitySelectionPanel.getElement().setId(CSS.ACTIVITY_SELECTION_PANEL);
		// this.add(activitySelectionPanel);

//		activityDetailsPanel.getElement().setId(CSS.ACTIVITY_DETAILS_PANEL);
//		this.add(activityDetailsPanel);

//		ArrayList<ActivityButton> activityButtons = new ArrayList<HomeBrowser.ActivityButton>();
//
//		// Add buttons
//
//		ActivityButton defaultButton = new ActivityButton("Introduction",
//				videoPanel);
//		activityButtons.add(defaultButton);
//
//		activityButtons.add(new ActivityButton("Make Equation",
//				makeEquationBrowser));
//
//		activityButtons
//				.add(new ActivityButton("Random Equation", generatePanel));
//
//		activityButtons.add(new ActivityButton("Conversion", conversionSpec));
//
//		// activitySelectionPanel.addAll(activityButtons);
//
//		// Add initial detail
//		defaultButton.onSelect();
	}

//	class ActivityButton extends SelectionButton {
//		Widget activityDetails;
//
//		ActivityButton(String title, Widget activityDetails) {
//			super(title);
//			// this.add(new FitParentHTML(title));
//			this.activityDetails = activityDetails;
//			addStyleName(CSS.ACTIVITY_SELECTION_BUTTON);
//		}
//
//		@Override
//		protected void onSelect() {
//			Widget currentWidget = activityDetailsPanel.getWidget();
//			if (activityDetails != currentWidget) {
//				if (currentWidget != null) {
//					currentWidget.removeFromParent();
//				}
//				activityDetailsPanel.add(activityDetails);
//			}
//			if (this != selectedActivityButton) {
//				if (selectedActivityButton != null) {
//					selectedActivityButton
//							.removeStyleName(CSS.ACTIVITY_SELECTION_BUTTON_SELECTED);
//					selectedActivityButton
//							.addStyleName(CSS.TRANSFORMATION_BUTTON);
//					selectedActivityButton
//							.addStyleName(CSS.ACTIVITY_SELECTION_BUTTON);
//				}
//				this.removeStyleName(CSS.ACTIVITY_SELECTION_BUTTON);
//				this.removeStyleName(CSS.TRANSFORMATION_BUTTON);
//				this.addStyleName(CSS.ACTIVITY_SELECTION_BUTTON_SELECTED);
//				selectedActivityButton = this;
//			}
//		}
//
//	}

}
