package com.sciencegadgets.client;

import com.google.gwt.dom.client.Element;


public class JSNICalls {

	public static native void parseMathJax(String areaId) /*-{
		$doc.prettify(areaId);
	}-*/;
	
	public static native void parseMathJax(Element element) /*-{
		$doc.prettify(element);
	}-*/;

	public static native double getElementWidth(Element elm) /*-{

		return elm.getBoundingClientRect().width;
		//		return elm.getBBox().width;
	}-*/;

	public static native double getElementHeight(Element elm) /*-{
		return elm.getBoundingClientRect().height;
		//		return elm.getBBox().height;
	}-*/;
	
	public static native void setAttributeNS(Element element,
			String nameSpace, String attribute, String value)/*-{
		element.setAttributeNS(nameSpace, attribute, value)
	}-*/;

	public static native Element createElementNS(final String ns,
			final String name)/*-{
		return document.createElementNS(ns, name);
	}-*/;
	
	public static native void consoleLog( String message) /*-{
      console.log( "me: " + message );
  }-*/;

}
