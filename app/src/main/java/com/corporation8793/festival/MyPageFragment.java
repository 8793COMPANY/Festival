package com.corporation8793.festival;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MyPageFragment extends Fragment {

    TextView logoutText;
    ListAdapter myPageAdapter;
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
        myPageAdapter = new ListAdapter(getActivity());

        myPageAdapter.addItem(new MyList("예약내역", R.drawable.festival_resize_month, R.drawable.arrow_resize_right_white2));
        myPageAdapter.addItem(new MyList("앱 정보", R.drawable.mypage_resize_image1, R.drawable.arrow_resize_right_white2));
        myPageAdapter.addItem(new MyList("자동로그인", R.drawable.mypage_resize_image2, R.drawable.arrow_resize_right_white2));

        listView.setAdapter(myPageAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    ((MainActivity)getActivity()).fragmentChange(BreakdownFragment.newInstance());
                }
            }
        });

        return view;
    }
}