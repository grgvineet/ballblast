package com.vineet.ballfall;

/**
 * Created by vineet on 15/4/15.
 */
public class AdManager implements AdInterface {

    IOSLauncher iosLauncher;

    public AdManager(IOSLauncher iosLauncher){
        this.iosLauncher  = iosLauncher;
    }


    @Override
    public void post(final String msg) {
        // Dont know how to implement in ios
    }

    @Override
    public void showAd() {
        // Dont know how to implement in ios
    }

    @Override
    public void createAd() {
        // Dont know how to implement in ios
    }


}