package com.example.chessclock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class ClockActivity extends AppCompatActivity {

    Player player1;
    Player player2;
    private int playerTurn = 1;
    private int gameType = 0;    // 0 -> full / 1 -> repeated
    private boolean isMenuOpen = false;

    private Button changePlayerButton;
    private Button pauseButton;
    private Button resetButton;
    private Button halfMenuExitButton;
    private Button halfMenuOptionButton;

    ImageView arrow1;
    ImageView arrow2;

    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout halfMenu;

    ConstraintLayout clockZone1;
    ConstraintLayout clockZone2;

    private TextView timeText;
    private boolean timeRun = false;
    private CountDownTimer timer;
    private long timeInMilisec;

    Intent optionIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        optionIntent = getIntent();
        setGameType(optionIntent.getStringExtra("gameType"));

        hideDeafultAppBar();
        CreatePlayers();
        SetButtonListeners();
        WriteTimeToTexts();
    }

    void setGameType(String type)
    {
        if (type == null) {
            gameType = 0;
        }
        else if (type.equals("full")) {
            gameType = 0;
        }
        else if (type.equals("repeat")) {
            gameType = 1;
        }
    }

    void SwapPlayer()
    {
        if(playerTurn == 1)
        {
            Log.d("Şuan burada: ", "Player 2");
            changePlayerButton = (Button) findViewById(R.id.Player2ChangeClockButton);
            pauseButton = (Button) findViewById(R.id.Player2PauseButton);
            resetButton = (Button) findViewById(R.id.Player2ResetButton);
            timeText = ((TextView)findViewById(R.id.Player2MaxTimer));
            playerTurn = 2;

            findViewById(R.id.Player2ChangeClockButton).setClickable(true);
            findViewById(R.id.Player2PauseButton).setClickable(true);
            findViewById(R.id.Player2ResetButton).setClickable(true);

            findViewById(R.id.Player1ChangeClockButton).setClickable(false);
            findViewById(R.id.Player1PauseButton).setClickable(false);
            findViewById(R.id.Player1ResetButton).setClickable(false);

            StopTimer();
            if (gameType == 1)
                ResetTime();
            StartTimer();
        }
        else if (playerTurn == 2)
        {
            Log.d("Şuan burada: ", "Player 1");
            changePlayerButton = (Button) findViewById(R.id.Player1ChangeClockButton);
            pauseButton = (Button) findViewById(R.id.Player1PauseButton);
            resetButton = (Button) findViewById(R.id.Player1ResetButton);
            timeText = ((TextView)findViewById(R.id.Player1MaxTimer));
            playerTurn = 1;

            findViewById(R.id.Player1ChangeClockButton).setClickable(true);
            findViewById(R.id.Player1PauseButton).setClickable(true);
            findViewById(R.id.Player1ResetButton).setClickable(true);

            findViewById(R.id.Player2ChangeClockButton).setClickable(false);
            findViewById(R.id.Player2PauseButton).setClickable(false);
            findViewById(R.id.Player2ResetButton).setClickable(false);


            StopTimer();
            if (gameType == 1)
                ResetTime();
            StartTimer();
        }
    }


    void OpenCloseGameMenu(int whichPlayer)
    {
        if (!isMenuOpen) {

            if (whichPlayer == 1) {
                if (halfMenu.getRotation() == 180)
                {
                    halfMenu.setRotation(0);
                }
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.GONE);

                halfMenu.setVisibility(View.VISIBLE);
                isMenuOpen = true;
            }
            else
            {
                if (halfMenu.getRotation() == 0)
                {
                    halfMenu.setRotation(180);
                }
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.GONE);

                halfMenu.setVisibility(View.VISIBLE);
                isMenuOpen = true;
            }
        }
        else
        {
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.VISIBLE);

            halfMenu.setVisibility(View.GONE);
            isMenuOpen = false;
        }
    }

    void SetButtonListeners()
    {
        arrow1 = (ImageView) findViewById(R.id.OpenMenuButton1);
        arrow2 = (ImageView) findViewById(R.id.OpenMenuButton2);

        layout1 = (LinearLayout) findViewById(R.id.Player1Layout);
        layout2 = (LinearLayout) findViewById(R.id.Player2Layout);
        halfMenu = (LinearLayout) findViewById(R.id.halfMenuLayout);

        clockZone1 = (ConstraintLayout) findViewById(R.id.ClockZonePlayer1);
        clockZone2 = (ConstraintLayout) findViewById(R.id.ClockZonePlayer2);

        halfMenuExitButton = (Button)  findViewById(R.id.halfMenuExitButtonn);
        halfMenuOptionButton = (Button)  findViewById(R.id.halfMenuOptionButton);

        halfMenuExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainMenuIntent = new Intent(ClockActivity.this, MainMenuActivity.class);
                startActivity(mainMenuIntent);
            }
        });

        halfMenuOptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent optionIntent = new Intent(ClockActivity.this, OptionActivity.class);
                startActivity(optionIntent);
            }
        });

        arrow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCloseGameMenu(1);
            }
        });

        arrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCloseGameMenu(2);
            }
        });

        findViewById(R.id.Player2ChangeClockButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn = 2;
                SwapPlayer();
            }
        });

        findViewById(R.id.Player1ChangeClockButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn = 1;
                SwapPlayer();
            }
        });

        findViewById(R.id.Player2PauseButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PauseTimer();
            }
        });

        findViewById(R.id.Player1PauseButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PauseTimer();
            }
        });

        findViewById(R.id.Player2ResetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetTime();
            }
        });

        findViewById(R.id.Player1ResetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetTime();
            }
        });

    }

    void CreatePlayers()
    {
        player1 = new Player();
        player2 = new Player();

        player1.setMainHour(optionIntent.getIntExtra("hour",0));
        player1.setMainMinute(optionIntent.getIntExtra("minute",10));
        player1.setMainSecond(optionIntent.getIntExtra("second",0));
        player1.setMainHourTemp(optionIntent.getIntExtra("hour",0));
        player1.setMainMinuteTemp(optionIntent.getIntExtra("minute",10));
        player1.setMainSecondTemp(optionIntent.getIntExtra("second",0));
        player1.setNickname(optionIntent.getStringExtra("player1name"));

        player2.setMainHour(optionIntent.getIntExtra("hour",0));
        player2.setMainMinute(optionIntent.getIntExtra("minute",10));
        player2.setMainSecond(optionIntent.getIntExtra("second",0));
        player2.setMainHourTemp(optionIntent.getIntExtra("hour",0));
        player2.setMainMinuteTemp(optionIntent.getIntExtra("minute",10));
        player2.setMainSecondTemp(optionIntent.getIntExtra("second",0));
        player2.setNickname(optionIntent.getStringExtra("player2name"));
    }


    void WriteTimeToTexts()
    {
        TextView player1name = ((TextView)findViewById(R.id.player1Name));
        TextView player2name = ((TextView)findViewById(R.id.player2Name));

        player1name.setText(player1.getNickname());
        player2name.setText(player2.getNickname());

        TextView timeText2 = ((TextView)findViewById(R.id.Player2MaxTimer));
        String timeLeftText = player2.getMainHour() + ":" + player2.getMainMinute() + ":" + player2.getMainSecond();
        if (player2.getMainMinute() < 10) {
            if (player2.getMainSecond() < 10)
                timeLeftText = player2.getMainHour() + ":0" + player2.getMainMinute() + ":0" + player2.getMainSecond();
            else
                timeLeftText = player2.getMainHour() + ":0" + player2.getMainMinute() + ":" + player2.getMainSecond();
        } else
            timeLeftText = player2.getMainHour() + ":" + player2.getMainMinute() + ":" + player2.getMainSecond();
        timeText2.setText(timeLeftText);

        TextView timeText3 = ((TextView)findViewById(R.id.Player1MaxTimer));
        String timeLeftText2 = player1.getMainHour() + ":" + player1.getMainMinute() + ":" + player1.getMainSecond();
        if (player1.getMainMinute() < 10) {
            if (player1.getMainSecond() < 10)
                timeLeftText2 = player1.getMainHour() + ":0" + player1.getMainMinute() + ":0" + player1.getMainSecond();
            else
                timeLeftText2 = player1.getMainHour() + ":0" + player1.getMainMinute() + ":" + player1.getMainSecond();
        } else
            timeLeftText2 = player1.getMainHour() + ":" + player1.getMainMinute() + ":" + player1.getMainSecond();
        timeText3.setText(timeLeftText2);
    }

    void hideDeafultAppBar()
    {
        getSupportActionBar().hide();
    }

    void StartTimer()
    {
        if (playerTurn == 1) {

            timeInMilisec = TimeUnit.HOURS.toMillis(player1.getMainHour()) + TimeUnit.MINUTES.toMillis(player1.getMainMinute()) + TimeUnit.SECONDS.toMillis(player1.getMainSecond());

            timer = new CountDownTimer(timeInMilisec, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer(millisUntilFinished);
                }

                @Override
                public void onFinish() {

                }
            }.start();
        }
        else if (playerTurn == 2)
        {
            timeInMilisec = TimeUnit.HOURS.toMillis(player2.getMainHour()) + TimeUnit.MINUTES.toMillis(player2.getMainMinute()) + TimeUnit.SECONDS.toMillis(player2.getMainSecond());

            timer = new CountDownTimer(timeInMilisec, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer(millisUntilFinished);
                }

                @Override
                public void onFinish() {

                }
            }.start();
        }
        timeRun = true;
    }

    void StopTimer()
    {
        if (timer != null)
        {
            timer.cancel();
            timeRun = false;
        }
    }

    void PauseTimer()
    {
        if (timeRun) {
            timer.cancel();
            timeRun = false;
        }
        else {
            StartTimer();
            timeRun = true;
        }
    }

    void ResetTime()
    {
        StopTimer();
        player1.setMainHour(player1.getMainHourTemp());
        player1.setMainMinute(player1.getMainMinuteTemp());
        player1.setMainSecond(player1.getMainSecondTemp());
        player2.setMainHour(player2.getMainHourTemp());
        player2.setMainMinute(player2.getMainMinuteTemp());
        player2.setMainSecond(player2.getMainSecondTemp());
        WriteTimeToTexts();
    }

    void updateTimer(long millisUntilFinished)
    {
        String timeLeftText = player1.getMainHour() + ":0" + player1.getMainMinute() + ":" + player1.getMainSecond();

        if (playerTurn == 1) {
            player1.setMainHour((int) (millisUntilFinished / 3600000));
            player1.setMainMinute((int) millisUntilFinished % 3600000 / 60000);
            player1.setMainSecond((int) millisUntilFinished % 60000 / 1000);
            WriteTimeToTexts();
        }
        else if (playerTurn == 2)
        {
            player2.setMainHour((int) (millisUntilFinished / 3600000));
            player2.setMainMinute((int) millisUntilFinished % 3600000 / 60000);
            player2.setMainSecond((int) millisUntilFinished % 60000 / 1000);
            WriteTimeToTexts();
        }
    }
}