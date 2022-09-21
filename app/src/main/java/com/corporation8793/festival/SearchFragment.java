package com.corporation8793.festival;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerAdapter2 recyclerAdapter2;
    ListView listView;
    ListAdapter2 myPageAdapter2;
    EditText rectangle13;
    ImageView search;
    TextView testText;

    String key = "asO9jixtVSQd1RMbVCUr%2F1yFhPuiL5H9VXW1qGHbnb8TXnIWvVQ4MP0qS0pi4gf2EplaNECQC6ucPukAlFhnyA%3D%3D";
    String data;
    StringBuffer buffer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = view.findViewById(R.id.recyclerView2);
        listView = view.findViewById(R.id.listView);
        rectangle13 = view.findViewById(R.id.rectangle13);
        search = view.findViewById(R.id.search);
        testText = view.findViewById(R.id.testText);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter2 = new RecyclerAdapter2(getActivity());

        recyclerAdapter2.addItem(new Festival2("꽃", R.drawable.search_image3));
        recyclerAdapter2.addItem(new Festival2("불꽃", R.drawable.search_image2));
        recyclerAdapter2.addItem(new Festival2("먹거리", R.drawable.search_image1));
        recyclerAdapter2.addItem(new Festival2("꽃", R.drawable.search_image3));
        recyclerAdapter2.addItem(new Festival2("불꽃", R.drawable.search_image2));

        recyclerView.setAdapter(recyclerAdapter2);

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

        myPageAdapter2 = new ListAdapter2(getActivity(),1);

        myPageAdapter2.addItem(new MyList2("1", "맥주축제"));
        myPageAdapter2.addItem(new MyList2("2", "보령머드축제"));
        myPageAdapter2.addItem(new MyList2("3", "조치원복숭아축제"));
        myPageAdapter2.addItem(new MyList2("4", "태백 해바라기축제"));

        listView.setAdapter(myPageAdapter2);

        return view;
    }

    private String getData() {
        buffer = new StringBuffer();
        String str = rectangle13.getText().toString();
        //String location = URLEncoder.encode(str);
        String queryUrl = "http://api.data.go.kr/openapi/tn_pubr_public_cltur_fstvl_api"
                + "?ServiceKey=" + key /*+ "&opar=" + str*/;

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
}