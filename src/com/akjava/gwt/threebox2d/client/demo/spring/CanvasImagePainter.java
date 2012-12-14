package com.akjava.gwt.threebox2d.client.demo.spring;

import com.akjava.gwt.lib.client.CanvasUtils;
import com.akjava.gwt.lib.client.LogUtils;
import com.google.gwt.canvas.client.Canvas;

public class CanvasImagePainter implements WaveImagePainter{
private Canvas src;
private Canvas dest;
private int width,height;//height is resized
	@Override
	public void setSrcImage(Object object) {
		src=(Canvas) object;
		if(dest!=null){
		dest.getContext2d().clearRect(0,0,dest.getCoordinateSpaceWidth(),dest.getCoordinateSpaceHeight());
		}
	}

	@Override
	public Object getSrcImage() {

		return src;
	}

	@Override
	public void setSrcSize(int width, int height) {
		this.width=width;
		this.height=height;
	}

	@Override
	public Object getDestImage() {
		return dest;
	}

	@Override
	public int getSrcImageWidth() {
		return width;
	}

	@Override
	public int getSrcImageHeight() {
		return height;
	}

	@Override
	public void drawImage(int dx1, int dy1, int dx2, int dy2,int sx1, int sy1,int sx2, int sy2) {
	//public void drawImage( int sx1, int sy1,int sx2, int sy2,int dx1, int dy1, int dx2, int dy2) {
		if(dest==null){
			dest=CanvasUtils.createCanvas(width, height);
		}
		
		//LogUtils.log(sx1+","+sy1+":"+sx2+","+sy2+":"+dx1+","+dy1+":"+dx2+","+dy2);
		CanvasUtils.drawImageByCordinate(dest, src.getCanvasElement(), sx1, sy1, sx2, sy2, dx1, dy1, dx2, dy2);
		
		//dest.getContext2d().setStrokeStyle("#f00");
		//dest.getContext2d().strokeRect(sx1, sy1, sx2, sy2);
		
	}

}
