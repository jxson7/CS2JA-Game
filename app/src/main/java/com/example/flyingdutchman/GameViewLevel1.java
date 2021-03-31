package com.example.flyingdutchman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.logging.LogRecord;

public class GameViewLevel1 extends View {
    private FlyingDutchman fd;
    Handler han;
    Runnable r;
    private int tubeSum, distance;
    private ArrayList<Tubes> arrTubes;

    public GameViewLevel1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initFd();
        initTubes();

        r = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };


    }

    private void initTubes() {
        tubeSum = 6;
        distance = 300 * Constants.SCREEN_HEIGHT / 1920;
        arrTubes = new ArrayList<>();
        for (int x = 0; x < tubeSum / 2; x++) {
            if (x < tubeSum / 2) {
                this.arrTubes.add(new Tubes(Constants.SCREEN_WIDTH + x * ((Constants.SCREEN_WIDTH + 200 * (Constants.SCREEN_WIDTH / 1080) / (tubeSum / 2))), 0, 200 * Constants.SCREEN_WIDTH / 1080, Constants.SCREEN_HEIGHT / 2));
                this.arrTubes.get(this.arrTubes.size() - 1).setBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.bottomtube));
                this.arrTubes.get(this.arrTubes.size() - 1).randomY();
            } else {
                this.arrTubes.add(new Tubes(this.arrTubes.get(x - tubeSum / 2).getX(), this.arrTubes.get(x - tubeSum / 2).getY()
                        + this.arrTubes.get(x - tubeSum / 2).getH() + this.distance, 200 * Constants.SCREEN_WIDTH / 1080, Constants.SCREEN_HEIGHT / 2));
                this.arrTubes.get(this.arrTubes.size() - 1).setBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.toptube));


            }

        }
    }


    private void initFd() {
        fd = new FlyingDutchman();
        fd.setH(100 * Constants.SCREEN_WIDTH / 1920);
        fd.setW(100 * Constants.SCREEN_WIDTH / 1080);
        fd.setX(100 * Constants.SCREEN_WIDTH / 1000);
        fd.setY(Constants.SCREEN_HEIGHT / 2 - fd.getH() / 2);
        ArrayList<Bitmap> arr = new ArrayList<>();
        arr.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.fly1));
        arr.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.fly2));
        fd.setArr(arr);
    }

    public void draw(Canvas canvas) {

        super.draw(canvas);
        fd.draw(canvas);

        for (int i = 0; i < tubeSum; i++) {
            if (this.arrTubes.get(i).getX() < arrTubes.get(i).getW()) {
                this.arrTubes.get(i).setX(Constants.SCREEN_WIDTH);
                if (i < tubeSum / 2) {
                    arrTubes.get(i).randomY();
                } else {
                    arrTubes.get(i).setY(this.arrTubes.get(i - tubeSum / 2).getY()
                            + this.arrTubes.get(i - tubeSum / 2).getH() + this.distance);
                }
            }
            this.arrTubes.get(i).draw(canvas);
        }

        getHandler().postDelayed(r,10);

    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        if (e.getAction() == MotionEvent.ACTION_DOWN){
            fd.setDrop(-15);

        }
        return true;
    }
}

