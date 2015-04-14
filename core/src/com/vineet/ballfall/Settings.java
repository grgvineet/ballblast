package com.vineet.ballfall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Settings {
	
	public static long highScore = 0;
    public static boolean sfxEnabled = true;
    public static boolean musicEnabled = true;
    public static boolean vibratorEnabled = true;
    public static float sensitivity = BallProperties.midSensitivity;
	public final static String file = ".settings";
    private static Preferences preferences;


	
	public static void load() {
        if (preferences == null) {
            preferences = Gdx.app.getPreferences(file);
        }
        highScore = preferences.getLong("highScore",0);
        sfxEnabled = preferences.getBoolean("sfx",true);
        musicEnabled = preferences.getBoolean("music",true);
        vibratorEnabled = preferences.getBoolean("vibrator",true);
        sensitivity = preferences.getFloat("sensitivity", BallProperties.midSensitivity);
	}
	
	public static void save () {
        preferences.putLong("highScore",highScore);
        preferences.putBoolean("sfx",sfxEnabled);
        preferences.putBoolean("music",musicEnabled);
        preferences.putBoolean("vibrator",vibratorEnabled);
        preferences.putFloat("sensitivity", sensitivity);
        preferences.flush();

	}
	
	public static void highScoreUpdate (long score){
		if (score > highScore){
			highScore = score;
		}
	}
	
	public static long getHighScore(){
		return highScore;
		
	}

}
