package com.corporation8793.festival;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    TextView joinText, textView1, findText;
    EditText editText1, editText2;
    Button move_MainActivity, userPageButton;
    ImageView pw_eye, arrow_right;

    List<User> userList = new ArrayList<>();
    String id, pw;
    Animation animation;

    CheckBox checkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        joinText = findViewById(R.id.joinText);
        pw_eye = findViewById(R.id.pw_eye);
        editText1 = findViewById(R.id.rectangle1);
        editText2 = findViewById(R.id.rectangle2);
        textView1 = findViewById(R.id.errorText);
        move_MainActivity = findViewById(R.id.loginButton);
        arrow_right = findViewById(R.id.arrow_right);
        findText = findViewById(R.id.findText);
        userPageButton = findViewById(R.id.userPageButton);
        checkButton = findViewById(R.id.checkButton);

        animation = AnimationUtils.loadAnimation(this, R.anim.blink_animation);

        //자동로그인 처리
        SharedPreferences auto = getSharedPreferences("autoLogin", Activity.MODE_PRIVATE);
        String userId = auto.getString("userId", null);
        String userPw = auto.getString("userPw", null);

        if(userId != null && userPw != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

        userPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserInformationActivity.class);
                startActivity(intent);
            }
        });

        // 회원가입 글자 색상 변경 및 클릭이벤트(페이지 이동)
        Spannable span = (Spannable) joinText.getText();
        String textData = joinText.getText().toString();

        String word = "회원가입";
        int start = textData.indexOf(word);
        int end = start + word.length();

        span.setSpan(new ClickableSpan() {
            // 클릭이벤트
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
            // 클릭이벤트 밑줄 제거
            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
            }
        }, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        joinText.setMovementMethod(LinkMovementMethod.getInstance());

        // 글자 색상 변경
        span.setSpan(new ForegroundColorSpan(Color.parseColor("#ad9cfd")), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        // 버튼 이미지 클릭시 회원가입 페이지로 이동
        arrow_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });

        // 아이디 비밀번호 찾기 클릭시 페이지로 이동
        findText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindActivity.class);
                startActivity(intent);
            }
        });

        // 비밀번호 보이기/숨기기
        pw_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pw_eye.getTag().equals("0")) {
                    pw_eye.setTag("1");
                    editText2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    pw_eye.setTag("0");
                    editText2.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                editText2.setSelection(editText2.getText().length());
            }
        });

        AppDatabase db  = AppDatabase.getDBInstance(this.getApplicationContext());
        userList = db.userDao().getAllUser();

        // 로그인시 MainActivity 이동
        move_MainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_id = editText1.getText().toString();
                String user_pw = editText2.getText().toString();

                if(user_id.equals("") || user_pw.equals("")) {
                    textView1.setText("아이디와 비밀번호를 입력하세요.");
                    textView1.startAnimation(animation);
                } else {
                    for (int i=0; i < userList.size(); i++) {
                        if(userList.get(i).userId.equals(user_id) && userList.get(i).userPw.equals(user_pw)) {
                            id = userList.get(i).userId;
                            pw = userList.get(i).userPw;
                        }
                    }

                    if(!user_id.equals(id) || !user_pw.equals(pw)) {
                        textView1.setText("아이디와 비밀번호가 일치하지 않습니다.");
                        textView1.startAnimation(animation);
                    } else {
                        if(checkButton.isChecked()) {
                            SharedPreferences auto = getSharedPreferences("autoLogin", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor autoLoginEdit = auto.edit();

                            autoLoginEdit.putString("userId", id);
                            autoLoginEdit.putString("userPw", pw);
                            //마이페이지 스위치버튼 체크
                            autoLoginEdit.putString("check", "on");
                            autoLoginEdit.commit();
                        }

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("로그인페이지아이디", id);
                        intent.putExtra("로그인페이지비밀번호", pw);
                        startActivity(intent);
                    }
                }
                /*
                if(user_id.equals("") || user_pw.equals("")) {
                    textView1.setText("아이디와 비밀번호를 입력하세요.");
                    textView1.startAnimation(animation);
                } else if(!user_id.equals(id) || !user_pw.equals(pw)) {
                    textView1.setText("아이디와 비밀번호가 일치하지 않습니다.");
                    textView1.startAnimation(animation);
                } else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }*/
            }
        });
    }

    /*
    private void loginUser(String e1, String e2) {
        AppDatabase db  = AppDatabase.getDBInstance(this.getApplicationContext());
        userList = db.userDao().getAllUser();
        String id, pw;

        if(e1.equals("") || e2.equals("")) {
            textView1.setText("아이디와 비밀번호를 입력하세요.");
        } else {
            for (int i=0; i < userList.size(); i++) {
                if(userList.get(i).userId.equals(e1) && userList.get(i).userPw.equals(e2)) {
                    id = userList.get(i).userId;
                    pw = userList.get(i).userPw;
                }
            }
        }*/

        /*
        if(e1.equals("") || e2.equals("")) {
            textView1.setText("아이디와 비밀번호를 입력하세요.");
        } else {
            for (int i=0; i < userList.size(); i++) {
                if(!userList.get(i).userId.equals(e1) || !userList.get(i).userPw.equals(e2)) {
                    textView1.setText("아이디와 비밀번호가 일치하지 않습니다.");
                } else {
                    //Toast.makeText(getApplicationContext(), String.valueOf(userList.get(i).uid), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        }
         */
}