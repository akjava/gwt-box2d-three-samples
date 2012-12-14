package com.akjava.gwt.threebox2d.client.demo.spring;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.DistanceJoint;
import org.jbox2d.dynamics.joints.DistanceJointDef;
import org.jbox2d.dynamics.joints.Joint;
import org.jbox2d.dynamics.joints.RevoluteJoint;


import com.akjava.gwt.lib.client.CanvasUtils;
import com.akjava.gwt.lib.client.ImageElementListener;
import com.akjava.gwt.lib.client.ImageElementLoader;
import com.akjava.gwt.lib.client.LogUtils;
import com.akjava.gwt.threebox2d.client.AbstractDemo;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SpringDemo extends AbstractDemo{

	
	private VerticalPanel controler;
	
	public SpringDemo(){
		super();
		gravityY=0;
	}
	
	public static Joint createDistanceJoint(World world,Body bodyA,Body bodyB,Vec2 anchorA,Vec2 anchorB,float frequencyHz,float dampingRatio){
		DistanceJointDef jd = new DistanceJointDef();
		jd.frequencyHz=frequencyHz;
		jd.dampingRatio=dampingRatio;
		
		jd.bodyA=bodyA;
		jd.bodyB=bodyB;
		
		if(anchorA!=null){
		//	jd.localAnchorA.set(anchorA);
		}
		if(anchorB!=null){
		//	jd.localAnchorB.set(anchorB);
		}
		
		Vec2 pt1=bodyA.getWorldPoint(jd.localAnchorA);
		Vec2 pt2=bodyB.getWorldPoint(jd.localAnchorB);
		jd.length=pt1.sub(pt2).length();
		return world.createJoint(jd);
	}
	
	AbstractEightSplitter splitter;
	@Override
	public void createItems() {
		splitter=new TypeAEightSplitter(world, 0.2f,.01f, 0, 0, 40, 40);
		splitter.setWaveImagePainter(new CanvasImagePainter());
		
		
		controler=new VerticalPanel();
		HorizontalPanel buttons=new HorizontalPanel();
		controler.add(buttons);
		Button force=new Button("force",new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				splitter.addForce(0, -1000);
				//center.applyForce(new Vec2(0,-500), topLeft.getPosition());
			}
		});
		buttons.add(force);
		
		if(drawCanvas!=null){
			controler.add(drawCanvas);
		}
	}
	public static String toString(DistanceJoint joint){
		String ret="[distance-joint]length="+joint.getLength()+" ";
		Vec2 vec=new Vec2();
		joint.getAnchorA(vec);
		ret+=vec.toString();
		joint.getAnchorB(vec);
		ret+=vec.toString();
		return ret;
	}
	


	public static Body createFixedBodyByLeftTop(World world,float left,float top,float width,float height,BodyType bodyType){
		if(height<=0 || width<=0){
			throw new RuntimeException("width and height must larger than 0");
		}
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox((float)width/2,(float)height/2);
		
		BodyDef bd = new BodyDef();
		bd.type = bodyType;
		bd.fixedRotation=true;//
		
		bd.position.set(left+width/2, top+height/2);
		Body body = world.createBody(bd);
		body.createFixture(shape,1);
		return body;
	}
	
	public static Body createBodyByLeftTop(World world,float left,float top,float width,float height,BodyType bodyType,float density,float friction,float restitution){
		if(height<=0 || width<=0){
			throw new RuntimeException("width and height must larger than 0");
		}
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox((float)width/2,(float)height/2);
		
		BodyDef bd = new BodyDef();
		bd.type = bodyType;
		
		
		bd.position.set(left+width/2, top+height/2);
		Body body = world.createBody(bd);
		FixtureDef fixtureDef=new FixtureDef();
		fixtureDef.shape=shape;
		fixtureDef.density=density;
		fixtureDef.friction=friction;
		fixtureDef.restitution=restitution;
		body.createFixture(fixtureDef);
		return body;
	}
	public static Body createCircle(World world,float x,float y,float radius,BodyType bodyType,float density,float friction,float restitution){
		if(radius<=0 ){
			throw new RuntimeException("width and height must larger than 0");
		}
		
		CircleShape circle=new CircleShape();
		circle.m_radius=radius;
		
		
		BodyDef bd = new BodyDef();
		bd.type = bodyType;
		bd.position.set(x,y);
		
		Body body = world.createBody(bd);
		FixtureDef fixtureDef=new FixtureDef();
		fixtureDef.shape=circle;
		fixtureDef.density=density;
		fixtureDef.friction=friction;
		fixtureDef.restitution=restitution;
		body.createFixture(fixtureDef);
		return body;
	}
	
	private Canvas drawCanvas,src;	
	private boolean loading;
	public void step(){
		super.step();
		
		if(loading){
			return;
		}
		
		if(src==null){
			loading=true;
			drawCanvas=CanvasUtils.createCanvas(400, 400);
			drawCanvas.getContext2d().fillRect(0, 0, drawCanvas.getCoordinateSpaceWidth(), drawCanvas.getCoordinateSpaceHeight());
			controler.add(drawCanvas);
			//RootPanel.get().add(drawCanvas);
			
			
			ImageElementLoader loader=new ImageElementLoader();
			loader.load("img/largecat.png", new ImageElementListener() {
				
				@Override
				public void onLoad(ImageElement element) {
					LogUtils.log("image-size:"+element.getWidth()+","+element.getHeight());
					src=CanvasUtils.createCanvas(element.getWidth(), element.getHeight());
					src.getContext2d().drawImage(element, 0, 0);
					loading=false;
				}
				
				@Override
				public void onError(String url, ErrorEvent event) {
					Window.alert(event.toString());
				}
			});
			}else{
				Canvas canvas=(Canvas) splitter.getWavedImage(src, 0, 0, src.getCoordinateSpaceWidth(), src.getCoordinateSpaceHeight());
				//LogUtils.log("size:"+canvas.getCoordinateSpaceWidth()+","+canvas.getCoordinateSpaceHeight());
				drawCanvas.getContext2d().setFillStyle("#000");
				drawCanvas.getContext2d().clearRect(0, 0, drawCanvas.getCoordinateSpaceWidth(),  drawCanvas.getCoordinateSpaceWidth());
				drawCanvas.getContext2d().drawImage(canvas.getCanvasElement(), 0, 0);
			}
			
	}
	

	@Override
	public void didpose() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getName() {
	
		return "Spring ImageWave";
	}

	@Override
	public Widget createControler() {
		
		return controler;
	}



}
