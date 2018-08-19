package com.uit.thaithang.dempflashlight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MenuActivity extends Activity implements View.OnClickListener {

    ImageButton btnLight, btnNeon, btnWarning, btnTraffic, btnFlashLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu);

        btnLight = (ImageButton) findViewById(R.id.btnLight);
        btnLight.setOnClickListener(this);

        btnNeon = (ImageButton) findViewById(R.id.btnNeon);
        btnNeon.setOnClickListener(this);

        btnWarning = (ImageButton) findViewById(R.id.btnWarning);
        btnWarning.setOnClickListener(this);

        btnTraffic = (ImageButton) findViewById(R.id.btnTraffic);
        btnTraffic.setOnClickListener(this);

        btnFlashLight = (ImageButton) findViewById(R.id.btnFlashLight);
        btnFlashLight.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent iOpenLightActivity;
        switch (view.getId()){
            case R.id.btnLight :
                iOpenLightActivity = new Intent(MenuActivity.this ,LightActivity.class);
                break;
            case R.id.btnNeon :
                iOpenLightActivity = new Intent(MenuActivity.this ,NeonActivity.class);
                break;
            case R.id.btnWarning :
                iOpenLightActivity = new Intent(MenuActivity.this ,WaringActivity.class);
                break;
            case R.id.btnTraffic :
                iOpenLightActivity = new Intent(MenuActivity.this ,TrafficActivity.class);
                break;
            case R.id.btnFlashLight :
                iOpenLightActivity = new Intent(MenuActivity.this ,FlashLightActivity.class);
                break;
            default:
                iOpenLightActivity = new Intent();
                break;
        }

        startActivity(iOpenLightActivity);
    }
}
