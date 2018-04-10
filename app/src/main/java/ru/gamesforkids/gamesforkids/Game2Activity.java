package ru.gamesforkids.gamesforkids;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
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
import android.widget.Space;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class Game2Activity extends AppCompatActivity {
    ImageView ivArc1;
    ImageView ivArc5;
    ImageView rainbowContour;
    GifImageView goodEnd;
    GifImageView badEnd;
    ImageButton info;

    int k = 0;
    ImageView[] arcs = new ImageView[3];
    int[] btColors = new int[3];
    ArrayList<Integer> playerComb;
    ArrayList<Integer> rightComb;
    int[] setOfColors;
    ImageButton[] allColorBT = new ImageButton[3];
    ImageView[] allColorIV = new ImageView[3];
    ImageView[] allFlowerIV = new ImageView[3];
    List<Integer> mix;
    MediaPlayer clickMP;
    MediaPlayer endMP = null;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        info = findViewById(R.id.info);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) info.getLayoutParams();
        params.height = size.y / 12;
        params.width = params.height;
        info.setLayoutParams(params);

        clickMP = MediaPlayer.create(this, R.raw.g2click);
        goodEnd = findViewById(R.id.happySun);
        badEnd = findViewById(R.id.sadCloud);

        allColorBT[0] = findViewById(R.id.color1ib);
        allColorBT[1] = findViewById(R.id.color2ib);
        allColorBT[2] = findViewById(R.id.color3ib);

        allColorIV[0] = findViewById(R.id.color1iv);
        allColorIV[1] = findViewById(R.id.color2iv);
        allColorIV[2] = findViewById(R.id.color3iv);

        allFlowerIV[0] = findViewById(R.id.flower1);
        allFlowerIV[1] = findViewById(R.id.flower2);
        allFlowerIV[2] = findViewById(R.id.flower3);

        ivArc1 = findViewById(R.id.arc1iv);
        arcs[0] = findViewById(R.id.arc2iv);
        arcs[1] = findViewById(R.id.arc3iv);
        arcs[2] = findViewById(R.id.arc4iv);
        ivArc5 = findViewById(R.id.arc5iv);
        rainbowContour = findViewById(R.id.rainbowContour);

        rightComb = new ArrayList<>();
        rightComb.add(1);
        rightComb.add(2);
        rightComb.add(3);

        newGame();

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Game2InfoActivity.class);
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
        for (int i = 0; i < 3; i++) {
            final int ii = i;
            allColorBT[ii].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            if (allColorBT[ii].isClickable()) {
                                allFlowerIV[ii].setColorFilter(0x99d3edff, PorterDuff.Mode.SRC_ATOP);
                                allFlowerIV[ii].invalidate();
                                break;
                            }
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:
                            if (allColorBT[ii].isClickable()) {
                                allFlowerIV[ii].clearColorFilter();
                                allFlowerIV[ii].invalidate();
                                break;
                            }
                    }
                    return false;
                }
            });
            allColorBT[ii].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickMP.start();
                    arcs[k].setColorFilter(btColors[ii]);
                    allColorBT[ii].setColorFilter(0x99d3edff, PorterDuff.Mode.SRC_ATOP);
                    allColorBT[ii].setClickable(false);
                    allColorIV[ii].setColorFilter(0x99d3edff, PorterDuff.Mode.SRC_ATOP);
                    allFlowerIV[ii].setColorFilter(0x99d3edff, PorterDuff.Mode.SRC_ATOP);
                    playerComb.add(mix.get(ii));
                    if (k < 2) {
                        k++;
                        arcs[k].clearColorFilter();
                    } else
                        check();
                }
            });
        }
    }

    private void newGame() {
        Random r = new Random();
        int inVar = r.nextInt(3);           // число, которое не будет изменяться
        int f = r.nextInt(3);               // число, которое начнет изменяться первым
        if (f == inVar)
            f = (inVar + 1) % 3;                   // взять просто следующее число...
        int c = r.nextInt(200) + 25;        // color[inVar] = c
        int d = (r.nextInt(5) + 5) * 10;    // на сколько будут изменяться значения r/g/b
        boolean redir = false;                     // color[f]:  false => 0 -> 255;  true => 255 -> 0
        boolean from0 = false;
        if (r.nextInt(1000) % 2 == 1)
            redir = true;
        if (r.nextInt(1000) % 2 == 0)
            from0 = true;

        setOfColors = genColors(redir, from0, inVar, f, c, d);

        ivArc1.setColorFilter(setOfColors[0]);
        ivArc5.setColorFilter(setOfColors[4]);

        redraw();
        k = 0;
        playerComb = new ArrayList<>();
    }

    private int compColor(int[] color) {
        return Color.rgb(color[0], color[1], color[2]);
    }

    private int[] genColors(boolean redir, boolean from0, int inVar, int f, int c, int d) {
        /*
        color[0] = r;
        color[1] = g;
        color[2] = b;
         */
        int[] color = new int[3];
        color[inVar] = c;
        int[] setOfGenColors = new int[5];
        int k = 0;
        if (redir)
            f = 3 - f - inVar;

        if (from0) {
            color[f] = 0;
            color[3 - f - inVar] = 255;            // т.к. 0+1+2 = 3
        } else {
            color[f] = 255 - 5 * d / 2;
            color[3 - f - inVar] = 5 * d / 2;
        }
        setOfGenColors[0] = compColor(color);
        while (k < 4) {
            if (color[f] + d < 256)
                color[f] += d;
            else {
                color[3 - f - inVar] -= d - 255 + color[f];
                color[f] = 255;
            }
            k++;
            setOfGenColors[k] = compColor(color);
        }
        return setOfGenColors;
    }

    private void check() {
        int delay = 4000;
        if (endMP != null) {
            endMP.reset();
            endMP.release();
        }
        if (playerComb.equals(rightComb)) {
            //Toast.makeText(view.getContext(), "Как красиво! :)", Toast.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    rainbowContour.setVisibility(View.GONE);
                    endMP = MediaPlayer.create(getApplicationContext(), R.raw.g2right);
                    endMP.start();
                }
            }, delay / 10);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    goodEnd.setVisibility(View.VISIBLE);
                }
            }, delay / 4);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    newGame();
                }
            }, delay);
        } else {
            //Toast.makeText(view.getContext(), ":c Давай еще раз!", Toast.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    rainbowContour.setVisibility(View.GONE);
                    endMP = MediaPlayer.create(getApplicationContext(), R.raw.g2wrong);
                    endMP.start();
                }
            }, delay / 10);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    badEnd.setVisibility(View.VISIBLE);
                }
            }, delay / 4);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    k = 0;
                    redraw();
                    playerComb = new ArrayList<>();
                }
            }, delay);
        }
    }

    private void redraw() {
        mix = Arrays.asList(1, 2, 3);
        Collections.shuffle(mix);

        for (int i = 0; i < 3; i++) {
            btColors[i] = setOfColors[mix.get(i)];
            allColorBT[i].setColorFilter(btColors[i]);
            allColorBT[i].setClickable(true);
            allColorIV[i].clearColorFilter();
            allFlowerIV[i].clearColorFilter();
            arcs[i].clearColorFilter();
        }
        arcs[1].setColorFilter(0xffd3edff, PorterDuff.Mode.SRC_ATOP);
        arcs[2].setColorFilter(0xffd3edff, PorterDuff.Mode.SRC_ATOP);

        rainbowContour.setVisibility(View.VISIBLE);

        goodEnd.setVisibility(View.GONE);
        badEnd.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        if (endMP != null)
            endMP.stop();
        super.onBackPressed();
    }
}
