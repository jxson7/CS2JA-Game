package com.example.flyingdutchman;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.flyingdutchman.GameView.screenRatioX;
import static com.example.flyingdutchman.GameView.screenRatioY;

/**
 *
 */
public class Enemy {

    public int speed = 10;
    public boolean wasShot = true;
    int x = 0, y, width, height, EnemyCounter = 1;
    Bitmap bird1, bird2, bird3, bird4;

    /**
     *
     * @param res
     */
    Enemy (Resources res) {

        bird1 = BitmapFactory.decodeResource(res, R.drawable.bird1);
        bird2 = BitmapFactory.decodeResource(res, R.drawable.bird2);
        bird3 = BitmapFactory.decodeResource(res, R.drawable.bird3);
        bird4 = BitmapFactory.decodeResource(res, R.drawable.bird4);

        width = bird1.getWidth();
        height = bird1.getHeight();

        width /= 20;
        height /= 20;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        bird1 = Bitmap.createScaledBitmap(bird1, width, height, false);
        bird2 = Bitmap.createScaledBitmap(bird2, width, height, false);
        bird3 = Bitmap.createScaledBitmap(bird3, width, height, false);
        bird4 = Bitmap.createScaledBitmap(bird4, width, height, false);

        y = -height;
    }

    /**
     *
     * @return
     */
    Bitmap getEnemy () {

        if (EnemyCounter == 1) {
            EnemyCounter++;
            return bird1;
        }

        if (EnemyCounter == 2) {
            EnemyCounter++;
            return bird2;
        }

        if (EnemyCounter == 3) {
            EnemyCounter++;
            return bird3;
        }

        EnemyCounter = 1;

        return bird4;
    }


    /**
     *
     * @return
     */
    Rect getCollisionShape () {
        return new Rect(x, y, x + width, y + height);
    }
//test
}
