package com.corporation8793.festival.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.corporation8793.festival.activity.MainActivity;
import com.corporation8793.festival.R;
import com.corporation8793.festival.adapter.FestivalInfoAdapter;
import com.corporation8793.festival.adapter.RankListAdapter;
import com.corporation8793.festival.adapter.FestivalCategoryAdapter;
import com.corporation8793.festival.mclass.Festival;
import com.corporation8793.festival.mclass.RankList;
import com.corporation8793.festival.room.AppDatabase2;
import com.corporation8793.festival.room.FestivalInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class SearchFragment extends Fragment {

    public RecyclerView recyclerView2, recyclerView5;
    FestivalCategoryAdapter festivalCategoryAdapter;
    FestivalInfoAdapter festivalInfoAdapter;
    Context context;
    ListView listView;
    RankListAdapter rankListAdapter;
    public EditText rectangle13;
    ImageView search;
    public TextView testText;

    List<FestivalInfo> festivalInfoList = new ArrayList<>();
    List<FestivalInfo> festivalInfoList2 = new ArrayList<>();
    List<FestivalInfo> festivalInfoList3 = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        context = container.getContext();

        recyclerView2 = view.findViewById(R.id.recyclerView2);
        recyclerView5 = view.findViewById(R.id.recyclerView5);
        listView = view.findViewById(R.id.listView);
        rectangle13 = view.findViewById(R.id.rectangle13);
        search = view.findViewById(R.id.search);
        testText = view.findViewById(R.id.testText);

        recyclerView5.setVisibility((View.GONE));
        testText.setVisibility((View.GONE));

        loadBasic();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager);
        festivalCategoryAdapter = new FestivalCategoryAdapter(getActivity());

        festivalCategoryAdapter.addItem(new Festival("???", R.drawable.search_flower));
        festivalCategoryAdapter.addItem(new Festival("??????", R.drawable.search_fireworks));
        festivalCategoryAdapter.addItem(new Festival("????????????", R.drawable.search_traditional));
        festivalCategoryAdapter.addItem(new Festival("??????", R.drawable.search_music));
        festivalCategoryAdapter.addItem(new Festival("??????", R.drawable.search_beer));
        festivalCategoryAdapter.addItem(new Festival("??????", R.drawable.search_maple));
        festivalCategoryAdapter.addItem(new Festival("??????", R.drawable.search_culture));
        festivalCategoryAdapter.addItem(new Festival("?????????", R.drawable.search_food));

        recyclerView2.setAdapter(festivalCategoryAdapter);

        festivalCategoryAdapter.setOnCategoryClickListener(new FestivalCategoryAdapter.OnCategoryClickListener() {
            @Override
            public void onCategoryClick(View view, int position) {
                Festival item = festivalCategoryAdapter.getItem(position);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                CategoryFragment categoryFragment = new CategoryFragment();

                Bundle bundle = new Bundle();
                if(item.name.equals("?????????")) {
                    bundle.putString("????????????", "??????");
                } else {
                    bundle.putString("????????????", item.name);
                }

                categoryFragment.setArguments(bundle);
                transaction.replace(R.id.containers, categoryFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        rankListAdapter = new RankListAdapter(getActivity());

        loadRankList();

        listView.setAdapter(rankListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RankList item = rankListAdapter.getItem(position);

                clickRankList(item.subText);
            }
        });


        Bundle bundle = getArguments();
        int uid = bundle.getInt("??????????????????");

        recyclerView5.setLayoutManager(new LinearLayoutManager(context));
        festivalInfoAdapter = new FestivalInfoAdapter(context, 3, uid);
        recyclerView5.setAdapter(festivalInfoAdapter);

        rectangle13.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //?????? ???
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //?????? ???
                search.setImageResource(R.drawable.search_on_button_edittext_resize);
                if(rectangle13.getText().toString().equals("")) {
                    search.setImageResource(R.drawable.search_off_button_edittext_resize);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                //?????? ??????
            }
        });

        rectangle13.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_ENTER:
                    {
                        if(rectangle13.getText().toString().equals("")) {
                            Toast.makeText(context, "???????????? ??????????????????", Toast.LENGTH_SHORT).show();
                        } else {
                            recyclerView5.setVisibility((View.VISIBLE));
                            loadUserList(rectangle13.getText().toString());
                            search.setImageResource(R.drawable.search_off_button_edittext_resize);
                        }
                        break;
                    }
                }
                return false;
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rectangle13.getText().toString().equals("")) {
                    Toast.makeText(context, "???????????? ??????????????????", Toast.LENGTH_SHORT).show();
                } else {
                    recyclerView5.setVisibility((View.VISIBLE));
                    loadUserList(rectangle13.getText().toString());
                    search.setImageResource(R.drawable.search_off_button_edittext_resize);
                }
            }
        });

        return view;
    }

    private void loadBasic() {
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
    }

    private void loadUserList(String s1) {
        festivalInfoList3.clear();

        for(int i=0; i < festivalInfoList2.size(); i++) {
            if(festivalInfoList2.get(i).festivalName.contains(s1) || festivalInfoList2.get(i).festivalLocation.contains(s1)
                    || festivalInfoList2.get(i).festivalRdnmadr.contains(s1) || festivalInfoList2.get(i).festivalLnmadr.contains(s1)) {
                festivalInfoList3.add(festivalInfoList2.get(i));
            }
        }

        if(festivalInfoList3.isEmpty()) {
            testText.setVisibility((View.VISIBLE));
        } else {
            testText.setVisibility((View.GONE));
        }
        //????????? ??????
        festivalInfoAdapter.setFestivalInfoList(festivalInfoList3);
    }

    private void loadRankList() {
        String [] festivalNames = new String[festivalInfoList2.size()];
        int [] choiceNames = new int[10];

        for(int i=0; i < festivalInfoList2.size(); i++) {
            festivalNames[i] = festivalInfoList2.get(i).festivalName;
        }

        Random random = new Random();

        for(int i=0; i < choiceNames.length; i++) {
            choiceNames[i] = random.nextInt(festivalNames.length);
            rankListAdapter.addItem(new RankList(String.valueOf(i + 1), festivalNames[choiceNames[i]]));
        }
    }

    private void clickRankList(String s) {
        FragmentTransaction transaction = ((MainActivity)context).getSupportFragmentManager().beginTransaction();
        FestivalInfoFragment festivalInfoFragment = new FestivalInfoFragment();

        Bundle bundle = new Bundle();
        bundle.putString("??????", "??????");

        for(int i=0; i < festivalInfoList2.size(); i++) {
            if(festivalInfoList2.get(i).festivalName.contains(s)) {
                bundle.putString("??????", festivalInfoList2.get(i).festivalName);
                bundle.putString("??????", festivalInfoList2.get(i).festivalStart + "~" + festivalInfoList2.get(i).festivalEnd);
                bundle.putString("??????", festivalInfoList2.get(i).festivalCo);
                bundle.putString("??????", festivalInfoList2.get(i).festivalMnnst);
                bundle.putString("??????", festivalInfoList2.get(i).festivalAuspcInstt);
                bundle.putString("??????", festivalInfoList2.get(i).festivalLocation);
                bundle.putString("?????????", festivalInfoList2.get(i).festivalRdnmadr);
                bundle.putString("??????", festivalInfoList2.get(i).festivalLnmadr);
                bundle.putString("??????", festivalInfoList2.get(i).festivalLatitude);
                bundle.putString("??????", festivalInfoList2.get(i).festivalLongitude);
            }
        }

        festivalInfoFragment.setArguments(bundle);
        transaction.replace(R.id.containers,festivalInfoFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}