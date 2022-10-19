package com.corporation8793.festival.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.corporation8793.festival.room.AppDatabase;
import com.corporation8793.festival.R;
import com.corporation8793.festival.room.User;

public class UpdateActivity extends AppCompatActivity {

    TextView nameText, idText, pwQuestionText, pwAnswerText, emailText, phoneNumText, areaText;
    EditText updatePw;
    Button updateButton, deleteButton;
    int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nameText = findViewById(R.id.nameText);
        idText = findViewById(R.id.idText);
        updatePw = findViewById(R.id.updatePw);
        pwQuestionText = findViewById(R.id.pwQuestionText);
        pwAnswerText = findViewById(R.id.pwAnswerText);
        emailText = findViewById(R.id.emailText);
        phoneNumText = findViewById(R.id.phoneNumText);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);
        areaText = findViewById(R.id.areaText);

        String name = getIntent().getStringExtra("userName");
        String id = getIntent().getStringExtra("userId");
        String pw = getIntent().getStringExtra("userPw");
        String pwQuestion = getIntent().getStringExtra("userPwQuestion");
        String pwAnswer = getIntent().getStringExtra("userPwAnswer");
        String email = getIntent().getStringExtra("userEmail");
        String phoneNumber = getIntent().getStringExtra("userPhoneNumber");
        String area = getIntent().getStringExtra("userArea");
        uid = getIntent().getIntExtra("uid", 0);

        nameText.setText(name);
        idText.setText(id);
        updatePw.setText(pw);
        pwQuestionText.setText(pwQuestion);
        pwAnswerText.setText(pwAnswer);
        emailText.setText(email);
        phoneNumText.setText(phoneNumber);
        areaText.setText(area);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = nameText.getText().toString();
                String userId = idText.getText().toString();
                String userPw = updatePw.getText().toString();
                String userPwQuestion = pwQuestionText.getText().toString();
                String userPwAnswer = pwAnswerText.getText().toString();
                String userEmail = emailText.getText().toString();
                String userPhoneNumber = phoneNumText.getText().toString();
                String userArea = areaText.getText().toString();

                User user = new User();
                user.uid = uid;
                user.userName = userName;
                user.userId = userId;
                user.userPwQuestion = userPwQuestion;
                user.userPwAnswer = userPwAnswer;
                user.userPw = userPw;
                user.userEmail = userEmail;
                user.userPhoneNumber = userPhoneNumber;
                user.userArea = userArea;

                AppDatabase db = AppDatabase.getDBInstance(UpdateActivity.this);

                db.userDao().updateUser(user);

                Intent intent  = new Intent(UpdateActivity.this, LoginActivity.class);
                startActivity(intent);

                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.uid = uid;

                AppDatabase db = AppDatabase.getDBInstance(UpdateActivity.this);

                db.userDao().deleteUser(user);

                Intent intent  = new Intent(UpdateActivity.this, LoginActivity.class);
                startActivity(intent);

                finish();
            }
        });
    }
}