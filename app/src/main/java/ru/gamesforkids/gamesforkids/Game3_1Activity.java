package ru.gamesforkids.gamesforkids;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static ru.gamesforkids.gamesforkids.R.id.lay1;

public class Game3_1Activity extends AppCompatActivity{
    float x;
    float y;
    String sDown;
    String sMove;
    String sUp;
    int colorP = Color.parseColor("#fc5fe2");

    public void blueOnClick (View view){
        colorP = Color.parseColor("#2a2add");
        deleteBorder();
        setBorder("blue");
    }
    public void purpleOnClick(View view){
        colorP = Color.parseColor("#8b00ff");
        deleteBorder();
        setBorder("purple");
    }
    public void orangeOnClick(View view){
        colorP = Color.parseColor("#ffa500");
        deleteBorder();
        setBorder("orange");
    }
    public void redOnClick(View view){
        colorP = Color.RED;
        deleteBorder();
        setBorder("red");
    }
    public void lightBlueOnClick(View view){
        colorP = Color.parseColor("#42AAFF");
        deleteBorder();
        setBorder("lightBlue");
    }
    public void pinkOnClick(View view){
        colorP =Color.parseColor("#ff4081");
        deleteBorder();
        setBorder("pink");
    }
    public void yellowOnClick(View view){
        colorP = Color.YELLOW;
        deleteBorder();
        setBorder("yellow");
    }
    public void lightGreenOnClick(View view){
        colorP = Color.parseColor("#a8f28d");
        deleteBorder();
        setBorder("lightGreen");
    }
    public void liloOnClick(View view){
        colorP = Color.parseColor("#fc5fe2");
        deleteBorder();
        setBorder("lilo");
    }
    public void greenOnClick(View view){
        colorP =Color.parseColor("#008000");
        deleteBorder();
        setBorder("green");
    }
    public void brownOnClick(View view){
        colorP = Color.parseColor("#ba6320");
        deleteBorder();
        setBorder("brown");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3_1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        final ImageView cat = findViewById(R.id.cat);
        final Paint p = new Paint();
        Rect rect = new Rect();
        p.setColor(Color.RED);
        // толщина линии = 10
        p.setStrokeWidth(10);
        cat.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {


                final ImageView img = (ImageView) v;
                final Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();

                int x = (int) (event.getX()*bitmap.getWidth()/img.getWidth());
                int y = (int) (event.getY()*bitmap.getHeight()/img.getHeight());
                int color = bitmap.getPixel(x,y);
                if (color != 0){
                    //Toast.makeText(Game3_1Activity.this, x + "x", Toast.LENGTH_SHORT).show();
                    ImageView firstImageView = findViewById(R.id.cat);
                    //bitmap.setPixel(x, y, Color.WHITE);
                    firstImageView.setImageBitmap(paint(bitmap,colorP, x,y));
                }

                //Text.setText(sDown + "\n" + sMove + "\n" + sUp);
                return true;
            }
        });
        //Canvas Canvas = new Canvas();
        //onDraw(Canvas);
    }
    protected void onDraw(Canvas canvas) {
        Paint p = new Paint();
        Rect rect = new Rect();

        p.setColor(Color.RED);
        // толщина линии = 10
        p.setStrokeWidth(10);
        //canvas.drawBitmap(bitmap, 0, 5, paint);

        // рисуем точку (50,50)
        canvas.drawPoint(50, 50, p);
    }

    public static Bitmap paint(Bitmap bitmap, int color, int x, int y) {
        Paint p = new Paint();
        p.setColor(color);
        // толщина линии = 10
        p.setStrokeWidth(30);
        Bitmap bitmapResult = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapResult);
        canvas.drawBitmap(bitmap, 0, 0, p);
        canvas.drawPoint(x, y, p);
        return bitmapResult;
    }

    public void setBorder(String Button){
        switch (Button){
            case "blue":
                findViewById(R.id.blue).setBackgroundResource(R.drawable.borders);
                break;
            case "red":
                findViewById(R.id.red).setBackgroundResource(R.drawable.borders);
                break;
            case "lightBlue":
                findViewById(R.id.lightblue).setBackgroundResource(R.drawable.borders);
                break;
            case "green":
                findViewById(R.id.green).setBackgroundResource(R.drawable.borders);
                break;
            case "lightGreen":
                findViewById(R.id.lightgreen).setBackgroundResource(R.drawable.borders);
                break;
            case "orange":
                findViewById(R.id.orange).setBackgroundResource(R.drawable.borders);
                break;
            case "yellow":
                findViewById(R.id.yellow).setBackgroundResource(R.drawable.borders);
                break;
            case "pink":
                findViewById(R.id.pink).setBackgroundResource(R.drawable.borders);
                break;
            case "lilo":
                findViewById(R.id.lilo).setBackgroundResource(R.drawable.borders);
                break;
            case "purple":
                findViewById(R.id.purple).setBackgroundResource(R.drawable.borders);
                break;
            case "brown":
                findViewById(R.id.brown).setBackgroundResource(R.drawable.borders);
                break;
        }
    }
    public void deleteBorder(){
        findViewById(R.id.blue).setBackgroundResource(R.drawable.roundcorner);
        findViewById(R.id.lightblue).setBackgroundResource(R.drawable.roundcorner);
        findViewById(R.id.green).setBackgroundResource(R.drawable.roundcorner);
        findViewById(R.id.lightgreen).setBackgroundResource(R.drawable.roundcorner);
        findViewById(R.id.red).setBackgroundResource(R.drawable.roundcorner);
        findViewById(R.id.orange).setBackgroundResource(R.drawable.roundcorner);
        findViewById(R.id.pink).setBackgroundResource(R.drawable.roundcorner);
        findViewById(R.id.yellow).setBackgroundResource(R.drawable.roundcorner);
        findViewById(R.id.purple).setBackgroundResource(R.drawable.roundcorner);
        findViewById(R.id.lilo).setBackgroundResource(R.drawable.roundcorner);
        findViewById(R.id.brown).setBackgroundResource(R.drawable.roundcorner);
    }
}