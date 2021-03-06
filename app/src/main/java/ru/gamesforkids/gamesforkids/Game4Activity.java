package ru.gamesforkids.gamesforkids;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import pl.droidsonroids.gif.GifImageView;


public class Game4Activity extends AppCompatActivity {

    ImageView imgResult, imgTask;
    GifImageView rightAns;
    TextView txRed, txBlue, txGreen;
    int red, green, blue, RGBtask;
    static int acc, levelCur;
    ImageButton info;
    private SeekBar redSeekBar, greenSeekBar, blueSeekBar;

    String TAG = "tag";
    MediaPlayer mp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mp = MediaPlayer.create(this, R.raw.g2right);

        info = findViewById(R.id.info_g4);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) info.getLayoutParams();
        params.height = size.y / 12;
        params.width = params.height;
        info.setLayoutParams(params);

        LinearLayout seekBarsLL = findViewById(R.id.seekBars);
        seekBarsLL.setPadding(0, 0, 0, size.y / 10);

        imgResult = (ImageView) findViewById(R.id.imgResult);
        imgTask = (ImageView) findViewById(R.id.imgTask);
        rightAns = (GifImageView) findViewById(R.id.right_ans_g4);

        txRed = (TextView) findViewById(R.id.txRed);
        txGreen = (TextView) findViewById(R.id.txGreen);
        txBlue = (TextView) findViewById(R.id.txBlue);
        txRed.setTextSize(size.x / 50 > size.y / 30 ? size.y / 30 : size.x / 50);
        txGreen.setTextSize(size.x / 50 > size.y / 30 ? size.y / 30 : size.x / 50);
        txBlue.setTextSize(size.x / 50 > size.y / 30 ? size.y / 30 : size.x / 50);

        redSeekBar = (SeekBar) findViewById(R.id.seekBarRed);
        greenSeekBar = (SeekBar) findViewById(R.id.seekBarGreen);
        blueSeekBar = (SeekBar) findViewById(R.id.seekBarBlue);
        updateBackground();

        redSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        greenSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        blueSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        // acc = l1; // точность

        levelCur = 1;
        acc = 40;

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Game4InfoActivity.class);
                intent.putExtra("level", levelCur);
                startActivity(intent);
            }
        });
        info.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        info.setColorFilter(0x65000000, PorterDuff.Mode.SRC_ATOP);
                        info.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        info.clearColorFilter();
                        info.invalidate();
                        break;
                    }
                }
                return false;
            }
        });

        newGame();
    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            updateBackground();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    //изменение значений бегунков
    private void newGame() {
        Random rnd = new Random();
        red = rnd.nextInt((255 - 0) + 1) + 0;
        green = rnd.nextInt((255 - 0) + 1) + 0;
        blue = rnd.nextInt((255 - 0) + 1) + 0;

        redSeekBar.setProgress(150);
        greenSeekBar.setProgress(150);
        blueSeekBar.setProgress(150);

        Log.i(TAG, String.valueOf(red));
        Log.i(TAG, String.valueOf(green));
        Log.i(TAG, String.valueOf(blue));
        RGBtask = android.graphics.Color.rgb(red, green, blue);
        imgTask.setColorFilter(RGBtask);
        imgResult.setColorFilter(android.graphics.Color.rgb(150, 150, 150));

        Log.i("LEVEL", String.valueOf(acc));
    }

    public static void setLevel(int level) {
        int l1, l2, l3;
        levelCur = level;
        l1 = level * 40;  // 40
        l2 = level * 15;  // 30
        l3 = level * 6;  // 18

        if (level == 1)
            acc = l1;
        if (level == 2)
            acc = l2;
        if (level == 3)
            acc = l3;
    }

    //изменение значений бегунков
    private void updateBackground() {
        int redValue, greenValue, blueValue;
        redValue = redSeekBar.getProgress();
        greenValue = greenSeekBar.getProgress();
        blueValue = blueSeekBar.getProgress();

        txRed.setText(Integer.toString(redValue));
        txGreen.setText(Integer.toString(greenValue));
        txBlue.setText(Integer.toString(blueValue));

        int RGB = android.graphics.Color.rgb(redValue, greenValue, blueValue);

        imgResult.setColorFilter(RGB);

        if (((redValue > red - acc) & (redValue < red + acc))
                & ((greenValue > green - acc) & (greenValue < green + acc))
                & ((blueValue > blue - acc) & (blueValue < blue + acc))) {
            rightAns.setVisibility(View.VISIBLE);
            mp.start();
            imgResult.setColorFilter(RGBtask);
            txRed.setText(Integer.toString(red));
            txGreen.setText(Integer.toString(green));
            txBlue.setText(Integer.toString(blue));
            redSeekBar.setProgress(red);
            greenSeekBar.setProgress(green);
            blueSeekBar.setProgress(blue);
            redSeekBar.setEnabled(false);
            greenSeekBar.setEnabled(false);
            blueSeekBar.setEnabled(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    rightAns.setVisibility(View.GONE);
                    newGame();
                    redSeekBar.setEnabled(true);
                    greenSeekBar.setEnabled(true);
                    blueSeekBar.setEnabled(true);

                }
            }, 3000);
        }
    }

    @Override
    public void onBackPressed() {
        if (mp != null)
            mp.stop();
        super.onBackPressed();
    }
}