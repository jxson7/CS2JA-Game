package com.example.flyingdutchman;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BackgroundLevel3 {

    int x = 0, y = 0;
    Bitmap background;

    /**
     *
     * @param screenX
     * @param screenY
     * @param res
     */
    BackgroundLevel3(int screenX, int screenY, Resources res) {

        background = BitmapFactory.decodeResource(res, R.drawable.backgroundl3);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);

    }

}
