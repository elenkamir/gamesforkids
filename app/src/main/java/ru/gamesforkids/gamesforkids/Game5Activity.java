package ru.gamesforkids.gamesforkids;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;

public class Game5Activity extends AppCompatActivity {

    ImageButton btR, btO, btY, btG, btC, btB, btV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game5);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btR = (ImageButton) findViewById(R.id.imageBtnPianoRed);
        btO = (ImageButton) findViewById(R.id.imageBtnPianoOrange);
        btY = (ImageButton) findViewById(R.id.imageBtnPianoYellow);
        btG = (ImageButton) findViewById(R.id.imageBtnPianoGreen);
        btC = (ImageButton) findViewById(R.id.imageBtnPianoCyan);
        btB = (ImageButton) findViewById(R.id.imageBtnPianoBlue);
        btV = (ImageButton) findViewById(R.id.imageBtnPianoViolet);

        listeners();
    }

    public void listeners() {
        // красный
        btR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btR.setColorFilter(Color.rgb(255, 150, 150));  // более светлый цвет
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btR.setColorFilter(Color.rgb(254, 0, 0));  // первоначальный цвет
                    }
                }, 300);
            }
        });

        // оранжевый
        btO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btO.setColorFilter(Color.rgb(255, 194, 133));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btO.setColorFilter(Color.rgb(255, 127, 0));
                    }
                }, 300);
            }
        });

        // желтый
        btY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btY.setColorFilter(Color.rgb(255, 255, 150));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btY.setColorFilter(Color.rgb(255, 255, 1));
                    }
                }, 300);
            }
        });

        // зеленый
        btG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btG.setColorFilter(Color.rgb(200, 255, 200));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btG.setColorFilter(Color.rgb(0, 255, 1));
                    }
                }, 300);
            }
        });

        // голубой
        btC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btC.setColorFilter(Color.rgb(200, 255, 255));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btC.setColorFilter(Color.rgb(1, 255, 255));
                    }
                }, 300);
            }
        });

        // синий
        btB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btB.setColorFilter(Color.rgb(150, 150, 255));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btB.setColorFilter(Color.rgb(0, 0, 254));
                    }
                }, 300);
            }
        });

        // фиолетовый
        btV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btV.setColorFilter(Color.rgb(220, 120, 255));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btV.setColorFilter(Color.rgb(189, 2, 255));
                    }
                }, 300);
            }
        });

    }

}
