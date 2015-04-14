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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class PlayScreen implements Screen{

    private static Game game;
    private static Ball ball;
    private static PlatformManager platforms;
    private static World world;
    private static OrthographicCamera cam;
    private static SpriteBatch batch;
    private static Box2DDebugRenderer b2dr;

    private static TopSpikes topSpikes;
    private static TopBar topBar;
    private static Texture back;
    private static TextureRegion region;

    private static Texture pause;
    private static Texture pauseResume;
    private static Texture pauseRestart;
    private static Texture pauseMenu;
    private static Rectangle pauseResumeBound;
    private static Rectangle pauseRestartBound;
    private static Rectangle pauseMenuBound;
    private static Sprite pauseResumeSprite;
    private static Sprite pauseRestartSprite;
    private static Sprite pauseMenuSprite;

    public static Texture gameOver;
    public static Rectangle gameOverReplayBounds,gameOverMenuBounds,gameOverFacebookBounds;

    private Texture tapToStart;
    private Sprite tapToStartSprite;

    private BitmapFont font;
	
	final int gameStatePlay = 1;
	final int gameStatePause = 2;
	final int gameStateGameOver = 3;
	final int gameStateStart = 4;
	static int gameState  = 4;

    Vector3 touchPoint;

//	Texture background;
//	Sprite sprite;
	public static final float w = Gdx.graphics.getWidth();
    public static final float h = Gdx.graphics.getHeight();

    AdInterface fb;




	public PlayScreen(Game gam){
		this.game = gam;
		
		world = new World(Vars.gravity,true);
		world.setContactListener(new CollisionManager(world));


        touchPoint = new Vector3();
		
		cam = new OrthographicCamera();
    	cam.setToOrtho(false, Gdx.graphics.getWidth()/Vars.world2box, Gdx.graphics.getHeight()/Vars.world2box);
    	batch = new SpriteBatch();
    	b2dr = new Box2DDebugRenderer();
    	
    	ball = new Ball(world);
    	platforms = new PlatformManager(world);
    	topSpikes = new TopSpikes();
    	topBar = new TopBar();

        tapToStart = Assets.tapToStart;
        tapToStartSprite = new Sprite(tapToStart);
        tapToStartSprite.setSize(w * 0.8f, w * 0.8f *tapToStart.getHeight()/tapToStart.getWidth());
        tapToStartSprite.setPosition(w/2 - tapToStartSprite.getWidth()/2,h/2 - tapToStartSprite.getHeight()/2);
    	
//    	background = new Texture(Assets.background);
//    	background = Assets.background;
//    	sprite = new Sprite(background);
//    	sprite.setPosition(0, 0);
//    	sprite.setBounds(0, 0, 320, 480);

        this.setUpPauseScreen();
        this.setUpGameOverScreen();
    	
    	back = Assets.back;

    	region = new TextureRegion();
    	
    	gameState = gameStateStart;
        System.out.println(gameState);

    	Gdx.input.setCatchBackKey(true);
        Gdx.input.setCatchMenuKey(true);
    	
//    	/////////  starting drawing /////////
//    	
////		world.step(1/30f, 6, 2);
//		world.step(Gdx.graphics.getDeltaTime(), 6, 2);
//		b2dr.render(world, cam.combined);
//		
//		batch.begin();
////		batch.draw(Assets.background, 0, 0);
////		batch.draw(Assets.background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		ball.render(batch);
//		platforms.render(batch);
//		topSpikes.render(batch);
//		topBar.render(batch);
//		batch.end();
//		//////////////////////////
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyPressed(Input.Keys.MENU)){
            Settings.save();
        }

        if (gameState == gameStateStart){

            if (Gdx.input.isTouched()){
                gameState = gameStatePlay;
            }

            batch.begin();
            //		batch.draw(Assets.background, 0, 0);
            batch.draw(Assets.background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            ball.draw(batch);
            platforms.render(batch);
            topSpikes.render(batch);
            topBar.render(batch);
            tapToStartSprite.draw(batch);
            batch.end();

//            System.out.println("tapToStartDrawn");
        }

		
		if (gameState == gameStatePause){
			this.pause();
		}
		
		if (gameState == gameStatePlay){
			
		
			if (Gdx.input.isKeyPressed(Input.Keys.BACK) || Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
				gameState = gameStatePause;
			}
			
			if (Ball.getPlayers() < 0){
				Assets.blastEffect.reset();

				ball.renderBallEffect = false;
				Settings.highScoreUpdate(Score.getScore());
                gameState = gameStateGameOver;
                Settings.save();
			}
			
	//		world.step(1/30f, 6, 2);
			world.step(Gdx.graphics.getDeltaTime(), 6, 2);
//			b2dr.render(world, cam.combined);
			
			batch.begin();
	//		batch.draw(Assets.background, 0, 0);
			batch.draw(Assets.background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			ball.render(batch);
			platforms.render(batch);
			topSpikes.render(batch);
			topBar.render(batch);
			batch.end();
		}

        if (gameState == gameStateGameOver){
            handleGameStateOver();
        }
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
//		tapToStart.dispose();
        if (gameState != gameStateGameOver) {
            Settings.save();
        }
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
//		world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        Settings.save();
		if (Gdx.input.justTouched()){
            touchPoint.x = Gdx.input.getX();
            touchPoint.y = Gdx.graphics.getHeight() - Gdx.input.getY();
            pauseMenuOverlapTester();
		}
		
//		b2dr.render(world, cam.combined);
		
		batch.begin();
//		batch.draw(Assets.background, 0, 0);
		batch.draw(Assets.background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		ball.draw(batch);
		platforms.draw(batch);
		topSpikes.render(batch);
		topBar.render(batch);
        batch.draw(pause,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        pauseRestartSprite.draw(batch);
        pauseResumeSprite.draw(batch);
        pauseMenuSprite.draw(batch);
		batch.end();
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
        gameState = gameStatePause;
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
        Settings.save();
		
	}
	
	public static SpriteBatch getBatch(){
		return batch;	
	}

    private static void setUpPauseScreen(){

        pause = Assets.pauseScreen;
        pauseResume = Assets.pauseResume;
        pauseRestart = Assets.pauseRestart;
        pauseMenu = Assets.pauseMenu;

        pauseResumeBound = new Rectangle();
        pauseRestartBound = new Rectangle();
        pauseMenuBound = new Rectangle();

        pauseResumeSprite = new Sprite(pauseResume);
        pauseResumeSprite.setSize(w*0.46f,h*0.075f);
        pauseResumeSprite.setPosition(w/2 - pauseResumeSprite.getWidth()/2,getPositionforY(h*0.515f - pauseResumeSprite.getHeight()/2));
        pauseResumeBound.set(pauseResumeSprite.getX(), pauseResumeSprite.getY(), pauseResumeSprite.getWidth(), pauseResumeSprite.getHeight());

        pauseRestartSprite = new Sprite(pauseRestart);
        pauseRestartSprite.setSize(w*0.46f,h*0.075f);
        pauseRestartSprite.setPosition(w/2 - pauseRestartSprite.getWidth()/2,getPositionforY(h*0.6645f - pauseRestartSprite.getHeight()/2));
        pauseRestartBound.set(pauseRestartSprite.getX(), pauseRestartSprite.getY(), pauseRestartSprite.getWidth(), pauseRestartSprite.getHeight());


        pauseMenuSprite = new Sprite(pauseMenu);
        pauseMenuSprite.setSize(w*0.33125f,h*0.075f);
        pauseMenuSprite.setPosition(w/2 - pauseMenuSprite.getWidth()/2,getPositionforY(h*0.7968f - pauseMenuSprite.getHeight()/2));
        pauseMenuBound.set(pauseMenuSprite.getX(), pauseMenuSprite.getY(), pauseMenuSprite.getWidth(), pauseMenuSprite.getHeight());



    }

    private void pauseMenuOverlapTester(){
        if (OverlapTester.pointInRectangle(pauseResumeBound, touchPoint.x, touchPoint.y)) {
            if (Settings.sfxEnabled) {
                Assets.playSound(Assets.click);
            }
            if (Settings.vibratorEnabled){
                Gdx.input.vibrate(50);
            }
//            System.out.println(" " + playBounds.x + " " + playBounds.y + " " + touchPoint.x + " " + touchPoint.y);
            gameState = gameStateStart;

            return;
        }

        if (OverlapTester.pointInRectangle(pauseRestartBound, touchPoint.x, touchPoint.y)) {
            if (Settings.sfxEnabled) {
                Assets.playSound(Assets.click);
            }
            if (Settings.vibratorEnabled){
                Gdx.input.vibrate(50);
            }
//            System.out.println(" " + playBounds.x + " " + playBounds.y + " " + touchPoint.x + " " + touchPoint.y);
            gameState = gameStateStart;
            game.getScreen().dispose();
            game.setScreen(new PlayScreen(game));
//            tapToStart.dispose();

            return;
        }

        if (OverlapTester.pointInRectangle(pauseMenuBound, touchPoint.x, touchPoint.y)) {
            if (Settings.sfxEnabled) {
                Assets.playSound(Assets.click);
            }
            if (Settings.vibratorEnabled){
                Gdx.input.vibrate(50);
            }
//            System.out.println(" " + playBounds.x + " " + playBounds.y + " " + touchPoint.x + " " + touchPoint.y);
            game.setScreen(new MenuScreen(game));
            game.getScreen().dispose();

            return;
        }
    }

    private static float getPositionforY(float x){
        return h-x;
    }

    private void setUpGameOverScreen(){

        gameOver = Assets.gameOver;

        gameOverFacebookBounds = new Rectangle();
        gameOverReplayBounds = new Rectangle();
        gameOverMenuBounds = new Rectangle();

        gameOverMenuBounds.set(w * 0.2734f - w * 0.153f/2f , h * 0.6687f - h * 0.102f/2f , w * 0.153f , h * 0.102f );
        gameOverReplayBounds.set(w * 0.5f - w * 0.153f/2f , h * 0.6687f - h * 0.102f/2f , w * 0.153f , h * 0.102f );
        gameOverFacebookBounds.set(w * 0.714f - w * 0.153f/2f , h * 0.6687f - h * 0.102f/2f , w * 0.153f , h * 0.102f );

        font = new BitmapFont(Gdx.files.internal("font/font-72.fnt"));
        font.setScale((h * 0.1f)/font.getCapHeight());


    }

    private void handleGameStateOver(){

        if (Gdx.input.justTouched()){
            touchPoint.x = Gdx.input.getX();
            touchPoint.y = Gdx.input.getY();

            if (OverlapTester.pointInRectangle(gameOverMenuBounds, touchPoint.x, touchPoint.y)) {
                if (Settings.sfxEnabled) {
                    Assets.playSound(Assets.click);
                }
                if (Settings.vibratorEnabled){
                    Gdx.input.vibrate(100);
                }

                Settings.save();
                gameState = gameStateStart;
                game.setScreen(new MenuScreen(game));
                this.dispose();

            }

            if (OverlapTester.pointInRectangle(gameOverReplayBounds, touchPoint.x, touchPoint.y)) {
                if (Settings.sfxEnabled) {
                    Assets.playSound(Assets.click);
                }
                if (Settings.vibratorEnabled){
                    Gdx.input.vibrate(100);
                }

                Settings.save();
                gameState = gameStateStart;
                game.setScreen(new PlayScreen(game));
                this.dispose();

            }

            if (OverlapTester.pointInRectangle(gameOverFacebookBounds, touchPoint.x, touchPoint.y)) {
                if (Settings.sfxEnabled) {
                    Assets.playSound(Assets.click);
                }
                if (Settings.vibratorEnabled){
                    Gdx.input.vibrate(100);
                }

                Settings.save();
//                gameState = gameStateStart;
//                game.setScreen(new PlayScreen(game));
//                this.dispose();


                Main.setPost("I have just played Ball Blast and my high score is " + Settings.getHighScore() + ". C'mon beat my score. \n https://play.google.com/store/apps/details?id=com.vineet.ballfall");

            }
        }

        batch.begin();
        //		batch.draw(Assets.background, 0, 0);
        batch.draw(Assets.background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ball.draw(batch);
        platforms.draw(batch);
        topSpikes.render(batch);
        topBar.render(batch);
        batch.draw(gameOver,0,0,w,h);
        font.draw(batch,"" + Score.getScore(),w/2 - font.getBounds("" + Score.getScore()).width/2, h*0.56f - font.getBounds("" + Score.getScore()).height/2);
        batch.end();


    }


}
