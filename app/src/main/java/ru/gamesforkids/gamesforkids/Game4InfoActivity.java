package ru.gamesforkids.gamesforkids;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.GestureDetector;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class Game4InfoActivity extends AppCompatActivity {

    RadioButton[] rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4_info);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        TextView info = findViewById(R.id.infoText);
        info.setTextSize(size.x / 50 > size.y / 30 ? size.y / 30 : size.x / 50);
        info.setPadding(0, size.y / 10, 0, size.y / 70);

        TextView infoLevel = findViewById(R.id.levelText);
        infoLevel.setTextSize(size.x / 50 > size.y / 30 ? size.y / 40 : size.x / 70); //2560 1600

        Intent intent = getIntent();
        int level = intent.getIntExtra("level", 1);

        rb = new RadioButton[3];
        rb[0] = findViewById(R.id.radioButton1);
        rb[1] = findViewById(R.id.radioButton2);
        rb[2] = findViewById(R.id.radioButton3);

        for (RadioButton rbt : rb) {
            rbt.setTextSize(size.x / 50 > size.y / 30 ? size.y / 45 : size.x / 80);
            rbt.setPadding(0, 0, size.x / 16, 0);
        }

        rb[level - 1].setChecked(true);

        Button bt = findViewById(R.id.button);
        bt.setTextSize(size.x / 50 > size.y / 30 ? size.y / 45 : size.x / 80);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int level = 0;
                for (int i = 0; i < 3; i++)
                    if (rb[i].isChecked()) {
                        level = i + 1;
                        i = 3;
                    }
                Game4Activity.setLevel(level);
                finish();
            }
        });
    }
}
