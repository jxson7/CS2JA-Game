package com.example.flyingdutchman;

import android.graphics.BitmapFactory;
import android.graphics.Point;
import com.example.flyingdutchman.Background;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

/**
 *
 */
public class GameActivity2 extends GameActivity {

    public GameView2 gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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

    public void onClick(View view) {
        // gameView.fireBullets();
        gameView.newBullet();

    }
}