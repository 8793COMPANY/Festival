package com.corporation8793.festival;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BoothReservationFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerAdapter4 recyclerAdapter4;
    TextView pointNumText;
    ImageView arrow_left;
    Button productButton;
    Context context;

    static String point = "";
    static int changePosition;
    static String changePoint;
    static int totalPoint = 0;

    //ArrayList<Booth> list = new ArrayList<Booth>();

    private static final String TAG = "BoothReservationFragment";

    public static BoothReservationFragment newInstance() {
        return new BoothReservationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Toast.makeText(getActivity(), "BoothReservationFragment onCreateView 호출", Toast.LENGTH_SHORT).show();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booth_reservation, container, false);

        pointNumText = view.findViewById(R.id.pointNumText);
        arrow_left = view.findViewById(R.id.arrow_left);
        recyclerView = view.findViewById(R.id.recyclerView4);
        productButton = view.findViewById(R.id.productButton);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter4 = new RecyclerAdapter4(getActivity());

        recyclerAdapter4.addItem(new Booth("비누만들기 부스", "운영시간: 12:00~18:00", "100p", "적립가능"));
        recyclerAdapter4.addItem(new Booth("그림그리기 부스", "운영시간: 12:00~18:00", "200p", "적립가능"));
        recyclerAdapter4.addItem(new Booth("머그컵 부스", "운영시간: 12:00~18:00", "300p", "적립가능"));
        recyclerAdapter4.addItem(new Booth("비누만들기 부스", "운영시간: 12:00~18:00", "400p", "적립가능"));
        recyclerAdapter4.addItem(new Booth("과녁맞추기 부스", "운영시간: 12:00~18:00", "500p", "적립가능"));
        recyclerAdapter4.addItem(new Booth("퍼스널컬러 부스", "운영시간: 12:00~18:00", "600p", "적립가능"));
        recyclerAdapter4.addItem(new Booth("아두이노 부스", "운영시간: 12:00~18:00", "700p", "적립가능"));
        recyclerAdapter4.addItem(new Booth("향초만들기 부스", "운영시간: 12:00~18:00", "800p", "적립가능"));
        recyclerAdapter4.addItem(new Booth("보드게임 부스", "운영시간: 12:00~18:00", "900p", "적립가능"));
        recyclerAdapter4.addItem(new Booth("종이접기 부스", "운영시간: 12:00~18:00", "1000p", "적립가능"));

        recyclerView.setAdapter(recyclerAdapter4);

        SharedPreferences booth = context.getSharedPreferences("booth", Activity.MODE_PRIVATE);
        SharedPreferences.Editor boothEdit = booth.edit();

        boothEdit.commit();

        recyclerAdapter4.setOnItemClickListener(new RecyclerAdapter4.OnItemClickListener() {
            @Override
            public void onScanClick(View view, int position) {
                Booth item = recyclerAdapter4.getItem(position);

                Intent intent = new Intent(getActivity(), ScanQrActivity.class);
                intent.putExtra("포인트", item.boothPoint);
                intent.putExtra("위치", position);

                String[] splitPoint = item.boothPoint.split("p");
                changePoint = splitPoint[0];
                //Toast.makeText(getActivity(), item.boothPoint, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                getActivity().startActivity(intent);
            }
        });

        recyclerAdapter4.setOnItemClicklistener(new OnItemClickListener2() {
            @Override
            public void onItemClick(RecyclerAdapter4.ViewHolder holder, View view, int position) {
            }
        });

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

    public void getPoint(String s, int i) {
        point = s;
        changePosition = i;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume : 호출됨");
        Log.e("point", point);
        Log.e("position", String.valueOf(changePosition));

        //changePosition의 부스포인트가 point랑 같을때 saveResult 변경
        if(!point.equals("")) {
            Toast.makeText(getActivity(), point, Toast.LENGTH_SHORT).show();
            if(point.equals(changePoint)) {
                //Toast.makeText(getActivity(), "부스 포인트가 같습니다.", Toast.LENGTH_SHORT).show();
                recyclerAdapter4.notifyItemChanged(changePosition, "payload");
                totalPoint = totalPoint + Integer.valueOf(point);
                pointNumText.setText(String.valueOf(totalPoint));
            } else {
                Toast.makeText(getActivity(), "부스 포인트가 다릅니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause : 호출됨");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop : 호출됨");
    }
    /*
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onStop : 호출됨");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onStop : 호출됨");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onStop : 호출됨");
    }*/
}