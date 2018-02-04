package ru.gamesforkids.gamesforkids;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity implements  View.OnClickListener {
    Button Game1,Game2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

//две кнопки для перехода на игры
        Game1 =  findViewById(R.id.btnGame1);
        Game2 =  findViewById(R.id.btnGame2);
        Game1.setOnClickListener(this);
        Game2.setOnClickListener(this);
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
            default:
                break;
        }
    }

}
