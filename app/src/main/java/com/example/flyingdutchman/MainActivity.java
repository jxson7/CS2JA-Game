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
 * The following class focuses on the creation of the main home screen that the user will interact with. This includes button controls for the user.
 */
public class MainActivity extends AppCompatActivity {

    private boolean isMute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // allowance of full screen for the user
        // loads the mainActivity from the xml files.
        setContentView(R.layout.activity_main);


        // clickListener used, so when the user clicks, the level is loaded.
        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GameActivity.class)); // starting activity of Level 1
            }
        });


        // clickListener used, so when the user clicks, the level is loaded.
        findViewById(R.id.L2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GameActivity2.class));// starting of Level 2
            }
        });

        // clickListener used, so when the user clicks, the level is loaded.
        findViewById(R.id.L3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GameActivity3.class));// starting of Level 3
            }
        });

        // used to represent the scores on the user home screen under the corresponding levels.
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
