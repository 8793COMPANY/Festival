package com.corporation8793.festival;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter();

        recyclerAdapter.addItem(new Festival("2022.09.01~09.12", "광주 맥주 축제"));
        recyclerAdapter.addItem(new Festival("2022.09.14~09.20", "가을 꽃 축제"));
        recyclerAdapter.addItem(new Festival("2022.09.31", "3"));
        recyclerAdapter.addItem(new Festival("4", "4"));
        recyclerAdapter.addItem(new Festival("5", "5"));

        recyclerView.setAdapter(recyclerAdapter);
    }

}