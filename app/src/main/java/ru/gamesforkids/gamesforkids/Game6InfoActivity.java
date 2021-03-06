package ru.gamesforkids.gamesforkids;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Game6InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game6_info);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        TextView info = findViewById(R.id.infoText);
        info.setTextSize(size.x / 50 > size.y / 28 ? size.y / 28 : size.x / 50);
        Button reset = findViewById(R.id.resetRec);
        reset.setTextSize(size.x / 75 > size.y / 42 ? size.y / 42 : size.x / 75);

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_game6_dialog);
        TextView question = dialog.findViewById(R.id.questionT);
        question.setTextSize(size.x / 50 > size.y / 28 ? size.y / 28 : size.x / 50);
        Button aYes = dialog.findViewById(R.id.yes);
        aYes.setTextSize(size.x / 75 > size.y / 42 ? size.y / 42 : size.x / 75);
        Button aNo = dialog.findViewById(R.id.no);
        aNo.setTextSize(size.x / 75 > size.y / 42 ? size.y / 42 : size.x / 75);

        aYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Game6Activity.resetRec();
                dialog.dismiss();
            }
        });
        aNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Game6Activity.resume();
        finish();
    }
}
