package com.corporation8793.festival;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainFragment mainFragment;
    SearchFragment searchFragment;
    EventFragment eventFragment;
    MyPageFragment myPageFragment;

    List<FestivalInfo> festivalInfoList;
    AppDatabase2 db;

    //String key = "asO9jixtVSQd1RMbVCUr%2F1yFhPuiL5H9VXW1qGHbnb8TXnIWvVQ4MP0qS0pi4gf2EplaNECQC6ucPukAlFhnyA%3D%3D";
    //String festivalName, festivalStart, festivalEnd, festivalCo, festivalLocation, festivalMnnst, festivalAuspcInstt;

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
        //bundle.putString("메인예약구별", intent.getStringExtra("로그인페이지예약구별"));
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
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, mainFragment).commit();
                        return true;
                    case R.id.search:
                        searchFragment.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, searchFragment).commit();
                        return true;
                    case R.id.event:
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
/*
        new Thread(new Runnable() {
            @Override
            public void run() {
                getData();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        insertFestivalInfo(festivalName, festivalStart, festivalEnd, festivalCo, festivalLocation, festivalMnnst, festivalAuspcInstt);
                    }
                });
            }
        }).start();*/
    }

    public void fragmentChange(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containers, fragment).commit();
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

                //setResult(Activity.RESULT_OK);
                //finish();
                /*
                festivalInfo.setFestivalName(festivalObject.getString("축제명"));
                festivalInfo.setFestivalStart(festivalObject.getString("축제시작일자"));
                festivalInfo.setFestivalEnd(festivalObject.getString("축제종료일자"));
                festivalInfo.setFestivalCo(festivalObject.getString("축제내용"));
                festivalInfo.setFestivalLocation(festivalObject.getString("개최장소"));
                festivalInfo.setFestivalMnnst(festivalObject.getString("주관기관"));
                festivalInfo.setFestivalAuspcInstt(festivalObject.getString("주최기관"));
                 */
            }
            festivalInfoList = db.festivalInfoDao().getAllFestivalInfo();
            Toast.makeText(getApplicationContext(), "등록된수 :" + festivalInfoList.size(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
/*
    private void insertFestivalInfo(String name, String start, String end, String co, String location, String mnnst, String auspcInstt) {

        FestivalInfo festivalInfo = new FestivalInfo();
        festivalInfo.festivalName = name;
        festivalInfo.festivalStart = start;
        festivalInfo.festivalEnd = end;
        festivalInfo.festivalCo = co;
        festivalInfo.festivalLocation = location;
        festivalInfo.festivalMnnst = mnnst;
        festivalInfo.festivalAuspcInstt = auspcInstt;

        AppDatabase2 db = AppDatabase2.getDBInstance(this.getApplicationContext());
        db.festivalInfoDao().insertFestivalInfo(festivalInfo);

        setResult(Activity.RESULT_OK);

        finish();

    }

    private void getData() {
        String queryUrl = "http://api.data.go.kr/openapi/tn_pubr_public_cltur_fstvl_api"
                + "?ServiceKey=" + key;

        try {
            URL url = new URL(queryUrl);
            InputStream is = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is,"UTF-8"));

            String tag;

            xpp.next();
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();

                        if(tag.equals("item"));
                        else if(tag.equals("fstvlNm")) {
                            xpp.next();
                            festivalName = xpp.getText();
                            //festivalInfo.setFestivalName(xpp.getText());
                        } else if(tag.equals("fstvlStartDate")) {
                            xpp.next();
                            festivalStart = xpp.getText();
                        } else if(tag.equals("fstvlEndDate")) {
                            xpp.next();
                            festivalEnd = xpp.getText();
                        } else if(tag.equals("fstvlCo")) {
                            xpp.next();
                            festivalCo = xpp.getText();
                        } else if(tag.equals("opar")) {
                            xpp.next();
                            festivalLocation = xpp.getText();
                        } else if(tag.equals("mnnst")) {
                            xpp.next();
                            festivalMnnst = xpp.getText();
                        } else if(tag.equals("auspcInstt")) {
                            xpp.next();
                            festivalAuspcInstt = xpp.getText();
                        } else {
                            insertFestivalInfo(festivalName, festivalStart, festivalEnd, festivalCo, festivalLocation, festivalMnnst, festivalAuspcInstt);
                        }
                        break;
                    case  XmlPullParser.TEXT:
                        break;
                    case  XmlPullParser.END_TAG:
                        break;
                }
                eventType = xpp.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 */
}