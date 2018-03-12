package ru.gamesforkids.gamesforkids;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.ByteBuffer;

import static ru.gamesforkids.gamesforkids.R.id.lay1;

public class Game3_1Activity extends AppCompatActivity{
    int colorP = Color.parseColor("#fc5fe2");
    Paint mPaint = new Paint();

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


        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setXfermode(null);
        mPaint.setAlpha(0xff);


        final ImageView cat = findViewById(R.id.cat);
        cat.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {


                final ImageView img = (ImageView) v;

                final Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();

                int x = (int) (event.getX()*bitmap.getWidth()/img.getWidth());
                int y = (int) (event.getY()*bitmap.getHeight()/img.getHeight());
                int color = bitmap.getPixel(x,y);
//if (color != 0){

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touch_start(x, y);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        touch_move(x, y, bitmap, colorP);
                        break;
                    case MotionEvent.ACTION_UP:
                        touch_up();
                        break;
                }
                return true;
// }
//return true;
            }
        });
    }


    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 5;
    private Path mPath= new Path();



    private void touch_start(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touch_move(float x, float y, Bitmap bitmap, int color) {

        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            closeOptionsMenu();
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
        mPath.lineTo(mX, mY);


//mPath.reset();

        if (bitmap.getPixel((int)x,(int)y)== Color.BLACK) {
            Toast.makeText(Game3_1Activity.this, "border", Toast.LENGTH_SHORT).show();
        }
        Bitmap bitmapResult = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(bitmapResult);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.contur);
        mPaint.setColor(color);
        mPaint.setStrokeWidth(20);
        mCanvas.drawBitmap(bitmap, 0, 0, mPaint);
        mCanvas.drawPath(mPath, mPaint);
        mCanvas.drawBitmap(bm, 0, 0, mPaint);


        ImageView firstImageView = findViewById(R.id.cat);
        firstImageView.setImageBitmap(bitmapResult);

    }

    private void touch_up() {
        mPath.reset();
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