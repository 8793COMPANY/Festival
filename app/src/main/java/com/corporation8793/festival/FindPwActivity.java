package com.corporation8793.festival;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class FindPwActivity extends AppCompatActivity {

    Spinner choiceQuestion;
    ArrayAdapter<CharSequence> choiceQuestion_adapter;
    Button nextButton;

    TextView maintextView, findIdText, findId;
    Button loginButton2;
    ImageView arrow_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pw);

        choiceQuestion = findViewById(R.id.choiceQuestion);
        nextButton = findViewById(R.id.nextButton);

        maintextView = findViewById(R.id.maintextView);
        findIdText = findViewById(R.id.findIdText);
        loginButton2 = findViewById(R.id.loginButton2);
        findId = findViewById(R.id.findId);
        arrow_left = findViewById(R.id.arrow_left);

        choiceQuestion_adapter = ArrayAdapter.createFromResource(this, R.array.question_text, android.R.layout.simple_spinner_item);
        choiceQuestion_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choiceQuestion.setAdapter(choiceQuestion_adapter);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindResultActivity.class);
                intent.putExtra("비밀번호제목", "비밀번호 찾기");
                intent.putExtra("비밀번호상단", "비밀번호를 찾았습니다.");
                intent.putExtra("비밀번호버튼", "로그인");
                intent.putExtra("비밀번호", "1234");
                startActivity(intent);
            }
        });

        arrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindActivity.class);
                startActivity(intent);
            }
        });
    }
}