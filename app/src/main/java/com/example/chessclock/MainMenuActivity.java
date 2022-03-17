package com.example.chessclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    int hour;
    int minute;
    int second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        hideDefaultAppBar();

        Button butProceed = (Button) findViewById(R.id.StartButton);
        butProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent clockIntent = new Intent(MainMenuActivity.this, ClockActivity.class);
                startActivity(clockIntent);
            }
        });

        Button butOptions = (Button) findViewById(R.id.optionbutton);
        butOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent optionIntent = new Intent(MainMenuActivity.this, OptionActivity.class);
                startActivity(optionIntent);
            }
        });

    }

    void hideDefaultAppBar()
    {
        getSupportActionBar().hide();
    }


}