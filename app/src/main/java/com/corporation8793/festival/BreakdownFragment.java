package com.corporation8793.festival;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class BreakdownFragment extends Fragment {

    RecyclerView recyclerView;
    //RecyclerAdapter3 recyclerAdapter3;
    ReservationAdapter reservationAdapter;
    Context context;
    ImageView arrow_left;

    List<Reservation> reservationList = new ArrayList<>();

    public static BreakdownFragment newInstance() {
        return new BreakdownFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_breakdown, container, false);

        context = container.getContext();

        arrow_left = view.findViewById(R.id.arrow_left);
        recyclerView = view.findViewById(R.id.recyclerView3);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        reservationAdapter = new ReservationAdapter(context);
        recyclerView.setAdapter(reservationAdapter);

        Bundle bundle = getArguments();
        if(bundle.getString("예약내역").equals("추가")) {
            //예약 내역 추가
            insertReservation(bundle.getString("알람예약축제이름"), bundle.getString("예약날짜"), bundle.getString("예약인원"));
        }
        //예약 내역 조회
        loadBreakdown();

        reservationAdapter.setOnItemClickListener(new OnItemClickListener3() {
            @Override
            public void onItemClick(ReservationAdapter.ViewHolder holder, View view, int position) {
                //Reservation item = reservationAdapter.getItem(position);

                FragmentTransaction transaction = ((MainActivity)context).getSupportFragmentManager().beginTransaction();
                BoothReservationFragment boothReservationFragment = new BoothReservationFragment();
                transaction.replace(R.id.containers,boothReservationFragment);
                transaction.addToBackStack(null);
                transaction.commit();

                //((MainActivity)getActivity()).fragmentChange(BoothReservationFragment.newInstance());
            }
        });


        //bundle.putString("예약날짜", total);
        //bundle.putString("예약인원", num + "명");
        //bundle.putString("알람예약축제이름", name);

        /*
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter3 = new RecyclerAdapter3(context);

        recyclerAdapter3.addItem(new Festival("2022.06.14 17:30 | 2명", "서울장미축제", R.drawable.mypage_festival));
        recyclerAdapter3.addItem(new Festival("2022.06.14 17:30 | 2명", "세계불꽃축제", R.drawable.mypage_festival2));
        recyclerAdapter3.addItem(new Festival("2022.06.14 17:30 | 2명", "서울장미축제", R.drawable.mypage_festival));
        recyclerAdapter3.addItem(new Festival("2022.06.14 17:30 | 2명", "세계불꽃축제", R.drawable.mypage_festival2));
        recyclerAdapter3.addItem(new Festival("2022.06.14 17:30 | 2명", "서울장미축제", R.drawable.mypage_festival));

        recyclerView.setAdapter(recyclerAdapter3);

        recyclerAdapter3.setOnItemClicklistener(new OnItemClickListener2() {
            @Override
            public void onItemClick(RecyclerAdapter3.ViewHolder holder, View view, int position) {
                Festival item = recyclerAdapter3.getItem(position);
                ((MainActivity)getActivity()).fragmentChange(BoothReservationFragment.newInstance());
            }
        });*/

        arrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((MainActivity)getActivity()).fragmentChange(MyPageFragment.newInstance());
                getActivity().onBackPressed();
            }
        });

        return view;
    }
    //예약 내역 추가
    private void insertReservation(String name, String date, String num) {
        Reservation reservation = new Reservation();
        reservation.rFestival = name;
        reservation.rDate = date;
        reservation.rNum = num;

        AppDatabase db = AppDatabase.getDBInstance(this.context);
        db.reservationDao().insertReservation(reservation);
    }
    //예약 내역 조회
    private void loadBreakdown() {
        AppDatabase db = AppDatabase.getDBInstance(this.context);

        reservationList = db.reservationDao().getAllReservation();

        reservationAdapter.setReservationList(reservationList);
    }
}