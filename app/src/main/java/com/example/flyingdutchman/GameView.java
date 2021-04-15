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
    private final SharedPreferences prefs;
    private final Random random;
    private final SoundPool soundPool;
    private final List<Bullet> bullets;
    private int sound;
    private final com.example.flyingdutchman.Flight flight;
    private final GameActivity activity;
    private final Background background1;
    private final Background background2;

    public GameView(GameActivity activity, int screenX, int screenY) {
        super(activity);

        this.activity = activity;


        prefs = activity.getSharedPreferences("l1", Context.MODE_PRIVATE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .build();

        } else
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        //sound = soundPool.load(activity, R.raw.shoot, 1);

        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;

        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());

        flight = new Flight(this, screenY, getResources());

        bullets = new ArrayList<>();

        background2.x = screenX;

        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.WHITE);

        enemys = new Enemy[4];

        for (int i = 0;i < 4;i++) {

            Enemy enemy = new Enemy(getResources());
            enemys[i] = enemy;

        }

        random = new Random();

    }




    @Override
    public void run() {

        while (isPlaying) {

            update ();
            draw ();
            sleep ();

        }

    }

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

//                if (!enemy.wasShot) {
//                 //   isGameOver = true;
//                    return;
//                }

                int bound = (int) (10 * screenRatioX);
                enemy.speed = random.nextInt(bound);

                if (enemy.speed < 10 * screenRatioX)
                    enemy.speed = (int) (10 * screenRatioX);

                enemy.x = screenX;
                enemy.y = random.nextInt(screenY - enemy.height);

                enemy.wasShot = false;
            }

            if (Rect.intersects(enemy.getCollisionShape(), flight.getCollisionShape())) {

                isGameOver = true;
                return;
            }

        }

    }



    private void draw () {

        if (getHolder().getSurface().isValid()) {

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);

            for (Enemy enemy : enemys)
                canvas.drawBitmap(enemy.getEnemy(), enemy.x, enemy.y, paint);

            canvas.drawText(score + "", screenX / 2f, 164, paint);

            if (isGameOver) {
                isPlaying = false;
                canvas.drawBitmap(flight.getDead(), flight.x, flight.y, paint);
                getHolder().unlockCanvasAndPost(canvas);
                saveIfHighScore();
                waitBeforeExiting ();
                return;
            }

            canvas.drawBitmap(flight.getFlight(), flight.x, flight.y, paint);

            for (Bullet bullet : bullets)
                canvas.drawBitmap(bullet.bullet, bullet.x, bullet.y, paint);

            getHolder().unlockCanvasAndPost(canvas);

        }

    }

    private void waitBeforeExiting() {

        try {
            Thread.sleep(3000);
            activity.startActivity(new Intent(activity, MainActivity.class));
            activity.finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void saveIfHighScore() {

        if (prefs.getInt("l1score", 0) < score) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("l1score", score);
            editor.apply();
        }

    }

    private void sleep () {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume () {

        isPlaying = true;
        thread = new Thread(this);
        thread.start();

    }

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

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX() < screenX / 2) {
                    flight.isGoingUp = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                flight.isGoingUp = false;
                if (event.getX() > screenX / 2)
                    flight.toShoot++;
                break;
        }

        return true;
    }

    public void newBullet() {


        Bullet bullet = new Bullet(getResources());
        bullet.x = flight.x + flight.width;
        bullet.y = flight.y + (flight.height / 2);
        bullets.add(bullet);

    }

}


