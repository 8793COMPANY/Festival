package com.corporation8793.festival;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CategoryFragment extends Fragment {

    RecyclerView recyclerView;
    FestivalInfoAdapter festivalInfoAdapter;
    TextView categoryText;
    String category;

    List<FestivalInfo> festivalInfoList = new ArrayList<>();
    List<FestivalInfo> festivalInfoList2 = new ArrayList<>();
    List<FestivalInfo> festivalInfoList3 = new ArrayList<>();
    AppDatabase2 db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        categoryText = view.findViewById(R.id.categoryText);

        Bundle bundle = getArguments();
        category = bundle.getString("카테고리");
        //Toast.makeText(getActivity(), category, Toast.LENGTH_SHORT).show();

        categoryText.setText(category);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        festivalInfoAdapter = new FestivalInfoAdapter(getActivity(), 1, 1);
        recyclerView.setAdapter(festivalInfoAdapter);

        db = AppDatabase2.getDBInstance(this.getActivity());
        loadCategoryList(category);

        return view;
    }

    private void loadCategoryList(String category) {
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
            if(festivalInfoList2.get(i).festivalName.contains(category) || festivalInfoList2.get(i).festivalCo.contains(category)) {
                festivalInfoList3.add(festivalInfoList2.get(i));
            }
        }

        if(festivalInfoList3.isEmpty()) {
            Toast.makeText(getActivity(), "관련 축제가 없습니다", Toast.LENGTH_SHORT).show();
        }

        festivalInfoAdapter.setFestivalInfoList(festivalInfoList3);
    }
}