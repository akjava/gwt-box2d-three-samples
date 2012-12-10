package com.akjava.gwt.threebox2d.client;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class Main implements EntryPoint {

	private World world;


	@Override
	public void onModuleLoad() {
		final Canvas canvas=Canvas.createIfSupported();
		canvas.setSize("100px", "100px");
		canvas.setCoordinateSpaceWidth(100);
		canvas.setCoordinateSpaceHeight(100);
		RootPanel.get().add(canvas);
		
		Vec2 gravity=new Vec2(0,10);
		world = new World(gravity, true);
		
		createBlock(0,46,32,6,0);//base block
		
		
		FixtureDef fd = new FixtureDef();
    	PolygonShape sd = new PolygonShape();
        sd.setAsBox(0.125f, 2f);
        fd.shape = sd;
        fd.density = 25.0f;
        fd.friction=.5f;
        BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
       
        bd.position.set(0, 0);
		
        final Body body = world.createBody(bd);
        body.createFixture(fd);
		//final Body body=createBlock(0,0,1,1,1);
		
		Timer timer=new Timer(){
			
			public void run(){
				canvas.getContext2d().clearRect(0, 0, 100, 100);
				canvas.getContext2d().setFillStyle("#800");
				float x=body.getPosition().x;
				float y=body.getPosition().y;
				canvas.getContext2d().fillRect(x, y, 2, 2);
				
				float dt=(float) (1.0/30);
				int velocityIterations=6;
				int positionIterations=2;
				world.step(dt, velocityIterations, positionIterations);
				
				
				
				
			}
		};
		timer.scheduleRepeating(1000/30);
	}

	
	public Body createBlock(int x, int y, int width,
			int height,float density) {
				BodyDef groundBodyDef = new BodyDef();
				//groundBodyDef.type=BodyType.DYNAMIC;
			    groundBodyDef.position.set(x+width/2, y+height/2);
			    Body groundBody = world.createBody(groundBodyDef);
			    
			       PolygonShape shape = new PolygonShape();
			       shape.setAsBox(width/2,height/2);
			       
			       groundBody.createFixture(shape,density);
			       
			       return groundBody;
			}
}
