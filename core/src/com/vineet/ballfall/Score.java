package com.vineet.ballfall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Score {
	
	private static long score;
	private static BitmapFont font;
    public static int adder;
	
	public Score(){
		score = 0;
		font = new BitmapFont(Gdx.files.internal("font/font-72.fnt"));
        font.setScale((Gdx.graphics.getHeight() * 0.055f)/font.getCapHeight());
        adder = 1;
	}
	
	public void update(){
		score += adder;
	}
	
	public static long getScore(){
		return  score;
	}
	
	public static void render(SpriteBatch batch){

		font.draw(batch, "" + score , Gdx.graphics.getWidth() - font.getBounds("" + score).width - Gdx.graphics.getWidth() * 0.05f, Gdx.graphics.getHeight() *1.005f );

	}
	
	

}
