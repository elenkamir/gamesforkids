package ru.gamesforkids.gamesforkids;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    ImageButton info;
    ProgressBar time;

    int white = Color.WHITE;
    int green = Color.GREEN;
    int red = Color.RED;
    int yellow = Color.YELLOW;
    int blue = Color.BLUE;

    int up;
    int down;
    int ansColor;
    List<Integer> btColors;
    List<String> colorsI;
    static CountDownTimer timer;
    int duration, interval, curTime;
    MediaPlayer clickMP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game6);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
        colorsI = Arrays.asList("Белый", "Зелёный", "Красный", "Желтый", "Синий");
        clickMP = MediaPlayer.create(this, R.raw.g2click);

        duration = 15000;
        time.setMax(duration);
        interval = 30;
        time.incrementProgressBy(interval);
        curTime = duration;
        time.setProgress(curTime);

        timer = new CountDownTimer(duration, interval) {
            @Override
            public void onTick(long l) {
                curTime -= interval;
                time.setProgress(curTime);
            }

            @Override
            public void onFinish() {
                curTime -= interval;
                time.setProgress(curTime);
                color1.setClickable(false);
                color2.setClickable(false);
                color3.setClickable(false);
                color4.setClickable(false);
                color5.setClickable(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), Game6FinishActivity.class);
                        intent.putExtra("up", String.valueOf(up));
                        intent.putExtra("down", String.valueOf(down));
                        intent.putExtra("result", String.valueOf(up - down));
                        startActivity(intent);
                        finish();
                    }
                }, 1000);
            }
        };
        timer.start();

        up = 0;
        down = 0;
        upPoint.setText(String.valueOf(up));
        downPoint.setText(String.valueOf(down));

        newRound();

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pause = true;
                timer.cancel();
                Intent intent = new Intent(getApplicationContext(), Game6InfoActivity.class);
                startActivity(intent);
            }
        });
        color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickMP.start();
                if (btColors.get(0) == ansColor) {
                    up++;
                    upPoint.setText(String.valueOf(up));
                } else {
                    down++;
                    downPoint.setText(String.valueOf(down));
                }
                newRound();
            }
        });
        color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickMP.start();
                if (btColors.get(1) == ansColor) {
                    up++;
                    upPoint.setText(String.valueOf(up));
                } else {
                    down++;
                    downPoint.setText(String.valueOf(down));
                }
                newRound();
            }
        });
        color3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickMP.start();
                if (btColors.get(2) == ansColor) {
                    up++;
                    upPoint.setText(String.valueOf(up));
                } else {
                    down++;
                    downPoint.setText(String.valueOf(down));
                }
                newRound();
            }
        });
        color4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickMP.start();
                if (btColors.get(3) == ansColor) {
                    up++;
                    upPoint.setText(String.valueOf(up));
                } else {
                    down++;
                    downPoint.setText(String.valueOf(down));
                }
                newRound();
            }
        });
        color5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickMP.start();
                if (btColors.get(4) == ansColor) {
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

    private void newRound() {
        setBtColors();
        setColor();
    }

    private void setBtColors() {
        btColors = Arrays.asList(1, 2, 3, 4, 5);
        Collections.shuffle(btColors);

        color1.setColorFilter(returnColor(btColors.get(0)));
        color2.setColorFilter(returnColor(btColors.get(1)));
        color3.setColorFilter(returnColor(btColors.get(2)));
        color4.setColorFilter(returnColor(btColors.get(3)));
        color5.setColorFilter(returnColor(btColors.get(4)));
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

    public static void resume() {
        timer.start();
    }
}
