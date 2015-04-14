package com.vineet.ballfall;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by vineet on 23-Jul-14.
 */
public class AboutScreen implements Screen {

    Game game;
    Texture texture;
    SpriteBatch batch;

    public AboutScreen(Game g){
        this.game = g;

        texture = Assets.aboutScreen;
        batch = new SpriteBatch();

        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta) {

        Gdx.gl20.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyPressed(Input.Keys.BACK) || Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            if (Settings.sfxEnabled) {
                Assets.playSound(Assets.click);
            }
            game.setScreen(new MenuScreen(game));
            game.getScreen().dispose();
        }

        batch.begin();
        batch.draw(texture,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

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
}
