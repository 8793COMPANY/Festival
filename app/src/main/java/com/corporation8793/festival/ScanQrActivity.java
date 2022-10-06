package com.corporation8793.festival;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanQrActivity extends AppCompatActivity {

    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

        //Intent intent = getIntent();
        //String point = intent.getStringExtra("포인트");
        //Toast.makeText(getApplicationContext(), intent.getStringExtra("포인트"), Toast.LENGTH_SHORT).show();

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
                Toast.makeText(this, "스캔완료: " + result.getContents(), Toast.LENGTH_LONG).show();
                Intent intent = getIntent();
                Toast.makeText(getApplicationContext(), intent.getStringExtra("포인트"), Toast.LENGTH_SHORT).show();

                //onBackPressed();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                BoothReservationFragment boothReservationFragment = new BoothReservationFragment();
                // myPageFragment = new MyPageFragment();
                transaction.replace(R.id.containers, boothReservationFragment).commit();
                //transaction.replace(R.id.containers, myPageFragment).commit();

                //Bundle bundle1 = new Bundle();
                //bundle1.putString("예약축제이름", bundle.getString("이름"));
                //bundle1.putInt("사용자구별", bundle.getInt("예약구별"));

                //reservationFragment.setArguments(bundle1);
                //transaction.replace(R.id.containers, boothReservationFragment);
                //transaction.addToBackStack(null);
                //.commit();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}