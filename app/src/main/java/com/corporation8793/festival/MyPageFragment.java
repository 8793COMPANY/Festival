package com.corporation8793.festival;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MyPageFragment extends Fragment {

    TextView logoutText;
    ArrayAdapter<CharSequence> myPageAdapter;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);

        logoutText = view.findViewById(R.id.logoutText);
        logoutText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        listView = view.findViewById(R.id.listView);
        myPageAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.mypage_array, android.R.layout.simple_list_item_1);
        listView.setAdapter(myPageAdapter);

        return view;
    }
}