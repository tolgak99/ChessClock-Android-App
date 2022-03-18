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
                hour = Integer.parseInt(hourInput.getText().toString());

                EditText minuteInput   = findViewById(R.id.editMinuteTimeText);
                minute = Integer.parseInt(minuteInput.getText().toString());

                EditText secondInput   = findViewById(R.id.editSecondTimeText);
                second = Integer.parseInt(secondInput.getText().toString());

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


}