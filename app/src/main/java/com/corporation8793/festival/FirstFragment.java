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

public class FirstFragment extends Fragment {

    Button idButton;
    EditText editText;

    List<User> userList = new ArrayList<>();
    String email, id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        editText = view.findViewById(R.id.rectangle3);
        idButton = view.findViewById(R.id.idButton);

        AppDatabase db  = AppDatabase.getDBInstance(this.getActivity());
        userList = db.userDao().getAllUser();

        idButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_email = editText.getText().toString();

                if(user_email.equals("")) {
                    Toast.makeText(getActivity(), "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else {
                    for (int i=0; i < userList.size(); i++) {
                        if(userList.get(i).userEmail.equals(user_email)) {
                            id = userList.get(i).userId;
                            email = userList.get(i).userEmail;
                        }
                    }

                    if(!user_email.equals(email)) {
                        Toast.makeText(getActivity(), "해당 이메일은 없는 이메일입니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(getActivity(), FindResultActivity.class);
                        intent.putExtra("아이디제목", "아이디 찾기");
                        intent.putExtra("아이디상단", "아이디를 찾았습니다.");
                        intent.putExtra("아이디버튼", "로그인");
                        intent.putExtra("아이디", id);
                        startActivity(intent);
                    }
                }
                /*
                if(user_address.equals("")) {
                    Toast.makeText(getActivity(), "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else {if(!user_address.equals(address)) {
                    Toast.makeText(getActivity(), "해당 이메일을 없는 이메일입니다.", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getActivity(), FindResultActivity.class);
                    intent.putExtra("아이디제목", "아이디 찾기");
                    intent.putExtra("아이디상단", "아이디를 찾았습니다.");
                    intent.putExtra("아이디버튼", "로그인");
                    intent.putExtra("아이디", "wisi8793");
                    startActivity(intent);
                }*/
            }
        });

        return view;
    }
}