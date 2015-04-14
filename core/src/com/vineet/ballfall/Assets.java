package com.vineet.ballfall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Assets {
	
	public static Texture ball;
    public static Texture ironBall;
	public static Texture platform;
	public static Texture back;
//	public static Texture splashScreen;
	public static Texture playButton;
	public static Texture background;
	public static Texture platformSpikes;
	public static Texture spike;
//    public static Texture menuScreenBackground;
    public static Texture aboutScreen;
    public static Texture sureToExit;


    public static Texture pauseScreen;
    public static Texture pauseResume;
    public static Texture pauseRestart;
    public static Texture pauseMenu;

    public static Texture gameOver;

    public static Texture tapToStart;

	
	//menu items//
	
	public static Texture play;
	public static Texture about;
	public static Texture help;
	public static Texture settings;

	//////////////

    /// powers/////

    public static Texture oneUp;
    public static Texture speedUp;
    public static Texture speedDown;
    public static Texture metalBall;
    public static Texture twox;
    public static Texture magic;
    public static Texture needle;

    ////////////////

    /////   settings   //////////

    public static Texture sfx,sound,vibration,sensitivity,settingsBack;
    public static Texture on,off,low,med,high;


    ///////////////////////////////


	
	public static Music music;
	public static Sound thud;
	public static Sound explode;
    public static Sound powerUp;
    public static Sound click;
    public static Sound alarm;

	
	public static Animation footballFissing;
	
	public static ParticleEffect blastEffect,magicEffect;
	
	
	public static void load(){
		
		ball = new Texture("ball.png");
        ironBall = new Texture("metalBall.png");
		platform = new Texture("platform.png");
//		splashScreen = new Texture ("start-2.png");
		background = new Texture("background.png");
		platformSpikes = new Texture ("platformSpikes.png");
		spike = new Texture("spike.png");
//        menuScreenBackground = new Texture("menuBackground.png");
        aboutScreen = new Texture("about.png");
        sureToExit = new Texture("exit.png");

        pauseScreen = new Texture("pause.png");
        pauseResume = new Texture("pauseOptions/resume.png");
        pauseRestart = new Texture("pauseOptions/restart.png");
        pauseMenu = new Texture ("pauseOptions/menu.png");

        gameOver = new Texture("gameover.png");

        tapToStart = new Texture("tapToStart.png");
		
		play = new Texture("menuItems/play.png");
		about = new Texture("menuItems/about.png");
		help = new Texture("menuItems/help.png");
		settings = new Texture("menuItems/settings.png");

        oneUp = new Texture("powers/oneup-2.png");
        speedUp = new Texture("powers/speedUp.png");
        speedDown = new Texture("powers/speedDown.png");
        metalBall = new Texture("powers/ironpower.png");
        twox = new Texture("powers/2x.png");
        magic = new Texture("powers/magic.png");
        needle = new Texture("powers/needle.png");


        //   Settings  //

        sfx = new Texture("settings/sfx.png");
        sound = new Texture("settings/music.png");
        vibration = new Texture("settings/vibration.png");
        sensitivity = new Texture("settings/sensitivity.png");
        on = new Texture("settings/on.png");
        off = new Texture("settings/off.png");
        low = new Texture("settings/low.png");
        med = new Texture("settings/med.png");
        high = new Texture("settings/high.png");
        settingsBack = new Texture("settings/back.png");

        ///////////////////




		
		music = Gdx.audio.newMusic(Gdx.files.internal("sounds/mavericks.ogg"));
		music.setVolume(0.5f);
		music.setLooping(true);
        if (Settings.musicEnabled) {
            music.play();
        }
		
		thud = Gdx.audio.newSound(Gdx.files.internal("sounds/bounce.ogg"));
		explode = Gdx.audio.newSound(Gdx.files.internal("sounds/explode.ogg"));
        powerUp = Gdx.audio.newSound(Gdx.files.internal("sounds/Powerup.ogg"));
        click = Gdx.audio.newSound(Gdx.files.internal("sounds/click.ogg"));
        alarm = Gdx.audio.newSound(Gdx.files.internal("sounds/alarm.ogg"));
		
		blastEffect = new ParticleEffect();
		blastEffect.load(Gdx.files.internal("effects/effext.p"), Gdx.files.internal("effects"));
		blastEffect.start();

        magicEffect = new ParticleEffect();
        magicEffect.load(Gdx.files.internal("effects/effext.p"), Gdx.files.internal("effects"));
        magicEffect.start();
		
	}
	
	public static void playSound(Sound sound){
		sound.play(1);
	}
	
	public static void particleSetPosition(ParticleEffect effect,float x,float y){
		effect.setPosition(x, y);	
	}
	
	public static void playEffect(ParticleEffect effect,SpriteBatch batch){
		effect.update(Gdx.graphics.getDeltaTime());
		effect.draw(batch);
	}
	
	public static boolean isEffectCompleted(ParticleEffect effect) {
		// TODO Auto-generated method stub
//		System.out.println(effect.isComplete());
		return effect.isComplete();
		
	}

    public static void stopMusic(){
        music.stop();
    }

    public static void playMusic(){
        music.setVolume(0.5f);
        music.setLooping(true);
        music.play();
    }

}
