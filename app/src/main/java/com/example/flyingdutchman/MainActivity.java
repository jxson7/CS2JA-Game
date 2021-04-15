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

        findViewById(R.id.L3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GameActivity3.class));// starting of Level 3
            }
        });

        TextView level1HighScore = findViewById(R.id.level1HighScore);
        SharedPreferences l1 = getSharedPreferences("l1", MODE_PRIVATE);
        level1HighScore.setText("HighScore: " + l1.getInt("l1score", 0));

        TextView level2HighScore = findViewById(R.id.level2HighScore);
        SharedPreferences l2 = getSharedPreferences("l2", MODE_PRIVATE);
        level2HighScore.setText("HighScore: " + l2.getInt("l2score", 0));

        TextView level3HighScore = findViewById(R.id.level13HighScore);
        SharedPreferences l3 = getSharedPreferences("l3", MODE_PRIVATE);
        level3HighScore.setText("HighScore: " + l3.getInt("l3score", 0));

        isMute = l1.getBoolean("isMute", false);



    }
}
