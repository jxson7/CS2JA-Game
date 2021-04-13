package com.example.flyingdutchman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * The following class focuses on the creation of th main home screen that the user will interact with. This includes button controls for the user.
 */
public class MainActivity extends AppCompatActivity {

    private boolean isMute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // allowance of full screen for the user

        setContentView(R.layout.activity_main);

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GameActivity.class)); // starting activity of Level 1
            }
        });

        findViewById(R.id.L2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GameActivity2.class));// starting of Level 2
            }
        });

//        findViewById(R.id.L3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, GameActivity3.class));// starting of Level 3
//            }
//        });

        TextView highScoreTxt = findViewById(R.id.highScoreTxt);

        final SharedPreferences prefs = getSharedPreferences("game", MODE_PRIVATE);
        highScoreTxt.setText("HighScore: " + prefs.getInt("highscore", 0));

        isMute = prefs.getBoolean("isMute", false);

        final ImageView volumeCtrl = findViewById(R.id.volumeCtrl);

        if (isMute)
            volumeCtrl.setImageResource(R.drawable.ic_baseline_volume_off_24);
        else
            volumeCtrl.setImageResource(R.drawable.ic_baseline_volume_up_24);

        // volume controller
        volumeCtrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isMute = !isMute;
                if (isMute)
                    volumeCtrl.setImageResource(R.drawable.ic_baseline_volume_off_24);
                else
                    volumeCtrl.setImageResource(R.drawable.ic_baseline_volume_up_24);

                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("isMute", isMute);
                editor.apply();

            }
        });

    }
}
