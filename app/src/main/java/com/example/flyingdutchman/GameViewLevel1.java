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
    private  FlyingDutchman fd;
    private  Handler handler;
    private  Runnable r;
    private int tubeSum, distance;
    private ArrayList<Tubes> arrTubes;
    public GameViewLevel1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initFd();
        initTubes();


        handler = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };

    }

    private void initTubes() {
        tubeSum = 6;
        distance = 300*Constants.SCREEN_HEIGHT/1920;
        arrTubes = new ArrayList<>();
        for (int x = 0; x < tubeSum/2; x++){
            if (x<tubeSum/2){
                this.arrTubes.add(new Tubes(Constants.SCREEN_WIDTH+x*((Constants.SCREEN_WIDTH+200(Constants.SCREEN_WIDTH/1000)/(tubeSum/2),0, 200*Constants.SCREEN_WIDTH/1080, Constants.SCREEN_HEIGHT/2 ));
                this.arrTubes.get(this.arrTubes.size()-1).setBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.bottomtube));
                this.arrTubes.get(this.arrTubes.size()-1).randomY();
            }else {
                this.arrTubes.add(new Tubes(new Tubes(this.arrTubes.get(x-tubeSum/2).getX(), this.arrTubes.get(x-tubeSum/2).getY()
                        + this.arrTubes.get(x-tubeSum/2).getH()+this.distance, 200*Constants.SCREEN_WIDTH/1080, 200*Constants.SCREEN_HEIGHT/2)));

                this
                this.arrTubes.get(this.arrTubes.size()-1).setBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.toptube));


            }

            }
        }


    private void initFd() {
        fd = new FlyingDutchman();
        fd.setH(100*Constants.SCREEN_WIDTH/1920);
        fd.setW(100*Constants.SCREEN_WIDTH/1080);
        fd.setX(100*Constants.SCREEN_WIDTH/1000);
        fd.setY(Constants.SCREEN_HEIGHT/2-fd.getH()/2);
        ArrayList<Bitmap> arr = new ArrayList<>();
        arr.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.fly1));
        arr.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.fly2));
        fd.setArr(arr);
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
    }
}

