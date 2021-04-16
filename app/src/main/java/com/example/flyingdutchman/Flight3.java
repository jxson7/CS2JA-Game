package com.example.flyingdutchman;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.flyingdutchman.GameView.screenRatioX;
import static com.example.flyingdutchman.GameView.screenRatioY;

/**
 * The following class is developed for the pilot (user) sprite based on the level.
 */
public class Flight3 {

    int toShoot = 0; //firing variable
    boolean isGoingUp = false; //movement boolean
    int x, y, width, height, wingCounter = 0, shootCounter = 1; // dimentions of screen
    Bitmap flight1, flight2, shoot1, shoot2, shoot3, shoot4, shoot5, dead; //variables used for bitmap images
    private final GameView3 gameView; //retrieval of gameView file

    /**
     *  @param gameView gameView represents the user level
     * @param screenY represents screen ratio
     * @param res resource retrieval from folder
     */
    Flight3 (GameView3 gameView, int screenY, Resources res) {

        this.gameView = gameView;


        // retrieval of user's sprites
        flight1 = BitmapFactory.decodeResource(res, R.drawable.fly1);
        flight2 = BitmapFactory.decodeResource(res, R.drawable.fly2);

        width = flight1.getWidth();
        height = flight1.getHeight();

        width /= 4;
        height /= 4;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        //scaling sprites based upon users screen
        flight1 = Bitmap.createScaledBitmap(flight1, width, height, false);
        flight2 = Bitmap.createScaledBitmap(flight2, width, height, false);

        // decoding resource retrieves the shoot sprites
        shoot1 = BitmapFactory.decodeResource(res, R.drawable.shoot1);
        shoot2 = BitmapFactory.decodeResource(res, R.drawable.shoot2);
        shoot3 = BitmapFactory.decodeResource(res, R.drawable.shoot3);
        shoot4 = BitmapFactory.decodeResource(res, R.drawable.shoot4);
        shoot5 = BitmapFactory.decodeResource(res, R.drawable.shoot5);

        // scaling once again
        shoot1 = Bitmap.createScaledBitmap(shoot1, width, height, false);
        shoot2 = Bitmap.createScaledBitmap(shoot2, width, height, false);
        shoot3 = Bitmap.createScaledBitmap(shoot3, width, height, false);
        shoot4 = Bitmap.createScaledBitmap(shoot4, width, height, false);
        shoot5 = Bitmap.createScaledBitmap(shoot5, width, height, false);

        dead = BitmapFactory.decodeResource(res, R.drawable.dead);
        dead = Bitmap.createScaledBitmap(dead, width, height, false);

        y = screenY / 2;
        x = (int) (64 * screenRatioX);

    }



    /**
     *
     * @return flight animation
     */
    Bitmap getFlight () {

        if (toShoot != 0) {

            if (shootCounter == 1) {
                shootCounter++;
                return shoot1;
            }

            if (shootCounter == 2) {
                shootCounter++;
                return shoot2;
            }

            if (shootCounter == 3) {
                shootCounter++;
                return shoot3;
            }

            if (shootCounter == 4) {
                shootCounter++;
                return shoot4;
            }

            shootCounter = 1;
            toShoot--;
            gameView.newBullet();

            return shoot5;
        }

        if (wingCounter == 0) {
            wingCounter++;
            return flight1;
        }
        wingCounter--;

        return flight2;
    }

    /**
     *
     * @return generation of rect representing sprite
     */
    Rect getCollisionShape () {
        return new Rect(x, y, x + width, y + height);
    }

    /**
     *
     * @return dead sprite
     */
    Bitmap getDead () {
        return dead;
    }

}
