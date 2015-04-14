package com.vineet.ballfall;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MenuScreen implements Screen {

    Game game;
    
    private Texture play;
    private Texture about;
    private Texture help;
    private Texture settings;
    private Texture back;
    private Texture sureToExit;
    private Texture title;


    
    private Sprite playSprite;
    private Sprite aboutSprite;
    private Sprite settingsSprite;
    private Sprite helpSprite;
    private Sprite sureToExitSprite;
    private Sprite titleSprite;
    
    
    Rectangle playBounds;
    Rectangle aboutBounds;
    Rectangle settingsBounds;
    Rectangle helpBounds;
    Rectangle yesBounds;
    Rectangle noBounds;

    BitmapFont font;
    
    Vector3 touchPoint;
    
    private OrthographicCamera cam;
    private SpriteBatch batch;
    
    private static final float height = Gdx.graphics.getHeight();
    private static final float width = Gdx.graphics.getWidth();
    private final float aspectRatio = width/height;

    private int state;
    private final int menuState = 1;
    private final int exitState = 2;
    
    public MenuScreen(Game g){
        this.game=g;
        state = menuState;
        create();
        
    }
 
    public void create(){
        
    	play = Assets.play;
    	about = Assets.about;
    	help = Assets.help;
    	settings = Assets.settings;
    	back = Assets.background;
        sureToExit = Assets.sureToExit;
        title = new Texture("title.png");


    	
    	cam = new OrthographicCamera(width , height);
    	cam.position.set(new Vector3(width/2,height/2,0));
    	
    	playBounds = new Rectangle();
    	settingsBounds = new Rectangle();
    	helpBounds = new Rectangle();
    	aboutBounds = new Rectangle();
        yesBounds = new Rectangle();
        noBounds = new Rectangle();
    	touchPoint = new Vector3();

        font = new BitmapFont(Gdx.files.internal("font/font-72.fnt"));
        font.setScale((height * 0.05f)/font.getCapHeight());

        titleSprite = new Sprite(title);
//        titleSprite.setSize(((float)title.getWidth()/title.getHeight())*height * 0.314f, height * 0.314f);
        titleSprite.setSize(width,((float)title.getHeight()/title.getWidth())*width );
        titleSprite.setPosition(getPositionForX(width * 0.5f) - (titleSprite.getWidth()/2f), getPositionForY(height * 0.26354f) - titleSprite.getHeight()/2f );
    	
    	playSprite = new Sprite(play);
//    	playSprite.setScale(height * 0.1f);
    	playSprite.setSize(((float)play.getWidth()/play.getHeight())*height * 0.0708f, height * 0.0708f);
    	playSprite.setPosition(getPositionForX(width * 0.5f) - (playSprite.getWidth()/2f), getPositionForY(height * 0.5260f) - playSprite.getHeight()/2f );
    	playBounds.set(playSprite.getX(), playSprite.getY(), playSprite.getWidth(), playSprite.getHeight());
    	

    	settingsSprite = new Sprite(settings);
//    	playSprite.setScale(height * 0.1f);
    	settingsSprite.setSize(((float)settings.getWidth()/settings.getHeight())*height * 0.0708f, height * 0.0708f);
    	settingsSprite.setPosition(getPositionForX(width * 0.5f) - (settingsSprite.getWidth()/2f), getPositionForY(height * 0.636f) - settingsSprite.getHeight()/2f );
    	settingsBounds.set(settingsSprite.getX(), settingsSprite.getY(), settingsSprite.getWidth(), settingsSprite.getHeight());
    	

    	helpSprite = new Sprite(help);
//    	playSprite.setScale(height * 0.1f);
    	helpSprite.setSize(((float)help.getWidth()/help.getHeight())*height * 0.0708f, height * 0.0708f);
    	helpSprite.setPosition(getPositionForX(width * 0.5f) - (helpSprite.getWidth()/2f), getPositionForY(height * 0.8489f) - helpSprite.getHeight()/2f );
    	helpBounds.set(helpSprite.getX(), helpSprite.getY(), helpSprite.getWidth(), helpSprite.getHeight());
    	

    	aboutSprite = new Sprite(about);
//    	playSprite.setScale(height * 0.1f);
    	aboutSprite.setSize(((float)about.getWidth()/about.getHeight())*height * 0.0708f, height * 0.0708f);
    	aboutSprite.setPosition(getPositionForX(width * 0.5f) - (aboutSprite.getWidth()/2f), getPositionForY(height * 0.739f) - aboutSprite.getHeight()/2f );
    	aboutBounds.set(aboutSprite.getX(), aboutSprite.getY(), aboutSprite.getWidth(), aboutSprite.getHeight());

        sureToExitSprite = new Sprite(sureToExit);
        sureToExitSprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        sureToExitSprite.setPosition(0,0);
        yesBounds.set(width * 0.353f - width * 0.132f/2f,height * 0.5531f - height * 0.0541f/2,width * 0.132f,height * 0.0541f);
        noBounds.set(width * 0.617f - width * 0.0912f/2f,height * 0.5531f - height * 0.0541f/2f,width * 0.0912f,height * 0.0541f);

    	
    	batch = new SpriteBatch();

        Gdx.input.setCatchBackKey(true);
        Gdx.input.setCatchMenuKey(true);
    	
    	
 
    }
    
    public void update(){

        if(Gdx.input.isKeyPressed(Input.Keys.MENU)){
            Settings.save();
        }
    	
    	if (Gdx.input.justTouched() && state == menuState) {
//    		cam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 0));
    		touchPoint.x = Gdx.input.getX();
    		touchPoint.y = Gdx.graphics.getHeight() - Gdx.input.getY();
    		if (OverlapTester.pointInRectangle(playBounds, touchPoint.x, touchPoint.y)) {
                if (Settings.sfxEnabled){
                    Assets.playSound(Assets.click);
                }
                if (Settings.vibratorEnabled){
                    Gdx.input.vibrate(50);
                }
//    			System.out.println(" " + playBounds.x + " " + playBounds.y + " " + touchPoint.x + " " + touchPoint.y);
				game.setScreen(new PlayScreen(game));
//				game.getScreen().dispose();
				return;
			}
    		
    		if (OverlapTester.pointInRectangle(helpBounds, touchPoint.x, touchPoint.y)) {
                if (Settings.sfxEnabled){
                    Assets.playSound(Assets.click);
                }
                if (Settings.vibratorEnabled){
                    Gdx.input.vibrate(50);
                }
//    			System.out.println(" " + playBounds.x + " " + playBounds.y + " " + touchPoint.x + " " + touchPoint.y);
				game.setScreen(new Help(game));
//                game.getScreen().dispose();
				
				return;
			}

            if (OverlapTester.pointInRectangle(settingsBounds, touchPoint.x, touchPoint.y)) {
                if (Settings.sfxEnabled){
                    Assets.playSound(Assets.click);
                }
                if (Settings.vibratorEnabled){
                    Gdx.input.vibrate(50);
                }
//    			System.out.println(" " + playBounds.x + " " + playBounds.y + " " + touchPoint.x + " " + touchPoint.y);
                game.setScreen(new SettingsScreen(game));
//                game.getScreen().dispose();

                return;
            }
    		
    		
    		
    		if (OverlapTester.pointInRectangle(aboutBounds, touchPoint.x, touchPoint.y)) {
                if (Settings.sfxEnabled){
                    Assets.playSound(Assets.click);
                }
                if (Settings.vibratorEnabled){
                    Gdx.input.vibrate(50);
                }
    			System.out.println(" " + aboutBounds.x + " " + aboutBounds.y + " " + touchPoint.x + " " + touchPoint.y);
//				game.setScreen(new PlayScreen(game));
    			game.setScreen(new AboutScreen(game));
//                game.getScreen().dispose();
				
				return;
			}
    		
    		System.out.println(" " + playBounds.x + " " + playBounds.y + " " + touchPoint.x + " " + touchPoint.y);

            if (Gdx.input.isKeyPressed(Input.Keys.BACK) || Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
                if (Settings.sfxEnabled){
                    Assets.playSound(Assets.click);
                }
                state = exitState;
            }

    	}

        if (Gdx.input.isKeyPressed(Input.Keys.BACK) || Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            if (state == menuState){
                if (Settings.sfxEnabled){
                    Assets.playSound(Assets.click);
                }
                state = exitState;
            }
        }


        if (Gdx.input.justTouched() && state == exitState){
            if (Gdx.input.isTouched()){
                touchPoint.x = Gdx.input.getX();
                touchPoint.y = Gdx.input.getY();
                if (OverlapTester.pointInRectangle(yesBounds, touchPoint.x, touchPoint.y)) {
                    if (Settings.sfxEnabled){
                        Assets.playSound(Assets.click);
                    }
                    Settings.save();
                    Main.fbInterface.showAd();
                    Gdx.app.exit();
                }

                if (OverlapTester.pointInRectangle(noBounds, touchPoint.x, touchPoint.y)) {
                    if (Settings.sfxEnabled){
                        Assets.playSound(Assets.click);
                    }
                    state = menuState;
                }
            }
        }


    	
    	
    }
 
    public void render (float delta) {
    	
    	update();
    	
        Gdx.gl.glClearColor(0.0f, 0.0f, 1.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        batch.draw(back, 0, 0, width, height);
        titleSprite.draw(batch);
        playSprite.draw(batch);
        settingsSprite.draw(batch);
        helpSprite.draw(batch);
        aboutSprite.draw(batch);
        font.draw(batch, "" + Settings.getHighScore() , Gdx.graphics.getWidth() * 0.9703f - font.getBounds("" + Settings.getHighScore()).width ,getPositionForY(height * 0.0416f) + font.getBounds("" + Settings.getHighScore()).height/2 );
        if (state == exitState){
            sureToExitSprite.draw(batch);
        }
        batch.end();
    }
 
    @Override
    public void resize (int width, int height) {
        playBounds.set(playSprite.getX(), playSprite.getY(), playSprite.getWidth(), playSprite.getHeight());
        settingsBounds.set(settingsSprite.getX(), settingsSprite.getY(), settingsSprite.getWidth(), settingsSprite.getHeight());
        helpBounds.set(helpSprite.getX(), helpSprite.getY(), helpSprite.getWidth(), helpSprite.getHeight());
        aboutBounds.set(aboutSprite.getX(), aboutSprite.getY(), aboutSprite.getWidth(), aboutSprite.getHeight());
        yesBounds.set(width * 0.353f - width * 0.132f/2f,height * 0.5531f - height * 0.0541f/2,width * 0.132f,height * 0.0541f);
        noBounds.set(width * 0.617f - width * 0.0912f/2f,height * 0.5531f - height * 0.0541f/2f,width * 0.0912f,height * 0.0541f);
    }
 
    @Override
    public void dispose () {
//        batch.dispose();
        Settings.save();
    }
 
    @Override
    public void show() {
        // TODO Auto-generated method stub
 
    }
 
    @Override
    public void hide() {
        // TODO Auto-generated method stub
//        Settings.save();
//    	dispose();
 
    }
 
    @Override
    public void pause() {
        // TODO Auto-generated method stub
        Settings.save();
 
    }
 
    @Override
    public void resume() {
        // TODO Auto-generated method stub
        render(Gdx.graphics.getDeltaTime());
    }

    public static float getPositionForX(float x){
        return x;
    }

    public static float getPositionForY(float y){
        return height - y;
    }
}
