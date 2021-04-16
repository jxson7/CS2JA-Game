package com.example.flyingdutchman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import java.util.ArrayList;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.view.SurfaceView;
import java.util.List;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying, isGameOver = false;
    public  int screenX;
    public int screenY;
    private int score = 0;
    public static float screenRatioX, screenRatioY;
    private final Paint paint;
    private final Enemy[] enemys;
    private SharedPreferences prefs;
    private final Random random;
    private final List<Bullet> bullets;
    private int sound;
    private final com.example.flyingdutchman.Flight flight;
    private final GameActivity activity;
    private final Background background1;
    private final Background background2;


    /**
     *
     * The following class focuses on the game flow based on the said level
     * @param activity link to GameActivity to control flow of game
     * @param screenX represents screen dimension
     * @param screenY represents screen dimension
     */
    public GameView(GameActivity activity, int screenX, int screenY) {
        super(activity);

        this.activity = activity;
        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1920f / screenX; // calculation of screen dimensions
        screenRatioY = 1080f / screenY;// calculation of screen dimensions

        background1 = new Background(screenX, screenY, getResources()); // relation to retrieing the background for the level.
        background2 = new Background(screenX, screenY, getResources()); // relation to retrieing the background for the level.

        flight = new Flight(this, screenY, getResources()); // retrieving the user sprite

        bullets = new ArrayList<>(); // setting array up for bullets

        background2.x = screenX;

        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.WHITE);

        enemys = new Enemy[4];

        // creation of loop to retrive all enemy sprites
        for (int i = 0;i < 4;i++) {
            Enemy enemy = new Enemy(getResources());
            enemys[i] = enemy;
        }
        random = new Random();

    }


    /**
     * run is used to trace the flow of the game, ensuring a regular update is done.
     */
    @Override
    public void run() {
        while (isPlaying) {
            update ();
            draw ();
            sleep ();
        }

    }

    /**
     * update() is used to ensure the game successfully refreshes. using a moving background, updated new enemy birds and new bullets.
     */
    private void update () {
        background1.x -= 10 * screenRatioX;
        background2.x -= 10 * screenRatioX;

        if (background1.x + background1.background.getWidth() < 0) {
            background1.x = screenX;
        }

        if (background2.x + background2.background.getWidth() < 0) {
            background2.x = screenX;
        }

        if (flight.isGoingUp)
            flight.y -= 30 * screenRatioY;
        else
            flight.y += 30 * screenRatioY;

        if (flight.y < 0)
            flight.y = 0;

        if (flight.y >= screenY - flight.height)
            flight.y = screenY - flight.height;

        List<Bullet> remove = new ArrayList<>();

        for (Bullet bullet : bullets) {
            if (bullet.x > screenX)
                remove.add(bullet);
            bullet.x += 50 * screenRatioX;
            for (Enemy enemy : enemys) {
                if (Rect.intersects(enemy.getCollisionShape(),
                        bullet.getCollisionShape())) {
                    score++;
                    enemy.x = -500;
                    bullet.x = screenX + 500;
                    enemy.wasShot = true;
                }
            }
        }

        for (Bullet bullet : remove)
            bullets.remove(bullet);

        for (Enemy enemy : enemys) {
            enemy.x -= enemy.speed;
            if (enemy.x + enemy.width < 0) {
                int bound = (int) (10 * screenRatioX);
                enemy.speed = random.nextInt(bound);

                if (enemy.speed < 10 * screenRatioX)
                    enemy.speed = (int) (10 * screenRatioX);

                enemy.x = screenX;
                enemy.y = random.nextInt(screenY - enemy.height);

                enemy.wasShot = false;
            }

            // trace of interaction between enemy and user sprite, and if so, game ends
            if (Rect.intersects(enemy.getCollisionShape(), flight.getCollisionShape())) {
                isGameOver = true;
                return;
            }

        }

    }


    /**
     * draw is used to draw the background, enemy sprites and user sprites
     */
    private void draw () {

        if (getHolder().getSurface().isValid()) {

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);

            for (Enemy enemy : enemys)
                canvas.drawBitmap(enemy.getEnemy(), enemy.x, enemy.y, paint);

            canvas.drawText(score + "Score: " + "", screenX / 2f, 164, paint); // illustration of score on scoreboard for user

            // if statement is used to check that if the game ends via the set constraints, the scores are saved (if higher).
            if (isGameOver) {
                isPlaying = false;
                canvas.drawBitmap(flight.getDead(), flight.x, flight.y, paint);
                getHolder().unlockCanvasAndPost(canvas);
                saveIfHighScore();
                try {
                    Thread.sleep(3000);
                    activity.startActivity(new Intent(activity, MainActivity.class));
                    activity.finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
            }

            canvas.drawBitmap(flight.getFlight(), flight.x, flight.y, paint);

            for (Bullet bullet : bullets)
                canvas.drawBitmap(bullet.bullet, bullet.x, bullet.y, paint);
            getHolder().unlockCanvasAndPost(canvas);

        }

    }


    /**
     * saveIfHighScore is used to save the high score based upon previous ones and if higher, the score is updated
     */
    private void saveIfHighScore() {

        if (prefs.getInt("l1score", 0) < score) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("l1score", score);
            editor.apply();
        }

    }

    /**
     * sleep thread is used to keep a slight delay for user convenience.
     */
    private void sleep () {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * when the user returns into the game the game flow resumes.
     */
    public void resume () {

        isPlaying = true;
        thread = new Thread(this);
        thread.start();

    }

    /**
     * when the user backs out of the game, pause() is used to suspend the game
     */
    public void pause () {

        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) { // if the user taps down, the user sprite moves.
            case MotionEvent.ACTION_DOWN:
                if (event.getX() < screenX / 2) {
                    flight.isGoingUp = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                flight.isGoingUp = false;
                if (event.getX() > screenX / 2)
                    flight.toShoot++; // if up is tapped, a bullet is fired.
                break;
        }

        return true;
    }

    /**
     * new bullets are generated when the user fires and are added to an arrayList for constant regeneration.
     */
    public void newBullet() {
        Bullet bullet = new Bullet(getResources());
        bullet.x = flight.x + flight.width;
        bullet.y = flight.y + (flight.height / 2);
        bullets.add(bullet);

    }

}


