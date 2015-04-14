package com.vineet.ballfall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TopBar {
	
	Texture ball;
	Sprite sprite;
	Pixmap black;
    Texture blackTexture;

	public TopBar(){
		ball  = Assets.ball;
		sprite = new Sprite(ball);

        black = new Pixmap((int)Gdx.graphics.getWidth(),(int)(Gdx.graphics.getHeight()*0.1f), Pixmap.Format.RGBA8888);
        black.setColor(0,0,0,1);
        black.fill();

        blackTexture = new Texture(black, Pixmap.Format.RGBA8888,false);
	}
	
	public void render(SpriteBatch batch){

        batch.draw(blackTexture,0,Gdx.graphics.getHeight() * 0.945f);
		for (int i = 0 ; i < Ball.getPlayers() ; i++){
			batch.draw(ball, i * Gdx.graphics.getHeight() * 0.075f / 1.75f, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * 0.075f / 1.7f) , Gdx.graphics.getHeight() * 0.075f / 2, Gdx.graphics.getHeight() * 0.075f / 2);
		}
		Score.render(batch);	
	}

	public static float getLowerLimit() {
		// TODO Auto-generated method stub
		return (Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * 0.075f / 1.75f) - (Gdx.graphics.getHeight() * 0.075f / 2))/Vars.world2box - BallProperties.radius;
	}

}
