package com.akjava.gwt.threebox2d.client.demo.simple;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import com.akjava.gwt.threebox2d.client.AbstractDemo;
import com.google.gwt.user.client.ui.Widget;

public class SimpleDemo extends AbstractDemo{

	@Override
	public void createItems() {
		
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


	@Override
	public Widget createControler() {
		// TODO Auto-generated method stub
		return null;
	}



}
