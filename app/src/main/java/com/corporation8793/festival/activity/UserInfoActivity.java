package com.corporation8793.festival.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.corporation8793.festival.room.AppDatabase;
import com.corporation8793.festival.R;
import com.corporation8793.festival.room.User;
import com.corporation8793.festival.adapter.UserAdapter;

import java.util.List;

public class UserInfoActivity extends AppCompatActivity {

    UserAdapter adapter;
    ImageView arrow_left;
    RecyclerView recyclerView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        arrow_left = findViewById(R.id.arrow_left);
        arrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                activityResult.launch(intent);
            }
        });

        recyclerView5 = findViewById(R.id.recyclerView5);
        recyclerView5.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter(getApplicationContext());
        recyclerView5.setAdapter(adapter);
        //사용자 조회
        loadUserList();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        loadUserList();
    }

    ActivityResultLauncher<Intent> activityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK) {
                        //사용자 조회
                        loadUserList();
                    }
                }
            }
    );

    private void loadUserList() {
        AppDatabase db  = AppDatabase.getDBInstance(this.getApplicationContext());

        List<User> userList = db.userDao().getAllUser();
        //리스트 저장
        adapter.setUserList(userList);
    }
}