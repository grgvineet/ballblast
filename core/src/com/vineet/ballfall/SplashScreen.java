package com.vineet.ballfall;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen implements Screen {

    Game game;
    Texture back;
    SpriteBatch batch;

    public SplashScreen(Game g){
        this.game = g;
//        back = Assets.splashScreen;
        batch = new SpriteBatch();



        Gdx.input.setCatchMenuKey(true);
    }


    @Override
    public void render(float delta) {

        Gdx.gl20.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if (Gdx.input.isKeyPressed(Input.Keys.MENU)){
            Settings.save();
        }

        if (Gdx.input.isTouched()){
            Settings.save();
            game.setScreen(new MenuScreen(game));
        }

        batch.begin();
        batch.draw(back,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();

//        System.out.println(Settings.musicEnabled);


    }

    @Override
    public void resize(int width, int height) {
        game.setScreen(new MenuScreen(game));
    }

    @Override
    public void show() {

   }

    @Override
    public void hide() {
//        Settings.save();
        dispose();
    }

    @Override
    public void pause() {
        Settings.save();
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        Settings.save();
//        batch.dispose();

    }
}