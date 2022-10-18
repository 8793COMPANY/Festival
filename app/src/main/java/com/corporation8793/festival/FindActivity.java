package com.corporation8793.festival;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class FindActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    ViewpagerAdapter viewpagerAdapter;
    FirstFragment firstFragment;
    SecondFragment secondFragment;
    TextView textView;
    ImageView arrow_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabs);
        textView = findViewById(R.id.textView);

        arrow_left = findViewById(R.id.arrow_left);
        arrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        createFragment();
        createViewpager();
        settingTabLayout();

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                switch (position) {
                    case 0:
                    {
                        tabLayout.getTabAt(position).select();
                        textView.setText("아이디 찾기");
                        break;
                    }
                    case 1:
                    {
                        tabLayout.getTabAt(position).select();
                        textView.setText("비밀번호 찾기");
                        break;
                    }
                }
            }
        });

    }

    //fragment 생성
    public void createFragment()
    {
        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
    }

    //viewpager 및 어댑터 생성
    public void createViewpager()
    {
        viewPager = findViewById(R.id.viewPager);
        viewpagerAdapter = new ViewpagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewpagerAdapter.addFragment(firstFragment);
        viewpagerAdapter.addFragment(secondFragment);

        viewPager.setAdapter(viewpagerAdapter);
        viewPager.setUserInputEnabled(true);//터치 스크롤 막음
    }

    //tablayout - viewpager 연결
    public void settingTabLayout()
    {
        tabLayout = findViewById(R.id.tabs);
        textView = findViewById(R.id.textView);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                switch (pos)
                {
                    case 0 :
                        viewPager.setCurrentItem(0);
                        textView.setText("아이디 찾기");
                        break;
                    case 1 :
                        viewPager.setCurrentItem(1);
                        textView.setText("비밀번호 찾기");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
}