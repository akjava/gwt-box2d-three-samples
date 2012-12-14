package com.akjava.gwt.threebox2d.client;

import org.jbox2d.common.Vec2;

public class Box2DUtils {

public static float calculateRadian(Vec2 p1,Vec2 p2){
Vec2 p=p1.sub(p2);
return (float)Math.atan2(p.y, p.x);
}

public static Vec2 getCenter(Vec2 p1,Vec2 p2,Vec2 argOut){
	argOut.set(p1);
	return divEquals(argOut.addLocal(p2),2);
}

public static Vec2 divEquals(Vec2 target,double s){
if (s == 0) s = 0.0001;
target.x /= s;
target.y /= s;
return target;
}

}
