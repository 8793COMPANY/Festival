package com.corporation8793.festival;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

public class BoothReservationFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerAdapter4 recyclerAdapter4;
    TextView pointNumText;
    ImageView arrow_left;
    Button productButton;
    //Context context;

    static String point = "";
    static int changePosition;
    static String changePoint;
    static int totalPoint = 0;
    static String result = "";

    //ArrayList<Booth> list = new ArrayList<Booth>();

    private static final String TAG = "BoothReservationFragment";

    public static BoothReservationFragment newInstance() {
        return new BoothReservationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Toast.makeText(getActivity(), "BoothReservationFragment onCreateView 호출", Toast.LENGTH_SHORT).show();
        //  Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booth_reservation, container, false);

        pointNumText = view.findViewById(R.id.pointNumText);
        arrow_left = view.findViewById(R.id.arrow_left);
        recyclerView = view.findViewById(R.id.recyclerView4);
        productButton = view.findViewById(R.id.productButton);

        Bundle bundle = getArguments();
        int fRId = bundle.getInt("축제개별저장", 0);

        SharedPreferences booth = getActivity().getSharedPreferences(String.valueOf(fRId), Activity.MODE_PRIVATE);
        SharedPreferences.Editor boothEdit = booth.edit();

        String fName = booth.getString("rFestival",null);
        String total = booth.getString("total", null);
        /*
        // 적립 포인트 결과
        String [] rBooth = new String[10];
        for(int i=0; i < 10; i++) {
            rBooth[i] = booth.getString("rBooth" + String.valueOf(i), null);
        }*/
        String rBooth1 = booth.getString("rBooth1", null);
        String rBooth2 = booth.getString("rBooth2", null);
        String rBooth3 = booth.getString("rBooth3", null);
        String rBooth4 = booth.getString("rBooth4", null);
        String rBooth5 = booth.getString("rBooth5", null);
        String rBooth6 = booth.getString("rBooth6", null);
        String rBooth7 = booth.getString("rBooth7", null);
        String rBooth8 = booth.getString("rBooth8", null);
        String rBooth9 = booth.getString("rBooth9", null);
        String rBooth10 = booth.getString("rBooth10", null);

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

        if(total != null) {
            pointNumText.setText(total);
            /*
            for(int i=0; i < 10; i++) {
                // 적립 포인트 결과
                recyclerAdapter4.getItem(i).saveResult = rBooth[i];
            }*/
            recyclerAdapter4.getItem(0).saveResult = rBooth1;
            recyclerAdapter4.getItem(1).saveResult = rBooth2;
            recyclerAdapter4.getItem(2).saveResult = rBooth3;
            recyclerAdapter4.getItem(3).saveResult = rBooth4;
            recyclerAdapter4.getItem(4).saveResult = rBooth5;
            recyclerAdapter4.getItem(5).saveResult = rBooth6;
            recyclerAdapter4.getItem(6).saveResult = rBooth7;
            recyclerAdapter4.getItem(7).saveResult = rBooth8;
            recyclerAdapter4.getItem(8).saveResult = rBooth9;
            recyclerAdapter4.getItem(9).saveResult = rBooth10;
            //Toast.makeText(getActivity(), "저장내용 존재", Toast.LENGTH_SHORT).show();
        } else {
            //부스 포인트 적립 결과 초기저장
            boothEdit.putString("total", pointNumText.getText().toString());
            /*
            for(int i=0; i < 10; i++) {
                boothEdit.putString("rBooth" + String.valueOf(i), recyclerAdapter4.getItem(i).saveResult);
            }*/
            boothEdit.putString("rBooth1", recyclerAdapter4.getItem(0).saveResult);
            boothEdit.putString("rBooth2", recyclerAdapter4.getItem(1).saveResult);
            boothEdit.putString("rBooth3", recyclerAdapter4.getItem(2).saveResult);
            boothEdit.putString("rBooth4", recyclerAdapter4.getItem(3).saveResult);
            boothEdit.putString("rBooth5", recyclerAdapter4.getItem(4).saveResult);
            boothEdit.putString("rBooth6", recyclerAdapter4.getItem(5).saveResult);
            boothEdit.putString("rBooth7", recyclerAdapter4.getItem(6).saveResult);
            boothEdit.putString("rBooth8", recyclerAdapter4.getItem(7).saveResult);
            boothEdit.putString("rBooth9", recyclerAdapter4.getItem(8).saveResult);
            boothEdit.putString("rBooth10", recyclerAdapter4.getItem(9).saveResult);
            boothEdit.commit();
            //Toast.makeText(getActivity(), "초기내용", Toast.LENGTH_SHORT).show();
            //Toast.makeText(getActivity(), recyclerAdapter4.getItem(0).saveResult, Toast.LENGTH_SHORT).show();
        }

        recyclerAdapter4.setOnItemClickListener(new RecyclerAdapter4.OnItemClickListener() {
            @Override
            public void onScanClick(View view, int position) {
                Booth item = recyclerAdapter4.getItem(position);

                if(item.saveResult.equals("적립완료")) {
                    Toast.makeText(getActivity(), "이미 적립된 포인트입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), ScanQrActivity.class);
                    intent.putExtra("포인트", item.boothPoint);
                    intent.putExtra("위치", position);

                    String[] splitPoint = item.boothPoint.split("p");
                    changePoint = splitPoint[0];
                    //Toast.makeText(getActivity(), item.boothPoint, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                    getActivity().startActivity(intent);
                }
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

    public void getPoint(String s, int i, String r) {
        point = s;
        changePosition = i;
        result = r;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume : 호출됨");
        Log.e("point", point);
        Log.e("position", String.valueOf(changePosition));

        //changePosition의 부스포인트가 point랑 같을때 saveResult 변경
        if(!point.equals("")) {

            //Toast.makeText(getActivity(), point, Toast.LENGTH_SHORT).show();
            if(point.equals(changePoint)) {
                //Toast.makeText(getActivity(), "부스 포인트가 같습니다.", Toast.LENGTH_SHORT).show();
                Bundle bundle = getArguments();
                int fRId = bundle.getInt("축제개별저장", 0);

                //recyclerAdapter4.notifyItemChanged(changePosition, "payload");
                //Log.e("changeCheck", recyclerAdapter4.getItem(changePosition).saveResult);

                SharedPreferences pref = getActivity().getSharedPreferences("sumFirst : " + String.valueOf(fRId), Activity.MODE_PRIVATE);
                boolean first = pref.getBoolean("sumFirst : " + String.valueOf(fRId), false);
                if(first==false){
                    Log.d("첫계산인가요?", "네");
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("sumFirst : " + String.valueOf(fRId), true);
                    editor.commit();
                    //최초 계산시
                    totalPoint = Integer.valueOf(point);
                }else{
                    Log.d("첫계산인가요?", "아니요");
                    totalPoint = Integer.valueOf(pointNumText.getText().toString()) + Integer.valueOf(point);
                }

                //totalPoint = totalPoint + Integer.valueOf(point);
                pointNumText.setText(String.valueOf(totalPoint));
                point = "";

                //부스 포인트 적립 결과 재저장?
                SharedPreferences booth = getActivity().getSharedPreferences(String.valueOf(fRId), Activity.MODE_PRIVATE);
                SharedPreferences.Editor boothEdit = booth.edit();

                boothEdit.putString("total", String.valueOf(totalPoint));

                for(int i=0; i < 10; i++) {
                    //boothEdit.putString("rBooth" + String.valueOf(i+1), recyclerAdapter4.getItem(i).saveResult);
                    //recyclerAdapter4.getItem(i).saveResult = booth.getString("rBooth" + String.valueOf(i+1), null);
                    if(changePosition == i) {
                        boothEdit.putString("rBooth" + String.valueOf(i+1), "적립완료");
                        boothEdit.commit();
                        recyclerAdapter4.getItem(i).saveResult = booth.getString("rBooth" + String.valueOf(i+1), null);
                        Log.e("check1", "rBooth" + String.valueOf(i+1));
                        Log.e("check2", booth.getString("rBooth" + String.valueOf(i+1), null));
                        recyclerAdapter4.notifyItemChanged(changePosition, "payload");
                        //recyclerView.setAdapter(recyclerAdapter4);
                    }
                }
                //boothEdit.commit();
            } else {
                Toast.makeText(getActivity(), "부스 포인트가 다릅니다.", Toast.LENGTH_SHORT).show();
                point = "";
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
}