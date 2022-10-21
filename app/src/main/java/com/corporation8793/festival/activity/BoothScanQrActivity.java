package com.corporation8793.festival.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.corporation8793.festival.fragment.BoothReservationFragment;
import com.corporation8793.festival.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BoothScanQrActivity extends AppCompatActivity {

    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booth_scan_qr);

        qrScan = new IntentIntegrator(this);
        qrScan.setOrientationLocked(true);
        qrScan.setPrompt("QR 코드를 사각형 안에 비춰주세요.");
        qrScan.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "스캔취소", Toast.LENGTH_SHORT).show();
                onBackPressed();
            } else {
                Intent intent = getIntent();

                BoothReservationFragment boothReservationFragment = new BoothReservationFragment();
                boothReservationFragment.getPoint(result.getContents(), intent.getIntExtra("위치", 0), "적립완료");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                },500);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}