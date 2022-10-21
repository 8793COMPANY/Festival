package com.corporation8793.festival.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.corporation8793.festival.room.AppDatabase;
import com.corporation8793.festival.activity.MainActivity;
import com.corporation8793.festival.listener.OnItemClickListener;
import com.corporation8793.festival.R;
import com.corporation8793.festival.room.Reservation;
import com.corporation8793.festival.adapter.ReservationAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationDetailFragment extends Fragment {

    RecyclerView recyclerView3;
    ReservationAdapter reservationAdapter;
    Context context;
    ImageView arrow_left;
    TextView testText;
    int uid;

    List<Reservation> reservationList = new ArrayList<>();
    List<Reservation> reservationList2 = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservation_detail, container, false);

        context = container.getContext();

        arrow_left = view.findViewById(R.id.arrow_left);
        recyclerView3 = view.findViewById(R.id.recyclerView3);
        testText = view.findViewById(R.id.testText);

        testText.setVisibility(View.GONE);

        recyclerView3.setLayoutManager(new LinearLayoutManager(context));
        reservationAdapter = new ReservationAdapter(context);
        recyclerView3.setAdapter(reservationAdapter);

        Bundle bundle = getArguments();

        if(bundle.getString("예약내역").equals("추가")) {
            uid = bundle.getInt("알람사용자구분");
        }else {
            uid = bundle.getInt("알람사용자구분2");
        }
        //예약 내역 조회
        loadBreakdown();

        if(reservationList.isEmpty()) {
            testText.setVisibility(View.VISIBLE);
        }

        reservationAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ReservationAdapter.ViewHolder holder, View view, int position) {
                Reservation item = reservationAdapter.getItem(position);

                SharedPreferences booth = context.getSharedPreferences(String.valueOf(item.rid), Activity.MODE_PRIVATE);
                SharedPreferences.Editor boothEdit = booth.edit();

                boothEdit.putString("rFestival", item.rFestival);
                boothEdit.commit();

                Bundle bundle1 = new Bundle();
                bundle1.putString("축제이름비교", item.rFestival);
                bundle1.putInt("축제개별저장", item.rid);

                FragmentTransaction transaction = ((MainActivity)context).getSupportFragmentManager().beginTransaction();
                BoothReservationFragment boothReservationFragment = new BoothReservationFragment();
                boothReservationFragment.setArguments(bundle1);
                transaction.replace(R.id.containers,boothReservationFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        arrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }

    //예약 내역 조회
    private void loadBreakdown() {
        //현재 날짜 가져오기
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = simpleDate.format(date);

        //예약 데이터 가져오기
        AppDatabase db = AppDatabase.getDBInstance(this.context);

        reservationList = db.reservationDao().getAllReservation();
        reservationList2.clear();

        //예약 데이터에서 이미 지난 축제 데이터 처리
        for(int i=0; i < reservationList.size(); i++) {
            if(reservationList.get(i).uid == uid) {
                reservationList2.add(reservationList.get(i));
            }
        }

        reservationAdapter.setReservationList(reservationList2);

        for(int i=0; i < reservationList2.size(); i++) {
            String newDate = reservationList2.get(i).rDate;

            //축제 종료일과 현재 비교
            int compare = getTime.compareTo(newDate);

            //이미 지난 축제가 있는 경우
            if(compare > 0) {
                //Toast.makeText(getActivity(), "지난 축제가 있습니다. " + reservationList2.get(i).rFestival, Toast.LENGTH_SHORT).show();
                //recyclerAdapter4.notifyItemChanged(changePosition, "payload");
                //reservationAdapter.notifyItemChanged(i, "payload");
            }
        }
    }
}