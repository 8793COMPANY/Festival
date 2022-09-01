package com.corporation8793.festival;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class FindPwActivity extends AppCompatActivity {

    Spinner choiceQuestion;
    ArrayAdapter<CharSequence> choiceQuestion_adapter;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pw);

        choiceQuestion = findViewById(R.id.choiceQuestion);
        nextButton = findViewById(R.id.nextButton);

        choiceQuestion_adapter = ArrayAdapter.createFromResource(this, R.array.question_text, android.R.layout.simple_spinner_item);
        choiceQuestion_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choiceQuestion.setAdapter(choiceQuestion_adapter);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindResultActivity.class);
                startActivity(intent);
            }
        });
    }
}