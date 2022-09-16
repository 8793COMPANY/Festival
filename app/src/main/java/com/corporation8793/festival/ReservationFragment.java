package com.corporation8793.festival;

import android.graphics.Point;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ReservationFragment extends Fragment {

    public static ReservationFragment newInstance() {
        return new ReservationFragment();
    }

    Spinner yearSpinner, monthSpinner, dateSpinner, personnelSpinner;
    ArrayAdapter<CharSequence> yearSpinner_adapter, monthSpinner_adapter, dateSpinner_adapter, personnelSpinner_adapter;
    Button reservationButton2;
    CustomDialog customDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservation, container, false);

        yearSpinner = view.findViewById(R.id.yearSpinner);
        monthSpinner = view.findViewById(R.id.monthSpinner);
        dateSpinner = view.findViewById(R.id.dateSpinner);
        personnelSpinner = view.findViewById(R.id.personnelSpinner);
        reservationButton2 = view.findViewById(R.id.reservationButton2);

        yearSpinner_adapter = ArrayAdapter.createFromResource(getActivity(), R.array.year, R.layout.spinner_item3);
        yearSpinner_adapter.setDropDownViewResource(R.layout.spinner_dropdown_item3);
        yearSpinner.setAdapter(yearSpinner_adapter);

        monthSpinner_adapter = ArrayAdapter.createFromResource(getActivity(), R.array.month, R.layout.spinner_item3);
        monthSpinner_adapter.setDropDownViewResource(R.layout.spinner_dropdown_item3);
        monthSpinner.setAdapter(monthSpinner_adapter);

        dateSpinner_adapter = ArrayAdapter.createFromResource(getActivity(), R.array.date, R.layout.spinner_item3);
        dateSpinner_adapter.setDropDownViewResource(R.layout.spinner_dropdown_item3);
        dateSpinner.setAdapter(dateSpinner_adapter);

        personnelSpinner_adapter = ArrayAdapter.createFromResource(getActivity(), R.array.personnel, R.layout.spinner_item3);
        personnelSpinner_adapter.setDropDownViewResource(R.layout.spinner_dropdown_item3);
        personnelSpinner.setAdapter(personnelSpinner_adapter);

        reservationButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog = new CustomDialog(getActivity(),"d");
                customDialog.show();

                Display display = getActivity().getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);

                WindowManager.LayoutParams params = new WindowManager.LayoutParams();
                params.width = size.x * 640/720;
                params.height = size.y * 540/1329;
                customDialog.getWindow().setAttributes(params);
                //customDialog.callDialog();
                /*
                WindowManager.LayoutParams params = new WindowManager.LayoutParams();
                params = customDialog.getWindow().getAttributes();
                params.width = 1000;
                params.height = 670;
                customDialog.getWindow().setAttributes(params);
                 */
            }
        });

        return view;
    }

}