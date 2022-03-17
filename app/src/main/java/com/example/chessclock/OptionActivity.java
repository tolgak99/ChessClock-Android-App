package com.example.chessclock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class OptionActivity extends AppCompatActivity {

    private int hour;
    private int minute;
    private int second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        Intent optionIntent = getIntent();

        hideDeafultAppBar();

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

                Intent myIntent = new Intent(OptionActivity.this, ClockActivity.class);
                myIntent.putExtra("hour", hour);
                myIntent.putExtra("minute",minute);
                myIntent.putExtra("second",second);
                startActivity(myIntent);
            }
        });
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    void hideDeafultAppBar()
    {
        getSupportActionBar().hide();
    }



}