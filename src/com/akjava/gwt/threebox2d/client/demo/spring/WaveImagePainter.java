package com.akjava.gwt.threebox2d.client.demo.spring;


public interface WaveImagePainter {
	public void setSrcImage(Object object);
	public Object getSrcImage();
	public void setSrcSize(int width,int height);
	public Object getDestImage();
	public int getSrcImageWidth();
	public int getSrcImageHeight();
	public void drawImage(int sx1,int sy1,int sx2,int sy2,int dx1,int dy1,int dx2,int dy2);
}
