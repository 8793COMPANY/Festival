package com.corporation8793.festival.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.corporation8793.festival.activity.PwCheckActivity;
import com.corporation8793.festival.R;
import com.corporation8793.festival.activity.LoginActivity;

public class MyPageFragment extends Fragment {

    TextView logoutText;
    ImageView list1Image2, listView1, list2Image2, listView2;
    Button dataModify;
    SwitchCompat list3Switch;
    //Switch list3Switch;
    String userId, userPw;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);

        // 로그아웃 처리
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
                getActivity().finish();
            }
        });
        // 예약내역이동1 >> 조건문으로 버튼 기능이 같을때로 찾아보기
        list1Image2 = view.findViewById(R.id.list1Image2);
        list1Image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ReservationDetailFragment breakdownFragment = new ReservationDetailFragment();

                Bundle bundle = new Bundle();
                Bundle bundle1 = getArguments();

                bundle.putString("예약내역", "추가안함");
                bundle.putInt("알람사용자구분2", bundle1.getInt("메인예약구별"));

                Log.e("myPageId", bundle1.getInt("메인예약구별")+"");

                breakdownFragment.setArguments(bundle);
                transaction.replace(R.id.containers,breakdownFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        // 예약내역이동1
        listView1 = view.findViewById(R.id.listView1);
        listView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ReservationDetailFragment breakdownFragment = new ReservationDetailFragment();

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
        // 앱정보이동1
        list2Image2 = view.findViewById(R.id.list2Image2);
        list2Image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AppInfoFragment appInfoFragment = new AppInfoFragment();

                transaction.replace(R.id.containers,appInfoFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        // 앱정보이동1
        listView2 = view.findViewById(R.id.listView2);
        listView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AppInfoFragment appInfoFragment = new AppInfoFragment();

                transaction.replace(R.id.containers,appInfoFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        // 회원정보수정버튼
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
        // 자동로그인 처리
        list3Switch = view.findViewById(R.id.list3Switch);
        // 자동로그인 체크 처리
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
                    autoLoginEdit.putString("userId", bundle.getString("메인아이디"));
                    autoLoginEdit.putString("userPw", bundle.getString("메인비밀번호"));
                    //마이페이지의 스위치버튼 체크
                    autoLoginEdit.putString("check", "on");
                    autoLoginEdit.commit();
                } else {
                    //로그인시 자동로그인 체크를 안했을때
                    userId = auto.getString("userId", null);
                    userPw = auto.getString("userPw", null);
                    autoLoginEdit.clear();
                    autoLoginEdit.commit();
                }
            }
        });

        return view;
    }
}