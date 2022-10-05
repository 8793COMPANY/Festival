package com.corporation8793.festival;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class MyPageFragment extends Fragment {

    TextView logoutText;
    //ListAdapter myPageAdapter;
    //ListView listView;
    ImageView list1Image2;
    Button dataModify;
    Switch list3Switch;
    String userId, userPw;

    public static MyPageFragment newInstance() {
        return new MyPageFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);

        logoutText = view.findViewById(R.id.logoutText);
        logoutText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences auto = getActivity().getSharedPreferences("autoLogin", Activity.MODE_PRIVATE);
                SharedPreferences.Editor autoLoginEdit = auto.edit();
                autoLoginEdit.clear();
                autoLoginEdit.commit();

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        list1Image2 = view.findViewById(R.id.list1Image2);
        list1Image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((MainActivity)getActivity()).fragmentChange(BreakdownFragment.newInstance());
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                BreakdownFragment breakdownFragment = new BreakdownFragment();

                Bundle bundle = new Bundle();
                Bundle bundle1 = getArguments();

                bundle.putString("예약내역", "추가안함");
                bundle.putInt("알람사용자구분2", bundle1.getInt("메인예약구별"));

                breakdownFragment.setArguments(bundle);
                transaction.replace(R.id.containers,breakdownFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        dataModify = view.findViewById(R.id.dataModify);
        dataModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle1 = getArguments();

                Intent intent = new Intent(getActivity(), PwCheckActivity.class);
                intent.putExtra("수정페이지사용자구분2", bundle1.getInt("메인예약구별"));
                intent.putExtra("수정페이지이름2", bundle1.getString("메인이름"));
                intent.putExtra("수정페이지아이디2", bundle1.getString("메인아이디"));
                intent.putExtra("수정페이지비밀번호2", bundle1.getString("메인비밀번호"));
                intent.putExtra("수정페이지질문2", bundle1.getString("메인질문"));
                intent.putExtra("수정페이지질문인덱스2", bundle1.getInt("메인질문인덱스"));
                intent.putExtra("수정페이지답변2", bundle1.getString("메인답변"));
                intent.putExtra("수정페이지이메일2", bundle1.getString("메인이메일"));
                intent.putExtra("수정페이지연락처2", bundle1.getString("메인연락처"));
                intent.putExtra("수정페이지지역2", bundle1.getString("메인지역"));
                intent.putExtra("수정페이지지역인덱스2", bundle1.getInt("메인지역인덱스"));
                startActivity(intent);
            }
        });

        list3Switch = view.findViewById(R.id.list3Switch);
        //자동로그인 체크 처리
        SharedPreferences auto = getActivity().getSharedPreferences("autoLogin", Activity.MODE_PRIVATE);
        SharedPreferences.Editor autoLoginEdit = auto.edit();
        String check = auto.getString("check", null);

        Bundle bundle = getArguments();

        if(check != null) {
            list3Switch.setChecked(true);
        }

        list3Switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list3Switch.isChecked()) {
                    SharedPreferences auto = getActivity().getSharedPreferences("autoLogin", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor autoLoginEdit = auto.edit();
                    //로그인시 자동로그인 체크를 했을때
                    //로그인시 자동로그인 체크를 안했을때
                    //autoLoginEdit.putString("userId", userId);
                    //autoLoginEdit.putString("userPw", userPw);

                    autoLoginEdit.putString("userId", bundle.getString("메인아이디"));
                    autoLoginEdit.putString("userPw", bundle.getString("메인비밀번호"));
                    //마이페이지의 스위치버튼 체크
                    autoLoginEdit.putString("check", "on");
                    autoLoginEdit.commit();
                } else {
                    userId = auto.getString("userId", null);
                    userPw = auto.getString("userPw", null);
                    autoLoginEdit.clear();
                    autoLoginEdit.commit();
                }
            }
        });
        /*
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
         */
        return view;
    }
}