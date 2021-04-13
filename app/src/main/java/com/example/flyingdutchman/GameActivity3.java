//package com.example.flyingdutchman;
//
//import android.graphics.Point;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.WindowManager;
//
///**
// *
// */
//public class GameActivity3 extends AppCompatActivity {
//
//    private GameView3 gameView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        Point point = new Point();
//        getWindowManager().getDefaultDisplay().getSize(point);
//
//        gameView = new GameView3(this, point.x, point.y);
//
//        setContentView(gameView);
//    }
//
//    /**
//     *
//     */
//    @Override
//    protected void onPause() {
//        super.onPause();
//        gameView.pause();
//    }
//
//    /**
//     *
//     */
//    @Override
//    protected void onResume() {
//        super.onResume();
//        gameView.resume();
//    }
//}