package com.vineet.ballfall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

/**
 * Created by vineet on 20-Jul-14.
 */
public class Powers {

    private World world;

    private static Texture powerTexture;
    private static Sprite sprite;
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    private Fixture fixture;
    private Body body;

    private static Random  ran;
    private int nextInt;

    public  static  final int oneUp = 0;
    public  static  final int speedUp = 1;
    public  static  final int speedDown = 2;
    public  static  final int metalBall = 3;
    public static final int magic = 4;
    public static final int twox = 5;

    public int powerType;

    public boolean isPowerTaken;

    public PowerTimer timer;

    private static float positionX,positionY,speedY;
    private static float spriteSetX,spriteSetY;

    public Powers(World w) {
        this.world = w;

        timer = new PowerTimer();

        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();


        ran = new Random(System.currentTimeMillis());

        sprite = new Sprite(Assets.ball);


        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(5000,5000);

        body = world.createBody(bodyDef);
//        body.setUserData(this);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(BallProperties.radius, BallProperties.radius);

        fixtureDef.shape = shape;
//        fixtureDef.isSensor = true;
        fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);

        sprite.setTexture(Assets.ball);
        sprite.setSize(BallProperties.radius * Vars.world2box * 2,BallProperties.radius  * Vars.world2box * 2);
        sprite.setPosition(Gdx.graphics.getWidth() * 2,Gdx.graphics.getHeight() * 2);

        isPowerTaken = false;



    }


    public void generate(float x,float y,float speed) {

        int randomInt;
        body.setTransform(
                (ran.nextFloat() *(PlatformProperties.width*Vars.world2box )+ x - PlatformProperties.width*Vars.world2box/2f )/Vars.world2box,
                y/Vars.world2box,
                0
        );
        System.out.println("body position " + body.getPosition().x + " " + body.getPosition().y);

        randomInt = ran.nextInt(110);

        if (randomInt <10){
            powerType = speedDown;
            sprite.setTexture(Assets.speedDown);
        }else if (randomInt < 30){
            powerType = oneUp;
            sprite.setTexture(Assets.oneUp);
        }else if (randomInt < 50){
            powerType = speedUp;
            sprite.setTexture(Assets.speedUp);
        }else if (randomInt < 70){
            powerType = metalBall;
            sprite.setTexture(Assets.metalBall);
        }else if (randomInt < 90){
            powerType = twox;
            sprite.setTexture(Assets.twox);
        }else if (randomInt < 110){
            powerType = magic;
            sprite.setTexture(Assets.magic);
        }

    }

    public void draw(SpriteBatch batch){
        timer.draw(batch);
        sprite.draw(batch);

    }

    public void update(){

        timer.update();

        if (isPowerTaken){
            body.setTransform(5000,5000,0);
            isPowerTaken = false;
        }

        sprite.setPosition(body.getPosition().x * Vars.world2box - sprite.getWidth()/2f,body.getPosition().y * Vars.world2box - sprite.getHeight()/2f);
        speedY += Gdx.graphics.getDeltaTime()/200f;

        if (body.getPosition().y > Gdx.graphics.getHeight()/Vars.world2box){
            body.setTransform(5000,5000,0);
        }
//        sprite.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);

//        System.out.println(" " + sprite.getX() + " " + sprite.getY());

    }

    public void render(SpriteBatch batch){

//        timer.render(batch);
        update();
        draw(batch);

    }

    public void powerTaken(){
        isPowerTaken = true;
        switch (powerType){
            case oneUp:
                Ball.oneUp();
                break;

            case speedUp:
                PlatformManager.speedUp();
                break;

            case speedDown:
                PlatformManager.speedDown();
                break;

            case metalBall:
                timer.metalBallTaken();
                break;

            case magic:
                timer.magicTaken();
                break;

            case twox:
                timer.twoxTaken();
                break;
        }
        if (Settings.sfxEnabled){
            Assets.playSound(Assets.powerUp);
        }
    }
}
