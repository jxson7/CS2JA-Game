package com.example.flyingdutchman;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Background {

    int x = 0, y = 0;
    Bitmap background;

    /**
     *
     * @param screenX
     * @param screenY
     * @param res
     */
    Background (int screenX, int screenY, Resources res) {

        background = BitmapFactory.decodeResource(res, R.drawable.backgroundl1);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);

    }

}
