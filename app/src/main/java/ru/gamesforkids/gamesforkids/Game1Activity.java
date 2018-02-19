package ru.gamesforkids.gamesforkids;

import android.app.Activity;
import android.graphics.Color;
import android.media.Image;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;


public class Game1Activity extends AppCompatActivity {
                String TAG = "TAG";
                ImageButton b1White;
                ImageButton b2Yellow;
                ImageButton b3Red;
                ImageButton b4Blue;
                ImageButton b5Black;

                ImageView resultColorImageView;
                TextView resultColorTextView;

                ImageView firstColorImageView;
                TextView firstColorTextView;

                ImageView secondColorImageView;
                TextView secondColorTextView;

                ImageView PlusImageView, EquationImageView;

                ImageView WrongAns;
                ImageView RightAns;
                ImageView GoodFinish;

                Random random;

                Boolean isFirstClick;
                int color1, color2, i;
                g1ResultColor color;
                ArrayList<g1ResultColor> g1ResultColors;

                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_game1);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                    resultColorImageView = (ImageView) findViewById(R.id.imgResult);
                    resultColorTextView = (TextView) findViewById(R.id.tvColorResult);

                    firstColorImageView = (ImageView) findViewById(R.id.imgColor1);
                    firstColorTextView = (TextView) findViewById(R.id.tvColor1);

                    secondColorImageView = (ImageView) findViewById(R.id.imgColor2);
                    secondColorTextView = (TextView) findViewById(R.id.tvColor2);

                    PlusImageView = (ImageView) findViewById(R.id.imgPlus);
                    EquationImageView = (ImageView) findViewById(R.id.imgEquation);

                    b1White = (ImageButton) findViewById(R.id.imageButton1);
                    b2Yellow = (ImageButton) findViewById(R.id.imageButton2);
                    b3Red = (ImageButton) findViewById(R.id.imageButton3);
                    b4Blue = (ImageButton) findViewById(R.id.imageButton4);
                    b5Black = (ImageButton) findViewById(R.id.imageButton5);


                    b1White.setColorFilter(getResources().getColor(R.color.white));
                    b2Yellow.setColorFilter(getResources().getColor(R.color.yellow));
                    b3Red.setColorFilter(getResources().getColor(R.color.red));
                    b4Blue.setColorFilter(getResources().getColor(R.color.blue));
                    b5Black.setColorFilter(getResources().getColor(R.color.black));

                    WrongAns = (ImageView) findViewById(R.id.wrong_ans);
                    RightAns = (ImageView) findViewById(R.id.right_ans);
                    GoodFinish = (ImageView) findViewById(R.id.good_finish);

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



                    b1White.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (isFirstClick) {
                                firstColorImageView.setImageDrawable(b1White.getDrawable());
                                color1 = 1;
                                firstColorTextView.setText("Белый");
                                firstColorTextView.setTextColor(getResources().getColor(R.color.white));
                                isFirstClick = false;
                            } else {
                                secondColorImageView.setImageDrawable(b1White.getDrawable());
                                secondColorTextView.setText("Белый");
                                secondColorTextView.setTextColor(getResources().getColor(R.color.white));
                                color2 = 1;
                                if ((color1 != color2) & (color1 == color.g1ColorOne || color1 == color.g1ColorTwo) & (color2 == color.g1ColorOne || color2 == color.g1ColorTwo)) {
                                    i++;
                                    RightAns.setVisibility(View.VISIBLE);
                                    setButtonsUnclickable();
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            RightAns.setVisibility(View.GONE);
                                            setButtonsClickable();
                                            setResultColor();
                                            firstColorImageView.setImageResource(R.drawable.question);
                                            secondColorImageView.setImageResource(R.drawable.question);
                                            firstColorTextView.setText("");
                                            secondColorTextView.setText("");
                                        }
                                    }, 1000);
                                } else {
                                    WrongAns.setVisibility(View.VISIBLE);
                                    setButtonsUnclickable();
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            WrongAns.setVisibility(View.GONE);
                                            setButtonsClickable();
                                            firstColorImageView.setImageResource(R.drawable.question);
                                            secondColorImageView.setImageResource(R.drawable.question);
                                            firstColorTextView.setText("");
                                            secondColorTextView.setText("");
                                        }
                                    }, 1500);
                                }
                                // сделать сброс цветов
                                isFirstClick = true;
                            }
                        }
                    });

                    b2Yellow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (isFirstClick) {
                                firstColorImageView.setImageDrawable(b2Yellow.getDrawable());
                                color1 = 2;
                                firstColorTextView.setText("Жёлтый");
                                firstColorTextView.setTextColor(getResources().getColor(R.color.yellow));
                                isFirstClick = false;
                            } else {
                                secondColorTextView.setText("Жёлтый");
                                secondColorTextView.setTextColor(getResources().getColor(R.color.yellow));
                                secondColorImageView.setImageDrawable(b2Yellow.getDrawable());
                                color2 = 2;
                                if ((color1 != color2) & (color1 == color.g1ColorOne || color1 == color.g1ColorTwo) & (color2 == color.g1ColorOne || color2 == color.g1ColorTwo)) {
                                    i++;
                                    RightAns.setVisibility(View.VISIBLE);
                                    setButtonsUnclickable();
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            RightAns.setVisibility(View.GONE);
                                            setButtonsClickable();
                                            setResultColor();
                                            firstColorImageView.setImageResource(R.drawable.question);
                                            secondColorImageView.setImageResource(R.drawable.question);
                                            firstColorTextView.setText("");
                                            secondColorTextView.setText("");
                                        }
                                    }, 1000);
                                } else {
                                    WrongAns.setVisibility(View.VISIBLE);
                                    setButtonsUnclickable();
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            WrongAns.setVisibility(View.GONE);
                                            setButtonsClickable();
                                            firstColorImageView.setImageResource(R.drawable.question);
                                            secondColorImageView.setImageResource(R.drawable.question);
                                            firstColorTextView.setText("");
                                            secondColorTextView.setText("");
                                        }
                                    }, 1500);
                                }
                                isFirstClick = true;
                            }
                        }
                    });

                    b3Red.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (isFirstClick) {
                                firstColorImageView.setImageDrawable(b3Red.getDrawable());
                                color1 = 3;
                                firstColorTextView.setText("Красный");
                                firstColorTextView.setTextColor(getResources().getColor(R.color.red));
                                isFirstClick = false;
                            } else {
                                secondColorTextView.setText("Красный");
                                secondColorTextView.setTextColor(getResources().getColor(R.color.red));
                                secondColorImageView.setImageDrawable(b3Red.getDrawable());
                                color2 = 3;
                                if ((color1 != color2) & (color1 == color.g1ColorOne || color1 == color.g1ColorTwo) & (color2 == color.g1ColorOne || color2 == color.g1ColorTwo)) {
                                    i++;
                                    RightAns.setVisibility(View.VISIBLE);
                                    setButtonsUnclickable();
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            RightAns.setVisibility(View.GONE);
                                            setButtonsClickable();
                                            setResultColor();
                                            firstColorImageView.setImageResource(R.drawable.question);
                                            secondColorImageView.setImageResource(R.drawable.question);
                                            firstColorTextView.setText("");
                                            secondColorTextView.setText("");
                                        }
                                    }, 1000);
                                } else {
                                    WrongAns.setVisibility(View.VISIBLE);
                                    setButtonsUnclickable();
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            WrongAns.setVisibility(View.GONE);
                                            setButtonsClickable();
                                            firstColorImageView.setImageResource(R.drawable.question);
                                            secondColorImageView.setImageResource(R.drawable.question);
                                            firstColorTextView.setText("");
                                            secondColorTextView.setText("");
                                        }
                                    }, 1500);
                                }
                                isFirstClick = true;
                            }
                        }
                    });

                    b4Blue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (isFirstClick) {
                                firstColorImageView.setImageDrawable(b4Blue.getDrawable());
                                color1 = 4;
                                firstColorTextView.setText("Синий");
                                firstColorTextView.setTextColor(getResources().getColor(R.color.blue));
                                isFirstClick = false;
                            } else {
                                secondColorTextView.setText("Синий");
                                secondColorTextView.setTextColor(getResources().getColor(R.color.blue));
                                secondColorImageView.setImageDrawable(b4Blue.getDrawable());
                                color2 = 4;
                                if ((color1 != color2) & (color1 == color.g1ColorOne || color1 == color.g1ColorTwo) & (color2 == color.g1ColorOne || color2 == color.g1ColorTwo)) {
                                    i++;
                                    RightAns.setVisibility(View.VISIBLE);
                                    setButtonsUnclickable();
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            RightAns.setVisibility(View.GONE);
                                            setButtonsClickable();
                                            setResultColor();
                                            firstColorImageView.setImageResource(R.drawable.question);
                                            secondColorImageView.setImageResource(R.drawable.question);
                                            firstColorTextView.setText("");
                                            secondColorTextView.setText("");
                                        }
                                    }, 1000);
                                } else {
                                    WrongAns.setVisibility(View.VISIBLE);
                                    setButtonsUnclickable();
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            WrongAns.setVisibility(View.GONE);
                                            setButtonsClickable();
                                            firstColorImageView.setImageResource(R.drawable.question);
                                            secondColorImageView.setImageResource(R.drawable.question);
                                            firstColorTextView.setText("");
                                            secondColorTextView.setText("");
                                        }
                                    }, 1500);
                                }
                                isFirstClick = true;
                            }
                        }
                    });

                    b5Black.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (isFirstClick) {
                                firstColorImageView.setImageDrawable(b5Black.getDrawable());
                                color1 = 5;
                                firstColorTextView.setText("Чёрный");
                                firstColorTextView.setTextColor(getResources().getColor(R.color.black));
                                isFirstClick = false;
                            } else {
                                secondColorTextView.setText("Чёрный");
                                secondColorTextView.setTextColor(getResources().getColor(R.color.black));
                                secondColorImageView.setImageDrawable(b5Black.getDrawable());
                                color2 = 5;
                                if ((color1 != color2) & (color1 == color.g1ColorOne || color1 == color.g1ColorTwo) & (color2 == color.g1ColorOne || color2 == color.g1ColorTwo)) {
                                    i++;
                                    RightAns.setVisibility(View.VISIBLE);
                                    setButtonsUnclickable();
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            RightAns.setVisibility(View.GONE);
                                            setButtonsClickable();
                                            setResultColor();
                                            firstColorImageView.setImageResource(R.drawable.question);
                                            secondColorImageView.setImageResource(R.drawable.question);
                                            firstColorTextView.setText("");
                                            secondColorTextView.setText("");
                                        }
                                    }, 1000);
                                } else {
                                    WrongAns.setVisibility(View.VISIBLE);
                                    setButtonsUnclickable();
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            WrongAns.setVisibility(View.GONE);
                                            setButtonsClickable();
                                            firstColorImageView.setImageResource(R.drawable.question);
                                            secondColorImageView.setImageResource(R.drawable.question);
                                            firstColorTextView.setText("");
                                            secondColorTextView.setText("");
                                        }
                                    }, 1500);
                                }
                                isFirstClick = true;
                            }
                        }
                    });
                }

    public void setResultColor() {
        if (i < g1ResultColors.size()) {
            color = g1ResultColors.get(i);
            resultColorTextView.setTextColor(color.g1Color);
            resultColorTextView.setText(color.g1Title);
            resultColorImageView.setColorFilter(color.g1Color);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setButtonsUnclickable();
                    resultColorTextView.setText("");
                    firstColorTextView.setText("");
                    secondColorTextView.setText("");
                    firstColorImageView.setImageDrawable(null);
                    secondColorImageView.setImageDrawable(null);
                    resultColorImageView.setImageDrawable(null);
                    PlusImageView.setImageDrawable(null);
                    EquationImageView.setImageDrawable(null);
                    GoodFinish.setVisibility(View.VISIBLE);
                }
            },1200);
            new Handler().postDelayed(new Runnable() {
                                          @Override
                                          public void run() {
                                              finish();
                                          }
                                      },4000);
            i = 0;
            Collections.shuffle(g1ResultColors);
        }
    }

    public  void setButtonsUnclickable(){
        b1White.setClickable(false);
        b2Yellow.setClickable(false);
        b3Red.setClickable(false);
        b4Blue.setClickable(false);
        b5Black.setClickable(false);
    }

    public  void setButtonsClickable(){
        b1White.setClickable(true);
        b2Yellow.setClickable(true);
        b3Red.setClickable(true);
        b4Blue.setClickable(true);
        b5Black.setClickable(true);
    }

}
