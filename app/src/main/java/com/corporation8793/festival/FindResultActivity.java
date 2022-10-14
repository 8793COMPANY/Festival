package com.corporation8793.festival;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FindResultActivity extends AppCompatActivity {

    TextView maintextView, findIdText, findId;
    Button loginButton2, pwChangeButton;
    ImageView arrow_left;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_result);

        maintextView = findViewById(R.id.maintextView);
        findIdText = findViewById(R.id.findIdText);
        loginButton2 = findViewById(R.id.loginButton2);
        findId = findViewById(R.id.findId);
        arrow_left = findViewById(R.id.arrow_left);
        pwChangeButton = findViewById(R.id.pwChangeButton);

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
        }

        Log.e("가져온 id", intent.getStringExtra("아이디"));
        id = intent.getStringExtra("아이디");

        arrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intent.hasExtra("아이디제목")) {
                    Intent intent = new Intent(getApplicationContext(), FindActivity.class);
                    startActivity(intent);
                    finish();
                }else if(intent.hasExtra("비밀번호제목")) {
                    Intent intent = new Intent(getApplicationContext(), FindPwActivity.class);
                    startActivity(intent);
                    finish();
                }else { }
            }
        });

        pwChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PwChangeActivity.class);
                intent.putExtra("찾기아이디", id);
                Log.e("가져온 id", id);
                startActivity(intent);
                finish();
            }
        });

        loginButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}