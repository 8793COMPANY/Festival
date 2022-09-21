package com.corporation8793.festival;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity {

    ImageView arrow_left;
    Button joinButton;
    RadioButton button[] = new RadioButton[7];
    int [] ridButton = {R.id.radioButton1, R.id.radioButton2, R.id.radioButton3, R.id.radioButton4,
            R.id.radioButton5, R.id.radioButton6, R.id.radioButton7};
    int i, j, uId;
    // rectangle9 비밀번호 질문
    Spinner rectangle9;
    ArrayAdapter<CharSequence> rectangle9_adapter;
    EditText rectangle5, rectangle6, rectangle7, rectangle8, rectangle10, rectangle11, rectangle12;
    String sArea;
    /**
     * rectangle5 이름
     * rectangle6 아이디
     * rectangle7 비밀번호
     * rectangle8 비밀번호 확인
     * rectangle10 비밀번호 질문 답
     * rectangle11 이메일
     * rectangle12 연락처
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        rectangle5 = findViewById(R.id.rectangle5);
        rectangle6 = findViewById(R.id.rectangle6);
        rectangle7 = findViewById(R.id.rectangle7);
        rectangle8 = findViewById(R.id.rectangle8);
        rectangle9 = findViewById(R.id.rectangle9);
        rectangle10 = findViewById(R.id.rectangle10);
        rectangle11 = findViewById(R.id.rectangle11);
        rectangle12 = findViewById(R.id.rectangle12);

        arrow_left = findViewById(R.id.arrow_left);
        arrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        for(i=0; i<7; i++) {
            button[i] = findViewById(ridButton[i]);
        }

        for(i=0; i<button.length; i++) {
            final int INDEX;
            INDEX = i;
            button[INDEX].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(j=0; j< button.length; j++) {
                        if(INDEX == j) {
                            button[INDEX].setChecked(true);
                            sArea = button[INDEX].getText().toString();
                        } else {
                            button[j].setChecked(false);
                        }
                    }
                    //Toast.makeText(JoinActivity.this, "눌림", Toast.LENGTH_SHORT).show();
                }
            });
        }

        // 가입시 필요한 내용이 없으면 알려주기, 다 적으면 페이지 이동
        joinButton = findViewById(R.id.joinButton);
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sName = rectangle5.getText().toString();
                String sId = rectangle6.getText().toString();
                String sPw = rectangle7.getText().toString();
                String sPwCheck = rectangle8.getText().toString();
                String sPwQuestion = rectangle9.getSelectedItem().toString();
                String sPwAnswer = rectangle10.getText().toString();
                String sEmail = rectangle11.getText().toString();
                String sPhoneNumber = rectangle12.getText().toString();

                if(!sPw.equals(sPwCheck)) {
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                } else if(sName.equals("")||sId.equals("")||sPw.equals("")||sPwCheck.equals("")||sPwAnswer.equals("")||sEmail.equals("")||sPhoneNumber.equals("")) {
                    Toast.makeText(getApplicationContext(), "회원정보가 부족합니다 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    //사용자 등록
                    insertUser(sName, sId, sPw, sPwQuestion, sPwAnswer, sEmail, sPhoneNumber, sArea);

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    //Intent intent = new Intent(getApplicationContext(), UserInformationActivity.class);
                    startActivity(intent);
                }
            }
        });

        rectangle9_adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.question_text, R.layout.spinner_item2);
        rectangle9_adapter.setDropDownViewResource(R.layout.spinner_dropdown_item2);
        rectangle9.setAdapter(rectangle9_adapter);

    }

    private void insertUser(String name, String id, String pw, String pwQuestion, String pwAnswer, String email, String phoneNumber, String area) {

        User user = new User();
        user.userName = name;
        user.userId = id;
        user.userPw = pw;
        user.userPwQuestion = pwQuestion;
        user.userPwAnswer = pwAnswer;
        user.userEmail = email;
        user.userPhoneNumber = phoneNumber;
        user.userArea = area;

        AppDatabase db = AppDatabase.getDBInstance(this.getApplicationContext());
        db.userDao().insertUser(user);

        setResult(Activity.RESULT_OK);

        finish();

    }

}