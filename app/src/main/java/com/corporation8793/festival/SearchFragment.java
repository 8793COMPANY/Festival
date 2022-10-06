package com.corporation8793.festival;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchFragment extends Fragment {

    RecyclerView recyclerView, recyclerView5;
    RecyclerAdapter2 recyclerAdapter2;
    FestivalInfoAdapter festivalInfoAdapter;
    Context context;
    ListView listView;
    ListAdapter2 myPageAdapter2;
    EditText rectangle13;
    ImageView search;
    TextView testText;

    List<FestivalInfo> festivalInfoList = new ArrayList<>();
    List<FestivalInfo> festivalInfoList2 = new ArrayList<>();
    List<FestivalInfo> festivalInfoList3 = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        context = container.getContext();

        recyclerView = view.findViewById(R.id.recyclerView2);
        recyclerView5 = view.findViewById(R.id.recyclerView5);
        listView = view.findViewById(R.id.listView);
        rectangle13 = view.findViewById(R.id.rectangle13);
        search = view.findViewById(R.id.search);
        testText = view.findViewById(R.id.testText);

        recyclerView5.setVisibility((View.GONE));
        testText.setVisibility((View.GONE));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter2 = new RecyclerAdapter2(getActivity());

        recyclerAdapter2.addItem(new Festival2("꽃", R.drawable.search_flower));
        recyclerAdapter2.addItem(new Festival2("불꽃", R.drawable.search_fireworks));
        recyclerAdapter2.addItem(new Festival2("전통문화", R.drawable.search_traditional));
        recyclerAdapter2.addItem(new Festival2("음악", R.drawable.search_music));
        recyclerAdapter2.addItem(new Festival2("맥주", R.drawable.search_beer));
        recyclerAdapter2.addItem(new Festival2("단풍", R.drawable.search_maple));
        recyclerAdapter2.addItem(new Festival2("문화", R.drawable.search_culture));

        recyclerView.setAdapter(recyclerAdapter2);

        recyclerAdapter2.setOnCategoryClickListener(new RecyclerAdapter2.OnCategoryClickListener() {
            @Override
            public void onCategoryClick(View view, int position) {
                Festival2 item = recyclerAdapter2.getItem(position);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                CategoryFragment categoryFragment = new CategoryFragment();

                Bundle bundle = new Bundle();
                bundle.putString("카테고리", bundle.getString(item.name));

                categoryFragment.setArguments(bundle);
                transaction.replace(R.id.containers, categoryFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        myPageAdapter2 = new ListAdapter2(getActivity(),1);

        myPageAdapter2.addItem(new MyList2("1", "맥주축제"));
        myPageAdapter2.addItem(new MyList2("2", "보령머드축제"));
        myPageAdapter2.addItem(new MyList2("3", "조치원복숭아축제"));
        myPageAdapter2.addItem(new MyList2("4", "태백 해바라기축제"));

        listView.setAdapter(myPageAdapter2);

        Bundle bundle = getArguments();
        int uid = bundle.getInt("메인예약구별");

        recyclerView5.setLayoutManager(new LinearLayoutManager(context));
        festivalInfoAdapter = new FestivalInfoAdapter(context, 3, uid);
        recyclerView5.setAdapter(festivalInfoAdapter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rectangle13.getText().toString().equals("")) {
                    Toast.makeText(context, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    recyclerView5.setVisibility((View.VISIBLE));
                    loadUserList(rectangle13.getText().toString());
                }
            }
        });

        return view;

        //String key = "asO9jixtVSQd1RMbVCUr%2F1yFhPuiL5H9VXW1qGHbnb8TXnIWvVQ4MP0qS0pi4gf2EplaNECQC6ucPukAlFhnyA%3D%3D";
        //String data;
        //StringBuffer buffer;
        /*
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            String queryUrl = "http://api.data.go.kr/openapi/tn_pubr_public_cltur_fstvl_api"
                                    + "?ServiceKey=" + key + "&type=json"/*+ "&opar=" + str;    //&type=json*/
/*
                            System.out.println(queryUrl);
                            JSONObject jsonObject = new JSONObject(queryUrl);
                            String name = jsonObject.getString("records");

                            JSONArray jsonArray = jsonObject.getJSONArray(name);
                            System.out.println("길이: " + jsonArray.length());

                            for(int i=0; i<jsonArray.length(); i++) {
                                JSONObject subJsonObject = jsonArray.getJSONObject(i);
                                String festival = subJsonObject.getString("축제명");
                                String start = subJsonObject.getString("축제시작일자");
                                String end = subJsonObject.getString("축제종료일자");

                                System.out.println("축제이름: " + festival + "\n" + "기간: " + start + "~" + end);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        data = getData();

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                testText.setText(data);
                            }
                        });
                    }
                }).start();
            }

        });
        */
    }

    private void loadUserList(String s1) {
        AppDatabase2 db  = AppDatabase2.getDBInstance(this.getActivity());

        festivalInfoList = db.festivalInfoDao().getAllFestivalInfo();
        festivalInfoList2.clear();
        festivalInfoList3.clear();

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = simpleDate.format(date);

        for(int i=0; i < festivalInfoList.size(); i++) {
            int compare = getTime.compareTo(festivalInfoList.get(i).festivalEnd);
            //현재 날짜가 축제 마지막날보다 전일때만 리스트에 추가
            if(compare < 0) {
                festivalInfoList2.add(festivalInfoList.get(i));
            }
        }

        for(int i=0; i < festivalInfoList2.size(); i++) {
            if(festivalInfoList2.get(i).festivalName.contains(s1) || festivalInfoList2.get(i).festivalLocation.contains(s1)
                    || festivalInfoList2.get(i).festivalRdnmadr.contains(s1) || festivalInfoList2.get(i).festivalLnmadr.contains(s1)) {
                festivalInfoList3.add(festivalInfoList2.get(i));
            }
        }

        if(festivalInfoList3.isEmpty()) {
            testText.setVisibility((View.VISIBLE));
        } else {
            testText.setVisibility((View.GONE));
        }
        //리스트 저장
        festivalInfoAdapter.setFestivalInfoList(festivalInfoList3);
    }

    /*
    private String getData() {
        buffer = new StringBuffer();
        String str = rectangle13.getText().toString();
        //String location = URLEncoder.encode(str);
        String queryUrl = "http://api.data.go.kr/openapi/tn_pubr_public_cltur_fstvl_api"
                + "?ServiceKey=" + key /*+ "&opar=" + str*/;    //&type=json
    /*
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
                        buffer.append("파싱 시작...\n\n");
                        break;
                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();
                        System.out.println(tag.length());

                        if(tag.equals("item"));
                        else if(tag.equals("fstvlNm")) {
                            buffer.append("축제명 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if(tag.equals("fstvlStartDate")) {
                            buffer.append("축제기간 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("~");
                        } else if(tag.equals("fstvlEndDate")) {
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        break;
                    case  XmlPullParser.TEXT:
                        break;
                    case  XmlPullParser.END_TAG:
                        tag = xpp.getName();
                        if(tag.equals("item")) buffer.append("\n");
                        break;
                }
                eventType = xpp.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        buffer.append("파싱 끝\n");

        return buffer.toString();
    }
    */
}