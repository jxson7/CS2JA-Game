package com.example.flyingdutchman;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.ArrayList;

public class FlyingDutchman extends BaseObjects {
    private ArrayList<Bitmap> arr = new ArrayList<>();
    private int counter, changer, idCurrentBitmap;
    private float drop;

    public FlyingDutchman() {
        this.counter = 0;
        this.changer = 0;
        this.idCurrentBitmap =0;
        this.drop = 0;
    }

    public void draw(Canvas canvas){
        drop();
        canvas.drawBitmap(this.getBitmap(), this.x, this.y,null);
    }

    private void drop() {
        this.drop+=0.6;
        this.y+=this.drop;
    }


    public ArrayList<Bitmap> getArr() {
        return arr;
    }

    public void setArr(ArrayList<Bitmap> arr) {
        this.arr = arr;

        for (int i= 0; i < arr.size(); i++){
            this.arr.set(i, Bitmap.createScaledBitmap(this.arr.get(i), this.w, this.h, true));
        }
    }

    @Override
    public Bitmap getBitmap() {
        counter++;

        if(this.counter == this.changer){
            for(int x=0; x<arr.size(); x++){
                if (x == arr.size()-1){
                    this.idCurrentBitmap =0;
                    break;
                }else if(this.idCurrentBitmap == x){
                    idCurrentBitmap = x+1;
                    break;
                }
            }
            counter = 0;
        }

        if (this.drop<0){
            Matrix matrix = new Matrix();
            matrix.postRotate(-25);
            return Bitmap.createBitmap(arr.get(idCurrentBitmap), 0,0,arr.get(idCurrentBitmap).getWidth(), arr.get(idCurrentBitmap).getHeight(), matrix, true);



        }else if (drop>=0){
            Matrix matrix = new Matrix();
            if (drop<70){
                matrix.postRotate(-25+(drop*2));
            }else{
                matrix.postRotate(45);
            }
            return Bitmap.createBitmap(arr.get(idCurrentBitmap), 0,0,arr.get(idCurrentBitmap).getWidth(), arr.get(idCurrentBitmap).getHeight(), matrix, true);

        }
        return this.getArr().get(idCurrentBitmap);

    }

    public float getDrop() {
        return drop;
    }

    public void setDrop(float drop) {
        this.drop = drop;
    }
}
