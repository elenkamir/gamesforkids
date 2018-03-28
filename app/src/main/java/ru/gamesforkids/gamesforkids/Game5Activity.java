package ru.gamesforkids.gamesforkids;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game5Activity extends AppCompatActivity {

    ImageButton arrOfImageButton[] = new ImageButton[7];
    ImageButton info, resetButton;
    Integer level;
    List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6);
    ArrayList<Integer> user_numbers;
    MediaPlayer mp = null;
    int[] sounds = {R.raw.piano_a, R.raw.piano_b, R.raw.piano_c, R.raw.piano_d, R.raw.piano_e, R.raw.piano_f, R.raw.piano_g};
    final int[] uColors = {Color.rgb(254, 0, 0), Color.rgb(255, 127, 0),
            Color.rgb(255, 255, 1), Color.rgb(0, 255, 1),
            Color.rgb(1, 255, 255), Color.rgb(0, 0, 254),
            Color.rgb(189, 2, 255)};
    Dialog dialog;
    Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game5);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        arrOfImageButton[0] = (ImageButton) findViewById(R.id.imageBtnPianoRed);
        arrOfImageButton[1] = (ImageButton) findViewById(R.id.imageBtnPianoOrange);
        arrOfImageButton[2] = (ImageButton) findViewById(R.id.imageBtnPianoYellow);
        arrOfImageButton[3] = (ImageButton) findViewById(R.id.imageBtnPianoGreen);
        arrOfImageButton[4] = (ImageButton) findViewById(R.id.imageBtnPianoCyan);
        arrOfImageButton[5] = (ImageButton) findViewById(R.id.imageBtnPianoBlue);
        arrOfImageButton[6] = (ImageButton) findViewById(R.id.imageBtnPianoViolet);

        for (int i = 0; i < 7; i++)
            arrOfImageButton[i].setColorFilter(uColors[i]);

        level = 1;
        ImageButtonsUnEnable();

        for (int i = 0; i < 7; i++) {
            final int ii = i;
            arrOfImageButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (toast != null)
                        toast.cancel();
                    clickColor(ii);
                    user_numbers.add(ii);
                    if (user_numbers.size() == level)
                        check();
                }
            });
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (level != 0) {
                    if (toast != null)
                        toast.cancel();
                    toast = Toast.makeText(getApplicationContext(),
                            "Уровень " + level, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        }, 150);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                newGame();
            }
        }, 500);

        info = (ImageButton) findViewById(R.id.info_g5);
        // вызов справки
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Game5InfoActivity.class);
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

        resetButton = (ImageButton) findViewById(R.id.imgButtonReset);
        resetButton.setEnabled(false);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetButton.setEnabled(false);
                if (toast != null)
                    toast.cancel();
                toast = Toast.makeText(getApplicationContext(),
                        "Хорошо, давай снова. Уровень 1.", Toast.LENGTH_SHORT);
                toast.show();
                ImageButtonsUnEnable();
                level = 1;

                newGame();
            }
        });


        dialog = new Dialog(this);
        //dialog.setTitle(" Молодец! Отгадал! Сыграем ещё?");
        dialog.setContentView(R.layout.activity_game5_dialog);

        Button btnOK = (Button) dialog.findViewById(R.id.button_OK_dialog);
        Button btnCancel = (Button) dialog.findViewById(R.id.button_Cancel_dialog);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        level = 1;
                        newGame();
                    }
                }, 500);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finish();
            }
        });
    }

    // ОН ДОЛЖЕН ОСТАНАВЛИВАТЬ МУЗЫКУ ПРИ НАЖАТИИ НА "НАЗАД!!!
    // НО ЭТОГО НЕ ПРОИСХОДИТ! ВТФ
    @Override
    public void onBackPressed() {
        level = 0;
        if (toast != null)
            toast.cancel();
        if (mp != null)
            mp.stop();
        toast = null;
        super.onBackPressed();
    }

    private void newGame() {
        resetButton.setEnabled(false);
        if (level < 8) {
            user_numbers = new ArrayList<>();
            Collections.shuffle(numbers);
            playit();
        }
    }

    public void playit() {
        if (level != 0) {
            resetButton.setEnabled(false);
            toast = Toast.makeText(getApplicationContext(),
                    "Твоя очередь", Toast.LENGTH_SHORT);
            ImageButtonsUnEnable();
            for (int i = 0; i < level; i++) {
                final int ii = i;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers.get(ii));
                    }
                }, (ii + 1) * 1000);
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageButtonsEnable();
                    if (toast != null)
                        toast.show();
                    resetButton.setEnabled(true);
                }
            }, level * 1000 + 500);
        }
    }

    public void check() {
        ImageButtonsUnEnable();
        if (user_numbers.equals(numbers))
            dialog.show();
        else if (user_numbers.equals(numbers.subList(0, level))) {
            if (toast != null)
                toast.cancel();
            toast = Toast.makeText(getApplicationContext(),
                    "Правильно!", Toast.LENGTH_SHORT);
            toast.show();
            level++;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (toast != null)
                        toast.cancel();
                    toast = Toast.makeText(getApplicationContext(),
                            "Уровень " + level, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }, 800);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    newGame();
                }
            }, 1500);
        } else {
            if (toast != null)
                toast.cancel();
            toast = Toast.makeText(getApplicationContext(),
                    "Неправильно!", Toast.LENGTH_SHORT);
            toast.show();
            user_numbers = new ArrayList<>();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    playit();
                }
            }, 1000);
        }
    }

    public void clickColor(int m) {
        resetButton.setEnabled(false);
        int[] lightColors = {Color.rgb(255, 150, 150), Color.rgb(255, 194, 133),
                Color.rgb(255, 255, 150), Color.rgb(200, 255, 200),
                Color.rgb(200, 255, 255), Color.rgb(150, 150, 255),
                Color.rgb(220, 120, 255)};

        arrOfImageButton[m].setColorFilter(lightColors[m]);  // более светлый цвет
        managerOfSound(m);
        final int mm = m;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                arrOfImageButton[mm].setColorFilter(uColors[mm]);  // первоначальный цвет
            }
        }, 300);
    }

    private void ImageButtonsEnable() {
        for (int i = 0; i < 7; i++)
            arrOfImageButton[i].setEnabled(true);
    }

    private void ImageButtonsUnEnable() {
        for (int i = 0; i < 7; i++)
            arrOfImageButton[i].setEnabled(false);
    }

    protected void managerOfSound(int key) {
        if (level != 0) {
            if (mp != null) {
                mp.reset();
                mp.release();
            }
            mp = MediaPlayer.create(this, sounds[key]);
            mp.start();
        }
    }
}
