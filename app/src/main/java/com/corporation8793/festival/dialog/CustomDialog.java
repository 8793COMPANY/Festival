package com.corporation8793.festival.dialog;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.corporation8793.festival.activity.MainActivity;
import com.corporation8793.festival.R;
import com.corporation8793.festival.fragment.EventFragment;
import com.corporation8793.festival.fragment.ReservationDetailFragment;

public class CustomDialog extends Dialog {

    TextView dialogDateText, dialogNumText;
    Button okButton, closeButton;
    Context context2;
    int userId;

    public CustomDialog(@NonNull Context context, String total, String num, String name, int uid) {
        super(context);
        setContentView(R.layout.activity_custom_dialog);

        dialogDateText = findViewById(R.id.dialogDateText);
        dialogNumText = findViewById(R.id.dialogNumText);

        dialogDateText.setText("예약날짜: " + total);
        dialogNumText.setText("예약인원: " + num + "명");

        context2 = context;
        userId = uid;

        okButton = findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = ((MainActivity)context).getSupportFragmentManager().beginTransaction();
                ReservationDetailFragment reservationDetailFragment = new ReservationDetailFragment();

                Bundle bundle = new Bundle();
                bundle.putString("예약내역", "추가");
                bundle.putString("예약날짜", total);
                bundle.putString("예약인원", num + "명");
                bundle.putString("알람예약축제이름", name);
                bundle.putInt("알람사용자구분", uid);

                reservationDetailFragment.setArguments(bundle);
                transaction.replace(R.id.containers,reservationDetailFragment);
                transaction.addToBackStack(null);
                transaction.commit();

                dismiss();
            }
        });

        closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = ((MainActivity)context).getSupportFragmentManager().beginTransaction();
                EventFragment eventFragment = new EventFragment();

                Bundle bundle = new Bundle();
                bundle.putInt("메인예약구별", uid);

                eventFragment.setArguments(bundle);
                transaction.replace(R.id.containers, eventFragment);
                transaction.addToBackStack(null);
                transaction.commit();

                dismiss();
            }
        });
    }

    public void onBackPressed() {
        FragmentTransaction transaction = ((MainActivity)context2).getSupportFragmentManager().beginTransaction();
        EventFragment eventFragment = new EventFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("메인예약구별", userId);

        eventFragment.setArguments(bundle);
        transaction.replace(R.id.containers, eventFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        dismiss();
    }
}