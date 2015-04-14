package com.vineet.ballfall;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by vineet on 21-Jul-14.
 */
public class SettingsScreen implements Screen {

    Game game;

    SpriteBatch batch;
    OrthographicCamera cam;

    Vector2 touchPoint ;


    private Texture sfx,sound,vibration,sensitivity,on,off,low,med,high,back;
    private Sprite sSfx,sSound,sVibration,sSensitivity,sLow,sMed,sHigh;
    private Sprite sSfxOn,sSfxOff;
    private Sprite sSoundOn,sSoundOff;
    private Sprite sVibrationOn,sVibrationOff;
    Rectangle rSfxon,rSfxOff,rSoundOff,rSoundOn,rVibrationOff,rVibrationOn,rLow,rHigh,rMed;

    float height = Gdx.graphics.getHeight();
    float width = Gdx.graphics.getWidth();

    public SettingsScreen(Game g){

        this.game = g;

        batch = new SpriteBatch();

        cam = new OrthographicCamera(width,height);
//        cam.position.set(new Vector3(width/2f,height/2f,0));
        cam.setToOrtho(false, width, height);

        sfx = Assets.sfx;
        sound = Assets.sound;
        vibration = Assets.vibration;
        sensitivity = Assets.sensitivity;
        on = Assets.on;
        off = Assets.off;
        low = Assets.low;
        med = Assets.med;
        high = Assets.high;
        back = Assets.settingsBack;

//        sSfx = new Sprite(sfx);
//        sSound = new Sprite(sound);
//        sVibration = new Sprite(vibration);
//        sSensitivity = new Sprite(sensitivity);
//        sSfxOff = new Sprite(off);
//
//        sSfxOn = new Sprite(on);
//        sSfxOn.setSize(width,height);
//        sSfxOn.setPosition(0,0);

        rSfxOff = new Rectangle();
        rSfxon = new Rectangle();
        rSoundOff = new Rectangle();
        rSoundOn = new Rectangle();
        rVibrationOn = new Rectangle();
        rVibrationOff = new Rectangle();
        rMed = new Rectangle();
        rLow = new Rectangle();
        rHigh = new Rectangle();


        touchPoint = new Vector2();


        sSfx = new Sprite(sfx);


        sSound = new Sprite(sound);


        sVibration = new Sprite(vibration);

//        playBounds.set(playSprite.getX(), playSprite.getY(), playSprite.getWidth(), playSprite.getHeight());

        sSensitivity = new Sprite(sensitivity);

        sSfxOn = new Sprite(on);

        sSfxOff = new Sprite(off);

        sSoundOn = new Sprite(on);


        sSoundOff = new Sprite(off);


        sVibrationOn = new Sprite(on);

        sVibrationOff = new Sprite(off);

        sLow = new Sprite(low);

        sMed = new Sprite(med);

        sHigh = new Sprite(high);

        create();

    }

    void create(){

        sSfx.setSize(((float)sfx.getWidth()/sfx.getHeight())*height * 0.042708f, height * 0.042708f);
        sSfx.setPosition(getPositionForX(width * 0.196875f) - (sSfx.getWidth()/2f), getPositionForY(height * 0.2979f) - sSfx.getHeight()/2f );
//        playBounds.set(playSprite.getX(), playSprite.getY(), playSprite.getWidth(), playSprite.getHeight());

        sSound.setSize(((float)sound.getWidth()/sound.getHeight())*height * 0.055208f, height * 0.055208f);
        sSound.setPosition(getPositionForX(width * 0.2468f) - (sSound.getWidth()/2f), getPositionForY(height * 0.4239f) - sSound.getHeight()/2f );
//        playBounds.set(playSprite.getX(), playSprite.getY(), playSprite.getWidth(), playSprite.getHeight());

        sVibration.setSize(((float)vibration.getWidth()/vibration.getHeight())*height * 0.055208f, height * 0.055208f);
        sVibration.setPosition(getPositionForX(width * 0.304f) - (sVibration.getWidth()/2f), getPositionForY(height * 0.5489f) - sVibration.getHeight()/2f );

        sSensitivity.setSize(((float)sensitivity.getWidth()/sensitivity.getHeight())*height * 0.055208f, height * 0.055208f);
        sSensitivity.setPosition(getPositionForX(width * 0.325f) - (sSensitivity.getWidth()/2f), getPositionForY(height * 0.671f) - sSensitivity.getHeight()/2f );
//        playBounds.set(playSprite.getX(), playSprite.getY(), playSprite.getWidth(), playSprite.getHeight());

        sSfxOn.setSize(((float)on.getWidth()/on.getHeight())*height * 0.088f, height * 0.088f);
        sSfxOn.setPosition(getPositionForX(width * 0.6375f) - (sSfxOn.getWidth()/2f), getPositionForY(height * 0.30f) - sSfxOn.getHeight()/2f );
        rSfxon.set(sSfxOn.getX(), sSfxOn.getY(), sSfxOn.getWidth(), sSfxOn.getHeight());

        sSfxOff.setSize(((float)off.getWidth()/off.getHeight())*height * 0.088f, height * 0.088f);
        sSfxOff.setPosition(getPositionForX(width * 0.7843f) - (sSfxOff.getWidth()/2f), getPositionForY(height * 0.30f) - sSfxOff.getHeight()/2f );
        rSfxOff.set(sSfxOff.getX(), sSfxOff.getY(), sSfxOff.getWidth(), sSfxOff.getHeight());

        sSoundOn.setSize(((float)on.getWidth()/on.getHeight())*height * 0.088f, height * 0.088f);
        sSoundOn.setPosition(getPositionForX(width * 0.6375f) - (sSoundOn.getWidth()/2f), getPositionForY(height * 0.4265f) - sSoundOn.getHeight()/2f );
        rSoundOn.set(sSoundOn.getX(), sSoundOn.getY(), sSoundOn.getWidth(), sSoundOn.getHeight());

        sSoundOff.setSize(((float)off.getWidth()/off.getHeight())*height * 0.088f, height * 0.088f);
        sSoundOff.setPosition(getPositionForX(width * 0.7843f) - (sSoundOff.getWidth()/2f), getPositionForY(height * 0.4265f) - sSoundOff.getHeight()/2f );
        rSoundOff.set(sSoundOff.getX(), sSoundOff.getY(), sSoundOff.getWidth(), sSoundOff.getHeight());

        sVibrationOn.setSize(((float)on.getWidth()/on.getHeight())*height * 0.088f, height * 0.088f);
        sVibrationOn.setPosition(getPositionForX(width * 0.6375f) - (sVibrationOn.getWidth()/2f), getPositionForY(height * 0.5468f) - sVibrationOn.getHeight()/2f );
        rVibrationOn.set(sVibrationOn.getX(), sVibrationOn.getY(), sVibrationOn.getWidth(), sVibrationOn.getHeight());

        sVibrationOff.setSize(((float)off.getWidth()/off.getHeight())*height * 0.088f, height * 0.088f);
        sVibrationOff.setPosition(getPositionForX(width * 0.7843f) - (sVibrationOff.getWidth()/2f), getPositionForY(height * 0.5468f) - sVibrationOff.getHeight()/2f );
        rVibrationOff.set(sVibrationOff.getX(), sVibrationOff.getY(), sVibrationOff.getWidth(), sVibrationOff.getHeight());

        sLow.setSize(((float)low.getWidth() / low.getHeight()) * height * 0.088f, height * 0.088f);
        sLow.setPosition(getPositionForX(width * 0.6375f) - (sLow.getWidth()/2f), getPositionForY(height * 0.6703f) - sLow.getHeight()/2f );
        rLow.set(sLow.getX(), sLow.getY(), sLow.getWidth(), sLow.getHeight());

        sMed.setSize(((float)med.getWidth()/med.getHeight())*height * 0.088f, height * 0.088f);
        sMed.setPosition(getPositionForX(width * 0.7843f) - (sMed.getWidth()/2f), getPositionForY(height * 0.6703f) - sMed.getHeight()/2f );
//        sMed.setPosition(Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/2);
        rMed.set(sMed.getX(), sMed.getY(), sMed.getWidth(), sMed.getHeight());

        sHigh.setSize(((float)high.getWidth()/high.getHeight())*height * 0.088f, height * 0.088f);
        sHigh.setPosition(getPositionForX(width * 0.92f) - (sHigh.getWidth()/2f), getPositionForY(height * 0.6703f) - sHigh.getHeight()/2f );
        rHigh.set(sHigh.getX(), sHigh.getY(), sHigh.getWidth(), sHigh.getHeight());


    }

    @Override
    public void show() {

        if (Settings.musicEnabled){
            sSoundOn.setAlpha(0.5f);
            sSoundOff.setAlpha(1.0f);
        }else if (!Settings.musicEnabled){
            sSoundOff.setAlpha(0.5f);
            sSoundOn.setAlpha(1.0f);
        }

        if (Settings.sfxEnabled){
            sSfxOn.setAlpha(0.5f);
            sSfxOff.setAlpha(1.0f);
        }else if (!Settings.sfxEnabled){
            sSfxOn.setAlpha(1.0f);
            sSfxOff.setAlpha(0.5f);
        }

        if (Settings.vibratorEnabled){
            sVibrationOff.setAlpha(1.0f);
            sVibrationOn.setAlpha(0.5f);
        }else if (!Settings.vibratorEnabled){
            sVibrationOff.setAlpha(0.5f);
            sVibrationOn.setAlpha(1.0f);
        }

        if (Settings.sensitivity == BallProperties.lowSensitivity){
            sLow.setAlpha(0.5f);
            sMed.setAlpha(1.0f);
            sHigh.setAlpha(1.0f);
        }else if (Settings.sensitivity == BallProperties.midSensitivity){
            sLow.setAlpha(1.0f);
            sMed.setAlpha(0.5f);
            sHigh.setAlpha(1.0f);
        }else if (Settings.sensitivity == BallProperties.highSensitivity){
            sLow.setAlpha(1.0f);
            sMed.setAlpha(1.0f);
            sHigh.setAlpha(0.5f);
        }

    }

    public void update(){

        if(Gdx.input.isKeyPressed(Input.Keys.MENU)){
            if (Settings.sfxEnabled) {
                Assets.playSound(Assets.click);
            }
            Settings.save();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.BACK) || Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            if (Settings.sfxEnabled) {
                Assets.playSound(Assets.click);
            }
            game.setScreen(new MenuScreen(game));
            this.dispose();
        }

        if (Gdx.input.justTouched()) {
            if (Settings.sfxEnabled) {
                Assets.playSound(Assets.click);
            }
//    		cam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 0));
            touchPoint.x = Gdx.input.getX();
            touchPoint.y = Gdx.graphics.getHeight() - Gdx.input.getY();
            if (OverlapTester.pointInRectangle(rSfxon, touchPoint.x, touchPoint.y) && Settings.sfxEnabled == false ){
				sSfxOn.setAlpha(0.5f);
                sSfxOff.setAlpha(1.0f);
                Settings.sfxEnabled = true;
                if (Settings.vibratorEnabled){
                    Gdx.input.vibrate(50);
                }
                return;
            }

            if (OverlapTester.pointInRectangle(rSfxOff, touchPoint.x, touchPoint.y) && Settings.sfxEnabled == true) {
                if (Settings.sfxEnabled) {
                    Assets.playSound(Assets.click);
                }
				sSfxOff.setAlpha(0.5f);
                sSfxOn.setAlpha(1.0f);
                Settings.sfxEnabled = false;
                if (Settings.vibratorEnabled){
                    Gdx.input.vibrate(50);
                }
                return;
            }

            if (OverlapTester.pointInRectangle(rSoundOn, touchPoint.x, touchPoint.y) && Settings.musicEnabled ==false) {
				sSoundOn.setAlpha(0.5f);
                sSoundOff.setAlpha(1.0f);
                if (Settings.musicEnabled == false){
                    Assets.playMusic();
                }
                if (Settings.vibratorEnabled){
                    Gdx.input.vibrate(50);
                }
                if (Settings.sfxEnabled){
                    Assets.playSound(Assets.click);
                }
                Settings.musicEnabled = true;

                return;
            }



            if (OverlapTester.pointInRectangle(rSoundOff, touchPoint.x, touchPoint.y) && Settings.musicEnabled == true) {
                sSoundOn.setAlpha(1.0f);
                sSoundOff.setAlpha(0.5f);
                if (Settings.musicEnabled){
                    Assets.stopMusic();
                }
                Settings.musicEnabled = false;
                if (Settings.vibratorEnabled){
                    Gdx.input.vibrate(50);
                }
                if (Settings.sfxEnabled){
                    Assets.playSound(Assets.click);
                }
                return;
            }

            if (OverlapTester.pointInRectangle(rVibrationOn, touchPoint.x, touchPoint.y) && Settings.vibratorEnabled == false) {
                sVibrationOn.setAlpha(0.5f);
                sVibrationOff.setAlpha(1.0f);
                Settings.vibratorEnabled = true;
                if (Settings.vibratorEnabled){
                    Gdx.input.vibrate(50);
                }
                if (Settings.sfxEnabled){
                    Assets.playSound(Assets.click);
                }
                return;
            }

            if (OverlapTester.pointInRectangle(rVibrationOff, touchPoint.x, touchPoint.y)) {
                sVibrationOn.setAlpha(1.0f);
                sVibrationOff.setAlpha(0.5f);
                Settings.vibratorEnabled = false;
                if (Settings.sfxEnabled){
                    Assets.playSound(Assets.click);
                }
                return;
            }

            if (OverlapTester.pointInRectangle(rLow, touchPoint.x, touchPoint.y) && Settings.sensitivity != BallProperties.lowSensitivity) {
                sLow.setAlpha(0.5f);
                sMed.setAlpha(1.0f);
                sHigh.setAlpha(1.0f);
                Settings.sensitivity = BallProperties.lowSensitivity;
                if (Settings.vibratorEnabled){
                    Gdx.input.vibrate(50);
                }
                if (Settings.sfxEnabled){
                    Assets.playSound(Assets.click);
                }
                return;
            }

            if (OverlapTester.pointInRectangle(rMed, touchPoint.x, touchPoint.y) && Settings.sensitivity != BallProperties.midSensitivity) {
                sLow.setAlpha(1.0f);
                sMed.setAlpha(0.5f);
                sHigh.setAlpha(1.0f);
                Settings.sensitivity = BallProperties.midSensitivity;
                if (Settings.vibratorEnabled){
                    Gdx.input.vibrate(50);
                }
                if (Settings.sfxEnabled){
                    Assets.playSound(Assets.click);
                }
                return;
            }

            if (OverlapTester.pointInRectangle(rHigh, touchPoint.x, touchPoint.y) && Settings.sensitivity != BallProperties.highSensitivity) {
                sLow.setAlpha(1.0f);
                sMed.setAlpha(1.0f);
                sHigh.setAlpha(0.5f);
                Settings.sensitivity = BallProperties.highSensitivity;
                if (Settings.vibratorEnabled){
                    Gdx.input.vibrate(50);
                }
                if (Settings.sfxEnabled){
                    Assets.playSound(Assets.click);
                }
                return;
            }



        }

    }


    @Override
    public void render(float delta) {

        update();

        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();
        batch.draw(back,0,0,width,height);
        sSfx.draw(batch);
        sSound.draw(batch);
        sSensitivity.draw(batch);
        sVibration.draw(batch);
        sSfxOn.draw(batch);
        sSfxOff.draw(batch);
        sSoundOn.draw(batch);
        sSoundOff.draw(batch);
        sVibrationOff.draw(batch);
        sVibrationOn.draw(batch);
        sLow.draw(batch);
        sMed.draw(batch);
        sHigh.draw(batch);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        System.out.println("window resized");
        this.height = height;
        this.width = width;
        create();
//        SettingsScreen(game);
//        rSfxon.set(sSfxOn.getX(), sSfxOn.getY(), sSfxOn.getWidth(), sSfxOn.getHeight());
//        rSfxOff.set(sSfxOff.getX(), sSfxOff.getY(), sSfxOff.getWidth(), sSfxOff.getHeight());
//        rSoundOn.set(sSoundOn.getX(), sSoundOn.getY(), sSoundOn.getWidth(), sSoundOn.getHeight());
//        rSoundOff.set(sSoundOff.getX(), sSoundOff.getY(), sSoundOff.getWidth(), sSoundOff.getHeight());
//        rVibrationOn.set(sVibrationOn.getX(), sVibrationOn.getY(), sVibrationOn.getWidth(), sVibrationOn.getHeight());
//        rVibrationOff.set(sVibrationOff.getX(), sVibrationOff.getY(), sVibrationOff.getWidth(), sVibrationOff.getHeight());
//        rVibrationOff.set(sVibrationOff.getX(), sVibrationOff.getY(), sVibrationOff.getWidth(), sVibrationOff.getHeight());
//        rLow.set(sLow.getX(), sLow.getY(), sLow.getWidth(), sLow.getHeight());
//        rMed.set(sMed.getX(), sMed.getY(), sMed.getWidth(), sMed.getHeight());
//        rHigh.set(sHigh.getX(), sHigh.getY(), sHigh.getWidth(), sHigh.getHeight());
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    public static float getPositionForX(float x){
        return x;
    }

    public float getPositionForY(float y){
        return height - y;
    }
}
