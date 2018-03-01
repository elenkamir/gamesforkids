package ru.gamesforkids.gamesforkids;

import android.content.pm.ActivityInfo;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;

public class Game5Activity extends AppCompatActivity {

    ImageButton btR, btO, btY, btG, btC, btB, btV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game5);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        btR = (ImageButton) findViewById(R.id.imageBtnPianoRed);
        btO = (ImageButton) findViewById(R.id.imageBtnPianoOrange);
        btY = (ImageButton) findViewById(R.id.imageBtnPianoYellow);
        btG = (ImageButton) findViewById(R.id.imageBtnPianoGreen);
        btC = (ImageButton) findViewById(R.id.imageBtnPianoCyan);
        btB = (ImageButton) findViewById(R.id.imageBtnPianoBlue);
        btV = (ImageButton) findViewById(R.id.imageBtnPianoViolet);


        btR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

    }


// some code
}
