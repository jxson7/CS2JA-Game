package com.example.flyingdutchman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.BitSet;

public class GameViewLevel1 extends View {
    private FlyingDutchman fd;
    private Handler handler;
    private Runnable r;

    public GameViewLevel1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        fd = new FlyingDutchman();
        fd.setH(100*Constants.SCREEN_WIDTH/1920);
        fd.setW(100*Constants.SCREEN_WIDTH/1080);
        fd.setX(100*Constants.SCREEN_WIDTH/1000);
        fd.setY(Constants.SCREEN_HEIGHT/2-fd.getH()/2);
        ArrayList<Bitmap> arr = new ArrayList<>();
        arr.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.fly1));
        arr.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.fly2));
        fd.setArr(arr);
        handler = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };

    }

    public void draw(Canvas canvas){

        super.draw(canvas);
        fd.draw(canvas);
        handler.postDelayed(r,10);


//        canvas.drawColor(Color.BLACK);

    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        if (e.getAction() == MotionEvent.ACTION_DOWN){
            fd.setDrop(-15);

        }
        return true;
    };
}
