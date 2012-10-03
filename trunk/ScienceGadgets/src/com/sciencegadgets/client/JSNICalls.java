package com.sciencegadgets.client;

import com.google.gwt.user.client.Element;

public class JSNICalls {

	public static native void parseMathJax(String areaId) /*-{
		$doc.prettify(areaId);
	}-*/;

	public static native double getWidth(Element elm) /*-{

		return elm.getBoundingClientRect().width;
		//		return elm.getBBox().width;
	}-*/;

	public static native double getHeight(Element elm) /*-{
		return elm.getBoundingClientRect().height;
		//		return elm.getBBox().height;
	}-*/;
}