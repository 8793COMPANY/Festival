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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainFragment extends Fragment {

    RecyclerView recyclerView;
    //RecyclerAdapter recyclerAdapter;
    FestivalInfoAdapter festivalInfoAdapter;
    Spinner choiceMonth, choiceArea;
    ArrayAdapter<CharSequence> choiceMonth_adapter, choiceArea_adapter;
    Context context;
    ImageView searchButton;
    String choiceAreaText;

    List<FestivalInfo> festivalInfoList = new ArrayList<>();
    List<FestivalInfo> festivalInfoList2 = new ArrayList<>();
    List<FestivalInfo> festivalInfoList3 = new ArrayList<>();
    List<FestivalInfo> festivalInfoList4 = new ArrayList<>();

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
        festivalInfoAdapter = new FestivalInfoAdapter(context, 1, 1);
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

        choiceArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(choiceArea.getSelectedItem().toString().equals("경북/대구")) {
                    choiceAreaText = "경상북도/대구광역시";
                    //Toast.makeText(getActivity(), choiceAreaText, Toast.LENGTH_SHORT).show();
                } else if(choiceArea.getSelectedItem().toString().equals("경남/부산")) {
                    choiceAreaText = "경상남도/부산";
                    //Toast.makeText(getActivity(), choiceAreaText, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loadUserList2(choiceMonth.getSelectedItem().toString(), choiceArea.getSelectedItem().toString());
                if(choiceArea.getSelectedItem().toString().equals("강원도") || choiceArea.getSelectedItem().toString().equals("제주도")) {
                    String[] splitMonth = choiceMonth.getSelectedItem().toString().split("월");

                    if(splitMonth[0].equals("10") || splitMonth[0].equals("11") || splitMonth[0].equals("12")) {
                        loadUserList2(splitMonth[0], choiceArea.getSelectedItem().toString());
                    } else {
                        loadUserList2("0" + splitMonth[0], choiceArea.getSelectedItem().toString());
                    }
                } else if(choiceArea.getSelectedItem().toString().equals("경북/대구") || choiceArea.getSelectedItem().toString().equals("경남/부산")) {
                    //Toast.makeText(getActivity(), choiceAreaText, Toast.LENGTH_SHORT).show();
                    String[] splitMonth = choiceMonth.getSelectedItem().toString().split("월");
                    String[] splitArea = choiceAreaText.toString().split("/");

                    if(splitMonth[0].equals("10") || splitMonth[0].equals("11") || splitMonth[0].equals("12")) {
                        loadUserList3(splitMonth[0], splitArea[0], splitArea[1]);
                    } else {
                        loadUserList3("0" + splitMonth[0], splitArea[0], splitArea[1]);
                    }
                } else {
                    String[] splitMonth = choiceMonth.getSelectedItem().toString().split("월");
                    String[] splitArea = choiceArea.getSelectedItem().toString().split("/");

                    if(splitMonth[0].equals("10") || splitMonth[0].equals("11") || splitMonth[0].equals("12")) {
                        loadUserList3(splitMonth[0], splitArea[0], splitArea[1]);
                    } else {
                            loadUserList3("0" + splitMonth[0], splitArea[0], splitArea[1]);
                    }
                }
            }
        });

        return view;
    }

    private void loadUserList() {
        AppDatabase2 db  = AppDatabase2.getDBInstance(this.getActivity());

        festivalInfoList = db.festivalInfoDao().getAllFestivalInfo();
        festivalInfoList2.clear();

        //festivalInfoList > 1056개
        //Toast.makeText(getActivity(), String.valueOf(festivalInfoList.size()), Toast.LENGTH_SHORT).show();

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
        //festivalInfoList2 > 246개
        //Toast.makeText(getActivity(), String.valueOf(festivalInfoList2.size()), Toast.LENGTH_SHORT).show();
        //리스트 저장
        festivalInfoAdapter.setFestivalInfoList(festivalInfoList2);
    }

    private void loadUserList2(String s1, String s2) {
        festivalInfoList3.clear();
        //Toast.makeText(getActivity(), s1, Toast.LENGTH_SHORT).show();
        //String[] splitMonth = festivalInfoList.get(1).festivalStart.toString().split("-");
        //Toast.makeText(getActivity(), splitMonth[1], Toast.LENGTH_SHORT).show();

        for(int i=0; i < festivalInfoList2.size(); i++) {

            if(festivalInfoList2.get(i).festivalLocation.contains(s2) || festivalInfoList2.get(i).festivalRdnmadr.contains(s2)
                    || festivalInfoList2.get(i).festivalLnmadr.contains(s2)) {
                String[] splitMonth = festivalInfoList2.get(i).festivalStart.toString().split("-");

                if(splitMonth[1].equals(s1)) {
                    festivalInfoList3.add(festivalInfoList2.get(i));
                }
            }
        }

        if(festivalInfoList3.isEmpty()) {
            Toast.makeText(getActivity(), "축제가 없습니다", Toast.LENGTH_SHORT).show();
        }
        //리스트 저장
        festivalInfoAdapter.setFestivalInfoList(festivalInfoList3);
    }

    private void loadUserList3(String s1, String s2, String s3) {
        festivalInfoList4.clear();

        //Toast.makeText(getActivity(), s2, Toast.LENGTH_SHORT).show();

        for(int i=0; i < festivalInfoList2.size(); i++) {

            if(festivalInfoList2.get(i).festivalLocation.contains(s2) || festivalInfoList2.get(i).festivalLocation.contains(s3)
                    || festivalInfoList2.get(i).festivalRdnmadr.contains(s2) || festivalInfoList2.get(i).festivalRdnmadr.contains(s3)
                    || festivalInfoList2.get(i).festivalLnmadr.contains(s2) || festivalInfoList2.get(i).festivalLnmadr.contains(s3))  {
                String[] splitMonth = festivalInfoList2.get(i).festivalStart.toString().split("-");

                if(splitMonth[1].equals(s1)) {
                    festivalInfoList4.add(festivalInfoList2.get(i));
                }
            }
        }

        if(festivalInfoList4.isEmpty()) {
            Toast.makeText(getActivity(), "축제가 없습니다", Toast.LENGTH_SHORT).show();
        }
        //리스트 저장
        festivalInfoAdapter.setFestivalInfoList(festivalInfoList4);
    }

}