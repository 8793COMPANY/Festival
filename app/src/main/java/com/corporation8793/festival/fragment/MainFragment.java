package com.corporation8793.festival.fragment;

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

import com.corporation8793.festival.R;
import com.corporation8793.festival.adapter.FestivalInfoAdapter;
import com.corporation8793.festival.mclass.NDSpinner;
import com.corporation8793.festival.room.AppDatabase2;
import com.corporation8793.festival.room.FestivalInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainFragment extends Fragment {

    RecyclerView recyclerView;
    FestivalInfoAdapter festivalInfoAdapter;
    public NDSpinner choiceMonth, choiceArea;
    ArrayAdapter<CharSequence> choiceMonth_adapter, choiceArea_adapter;
    Context context;
    public ImageView searchButton;
    String choiceAreaText;

    List<FestivalInfo> festivalInfoList = new ArrayList<>();
    List<FestivalInfo> festivalInfoList2 = new ArrayList<>();
    List<FestivalInfo> festivalInfoList3 = new ArrayList<>();
    List<FestivalInfo> festivalInfoList4 = new ArrayList<>();

    public static String check = "";
    public static String check2 = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        context = container.getContext();

        recyclerView = view.findViewById(R.id.recyclerView);
        choiceMonth = view.findViewById(R.id.choiceMonth);
        choiceArea = view.findViewById(R.id.choiceArea);
        searchButton = view.findViewById(R.id.searchButton);
        searchButton.setBackgroundResource(R.drawable.search_off_button_resize);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        festivalInfoAdapter = new FestivalInfoAdapter(context, 1, 1);
        recyclerView.setAdapter(festivalInfoAdapter);

        //?????? ??????
        loadUserList();

        choiceMonth_adapter = ArrayAdapter.createFromResource(getActivity(), R.array.month_array, R.layout.item_spinner_main);
        choiceMonth_adapter.setDropDownViewResource(R.layout.item_spinner_dropdown_main);
        choiceMonth.setAdapter(choiceMonth_adapter);

        choiceMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!check.equals("")) {
                    searchButton.setBackgroundResource(R.drawable.search_on_button_spinner_resize);
                } else {
                    check = "ok";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        choiceMonth.setSpinnerEventsListener(new NDSpinner.OnSpinnerEventsListener() {
            @Override
            public void onSpinnerOpened(Spinner spinner) {
                choiceMonth.setBackgroundResource(R.drawable.custom_spinner4);
            }
            @Override
            public void onSpinnerClosed(Spinner spinner) {
                choiceMonth.setBackgroundResource(R.drawable.custom_spinner3);
            }
        });

        choiceArea_adapter = ArrayAdapter.createFromResource(getActivity(), R.array.area_array, R.layout.item_spinner_main);
        choiceArea_adapter.setDropDownViewResource(R.layout.item_spinner_dropdown_main);
        choiceArea.setAdapter(choiceArea_adapter);

        choiceArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!check2.equals("")) {
                    searchButton.setBackgroundResource(R.drawable.search_on_button_spinner_resize);
                } else {
                    check2 = "ok";
                }

                if(choiceArea.getSelectedItem().toString().equals("??????/??????")) {
                    choiceAreaText = "????????????/???????????????";
                } else if(choiceArea.getSelectedItem().toString().equals("??????/??????")) {
                    choiceAreaText = "????????????/??????";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        choiceArea.setSpinnerEventsListener(new NDSpinner.OnSpinnerEventsListener() {
            @Override
            public void onSpinnerOpened(Spinner spinner) {
                choiceArea.setBackgroundResource(R.drawable.custom_spinner4);
            }
            @Override
            public void onSpinnerClosed(Spinner spinner) {
                choiceArea.setBackgroundResource(R.drawable.custom_spinner3);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchButton.setBackgroundResource(R.drawable.search_off_button_resize);

                if(choiceArea.getSelectedItem().toString().equals("?????????") || choiceArea.getSelectedItem().toString().equals("?????????")) {
                    if(choiceMonth.getSelectedItem().toString().equals("??????")) {
                        loadUserList6(choiceArea.getSelectedItem().toString(), "");
                    } else {
                        String[] splitMonth = choiceMonth.getSelectedItem().toString().split("???");

                        if(splitMonth[0].equals("10") || splitMonth[0].equals("11") || splitMonth[0].equals("12")) {
                            loadUserList2(splitMonth[0], choiceArea.getSelectedItem().toString());
                        } else {
                            loadUserList2("0" + splitMonth[0], choiceArea.getSelectedItem().toString());
                        }
                    }
                } else if(choiceArea.getSelectedItem().toString().equals("??????/??????") || choiceArea.getSelectedItem().toString().equals("??????/??????")) {
                    if(choiceMonth.getSelectedItem().toString().equals("??????")) {
                        String[] splitArea = choiceAreaText.toString().split("/");

                        loadUserList6(splitArea[0], splitArea[1]);
                    } else {
                        String[] splitMonth = choiceMonth.getSelectedItem().toString().split("???");
                        String[] splitArea = choiceAreaText.toString().split("/");

                        if(splitMonth[0].equals("10") || splitMonth[0].equals("11") || splitMonth[0].equals("12")) {
                            loadUserList3(splitMonth[0], splitArea[0], splitArea[1]);
                        } else {
                            loadUserList3("0" + splitMonth[0], splitArea[0], splitArea[1]);
                        }
                    }
                } else if(choiceArea.getSelectedItem().toString().equals("??????") && !choiceMonth.getSelectedItem().toString().equals("??????")) {
                    String[] splitMonth = choiceMonth.getSelectedItem().toString().split("???");

                    if(splitMonth[0].equals("10") || splitMonth[0].equals("11") || splitMonth[0].equals("12")) {
                        loadUserList5(splitMonth[0]);
                    } else {
                        loadUserList5("0" + splitMonth[0]);
                    }

                } else if(!choiceArea.getSelectedItem().toString().equals("??????") && choiceMonth.getSelectedItem().toString().equals("??????")) {
                    String[] splitArea = choiceArea.getSelectedItem().toString().split("/");

                    loadUserList6(splitArea[0], splitArea[1]);

                } else if(choiceArea.getSelectedItem().toString().equals("??????") && choiceMonth.getSelectedItem().toString().equals("??????")) {
                    loadUserList();
                } else {
                    String[] splitMonth = choiceMonth.getSelectedItem().toString().split("???");
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

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = simpleDate.format(date);

        for(int i=0; i < festivalInfoList.size(); i++) {
            int compare = getTime.compareTo(festivalInfoList.get(i).festivalEnd);
            //?????? ????????? ?????? ?????????????????? ???????????? ???????????? ??????
            if(compare < 0) {
                festivalInfoList2.add(festivalInfoList.get(i));
            }
        }
        //????????? ??????
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
            Toast.makeText(getActivity(), "????????? ????????????", Toast.LENGTH_SHORT).show();
        }
        //????????? ??????
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
            Toast.makeText(getActivity(), "????????? ????????????", Toast.LENGTH_SHORT).show();
        }
        //????????? ??????
        festivalInfoAdapter.setFestivalInfoList(festivalInfoList4);
    }
    //????????? ???????????? ????????? ????????? ????????????
    private void loadUserList5(String s1) {
        festivalInfoList3.clear();

        for(int i=0; i < festivalInfoList2.size(); i++) {
            String[] splitMonth = festivalInfoList2.get(i).festivalStart.toString().split("-");

            if(splitMonth[1].equals(s1)) {
                festivalInfoList3.add(festivalInfoList2.get(i));
            }
        }

        if(festivalInfoList3.isEmpty()) {
            Toast.makeText(getActivity(), "????????? ????????????", Toast.LENGTH_SHORT).show();
        }
        //????????? ??????
        festivalInfoAdapter.setFestivalInfoList(festivalInfoList3);
    }
    //????????? ?????????????????? ????????? ????????? ??????
    private void loadUserList6(String s1, String s2) {
        festivalInfoList3.clear();

        if(s2.equals("")) {     //???????????? ???????????? ??????
            for(int i=0; i < festivalInfoList2.size(); i++) {
                if(festivalInfoList2.get(i).festivalLocation.contains(s1) || festivalInfoList2.get(i).festivalRdnmadr.contains(s1) ||
                        festivalInfoList2.get(i).festivalLnmadr.contains(s1))  {
                    festivalInfoList3.add(festivalInfoList2.get(i));
                }
            }
        } else {    //??? ?????? ??????
            for(int i=0; i < festivalInfoList2.size(); i++) {
                if(festivalInfoList2.get(i).festivalLocation.contains(s1) || festivalInfoList2.get(i).festivalLocation.contains(s2)
                        || festivalInfoList2.get(i).festivalRdnmadr.contains(s1) || festivalInfoList2.get(i).festivalRdnmadr.contains(s2)
                        || festivalInfoList2.get(i).festivalLnmadr.contains(s1) || festivalInfoList2.get(i).festivalLnmadr.contains(s2))  {
                    festivalInfoList3.add(festivalInfoList2.get(i));
                }
            }
        }

        if(festivalInfoList3.isEmpty()) {
            Toast.makeText(getActivity(), "????????? ????????????", Toast.LENGTH_SHORT).show();
        }
        //????????? ??????
        festivalInfoAdapter.setFestivalInfoList(festivalInfoList3);
    }
}