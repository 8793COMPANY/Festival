package com.corporation8793.festival;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class BoothReservationFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerAdapter4 recyclerAdapter4;
    ImageView arrow_left;
    Button productButton;

    public static BoothReservationFragment newInstance() {
        return new BoothReservationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booth_reservation, container, false);

        arrow_left = view.findViewById(R.id.arrow_left);
        recyclerView = view.findViewById(R.id.recyclerView4);
        productButton = view.findViewById(R.id.productButton);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter4 = new RecyclerAdapter4(getActivity());

        recyclerAdapter4.addItem(new Booth("비누만들기 부스", "운영시간: 12:00~18:00", "260p", "적립완료"));
        recyclerAdapter4.addItem(new Booth("그림그리기 부스", "운영시간: 12:00~18:00", "520p", "적립가능"));
        recyclerAdapter4.addItem(new Booth("머그컵 부스", "운영시간: 12:00~18:00", "300p", "적립완료"));
        recyclerAdapter4.addItem(new Booth("비누만들기 부스", "운영시간: 12:00~18:00", "260p", "적립완료"));
        recyclerAdapter4.addItem(new Booth("그림그리기 부스", "운영시간: 12:00~18:00", "520p", "적립가능"));

        recyclerView.setAdapter(recyclerAdapter4);

        arrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((MainActivity)getActivity()).fragmentChange(BreakdownFragment.newInstance());
                getActivity().onBackPressed();
            }
        });

        productButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}