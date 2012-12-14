package com.akjava.gwt.threebox2d.client.demo.spring;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.akjava.gwt.lib.client.LogUtils;




public abstract class AbstractEightSplitter implements WaveImageCreator{
	protected Body left2;
	protected Body right2;

	protected int sx=0;
	protected int sy=0;
	protected int dw=100;
	protected int dh=100;
	protected Body center;
	protected Body trueCenter;
	
	public WaveImagePainter waveImagePainter;
	
	protected float frequencyHz;
	protected float dampingRatio;
	
	public void setWaveImagePainter(WaveImagePainter painter){
		this.waveImagePainter=painter;
	}
	public AbstractEightSplitter(float frequencyHz,float dampingRatio,int sx,int sy,int dw,int dh){
		this.frequencyHz=frequencyHz;
		this.dampingRatio=dampingRatio;
		this.sx=sx;
		this.sy=sy;
		this.dw=dw;
		this.dh=dh;
	}
	
	public void addForce(float x,float y){
		trueCenter.applyForce(new Vec2(x,y),trueCenter.getPosition());
	}
	
	/*
	public Canvas gwtCanvasImage(Canvas srcImage,int x,int y,int width,int height){
		
		int cy= (int) ((center.getPosition().y-sy)/dh*height);
		 int dheight=height-cy;
		 
		 if(dheight<0){
			 dheight=1;
		 }
		 
		
        int lx= (int) ((left2.px()-sx)/dw*width);
        int ly= (int) ((left2.getPosition().y-sy)/dh*height);
        int cx= (int) ((center.px()-sx)/dw*width);
        
        int rx= (int) ((right2.px()-sx)/dw*width);
        int ry= (int) ((right2.getPosition().y-sy)/dh*height);
        
        Canvas retImage=CanvasUtils.createCanvas(width, dheight);
        
  
       
       
        //int imgWidth=srcImage.getCoordinateSpaceWidth();
        //int imgHeight=srcImage.getCoordinateSpaceHeight();
       
        Context2d g=retImage.getContext2d();
        
        //left-up
        g.drawImage(srcImage.getCanvasElement(), 0, 0, lx,ly,x, y, x+width/4, y+height/2);
        ////log.info(0+","+(cy-cy)+","+ lx+","+ly+","+x+","+ y+","+ (x+imgWidth/4)+","+ (y+imgHeight/2));
        g.drawImage(srcImage.getCanvasElement(), lx, 0, cx,ly,x+width/4, y, x+width/2, y+height/2);
       
        //left-down
        g.drawImage(srcImage.getCanvasElement(), 0, ly, lx,dheight,x, y+height/2, x+width/4, y+height);
        g.drawImage(srcImage.getCanvasElement(), lx, ly, cx,dheight,x+width/4, y+height/2, x+width/2, y+height);
        
        //right-up
        g.drawImage(srcImage.getCanvasElement(), cx, 0, rx,ry,x+width/2, y, x+width/2+width/4, y+height/2);
        g.drawImage(srcImage.getCanvasElement(), rx, 0, width,ry,x+width/2+width/4, y,x+width, y+height/2);
        
        //right-down
        g.drawImage(srcImage.getCanvasElement(), cx, ry, rx,dheight,x+width/2, y+height/2, x+width/2+width/4, y+height);
        g.drawImage(srcImage.getCanvasElement(), rx, ry, width,dheight,x+width/2+width/4, y+height/2, x+width, y+height);
        
       
        return retImage;
	}*/
	
	
private boolean painting;
public Object getWavedImage(Object srcImage,int x,int y,int width,int height){
		if(painting){
			LogUtils.log("painting");
			waveImagePainter.getDestImage();
		}
		if(waveImagePainter==null){
			throw new RuntimeException("image painter is null");
		}
		painting=true;
		 int cy= (int) ((center.getPosition().y-sy)/dh*height);
		 int dheight=height-cy;
		 
		 if(dheight<0){
			 dheight=1;//zero height make problem
		 }
		 
		
        int lx= (int) ((left2.getPosition().x-sx)/dw*width);
        int ly= (int) ((left2.getPosition().y-sy)/dh*height);
        int cx= (int) ((center.getPosition().x-sx)/dw*width);
        
        int rx= (int) ((right2.getPosition().x-sx)/dw*width);
        int ry= (int) ((right2.getPosition().y-sy)/dh*height);
        
       
        waveImagePainter.setSrcImage(srcImage);
        waveImagePainter.setSrcSize(width, dheight);
       
        
      //  int imgWidth=waveImagePainter.getSrcImageWidth();
      //  int imgHeight=waveImagePainter.getSrcImageHeight();
        
        
        
        //left-up
        waveImagePainter.drawImage(0, 0, lx,ly,x, y, x+width/4, y+height/2);
        ////log.info(0+","+(cy-cy)+","+ lx+","+ly+","+x+","+ y+","+ (x+imgWidth/4)+","+ (y+imgHeight/2));
        waveImagePainter.drawImage( lx, 0, cx,ly,x+width/4, y, x+width/2, y+height/2);
       
        //left-down
        waveImagePainter.drawImage( 0, ly, lx,dheight,x, y+height/2, x+width/4, y+height);
        waveImagePainter.drawImage( lx, ly, cx,dheight,x+width/4, y+height/2, x+width/2, y+height);
        
        //right-up
        waveImagePainter.drawImage( cx, 0, rx,ry,x+width/2, y, x+width/2+width/4, y+height/2);
        waveImagePainter.drawImage( rx, 0, width,ry,x+width/2+width/4, y,x+width, y+height/2);
        
        //right-down
        waveImagePainter.drawImage( cx, ry, rx,dheight,x+width/2, y+height/2, x+width/2+width/4, y+height);
        waveImagePainter.drawImage( rx, ry, width,dheight,x+width/2+width/4, y+height/2, x+width, y+height);
        
        painting=false;
        return waveImagePainter.getDestImage();
	}


}
