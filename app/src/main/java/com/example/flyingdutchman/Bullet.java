package com.example.flyingdutchman;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.flyingdutchman.GameView.screenRatioX;
import static com.example.flyingdutchman.GameView.screenRatioY;

/**
 * The following class focuses on the development of the bullet, including generation.
 */
public class Bullet {

    int  w, h, x, y; // x,y values, width and height.
    Bitmap bullet; // bitmap variable of bullet.

    /**
     *
     * @param res used to get resource from drawable files to draw bullet on the canvas.
     */
    Bullet (Resources res) {

        bullet = BitmapFactory.decodeResource(res, R.drawable.bullet);

        w = bullet.getWidth(); //use of get Width to calculate the size required
        h = bullet.getHeight(); //use of get Height to calculate the size required

        w /= 4; // values divided by 4 for compression
        h /= 4; // values divided by 4 for compression

        w = (int) (w * screenRatioX);
        h = (int) (h * screenRatioY);

        bullet = Bitmap.createScaledBitmap(bullet, w, h, false);

    }

    /**
     *
     * @return the bullet size with the set dimensions is returned to the user.
     */
    Rect getCollisionShape () {
        return new Rect(x, y, x + w, y + h); //development of bullet dimensions
    }

}