package com.corporation8793.festival;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

public class JoinActivity extends AppCompatActivity {

    ImageView arrow_left;
    Button joinButton;
    RadioButton button[] = new RadioButton[7];
    int [] ridButton = {R.id.radioButton1, R.id.radioButton2, R.id.radioButton3, R.id.radioButton4,
            R.id.radioButton5, R.id.radioButton6, R.id.radioButton7};
    int i, j;
    Spinner rectangle9;
    ArrayAdapter<CharSequence> rectangle9_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        arrow_left = findViewById(R.id.arrow_left);
        arrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        // 가입시 필요한 내용이 없으면 알려주기, 다 적으면 완료되었음을 알려주고 페이지 이동
        joinButton = findViewById(R.id.joinButton);
        joinButton.setOnClickListener(new View.OnClickListener() {
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
                        } else {
                            button[j].setChecked(false);
                        }
                    }
                    //Toast.makeText(JoinActivity.this, "눌림", Toast.LENGTH_SHORT).show();
                }
            });
        }

        rectangle9 = findViewById(R.id.rectangle9);
        rectangle9_adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.question_text, R.layout.spinner_item2);
        rectangle9_adapter.setDropDownViewResource(R.layout.spinner_dropdown_item2);
        rectangle9.setAdapter(rectangle9_adapter);

    }
}