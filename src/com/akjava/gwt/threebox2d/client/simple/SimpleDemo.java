package com.akjava.gwt.threebox2d.client.simple;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import com.akjava.gwt.threebox2d.client.Box2dDemo;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;

public class SimpleDemo implements Box2dDemo{
private World world;
	@Override
	public void initialize() {
		
		if(world!=null){
			List<Body> bodies=new ArrayList<Body>();
			for(Body b=world.getBodyList();b!=null;b=b.getNext()){
				bodies.add(b);
			}
			for(Body body:bodies){
				world.destroyBody(body);
			}
			
		}
		
		Vec2 gravity=new Vec2(0,10);
		world = new World(gravity, true);
		
		
		createBlock(160,300,320,60);//base block
		
		FixtureDef fd = new FixtureDef();
    	PolygonShape sd = new PolygonShape();
        sd.setAsBox(10f, 10f);
        fd.shape = sd;
        fd.density = 1;
        fd.friction=0f;
        fd.restitution=1;
        BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
        bd.position.set(320, 0);
		
        Body body = world.createBody(bd);
        body.createFixture(fd);
		
	}

	@Override
	public void didpose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void step() {
		float dt=(float) (1.0/30);
		int velocityIterations=6;
		int positionIterations=2;
		world.step(dt, velocityIterations, positionIterations);
	}

	@Override
	public void KeyUp(KeyUpEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyDown(KeyDownEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Simple";
	}

	@Override
	public World getWorld() {
		return world;
	}
	
	
	public Body createBlock(int left, int top, int width,
			int height) {
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

}
