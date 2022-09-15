package com.corporation8793.festival;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class PwCheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw_check);

        findViewById(R.id.next_btn).setOnClickListener(v->{
            finish();
            Intent intent= new Intent(PwCheckActivity.this, JoinActivity.class);
            intent.putExtra("modify","회원정보 수정");
            startActivity(intent);
        });
    }
}