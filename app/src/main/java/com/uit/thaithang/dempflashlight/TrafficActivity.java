package com.uit.thaithang.dempflashlight;

import android.app.Activity;
import android.graphics.drawable.LevelListDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class TrafficActivity extends Activity {
    ImageView imgTraffic;
    Handler handler;
    Timer timer;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_traffic);

        mediaPlayer = MediaPlayer.create(TrafficActivity.this,R.raw.light_switch_sounds_3);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        imgTraffic = (ImageView )findViewById(R.id.imgTraffic);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                LevelListDrawable levelListDrawable = (LevelListDrawable) imgTraffic.getDrawable();

                int currentLevel = levelListDrawable.getLevel();
                if(currentLevel < 2) currentLevel++;
                else currentLevel = 0;
                levelListDrawable.setLevel(currentLevel);
            }
        };

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        },500,500);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }
}
