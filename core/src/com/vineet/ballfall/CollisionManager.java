package com.vineet.ballfall;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class CollisionManager implements ContactListener {
	
	private Platform platform;
	private Ball ball;
    private Powers power;
    private World world;
    Array<Contact> contacts;

    public CollisionManager(World w){
        this.world = w;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fa,fb;



        contacts = world.getContactList();
        for(Contact c : contacts){

            fa = c.getFixtureA();
            fb = c.getFixtureB();
            try{
                ball = (Ball)fa.getUserData();
                try{
                    platform = (Platform)fb.getUserData();
                    if (platform.getType() == PlatformProperties.platform){
                        if (platform.playmusic == true){
                            Assets.playSound(Assets.thud);
                            platform.playmusic = false;
                        }
                    }else{
                        if (Ball.getType() == BallProperties.typeSimple) {
                            if (platform.playmusic == true) {
                                Ball.isCollidedWithPlatformSpike = true;
                                platform.playmusic = false;
                            }
                        } else{
                            if (platform.playmusic == true) {
                                Assets.playSound(Assets.thud);
                                platform.playmusic = false;
                            }
                        }
                    }
                }catch (Exception e){
                    //TODO
                }

                try {
                    power = (Powers) fb.getUserData();

                    try {
                        power.powerTaken();
                    }catch (Exception p){
                        p.printStackTrace();
                    }
                    System.out.println("power taken");
                } catch (Exception e2) {
//                    e2.printStackTrace();
                }

            }catch (Exception e){

            }

            try {
                ball = (Ball) fb.getUserData();
                try {
                    platform = (Platform) fa.getUserData();
                    if (platform.getType() == PlatformProperties.platform) {
                        if (platform.playmusic == true) {
                            Assets.playSound(Assets.thud);
                            platform.playmusic = false;
                        }
                    } else {
                        if (Ball.getType() == BallProperties.typeSimple) {
                            if (platform.playmusic == true) {
                                Ball.isCollidedWithPlatformSpike = true;
                                platform.playmusic = false;
                            }
                        } else{
                            if (platform.playmusic == true) {
                                Assets.playSound(Assets.thud);
                                platform.playmusic = false;
                            }
                        }

                    }
                } catch (Exception e1) {


                }
                try {
                    power = (Powers) fa.getUserData();
                    try {
                        power.powerTaken();
                    }catch (Exception p){
                        p.printStackTrace();
                    }
                    System.out.println("power taken");
                } catch (Exception e2) {
//                    e2.printStackTrace();
                }

            }catch (Exception e1){

            }

        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

/*
    @Override
    public void beginContact(Contact contact) {
        Fixture fa,fb;

        fa = contact.getFixtureA();
        fb = contact.getFixtureB();

        try{
            ball = (Ball)fa.getUserData();
            try{
                platform = (Platform)fb.getUserData();
                if (platform.getType() == PlatformProperties.platform){
                    if (platform.playmusic == true){
                        Assets.playSound(Assets.thud);
                        platform.playmusic = false;
                    }
                }else{
                    if (platform.playmusic == true){
                        Ball.isCollidedWithPlatformSpike = true;
                        platform.playmusic = false;
                    }
                }

            }catch (Exception e){

            }finally {
                try {
                    power = (Powers) fb.getUserData();
                    power.powerTaken();
                    System.out.println("power taken");
                } catch (Exception e2) {

                }
            }

        }catch (Exception e){
            try {
                ball = (Ball) fb.getUserData();
                try {
                    platform = (Platform) fa.getUserData();
                    if (platform.getType() == PlatformProperties.platform) {
                        if (platform.playmusic == true) {
                            Assets.playSound(Assets.thud);
                            platform.playmusic = false;
                        }
                    } else {
                        if (platform.playmusic == true) {
                            Ball.isCollidedWithPlatformSpike = true;
                            platform.playmusic = false;
                        }
                    }
                } catch (Exception e1) {


                }finally {
                    try {
                        power = (Powers) fa.getUserData();
                        power.powerTaken();
                        System.out.println("power taken");
                    } catch (Exception e2) {

                    }
                }
            }catch (Exception e1){

            }
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

*/

/*
	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		
		Fixture fa,fb;
		
		fa = contact.getFixtureA();
		fb = contact.getFixtureB();
		
		try{
			platform = (Platform) fa.getUserData();
			ball = (Ball) fb.getUserData();
		}catch (Exception e){
			platform = (Platform) fb.getUserData();
			ball = (Ball) fa.getUserData();
		}
		
		

		if (fa.getUserData().toString().equals("ball") && fb.getUserData(). ){
			System.out.println("ball " + PlatformProperties.platform);
			Assets.playSound(Assets.thud);
		}else if (fb.getUserData().toString().equals("ball") && fa.getUserData().equals(PlatformProperties.platform) ){
			System.out.println("ball " + PlatformProperties.platform);
			Assets.playSound(Assets.thud);
		}


		
		
		
		if (fa.getUserData().toString().equals("ball") && fb.getUserData().equals(PlatformProperties.platformSpike) ){
			System.out.println("ball " + PlatformProperties.platformSpike);
		}else if (fb.getUserData().toString().equals("ball") && fa.getUserData().equals(PlatformProperties.platformSpike) ){
			System.out.println("ball " + PlatformProperties.platformSpike);
		}
		

		if(platform.getType() == PlatformProperties.platform){
//			if(ball.getPosition().y - ball.getRadius() - platform.getHeight() - platform.getPosition().y <= 0.0f){
//			if (ball.getVelocity() == 0){
			if (platform.playmusic == true){
				Assets.playSound(Assets.thud);
				platform.playmusic = false;
			}
		}else{
			if (platform.playmusic == true){
				Ball.isCollidedWithPlatformSpike = true;
				platform.playmusic = false;
			}
		}
		
		
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

*/





}
