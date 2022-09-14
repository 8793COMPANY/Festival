package com.corporation8793.festival;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SearchFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerAdapter2 recyclerAdapter2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = view.findViewById(R.id.recyclerView2);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter2 = new RecyclerAdapter2(getActivity());

        recyclerAdapter2.addItem(new Festival2("꽃", R.drawable.search_image3));
        recyclerAdapter2.addItem(new Festival2("불꽃", R.drawable.search_image2));
        recyclerAdapter2.addItem(new Festival2("먹거리", R.drawable.search_image1));
        recyclerAdapter2.addItem(new Festival2("꽃", R.drawable.search_image3));
        recyclerAdapter2.addItem(new Festival2("불꽃", R.drawable.search_image2));

        recyclerView.setAdapter(recyclerAdapter2);

        Intent intent = new Intent();
        intent.putExtra("순위","인기 검색 순위");

        return view;
    }
}