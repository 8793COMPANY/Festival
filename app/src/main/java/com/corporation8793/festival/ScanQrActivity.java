package com.corporation8793.festival;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanQrActivity extends AppCompatActivity {

    private IntentIntegrator qrScan;

    private static final String TAG = "ScanQrActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

        //Intent intent = getIntent();
        //String point = intent.getStringExtra("포인트");
        //Toast.makeText(getApplicationContext(), intent.getStringExtra("포인트") + "받음", Toast.LENGTH_SHORT).show();

        //new IntentIntegrator(this).initiateScan();
        qrScan = new IntentIntegrator(this);
        qrScan.setOrientationLocked(true);
        qrScan.setPrompt("QR코드를 사각형 안에 비춰주세요.");
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
                //Toast.makeText(this, "스캔완료: " + result.getContents(), Toast.LENGTH_LONG).show();
                Intent intent = getIntent();
                //Toast.makeText(getApplicationContext(), intent.getStringExtra("포인트"), Toast.LENGTH_SHORT).show();
                BoothReservationFragment boothReservationFragment = new BoothReservationFragment();
                boothReservationFragment.getPoint(result.getContents(), intent.getIntExtra("위치", 0));
                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}