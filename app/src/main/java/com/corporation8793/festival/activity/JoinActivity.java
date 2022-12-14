package com.corporation8793.festival.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.corporation8793.festival.room.AppDatabase;
import com.corporation8793.festival.R;
import com.corporation8793.festival.room.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JoinActivity extends AppCompatActivity {

    TextView textView, pwChangeText, pwCheckInputText;
    ImageView arrow_left, checkButtonImage;
    Button joinButton;
    RadioButton button[] = new RadioButton[7];
    int [] ridButton = {R.id.radioButton1, R.id.radioButton2, R.id.radioButton3, R.id.radioButton4,
            R.id.radioButton5, R.id.radioButton6, R.id.radioButton7};
    int i, j, index;
    CheckBox checkButton2;
    // rectangle9 비밀번호 질문
    Spinner rectangle9;
    ArrayAdapter<CharSequence> rectangle9_adapter;

    EditText rectangle5, rectangle6, rectangle7, rectangle8, rectangle10, rectangle11, rectangle12;
    String sArea;

    AppDatabase db;
    List<User> userList = new ArrayList<>();
    String id, email, id2, email2;

    //비밀번호 변경시 수정 변수
    static String changePw = "";
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

        textView = findViewById(R.id.textView);
        pwChangeText = findViewById(R.id.pwChangeText);
        pwCheckInputText = findViewById(R.id.pwCheckInputText);
        rectangle5 = findViewById(R.id.rectangle5);
        rectangle6 = findViewById(R.id.rectangle6);
        rectangle7 = findViewById(R.id.rectangle7);
        rectangle8 = findViewById(R.id.rectangle8);
        rectangle9 = findViewById(R.id.rectangle9);
        rectangle10 = findViewById(R.id.rectangle10);
        rectangle11 = findViewById(R.id.rectangle11);
        rectangle12 = findViewById(R.id.rectangle12);
        joinButton = findViewById(R.id.joinButton);
        checkButtonImage = findViewById(R.id.checkButtonImage);
        checkButton2 = findViewById(R.id.checkButton2);

        rectangle9_adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.question_text, R.layout.item_spinner_question);
        rectangle9_adapter.setDropDownViewResource(R.layout.item_spinner_dropdown_question);
        rectangle9.setAdapter(rectangle9_adapter);

        // 웹뷰(URL)
        checkButtonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                startActivity(intent);
            }
        });

        for(i=0; i<7; i++) {
            button[i] = findViewById(ridButton[i]);
        }

        if(button[0].isChecked()) {
            sArea = "서울/경기";
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
                            index = INDEX;
                        } else {
                            button[j].setChecked(false);
                        }
                    }
                }
            });
        }

        db = AppDatabase.getDBInstance(this.getApplicationContext());

        Intent intent = getIntent();

        if(intent.hasExtra("회원가입페이지이동")) {
            pwChangeText.setVisibility(View.GONE);

            // 가입시 필요한 내용이 없으면 알려주기, 다 적으면 페이지 이동
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

                    int position = rectangle9_adapter.getPosition(sPwQuestion);

                    Pattern pattern = android.util.Patterns.EMAIL_ADDRESS;
                    Matcher matcher = pattern.matcher((rectangle11).getText().toString());


                    if(!Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{8,15}$", sPw)) {
                        Toast.makeText(getApplicationContext(), "비밀번호 형식이 올바르지 않습니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                    } else if(!Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,12}$", sId)) {
                        Toast.makeText(getApplicationContext(), "아이디 형식이 올바르지 않습니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                    } else if(!sPw.equals(sPwCheck)) {
                        Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    } else if(sName.equals("")||sId.equals("")||sPw.equals("")||sPwCheck.equals("")||sPwAnswer.equals("")||sEmail.equals("")||sPhoneNumber.equals("")) {
                        Toast.makeText(getApplicationContext(), "회원정보가 부족합니다 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                    } else if(!matcher.matches()) {
                        Toast.makeText(getApplicationContext(), "이메일 형식이 올바르지 않습니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                    } else if(!Pattern.matches("^\\d{3}-\\d{3,4}-\\d{4}$", sPhoneNumber)) {
                        Toast.makeText(getApplicationContext(), "연락처 형식이 올바르지 않습니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                    } else if(!checkButton2.isChecked()) {
                        Toast.makeText(getApplicationContext(), "개인정보처리방침을 확인해주세요.", Toast.LENGTH_SHORT).show();
                    } else {
                        //중복확인
                        userList = db.userDao().getAllUser();
                        for (int i=0; i < userList.size(); i++) {
                            if(userList.get(i).userId.equals(sId)) {
                                id = userList.get(i).userId;
                            } else if(userList.get(i).userEmail.equals(sEmail)) {
                                email = userList.get(i).userEmail;
                            }
                        }

                        if(sId.equals(id)) {
                            Toast.makeText(getApplicationContext(), "아이디가 중복됩니다. 다른 아이디를 사용해주세요.", Toast.LENGTH_SHORT).show();
                        } else if(sEmail.equals(email)) {
                            Toast.makeText(getApplicationContext(), "이미 등록된 이메일입니다. 다른 이메일을 사용해주세요.", Toast.LENGTH_SHORT).show();
                        } else {
                            //사용자 등록
                            insertUser(sName, sId, sPw, sPwQuestion, position, sPwAnswer, sEmail, sPhoneNumber, sArea, index);

                            Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
            });

        }else {
            textView.setText(intent.getStringExtra("수정페이지제목"));

            pwChangeText.setVisibility(View.VISIBLE);
            pwCheckInputText.setVisibility(View.GONE);
            rectangle8.setVisibility(View.GONE);
            //비밀번호 수정 불가하게 하기 > 버튼으로만 수정할 수 있도록
            rectangle7.setEnabled(false);

            String name = intent.getStringExtra("수정페이지이름");
            String id = intent.getStringExtra("수정페이지아이디");
            String pw = intent.getStringExtra("수정페이지비밀번호");
            String pwQuestion = intent.getStringExtra("수정페이지질문");
            int pwQuestionIndex = intent.getIntExtra("수정페이지질문인덱스", 0);
            String pwAnswer = intent.getStringExtra("수정페이지답변");
            String email = intent.getStringExtra("수정페이지이메일");
            String phoneNumber = intent.getStringExtra("수정페이지연락처");
            int area = intent.getIntExtra("수정페이지지역인덱스", 0);
            int uid = intent.getIntExtra("수정페이지사용자구분", 0);

            pwChangeText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), PwChangeActivity.class);
                    intent.putExtra("찾기아이디", id);
                    startActivity(intent);
                }
            });

            rectangle5.setText(name);
            rectangle6.setText(id);
            rectangle7.setText(pw);
            rectangle9.setSelection(pwQuestionIndex);
            rectangle10.setText(pwAnswer);
            rectangle11.setText(email);
            rectangle12.setText(phoneNumber);
            checkButton2.setChecked(true);

            for(i=0; i<7; i++) {
                if(area == i) {
                    button[i].setChecked(true);
                } else {
                    button[i].setChecked(false);
                }
            }

            joinButton.setText("수정하기");
            joinButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userName = rectangle5.getText().toString();
                    String userId = rectangle6.getText().toString();
                    String userPw = rectangle7.getText().toString();
                    String userPwQuestion = rectangle9.getSelectedItem().toString();
                    int pwQuestionIndex = rectangle9_adapter.getPosition(userPwQuestion);
                    String userPwAnswer = rectangle10.getText().toString();
                    String userEmail = rectangle11.getText().toString();
                    String userPhoneNumber = rectangle12.getText().toString();
                    String userArea = sArea;
                    int userAreaIndex = index;


                    Pattern pattern = android.util.Patterns.EMAIL_ADDRESS;
                    Matcher matcher = pattern.matcher((rectangle11).getText().toString());


                    if(!matcher.matches()) {
                        Toast.makeText(getApplicationContext(), "이메일 형식이 올바르지 않습니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                    } else if(!Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,12}$", userId)) {
                        Toast.makeText(getApplicationContext(), "아이디 형식이 올바르지 않습니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                    } else if(!Pattern.matches("^\\d{3}-\\d{3,4}-\\d{4}$", userPhoneNumber)) {
                        Toast.makeText(getApplicationContext(), "연락처 형식이 올바르지 않습니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                    } else if(userName.equals("")||userId.equals("")||userPw.equals("")||userPwAnswer.equals("")||userEmail.equals("")||userPhoneNumber.equals("")) {
                        Toast.makeText(getApplicationContext(), "회원정보가 부족합니다 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                    } else {
                        //중복확인(기존의 아이디와 이메일을 그대로 썼을때는 예외로 두어야 함)
                        userList = db.userDao().getAllUser();
                        for (int i = 0; i < userList.size(); i++) {
                            if (userList.get(i).userId.equals(userId)) {
                                if(userList.get(i).userId.equals(id)) {
                                    id2 = "";
                                }else {
                                    id2 = userList.get(i).userId;
                                }
                            } else if (userList.get(i).userEmail.equals(userEmail)) {
                                if(userList.get(i).userEmail.equals(email)) {
                                    id2 = "";
                                }else {
                                    email2 = userList.get(i).userEmail;
                                }
                            }
                        }

                        if(userId.equals(id2)) {
                            Toast.makeText(getApplicationContext(), "아이디가 중복됩니다. 다른 아이디를 사용해주세요.", Toast.LENGTH_SHORT).show();
                        } else if(userEmail.equals(email2)) {
                            Toast.makeText(getApplicationContext(), "이미 등록된 이메일입니다. 다른 이메일을 사용해주세요.", Toast.LENGTH_SHORT).show();
                        } else if(!checkButton2.isChecked()) {
                            Toast.makeText(getApplicationContext(), "개인정보처리방침을 확인해주세요.", Toast.LENGTH_SHORT).show();
                        } else {
                            //정보 업데이트
                            User user = new User();
                            user.uid = uid;
                            user.userName = userName;
                            user.userId = userId;
                            user.userPwQuestion = userPwQuestion;
                            user.PwQuestionIndex = pwQuestionIndex;
                            user.userPwAnswer = userPwAnswer;
                            user.userPw = userPw;
                            user.userEmail = userEmail;
                            user.userPhoneNumber = userPhoneNumber;
                            user.userArea = userArea;
                            user.userAreaIndex = userAreaIndex;

                            db.userDao().updateUser(user);

                            //자동로그인 체크되어있으면 해제
                            SharedPreferences auto = getSharedPreferences("autoLogin", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor autoLoginEdit = auto.edit();
                            String userId2 = auto.getString("userId", null);
                            String userPw2 = auto.getString("userPw", null);

                            if(userId2 != null && userPw2 != null) {
                                autoLoginEdit.clear();
                                autoLoginEdit.commit();
                            }

                            Toast.makeText(getApplicationContext(), "회원 정보가 수정되었습니다. 다시 로그인해주세요.", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
            });
        }

        arrow_left = findViewById(R.id.arrow_left);
        arrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intent.hasExtra("회원가입페이지이동")) {
                    Intent intent2 = new Intent(JoinActivity.this, LoginActivity.class);
                    startActivity(intent2);
                    finish();
                } else {
                    finish();
                }
            }
        });
    }

    public void getChangePw(String s) {
        changePw = s;
    }

    public void onResume() {
        super.onResume();
        Log.e("회원가입페이지", "onResume : 호출됨");

        if(!changePw.equals("")) {
            rectangle7.setText(changePw);

            changePw = "";
        }
    }

    public void onBackPressed() {
        Intent intent = getIntent();

        if(intent.hasExtra("회원가입페이지이동")) {
            Intent intent2 = new Intent(JoinActivity.this, LoginActivity.class);
            startActivity(intent2);
            finish();
        } else {
            finish();
        }
    }

    private void insertUser(String name, String id, String pw, String pwQuestion, int position, String pwAnswer, String email, String phoneNumber, String area, int index) {

        User user = new User();
        user.userName = name;
        user.userId = id;
        user.userPw = pw;
        user.userPwQuestion = pwQuestion;
        user.PwQuestionIndex = position;
        user.userPwAnswer = pwAnswer;
        user.userEmail = email;
        user.userPhoneNumber = phoneNumber;
        user.userArea = area;
        user.userAreaIndex = index;

        db.userDao().insertUser(user);

        setResult(Activity.RESULT_OK);

        finish();
    }
}