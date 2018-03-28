package ru.gamesforkids.gamesforkids;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import pl.droidsonroids.gif.GifImageView;


public class Game1Activity extends AppCompatActivity {
    String TAG = "TAG";

    ImageButton info;

    ImageView resultColorImageView;
    TextView resultColorTextView;

    ImageView firstColorImageView;
    TextView firstColorTextView;

    ImageView secondColorImageView;
    TextView secondColorTextView;

    ImageView PlusImageView, EquationImageView;

    GifImageView WrongAns;
    GifImageView RightAns;
    ImageView GoodFinish;

    Random random;

    Boolean isFirstClick;
    int color1, color2, i;
    g1ResultColor color;
    ArrayList<g1ResultColor> g1ResultColors;
    String[] cTitles = new String[5];
    int[] btColors = new int[5];
    int[] btDarkColors = new int[5];
    ImageButton[] buttons = new ImageButton[5];

    MediaPlayer mp = null;
    MediaPlayer click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        click = MediaPlayer.create(this, R.raw.g2click);

        cTitles[0] = "Белый";
        cTitles[1] = "Жёлтый";
        cTitles[2] = "Красный";
        cTitles[3] = "Синий";
        cTitles[4] = "Черный";

        btColors[0] = Color.WHITE;
        btColors[1] = Color.YELLOW;
        btColors[2] = Color.RED;
        btColors[3] = Color.BLUE;
        btColors[4] = Color.BLACK;

        btDarkColors[0] = Color.parseColor("#f4f4f4");
        btDarkColors[1] = Color.parseColor("#f4f400");
        btDarkColors[2] = Color.parseColor("#ce0000");
        btDarkColors[3] = Color.parseColor("#0000ce");
        btDarkColors[4] = Color.parseColor("#333333");

        info = (ImageButton) findViewById(R.id.info_g1);

        resultColorImageView = (ImageView) findViewById(R.id.imgResult);
        resultColorTextView = (TextView) findViewById(R.id.tvColorResult);

        firstColorImageView = (ImageView) findViewById(R.id.imgColor1);
        firstColorTextView = (TextView) findViewById(R.id.tvColor1);

        secondColorImageView = (ImageView) findViewById(R.id.imgColor2);
        secondColorTextView = (TextView) findViewById(R.id.tvColor2);

        PlusImageView = (ImageView) findViewById(R.id.imgPlus);
        EquationImageView = (ImageView) findViewById(R.id.imgEquation);

        buttons[0] = findViewById(R.id.imageButton1);
        buttons[1] = findViewById(R.id.imageButton2);
        buttons[2] = findViewById(R.id.imageButton3);
        buttons[3] = findViewById(R.id.imageButton4);
        buttons[4] = findViewById(R.id.imageButton5);
        for (int i = 0; i < 5; i++)
            buttons[i].setColorFilter(btColors[i]);

        WrongAns = (GifImageView) findViewById(R.id.wrong_ans);
        RightAns = (GifImageView) findViewById(R.id.right_ans);
        GoodFinish = (ImageView) findViewById(R.id.good_finish);

        // вызов справки
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Game1InfoActivity.class);
                startActivity(intent);
            }
        });
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

        random = new Random();

        g1ResultColors = new ArrayList<>();

  /*      б - 1
                ж - 2
                        к - 3
                                с - 4
                                        ч - 5*/

        g1ResultColor purple = new g1ResultColor();
        purple.g1Title = getResources().getString(R.string.g1_purple);
        purple.g1ColorOne = 3;
        purple.g1ColorTwo = 4;
        purple.g1Color = ContextCompat.getColor(this, R.color.purple);
        g1ResultColors.add(purple);

        g1ResultColor orange = new g1ResultColor();
        orange.g1Title = getResources().getString(R.string.g1_orange);
        orange.g1ColorOne = 2;
        orange.g1ColorTwo = 3;
        orange.g1Color = ContextCompat.getColor(this, R.color.orange);
        g1ResultColors.add(orange);

        g1ResultColor pink = new g1ResultColor();
        pink.g1Title = getResources().getString(R.string.g1_pink);
        pink.g1ColorOne = 1;
        pink.g1ColorTwo = 3;
        pink.g1Color = ContextCompat.getColor(this, R.color.pink);
        g1ResultColors.add(pink);

        g1ResultColor lightblue = new g1ResultColor();
        lightblue.g1Title = getResources().getString(R.string.g1_lightblue);
        lightblue.g1ColorOne = 1;
        lightblue.g1ColorTwo = 4;
        lightblue.g1Color = ContextCompat.getColor(this, R.color.lightblue);
        g1ResultColors.add(lightblue);

        g1ResultColor green = new g1ResultColor();
        green.g1Title = getResources().getString(R.string.g1_green);
        green.g1ColorOne = 2;
        green.g1ColorTwo = 4;
        green.g1Color = ContextCompat.getColor(this, R.color.green);
        g1ResultColors.add(green);

        g1ResultColor gray = new g1ResultColor();
        gray.g1Title = getResources().getString(R.string.g1_gray);
        gray.g1ColorOne = 1;
        gray.g1ColorTwo = 5;
        gray.g1Color = ContextCompat.getColor(this, R.color.gray);
        g1ResultColors.add(gray);

        g1ResultColor darkblue = new g1ResultColor();
        darkblue.g1Title = getResources().getString(R.string.g1_darkblue);
        darkblue.g1ColorOne = 4;
        darkblue.g1ColorTwo = 5;
        darkblue.g1Color = ContextCompat.getColor(this, R.color.darkblue);
        g1ResultColors.add(darkblue);

        //Log.i(TAG, String.valueOf(getResources().getColor(R.color.purple)));


        Collections.shuffle(g1ResultColors);
        setResultColor();

        isFirstClick = true;

        for (int j = 0; j < 5; j++) {
            final int jj = j;
            buttons[j].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            if (buttons[jj].isClickable()) {
                                buttons[jj].setColorFilter(btDarkColors[jj]);
                                buttons[jj].invalidate();
                                break;
                            }
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL: {
                            buttons[jj].setColorFilter(btColors[jj]);
                            buttons[jj].invalidate();
                            break;
                        }
                    }
                    return false;
                }
            });
            buttons[j].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click.start();
                    if (isFirstClick) {
                        firstColorImageView.setColorFilter(btColors[jj]);
                        color1 = jj + 1;
                        firstColorTextView.setText(cTitles[jj]);
                        firstColorTextView.setTextColor(btColors[jj]);
                        isFirstClick = false;
                    } else {
                        secondColorImageView.setColorFilter(btColors[jj]);
                        secondColorTextView.setText(cTitles[jj]);
                        secondColorTextView.setTextColor(btColors[jj]);
                        color2 = jj + 1;
                        if ((color1 != color2) & (color1 == color.g1ColorOne || color1 == color.g1ColorTwo) & (color2 == color.g1ColorOne || color2 == color.g1ColorTwo)) {
                            i++;
                            RightAns.setVisibility(View.VISIBLE);
                            managerOfSound(true);
                            setButtonsUnclickable();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    RightAns.setVisibility(View.GONE);
                                    setButtonsClickable();
                                    setResultColor();
                                    firstColorImageView.clearColorFilter();
                                    secondColorImageView.clearColorFilter();
                                    firstColorTextView.setText("");
                                    secondColorTextView.setText("");
                                }
                            }, 3000);
                        } else {
                            WrongAns.setVisibility(View.VISIBLE);
                            managerOfSound(false);
                            setButtonsUnclickable();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    WrongAns.setVisibility(View.GONE);
                                    setButtonsClickable();
                                    firstColorImageView.clearColorFilter();
                                    secondColorImageView.clearColorFilter();
                                    firstColorTextView.setText("");
                                    secondColorTextView.setText("");
                                }
                            }, 3000);
                        }
                        // сделать сброс цветов
                        isFirstClick = true;
                    }
                }
            });
        }
    }

    public void onBackPressed() {
        if (mp != null)
            mp.stop();
        super.onBackPressed();
    }

    public void setResultColor() {
        if (i < g1ResultColors.size()) {
            color = g1ResultColors.get(i);
            resultColorTextView.setTextColor(color.g1Color);
            resultColorTextView.setText(color.g1Title);
            resultColorImageView.setColorFilter(color.g1Color);
        } else {
            setButtonsUnclickable();
            info.setClickable(false);
            resultColorTextView.setText("");
            firstColorTextView.setText("");
            secondColorTextView.setText("");
            firstColorImageView.setImageDrawable(null);
            secondColorImageView.setImageDrawable(null);
            resultColorImageView.setImageDrawable(null);
            PlusImageView.setImageDrawable(null);
            EquationImageView.setImageDrawable(null);
            GoodFinish.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 5000);
            i = 0;
            Collections.shuffle(g1ResultColors);
        }
    }

    public void setButtonsUnclickable() {
        for (ImageButton bt : buttons)
            bt.setClickable(false);
    }

    public void setButtonsClickable() {
        for (ImageButton bt : buttons)
            bt.setClickable(true);
    }

    protected void managerOfSound(boolean correct) {
        if (mp != null) {
            mp.reset();
            mp.release();
        }
        if (correct)
            mp = MediaPlayer.create(this, R.raw.g2right);
        else
            mp = MediaPlayer.create(this, R.raw.g2wrong);
        mp.start();
    }
}