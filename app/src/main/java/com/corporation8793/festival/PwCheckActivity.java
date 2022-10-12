package com.corporation8793.festival;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class PwCheckActivity extends AppCompatActivity {

    EditText pw_check_input_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw_check);

        pw_check_input_box = findViewById(R.id.pw_check_input_box);

        Intent intent2 = getIntent();

        String name = intent2.getStringExtra("수정페이지이름2");
        String id = intent2.getStringExtra("수정페이지아이디2");
        String pw = intent2.getStringExtra("수정페이지비밀번호2");
        String pwQuestion = intent2.getStringExtra("수정페이지질문2");
        int pwQuestionIndex = intent2.getIntExtra("수정페이지질문인덱스2", 0);
        String pwAnswer = intent2.getStringExtra("수정페이지답변2");
        String email = intent2.getStringExtra("수정페이지이메일2");
        String phoneNumber = intent2.getStringExtra("수정페이지연락처2");
        int area = intent2.getIntExtra("수정페이지지역인덱스2", 0);
        int uid = intent2.getIntExtra("수정페이지사용자구분2", 0);

        findViewById(R.id.next_btn).setOnClickListener(v->{
            //finish();
            if(pw_check_input_box.getText().toString().equals("")) {
                Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            }else if(!pw_check_input_box.getText().toString().equals(pw)) {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            }else {
                Intent intent= new Intent(PwCheckActivity.this, JoinActivity.class);
                intent.putExtra("modify","회원정보 수정");
                intent.putExtra("수정페이지제목", "회원정보 수정");
                intent.putExtra("수정페이지사용자구분", uid);
                intent.putExtra("수정페이지이름", name);
                intent.putExtra("수정페이지아이디", id);
                intent.putExtra("수정페이지비밀번호", pw);
                intent.putExtra("수정페이지질문", pwQuestion);
                intent.putExtra("수정페이지질문인덱스", pwQuestionIndex);
                intent.putExtra("수정페이지답변", pwAnswer);
                intent.putExtra("수정페이지이메일", email);
                intent.putExtra("수정페이지연락처", phoneNumber);
                intent.putExtra("수정페이지지역인덱스", area);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.arrow_left).setOnClickListener(v->{
            onBackPressed();
        });
    }
}