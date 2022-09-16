package com.corporation8793.festival;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FestivalInfoFragment extends Fragment {

    ImageView arrow_left, infoImage;
    ListAdapter2 myPageAdapter2;
    ListView listView;
    TextView infoFestivalText;
    String name;
    int image;
    Button reservationButton;

    public static FestivalInfoFragment newInstance() {
        return new FestivalInfoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_festival_info, container, false);

        infoFestivalText = view.findViewById(R.id.infoFestivalText);
        infoImage = view.findViewById(R.id.infoImage);
        reservationButton = view.findViewById(R.id.reservationButton);
        Bundle bundle = getArguments();

        if(bundle != null) {
            name = bundle.getString("축제");
            infoFestivalText.setText(name);
            image = bundle.getInt("이미지");
            infoImage.setBackgroundResource(image);
        } else {
            infoImage.setBackgroundResource(R.drawable.event_image1);
            reservationButton.setVisibility(View.GONE);
        }

        arrow_left = view.findViewById(R.id.arrow_left);
        arrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bundle != null) {
                    ((MainActivity)getActivity()).fragmentChange(EventFragment.newInstance());
                } else {
                    ((MainActivity)getActivity()).fragmentChange(MainFragment.newInstance());
                }
            }
        });

        listView = view.findViewById(R.id.listView2);
        myPageAdapter2 = new ListAdapter2(getActivity(),2);

        myPageAdapter2.addItem(new MyList2("입장료", "무료"));
        myPageAdapter2.addItem(new MyList2("주관/주최", "보령시/보령축제관광재단"));
        myPageAdapter2.addItem(new MyList2("위치", "광주광역시 동구 동계천로 150"));

        listView.setAdapter(myPageAdapter2);

        reservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).fragmentChange(ReservationFragment.newInstance());
            }
        });

        return view;
    }
}