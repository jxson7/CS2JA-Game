package com.example.flyingdutchman;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 *
 */
public class GameActivity2 extends AppCompatActivity {

    public GameView2 gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int x =1;
        int y = 1;

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        DisplayMetrics displaymetrics = new DisplayMetrics(); getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
//        int height = displaymetrics.heightPixels;
//        int width = displaymetrics.widthPixels;
//
//        gameView = new GameView2(this, height, width);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameView = new GameView2(this, point.x, point.y);

        setContentView(gameView);
    }

    /**
     *
     */
    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    /**
     *
     */
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}