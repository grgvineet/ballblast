package com.vineet.ballfall;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HighScoreScreen implements Screen {
	
	Texture back;
	SpriteBatch batch;
	Texture highScore;
	private static BitmapFont font;
	Game game;
	
	public HighScoreScreen(Game g){
		
		this.game = g;
		back = Assets.background;
		highScore = Assets.help;
		font = new BitmapFont();
		batch = new SpriteBatch();
		
		Gdx.input.setCatchBackKey(true);
	}
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
		
	}
	
	@Override
	public void render(float delta) {
		
		if (Gdx.input.isKeyPressed(Input.Keys.BACK) || Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			game.setScreen(new MenuScreen(game));
		}
		// TODO Auto-generated method stub
		batch.begin();
		batch.draw(back, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(highScore, 0 , Gdx.graphics.getHeight()*0.8f, Gdx.graphics.getWidth(), Gdx.graphics.getWidth()*highScore.getHeight()/highScore.getWidth());
//		batch.draw(highScore, 0, Gdx.graphics.getHeight()*0.85f);
		font.setColor(Color.BLACK);
		font.draw(batch, "" + Settings.getHighScore() , Gdx.graphics.getWidth()/2 , Gdx.graphics.getHeight() *0.5f );
		batch.end();
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		this.dispose();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
        Settings.save();
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
