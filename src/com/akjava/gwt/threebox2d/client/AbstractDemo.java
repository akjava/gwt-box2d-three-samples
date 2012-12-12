package com.akjava.gwt.threebox2d.client;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;

public abstract class AbstractDemo implements Box2dDemo{
protected  World world;

@Override
public World getWorld() {
	return world;
}

public void clearItems(){
if(world!=null){
	List<Body> bodies=new ArrayList<Body>();
	for(Body b=world.getBodyList();b!=null;b=b.getNext()){
		bodies.add(b);
	}
	for(Body body:bodies){
		world.destroyBody(body);
	}	
}
}

@Override
public void step() {
	float dt=(float) (1.0/20);
	int velocityIterations=6;
	int positionIterations=2;
	world.step(dt, velocityIterations, positionIterations);
}

public Body createBlock(float left, float top, float width,
		float height) {
		if(height<=0 || width<=0){
			throw new RuntimeException("width and height must larger than 0");
		}
			BodyDef groundBodyDef = new BodyDef();
			//groundBodyDef.type=BodyType.DYNAMIC;
		    groundBodyDef.position.set(left+width/2, top+height/2);
		    Body groundBody = world.createBody(groundBodyDef);
		    
		       PolygonShape shape = new PolygonShape();
		       shape.setAsBox((float)width/2,(float)height/2);
		       
		       groundBody.createFixture(shape,0);
		       
		       return groundBody;
		}

@Override
public void KeyUp(KeyUpEvent event) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyDown(KeyDownEvent event) {
	// TODO Auto-generated method stub
	
}
}
