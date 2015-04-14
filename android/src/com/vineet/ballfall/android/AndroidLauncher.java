package com.vineet.ballfall.android;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.revmob.RevMob;
import com.revmob.ads.banner.RevMobBanner;
import com.revmob.ads.fullscreen.RevMobFullscreen;
import com.vineet.ballfall.Main;


public class AndroidLauncher extends AndroidApplication {

    protected View adView;
    protected View gameView;
    RelativeLayout layout;

    AdManager adManager;
    public RevMob revmob;
    private RevMobFullscreen fullscreen;
    RevMobBanner banner;

    ConnectivityManager cm;
    NetworkInfo ni;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        adManager = new AdManager(this);

		config.useAccelerometer = true;
		config.useWakelock = true;
        config.useCompass = false;

        layout = new RelativeLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(params);


        View gameeView = createGameView(config);
        layout.addView(gameeView);
        View addView = createAdView();
        layout.addView(addView);

        setContentView(layout);
//
//        revmob = RevMob.start(this); // RevMob Media ID configured in the AndroidManifest.xml file
//        banner = revmob.createBanner(this);
//        ViewGroup view = (ViewGroup) findViewById(R.id.adLayout);
//        view.addView(banner);
//
//        gameView = initializeForView(new Main(adManager), config);
//        ViewGroup gameViewGroup = (ViewGroup) findViewById(R.id.gameLayout);
//        gameViewGroup.addView(gameView);
//
        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            adView.setVisibility(View.GONE);
        }else{
            revmob = RevMob.start(this);
            banner = revmob.createBanner(this);
            ViewGroup view = (ViewGroup)findViewById(addView.getId());
            view.addView(banner);
        }


	}

    private View createAdView() {
        adView = new RelativeLayout(this);
        adView.setId(12345); // this is an arbitrary id, allows for relative positioning in createGameView()
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        adView.setLayoutParams(params);
        adView.setBackgroundColor(Color.BLACK);
        return adView;
    }

    private View createGameView(AndroidApplicationConfiguration cfg) {
        gameView = initializeForView(new Main(adManager), cfg);
//        gameView = initializeForView(new Main(), cfg);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
//        params.addRule(RelativeLayout.ABOVE, adView.getId());
        gameView.setLayoutParams(params);
        return gameView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void showFullScreenAd(){
        if (ni != null) {
            fullscreen.show();
        }
    }

    public void createFullScreenAd(){
        if (ni != null) {
            fullscreen = revmob.createFullscreen(this, null);
        }
    }

}
