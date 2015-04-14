package com.vineet.ballfall;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by vineet on 29-Aug-14.
 */
public class Help implements Screen {

    Game game;
    Texture background,next;
    Sprite nextSprite;
    SpriteBatch batch;

    float touchX,touchY;

    final float height = Gdx.graphics.getHeight();
    final float width = Gdx.graphics.getWidth();

    public Help(Game game) {
        this.game = game;

        background = new Texture("help.png");
        next = new Texture("next.png");
        nextSprite = new Sprite(next);
        batch = new SpriteBatch();
    }

    @Override
    public void show() {
        nextSprite.setSize(((float)next.getWidth()/next.getHeight())*Gdx.graphics.getHeight() * 0.059375f, Gdx.graphics.getHeight() * 0.059375f);
        nextSprite.setPosition(width - (nextSprite.getWidth()), getPositionForY(height * 0.4703f) - nextSprite.getHeight()/2f );
    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isTouched()){
            touchX = Gdx.input.getX();
            touchY = Gdx.input.getY();
            touchY = Gdx.graphics.getHeight() - touchY;

            if (touchX>= nextSprite.getX() && touchX <= nextSprite.getX() + nextSprite.getWidth()
                    && touchY >= nextSprite.getY() && touchY<= nextSprite.getY() + nextSprite.getHeight()){
                if (Settings.sfxEnabled){
                    Assets.playSound(Assets.click);
                }
                game.setScreen(new HelpPowers(game));
                dispose();
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.BACK) || Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            if (Settings.sfxEnabled){
                Assets.playSound(Assets.click);
            }
            game.setScreen(new MenuScreen(game));
            dispose();
        }

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        nextSprite.draw(batch);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }



    @Override
    public void hide() {
//        dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        background.dispose();
        next.dispose();
//        batch.dispose();
    }

    public float getPositionForY(float y){
        return height - y;
    }
}
