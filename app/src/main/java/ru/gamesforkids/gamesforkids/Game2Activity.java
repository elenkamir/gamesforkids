package ru.gamesforkids.gamesforkids;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Game2Activity extends AppCompatActivity {
    ImageButton ibColor1;
    ImageButton ibColor2;
    ImageButton ibColor3;
    ImageView ivArc1;
    ImageView ivArc2;
    ImageView ivArc3;
    ImageView ivArc4;
    ImageView ivArc5;

    int k = 0;
    ArrayList<ImageView> arcs;
    ArrayList<Integer> btColors;
    ArrayList<Integer> playerComb;
    ArrayList<Integer> rightComb;
    ArrayList<Integer> setOfColors;
    List<Integer> mix;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        ibColor1 = findViewById(R.id.color1ib);
        ibColor2 = findViewById(R.id.color2ib);
        ibColor3 = findViewById(R.id.color3ib);

        ivArc1 = findViewById(R.id.arc1iv);
        ivArc2 = findViewById(R.id.arc2iv);
        ivArc3 = findViewById(R.id.arc3iv);
        ivArc4 = findViewById(R.id.arc4iv);
        ivArc5 = findViewById(R.id.arc5iv);

        arcs = new ArrayList<>();
        arcs.add(ivArc2);
        arcs.add(ivArc3);
        arcs.add(ivArc4);

        rightComb = new ArrayList<>();
        rightComb.add(1);
        rightComb.add(2);
        rightComb.add(3);
        newGame();

        ibColor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arcs.get(k).setColorFilter(btColors.get(0));
                ibColor1.setColorFilter(null);
                ibColor1.setClickable(false);
                playerComb.add(mix.get(0));
                if (k < 2)
                    k++;
                else
                    check(view);
            }
        });
        ibColor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arcs.get(k).setColorFilter(btColors.get(1));
                ibColor2.setColorFilter(null);
                ibColor2.setClickable(false);
                playerComb.add(mix.get(1));
                if (k < 2)
                    k++;
                else
                    check(view);
            }
        });
        ibColor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arcs.get(k).setColorFilter(btColors.get(2));
                ibColor3.setColorFilter(null);
                ibColor3.setClickable(false);
                playerComb.add(mix.get(2));
                if (k < 2)
                    k++;
                else
                    check(view);
            }
        });
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
        mix = Arrays.asList(1,2,3);
        Collections.shuffle(mix);

        btColors = new ArrayList<>();
        btColors.add(setOfColors.get(mix.get(0)));
        btColors.add(setOfColors.get(mix.get(1)));
        btColors.add(setOfColors.get(mix.get(2)));

        ivArc1.setColorFilter(setOfColors.get(0));
        ivArc5.setColorFilter(setOfColors.get(4));

        redraw();
        k = 0;
        playerComb = new ArrayList<>();
    }
    private int compColor(int[] color) {
        return Color.rgb(color[0],color[1],color[2]);
    }
    private ArrayList<Integer> genColors(boolean redir, boolean from0, int inVar, int f, int c, int d) {
        /*
        color[0] = r;
        color[1] = g;
        color[2] = b;
         */
        int[] color = new int[3];
        color[inVar] = c;
        ArrayList<Integer> setOfGenColors = new ArrayList<>();
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
        setOfGenColors.add(compColor(color));
        while (k < 5) {
            if (color[f] + d < 256)
                color[f] += d;
            else {
                color[3 - f - inVar] -= d - 255 + color[f];
                color[f] = 255;
            }
            setOfGenColors.add(compColor(color));
            k++;
        }
        return setOfGenColors;
    }
    private void check(View view) {
        int delay = 3500;
        if (playerComb.equals(rightComb)) {
            Toast.makeText(view.getContext(), "Как красиво! :)", Toast.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    newGame();
                }
            }, delay);
        } else {
            Toast.makeText(view.getContext(), ":c Давай еще раз!", Toast.LENGTH_LONG).show();
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
        ibColor1.setColorFilter(btColors.get(0));
        ibColor2.setColorFilter(btColors.get(1));
        ibColor3.setColorFilter(btColors.get(2));
        ibColor1.setClickable(true);
        ibColor2.setClickable(true);
        ibColor3.setClickable(true);
        ivArc2.setColorFilter(null);
        ivArc3.setColorFilter(null);
        ivArc4.setColorFilter(null);
    }
}
