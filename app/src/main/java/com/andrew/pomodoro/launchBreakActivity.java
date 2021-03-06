package com.andrew.pomodoro;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.View;

import static android.view.View.*;

public class launchBreakActivity extends AppCompatActivity {
    private static final long STANDARD_BREAK_LENGTH = (long) 3e5;
    private static final long TEST_POMODORO_LENGTH = 3000;
    private Context mContext;
    private TextView breakTimerText;
    private Button endBreakButton;
    private CountDownTimer countdownTimer;
    private long millisecondsLeft = TEST_POMODORO_LENGTH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_break);
        mContext = getApplicationContext();
        breakTimerText = findViewById(R.id.break_timer_text);
        endBreakButton = findViewById(R.id.finish_break_button);
        endBreakButton.setVisibility(VISIBLE);
        endBreakButton.setText(getString(R.string.end_break_early));
        endBreakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        countdownTimer = new CountDownTimer(millisecondsLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                millisecondsLeft = millisUntilFinished;
                updateTimer();
            }
            @Override
            public void onFinish() {
                millisecondsLeft = 0;
                updateTimer();
                endBreakButton.setText(R.string.end_break);
            }
        }.start();
    }

    private void updateTimer() {
        int timerDisplayMinutes = (int) millisecondsLeft / 60000;
        int timerDisplaySeconds = (int) millisecondsLeft % 60000 / 1000;
        String timeLeftText = String.format("%02d", timerDisplayMinutes) + ":" + String.format("%02d", timerDisplaySeconds);
        breakTimerText.setText(timeLeftText);
    }
}
