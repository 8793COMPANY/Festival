package com.corporation8793.festival;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class FindResultActivity extends AppCompatActivity {

    TextView maintextView, findIdText, findId;
    Button loginButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_result);

        maintextView = findViewById(R.id.maintextView);
        findIdText = findViewById(R.id.findIdText);
        loginButton2 = findViewById(R.id.loginButton2);
        findId = findViewById(R.id.findId);

        Intent intent = getIntent();

        if(intent.hasExtra("아이디제목")) {
            maintextView.setText(intent.getStringExtra("아이디제목"));
            findIdText.setText(intent.getStringExtra("아이디상단"));
            findId.setText(intent.getStringExtra("아이디"));
            loginButton2.setText(intent.getStringExtra("아이디버튼"));
        }else if(intent.hasExtra("비밀번호제목")) {
            maintextView.setText(intent.getStringExtra("비밀번호제목"));
            findIdText.setText(intent.getStringExtra("비밀번호상단"));
            findId.setText(intent.getStringExtra("비밀번호"));
            loginButton2.setText(intent.getStringExtra("비밀번호버튼"));
        }else { }
    }}