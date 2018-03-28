package ru.gamesforkids.gamesforkids;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

public class Game6FinishActivity extends AppCompatActivity {
    TextView result;
    TextView record;
    TextView upPoint;
    TextView downPoint;
    TextView newRecord;
    GifImageView firework;
    Button menu;
    Button retry;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game6_finish);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        upPoint = findViewById(R.id.up_point);
        downPoint = findViewById(R.id.down_point);
        result = findViewById(R.id.result);
        record = findViewById(R.id.record);
        newRecord = findViewById(R.id.new_record);
        firework = findViewById(R.id.firework);
        menu = findViewById(R.id.menu);
        retry = findViewById(R.id.retry);

        Intent intent = getIntent();
        int up = intent.getIntExtra("up", 0);
        upPoint.setText(String.valueOf(up));
        int down = intent.getIntExtra("down", 0);
        downPoint.setText(String.valueOf(down));
        result.setText(String.valueOf(up - down));
        int rec = intent.getIntExtra("record", 0);
        if (up - down > rec) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.g2right);
                    mp.start();
                }
            }, 200);
            record.setText(String.valueOf(up - down));
            firework.setVisibility(View.VISIBLE);
        } else {
            newRecord.setVisibility(View.INVISIBLE);
            record.setText(String.valueOf(rec));
        }

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Game6Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mp != null)
            mp.stop();
        super.onBackPressed();
    }
}
