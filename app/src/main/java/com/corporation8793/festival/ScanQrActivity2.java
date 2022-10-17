package com.corporation8793.festival;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanQrActivity2 extends AppCompatActivity {

    private IntentIntegrator qrScan;

    TextView notificationText;
    Button applyButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr2);

        notificationText = findViewById(R.id.notificationText);
        applyButton = findViewById(R.id.applyButton);
        cancelButton = findViewById(R.id.cancelButton);

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
                //Toast.makeText(this, "스캔완료: " + result.getContents() + "P가 차감됩니다.", Toast.LENGTH_LONG).show();
                //포인트 차감 가능하면 포인트 계산하여 갱신, 포인트가 부족하면 문구만 출력해주기
                Intent intent = getIntent();
                String aPoint, dPoint;//, pointResult;
                aPoint = intent.getStringExtra("적립포인트");
                dPoint = intent.getStringExtra("포인트");

                int aIntPoint, dIntPoint;
                aIntPoint = Integer.parseInt(aPoint);
                dIntPoint = Integer.parseInt(dPoint);

                if(aIntPoint < dIntPoint) {
                    Toast.makeText(this, "포인트가 부족합니다.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    notificationText.setText(result.getContents() + "포인트를 사용하시겠습니까?");

                    applyButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String pointResult = String.valueOf((aIntPoint - dIntPoint));
                            ProductFragment productFragment = new ProductFragment();
                            productFragment.getPoint(result.getContents(), pointResult);

                            finish();
                        }
                    });

                    cancelButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(), "취소되었습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });

                    /*
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    },500);*/
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "취소되었습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }

}