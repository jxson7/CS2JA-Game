package com.example.flyingdutchman;

import android.content.Context;
import android.content.SharedPreferences;

public class GameView2 extends GameView implements Runnable {

    private final SharedPreferences prefs;
    GameActivity2 activity;
    public  int screenX;
    public int screenY;

     BackgroundLevel2 background1;
     BackgroundLevel2 background2;

    public GameView2(GameActivity2 activity, int screenX, int screenY) {
        super(activity);
        prefs = activity.getSharedPreferences("game", Context.MODE_PRIVATE);


        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;

        background1 = new BackgroundLevel2(screenX, screenY, getResources());
        background2 = new BackgroundLevel2(screenX, screenY, getResources());


        }



}


