package com.corporation8793.festival.fragment;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.corporation8793.festival.room.AppDatabase;
import com.corporation8793.festival.dialog.CustomDialog;
import com.corporation8793.festival.R;
import com.corporation8793.festival.room.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservationFragment extends Fragment {

    public static ReservationFragment newInstance() {
        return new ReservationFragment();
    }

    Spinner yearSpinner, monthSpinner, dateSpinner, personnelSpinner;
    ArrayAdapter<CharSequence> yearSpinner_adapter, monthSpinner_adapter, dateSpinner_adapter, personnelSpinner_adapter;
    Button reservationButton2;
    CustomDialog customDialog;
    ImageView arrow_left;
    String year, month, date, total, num, name;
    int uid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservation, container, false);

        yearSpinner = view.findViewById(R.id.yearSpinner);
        monthSpinner = view.findViewById(R.id.monthSpinner);
        dateSpinner = view.findViewById(R.id.dateSpinner);
        personnelSpinner = view.findViewById(R.id.personnelSpinner);
        reservationButton2 = view.findViewById(R.id.reservationButton2);
        arrow_left = view.findViewById(R.id.arrow_left);

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
                Bundle bundle = getArguments();

                year = yearSpinner.getSelectedItem().toString();
                month = monthSpinner.getSelectedItem().toString();
                date = dateSpinner.getSelectedItem().toString();
                total = year + "-" + month + "-" + date;
                num = personnelSpinner.getSelectedItem().toString();
                name = bundle.getString("예약축제이름");
                uid = bundle.getInt("사용자구별");

                //예약 날짜와 축제 기간 비교
                String period = bundle.getString("예약축제기간");
                String[] splitPeriod = period.split("~");

                long now = System.currentTimeMillis();
                Date date = new Date(now);

                SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
                Date day1, day2, day3;
                String date1, date2, date3, getTime;

                try {
                    day1 = simpleDate.parse(total);
                    day2 = simpleDate.parse(splitPeriod[0]);
                    day3 = simpleDate.parse(splitPeriod[1]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                getTime = simpleDate.format(date);

                //int compare1 = day1.compareTo(day2);
                //int compare2 = day1.compareTo(day3);
                int compare1 = total.compareTo(getTime);
                int compare2 = total.compareTo(splitPeriod[1]);

                if(compare1 >= 0 && compare2 <= 0) {
                    if(!num.equals("0")) {
                        insertReservation(name, total, num, uid);

                        customDialog = new CustomDialog(getActivity(), total, num, name, uid);
                        customDialog.show();

                        Display display = getActivity().getWindowManager().getDefaultDisplay();
                        Point size = new Point();
                        display.getSize(size);

                        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
                        params.width = size.x * 640/720;
                        params.height = size.y * 440/1329;
                        customDialog.getWindow().setAttributes(params);
                        customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    } else {
                        Toast.makeText(getActivity(), "인원수를 선택해주세요.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "축제 기간이 맞지 않습니다.", Toast.LENGTH_SHORT).show();
                }
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

    //예약 내역 추가
    private void insertReservation(String name, String date, String num, int uid) {
        Reservation reservation = new Reservation();
        reservation.rFestival = name;
        reservation.rDate = date;
        reservation.rNum = num;
        reservation.uid = uid;

        AppDatabase db = AppDatabase.getDBInstance(getActivity());
        db.reservationDao().insertReservation(reservation);
    }

}