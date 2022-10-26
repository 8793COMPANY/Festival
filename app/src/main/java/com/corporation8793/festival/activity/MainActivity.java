package com.corporation8793.festival.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.corporation8793.festival.R;
import com.corporation8793.festival.fragment.EventFragment;
import com.corporation8793.festival.fragment.MainFragment;
import com.corporation8793.festival.fragment.MyPageFragment;
import com.corporation8793.festival.fragment.SearchFragment;
import com.corporation8793.festival.mclass.NDSpinner;
import com.corporation8793.festival.room.AppDatabase2;
import com.corporation8793.festival.room.FestivalInfo;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class

MainActivity extends AppCompatActivity {

    MainFragment mainFragment;
    SearchFragment searchFragment;
    EventFragment eventFragment;
    MyPageFragment myPageFragment;

    List<FestivalInfo> festivalInfoList;
    AppDatabase2 db;

    long finishTime = 2000;
    long pressTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = new MainFragment();
        searchFragment = new SearchFragment();
        eventFragment = new EventFragment();
        myPageFragment = new MyPageFragment();

        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putString("메인아이디", intent.getStringExtra("로그인페이지아이디"));
        bundle.putString("메인비밀번호", intent.getStringExtra("로그인페이지비밀번호"));
        bundle.putInt("메인예약구별", intent.getIntExtra("로그인페이지예약구별", 0));
        bundle.putString("메인이름", intent.getStringExtra("로그인페이지이름"));
        bundle.putString("메인질문", intent.getStringExtra("로그인페이지질문"));
        bundle.putInt("메인질문인덱스", intent.getIntExtra("로그인페이지질문인덱스", 0));
        bundle.putString("메인답변", intent.getStringExtra("로그인페이지답변"));
        bundle.putString("메인이메일", intent.getStringExtra("로그인페이지이메일"));
        bundle.putString("메인연락처", intent.getStringExtra("로그인페이지연락처"));
        bundle.putString("메인지역", intent.getStringExtra("로그인페이지지역"));
        bundle.putInt("메인지역인덱스", intent.getIntExtra("로그인페이지지역인덱스", 0));

        getSupportFragmentManager().beginTransaction().replace(R.id.containers, mainFragment).commit();

        NavigationBarView navigationBarView = findViewById(R.id.bottom_navigationview);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.main:
                        if(mainFragment.choiceMonth != null) {
                            mainFragment.choiceMonth.setSelection(0);
                            mainFragment.choiceArea.setSelection(0);
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, mainFragment).commit();
                        return true;
                    case R.id.search:
                        if(searchFragment.recyclerView5 != null) {
                            if(searchFragment.recyclerView5.getVisibility() == View.VISIBLE) {
                                searchFragment.recyclerView5.setVisibility(View.GONE);
                                if(searchFragment.testText.getVisibility() == View.VISIBLE) {
                                    searchFragment.testText.setVisibility(View.GONE);
                                }
                                searchFragment.rectangle13.setText(null);
                            }
                        }
                        searchFragment.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, searchFragment).commit();
                        return true;
                    case R.id.event:
                        if(eventFragment.choiceMonth2 != null) {
                            eventFragment.choiceMonth2.setSelection(0);
                            eventFragment.choiceArea2.setSelection(0);
                        }

                        eventFragment.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, eventFragment).commit();
                        return true;
                    case R.id.mypage:
                        myPageFragment.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, myPageFragment).commit();
                        return true;
                }
                return false;
            }
        });

        SharedPreferences pref = getSharedPreferences("isFirst", Activity.MODE_PRIVATE);
        boolean first = pref.getBoolean("isFirst", false);
        if(first==false){
            Log.d("Is first Time?", "first");
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isFirst",true);
            editor.commit();
            //앱 최초 실행시 하고 싶은 작업
            jsonParsing(getJsonString());
        }else{
            Log.d("Is first Time?", "not first");
        }
    }

    /*@Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        Log.e("mainFocus", getCurrentFocus()+"");

        MainFragment mainFragment = new MainFragment();

        View view = getCurrentFocus();
        if(view instanceof NDSpinner) {
            mainFragment.doSomething("yes");
        } else {
            mainFragment.doSomething("no");
        }

        return super.dispatchTouchEvent(event);
    }*/

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.containers);

        if(fragment instanceof MainFragment || fragment instanceof SearchFragment ||
        fragment instanceof EventFragment || fragment instanceof MyPageFragment) {
            long tempTime = System.currentTimeMillis();
            long intervalTime = tempTime - pressTime;

            if (0 <= intervalTime && finishTime >= intervalTime)
            {
                moveTaskToBack(true);
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
            else
            {
                pressTime = tempTime;
                Toast.makeText(getApplicationContext(), "한번더 누르시면 앱이 종료됩니다", Toast.LENGTH_SHORT).show();
            }
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    private String getJsonString() {

        String json = "";

        try {
            InputStream is = getAssets().open("Festivals.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return json;
    }

    private void jsonParsing(String json) {

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray festivalArray = jsonObject.getJSONArray("records");

            Toast.makeText(getApplicationContext(), "축제수 :" + festivalArray.length(), Toast.LENGTH_SHORT).show();

            for(int i=0; i<festivalArray.length(); i++) {
                JSONObject festivalObject = festivalArray.getJSONObject(i);
                FestivalInfo festivalInfo = new FestivalInfo();

                festivalInfo.festivalName = festivalObject.getString("축제명");
                festivalInfo.festivalStart = festivalObject.getString("축제시작일자");
                festivalInfo.festivalEnd = festivalObject.getString("축제종료일자");
                festivalInfo.festivalCo = festivalObject.getString("축제내용");
                festivalInfo.festivalLocation = festivalObject.getString("개최장소");
                festivalInfo.festivalMnnst = festivalObject.getString("주관기관");
                festivalInfo.festivalAuspcInstt = festivalObject.getString("주최기관");
                festivalInfo.festivalRdnmadr = festivalObject.getString("소재지도로명주소");
                festivalInfo.festivalLnmadr = festivalObject.getString("소재지지번주소");
                festivalInfo.festivalLatitude = festivalObject.getString("위도");
                festivalInfo.festivalLongitude = festivalObject.getString("경도");

                db = AppDatabase2.getDBInstance(this.getApplicationContext());
                db.festivalInfoDao().insertFestivalInfo(festivalInfo);
            }
            festivalInfoList = db.festivalInfoDao().getAllFestivalInfo();
            Toast.makeText(getApplicationContext(), "등록된수 :" + festivalInfoList.size(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}