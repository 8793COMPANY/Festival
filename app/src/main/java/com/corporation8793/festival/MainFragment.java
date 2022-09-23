package com.corporation8793.festival;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

public class MainFragment extends Fragment {

    RecyclerView recyclerView;
    //RecyclerAdapter recyclerAdapter;
    FestivalInfoAdapter festivalInfoAdapter;
    Spinner choiceMonth, choiceArea;
    ArrayAdapter<CharSequence> choiceMonth_adapter, choiceArea_adapter;
    Context context;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        context = container.getContext();

        recyclerView = view.findViewById(R.id.recyclerView);
        choiceMonth = view.findViewById(R.id.choiceMonth);
        choiceArea = view.findViewById(R.id.choiceArea);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        festivalInfoAdapter = new FestivalInfoAdapter(context);
        recyclerView.setAdapter(festivalInfoAdapter);

        //축제 조회
        loadUserList();

        /*
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(context);

        recyclerAdapter.addItem(new Festival("2022.09.01~09.12", "광주 맥주 축제", R.drawable.festival_image_1));
        recyclerAdapter.addItem(new Festival("2022.09.14~09.20", "가을 꽃 축제", R.drawable.festival_image_2));
        recyclerAdapter.addItem(new Festival("2022.09.31", "3", R.drawable.festival_image_3));
        recyclerAdapter.addItem(new Festival("4", "4", R.drawable.festival_image_1));
        recyclerAdapter.addItem(new Festival("5", "5", R.drawable.festival_image_2));

        recyclerView.setAdapter(recyclerAdapter);


        recyclerAdapter.setOnItemClicklistener(new OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, View view, int position) {
                Festival item = recyclerAdapter.getItem(position);
                //Toast.makeText(context,"d",Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).fragmentChange(FestivalInfoFragment.newInstance());
            }
        });
         */

        choiceMonth_adapter = ArrayAdapter.createFromResource(getActivity(), R.array.month_array, R.layout.spinner_item);
        choiceMonth_adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        choiceMonth.setAdapter(choiceMonth_adapter);

        choiceMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //((TextView));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        choiceArea_adapter = ArrayAdapter.createFromResource(getActivity(), R.array.area_array, R.layout.spinner_item);
        choiceArea_adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        choiceArea.setAdapter(choiceArea_adapter);

        return view;
    }

    private void loadUserList() {
        AppDatabase2 db  = AppDatabase2.getDBInstance(this.getActivity());

        List<FestivalInfo> festivalInfoList = db.festivalInfoDao().getAllFestivalInfo();

        //리스트 저장
        festivalInfoAdapter.setFestivalInfoList(festivalInfoList);
    }

}