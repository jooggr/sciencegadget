package com.sciencegadgets.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sciencegadgets.client.equationbrowser.EquationDatabase;

public class ScienceGadgets implements EntryPoint {

	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	

	public void onModuleLoad() {

		// /////////////////////////////////////////
		// experimental
		// ////////////////////////////////

		final Button sendButton = new Button("Send");

		RootPanel.get().add(sendButton);
		// RootPanel.get().add(new EquationWriter());

		ClickHandler handler = new ClickHandler() {
			public void onClick(ClickEvent event) {
				//string2MathML_BySymja_OnServer("a+b");
			}
		};

		sendButton.addClickHandler(handler);
	}

	/**
	 * Async call to server to parse the equation using Symja
	 * 
	 * @param textToServer
	 */
	private void string2MathML_BySymja_OnServer(String textToServer) {

		greetingService.greetServer(textToServer, new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				Window.alert("Math parseing FAIL :(");
			}

			public void onSuccess(String result) {
				//labelSumEq.setHTML(result);
				//onEqSelect(result);
			}
		});
	}

}
