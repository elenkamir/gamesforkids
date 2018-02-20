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
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class Game3_1Activity extends AppCompatActivity {
    int color = Color.WHITE;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3_1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Button color1 = findViewById(R.id.bRed);
        ImageView img1 = (ImageView) findViewById(R.id.catHead);
        ImageView img2 = (ImageView) findViewById(R.id.catBackear);
        ImageView img3 = findViewById(R.id.catFrontear);
        ImageView img4 = findViewById(R.id.catMiddlebow);
        ImageView img5 = findViewById(R.id.catLeftbow);
        ImageView img6 = findViewById(R.id.catRightbow);
        ImageView img7 = findViewById(R.id.catNeck);
        ImageView img8 = findViewById(R.id.catBody);
        ImageView img9 = findViewById(R.id.catLeftpad);
        ImageView img10 = findViewById(R.id.catTail);

        color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = Color.RED;
            }
        });

        img1.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                final ImageView img = (ImageView) v;
                final Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
                int x = (int) (event.getX() * bitmap.getWidth() / img.getWidth());
                int y = (int) (event.getY() * bitmap.getHeight() / img.getHeight());
                int colory = bitmap.getPixel(x, y);
                if (colory != 0) {
                    ImageView firstImageView = findViewById(R.id.catHead);
                    firstImageView.setImageBitmap(tintImage(bitmap, color));
                }
                return true;
            }

        });

        img2.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                final ImageView img = (ImageView) v;
                final Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
                int x = (int) (event.getX() * bitmap.getWidth() / img.getWidth());
                int y = (int) (event.getY() * bitmap.getHeight() / img.getHeight());
                int colory = bitmap.getPixel(x, y);
                if (colory != 0) {
                    ImageView firstImageView = findViewById(R.id.catBackear);
                    firstImageView.setImageBitmap(tintImage(bitmap, color));
                }
                return true;
            }

        });

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