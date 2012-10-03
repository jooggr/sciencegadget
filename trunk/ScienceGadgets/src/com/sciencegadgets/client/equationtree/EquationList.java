package com.sciencegadgets.client.equationtree;

import java.util.LinkedList;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.sciencegadgets.client.JSNICalls;
import com.sciencegadgets.client.algebramanipulation.MLElementWrapper;
import com.sciencegadgets.client.equationtree.MathMLBindingTree.MathMLBindingNode;

public class EquationList {
	private MathMLBindingTree mathMLBindingTree;
	private AbsolutePanel panel;
	private Grid eqList = new Grid(1, 1);
	private Timer timer;
	private LinkedList<LinkedList<MathMLBindingNode>> nodeLayers = new LinkedList<LinkedList<MathMLBindingNode>>();
	private HTML responseNotes = new HTML("notes");

	public EquationList(AbsolutePanel panel, final MathMLBindingTree jTree) {

		this.panel = panel;
		this.mathMLBindingTree = jTree;
		
		this.panel.add(eqList);
		eqList.setWidth(panel.getOffsetWidth() + "px");
		eqList.setStyleName("textCenter");
		responseNotes.setSize(eqList.getOffsetWidth()+"px", "40px");
		eqList.setWidget(0, 0, responseNotes);
		
		
		fillNextNodeLayer(mathMLBindingTree.getLeftSide(), 0);
		fillNextNodeLayer(mathMLBindingTree.getRightSide(), 0);

		// Wait for mathjax to format first
		timer = new Timer() {
			public void run() {
				checkIfWeCanDraw();
			}
		};
		timer.scheduleRepeating(100);
	}

	private void checkIfWeCanDraw() {
		String eqId = "svg" + mathMLBindingTree.getEquals().getId();
		Element eqEl = DOM.getElementById(eqId);
		if (eqEl != null) {
			timer.cancel();
			draw(mathMLBindingTree);
		}
	}

	public void draw(MathMLBindingTree jTree) {

		for (int i = 1; i < nodeLayers.size() + 1; i++) {
			Node nextEq = mathMLBindingTree.getMathML().getElement()
					.getFirstChild().cloneNode(true);

			replaceChildsId(nextEq, i);
			HTML eq = new HTML();
			eq.getElement().appendChild(nextEq);

			int rowCount = eqList.getRowCount() + 1;
			eqList.resizeRows(rowCount);
			eqList.setWidget(rowCount - 1, 0, eq);
		}
		
		placeNextEqWrappers(0);
	}
	
	
	///////////////////////////////////////////////////////////////
	// Details
	//////////////////////////////////////////////////////////////
	
	private void fillNextNodeLayer(MathMLBindingNode parent, int layer){
		LinkedList<MathMLBindingNode> children = parent.getChildren();

		if(nodeLayers.size()<layer+1){
			nodeLayers.add(new LinkedList<MathMLBindingNode>());
		}
		
		nodeLayers.get(layer).addAll(children);
		
		for(int i=0 ; i<children.size() ; i++){
			MathMLBindingNode curChild = children.get(i);
			
//			if(curChild.getChildCount() > 0){
			String curTag = curChild.getTag();
			if(!curTag.equals("mo") && !curTag.equals("mn") && !curTag.equals("mi")){
				fillNextNodeLayer(curChild, layer + 1);
			}
		}
	}
	
	private void placeNextEqWrappers(int layer){
		LinkedList<MathMLBindingNode> nodes = nodeLayers.get(layer);
		
		for(MathMLBindingNode node : nodes){
			String bareId = node.getId();
			
			Element el = DOM.getElementById(layer+1+"-svg"+bareId);
			
			el.setAttribute("style", "fill:red");

			String height = JSNICalls.getHeight((com.google.gwt.user.client.Element) el)+"px";
			String width = JSNICalls.getWidth((com.google.gwt.user.client.Element) el)+"px";

			MLElementWrapper wrap = node.getWrapper().getJoinedWrapper();
			wrap.setHeight(height);
			wrap.setWidth(width);
			
			panel.add(wrap, el.getAbsoluteLeft()-panel.getAbsoluteLeft(), el.getAbsoluteTop()-panel.getAbsoluteTop());
		}
		if(nodeLayers.size() > layer+1){
		placeNextEqWrappers(layer+1);
	}}
	
	/**
	 * Each equation must have a different set of ID's which only differ in the prefix. The prefix is the equations placement in the list
	 * @param parent
	 * @param eqRow
	 */
	private void replaceChildsId(Node parent, int eqRow) {
		NodeList<Node> children = parent.getChildNodes();

		for (int i = 0; i < children.getLength(); i++) {
			Element curEl = ((Element) children.getItem(i));
			String oldId = curEl.getAttribute("id");

			// Each equation in the list will have a different prefix for id's
			// [equation #]-svg... example 1-svg0
			if (oldId.contains("svg")) {
				if (oldId.equals("svg0")) {
					resizeEquations(curEl);
				}
				String newId = oldId.replaceFirst("svg", eqRow + "-svg");
				curEl.setAttribute("id", newId);
				
				// Each equation will have a different MathJax frame id
				// MathJax-Element-[equation #]-Frame
			} else if (oldId.contains("MathJax-Element")) {
				String newId = "MathJax-Element-" + (eqRow + 1) + "-Frame";
				curEl.setAttribute("id", newId);
			}

			if (!children.getItem(i).getNodeName().equalsIgnoreCase("script")) {
				replaceChildsId(children.getItem(i), eqRow);
			}
		}
	}
/**
 * Gives the top node of each equation a certain size
 * @param el
 */
	private void resizeEquations(Element el) {
		String widthAnchor = "-widthAnchor-";
		String heightAnchor = "-heightAnchor-";
		double width = 0;
		double height = 0;

		String entireStyle = el.getAttribute("style");
		entireStyle = entireStyle.replaceAll(" ", "");
		String[] styles = entireStyle.split(";");

		// get old width and height
		for (int i = 0; i < styles.length; i++) {
			if (styles[i].startsWith("width")) {
				styles[i] = styles[i].replaceFirst("width:", "").replaceFirst(
						"ex", "");
				width = Double.parseDouble(styles[i]);
				styles[i] = "width: " + widthAnchor + "px";
			} else if (styles[i].startsWith("height")) {
				styles[i] = styles[i].replaceFirst("height:", "").replaceFirst(
						"ex", "");
				height = Double.parseDouble(styles[i]);
				styles[i] = "height: " + heightAnchor + "px";
			}
		}

		// replace width and height
		String newStyle = "";
		for (String style : styles) {
			newStyle = newStyle + "; " + style;
		}
		newStyle = newStyle.replaceFirst("; ", "");

		//Width will always be 1/2 the panel, height is calculated from width
		double newWidth = panel.getOffsetWidth()/2;
		double newHeight = height * (newWidth / width);

		newStyle = newStyle.replaceFirst(widthAnchor, "" + (newWidth));
		newStyle = newStyle.replaceFirst(heightAnchor, "" + (newHeight));

		el.setAttribute("style", newStyle);

	}
}