package com.corporation8793.festival;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class EventFragment extends Fragment {

    RecyclerView recyclerView2;
    RecyclerAdapter recyclerAdapter2;
    Spinner choiceMonth2, choiceArea2;
    ArrayAdapter<CharSequence> choiceMonth_adapter2, choiceArea_adapter2;

    public static EventFragment newInstance() {
        return new EventFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        recyclerView2 = view.findViewById(R.id.recyclerView2);
        choiceMonth2 = view.findViewById(R.id.choiceMonth2);
        choiceArea2 = view.findViewById(R.id.choiceArea2);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerAdapter2 = new RecyclerAdapter(getActivity());

        recyclerAdapter2.addItem(new Festival("2022.09.01~09.12", "광주 맥주 축제", R.drawable.festival_image_1));
        recyclerAdapter2.addItem(new Festival("2022.09.14~09.20", "가을 꽃 축제", R.drawable.festival_image_2));
        recyclerAdapter2.addItem(new Festival("2022.09.31", "3", R.drawable.festival_image_3));
        recyclerAdapter2.addItem(new Festival("4", "4", R.drawable.festival_image_1));
        recyclerAdapter2.addItem(new Festival("5", "5", R.drawable.festival_image_2));

        recyclerView2.setAdapter(recyclerAdapter2);

        recyclerAdapter2.setOnItemClicklistener(new OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, View view, int position) {
                Festival item = recyclerAdapter2.getItem(position);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                FestivalInfoFragment festivalInfoFragment = new FestivalInfoFragment();

                Bundle bundle = new Bundle();
                bundle.putString("축제","광주 불꽃 축제");
                bundle.putInt("이미지", R.drawable.event_image2);

                festivalInfoFragment.setArguments(bundle);
                transaction.replace(R.id.containers,festivalInfoFragment);
                transaction.commit();

                //((MainActivity)getActivity()).fragmentChange(FestivalInfoFragment.newInstance());
            }
        });


        choiceMonth_adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.month_array, R.layout.spinner_item);
        choiceMonth_adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        choiceMonth2.setAdapter(choiceMonth_adapter2);

        choiceMonth2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //((TextView));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        choiceArea_adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.area_array, R.layout.spinner_item);
        choiceArea_adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        choiceArea2.setAdapter(choiceArea_adapter2);

        return view;
    }
}