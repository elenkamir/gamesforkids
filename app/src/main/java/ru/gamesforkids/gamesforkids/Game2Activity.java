package ru.gamesforkids.gamesforkids;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        ibColor1 = (ImageButton) findViewById(R.id.color1ib);
        ibColor2 = (ImageButton) findViewById(R.id.color2ib);
        ibColor3 = (ImageButton) findViewById(R.id.color3ib);

        ivArc1 = (ImageView) findViewById(R.id.arc1iv);
        ivArc2 = (ImageView) findViewById(R.id.arc2iv);
        ivArc3 = (ImageView) findViewById(R.id.arc3iv);
        ivArc4 = (ImageView) findViewById(R.id.arc4iv);
        ivArc5 = (ImageView) findViewById(R.id.arc5iv);

        Random r = new Random();
        int inVar = r.nextInt(3);           // число, которое не будет изменяться
        int f = r.nextInt(3);               // число, которое начнет изменяться первым
        if (f == inVar)
            f = (inVar + 1) % 3;                   // взять просто следующее число...
        int c = r.nextInt(50) + 150;             // color[inVar] = c
        int d = (r.nextInt(4) + 6) * 10;    // на сколько будут изменяться значения r/g/b
        int tmp = r.nextInt(1000);
        boolean redir = false;                     // color[f]:  false => 0 -> 255;  true => 255 -> 0
        if (tmp % 2 == 1) redir = true;

        ArrayList<g2Color> setOfColors = genColors(redir, inVar, f, c, d);

        // Todo: убрать заливку радуги и поработать с кнопками
        ivArc1.setColorFilter(Color.rgb(setOfColors.get(0).r,setOfColors.get(0).g,setOfColors.get(0).b));
        ivArc2.setColorFilter(Color.rgb(setOfColors.get(1).r,setOfColors.get(1).g,setOfColors.get(1).b));
        ivArc3.setColorFilter(Color.rgb(setOfColors.get(2).r,setOfColors.get(2).g,setOfColors.get(2).b));
        ivArc4.setColorFilter(Color.rgb(setOfColors.get(3).r,setOfColors.get(3).g,setOfColors.get(3).b));
        ivArc5.setColorFilter(Color.rgb(setOfColors.get(4).r,setOfColors.get(4).g,setOfColors.get(4).b));
    }
    private g2Color compColor(int[] color) {
        g2Color g2C = new g2Color();
        g2C.r = color[0];
        g2C.g = color[1];
        g2C.b = color[2];
        return g2C;
    }
    private ArrayList<g2Color> genColors(boolean redir, int inVar, int f, int c, int d) {
        /*
        color[0] = r;
        color[1] = g;
        color[2] = b;
         */
        int[] color = new int[3];
        color[inVar] = c;
        ArrayList<g2Color> setOfColors = new ArrayList<>();
        int k = 0;
        if (redir)
            f = 3 - f - inVar;

        color[f] = 0;
        color[3 - f - inVar] = 255;            // т.к. 0+1+2 = 3
        setOfColors.add(compColor(color));
        while (k < 5) {
            if (color[f] + d < 256)
                color[f] += d;
            else
                color[3 - f - inVar] -= d;

            setOfColors.add(compColor(color));
            k++;
        }
        return setOfColors;
    }
}
