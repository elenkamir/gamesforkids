package ru.gamesforkids.gamesforkids;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Game4Activity extends AppCompatActivity {
    ImageView imgResult,imgTask;
    TextView txRed,txBlue,txGreen;
    private SeekBar redSeekBar, greenSeekBar, blueSeekBar;

                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_game4);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


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


//для проверки - нормально заполняет
                    imgTask.setColorFilter(0xff00ffff);

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
    private void updateBackground() {
        int redValue, greenValue, blueValue;
        redValue = redSeekBar.getProgress();
        greenValue = greenSeekBar.getProgress();
        blueValue = blueSeekBar.getProgress();

        txRed.setText(Integer.toString(redValue));
        txGreen.setText(Integer.toString(greenValue));
        txBlue.setText(Integer.toString(blueValue));

    }
}

