package ru.gamesforkids.gamesforkids;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class Game3_1Activity extends AppCompatActivity {



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3_1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        ImageView img1 = (ImageView) findViewById(R.id.catHead);
        //ImageView secondImageView = findViewById(R.id.imageView8);


        img1.setOnTouchListener(new  View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                final ImageView img = (ImageView) v;
                final Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
                int x = (int) (event.getX()*bitmap.getWidth()/img.getWidth());
                int y = (int) (event.getY()*bitmap.getHeight()/img.getHeight());
                int color = bitmap.getPixel(x,y);
                if (color != 0){
                    ImageView firstImageView = findViewById(R.id.catHead);
                    firstImageView.setImageBitmap(tintImage(bitmap,Color.YELLOW));
                }
                return true;
            }

        });
        /*secondImageView.setOnTouchListener(new  View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                final ImageView img = (ImageView) v;
                final Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
                int x = (int) (event.getX()*bitmap.getWidth()/img.getWidth());
                int y = (int) (event.getY()*bitmap.getHeight()/img.getHeight());
                int color = bitmap.getPixel(x,y);
                if (color != 0){
                    ImageView firstImageView = findViewById(R.id.imageView8);
                    firstImageView.setImageBitmap(tintImage(bitmap,Color.GREEN));
                }
                return true;
            }

        });*/





    }


    public static Bitmap tintImage(Bitmap bitmap, int color) {
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        Bitmap bitmapResult = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapResult);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return bitmapResult;
    }

}
