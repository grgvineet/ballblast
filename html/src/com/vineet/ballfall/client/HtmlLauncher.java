package com.vineet.ballfall.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.vineet.ballfall.Main;

public class HtmlLauncher extends GwtApplication {

        AdManager adManager;

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(320, 480);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                adManager = new AdManager(this);
                return new Main(adManager);
        }
}