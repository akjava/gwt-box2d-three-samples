package com.akjava.gwt.threebox2d.client.demo;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import com.akjava.gwt.threebox2d.client.AbstractDemo;
import com.google.gwt.user.client.ui.Widget;

public class EdgeDemo extends AbstractDemo{

	@Override
	public void createItems() {
		
		List<Vec2> points=new ArrayList<Vec2>();
		points.add(new Vec2(25,-10));
		points.add(new Vec2(0,15));
		points.add(new Vec2(30,30));
		points.add(new Vec2(60,15));
		points.add(new Vec2(35,-10));
		points.add(new Vec2(28,-10));
		createEdges(points);
		
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
	
	public Body createEdges(List<Vec2> points) {
				if(points.size()<2){
					throw new RuntimeException("at leaset 2 points needed");
				}
				BodyDef groundBodyDef = new BodyDef();
				Body groundBody = world.createBody(groundBodyDef);
			    
				for(int i=0;i<points.size()-1;i++){
					Vec2 pt1=points.get(i);
					Vec2 pt2=points.get(i+1);
					  PolygonShape shape = new PolygonShape();
					  shape.setAsEdge(pt1.clone(), pt2.clone());
				       groundBody.createFixture(shape,0);
				}
				
			     
			       
			       return groundBody;
			}

	@Override
	public void didpose() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Edge";
	}


	@Override
	public Widget createControler() {
		// TODO Auto-generated method stub
		return null;
	}
}
