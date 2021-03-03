package com.example.flyingdutchman;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public class Tubes extends BaseObjects {
    public static int speed;

    public static int getSpeed() {
        return speed;
    }

    public static void setSpeed(int speed) {
        Tubes.speed = speed;
    }

    public Tubes(float x, float y, int w, int h){
        super (x,y,w,h);
        speed = 10*Constants.SCREEN_WIDTH/1080;

    }

    public void draw(Canvas canvas){
        this.x-= speed;
        canvas.drawBitmap(this.bitmap, this.x,this.y, null);
    }

    public void randomY() {
        Random r = new Random();
        this.y = r.nextInt((0 + this.h / 4) + 1) - this.h / 4;
    }


    @Override
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = Bitmap.createScaledBitmap(bitmap,w, h, true);
    }
    }
}
