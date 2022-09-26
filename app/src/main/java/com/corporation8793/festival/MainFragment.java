package com.corporation8793.festival;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.List;

public class MainFragment extends Fragment {

    RecyclerView recyclerView;
    //RecyclerAdapter recyclerAdapter;
    FestivalInfoAdapter festivalInfoAdapter;
    Spinner choiceMonth, choiceArea;
    ArrayAdapter<CharSequence> choiceMonth_adapter, choiceArea_adapter;
    Context context;
    ImageView searchButton;
    List<FestivalInfo> festivalInfoList;
    List<FestivalInfo> festivalInfoList2;

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
        searchButton = view.findViewById(R.id.searchButton);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        festivalInfoAdapter = new FestivalInfoAdapter(context, 1);
        recyclerView.setAdapter(festivalInfoAdapter);

        //축제 조회
        loadUserList();

        choiceMonth_adapter = ArrayAdapter.createFromResource(getActivity(), R.array.month_array, R.layout.spinner_item);
        choiceMonth_adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        choiceMonth.setAdapter(choiceMonth_adapter);

        choiceMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        choiceArea_adapter = ArrayAdapter.createFromResource(getActivity(), R.array.area_array, R.layout.spinner_item);
        choiceArea_adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        choiceArea.setAdapter(choiceArea_adapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loadUserList2(choiceMonth.getSelectedItem().toString(), choiceArea.getSelectedItem().toString());
            }
        });

        return view;
    }

    private void loadUserList() {
        AppDatabase2 db  = AppDatabase2.getDBInstance(this.getActivity());

        festivalInfoList = db.festivalInfoDao().getAllFestivalInfo();

        //리스트 저장
        festivalInfoAdapter.setFestivalInfoList(festivalInfoList);
    }

    private void loadUserList2(String s1, String s2) {
        festivalInfoList2.clear();

        for(int i=0; i < festivalInfoList.size(); i++) {
           if(festivalInfoList.get(i).festivalLocation.toLowerCase().contains(s2.toLowerCase())) {
               festivalInfoList2.add(festivalInfoList.get(i));
               /*
               FestivalInfo festivalInfo = new FestivalInfo();
               festivalInfo.festivalName = festivalInfoList.get(i).festivalName;
               festivalInfo.festivalStart = festivalInfoList.get(i).festivalStart;
               festivalInfo.festivalEnd = festivalInfoList.get(i).festivalEnd;

               db.festivalInfoDao().insertFestivalInfo(festivalInfo);
                */
           }
           //festivalInfoList2 = db.festivalInfoDao().getAllFestivalInfo();
        }
        //리스트 저장
        //festivalInfoAdapter.setFestivalInfoList(festivalInfoList2);
    }

}