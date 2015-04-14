package com.vineet.ballfall;

import com.badlogic.gdx.Gdx;

public class BallProperties {
	
	public static float startingPositionX = (Gdx.graphics.getWidth()/ Vars.world2box)/2f;
	public static float startingPositionY = (Gdx.graphics.getHeight()/2f)/ Vars.world2box;
	public static float radius = (Gdx.graphics.getWidth()/30)/ Vars.world2box;
	public static float density = 0.1f;
	public static float friction = 0.0f;
	public static float horizontalForce = 1.5f/ Vars.world2box;
	public static float torque = 1.5f/ Vars.world2box;

    public static float lowSensitivity = 0.75f;
    public static float midSensitivity = 1.0f;
    public static float highSensitivity = 1.25f;

    public static int typeSimple = 0;
    public static int typeMetal = 1;


}
