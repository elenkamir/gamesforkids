package ru.gamesforkids.gamesforkids;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Game5Activity extends AppCompatActivity {


    ImageButton arrOfImageButton[] = new ImageButton[7];
    ImageButton info, resetButton;
    Integer level;
    int[] numbers = {0, 1, 2, 3, 4, 5, 6};
    int[] user_numbers;
    int ui;
    MediaPlayer mp = null;
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

        arrOfImageButton[0].setColorFilter(Color.rgb(254, 0, 0));
        arrOfImageButton[1].setColorFilter(Color.rgb(255, 127, 0));
        arrOfImageButton[2].setColorFilter(Color.rgb(255, 255, 1));
        arrOfImageButton[3].setColorFilter(Color.rgb(0, 255, 1));
        arrOfImageButton[4].setColorFilter(Color.rgb(1, 255, 255));
        arrOfImageButton[5].setColorFilter(Color.rgb(0, 0, 254));
        arrOfImageButton[6].setColorFilter(Color.rgb(189, 2, 255));


        level = 0;
        ImageButtonsUnEnable();
        listeners();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                newGame();
            }
        }, 1000);

        info = (ImageButton) findViewById(R.id.info_g5);
        // вызов справки
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Game5InfoActivity.class);
                startActivity(intent);
            }
        });

        resetButton = (ImageButton) findViewById(R.id.imgButtonReset);
        resetButton.setEnabled(false);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetButton.setEnabled(false);
                if (toast == null || toast.getView().getWindowVisibility() != View.VISIBLE) {
                    toast = Toast.makeText(getApplicationContext(),
                            "Хорошо, давай снова. Уровень 1.", Toast.LENGTH_SHORT);
                    toast.show();
                }
                ImageButtonsUnEnable();
                level = 0;

                newGame();
            }
        });


        dialog = new Dialog(this);

        dialog.setTitle(" Молодец! Отгадал! Сыграем ещё?");
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
                        level = 0;
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
/*    @Override
    public void onBackPressed() {
        if (this.isFinishing() & mp != null) {
            mp.stop();
        }
        super.onBackPressed();
    }*/


    @Override
    public void onPause() {
 /*       if (this.isFinishing() & mp != null){
            mp.stop();
        }*/
        if (this.isFinishing()) {
            level = -1;
            toast = null;
        }
        super.onPause();
    }


    private void newGame() {
        resetButton.setEnabled(false);
        if (level < 7) {
            ui = 0;
            user_numbers = new int[level + 1];
            shuffle();
        }
    }

    public void shuffle() {
        resetButton.setEnabled(false);
        Random random = new Random();

        for (int i = 6; i >= 1; i--) {
            int j = random.nextInt(i + 1);
            // обменять значения data[j] и data[i]
            int temp = numbers[j];
            numbers[j] = numbers[i];
            numbers[i] = temp;
        }


        playit();

    }

    public void playit() {
        resetButton.setEnabled(false);
        toast = Toast.makeText(getApplicationContext(),
                "Твоя очередь", Toast.LENGTH_SHORT);
        ImageButtonsUnEnable();
        switch (level) {
            case 0:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[0]);
                    }
                }, 1000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ImageButtonsEnable();
                        if (toast != null) toast.show();
                    }
                }, 1500);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resetButton.setEnabled(true);
                    }
                }, 1500);
                break;
            case 1:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[0]);
                    }
                }, 1000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[1]);
                    }
                }, 2000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ImageButtonsEnable();
                        if (toast != null) toast.show();
                    }
                }, 2500);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resetButton.setEnabled(true);
                    }
                }, 2500);
                break;
            case 2:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[0]);
                    }
                }, 1000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[1]);
                    }
                }, 2000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[2]);
                    }
                }, 3000);
                if (level != -1) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ImageButtonsEnable();
                            if (toast != null) toast.show();
                        }
                    }, 3500);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resetButton.setEnabled(true);
                    }
                }, 3500);
                break;
            case 3:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[0]);
                    }
                }, 1000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[1]);
                    }
                }, 2000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[2]);
                    }
                }, 3000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[3]);
                    }
                }, 4000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ImageButtonsEnable();
                        if (toast != null) toast.show();
                    }
                }, 4500);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resetButton.setEnabled(true);
                    }
                }, 4500);
                break;
            case 4:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[0]);
                    }
                }, 1000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[1]);
                    }
                }, 2000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[2]);
                    }
                }, 3000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[3]);
                    }
                }, 4000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[4]);
                    }
                }, 5000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ImageButtonsEnable();
                        if (toast != null) toast.show();
                    }
                }, 5500);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resetButton.setEnabled(true);
                    }
                }, 5500);
                break;
            case 5:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[0]);
                    }
                }, 1000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[1]);
                    }
                }, 2000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[2]);
                    }
                }, 3000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[3]);
                    }
                }, 4000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[4]);
                    }
                }, 5000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[5]);
                    }
                }, 6000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ImageButtonsEnable();
                        if (toast != null) toast.show();
                    }
                }, 6500);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resetButton.setEnabled(true);
                    }
                }, 6500);
                break;
            case 6:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[0]);
                    }
                }, 1000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[1]);
                    }
                }, 2000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[2]);
                    }
                }, 3000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[3]);
                    }
                }, 4000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[4]);
                    }
                }, 5000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[5]);
                    }
                }, 6000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickColor(numbers[6]);
                    }
                }, 7000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ImageButtonsEnable();
                        if (toast != null) toast.show();
                    }
                }, 7500);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resetButton.setEnabled(true);
                    }
                }, 7500);
                break;
            default:
                break;
        }
    }

    public void check() {
        ImageButtonsUnEnable();

        int count = 0;
        for (int i = 0; i <= level; i++) {
            if (user_numbers[i] == numbers[i]) count++;
        }

        if (count == level + 1 & level < 6) {
            ImageButtonsUnEnable();
            toast = Toast.makeText(getApplicationContext(),
                    "Правильно!", Toast.LENGTH_SHORT);
            toast.show();
            level++;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast = Toast.makeText(getApplicationContext(),
                            "Уровень " + (level + 1), Toast.LENGTH_SHORT);
                    toast.show();
                }
            }, 800);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    newGame();
                }
            }, 3500);

        } else if (count == level + 1 & level == 6) {
            dialog.show();
            ImageButtonsUnEnable();
        } else {
            ImageButtonsUnEnable();

            toast = Toast.makeText(getApplicationContext(),
                    "Неправильно!", Toast.LENGTH_SHORT);
            toast.show();
            ui = 0;

            user_numbers = new int[0];
            user_numbers = new int[level + 1];

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    playit();
                }
            }, 1400);


        }

    }


    public void clickColor(int m) {
        resetButton.setEnabled(false);
        switch (m) {
            case 0:
                arrOfImageButton[0].setColorFilter(Color.rgb(255, 150, 150));  // более светлый цвет
                managerOfSound(0);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        arrOfImageButton[0].setColorFilter(Color.rgb(254, 0, 0));  // первоначальный цвет
                    }
                }, 1000);
                break;

            // оранжевый
            case 1:
                arrOfImageButton[1].setColorFilter(Color.rgb(255, 194, 133));
                managerOfSound(1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        arrOfImageButton[1].setColorFilter(Color.rgb(255, 127, 0));
                    }
                }, 1000);
                break;

            // желтый
            case 2:
                arrOfImageButton[2].setColorFilter(Color.rgb(255, 255, 150));
                managerOfSound(2);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        arrOfImageButton[2].setColorFilter(Color.rgb(255, 255, 1));
                    }
                }, 1000);
                break;

            // зеленый
            case 3:
                arrOfImageButton[3].setColorFilter(Color.rgb(200, 255, 200));
                managerOfSound(3);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        arrOfImageButton[3].setColorFilter(Color.rgb(0, 255, 1));
                    }
                }, 1000);
                break;

            // голубой
            case 4:
                arrOfImageButton[4].setColorFilter(Color.rgb(200, 255, 255));
                managerOfSound(4);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        arrOfImageButton[4].setColorFilter(Color.rgb(1, 255, 255));
                    }
                }, 1000);
                break;

            // синий
            case 5:
                arrOfImageButton[5].setColorFilter(Color.rgb(150, 150, 255));
                managerOfSound(5);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        arrOfImageButton[5].setColorFilter(Color.rgb(0, 0, 254));
                    }
                }, 1000);
                break;

            // фиолетовый
            case 6:
                arrOfImageButton[6].setColorFilter(Color.rgb(220, 120, 255));
                managerOfSound(6);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        arrOfImageButton[6].setColorFilter(Color.rgb(189, 2, 255));
                    }
                }, 1000);
                break;
            default:
                break;
        }

    }


    public void listeners() {
        // красный
        arrOfImageButton[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickColor(0);
                user_numbers[ui++] = 0;
                if (ui == level + 1) {
                    check();
                }
            }
        });

        // оранжевый
        arrOfImageButton[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickColor(1);
                user_numbers[ui++] = 1;
                if (ui == level + 1) {
                    check();
                }
            }
        });

        // желтый
        arrOfImageButton[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickColor(2);
                user_numbers[ui++] = 2;
                if (ui == level + 1) {
                    check();
                }
            }
        });

        // зеленый
        arrOfImageButton[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickColor(3);
                user_numbers[ui++] = 3;
                if (ui == level + 1) {
                    check();
                }
            }
        });

        // голубой
        arrOfImageButton[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickColor(4);
                user_numbers[ui++] = 4;
                if (ui == level + 1) {
                    check();
                }
            }
        });
        // синий
        arrOfImageButton[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickColor(5);
                user_numbers[ui++] = 5;
                if (ui == level + 1) {
                    check();
                }
            }
        });

        // фиолетовый
        arrOfImageButton[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickColor(6);
                user_numbers[ui++] = 6;
                if (ui == level + 1) {
                    check();
                }
            }
        });

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
        if (level != -1) {
            if (mp != null) {
                mp.reset();
                mp.release();
            }
            switch (key) {
                case 0:
                    mp = MediaPlayer.create(this, R.raw.piano_a);
                    break;
                case 1:
                    mp = MediaPlayer.create(this, R.raw.piano_b);
                    break;
                case 2:
                    mp = MediaPlayer.create(this, R.raw.piano_c);
                    break;
                case 3:
                    mp = MediaPlayer.create(this, R.raw.piano_d);
                    break;
                case 4:
                    mp = MediaPlayer.create(this, R.raw.piano_e);
                    break;
                case 5:
                    mp = MediaPlayer.create(this, R.raw.piano_f);
                    break;
                case 6:
                    mp = MediaPlayer.create(this, R.raw.piano_g);
                    break;
            }
            mp.start();
        }
    }
}

