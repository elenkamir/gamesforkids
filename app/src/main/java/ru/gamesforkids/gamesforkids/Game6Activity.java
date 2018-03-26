package ru.gamesforkids.gamesforkids;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ankushgrover.hourglass.Hourglass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game6Activity extends AppCompatActivity {
    TextView textColor;
    TextView upPoint;
    TextView downPoint;
    ImageButton color1;
    ImageButton color2;
    ImageButton color3;
    ImageButton color4;
    ImageButton color5;
    ArrayList<ImageButton> colors;
    ImageButton info;
    ProgressBar time;

    int white = Color.parseColor("#ffffff");
    int green = Color.parseColor("#00ff00");
    int red = Color.parseColor("#ff0000");
    int yellow = Color.parseColor("#ffff00");
    int blue = Color.parseColor("#0000ff");
    int darkWhite = Color.parseColor("#a8a8a8");
    int darkGreen = Color.parseColor("#00a800");
    int darkRed = Color.parseColor("#a80000");
    int darkYellow = Color.parseColor("#a8a800");
    int darkBlue = Color.parseColor("#0000a8");

    int up;
    int down;
    int ansColor;
    List<Integer> btColors;
    List<String> colorsI;
    static Hourglass timer;
    int duration;
    MediaPlayer clickMP;
    SharedPreferences rec;
    final String RECORD = "record";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game6);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //resetRec(); // сбросить рекорд
        info = findViewById(R.id.info);
        time = findViewById(R.id.timer);
        textColor = findViewById(R.id.r_color);
        upPoint = findViewById(R.id.up_point);
        downPoint = findViewById(R.id.down_point);
        color1 = findViewById(R.id.bt_color1);
        color2 = findViewById(R.id.bt_color2);
        color3 = findViewById(R.id.bt_color3);
        color4 = findViewById(R.id.bt_color4);
        color5 = findViewById(R.id.bt_color5);
        colors = new ArrayList<>();
        colors.add(color1);
        colors.add(color2);
        colors.add(color3);
        colors.add(color4);
        colors.add(color5);
        colorsI = Arrays.asList("Белый", "Зелёный", "Красный", "Желтый", "Синий");
        clickMP = MediaPlayer.create(this, R.raw.g2click);

        duration = 10000;
        time.setMax(duration);
        time.incrementProgressBy(50);
        time.setProgress(duration);

        timer = new Hourglass(duration, 50) {
            @Override
            public void onTimerTick(long timeRemaining) {
                time.setProgress((int) timeRemaining);
            }

            @Override
            public void onTimerFinish() {
                time.setProgress(0);
                for (ImageButton color : colors)
                    color.setClickable(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), Game6FinishActivity.class);
                        intent.putExtra("up", up);
                        intent.putExtra("down", down);
                        intent.putExtra("record", getRec());
                        startActivity(intent);
                        if (up - down > getRec())
                            saveRec(up - down);
                        finish();
                    }
                }, 1000);
            }
        };
        timer.startTimer();
        up = 0;
        down = 0;
        upPoint.setText(String.valueOf(up));
        downPoint.setText(String.valueOf(down));

        newRound();

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
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.pauseTimer();
                Intent intent = new Intent(getApplicationContext(), Game6InfoActivity.class);
                startActivity(intent);
            }
        });
        for (int i = 0; i < 5; i++) {
            final int ii = i;
            colors.get(i).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            colors.get(ii).setColorFilter(returnDarkColor(btColors.get(ii)));
                            colors.get(ii).invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL: {
                            colors.get(ii).clearColorFilter();
                            colors.get(ii).invalidate();
                            break;
                        }
                    }
                    return false;
                }
            });
            colors.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickMP.start();
                    if (btColors.get(ii) == ansColor) {
                        up++;
                        upPoint.setText(String.valueOf(up));
                    } else {
                        down++;
                        downPoint.setText(String.valueOf(down));
                    }
                    newRound();
                }
            });
        }
    }

    private void newRound() {
        setBtColors();
        setColor();
    }

    private void setBtColors() {
        btColors = Arrays.asList(1, 2, 3, 4, 5);
        Collections.shuffle(btColors);
        for (int i = 0; i < 5; i++)
            colors.get(i).setColorFilter(returnColor(btColors.get(i)));
    }

    private void setColor() {
        ansColor = new Random().nextInt(5) + 1;
        textColor.setText(colorsI.get(new Random().nextInt(5)));
        textColor.setTextColor(returnColor(ansColor));
    }

    private int returnColor(int n) {
        switch (n) {
            case 2:
                return green;
            case 3:
                return red;
            case 4:
                return yellow;
            case 5:
                return blue;
            default:
                return white;
        }
    }

    private int returnDarkColor(int n) {
        switch (n) {
            case 2:
                return darkGreen;
            case 3:
                return darkRed;
            case 4:
                return darkYellow;
            case 5:
                return darkBlue;
            default:
                return darkWhite;
        }
    }

    public static void resume() {
        timer.resumeTimer();
    }

    @Override
    public void onBackPressed() {
        timer.pauseTimer();
        finish();
    }

    public void resetRec() {
        rec = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = rec.edit();
        ed.clear();
        ed.apply();
    }

    public int getRec() {
        rec = getPreferences(MODE_PRIVATE);
        String record = rec.getString(RECORD, "0");
        return Integer.valueOf(record);
    }

    void saveRec(int res) {
        rec = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = rec.edit();
        ed.putString(RECORD, String.valueOf(res));
        ed.apply();
    }
}
