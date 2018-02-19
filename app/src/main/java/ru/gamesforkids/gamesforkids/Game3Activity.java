package ru.gamesforkids.gamesforkids;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class Game3Activity extends AppCompatActivity {

    ImageButton first;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        first = findViewById(R.id.imageButton6);

    }

    public void onClick(View v){
        Intent intent = new Intent(this, Game3_1Activity.class);
        startActivity(intent);
    }

}

