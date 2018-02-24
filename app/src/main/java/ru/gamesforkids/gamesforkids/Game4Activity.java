package ru.gamesforkids.gamesforkids;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Game4Activity extends AppCompatActivity {

    ImageView imgResult, imgTask;
    TextView txRed, txBlue, txGreen;
    int red, green, blue, acc, RGBtask;
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

        imgResult = (ImageView) findViewById(R.id.imgResult);
        imgTask = (ImageView) findViewById(R.id.imgTask);

        txRed = (TextView) findViewById(R.id.txRed);
        txGreen = (TextView) findViewById(R.id.txGreen);
        txBlue = (TextView) findViewById(R.id.txBlue);

        redSeekBar = (SeekBar) findViewById(R.id.seekBarRed);
        greenSeekBar = (SeekBar) findViewById(R.id.seekBarGreen);
        blueSeekBar = (SeekBar) findViewById(R.id.seekBarBlue);
        updateBackground();

        redSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        greenSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        blueSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        acc = 30; // точность
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
            Toast.makeText(getApplicationContext(), "УРА", Toast.LENGTH_LONG).show();
            managerOfSound(true);
            imgResult.setColorFilter(RGBtask);
            redSeekBar.setEnabled(false);
            greenSeekBar.setEnabled(false);
            blueSeekBar.setEnabled(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    newGame();
                    redSeekBar.setEnabled(true);
                    greenSeekBar.setEnabled(true);
                    blueSeekBar.setEnabled(true);
                }
            }, 3000);


        }

    }



    protected void managerOfSound(boolean correct) {
        if (mp != null) {
            mp.reset();
            mp.release();
        }
        if (correct)
            mp = MediaPlayer.create(this, R.raw.g1correct);
        else
            mp = MediaPlayer.create(this, R.raw.g1incorrect);
        mp.start();
    }
}