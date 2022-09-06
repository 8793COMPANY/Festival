package com.corporation8793.festival;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    Spinner choiceMonth, choiceArea;
    ArrayAdapter<CharSequence> choiceMonth_adapter, choiceArea_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        choiceMonth = view.findViewById(R.id.choiceMonth);
        choiceArea = view.findViewById(R.id.choiceArea);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter();

        recyclerAdapter.addItem(new Festival("2022.09.01~09.12", "광주 맥주 축제"));
        recyclerAdapter.addItem(new Festival("2022.09.14~09.20", "가을 꽃 축제"));
        recyclerAdapter.addItem(new Festival("2022.09.31", "3"));
        recyclerAdapter.addItem(new Festival("4", "4"));
        recyclerAdapter.addItem(new Festival("5", "5"));

        recyclerView.setAdapter(recyclerAdapter);

        choiceMonth_adapter = ArrayAdapter.createFromResource(getActivity(), R.array.month_array, android.R.layout.simple_spinner_item);
        choiceMonth_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choiceMonth.setAdapter(choiceMonth_adapter);

        choiceArea_adapter = ArrayAdapter.createFromResource(getActivity(), R.array.area_array, android.R.layout.simple_spinner_item);
        choiceArea_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choiceArea.setAdapter(choiceArea_adapter);

        return view;
    }
}