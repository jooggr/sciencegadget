package com.sciencegadgets.util.equationWriter;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Symbol extends HorizontalPanel{
	Label label;
	Image image;
	String utf8;
	int size;
	boolean isLoaded = false;
	VerticalPanel left = new VerticalPanel();
	VerticalPanel right = new VerticalPanel();
	HorizontalPanel top = new HorizontalPanel();
	HorizontalPanel bottom = new HorizontalPanel();
	ClickHandler invokeContextMenu;
	
	public Symbol(String u){
		//image = new Image(url);
		utf8 = u;
		size = 10;
		label = new Label(utf8);
		right.add(top);
		right.add(bottom);
		left.add(label);
		
		this.add(left);
		this.add(right);
		
		this.getElement().getStyle().setPosition(Position.ABSOLUTE);
		setFont(size);
		setOnClick();
		addDomHandler(invokeContextMenu, ClickEvent.getType());
		
		top.getElement().getStyle().setWidth(10, Unit.PX);
		top.getElement().getStyle().setHeight(10, Unit.PX);
		top.getElement().getStyle().setBackgroundColor("green");
		top.addDomHandler(invokeKeyboard, ClickEvent.getType());
	}
	
	public void onLoad(){
		this.getElement().getStyle().setPosition(Position.ABSOLUTE);
	}
	
	private void setSize(int w, int h){
		//image.setPixelSize(w, h);
	}
	
	void setFont(int s){
		label.getElement().getStyle().setFontSize(s, Unit.EM);
	}
	
	private int getHeight(){
		return image.getHeight();
	}
	
	private int getWidth(){
		return image.getWidth();
	}
	
	public Symbol getSuperScript(){
		return (Symbol)top;
	}

	public void setSuperScript(Symbol s){
		//int h = this.getHeight();
		//int w = this.getWidth();
		//s.setSize(w/4, h/4);
		
		s.setFont(size/4);
		bottom.removeFromParent();
		top.removeFromParent();
		top = (HorizontalPanel)s;
		right.add(top);
		right.add(bottom);
		
	}
	
	public void setSubScript(Symbol s){
		//int h = this.getHeight();
		//int w = this.getWidth();
		//s.setSize(w/4, h/4);
		s.setFont(size/4);
		bottom.removeFromParent();
		top.removeFromParent();
		bottom = (HorizontalPanel)s;
		right.add(top);
		right.add(bottom);
		bottom.getElement().getStyle().setBottom(0, Unit.PX);
		bottom.getElement().getStyle().setPosition(Position.ABSOLUTE);
	}
	
	//public abstract void setOnClick();
	
	
	public void setOnClick(){
		invokeContextMenu = new ClickHandler(){
			public void onClick(ClickEvent event){
				event.stopPropagation();
				Widget w = (Widget) event.getSource();
				w.getElement().getStyle().setBackgroundColor("red");
				new ContextMenu(event);
			}
		};
	}
	
	ClickHandler invokeKeyboard = new ClickHandler(){
		public void onClick(ClickEvent event){
			event.stopPropagation();
			Widget w = (Widget) event.getSource();
			w.getElement().getStyle().setBackgroundColor("gray");
			RootPanel.get().add(new EquationKeyboard(w));
		}
	};
	
	
}