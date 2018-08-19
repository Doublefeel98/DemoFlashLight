package com.uit.thaithang.dempflashlight;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class FlashLightActivity extends Activity implements CompoundButton.OnCheckedChangeListener {

    ToggleButton tgFlashCamera;

    private static final int CAMERA_REQUEST = 50;
    boolean hasCameraFlash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_flashlight);

        tgFlashCamera = (ToggleButton) findViewById(R.id.tgFlashCamera);
        tgFlashCamera.setOnCheckedChangeListener(this);

        hasCameraFlash = getPackageManager().
                hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        boolean isEnabled = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (hasCameraFlash) {
            if (!isChecked)
                turnOffFlash();
            else
                turnOnFlash();
        } else {
            Toast.makeText(FlashLightActivity.this, "No flash available on your device",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void turnOffFlash() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, false);
            }
        }
        catch (CameraAccessException e) {
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void turnOnFlash() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, true);
            }
        }
        catch (CameraAccessException e) {
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case CAMERA_REQUEST :
                if (grantResults.length > 0  &&  grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(FlashLightActivity.this, "Permission Denied for the Camera", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

