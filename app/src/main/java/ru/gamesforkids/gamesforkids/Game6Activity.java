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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game6Activity extends AppCompatActivity {
    TextView tvColor;
    TextView tvUpPoint;
    TextView tvDownPoint;
    ImageButton ibColor1;
    ImageButton ibColor2;
    ImageButton ibColor3;
    ImageButton ibColor4;
    ImageButton ibColor5;
    static ImageButton[] ibColors;
    ImageButton info;
    ProgressBar time;

    int[] colorsNum = {Color.parseColor("#ffffff"), Color.parseColor("#00ff00"),
            Color.parseColor("#ff0000"), Color.parseColor("#ffff00"), Color.parseColor("#0000ff")};
    int[] darkColorsNum = {Color.parseColor("#a8a8a8"), Color.parseColor("#00a800"),
            Color.parseColor("#a80000"), Color.parseColor("#a8a800"), Color.parseColor("#0000a8")};

    int upPoint;
    int downPoint;
    int ansColorNum;
    List<Integer> btColorsNum;
    String[] cTitles = {"Белый", "Зелёный", "Красный", "Желтый", "Синий"};
    static Hourglass timer;
    int duration;
    int curDur;
    MediaPlayer clickMP;
    static SharedPreferences rec;
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
        rec = getPreferences(MODE_PRIVATE);
        info = findViewById(R.id.info);
        time = findViewById(R.id.timer);
        tvColor = findViewById(R.id.r_color);
        tvUpPoint = findViewById(R.id.up_point);
        tvDownPoint = findViewById(R.id.down_point);
        ibColor1 = findViewById(R.id.bt_color1);
        ibColor2 = findViewById(R.id.bt_color2);
        ibColor3 = findViewById(R.id.bt_color3);
        ibColor4 = findViewById(R.id.bt_color4);
        ibColor5 = findViewById(R.id.bt_color5);
        ibColors = new ImageButton[5];
        ibColors[0] = ibColor1;
        ibColors[1] = ibColor2;
        ibColors[2] = ibColor3;
        ibColors[3] = ibColor4;
        ibColors[4] = ibColor5;
        clickMP = MediaPlayer.create(this, R.raw.g2click);

        duration = 5000;
        curDur = duration;
        time.setMax(duration);
        time.incrementProgressBy(50);
        time.setProgress(duration);

        setTimer(curDur);
        timer.startTimer();
        upPoint = 0;
        downPoint = 0;
        tvUpPoint.setText(String.valueOf(upPoint));
        tvDownPoint.setText(String.valueOf(downPoint));

        newRound();

        info.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (info.isClickable()) {
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
            ibColors[i].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            ibColors[ii].setColorFilter(darkColorsNum[btColorsNum.get(ii) - 1]);
                            ibColors[ii].invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL: {
                            ibColors[ii].setColorFilter(colorsNum[btColorsNum.get(ii) - 1]);
                            ibColors[ii].invalidate();
                            break;
                        }
                    }
                    return false;
                }
            });
            ibColors[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickMP.start();
                    if (btColorsNum.get(ii) == ansColorNum) {
                        upPoint++;
                        tvUpPoint.setText(String.valueOf(upPoint));
                        timer.pauseTimer();
                        timer = null;
                        if (curDur + 600 > duration)
                            setTimer(duration);
                        else
                            setTimer(curDur + 600);
                        timer.startTimer();
                    } else {
                        downPoint++;
                        tvDownPoint.setText(String.valueOf(downPoint));
                        if (curDur - 500 <= 0)
                            timer.stopTimer();
                        else {
                            timer.pauseTimer();
                            timer = null;
                            setTimer(curDur - 500);
                            timer.startTimer();
                        }
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

    private void setTimer(int dur) {
        timer = new Hourglass(dur, 50) {
            @Override
            public void onTimerTick(long timeRemaining) {
                curDur = (int) timeRemaining;
                time.setProgress(curDur);
            }

            @Override
            public void onTimerFinish() {
                time.setProgress(0);
                for (ImageButton color : ibColors)
                    color.setClickable(false);
                clickMP.reset();
                clickMP.release();
                clickMP = MediaPlayer.create(getApplicationContext(), R.raw.g6end);
                clickMP.start();
                info.setClickable(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!isFinishing()) {
                            Intent intent = new Intent(getApplicationContext(), Game6FinishActivity.class);
                            intent.putExtra("up", upPoint);
                            intent.putExtra("down", downPoint);
                            intent.putExtra("record", getRec());
                            startActivity(intent);
                            if (upPoint - downPoint > getRec())
                                saveRec(upPoint - downPoint);
                            finish();
                        }
                    }
                }, 1000);
            }
        };
    }

    private void setBtColors() {
        btColorsNum = Arrays.asList(1, 2, 3, 4, 5);
        Collections.shuffle(btColorsNum);
        for (int i = 0; i < 5; i++)
            ibColors[i].setColorFilter(colorsNum[btColorsNum.get(i) - 1]);
    }

    private void setColor() {
        ansColorNum = new Random().nextInt(5) + 1;
        tvColor.setText(cTitles[new Random().nextInt(5)]);
        tvColor.setTextColor(colorsNum[ansColorNum - 1]);
    }

    public static void resume() {
        for (ImageButton color : ibColors)
            color.setClickable(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (ImageButton color : ibColors)
                    color.setClickable(true);
                timer.resumeTimer();
            }
        }, 70);
    }

    @Override
    public void onBackPressed() {
        timer.pauseTimer();
        clickMP.stop();
        finish();
    }

    public static void resetRec() {
        SharedPreferences.Editor ed = rec.edit();
        ed.clear();
        ed.apply();
    }

    public int getRec() {
        String record = rec.getString(RECORD, "0");
        return Integer.valueOf(record);
    }

    void saveRec(int res) {
        SharedPreferences.Editor ed = rec.edit();
        ed.putString(RECORD, String.valueOf(res));
        ed.apply();
    }
}
