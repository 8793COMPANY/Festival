package com.corporation8793.festival;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FestivalInfoFragment extends Fragment {

    ImageView arrow_left, infoImage;
    TextView infoFestivalText, infoFestivalPeriod, infoText, subText, subText2, subText3;
    int[] image;
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
        infoFestivalPeriod = view.findViewById(R.id.infoFestivalPeriod);
        infoText = view.findViewById(R.id.infoText);
        infoImage = view.findViewById(R.id.infoImage);
        reservationButton = view.findViewById(R.id.reservationButton);
        subText = view.findViewById(R.id.subText);
        subText2 = view.findViewById(R.id.subText2);
        subText3 = view.findViewById(R.id.subText3);

        image = new int[] {R.drawable.event_image1, R.drawable.event_image2};
        int imageId = (int)(Math.random() * image.length);

        Bundle bundle = getArguments();

        if(bundle.getString("이동").equals("메인")) {
            infoFestivalText.setText(bundle.getString("이름"));
            infoFestivalPeriod.setText(bundle.getString("기간"));
            infoText.setText(bundle.getString("내용"));
            subText.setText(bundle.getString("위치"));
            subText2.setText(bundle.getString("주최"));
            if(bundle.getString("도로명").isEmpty()) {
                subText3.setText(bundle.getString("지번"));
            } else {
                subText3.setText(bundle.getString("도로명"));
            }
            infoImage.setBackgroundResource(image[imageId]);
            reservationButton.setVisibility(View.GONE);
        } else {
            infoFestivalText.setText(bundle.getString("이름"));
            infoFestivalPeriod.setText(bundle.getString("기간"));
            infoText.setText(bundle.getString("내용"));
            subText.setText(bundle.getString("위치"));
            subText2.setText(bundle.getString("주관"));
            if(bundle.getString("도로명").isEmpty()) {
                subText3.setText(bundle.getString("지번"));
            } else {
                subText3.setText(bundle.getString("도로명"));
            }
            infoImage.setBackgroundResource(image[imageId]);
        }

        /*
        if(bundle != null) {
            name = bundle.getString("축제");
            infoFestivalText.setText(name);
            image = bundle.getInt("이미지");
            infoImage.setBackgroundResource(image);
        } else {
            infoImage.setBackgroundResource(R.drawable.event_image1);
            reservationButton.setVisibility(View.GONE);
        }
         */

        arrow_left = view.findViewById(R.id.arrow_left);
        arrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
                /*
                if(bundle.getString("이동").equals("메인")) {
                    ((MainActivity)getActivity()).fragmentChange(MainFragment.newInstance());
                } else {
                    ((MainActivity)getActivity()).fragmentChange(EventFragment.newInstance());
                }
                 */
            }
        });

        reservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((MainActivity)getActivity()).fragmentChange(ReservationFragment.newInstance());
                FragmentTransaction transaction = ((MainActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                ReservationFragment reservationFragment = new ReservationFragment();

                transaction.replace(R.id.containers,reservationFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }
}