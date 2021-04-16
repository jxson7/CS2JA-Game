package com.example.flyingdutchman;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

/**
 * Game activity represents the activities during gameplay of the levels.
 */
public class GameActivity2 extends AppCompatActivity {

    public GameView2 gameView; // link to gameview.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameView = new GameView2(this, point.x, point.y);

        setContentView(gameView); // setting content view to show the gameView
    }

    /**
     * represents when the user pauses the game
     */
    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    /**
     * represents when the user returns into game
     */
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

}