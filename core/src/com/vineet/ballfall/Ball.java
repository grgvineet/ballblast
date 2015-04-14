package com.vineet.ballfall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Ball {
	
	private Texture ballTexture;
	private Sprite sprite;
	
	private World world;
	private static Body body;
	private BodyDef bdef;
	private FixtureDef fdef;
	private Shape circle;
	private Fixture fixture;
	private static float speedY;
	private float speedX;
	private float angularVelocity,angle;
	private boolean renderBallEffects;
	private static int players;
	private static boolean ready2die;
	
	public static boolean isCollidedWithPlatformSpike;
	public static boolean renderBallEffect;
	public static Vector2 effectPosition;

    public static int type;
    public static boolean magicEnabled;

    private Queue<MagicParticleEffect> magicEffects;
    private Iterator iter;
	
	public Ball(World world){
		
		this.world = world;
		
		ballTexture = Assets.ball;
		sprite = new Sprite(ballTexture);
		bdef = new BodyDef();
		fdef = new FixtureDef();
		circle = new CircleShape();
		
		players = 3;
		ready2die = true;
		isCollidedWithPlatformSpike = false;
		renderBallEffect = false;
		effectPosition = new Vector2();

        type = BallProperties.typeSimple;
        magicEnabled = false;

        magicEffects = new LinkedList<MagicParticleEffect>();
		
		create();
		
	}

	private void create() {
		// TODO Auto-generated method stub
		
		speedX = Gdx.graphics.getWidth()/160;
		angle = 0;
		
		sprite.setSize(2*BallProperties.radius*Vars.world2box, 2*BallProperties.radius*Vars.world2box);
		
		circle.setRadius(BallProperties.radius);
		
		bdef.position.set(BallProperties.startingPositionX, BallProperties.startingPositionY);
		bdef.type = BodyType.DynamicBody;
		
		
		body = world.createBody(bdef);	
		body.setUserData(sprite);
		
		fdef.shape = circle ;
		fdef.density = BallProperties.density;
		fdef.friction = BallProperties.friction;
//		fdef.filter.categoryBits =  Vars.ballBit;
//		fdef.filter.maskBits = -1;
//		fdef.restitution = 1.0f;

		fixture = body.createFixture(fdef);
		fixture.setUserData(this);
		
		
		sprite.setOriginCenter();
		
		circle.dispose();
		
		renderBallEffects = false;
		Assets.blastEffect.reset();
	}
	
	public void render(SpriteBatch batch){
		
		speedY = -1 * Platform.getSpeedForBall();
		speedX += Gdx.graphics.getDeltaTime()/200f;
		update();
		draw(batch);
		
	}
	
	public void update() {
		// TODO Auto-generated method stub

        body.setLinearVelocity(0, speedY);

        if (type == BallProperties.typeSimple){
            sprite.setTexture(Assets.ball);
        }else{
            sprite.setTexture(Assets.ironBall);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.R)){
            players = 3;
            ready2die = true;
            body.setTransform((Gdx.graphics.getWidth()/2f)/Vars.world2box,(Gdx.graphics.getHeight()/2f)/Vars.world2box,0 );
        }

    /////////////////////////////      MOVING BODY     //////////////////////////////////////////////////////////////////////////////////

        if (body.getPosition().y > Gdx.graphics.getHeight()*0.05/Vars.world2box){
            ready2die = true;
//        	body.applyForceToCenter(-1* BallProperties.horizontalForce * Gdx.input.getAccelerometerX() /5, 0, true);
            body.setTransform(body.getPosition().x + (-1 * speedX * Settings.sensitivity* Gdx.graphics.getDeltaTime() * 100 * Gdx.input.getAccelerometerX() /200),body.getPosition().y, 0);
            angularVelocity = -1 * speedX/(BallProperties.radius/ Vars.world2box);
            angle +=  -1 * angularVelocity * (Gdx.input.getAccelerometerX() /400);
            body.setLinearVelocity(0, speedY);

            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
//        		body.applyForceToCenter(-1 * BallProperties.horizontalForce, 0, true);
//    			body.applyForce(new Vector2(5000,0), body.getPosition(), true);
                body.setTransform(body.getPosition().x - speedX* Settings.sensitivity * Gdx.graphics.getDeltaTime(), body.getPosition().y, 0);
//        		body.applyTorque(BallProperties.torque, true);
                body.setLinearVelocity(0, speedY);
//        		body.setAngularVelocity(0);
//        		body.applyTorque(50000000, true);
//        		System.out.println("z");
//        		angle += 100 * Gdx.graphics.getDeltaTime();
                angularVelocity =  speedX/(BallProperties.radius / Vars.world2box);
                angle +=  angularVelocity * (Gdx.graphics.getDeltaTime()/4f);
            }


            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
//        		body.applyForceToCenter(BallProperties.horizontalForce, 0, true);
                body.setTransform(body.getPosition().x + speedX* Settings.sensitivity * Gdx.graphics.getDeltaTime(), body.getPosition().y, 0 );
//        		body.applyTorque(-1 * BallProperties.torque, true);
                body.setLinearVelocity(0, speedY);
//        		body.setAngularVelocity(0);

//        		body.applyTorque(-50000000, false);
//        		System.out.println("x");
//        		angle -= 100 * Gdx.graphics.getDeltaTime();
                angularVelocity = -1 * speedX/(BallProperties.radius/ Vars.world2box);
                angle +=  angularVelocity * (Gdx.graphics.getDeltaTime()/4f);
            }

        }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////   CHECKING IF DEAD       /////////////////////////////////////////////////////////////////////////////////////

    	if(body.getPosition().y < 0 && ready2die){
    		players --;

            magicEnabled = false;

            PowerTimer.magicEnabled = false;
            PowerTimer.metalBallEnabled = false;
            PowerTimer.twoxEnabled = false;
            Score.adder = 1;

            if (Settings.vibratorEnabled){
                Gdx.input.vibrate(100);
            }

            type = BallProperties.typeSimple;

    		ready2die = false;
    		body.setTransform(PlatformManager.getPositionForBall().x, PlatformManager.getPositionForBall().y, 0);
    		body.setLinearVelocity(0, speedY);
    	}

/*    	if(body.getPosition().y > (Gdx.graphics.getHeight()/Vars.world2box)){
    		body.setTransform(body.getPosition().x,Gdx.graphics.getHeight()/Vars.world2box - BallProperties.radius, 0);
//    		body.setTransform(200/Vars.world2box, 200/Vars.world2box, 0);
    		body.setLinearVelocity(0, speedY);
    	}
    	
*/
    	if (body.getPosition().y > TopBar.getLowerLimit() ){
            if (type == BallProperties.typeSimple) {
                players--;

                type = BallProperties.typeSimple;

                magicEnabled = false;

                PowerTimer.twoxEnabled = false;
                PowerTimer.metalBallEnabled = false;
                PowerTimer.magicEnabled = false;
                Score.adder = 1;


                if (Settings.sfxEnabled) {
                    Assets.playSound(Assets.explode);
                }
                if (Settings.vibratorEnabled) {
                    Gdx.input.vibrate(100);
                }
                renderBallEffects = true;
                ready2die = false;
                effectPosition.x = body.getPosition().x * Vars.world2box;
                effectPosition.y = body.getPosition().y * Vars.world2box;
                Assets.particleSetPosition(Assets.blastEffect,effectPosition.x, effectPosition.y);
                body.setTransform(PlatformManager.getPositionForBall().x, PlatformManager.getPositionForBall().y, 0);
                body.setLinearVelocity(0, speedY);
            }else if (type == BallProperties.typeMetal){
                System.out.println(type);
//                body.setTransform(body.getPosition().x,body.getPosition().y - 2 * TopBar.getLowerLimit(),0);
                body.setTransform(body.getPosition().x,body.getPosition().y - sprite.getHeight()/Vars.world2box,0);
            }

    	}

        if (isCollidedWithPlatformSpike){
            players --;

            type = BallProperties.typeSimple;
            magicEnabled = false;

            PowerTimer.twoxEnabled = false;
            PowerTimer.metalBallEnabled = false;
            PowerTimer.magicEnabled = false;
            Score.adder = 1;

            if(Settings.sfxEnabled) {
                Assets.playSound(Assets.explode);
            }
            if (Settings.vibratorEnabled){
                Gdx.input.vibrate(100);
            }
            renderBallEffects = true;
            ready2die = false;
            isCollidedWithPlatformSpike = false;
            effectPosition.x = body.getPosition().x * Vars.world2box;
            effectPosition.y = body.getPosition().y * Vars.world2box;
            Assets.particleSetPosition(Assets.blastEffect,effectPosition.x, effectPosition.y);
            body.setTransform(PlatformManager.getPositionForBall().x, PlatformManager.getPositionForBall().y, 0);
            body.setLinearVelocity(0, speedY);
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /////////////////////////////////////     RIGHT LEFT BOUND //////////////////////////////////////////////////////////////////////////

    	if (body.getPosition().x > Gdx.graphics.getWidth()/Vars.world2box){
    		body.setTransform(0,body.getPosition().y, 0);
//    		body.setTransform(200/Vars.world2box, 200/Vars.world2box, 0);
    		body.setLinearVelocity(0, speedY);
    	}
    	
    	if (body.getPosition().x < 0){
    		body.setTransform(Gdx.graphics.getWidth()/Vars.world2box,body.getPosition().y, 0);
//    		body.setTransform(200/Vars.world2box, 200/Vars.world2box, 0);
    		body.setLinearVelocity(0, speedY);
    	}

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /////////////////////////////////////   CHECKING BURST EFFECT //////////////////////////////////////////////////////////////////////////

    	if (Assets.isEffectCompleted(Assets.blastEffect)){
    		
    		if(renderBallEffects){
	    		renderBallEffects = false;
	    		Assets.blastEffect.reset();;
	    		System.out.println("effect completed");
	    		effectPosition.x = Gdx.graphics.getWidth() * 100;
	    		effectPosition.y = Gdx.graphics.getHeight() * 100;
	    		Assets.particleSetPosition(Assets.blastEffect,effectPosition.x, effectPosition.y);
    		}
    	}

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
        if (magicEnabled){
            if (Gdx.input.justTouched()) {
                body.setTransform(Gdx.input.getX() / Vars.world2box,( Gdx.graphics.getHeight() - Gdx.input.getY() )/ Vars.world2box, 0);
                magicEffects.add(new MagicParticleEffect(Gdx.input.getX(),Gdx.graphics.getHeight() - Gdx.input.getY()));
                System.out.println("Effects size " + magicEffects.size());
            }
        }



	}

	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
		sprite.setPosition((body.getPosition().x  -BallProperties.radius)*Vars.world2box, (body.getPosition().y -BallProperties.radius)*Vars.world2box);
//		sprite.setRotation(MathUtils.degreesToRadians * body.getAngle());
//		sprite.rotate( body.getAngle());
//		sprite.setOrigin(body.getPosition().x, body.getPosition().y);
		if (renderBallEffects){
			Assets.playEffect(Assets.blastEffect,batch);
		}

        if (magicEffects.size() > 0){
            iter = magicEffects.iterator();
            System.out.println("Magic effect size " + magicEffects.size());
                while(iter.hasNext()){
                MagicParticleEffect tempEffect = (MagicParticleEffect)iter.next();
                tempEffect.render(batch);
            }
            if (magicEffects.peek().isEffectCompleted()){
                magicEffects.peek().dispose();
                magicEffects.poll();
            }
        }

		sprite.setRotation(angle);
		sprite.draw(batch);
		
	}


    /////////////////////////////////////       GETTERS     //////////////////////////////////////////////////////////////////////////

	public Vector2 getPosition(){
		return body.getPosition();
	}
	
	public float getRadius(){
		return BallProperties.radius;
	}
	
	public float getVelocity(){
		return body.getLinearVelocity().x;
	}
	
	public static int getPlayers(){
		return players;
	}

    public static int getType(){
        return type;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////   SETTERS     //////////////////////////////////////////////////////////////////////////
	
	public static void decreasePlayer(){
		players -- ;
	}

    public  static void oneUp(){
        if (players <5){
            players ++;
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private class MagicParticleEffect{

        ParticleEffect effect;

        MagicParticleEffect(float x,float y){
            effect = new ParticleEffect();
            effect.load(Gdx.files.internal("effects/magiceffect.p"), Gdx.files.internal("effects"));
            effect.setPosition(x,y);
            effect.start();
        }

        public void render(SpriteBatch batch) {
            effect.update(Gdx.graphics.getDeltaTime());
            effect.draw(batch);
        }

        public boolean isEffectCompleted(){
            return  effect.isComplete();
        }

        public void dispose(){
            effect.dispose();
        }
    }



}
