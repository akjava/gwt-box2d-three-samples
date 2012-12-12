package com.akjava.gwt.threebox2d.client.demo.simple;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import com.akjava.gwt.threebox2d.client.AbstractDemo;
import com.akjava.gwt.threebox2d.client.Box2dDemo;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;

public class SimpleDemo extends AbstractDemo{

	@Override
	public void initialize() {
		
		clearItems();
		
		Vec2 gravity=new Vec2(0,10);
		world = new World(gravity, true);//TODO share world
		
		
		createBlock(16.0f,30.0f,32.0f,6f);//base block
		
		FixtureDef fd = new FixtureDef();
    	PolygonShape sd = new PolygonShape();
        sd.setAsBox(1f, 1f);
        fd.shape = sd;
        fd.density = 1;
        fd.friction=0f;
        fd.restitution=1;
        BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
        bd.position.set(30f, 0);
		
        Body body = world.createBody(bd);
        body.createFixture(fd);
		
	}

	@Override
	public void didpose() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Simple";
	}



}
