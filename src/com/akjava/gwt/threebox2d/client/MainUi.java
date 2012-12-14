package com.akjava.gwt.threebox2d.client;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ButtonGroup;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainUi extends Composite  {

	private static TestUiBinder uiBinder = GWT.create(TestUiBinder.class);

	interface TestUiBinder extends UiBinder<Widget, MainUi> {
	}

	public MainUi() {
		initWidget(uiBinder.createAndBindUi(this));
		
		
		final Button b=(Button) renderer.getWidget(2);//css3d is default
		b.getElement().addClassName("active");// newer bootstrap support it
		b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				//called before set active
				System.out.println(b.getText()+","+b.isToggled()+","+b.getStyleName());
			}
		});
		
	}
@UiField
ButtonGroup renderer;
@UiField
VerticalPanel center,bottom,controler;
public VerticalPanel getControler() {
	return controler;
}
public VerticalPanel getBottom() {
	return bottom;
}
public VerticalPanel getCenter() {
	return center;
}
@UiField 
Button webglButton,canvasButton,css3dButton;
public Button getWebglButton() {
	return webglButton;
}
public Button getCanvasButton() {
	return canvasButton;
}
public Button getCss3dButton() {
	return css3dButton;
}
@UiField 
HorizontalPanel buttons;
public HorizontalPanel getButtons() {
	return buttons;
}


}
