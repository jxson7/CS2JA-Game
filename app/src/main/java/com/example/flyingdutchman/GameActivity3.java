package com.example.flyingdutchman;

import android.graphics.BitmapFactory;
import android.graphics.Point;
import com.example.flyingdutchman.Background;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

/**
 *
 */
public class GameActivity3 extends GameActivity {

    public GameView3 gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameView = new GameView3(this, point.x, point.y);

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