package com.corporation8793.festival;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class FindResultActivity extends AppCompatActivity {

    TextView findIdText, findId;
    Button loginButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_result);

        findIdText = findViewById(R.id.findText);
        loginButton2 = findViewById(R.id.loginButton2);
        findId = findViewById(R.id.findId);

        //Intent intent = getIntent();
        //findIdText.setText(intent.getStringExtra("상단타이틀"));
        //loginButton2.setText(intent.getStringExtra("버튼타이틀"));
        //findId.setText(intent.getStringExtra("아이디"));

    }}