package com.corporation8793.festival.activity;

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
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.corporation8793.festival.room.AppDatabase;
import com.corporation8793.festival.R;
import com.corporation8793.festival.room.User;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    TextView joinText, textView1, findText;
    EditText editText1, editText2;
    Button move_MainActivity, userPageButton;
    ImageView pw_eye, arrow_right;

    List<User> userList = new ArrayList<>();
    String id, pw, name, pwQ, pwA, email, phone, area;
    int uid, pwQIndex, areaIndex;
    Animation animation;

    CheckBox checkButton;

    ProgressBar progressBar;

    long finishTime = 2000;
    long pressTime = 0;

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
        progressBar = findViewById(R.id.progressBar);

        animation = AnimationUtils.loadAnimation(this, R.anim.blink_animation);

        //??????????????? ??????
        SharedPreferences auto = getSharedPreferences("autoLogin", Activity.MODE_PRIVATE);
        String userId = auto.getString("userId", null);
        String userPw = auto.getString("userPw", null);
        int userUid = auto.getInt("userUid", 0);
        String userName = auto.getString("userName", null);
        String userPwQ = auto.getString("userPwQ", null);
        int userPwQIndex = auto.getInt("userPwQIndex", 0);
        String userPwA = auto.getString("userPwA", null);
        String userEmail = auto.getString("userEmail", null);
        String userPhone = auto.getString("userPhone", null);
        String userArea = auto.getString("userArea", null);
        int userAreaIndex = auto.getInt("userAreaIndex", 0);

        if(userId != null && userPw != null) {
            editText1.setText(userId);
            editText2.setText(userPw);
            checkButton.setChecked(true);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        progressBar.setVisibility(View.VISIBLE);
                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBar.setVisibility(View.INVISIBLE);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("???????????????????????????", userId);
                    intent.putExtra("??????????????????????????????", userPw);
                    intent.putExtra("??????????????????????????????", userUid);
                    intent.putExtra("????????????????????????", userName);
                    intent.putExtra("????????????????????????", userPwQ);
                    intent.putExtra("?????????????????????????????????", userPwQIndex);
                    intent.putExtra("????????????????????????", userPwA);
                    intent.putExtra("???????????????????????????", userEmail);
                    intent.putExtra("???????????????????????????", userPhone);
                    intent.putExtra("????????????????????????", userArea);
                    intent.putExtra("?????????????????????????????????", userAreaIndex);
                    startActivity(intent);
                    finish();
                }
            }).start();
        }

        userPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // ???????????? ?????? ?????? ?????? ??? ???????????????(????????? ??????)
        Spannable span = (Spannable) joinText.getText();
        String textData = joinText.getText().toString();

        String word = "????????????";
        int start = textData.indexOf(word);
        int end = start + word.length();

        span.setSpan(new ClickableSpan() {
            // ???????????????
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                intent.putExtra("???????????????????????????", "?????????????????????");
                startActivity(intent);
                finish();
            }
            // ??????????????? ?????? ??????
            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
            }
        }, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        joinText.setMovementMethod(LinkMovementMethod.getInstance());

        // ?????? ?????? ??????
        span.setSpan(new ForegroundColorSpan(Color.parseColor("#ff5b8f")), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        // ?????? ????????? ????????? ???????????? ???????????? ??????
        arrow_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                intent.putExtra("???????????????????????????", "?????????????????????");
                startActivity(intent);
                finish();
            }
        });

        // ????????? ???????????? ?????? ????????? ???????????? ??????
        findText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // ???????????? ?????????/?????????
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

        // ???????????? MainActivity ??????
        move_MainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_id = editText1.getText().toString();
                String user_pw = editText2.getText().toString();

                if(user_id.equals("") || user_pw.equals("")) {
                    textView1.setText("???????????? ??????????????? ???????????????.");
                    textView1.startAnimation(animation);
                } else {
                    for (int i=0; i < userList.size(); i++) {
                        if(userList.get(i).userId.equals(user_id) && userList.get(i).userPw.equals(user_pw)) {
                            id = userList.get(i).userId;
                            pw = userList.get(i).userPw;
                            uid = userList.get(i).uid;
                            name = userList.get(i).userName;
                            pwQ = userList.get(i).userPwQuestion;
                            pwQIndex = userList.get(i).PwQuestionIndex;
                            pwA = userList.get(i).userPwAnswer;
                            email = userList.get(i).userEmail;
                            phone = userList.get(i).userPhoneNumber;
                            area = userList.get(i).userArea;
                            areaIndex = userList.get(i).userAreaIndex;
                        }
                    }

                    if(!user_id.equals(id) || !user_pw.equals(pw)) {
                        textView1.setText("???????????? ??????????????? ???????????? ????????????.");
                        textView1.startAnimation(animation);
                    } else {
                        if(checkButton.isChecked()) {
                            SharedPreferences auto = getSharedPreferences("autoLogin", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor autoLoginEdit = auto.edit();

                            autoLoginEdit.putString("userId", id);
                            autoLoginEdit.putString("userPw", pw);
                            autoLoginEdit.putInt("userUid", uid);
                            autoLoginEdit.putString("userName", name);
                            autoLoginEdit.putString("userPwQ", pwQ);
                            autoLoginEdit.putInt("userPwQIndex", pwQIndex);
                            autoLoginEdit.putString("userPwA", pwA);
                            autoLoginEdit.putString("userEmail", email);
                            autoLoginEdit.putString("userPhone", phone);
                            autoLoginEdit.putString("userArea", area);
                            autoLoginEdit.putInt("userAreaIndex", areaIndex);

                            //??????????????? ??????????????? ??????
                            autoLoginEdit.putString("check", "on");
                            autoLoginEdit.commit();
                        }

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("???????????????????????????", id);
                        intent.putExtra("??????????????????????????????", pw);
                        intent.putExtra("??????????????????????????????", uid);
                        intent.putExtra("????????????????????????", name);
                        intent.putExtra("????????????????????????", pwQ);
                        intent.putExtra("?????????????????????????????????", pwQIndex);
                        intent.putExtra("????????????????????????", pwA);
                        intent.putExtra("???????????????????????????", email);
                        intent.putExtra("???????????????????????????", phone);
                        intent.putExtra("????????????????????????", area);
                        intent.putExtra("?????????????????????????????????", areaIndex);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - pressTime;

        if (0 <= intervalTime && finishTime >= intervalTime)
        {
            finish();
        }
        else
        {
            pressTime = tempTime;
            Toast.makeText(getApplicationContext(), "????????? ???????????? ?????? ???????????????", Toast.LENGTH_SHORT).show();
        }
    }
}