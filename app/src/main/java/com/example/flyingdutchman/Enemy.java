package com.example.flyingdutchman;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.flyingdutchman.GameView.screenRatioX;
import static com.example.flyingdutchman.GameView.screenRatioY;
import static com.example.flyingdutchman.R.drawable.bird1;
import static com.example.flyingdutchman.R.drawable.bird2;
import static com.example.flyingdutchman.R.drawable.bird3;
import static com.example.flyingdutchman.R.drawable.bird4;

/**
 * This class focuses on the enemy and generation and illustrations of the sprite.
 */
public class Enemy {

    public int speed = 3; // a based speed value is set up
    public boolean wasShot = true; // boolean is set to trigger if the bird was shot.
    int x = 0, y, width, height, EnemyCounter = 1; //sets of variable
    Bitmap enemySprite1, enemySprite2, enemySprite3, enemySprite4; //bitmap to generate sprites

    /**
     *
     * @param res
     */
    Enemy (Resources res) {

        // retrieving all files for enemySprites
        enemySprite1 = BitmapFactory.decodeResource(res, bird1);
        enemySprite2 = BitmapFactory.decodeResource(res, bird2);
        enemySprite3 = BitmapFactory.decodeResource(res, bird3);
        enemySprite4 = BitmapFactory.decodeResource(res, bird4);

        width = enemySprite1.getWidth();
        height = enemySprite1.getHeight();

        width /= 20;
        height /= 20;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        //scaling image based on use screen
        enemySprite1 = Bitmap.createScaledBitmap(enemySprite1, width, height, false);
        enemySprite2 = Bitmap.createScaledBitmap(enemySprite2, width, height, false);
        enemySprite3 = Bitmap.createScaledBitmap(enemySprite3, width, height, false);
        enemySprite4 = Bitmap.createScaledBitmap(enemySprite4, width, height, false);

        y = -height;
    }

    /**
     *
     * @return enemy sprite based on position and transition
     */
    Bitmap getEnemy () {

        if (EnemyCounter == 1) {
            EnemyCounter++;
            return enemySprite1;
        }

        if (EnemyCounter == 2) {
            EnemyCounter++;
            return enemySprite2;
        }

        if (EnemyCounter == 3) {
            EnemyCounter++;
            return enemySprite3;
        }

        EnemyCounter = 1;

        return enemySprite4;
    }


    /**
     *
     * @return creation of enemy sprite
     */
    Rect getCollisionShape () {
        return new Rect(x, y, x + width, y + height);
    }

}
