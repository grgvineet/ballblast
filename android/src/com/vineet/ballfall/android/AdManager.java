package com.vineet.ballfall.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

import com.vineet.ballfall.AdInterface;


//import com.facebook.Session;
//import com.facebook.SessionState;
//import com.facebook.UiLifecycleHelper;

/**
 * Created by vineet on 26-Jul-14.
 */
public class AdManager extends Activity implements AdInterface {


    Handler handler;
    AndroidLauncher c;

    public AdManager(AndroidLauncher androidLauncher){
        this.c = androidLauncher;
        handler = new Handler();
    }


    @Override
    public void post(final String msg) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, msg);
//                Toast.makeText(c,"this is a toast",Toast.LENGTH_SHORT).show();
                c.startActivity(Intent.createChooser(shareIntent, "Share with..."));

            }
        });

    }

    @Override
    public void showAd() {
        c.showFullScreenAd();
    }

    @Override
    public void createAd() {
        c.createFullScreenAd();
    }


}
