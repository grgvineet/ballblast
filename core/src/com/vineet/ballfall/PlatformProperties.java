package com.vineet.ballfall;

import com.badlogic.gdx.Gdx;

public class PlatformProperties {
	
	public static float height = (Gdx.graphics.getHeight()/32) / Vars.world2box;
	public static float width = Gdx.graphics.getWidth()/(3* Vars.world2box);
//	public static float initialSpeed = (Gdx.graphics.getHeight()/9.6f)/Vars.world2box;
	public static float initialSpeed = (Gdx.graphics.getHeight()/4.8f)/ Vars.world2box;
//	public static float initialSpeed = (Gdx.graphics.getHeight()/2.4f)/Vars.world2box;
	public static int platformSpike = 0;
	public static int platform = 1;


    public static final int noOfPlatformForPowerComing = 7;


}
