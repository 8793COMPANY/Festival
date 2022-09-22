package com.corporation8793.festival;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MainFragment mainFragment;
    SearchFragment searchFragment;
    EventFragment eventFragment;
    MyPageFragment myPageFragment;

    String key = "asO9jixtVSQd1RMbVCUr%2F1yFhPuiL5H9VXW1qGHbnb8TXnIWvVQ4MP0qS0pi4gf2EplaNECQC6ucPukAlFhnyA%3D%3D";
    String festivalName, festivalStart, festivalEnd, festivalCo, festivalLocation, festivalMnnst, festivalAuspcInstt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = new MainFragment();
        searchFragment = new SearchFragment();
        eventFragment = new EventFragment();
        myPageFragment = new MyPageFragment();

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
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, searchFragment).commit();
                        return true;
                    case R.id.event:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, eventFragment).commit();
                        return true;
                    case R.id.mypage:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, myPageFragment).commit();
                        return true;
                }
                return false;
            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                getData(); /*

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        insertFestivalInfo(festivalName, festivalStart, festivalEnd, festivalCo, festivalLocation, festivalMnnst, festivalAuspcInstt);
                    }
                }); */
            }
        }).start();

    }

    public void fragmentChange(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containers, fragment).commit();
    }

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

}