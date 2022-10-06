package com.corporation8793.festival;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    RecyclerView recyclerView;
    FestivalInfoAdapter festivalInfoAdapter;
    TextView categoryText;

    List<FestivalInfo> festivalInfoList = new ArrayList<>();
    List<FestivalInfo> festivalInfoList2 = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        //recyclerView.findViewById(R.id.recyclerView);
        //categoryText.findViewById(R.id.categoryText);

        return view;
    }
}