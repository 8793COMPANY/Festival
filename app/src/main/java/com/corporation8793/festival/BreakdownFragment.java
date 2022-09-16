package com.corporation8793.festival;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BreakdownFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerAdapter3 recyclerAdapter3;
    Context context;
    ImageView arrow_left;

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
        });

        arrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).fragmentChange(MyPageFragment.newInstance());
            }
        });

        return view;
    }
}