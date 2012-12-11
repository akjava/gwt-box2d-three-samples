package com.akjava.gwt.threebox2d.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbox2d.collision.AABB;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;

import com.akjava.gwt.lib.client.CanvasUtils;
import com.akjava.gwt.lib.client.LogUtils;
import com.akjava.gwt.stats.client.Stats;
import com.akjava.gwt.three.client.THREE;
import com.akjava.gwt.three.client.cameras.Camera;
import com.akjava.gwt.three.client.core.Object3D;
import com.akjava.gwt.three.client.experiments.CSS3DObject;
import com.akjava.gwt.three.client.experiments.CSS3DRenderer;
import com.akjava.gwt.three.client.lights.Light;
import com.akjava.gwt.three.client.materials.MeshBasicMaterialBuilder;
import com.akjava.gwt.three.client.renderers.WebGLRenderer;
import com.akjava.gwt.three.client.scenes.Scene;
import com.akjava.gwt.three.client.textures.Texture;
import com.akjava.gwt.threebox2d.client.simple.SimpleDemo;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ValueListBox;

public class Main implements EntryPoint {



	//private Canvas canvas;
	
	

	

	private Camera camera;

	private WebGLRenderer renderer;

	private Scene scene;
	int width=600;
	int height=400;
	Object3D objRoot;
	Map<Body,Object3D> threeObjects=new HashMap<Body,Object3D>();
	List<Box2dDemo> demos=new ArrayList<Box2dDemo>();
	//private String currentRendererType="css3d";
	private void switchRenderer(String type){
		init();
		if(renderer.gwtGetType().equals(type)){
			return;
		}
		focusPanel.clear();
		HTMLPanel div=new HTMLPanel("");
		if(type.equals("css3d")){
			renderer = THREE.CSS3DRenderer();
		}else if(type.equals("webgl")){
			renderer=THREE.WebGLRenderer();
		}else{//canvas
			renderer=THREE.CanvasRenderer();
		}
		renderer.setSize(width, height);
		div.getElement().appendChild(renderer.getDomElement());
		renderer.gwtSetType(type);
		focusPanel.add(div);
	}
	@Override
	public void onModuleLoad() {
		
		
		
		main = new MainUi();
		RootLayoutPanel.get().add(main);
		//RootPanel.get().add(main);
		demos.add(new SimpleDemo());
		
		//demos.add(new CarDemo());
		//demos.add(new ImageWaveDemo());
		
		//APEngine.container(new ArrayListDisplayObjectContainer());
		
		//apeDemo = new CarDemo();
		//apeDemo=new ImageWaveDemo();
		
		//apeDemo.initialize();
		
		//APEngine.step();
		
		
		scene = THREE.Scene();
		objRoot=THREE.Object3D();
		objRoot.setPosition(-width/2, 0, 0);
		scene.add(objRoot);
		
		camera = THREE.PerspectiveCamera(35,(double)width/height,.1,10000);
		scene.add(camera);
		camera.getPosition().setZ(700);
		camera.getPosition().setX(0);
		camera.getPosition().setY(-150);
		
		
		light = THREE.PointLight(0xffffff);
		light.setPosition(10, 0, 10);
		scene.add(light);
		
		
		//camera.getRotation().setZ(Math.toRadians(180)); //fliphorizontaled
		renderer = THREE.CSS3DRenderer();
		renderer.gwtSetType("css3d");
		renderer.setSize(width, height);
		
		
		HTMLPanel div=new HTMLPanel("");
		div.getElement().appendChild(renderer.getDomElement());
		focusPanel = new FocusPanel();
		focusPanel.add(div);
		main.getCenter().add(focusPanel);
		focusPanel.addKeyDownHandler(new KeyDownHandler() {
			
			@Override
			public void onKeyDown(KeyDownEvent event) {
				apeDemo.keyDown(event);
			}
		});
		focusPanel.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				apeDemo.KeyUp(event);
			}
		});
		focusPanel.setFocus(true);
		
		main.getWebglButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				switchRenderer("webgl");
			}
		});
		main.getCanvasButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				switchRenderer("canvas");
			}
		});
		main.getCss3dButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				switchRenderer("css3d");
			}
		});
		
		/*
		canvas = Canvas.createIfSupported();
		canvas.setSize("600px", "600px");
		canvas.setCoordinateSpaceWidth(600);
		canvas.setCoordinateSpaceHeight(600);
		
		root.add(canvas);
		*/
		final Stats stats=Stats.insertStatsToRootPanel();
		stats.domElement().getStyle().setWidth(90.0, Unit.PX);
		//stats.setPosition(8, 0);
		
		Timer timer=new Timer(){

			@Override
			public void run() {//wait?
				long t=System.currentTimeMillis();
				//LogUtils.log(""+(t-last));
				last=t;
				stats.begin();
				if(doInit){
					//LogUtils.log("init");
					init();
					doInit=false;
				}
				
				updateCanvas();
				stats.end();
			}

			
			
		};
		timer.scheduleRepeating(1000/60);
		
		
		
		updateCanvas();
		

	HorizontalPanel buttons=new HorizontalPanel();	
	buttons.setVerticalAlignment(HorizontalPanel.ALIGN_MIDDLE);
	
	ValueListBox<Box2dDemo> demosList=new ValueListBox<Box2dDemo>(new Renderer<Box2dDemo>() {

		@Override
		public String render(Box2dDemo object) {
			if(object==null){
				return null;
			}
			return object.getName();
		}

		@Override
		public void render(Box2dDemo object, Appendable appendable)
				throws IOException {
			
		}
	});
	demosList.addValueChangeHandler(new ValueChangeHandler<Box2dDemo>() {
		
		@Override
		public void onValueChange(ValueChangeEvent<Box2dDemo> event) {
			
			init();//remove
			apeDemo=event.getValue();
			apeDemo.initialize();
		}
	});
	demosList.setValue(demos.get(0));
	demosList.setAcceptableValues(demos);
	
	buttons.add(demosList);
	
	apeDemo=demos.get(0);
	apeDemo.initialize();
	
	
Button init=new Button("initialize",new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				doInit=true;
				focusPanel.setFocus(true);
			}
		});
		main.getButtons().add(buttons);
		buttons.add(init);
		
		
		
	}
	
	long last;
	
	boolean doInit;
	public static Map<Object,Integer> colorMap=new HashMap<Object, Integer>();
	
	private void init(){
		/*
		 * initialize faild.car something faild
		 */
		if(apeDemo!=null){
		apeDemo.initialize();
		}
		
		threeObjects.clear();
		
		
		if(renderer.gwtGetType().equals("css3d")){
		LogUtils.log("renderer is css3d and initialize");
		((CSS3DRenderer)renderer).gwtClear();
		}
		
		scene = THREE.Scene();
		scene.add(camera);
		
		objRoot=THREE.Object3D();
		objRoot.setPosition(-width/2, 0, 0);
		scene.add(objRoot);
		
		scene.add(light);		
		//LogUtils.log("renderer:"+renderer.getCameraElement().getChildNodes().getLength());
	}
	
	private void updateCanvas() {
		if(apeDemo==null){
			return;//useless
		}
		
		
		World world=apeDemo.getWorld();
		for(Body b=world.getBodyList();b!=null;b=b.getNext()){
			drawBody(b);
		}
		
		
		renderer.render(scene, camera);
		
		
		apeDemo.step();
		
	}
	
	
	private void drawBody(Body body) {
		Object3D obj=threeObjects.get(body);
		if(obj==null){
		Canvas bodyCanvas=createCanvasFill(body,"#800",true);
		obj=createCanvasObject(bodyCanvas,bodyCanvas.getCoordinateSpaceWidth(),bodyCanvas.getCoordinateSpaceHeight());
		threeObjects.put(body, obj);
		LogUtils.log("create object:"+bodyCanvas.getCoordinateSpaceWidth()+","+bodyCanvas.getCoordinateSpaceHeight());
		objRoot.add(obj);
		}
		Vec2 pos=body.getPosition();
		obj.setPosition(pos.x, -pos.y, 0);
		obj.getRotation().setZ(-body.getAngle());
		
	}
	private Object3D createColorCircleObject(int r,int g,int b,double alpha,int radius,boolean stroke){
		Object3D object;
		Canvas canvas=CanvasUtils.createCircleImageCanvas(r, g, b, alpha, (int)(radius), 3,stroke);
		Texture texture=THREE.Texture(canvas.getCanvasElement());
		texture.setNeedsUpdate(true);
		if(!renderer.gwtGetType().equals("css3d")){
			MeshBasicMaterialBuilder basicMaterial=MeshBasicMaterialBuilder.create().map(texture);
			object=THREE.Mesh(THREE.PlaneGeometry(radius*2, radius*2), 
					basicMaterial.build());
		}else{
			Image img=new Image(canvas.toDataUrl());
			object=CSS3DObject.createObject(img.getElement());
		}
		return object;
	}
	
	private Object3D createCanvasObject(Canvas canvas,int w,int h){
		Object3D object;
		Texture texture=THREE.Texture(canvas.getCanvasElement());
		texture.setNeedsUpdate(true);
		if(!renderer.gwtGetType().equals("css3d")){
			MeshBasicMaterialBuilder basicMaterial=MeshBasicMaterialBuilder.create().map(texture);
			object=THREE.Mesh(THREE.PlaneGeometry(w, h), 
					basicMaterial.build());
		}else{
			Image img=new Image(canvas.toDataUrl());
			object=CSS3DObject.createObject(img.getElement());
		}
		return object;
	}
	
	private Object3D createColorRectObject(int r,int g,int b,double alpha,int width,int height){
		Object3D object;
		if(!renderer.gwtGetType().equals("css3d")){
			MeshBasicMaterialBuilder basicMaterial=MeshBasicMaterialBuilder.create().color(r,g,b).opacity(alpha)
					.transparent(true);
			object=THREE.Mesh(THREE.PlaneGeometry(width, height), 
					basicMaterial.build());
		}else{
			Image img=new Image(CanvasUtils.createColorRectImageDataUrl(r, g, b, 1, (int)width, (int)height));
			object=CSS3DObject.createObject(img.getElement());
		}
		return object;
	}
	
	

	


	private Box2dDemo apeDemo;

	private FocusPanel focusPanel;

	public static MainUi main;

	private Light light;
	

	



	

	
	
	
	
	

	public void drawShape(Body body){
		Canvas bodyCanvas=createCanvasFill(body,"#800",true);
		Vec2 pos=body.getPosition();
		//canvas.getContext2d().drawImage(bodyCanvas.getCanvasElement(), pos.x-(float)bodyCanvas.getCoordinateSpaceWidth()/2, pos.y-(float)bodyCanvas.getCoordinateSpaceHeight()/2);
	}
	
	public Canvas createCanvasFill(Body body,String style,boolean stroke){
		List<PolygonShape> polygons=new ArrayList<PolygonShape>();
		for(Fixture fixture=body.getFixtureList();fixture!=null;fixture=fixture.getNext()){
			ShapeType type=fixture.getType();
		if(type==ShapeType.POLYGON){
			polygons.add((PolygonShape) fixture.getShape());
			}
		else{
			//TODO support circle
		}
		}
		AABB aabb=calculateBox(polygons);
		int w=(int) (aabb.upperBound.x-aabb.lowerBound.x);
		int h=(int) (aabb.upperBound.y-aabb.lowerBound.y);
		if(w<=0){
			w=1;
		}
		if(h<0){
			h=1;
		}
		float offx=aabb.lowerBound.x;
		float offy=aabb.lowerBound.y;
		Canvas canvas=CanvasUtils.createCanvas(w, h);
		Context2d context=canvas.getContext2d();
		for(Fixture fixture=body.getFixtureList();fixture!=null;fixture=fixture.getNext()){
			ShapeType type=fixture.getType();
			if(type==ShapeType.POLYGON){
				PolygonShape poly=(PolygonShape) fixture.getShape();
				int size=poly.m_vertexCount;
				context.beginPath();
				context.moveTo(poly.m_vertices[0].x-offx, poly.m_vertices[0].y-offy);
				for(int i=1;i<size;i++){
					context.lineTo(poly.m_vertices[i].x-offx, poly.m_vertices[i].y-offy);
				}
				context.lineTo(poly.m_vertices[0].x-offx, poly.m_vertices[0].y-offy);
				context.closePath();
				if(stroke){
					context.setStrokeStyle(style);
					context.stroke();
				}else{
					context.setFillStyle(style);
					context.fill();
				}
			}
			
			}
		
		
		return canvas;
	}
	
	//calculate multiple
	public AABB calculateBox(List<PolygonShape> polygons){
		List<Vec2> points=new ArrayList<Vec2>();
		for(PolygonShape polygon:polygons){
			int vertexCount=polygon.m_vertexCount;
			for (int i = 0; i < vertexCount; ++i) {
				points.add(polygon.m_vertices[i]);
			}
		}
		
		float minX=points.get(0).x;
		float minY=points.get(0).y;
		float maxX=points.get(0).x;
		float maxY=points.get(0).y;
		for (int i = 1; i < points.size(); ++i) {
			Vec2 v=points.get(i);
				if(v.x<minX){
					minX=v.x;
				}
				if(v.y<minY){
					minY=v.y;
				}
				if(v.x>maxX){
					maxX=v.x;
				}
				if(v.y>maxY){
					maxY=v.y;
				}
		}
		
		AABB aabb=new AABB();
		aabb.lowerBound.x=minX;
		aabb.lowerBound.y=minY;
		aabb.upperBound.x=maxX;
		aabb.upperBound.y=maxY;
		return aabb;
	}
	
	

}
