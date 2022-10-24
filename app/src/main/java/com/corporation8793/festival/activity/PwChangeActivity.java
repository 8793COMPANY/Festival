package com.corporation8793.festival.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.corporation8793.festival.room.AppDatabase;
import com.corporation8793.festival.R;
import com.corporation8793.festival.room.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PwChangeActivity extends AppCompatActivity {

    ImageView arrow_left;
    EditText newPwInput, newPwCheck;
    TextView errorText;
    Button changeOkButton;
    Animation animation;

    List<User> userList = new ArrayList<>();
    String userName, userId, userPw, userPwQuestion, userPwAnswer, userEmail, userPhoneNumber, userArea, getId;
    int uid, pwQuestionIndex, userAreaIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw_change);

        arrow_left = findViewById(R.id.arrow_left);
        newPwInput = findViewById(R.id.newPwInput);
        newPwCheck = findViewById(R.id.newPwCheck);
        errorText = findViewById(R.id.errorText);
        changeOkButton = findViewById(R.id.changeOkButton);
        animation = AnimationUtils.loadAnimation(this, R.anim.blink_animation);

        Intent intent = getIntent();
        getId = intent.getStringExtra("찾기아이디");
        if(getId == null) {
            Log.e("가져온 id", "없음");
        } else {
            Log.e("가져온 id", getId);
        }

        AppDatabase db  = AppDatabase.getDBInstance(this);
        userList = db.userDao().getAllUser();

        changeOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPw = newPwInput.getText().toString();
                String newPwC = newPwCheck.getText().toString();

                if(newPw.equals("") || newPwC.equals("")) {
                    errorText.setText("빈칸을 채워주세요.");
                    errorText.startAnimation(animation);
                } else {
                    if(!newPw.equals(newPwC)) {
                        errorText.setText("비밀번호가 일치하지 않습니다.");
                        errorText.startAnimation(animation);
                    } else {
                        if(!Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{8,15}$", newPw)) {
                            Toast.makeText(getApplicationContext(), "비밀번호 형식이 올바르지 않습니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                        } else {
                            for (int i=0; i < userList.size(); i++) {
                                if(userList.get(i).userId.equals(getId)) {
                                    uid = userList.get(i).uid;
                                    userId = userList.get(i).userId;
                                    userName = userList.get(i).userName;
                                    userPwQuestion = userList.get(i).userPwQuestion;
                                    pwQuestionIndex = userList.get(i).PwQuestionIndex;
                                    userPwAnswer = userList.get(i).userPwAnswer;
                                    userEmail = userList.get(i).userEmail;
                                    userPhoneNumber = userList.get(i).userPhoneNumber;
                                    userArea = userList.get(i).userArea;
                                    userAreaIndex = userList.get(i).userAreaIndex;

                                    //정보 업데이트
                                    User user = new User();
                                    user.uid = uid;
                                    user.userName = userName;
                                    user.userId = userId;
                                    user.userPwQuestion = userPwQuestion;
                                    user.PwQuestionIndex = pwQuestionIndex;
                                    user.userPwAnswer = userPwAnswer;
                                    user.userPw = newPw;
                                    user.userEmail = userEmail;
                                    user.userPhoneNumber = userPhoneNumber;
                                    user.userArea = userArea;
                                    user.userAreaIndex = userAreaIndex;

                                    db.userDao().updateUser(user);

                                    Intent intent1 = getIntent();

                                    if(intent1.hasExtra("페이지이름")) {
                                        Toast.makeText(getApplicationContext(), "수정이 완료되었습니다.\n다시 로그인해주세요.", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        JoinActivity joinActivity = new JoinActivity();
                                        joinActivity.getChangePw(newPw);

                                        finish();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        arrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = getIntent();

                if(intent1.hasExtra("페이지이름")) {
                    Intent intent = new Intent(getApplicationContext(), FindActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    finish();
                }
            }
        });
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), FindActivity.class);
        startActivity(intent);
        finish();
    }
}