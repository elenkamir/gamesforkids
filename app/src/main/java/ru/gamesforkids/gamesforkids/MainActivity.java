package ru.gamesforkids.gamesforkids;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity implements  View.OnClickListener {
    ImageButton Game1, Game2, Game3, Game4, Game5, Game6;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // кнопки для перехода на игры
        Game1 = findViewById(R.id.btnGame1);
        Game2 = findViewById(R.id.btnGame2);
        Game3 = findViewById(R.id.btnGame3);
        Game4 = findViewById(R.id.btnGame4);
        Game5 = findViewById(R.id.btnGame5);
        Game6 = findViewById(R.id.btnGame6);
        Game1.setOnClickListener(this);
        Game2.setOnClickListener(this);
        Game3.setOnClickListener(this);
        Game4.setOnClickListener(this);
        Game5.setOnClickListener(this);
        Game6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGame1:
                Intent intent1 = new Intent(this, Game1Activity.class);
                startActivity(intent1);
                break;

            case R.id.btnGame2:
                Intent intent2 = new Intent(this, Game2Activity.class);
                startActivity(intent2);
                break;

            case R.id.btnGame3:
                Intent intent3 = new Intent(this, Game3Activity.class);
                startActivity(intent3);
                break;

            case R.id.btnGame4:
                Intent intent4 = new Intent(this, Game4Activity.class);
                startActivity(intent4);
                break;

            case R.id.btnGame5:
                Intent intent5 = new Intent(this, Game5Activity.class);
                startActivity(intent5);
                break;

            case R.id.btnGame6:
                Intent intent6 = new Intent(this, Game6Activity.class);
                startActivity(intent6);
                break;

            default:
                break;
        }
    }

}