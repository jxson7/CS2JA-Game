package com.example.flyingdutchman;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

/**
 *
 */
public class GameActivity extends AppCompatActivity {

    public GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameView = new GameView(this, point.x, point.y);

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


    public void onClick(View view) {
        gameView.newBullet();

    }
}