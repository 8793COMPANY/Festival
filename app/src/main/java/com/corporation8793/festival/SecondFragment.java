package com.corporation8793.festival;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {

    Button pwButton;
    EditText editText;

    List<User> userList = new ArrayList<>();
    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        editText = view.findViewById(R.id.rectangle4);
        pwButton = view.findViewById(R.id.pwButton);

        AppDatabase db  = AppDatabase.getDBInstance(this.getActivity());
        userList = db.userDao().getAllUser();

        pwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_id = editText.getText().toString();

                if(user_id.equals("")) {
                    Toast.makeText(getActivity(), "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else {
                    for (int i=0; i < userList.size(); i++) {
                        if(userList.get(i).userId.equals(user_id)) {
                            id = userList.get(i).userId;
                        }
                    }

                    if(!user_id.equals(id)) {
                        Toast.makeText(getActivity(), "해당 아이디는 없는 아이디입니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(getActivity(), FindPwActivity.class);
                        intent.putExtra("비밀번호찾기아이디", id);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                /*
                if(user_id.equals("")) {
                    Toast.makeText(getActivity(), "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else if(!user_id.equals(id)) {
                    Toast.makeText(getActivity(), "해당 아이디는 없는 아이디입니다.", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getActivity(), FindPwActivity.class);
                    startActivity(intent);
                }*/
            }
        });

        return view;
    }
}