package com.admin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.TextBox;

public class Admin implements EntryPoint {

	TextBox inputBox = new TextBox();
	AppEngineData data = new AppEngineData();


	@Override
	public void onModuleLoad() {
		
		try {
			TestBot_Addition.deployTestBot();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		Button saveButton = new Button("Save");
//		RootPanel.get().add(inputBox);
//		RootPanel.get().add(saveButton);
//
//		saveButton.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				data.saveEquation(inputBox.getText());
//			}
//		});

	}

}
