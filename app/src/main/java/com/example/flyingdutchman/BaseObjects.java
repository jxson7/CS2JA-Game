package com.example.flyingdutchman;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class BaseObjects {
    protected float x,y;
    protected int w,h;
    protected Rect rect;

    protected Bitmap bitmap;

    public BaseObjects() {
    }

    public BaseObjects(float x, float y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public Rect getRect() {
        return new Rect((int)this.x, (int)this.y, (int)this.x+this.w, (int)this.y+this.h);
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }
}
