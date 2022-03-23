package com.example.chessclock;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class OptionActivity extends AppCompatActivity {

    private int hour;
    private int minute;
    private int second;

    private String player1Name;
    private String player2Name;

    Intent myIntent;

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        Intent optionIntent = getIntent();

        hideDeafultAppBar();
        myIntent = new Intent(OptionActivity.this, ClockActivity.class);

        radioGroup = findViewById(R.id.GameTimeRadioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    case R.id.fullTimeButton:
                        myIntent.putExtra("gameType","full");
                        break;
                    case R.id.repeatedTimeButton:
                        myIntent.putExtra("gameType","repeat");
                        break;
                }
            }
        });

        radioGroup.clearCheck();

        Button butOptionsSave = (Button) findViewById(R.id.optionsSaveButton);
        butOptionsSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText hourInput   = findViewById(R.id.editHourTimeText);
                EditText minuteInput   = findViewById(R.id.editMinuteTimeText);
                EditText secondInput  = findViewById(R.id.editSecondTimeText);

                CheckNullExceptions(hourInput.getText().toString(), minuteInput.getText().toString(), secondInput.getText().toString());

                EditText player1text = findViewById(R.id.player1NameText);
                EditText player2text = findViewById(R.id.player2NameText);

                player1Name = player1text.getText().toString();
                player2Name = player2text.getText().toString();

                myIntent.putExtra("player1name",player1Name);
                myIntent.putExtra("player2name",player2Name);

                myIntent.putExtra("hour", hour);
                myIntent.putExtra("minute",minute);
                myIntent.putExtra("second",second);
                startActivity(myIntent);
            }
        });
    }

    void hideDeafultAppBar()
    {
        getSupportActionBar().hide();
    }

    void CheckNullExceptions(String h, String m, String s)
    {
        if (h == null || h == "") {
            hour = 0;
        }
        else
        {
            hour = Integer.parseInt(h);
        }

        if (m == null || m == "") {
            minute = 10;
        }
        else
        {
            minute = Integer.parseInt(m);
        }

        if (s == null ||  s == ""){
            second = 0;
        }
        else
        {
            second = Integer.parseInt(s);
        }
    }

}