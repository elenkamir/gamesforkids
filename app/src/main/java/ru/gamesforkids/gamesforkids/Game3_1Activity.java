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
                    firstImageView.setImageBitmap(paint(bitmap,Color.RED, x,y));
                }

                TextView Text = findViewById(R.id.textView);
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
}