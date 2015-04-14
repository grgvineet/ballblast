package com.vineet.ballfall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by vineet on 27-Jul-14.
 */
public class PowerTimer {

    public static boolean magicEnabled;
    public static boolean twoxEnabled;
    public static boolean metalBallEnabled;
    private int powersShowing;
    private float timelimit = 10;
    private float magicTime;
    private float twoxTime;
    private float metalBallTime;

    private Sprite twoxSprite,magicSprite,metalSprite;
    private Sprite twoxNeedleSprite,magicNeedleSprite,metalNeedleSprite;

    private final float padding = Gdx.graphics.getWidth()/40f;

    private final float powerXPositon = Gdx.graphics.getHeight() * 0.85f;

    int i;

    public PowerTimer(){

        magicEnabled = false;
        twoxEnabled = false;
        metalBallEnabled = false;
        powersShowing = 0;

        twoxSprite = new Sprite(Assets.twox);
        magicSprite = new Sprite(Assets.magic);
        metalSprite = new Sprite(Assets.metalBall);

        twoxNeedleSprite = new Sprite(Assets.needle);
        magicNeedleSprite = new Sprite(Assets.needle);
        metalNeedleSprite = new Sprite(Assets.needle);

        twoxSprite.setSize(2 * BallProperties.radius * Vars.world2box,2 * BallProperties.radius * Vars.world2box);
        magicSprite.setSize(2 * BallProperties.radius * Vars.world2box,2 * BallProperties.radius * Vars.world2box);
        metalSprite.setSize(2 * BallProperties.radius * Vars.world2box,2 * BallProperties.radius * Vars.world2box);

        twoxNeedleSprite.setSize(BallProperties.radius*Vars.world2box / 2.5f,BallProperties.radius * Vars.world2box);
        magicNeedleSprite.setSize(BallProperties.radius*Vars.world2box / 2.5f,BallProperties.radius * Vars.world2box);
        metalNeedleSprite.setSize(BallProperties.radius*Vars.world2box / 2.5f,BallProperties.radius * Vars.world2box);

        twoxNeedleSprite.setOrigin(0.5f,12/192.0f);
        magicNeedleSprite.setOrigin(0.5f,12/192.0f);
        metalNeedleSprite.setOrigin(0.5f,12/192.0f);

    }




    public void magicTaken(){
        if (magicEnabled){
            magicTime = 0;
        }else{
            magicEnabled = true;
            magicTime = 0;
            Ball.magicEnabled = true;
            powersShowing++;
        }

        magicNeedleSprite.setRotation(0);

    }

    public void twoxTaken(){
        if(twoxEnabled){
            twoxTime = 0;
        }else{
            twoxEnabled = true;
            Score.adder = 2;
            twoxTime = 0;
            powersShowing++;
        }

        twoxNeedleSprite.setRotation(0);

    }

    public void metalBallTaken(){
        if(metalBallEnabled){
            metalBallTime = 0;
        }else {
            Ball.type = BallProperties.typeMetal;
            metalBallEnabled = true;
            metalBallTime = 0;
            powersShowing++;
        }

        metalNeedleSprite.setRotation(0);

    }

    public void update(){

        if (metalBallEnabled){
            metalBallTime += Gdx.graphics.getDeltaTime();
            metalNeedleSprite.rotate(-1 * Gdx.graphics.getDeltaTime() * 360f / timelimit);
            if (metalBallTime > timelimit){
                if (Settings.sfxEnabled){
                    Assets.playSound(Assets.alarm);
                }
                metalBallTime = 0;
                Ball.type = BallProperties.typeSimple;
                metalBallEnabled = false;
                powersShowing--;
            }

        }

        if(twoxEnabled){
            twoxTime += Gdx.graphics.getDeltaTime();
            twoxNeedleSprite.rotate(-1 * Gdx.graphics.getDeltaTime() * 360f / timelimit);
            if(twoxTime >= timelimit){
                if (Settings.sfxEnabled){
                    Assets.playSound(Assets.alarm);
                }
                twoxTime = 0;
                twoxEnabled = false;
                Score.adder = 1;
                powersShowing--;
            }

        }

        if(magicEnabled){
            magicTime += Gdx.graphics.getDeltaTime();
            magicNeedleSprite.rotate(-1 * Gdx.graphics.getDeltaTime() * 360f / timelimit);
            if (magicTime >= timelimit){
                if (Settings.sfxEnabled){
                    Assets.playSound(Assets.alarm);
                }
                magicTime = 0;
                magicEnabled = false;
                Ball.magicEnabled = false;
                powersShowing --;
            }

        }

    }

    public void draw(SpriteBatch batch){



        i = 0;
        if (magicEnabled){
            magicSprite.setPosition((magicSprite.getWidth() + padding)*i + padding,powerXPositon);
            magicNeedleSprite.setPosition(magicSprite.getX() + magicSprite.getWidth()/2f, magicSprite.getY()+ magicSprite.getHeight()/2f);
//            magicNeedleSprite.setOrigin(magicNeedleSprite.getWidth()/2f,0);
//            magicSprite.setSize(Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/2f);
//            magicSprite.setPosition(Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/2f);
            magicSprite.draw(batch);
            magicNeedleSprite.draw(batch);
            i++;
//            System.out.println("powers drawn" + " " + magicSprite.getX() + " " + magicSprite.getY());
        }

        if(twoxEnabled){
            twoxSprite.setPosition((twoxSprite.getWidth() + padding)*i + padding,powerXPositon);
            twoxNeedleSprite.setPosition(twoxSprite.getX() + twoxSprite.getWidth()/2f,twoxSprite.getY() + twoxSprite.getHeight()/2f);
//            twoxNeedleSprite.setOrigin(twoxNeedleSprite.getWidth()/2f,0);
//            twoxSprite.setPosition(Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/2f);
            twoxSprite.draw(batch);
            twoxNeedleSprite.draw(batch);
            i++;
//            System.out.println("powers drawn");
        }

        if (metalBallEnabled){
            metalSprite.setPosition((metalSprite.getWidth() + padding)*i + padding,powerXPositon);
            metalNeedleSprite.setPosition(metalSprite.getX() + metalSprite.getWidth()/2f,metalSprite.getY() + metalSprite.getHeight()/2f);
//            metalNeedleSprite.setOrigin(twoxNeedleSprite.getWidth()/2f,0);
//            metalSprite.setPosition(Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/2f);
            metalSprite.draw(batch);
            metalNeedleSprite.draw(batch);
            i++;
//            System.out.println("powers drawn");
        }


    }

    public void render(SpriteBatch batch){
        update();
        draw(batch);
    }

}
