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

public class EventFragment extends Fragment {

    RecyclerView recyclerView2;
    //RecyclerAdapter recyclerAdapter2;
    FestivalInfoAdapter festivalInfoAdapter;
    Spinner choiceMonth2, choiceArea2;
    ArrayAdapter<CharSequence> choiceMonth_adapter2, choiceArea_adapter2;
    Context context;
    ImageView searchButton2;
    String choiceAreaText;

    List<FestivalInfo> festivalInfoList = new ArrayList<>();
    List<FestivalInfo> festivalInfoList2 = new ArrayList<>();
    List<FestivalInfo> festivalInfoList3 = new ArrayList<>();
    List<FestivalInfo> festivalInfoList4 = new ArrayList<>();

    public static EventFragment newInstance() {
        return new EventFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        context = container.getContext();

        recyclerView2 = view.findViewById(R.id.recyclerView2);
        choiceMonth2 = view.findViewById(R.id.choiceMonth2);
        choiceArea2 = view.findViewById(R.id.choiceArea2);
        searchButton2 = view.findViewById(R.id.searchButton2);

        Bundle bundle = getArguments();
        int uid = bundle.getInt("메인예약구별");

        recyclerView2.setLayoutManager(new LinearLayoutManager(context));
        festivalInfoAdapter = new FestivalInfoAdapter(context, 2, uid);
        recyclerView2.setAdapter(festivalInfoAdapter);

        //축제 조회
        loadUserList();

        choiceMonth_adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.month_array, R.layout.spinner_item);
        choiceMonth_adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        choiceMonth2.setAdapter(choiceMonth_adapter2);

        choiceMonth2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        choiceArea_adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.area_array, R.layout.spinner_item);
        choiceArea_adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        choiceArea2.setAdapter(choiceArea_adapter2);

        choiceArea2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(choiceArea2.getSelectedItem().toString().equals("경북/대구")) {
                    choiceAreaText = "경상북도/대구광역시";
                } else if(choiceArea2.getSelectedItem().toString().equals("경남/부산")) {
                    choiceAreaText = "경상남도/부산";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        searchButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choiceArea2.getSelectedItem().toString().equals("강원도") || choiceArea2.getSelectedItem().toString().equals("제주도")) {
                    String[] splitMonth = choiceMonth2.getSelectedItem().toString().split("월");

                    if(splitMonth[0].equals("10") || splitMonth[0].equals("11") || splitMonth[0].equals("12")) {
                        loadUserList2(splitMonth[0], choiceArea2.getSelectedItem().toString());
                    } else {
                        loadUserList2("0" + splitMonth[0], choiceArea2.getSelectedItem().toString());
                    }
                } else if(choiceArea2.getSelectedItem().toString().equals("경북/대구") || choiceArea2.getSelectedItem().toString().equals("경남/부산")) {
                    String[] splitMonth = choiceMonth2.getSelectedItem().toString().split("월");
                    String[] splitArea = choiceAreaText.toString().split("/");

                    if(splitMonth[0].equals("10") || splitMonth[0].equals("11") || splitMonth[0].equals("12")) {
                        loadUserList3(splitMonth[0], splitArea[0], splitArea[1]);
                    } else {
                        loadUserList3("0" + splitMonth[0], splitArea[0], splitArea[1]);
                    }
                } else {
                    String[] splitMonth = choiceMonth2.getSelectedItem().toString().split("월");
                    String[] splitArea = choiceArea2.getSelectedItem().toString().split("/");

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
        //리스트 저장
        festivalInfoAdapter.setFestivalInfoList(festivalInfoList2);
    }

    private void loadUserList2(String s1, String s2) {
        festivalInfoList3.clear();

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