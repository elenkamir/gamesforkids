package ru.gamesforkids.gamesforkids;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;

public class Game4InfoActivity extends AppCompatActivity {

    RadioButton rb1, rb2, rb3;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4_info);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        int level = intent.getIntExtra("level",1);

        rb1 = (RadioButton) findViewById(R.id.radioButton1);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        rb3 = (RadioButton) findViewById(R.id.radioButton3);

        if (level == 1) {
            rb1.setChecked(true);
        }
        if (level == 2) {
            rb2.setChecked(true);
        }
        if (level == 3) {
            rb3.setChecked(true);
        }

        bt = (Button) findViewById(R.id.button);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int level = 0;
                if (rb1.isChecked()) {
                    level = 1;
                }
                if (rb2.isChecked()) {
                    level = 2;
                }
                if (rb3.isChecked()) {
                    level = 3;
                }
                /*Intent intent = new Intent(getApplicationContext(),Game4Activity.class);
                intent.putExtra("level",level);
                startActivity(intent);*/
                Game4Activity.setLevel(level);
                finish();
            }
        });


    }
}
