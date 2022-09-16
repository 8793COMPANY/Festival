package com.corporation8793.festival;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class SearchFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerAdapter2 recyclerAdapter2;
    ListView listView;
    ListAdapter2 myPageAdapter2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = view.findViewById(R.id.recyclerView2);
        listView = view.findViewById(R.id.listView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter2 = new RecyclerAdapter2(getActivity());

        recyclerAdapter2.addItem(new Festival2("꽃", R.drawable.search_image3));
        recyclerAdapter2.addItem(new Festival2("불꽃", R.drawable.search_image2));
        recyclerAdapter2.addItem(new Festival2("먹거리", R.drawable.search_image1));
        recyclerAdapter2.addItem(new Festival2("꽃", R.drawable.search_image3));
        recyclerAdapter2.addItem(new Festival2("불꽃", R.drawable.search_image2));

        recyclerView.setAdapter(recyclerAdapter2);

        myPageAdapter2 = new ListAdapter2(getActivity(),1);

        myPageAdapter2.addItem(new MyList2("1", "맥주축제"));
        myPageAdapter2.addItem(new MyList2("2", "보령머드축제"));
        myPageAdapter2.addItem(new MyList2("3", "조치원복숭아축제"));
        myPageAdapter2.addItem(new MyList2("4", "태백 해바라기축제"));

        listView.setAdapter(myPageAdapter2);

        return view;
    }
}