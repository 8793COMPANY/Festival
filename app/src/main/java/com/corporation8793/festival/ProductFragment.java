package com.corporation8793.festival;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductFragment extends Fragment {

    ImageView arrow_left;
    TextView pointNumText;

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

        return view;
    }
}