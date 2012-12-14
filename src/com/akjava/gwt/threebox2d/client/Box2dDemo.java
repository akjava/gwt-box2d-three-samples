package com.akjava.gwt.threebox2d.client;

import org.jbox2d.dynamics.World;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.user.client.ui.Widget;

public interface Box2dDemo {
	public void initialize();
	public void didpose();
	public void step();
	public void KeyUp(KeyUpEvent event);
	public void keyDown(KeyDownEvent event);
	public String getName();
	
	public World getWorld();
	public Widget createControler();
}
