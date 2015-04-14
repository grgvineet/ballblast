package com.vineet.ballfall.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.vineet.ballfall.AdInterface;
import com.vineet.ballfall.Main;

public class DesktopLauncher implements AdInterface {
    private static DesktopLauncher fb;
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height = 480;
        config.width = 320;
        new LwjglApplication(new Main( new DesktopLauncher()), config);
    }


    @Override
    public void post(String msg) {
        Gdx.app.log("Desktop","This Sevice is not valid in desktop version");
    }

    @Override
    public void showAd() {
        System.out.println("This is service is not valid in desktop version");
    }

    @Override
    public void createAd() {
        System.out.println("This is service is not valid in desktop version");
    }


}
