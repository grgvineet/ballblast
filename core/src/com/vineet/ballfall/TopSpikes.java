package com.vineet.ballfall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TopSpikes {
	
	private Texture texture;
	private Sprite sprite;
	private float position ;
	
	public TopSpikes(){
		
		position = 0;
		
		texture = Assets.spike;
		sprite = new Sprite(texture);
	}
	
	public void render(SpriteBatch batch){
		
		position = 0;
		
		for (int i = 0 ; i < Gdx.graphics.getWidth() ; i += Gdx.graphics.getWidth()/30f){
//			batch.draw(texture, i, Gdx.graphics.getWidth()/0.85f);
			batch.draw(texture, i, Gdx.graphics.getHeight()*0.925f, Gdx.graphics.getWidth()/30f, Gdx.graphics.getWidth()/30f);
		}
		
	}

	public static float getHeight() {
		// TODO Auto-generated method stub
		return Gdx.graphics.getWidth()/30f;
	}

}
