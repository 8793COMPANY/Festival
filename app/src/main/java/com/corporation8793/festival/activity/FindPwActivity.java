package com.corporation8793.festival.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.corporation8793.festival.room.AppDatabase;
import com.corporation8793.festival.R;
import com.corporation8793.festival.room.User;

import java.util.ArrayList;
import java.util.List;

public class FindPwActivity extends AppCompatActivity {

    Spinner choiceQuestion;
    ArrayAdapter<CharSequence> choiceQuestion_adapter;
    Button nextButton;
    ImageView arrow_left;
    EditText writeAnswer;

    List<User> userList = new ArrayList<>();
    String id, answer, spinner, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pw);

        choiceQuestion = findViewById(R.id.choiceQuestion);
        nextButton = findViewById(R.id.nextButton);
        arrow_left = findViewById(R.id.arrow_left);
        writeAnswer = findViewById(R.id.writeAnswer);

        choiceQuestion_adapter = ArrayAdapter.createFromResource(this, R.array.question_text, R.layout.item_spinner_reservation);
        choiceQuestion_adapter.setDropDownViewResource(R.layout.item_spinner_dropdown_reservation);
        choiceQuestion.setAdapter(choiceQuestion_adapter);

        Intent findIntent = getIntent();
        id = findIntent.getStringExtra("비밀번호찾기아이디");

        AppDatabase db  = AppDatabase.getDBInstance(this);
        userList = db.userDao().getAllUser();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_spinner = choiceQuestion.getSelectedItem().toString();
                String user_answer = writeAnswer.getText().toString();

                if(user_answer.equals("")) {
                    Toast.makeText(getApplicationContext(), "답변을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else {
                    for (int i=0; i < userList.size(); i++) {
                        if(userList.get(i).userId.equals(id)) {
                            spinner = userList.get(i).userPwQuestion;
                            answer = userList.get(i).userPwAnswer;
                            pw = userList.get(i).userPw;
                        }
                    }

                    if(!user_spinner.equals(spinner) || !user_answer.equals(answer)) {
                        Toast.makeText(getApplicationContext(), "일치하는 데이터가 없습니다.", Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intent = new Intent(getApplicationContext(), FindResultActivity.class);
                        intent.putExtra("비밀번호제목", "비밀번호 찾기");
                        intent.putExtra("비밀번호상단", "비밀번호를 찾았습니다.");
                        intent.putExtra("비밀번호버튼", "로그인");
                        intent.putExtra("비밀번호", pw);
                        intent.putExtra("아이디", id);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

        arrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), FindActivity.class);
        startActivity(intent);
        finish();
    }
}