package com.uit.thaithang.dempflashlight;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ToggleButton;

import java.util.Timer;
import java.util.TimerTask;

public class WaringActivity extends Activity {

    ToggleButton tgWarning;
    Handler handler;
    Timer timer;
    boolean checkWarning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_waring);

        tgWarning = (ToggleButton) findViewById(R.id.tgWarning);

        handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(!checkWarning)
                {
                    checkWarning = true;
                }
                else
                {
                    checkWarning = false;
                }
                tgWarning.setChecked(checkWarning);
            }
        };

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        },1000,1000);
    }

}
