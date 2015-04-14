package com.vineet.ballfall;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;

public class Main extends Game implements ApplicationListener {

    public static AdInterface fbInterface;

    public Main(AdInterface fi){
        this.fbInterface = fi;
    }

	@Override
	public void create () {

        Settings.load();
        Settings.save();
		Assets.load();

        Main.fbInterface.createAd();
//        fbInterface.setUpFbManager();
		setScreen(new SplashScreen(this));

	}

	@Override
	public void render () {
		super.render();
		
	}

	@Override
	public void dispose(){
//		getScreen().dispose();
	}

    public static void setPost(String msg){
        fbInterface.post(msg);
    }
}
