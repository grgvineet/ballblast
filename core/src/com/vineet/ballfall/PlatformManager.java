package com.vineet.ballfall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class PlatformManager {
	

	private static World world;
	static Queue<Platform> q;
	private static int platformCount = 5;
	Random ran;
	private Score score;
	
	String currentScore;
	private static Iterator iter;
	private static Platform lastPlatform;
	private static Platform temp;
	private static Vector2 v ;
    private Powers powers;

    private int count ;

	
	
	public PlatformManager(World worl){
		
		this.world = worl;
		ran = new Random(System.currentTimeMillis());
		v = new Vector2();
		
		score = new Score();
        powers = new Powers(world);

        count = platformCount;

		q = new LinkedList<Platform>();
		
		
		for (int i = 0; i < platformCount ; i++){
			q.add(new Platform(world , PlatformProperties.initialSpeed,-1*i*(Gdx.graphics.getHeight()/ Vars.world2box)/platformCount , PlatformProperties.platform));
		}
		
		
	}

    /////////////////////////////////////   UPDATE AND DRAW //////////////////////////////////////////////////////////////////////////

	private void update() {

        powers.update();
		// TODO Auto-generated method stub
		
		currentScore = "" + score.getScore();
		for (Platform p : q){
			p.update();
		}
		

		if (q.peek().getPosition().y > ((Gdx.graphics.getHeight()*0.925f + (TopSpikes.getHeight()/2))/ Vars.world2box)){
			
			score.update();
			
			if (ran.nextInt()%100 > 50){

                /////////////////////////////////////ADDING SPIKED PLATFORM //////////////////////////////////////////////////////////////////////////

//				q.add(new Platform(world,q.peek().getSpeed(),0,PlatformProperties.platformSpike));
//				q.peek().setPositon();
				iter = q.iterator();
				lastPlatform = (Platform) iter.next();
				while(iter.hasNext()) {
			        lastPlatform = (Platform) iter.next();
			    }
				q.add(new Platform(world,q.peek().getSpeed(),lastPlatform.getPosition().y - ((Gdx.graphics.getHeight()/ Vars.world2box)/platformCount), PlatformProperties.platformSpike));

			}else{

                /////////////////////////////////////adding simple platform     //////////////////////////////////////////////////////////////////////////

                count ++;
//                System.out.print(count + "\n");

				iter = q.iterator();
				lastPlatform = (Platform) iter.next();
				while(iter.hasNext()) {
			        lastPlatform = (Platform) iter.next();
			    }

                q.add(new Platform(world, q.peek().getSpeed(), lastPlatform.getPosition().y - ((Gdx.graphics.getHeight() / Vars.world2box) / platformCount), PlatformProperties.platform));

                if (count == PlatformProperties.noOfPlatformForPowerComing) {
                    count = 0;
                    iter = q.iterator();
                    lastPlatform = (Platform) iter.next();
                    while(iter.hasNext()) {
                        lastPlatform = (Platform) iter.next();
                    }
//				    q.add(new Platform(world,q.peek().getSpeed(),0,PlatformProperties.platform));
                    powers.generate(lastPlatform.getPosition().x * Vars.world2box,lastPlatform.getPosition().y * Vars.world2box,q.peek().getSpeed());
                    System.out.println("power generated");
                }
			}
//			q.peek().dispose();
        q.peek().destroy();
        q.poll();
    }

}

    public void draw(SpriteBatch batch) {
        // TODO Auto-generated method stub
        powers.draw(batch);
        for (Platform p : q){
            p.render(batch);
        }


    }

    public void render(SpriteBatch batch){
        update();
        draw(batch);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////   GETTERS     ////////////////////////////////////////////////////////////////////////////////////////////////

    public static Vector2 getPositionForBall() {
		// TODO Auto-generated method stub
		
		
		iter = q.iterator();
		lastPlatform = (Platform) iter.next();
		while(iter.hasNext()) {
			lastPlatform = (Platform) iter.next();
	    }
		
		if (lastPlatform.getType() == PlatformProperties.platform){
			v.x = lastPlatform.getPosition().x;
			v.y = lastPlatform.getPosition().y + (2 * lastPlatform.getHeight());
		}else{
			if (q.size() > platformCount){
				q.remove(lastPlatform);
			}
			q.add(new Platform(world,q.peek().getSpeed(),lastPlatform.getPosition().y - ((Gdx.graphics.getHeight()/ Vars.world2box)/platformCount), PlatformProperties.platform));
			iter = q.iterator();
			lastPlatform = (Platform) iter.next();
			while(iter.hasNext()) {
				lastPlatform = (Platform) iter.next();
		    }
			v.x = lastPlatform.getPosition().x;
			v.y = lastPlatform.getPosition().y + (2 * lastPlatform.getHeight());
		}

		return v;
	}

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////       SETERS      //////////////////////////////////////////////////////////////////////////

    public static void speedUp(){
        iter = q.iterator();
//        lastPlatform = (Platform) iter.next();
        while(iter.hasNext()) {
            lastPlatform = (Platform) iter.next();
            lastPlatform.speedUp();
        }
    }

    public static void speedDown(){
        iter = q.iterator();
//        lastPlatform = (Platform) iter.next();
        while(iter.hasNext()) {
            lastPlatform = (Platform) iter.next();
            lastPlatform.speedDown();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	

}
