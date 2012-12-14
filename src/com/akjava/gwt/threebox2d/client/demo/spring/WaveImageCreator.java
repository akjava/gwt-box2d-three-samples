package com.akjava.gwt.threebox2d.client.demo.spring;


public interface WaveImageCreator {
	public Object getWavedImage(Object srcImage,int x,int y,int width,int height);
	public void setWaveImagePainter(WaveImagePainter painter);
	public void addForce(float xforce,float yforce);
	//public ArrayList getPaintQueue();
}
