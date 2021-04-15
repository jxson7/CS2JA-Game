package com.example.flyingdutchman;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * The following class is used to generate the background image that is to be used within that level of the game.
 */
public class BackgroundLevel2 {

    int x = 0;
    int y = 0;

    Bitmap background; // Bitmap variable to create the wallpaper
    /**
     *
     * @param screenX parameter used to reference the X Dimensions of the user's screen
     * @param screenY parameter used to reference the Y Dimensions of the user's screen
     * @param res referencing to the passing of resources via backgrounds
     */
    BackgroundLevel2(int screenX, int screenY, Resources res) {

        background = BitmapFactory.decodeResource(res, R.drawable.backgroundl2); //drawing of level 2 wallpaper
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false); // scaling image to meet user's display size

    }

}
