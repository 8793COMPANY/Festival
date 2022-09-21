package com.corporation8793.festival;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

import java.util.List;

public class UserInformationActivity extends AppCompatActivity {

    UserAdapter adapter;
    ImageView arrow_left;
    RecyclerView recyclerView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

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

        //사용자 삭제
        /*
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //int position = viewHolder.getBindingAdapterPosition();

            }
        });
         */

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