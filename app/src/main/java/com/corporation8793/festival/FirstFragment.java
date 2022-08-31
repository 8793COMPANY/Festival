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

public class FirstFragment extends Fragment {

    Button idButton;
    EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        editText = view.findViewById(R.id.rectangle3);
        idButton = view.findViewById(R.id.idButton);
        idButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_address = editText.getText().toString();
                String address = "wisi8793@gmail.com";
                
                if(user_address.equals("")) {
                    Toast.makeText(getActivity(), "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else if(!user_address.equals(address)) {
                    Toast.makeText(getActivity(), "해당 이메일을 없는 이메일입니다.", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getActivity(), FindResultActivity.class);
                    intent.putExtra("상단타이틀", "아이디를 찾았습니다.");
                    intent.putExtra("버튼타이틀", "로그인");
                    intent.putExtra("아이디", "wisi8793");
                    startActivity(intent);
                }
            }
        });

        return view;
    }
}