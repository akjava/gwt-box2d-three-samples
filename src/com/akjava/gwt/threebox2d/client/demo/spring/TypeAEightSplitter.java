package com.akjava.gwt.threebox2d.client.demo.spring;

import java.util.ArrayList;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

public class TypeAEightSplitter extends AbstractEightSplitter {
	
	

	public TypeAEightSplitter(World world,float frequencyHz,float dampingRatio,int sx,int sy,int dw,int dh){
		super(frequencyHz,dampingRatio,sx,sy,dw,dh);
		
		
		ArrayList<Body> particles=new ArrayList<Body>();
		int size=2;
		Body left1 = SpringDemo.createFixedBodyByLeftTop(world,sx-size/2,sy+dh-size/2,size,size,BodyType.STATIC);
		
		
		
		left2 = SpringDemo.createFixedBodyByLeftTop(world,sx+dw/4-size/2,sy+dh/2-size/2,size,size,BodyType.DYNAMIC);
		Body right1 = SpringDemo.createFixedBodyByLeftTop(world,sx+dw-size/2,sy+dh-size/2,size,size,BodyType.STATIC);
		
		right2 = SpringDemo.createFixedBodyByLeftTop(world,sx+dw/2+dw/4-size/2,sy+dh/2-size/2,size,size,BodyType.DYNAMIC);
		
		
		
		center = SpringDemo.createFixedBodyByLeftTop(world,sx+dw/2-size/2,sy-size/2,size,size,BodyType.DYNAMIC);
		
		
		trueCenter=SpringDemo.createFixedBodyByLeftTop(world,sx+dw/2-size/2,sy+dh/2-size/2,size,size,BodyType.DYNAMIC);
		
		particles.add(trueCenter);
		particles.add(center);
		particles.add(left1);
		particles.add(left2);
		particles.add(right1);
		particles.add(right2);
		
		
		
		
	
		//create each other
		for (int i = 0; i < particles.size(); i++) {
			for (int j = i+1; j < particles.size(); j++) {
				SpringDemo.createDistanceJoint(world,particles.get(i),particles.get(j),null,null,frequencyHz,dampingRatio);
			}
		}
		
	}

	
	
}
