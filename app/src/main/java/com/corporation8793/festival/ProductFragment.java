package com.corporation8793.festival;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductFragment extends Fragment {

    ImageView arrow_left;
    TextView pointNumText;

    RecyclerView recyclerView;
    RecyclerAdapter5 recyclerAdapter5;

    static String point = "";
    static String checkPoint;
    static String resultPoint;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        pointNumText = view.findViewById(R.id.pointNumText);

        arrow_left = view.findViewById(R.id.arrow_left);
        arrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        Bundle bundle = getArguments();
        pointNumText.setText(bundle.getString("적립포인트"));

        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter5 = new RecyclerAdapter5(getActivity());

        recyclerAdapter5.addItem(new Product("캐릭터 그립톡", "1000P"));
        recyclerAdapter5.addItem(new Product("축제 미니캔들", "500P"));
        recyclerAdapter5.addItem(new Product("축제 머그컵", "1000P"));
        recyclerAdapter5.addItem(new Product("축제 엽서", "300P"));
        recyclerAdapter5.addItem(new Product("캐릭터 키링", "1500P"));
        recyclerAdapter5.addItem(new Product("축제 마그넷", "500P"));

        recyclerView.setAdapter(recyclerAdapter5);

        recyclerAdapter5.setOnItemClickListener(new RecyclerAdapter5.OnItemClickListener() {
            @Override
            public void onScanClick(View view, int position) {
                Product item = recyclerAdapter5.getItem(position);

                String[] splitPoint = item.productPoint.split("P");
                checkPoint = splitPoint[0];

                Intent intent = new Intent(getActivity(), ScanQrActivity2.class);
                intent.putExtra("포인트", checkPoint);
                intent.putExtra("적립포인트", pointNumText.getText().toString());

                getActivity().startActivity(intent);
            }
        });

        return view;
    }

    public void getPoint(String s1, String s2) {
        point = s1;
        resultPoint = s2;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("ProductFragment", "onResume : 호출됨");
        Log.e("상품 스캔된 포인트", point);

        if(!point.equals("")) {
            if(point.equals(checkPoint)) {
                pointNumText.setText(resultPoint);

                BoothReservationFragment boothReservationFragment = new BoothReservationFragment();
                boothReservationFragment.setPoint(resultPoint);

                Toast.makeText(getActivity(), "포인트가 차감되었습니다.", Toast.LENGTH_SHORT).show();

                point = "";
            } else {
                Toast.makeText(getActivity(), "포인트가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                point = "";
            }
        }
    }
}