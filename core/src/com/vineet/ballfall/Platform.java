package com.vineet.ballfall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

public class Platform {
	
	private BodyDef bdef;
	private FixtureDef fdef;
	private Body body;
	private World world;
	private Random ran;
	private Texture texture;
	private Sprite sprite;
	private Fixture fixture;
	private float speed;
	private float positionY;
	private static float forBallSpeed;
	private int type;
	public boolean playmusic;
	public static final float min = (PlatformProperties.width/2)* Vars.world2box;
	public static final float max = ((Gdx.graphics.getWidth()) - min);
	
	public Platform(World worl,float spee,float position,int typ){
		
		this.world = worl;
		this.speed = spee;
		this.positionY = position;
		this.type = typ;
		
		bdef = new BodyDef();
    	fdef = new FixtureDef();
    	ran = new Random();
    	
    	playmusic = true;
    	
//    	fdef.filter.categoryBits = Vars.platformBit;
//    	fdef.filter.categoryBits = -1 ;
    	
    	if (type == PlatformProperties.platform){

            /////////////////////////////////////   CREATING SIMPLE PLATFORM    //////////////////////////////////////////////////////////////////////////

    		texture = Assets.platform;
    		
    		sprite = new Sprite(texture);
        	
        	sprite.setSize(PlatformProperties.width* Vars.world2box, PlatformProperties.height* Vars.world2box);
        	
        	bdef.position.set((ran.nextInt((int) (max - min + 1))+min)/ Vars.world2box, positionY);
        	bdef.type = BodyType.KinematicBody;
//        	bdef.linearVelocity.set(0, speed);
        	
        	body = world.createBody(bdef);

        	
        	body.setLinearVelocity(0, speed);
        	
        	PolygonShape shape = new PolygonShape();
        	shape.setAsBox((PlatformProperties.width/2), (PlatformProperties.height/2));
        	
//        	ChainShape shape = new ChainShape();
//        	Vector2[] v = new Vector2[2];
//        	v[0] = new Vector2(body.getPosition().x - (PlatformProperties.width/2f),body.getPosition().y + PlatformProperties.height/2);
//        	v[1] = new Vector2(body.getPosition().x + (PlatformProperties.width/2f),body.getPosition().y + PlatformProperties.height/2);
//        	shape.createChain(v);
        	
        	FixtureDef pfdef = new FixtureDef();
        	pfdef.shape = shape;
        	fixture = body.createFixture(pfdef);
        	
        	shape.dispose();
        	
    	}else{

            /////////////////////////////////////   CREATING SPIKED PLATFORM    //////////////////////////////////////////////////////////////////////////

    		texture = Assets.platformSpikes;
    	
	    	sprite = new Sprite(texture);
	    	
	    	sprite.setSize(PlatformProperties.width* Vars.world2box, (float) (1.5 * PlatformProperties.height* Vars.world2box));
	    	
	    	bdef.position.set((ran.nextInt((int) (max - min + 1))+min)/ Vars.world2box, positionY);
	    	bdef.type = BodyType.KinematicBody;
	//    	bdef.linearVelocity.set(0, speed);
	    	
	    	body = world.createBody(bdef);
	    	
	    	body.setLinearVelocity(0, speed);
	    	
	    	PolygonShape shape = new PolygonShape();
	    	shape.setAsBox((PlatformProperties.width/2), 1.5f * (PlatformProperties.height/2));
	    	
	    	FixtureDef pfdef = new FixtureDef();
	    	pfdef.shape = shape;
	    	fixture = body.createFixture(pfdef);
	    	
	    	shape.dispose();
    	}
    	
    	fixture.setUserData(this);
    	
		
	}
	
	
	
	////    getters   ////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public Vector2 getPosition(){
		return body.getPosition();
	}
	
	public static float getSpeedForBall() {
		// TODO Auto-generated method stub
		return forBallSpeed;
	}
	
	public  float getSpeed(){
		return body.getLinearVelocity().y;
	}
	
	public int getType(){
		return type;
	}
	
	public float getHeight(){
		return PlatformProperties.height;
	}

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////   SETTERS  /////////////////////////////////////

    public void speedUp(){
        speed = body.getLinearVelocity().y * 1.20f;
        System.out.println("pedal velocity " + body.getLinearVelocity().y);
        body.setLinearVelocity(0,body.getLinearVelocity().y * 1.20f);
    }

    public void speedDown(){
        speed = body.getLinearVelocity().y * 0.80f;
        body.setLinearVelocity(0,body.getLinearVelocity().y * 0.80f);

    }
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/////// updating and drawing ////////////////////////////////////////////////////////////////////////////////////////
	
	public void update(){
		
		speed += Gdx.graphics.getDeltaTime()/200f;
		body.setLinearVelocity(0, speed);
		forBallSpeed = speed;
		
	}
	
	private void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
		if (type == PlatformProperties.platform){
			
			sprite.setPosition((body.getPosition().x-(PlatformProperties.width/2))* Vars.world2box, (body.getPosition().y-(PlatformProperties.height/2))* Vars.world2box);
		}else{
			sprite.setPosition((body.getPosition().x-(PlatformProperties.width/2))* Vars.world2box, (body.getPosition().y- 1.5f *(PlatformProperties.height/2))* Vars.world2box);
			
		}
		sprite.draw(batch);
		
	}
	
	public void render(SpriteBatch batch){
		update();
		draw(batch);
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
    public void destroy(){
        System.out.println(world.getBodyCount());
        world.destroyBody(body);

    }


}
