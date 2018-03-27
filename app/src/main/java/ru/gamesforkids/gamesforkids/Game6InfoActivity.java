package ru.gamesforkids.gamesforkids;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
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
        TextView info = findViewById(R.id.infoText);
        info.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_medium));
        Button reset = findViewById(R.id.resetRec);
        Button back = findViewById(R.id.back);

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_game6_dialog);
        Button aYes = dialog.findViewById(R.id.yes);
        Button aNo = dialog.findViewById(R.id.no);

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
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Game6Activity.resume();
        finish();
    }
}
