package com.corporation8793.festival;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView joinText;
    String id, pw;
    EditText editText1, editText2;
    TextView textView1;
    Button move_MainActivity;
    ImageView pw_eye, arrow_right;

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

        // 회원가입 색상 변경 및 클릭이벤트 넣기
        String textData = joinText.getText().toString();
        SpannableStringBuilder builder = new SpannableStringBuilder(textData);

        String word = "회원가입";
        int start = textData.indexOf(word);
        int end = start + word.length();

        builder.setSpan(new ForegroundColorSpan(Color.parseColor("#ad9cfd")), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        joinText.setText(builder);

        /*
        Spannable spannable = Spannable.Factory.getInstance().newSpannable(textData);
        spannable.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {

            }
        }, start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        */

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

        // 로그인시 MainActivity 이동
        id = "wisi8793";
        pw = "1234";

        move_MainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_id = editText1.getText().toString();
                String user_pw = editText2.getText().toString();

                if(user_id.equals("") || user_pw.equals("")) {
                    textView1.setText("아이디와 비밀번호를 입력하세요.");
                } else if(!user_id.equals(id) || !user_pw.equals(pw)) {
                    textView1.setText("아이디와 비밀번호가 일치하지 않습니다.");
                } else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        // 회원가입 페이지로 이동

    }
}